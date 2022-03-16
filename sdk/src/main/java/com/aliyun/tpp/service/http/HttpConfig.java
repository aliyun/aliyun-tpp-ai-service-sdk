/**
 * author: oe
 * date:   2021/12/13
 * comment:http client 各项配置
 */
package com.aliyun.tpp.service.http;

import com.aliyun.tpp.service.inner.ServiceConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpConfig implements ServiceConfig {

    /**
     * 和服务端传输数据的时间 ms
     */
    private int socketTimeout = 2000;//
    /**
     * 和服务端建立连接的时间 ms
     */
    private int connectTimeout = 2000;//
    /**
     * 从连接池拿连接的时间 ms
     */
    private int connectionRequestTimeout = 500;//
    /**
     * 检查连接是否有效的间隔时间 ms
     */
    private int validateAfterInactivity = 2000;//
    /**
     * 客户端连接保活时间 ms
     */
    private int keepAliveTimeout = 6000;//

    public boolean isValid() {
        if (socketTimeout > keepAliveTimeout) {
            throw new IllegalArgumentException(String.format("socketTimeout%d>keepAliveTimeout%d", socketTimeout, keepAliveTimeout));
        }
        if (connectTimeout > keepAliveTimeout) {
            throw new IllegalArgumentException(String.format("connectTimeout%d>keepAliveTimeout%d", connectTimeout, keepAliveTimeout));
        }
        if (validateAfterInactivity > keepAliveTimeout) {
            throw new IllegalArgumentException(String.format("validateAfterInactivity%d>keepAliveTimeout%d", validateAfterInactivity, keepAliveTimeout));
        }
        if (validateAfterInactivity > keepAliveTimeout) {
            throw new IllegalArgumentException(String.format("validateAfterInactivity%d>keepAliveTimeout%d", validateAfterInactivity, keepAliveTimeout));
        }
        if (socketTimeout > keepAliveTimeout) {
            throw new IllegalArgumentException(String.format("socketTimeout%d>keepAliveTimeout%d", socketTimeout, keepAliveTimeout));
        }
        if (connectTimeout > keepAliveTimeout) {
            throw new IllegalArgumentException(String.format("connectTimeout%d>keepAliveTimeout%d", connectTimeout, keepAliveTimeout));
        }
        return true;
    }


    public enum DataFormat {
        JSON,
        FORM
    }
}
