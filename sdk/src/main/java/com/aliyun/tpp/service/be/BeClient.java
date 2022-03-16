/**
 * author: oe
 * date:   2022/2/23
 * comment:
 */
package com.aliyun.tpp.service.be;

import com.aliyun.tpp.service.inner.Service;
import com.aliyuncs.be.client.BeReadRequest;
import com.aliyuncs.be.client.BeResponse;
import com.aliyuncs.be.client.BeResult;
import com.aliyuncs.be.client.BeWriteRequest;
import com.aliyuncs.be.client.exception.InvalidParameterException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public interface BeClient extends Service<BeConfig> {
    @Override
    default void setConfig(BeConfig beConfig){
        setBeConfig(beConfig);
    }

    void setBeConfig(BeConfig beConfig);

    @PostConstruct
    void init();

    @PreDestroy
    void destroy();

    /**
     * 查询
     */
    BeResponse<BeResult> query(BeReadRequest request) throws InvalidParameterException;

    /**
     * 写入
     */
    BeResponse write(BeWriteRequest request) throws InvalidParameterException;

}
