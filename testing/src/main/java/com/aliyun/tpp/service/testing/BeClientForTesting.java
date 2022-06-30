/**
 * author: oe
 * date:   2022/2/23
 * comment:
 */
package com.aliyun.tpp.service.testing;

import com.aliyun.tpp.service.be.BeClient;
import com.aliyun.tpp.service.be.BeConfig;
import com.aliyuncs.be.client.BeReadRequest;
import com.aliyuncs.be.client.BeResponse;
import com.aliyuncs.be.client.BeResult;
import com.aliyuncs.be.client.BeWriteRequest;
import com.aliyuncs.be.client.exception.InvalidParameterException;

import java.util.concurrent.atomic.AtomicBoolean;

public class BeClientForTesting implements BeClient {
    //BE各项配置
    private BeConfig beConfig;

    private com.aliyuncs.be.client.BeClient beClient;

    //初始化标识
    private transient AtomicBoolean initial = new AtomicBoolean(false);

    public BeClientForTesting() {
    }

    @Override
    public void setBeConfig(BeConfig beConfig) {
        if (beConfig.isValid()) {
            this.beConfig = beConfig;
        } else {
            throw new IllegalArgumentException("beConfig is invalid");
        }
    }

    @Override
    public void init() {
        if (initial.compareAndSet(false, true)) {
            this.beClient = new com.aliyuncs.be.client.BeClient(beConfig.getDomain(), beConfig.getPort(), beConfig.getUsername(), beConfig.getPassword());
        }
    }

    @Override
    public void destroy() {
        if (this.initial.compareAndSet(true, false)) {
            this.beClient = null;
        }
    }

    @Override
    public BeResponse<BeResult> query(BeReadRequest request) throws InvalidParameterException {
        return this.beClient.query(request);
    }

    @Override
    public BeResponse write(BeWriteRequest request) throws InvalidParameterException {
        return this.beClient.write(request);
    }

}
