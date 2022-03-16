package com.aliyun.tpp.service.testing;

import com.aliyun.tpp.service.proxy.ProxyEden;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

/**
 * create-time: 2022/02/08
 *
 * 这是一个为单测实现的普通的 spi 实现,
 *
 * @author qixiang
 */
public class ProxyEdenForTesting implements ProxyEden {

    @Override
    public <T> T createObjectProxyAttachServiceLog(T t) {

        return t;
    }

    @Override
    public <T> RunnableFuture<T> createMonitorTask(Callable<T> run) {

        return new FutureTask<>(run);
    }
}
