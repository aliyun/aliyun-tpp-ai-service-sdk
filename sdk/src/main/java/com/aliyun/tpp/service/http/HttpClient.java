/**
 * author: oe
 * date:   2021/12/13
 * comment:
 */
package com.aliyun.tpp.service.http;

import com.alibaba.fastjson.JSON;
import com.aliyun.tpp.service.inner.Service;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface HttpClient extends Service<HttpConfig> {

    @Override
    default void setConfig(HttpConfig httpConfig){
        setHttpConfig(httpConfig);
    }

    /**
     * 在init之前设置config
     */
    void setHttpConfig(HttpConfig httpConfig);

    /**
     * 根据config初始化client
     */
    @PostConstruct
    void init();

    /**
     * get请求
     *
     * @param uri     完整的请求地址 :http://127.0.0.1/a/b/c?userId=XX
     * @param headers 请求头 content-type:application/json
     * @param charset 解析返回结果的字符集
     */
    default String get(String uri, Charset charset, Header... headers) throws Exception {
        HttpGet httpGet = new HttpGet(uri);
        httpGet.setHeaders(headers);
        return service(httpGet, charset);
    }

    /**
     * post请求
     *
     * @param url     请求地址 :http://127.0.0.1/a/b/c
     * @param data    {userId:XX}
     * @param format
     * @param charset
     * @param headers
     */
    default String post(String url, Map<String, Object> data, HttpConfig.DataFormat format, Charset charset, Header... headers) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        String s = formatData(data, format, charset);
        httpPost.setEntity(new StringEntity(s));
        for (Header header : headers) {
            httpPost.addHeader(header);
        }
        return service(httpPost, charset);
    }

    /**
     * doService 执行http请求
     *
     * @param httpRequestBase
     * @param charset
     */
    String service(HttpRequestBase httpRequestBase, Charset charset) throws Exception;

    /**
     * 关闭httpClient
     */
    void close() throws IOException;

    /**
     * close ignore exception
     */
    @PreDestroy
    default void closeQuietly() {
        try {
            close();
        } catch (Exception e) {
            //ignore
        }
    }

    /**
     * 处理http请求参数，默认是JSON格式
     *
     * @param data    请求参数
     * @param format  格式：FORM-表单 JSON-toJson
     * @param charset
     */
    static String formatData(Map<String, Object> data, HttpConfig.DataFormat format, Charset charset) {
        String s = "";
        if (format == HttpConfig.DataFormat.FORM) {
            List<NameValuePair> params = new ArrayList();
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                params.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
            s = URLEncodedUtils.format(params, charset);
        } else {
            s = JSON.toJSONString(data);
        }
        return s;
    }


}
