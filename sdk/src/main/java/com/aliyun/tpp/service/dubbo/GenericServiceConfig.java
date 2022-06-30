/**
 * author: oe
 * date:   2022/5/27
 * comment:
 */
package com.aliyun.tpp.service.dubbo;

import com.aliyun.tpp.service.inner.ServiceConfig;
import lombok.Data;

/**
 * 泛化调用配置
 * <p>
 * TPP方案代码使用的一些限制：
 * 注册中心：tpp内暂时不支持注册中心、配置中心、元数据中心，建议给您的provider添加slb，consumer调用时直接连slb即可。
 * 泛化调用：tpp只支持consumer用泛化的方式调用provider，泛化有多种类型，暂时只测试过generic="true"
 * protocol 协议：tpp暂时只测试过dubbo，且序列化不支持protobuf。
 * <p>
 * 参考文档 https://dubbo.apache.org/zh/docs/references/xml/
 */
@Data
public class GenericServiceConfig implements ServiceConfig {
    private String url;//绕过注册中心 直联服务端机器，不填表示不直联
    protected int timeout = 100; //远程服务调用超时时间(毫秒)
    protected int retries = 0; //远程服务调用重试次数，不包括第一次调用，不需要重试请设为0,仅在cluster为failback/failover时有效
    private String group;//服务分组，当一个接口有多个实现，可以用分组区分
    private String interfaceName;//要调用的服务名称 notNull
    private String version;//要调用的服务版本 notNull
    private String generic;//泛化调用方式 notNull
    private String applicationName;//dubbo:application name notNull

    @Override
    public boolean isValid() {
        if (applicationName == null || applicationName.isEmpty() ||
                interfaceName == null || interfaceName.isEmpty() ||
                version == null || version.isEmpty() ||
                generic == null || generic.isEmpty() ||
                timeout <= 0 ||
                retries < 0) {
            return false;
        }
        return true;
    }
}
