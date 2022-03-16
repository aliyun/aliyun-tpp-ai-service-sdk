/**
 * author: oe date:   2022/2/24 comment:
 */
package com.aliyun.tpp.service.inner;


import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ServiceLoader;

/**
 * 提供serviceLoader
 */
public class ServiceLoaderProvider {

    /**
     * 获取高权限serviceLoader
     * 此serviceLoader只能用来加载本sdk提供的类
     */
    static <SERVICE> ServiceLoader<SERVICE> getSuperLoader(Class<SERVICE> serviceClass) throws IllegalAccessException {
        //确保获取正确的classLoader
        if (serviceClass.getClassLoader() != ServiceLoaderProvider.class.getClassLoader()) {
            throw new IllegalAccessException(
                String.format("Class<%s> is not provided by [aliyun-tpp-ai-service-sdk]!", serviceClass.getName()));
        }
        if (System.getSecurityManager() != null) {
            return AccessController.doPrivileged((PrivilegedAction<ServiceLoader<SERVICE>>) () ->
                ServiceLoader.load(serviceClass, ServiceLoaderProvider.class.getClassLoader())
            );
        } else {
            return ServiceLoader.load(serviceClass, ServiceLoaderProvider.class.getClassLoader());
        }
    }

    /**
     * 方便的获取高权限serviceLoader
     * 出错改为抛 RuntimeException，编译期间可以不catch异常，更加便捷
     */
    public static <SERVICE> ServiceLoader<SERVICE> getSuperLoaderEasy(Class<SERVICE> serviceClass) {

        try {
            return getSuperLoader(serviceClass);
        } catch (Exception e) {
            throw new RuntimeException("getSuperLoader error", e);
        }
    }
}
