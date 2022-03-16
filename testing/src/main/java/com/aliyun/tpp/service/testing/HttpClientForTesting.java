/**
 * author: oe
 * date:   2022/2/23
 * comment:
 */
package com.aliyun.tpp.service.testing;

import com.aliyun.tpp.service.http.HttpClient;
import com.aliyun.tpp.service.http.HttpConfig;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class HttpClientForTesting implements HttpClient {

    /**
     * http client 各项配置
     */
    private HttpConfig httpConfig;
    /**
     * 可关闭http协议客户端
     */
    private transient CloseableHttpClient closeableHttpClient;
    /**
     * 初始化标识
     */
    private transient AtomicBoolean initial = new AtomicBoolean(false);

    /**
     * new DefaultHttpClient().setHttpConfig().init()
     **/
    public HttpClientForTesting() {
    }

    public void setHttpConfig(HttpConfig httpConfig) {
        if (httpConfig.isValid()) {
            this.httpConfig = httpConfig;
        } else {
            throw new IllegalArgumentException("httpConfig is invalid");
        }
    }

    @PostConstruct
    public void init() {
        if (initial.compareAndSet(false, true)) {
            this.closeableHttpClient = HttpClients.custom()
                    .setConnectionManager(initPoolingHttpClientConnectionManager())
                    .setDefaultRequestConfig(initRequestConfig())
                    .setKeepAliveStrategy(initConnectionKeepAliveStrategy())
                    .disableContentCompression()
                    .evictExpiredConnections() //定期清理闲置连接
                    .evictIdleConnections(this.httpConfig.getKeepAliveTimeout(), TimeUnit.MILLISECONDS)
                    .build();
        }
    }

    private RequestConfig initRequestConfig() {
        return RequestConfig.custom()
                .setSocketTimeout(this.httpConfig.getSocketTimeout())
                .setConnectTimeout(this.httpConfig.getConnectTimeout())
                .setConnectionRequestTimeout(this.httpConfig.getConnectionRequestTimeout())
                .build();
    }

    private PoolingHttpClientConnectionManager initPoolingHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(200);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(20);
        poolingHttpClientConnectionManager.setValidateAfterInactivity(this.httpConfig.getValidateAfterInactivity());
        return poolingHttpClientConnectionManager;
    }

    private ConnectionKeepAliveStrategy initConnectionKeepAliveStrategy() {
        return new DefaultConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(
                    HttpResponse response,
                    HttpContext context) {
                long keepAlive = super.getKeepAliveDuration(response, context);
                if (keepAlive == -1) {
                    // Keep connections alive keepAliveTimeout seconds if a keep-alive value
                    // has not be explicitly set by the server
                    keepAlive = httpConfig.getKeepAliveTimeout();
                }
                return keepAlive;
            }
        };
    }

    public String service(HttpRequestBase httpRequestBase, Charset charset) throws Exception {
        CloseableHttpResponse response = this.closeableHttpClient.execute(httpRequestBase);
        if (response != null) {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    return EntityUtils.toString(entity, charset);
                }
            }
        }
        return null;
    }

    public void close() throws IOException {
        if (this.closeableHttpClient != null) {
            this.closeableHttpClient.close();
        }
        this.initial.compareAndSet(true, false);
    }
}
