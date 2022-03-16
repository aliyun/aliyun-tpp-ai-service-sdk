/**
 * author: oe date:   2021/8/30 comment:
 */
package com.aliyun.tpp.service.redis;

import com.aliyun.tpp.service.inner.ServiceConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedisConfig implements ServiceConfig {

    //redis实例的连接地址
    //如果代码运行环境和redis在同一vpc的同一可用区，可以使用使用【专有网络】
    //否则请开通redis的【公网访问】，使用【公网访问】地址
    private String host;
    //redis实例的连接密码
    //如果代码运行环境和redis在同一vpc的同一可用区，可以设置【免密访问】，不使用密码也能访问
    private String password;
    //redis连接端口
    private int port = 6379;
    //redis连接超时时间, 单位ms
    private int timeout = 3000;
    //redis连接获取时间, 单位ms
    private int maxWaitMillis = 1000;
    //最大空闲连接数，需自行评估，不超过Redis实例的最大连接数
    private int maxIdleConnectionCount = 256;
    //最大连接数，需自行评估，不超过Redis实例的最大连接数。
    private int maxTotalConnectionCount = 256;

    public boolean isValid() {

        return host != null && host.trim().length() > 0 && password != null && password.trim().length() > 0 && port > 0
            && timeout > 0 && maxWaitMillis > 0 && maxIdleConnectionCount > 0 && maxTotalConnectionCount > 0;

    }
}
