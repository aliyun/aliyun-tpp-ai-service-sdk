/**
 * author: oe
 * date:   2021/9/3
 * comment:
 */
package com.aliyun.tpp.service.current;

import lombok.Data;

import java.util.concurrent.TimeUnit;

@Data
public class NamedThreadPoolExecutorConfig {
    private int corePoolSize = 2;
    private int maximumPoolSize =2;
    private long keepAliveTime = 60;
    private TimeUnit timeUnit=TimeUnit.SECONDS;
    private int workerSize = 20;
    private String name ;

    public NamedThreadPoolExecutorConfig(String name) {
        this.name = name;
    }
}
