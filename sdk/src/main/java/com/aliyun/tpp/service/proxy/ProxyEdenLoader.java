package com.aliyun.tpp.service.proxy;

import com.aliyun.tpp.service.inner.ServiceLoaderProvider;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ServiceLoader;

/**
 * create-time: 2022/01/12 为了可以直接使用proxyEden的部分方法，提供了一个proxyEden实例 load proxy
 *
 * @author qixiang
 */
public class ProxyEdenLoader {

    /**
     * proxyEden实例
     */
    public final static ProxyEden proxyEden = doCreate(ServiceLoaderProvider.getSuperLoaderEasy(ProxyEden.class));


    /**
     * spi方式加载实际service,这些service都是代理对象，比原始service多了监控日志的功能
     */
    private static ProxyEden doCreate(ServiceLoader<ProxyEden> loader) throws RuntimeException {
        //service loader 需要从插件 classloader 中加载, 防止访问方案类加载器
        if (System.getSecurityManager() != null) {
            return AccessController.doPrivileged((PrivilegedAction<? extends ProxyEden>) () -> createService(loader));
        } else {
            return createService(loader);
        }
    }

    /**
     * spi方式获取实际的service
     */
    private static <SERVICE> SERVICE createService(ServiceLoader<SERVICE> loader) throws RuntimeException {

        SERVICE item = null;
        for (SERVICE next : loader) {
            if (next != null) {
                item = next;
                break;
            }
        }
        if (item != null) {
            return item;
        } else {
            throw new RuntimeException(
                String.format(
                    "%s load null! if you are do unit testing in local, please add ai-service-sdk-testing as dependency",
                    loader));

        }
    }
}