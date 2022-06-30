/**
 * author: oe
 * date:   2022/5/27
 * comment:
 */
package com.aliyun.tpp.service.testing;

import com.aliyun.tpp.service.dubbo.GenericService;
import com.aliyun.tpp.service.dubbo.GenericServiceConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.*;
import org.apache.dubbo.rpc.service.GenericException;

import java.util.concurrent.atomic.AtomicBoolean;

public class GenericServiceForTesting implements GenericService {

    //service config
    private GenericServiceConfig config;
    //dubbo service
    private org.apache.dubbo.rpc.service.GenericService genericService;

    //初始化标识
    private transient AtomicBoolean initial = new AtomicBoolean(false);

    @Override
    public void setGenericServiceConfig(GenericServiceConfig config) {
        if (config.isValid()) {
            this.config = config;
        } else {
            throw new IllegalArgumentException("config is invalid");
        }
    }

    @Override
    public void init() {
        if (initial.compareAndSet(false, true)) {
            // 引用远程服务
            // 该实例很重量，里面封装了所有与注册中心及服务提供方连接
            ReferenceConfig<org.apache.dubbo.rpc.service.GenericService> reference = new ReferenceConfig<>();
            ApplicationConfig applicationConfig = new ApplicationConfig(config.getApplicationName());
            reference.setApplication(applicationConfig);
            reference.setInterface(config.getInterfaceName());
            reference.setVersion(config.getVersion());
            reference.setGeneric(config.getGeneric());
            reference.setTimeout(config.getTimeout());
            reference.setRetries(config.getRetries());
            if (StringUtils.isNoneBlank(config.getGroup())){
                reference.setGroup(config.getGroup());
            }
            //绕过注册中心 直联服务端机器
            if (StringUtils.isNoneBlank(config.getUrl())){
                reference.setUrl(config.getUrl());
            }
            genericService = reference.get();
        }

    }

    @Override
    public void destroy() {
        if (this.initial.compareAndSet(true, false)) {
            this.genericService = null;
        }
    }

    @Override
    public Object invoke(String method, String[] parameterTypes, Object[] args) throws GenericException {
        return genericService.$invoke(method, parameterTypes, args);
    }
}
