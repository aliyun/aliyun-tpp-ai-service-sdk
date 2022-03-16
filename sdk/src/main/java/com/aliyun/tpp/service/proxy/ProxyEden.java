package com.aliyun.tpp.service.proxy;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

/**
 * create-time: 2022/01/12
 * <p>
 * create Proxy
 *
 * @author qixiang
 */
public interface ProxyEden {

    /**
     * create monitor enhanced client with inner api
     */
    <T> T createObjectProxyAttachServiceLog(T t);

    /**
     * create wrapped callable  with monitored enhanced
     */
    <T> RunnableFuture<T> createMonitorTask(Callable<T> run);
}
