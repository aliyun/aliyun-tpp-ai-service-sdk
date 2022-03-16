/**
 * author: oe date:   2022/2/23 comment:
 */
package com.aliyun.tpp.service.proxy;

import com.aliyun.tpp.service.inner.Service;
import com.aliyun.tpp.service.inner.ServiceConfig;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 提供用户需要使用的service
 *
 * 例如：redisClient代理 HttpClient代理 PredictClient代理
 */
public class ServiceProxyHolder {

    private static ConcurrentHashMap<String, Service> HOLDER = new ConcurrentHashMap<>();

    public static <SERVICE extends Service, CONFIG extends ServiceConfig> SERVICE getService(CONFIG config,
        ServiceLoader<SERVICE> loader) {

        String key = config.toString();
        SERVICE service = (SERVICE) HOLDER.get(key);
        if (service == null) {
            return (SERVICE) HOLDER.computeIfAbsent(key, (mapKey) -> {

                SERVICE newService = createService(config, loader);
                return ProxyEdenLoader.proxyEden.createObjectProxyAttachServiceLog(newService);
            });
        }
        return service;
    }

    private static <SERVICE extends Service, CONFIG extends ServiceConfig> SERVICE createService(CONFIG config,
        ServiceLoader<SERVICE> loader) throws RuntimeException {

        if (System.getSecurityManager() != null) {
            return AccessController.doPrivileged((PrivilegedAction<SERVICE>) () -> createServiceInner(config, loader));
        } else {
            return createServiceInner(config, loader);
        }
    }

    /**
     * spi方式获取实际的service
     */
    private static <SERVICE extends Service, CONFIG extends ServiceConfig> SERVICE createServiceInner(CONFIG config,
        ServiceLoader<SERVICE> loader) throws RuntimeException {

        SERVICE item = null;
        for (Service next : loader) {
            if (next != null) {
                item = (SERVICE) next;
                break;
            }
        }
        if (item != null) {
            item.setConfig(config);
            return item;
        } else {
            throw new RuntimeException(
                String.format(
                    "%s load null! if you are do unit testing in local, please add ai-service-sdk-testing as dependency",
                    loader.toString()));
        }
    }

}
