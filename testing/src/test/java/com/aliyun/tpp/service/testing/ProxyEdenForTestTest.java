package com.aliyun.tpp.service.testing;

import com.aliyun.tpp.service.proxy.ProxyEden;
import com.aliyun.tpp.service.proxy.ProxyEdenLoader;
import com.aliyun.tpp.service.testing.ProxyEdenForTesting;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableFuture;

/**
 * create-time: 2022/02/08
 *
 * @author qixiang
 */
public class ProxyEdenForTestTest {

    /**
     * 测试用 provider 加载测试
     */
    @Test
    public void testingProviderShouldWork() throws InterruptedException {

        final ProxyEden proxyEden = ProxyEdenLoader.proxyEden;

        assert proxyEden instanceof ProxyEdenForTesting;

        final Object t = new Object();
        assert t == proxyEden.createObjectProxyAttachServiceLog(t);

        final RunnableFuture<Object> task = proxyEden.createMonitorTask(() -> {
            throw new IllegalStateException("error");
        });

        try {
            Executors.newSingleThreadExecutor().submit(task);
            task.get();
            Assert.fail("never reach here");
        } catch (ExecutionException e) {
            MatcherAssert.assertThat("should throw error in future", e.getCause(),
                IsInstanceOf.instanceOf(IllegalStateException.class));
        }
    }


}
