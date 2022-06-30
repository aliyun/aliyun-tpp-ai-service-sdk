/**
 * author: oe
 * date:   2022/5/27
 * comment:dubbo 泛化调用
 */
package com.aliyun.tpp.service.dubbo;

import com.aliyun.tpp.service.inner.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
/**
 * 泛化调用dubbo provider
 * <p>
 * TPP方案代码使用的一些限制：
 * 注册中心：tpp内暂时不支持注册中心，建议给您的provider添加slb，consumer调用时直接连slb即可。
 * 泛化调用：tpp只支持consumer用泛化的方式调用provider，泛化有多种类型，暂时只测试过generic="true"
 * protocol 协议：tpp暂时只测试过dubbo，且序列化不支持protobuf。
 * <p>
 * 如果您用到sae，千万要注意"sae内置的注册中心"只适用于consumer和provider都在sae的情况
 * 参考 https://dubbo.apache.org/zh/docs/advanced/generic-reference/
 */
public interface GenericService extends Service<GenericServiceConfig> {
    @Override
    default void setConfig(GenericServiceConfig config) {
        setGenericServiceConfig(config);
    }

    void setGenericServiceConfig(GenericServiceConfig config);

    @PostConstruct
    void init();

    @PreDestroy
    void destroy();

    /**
     * 同步调用
     */
    Object invoke(String method, String[] parameterTypes, Object[] args) throws Exception;

    /**
     * 异步调用，暂不支持
     */
//    CompletableFuture<Object> invokeAsync(String method, String[] parameterTypes, Object[] args) throws Exception;
}
