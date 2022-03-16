/**
 * author: oe
 * date:   2022/2/23
 * comment:
 */
package com.aliyun.tpp.service.testing;

import com.aliyun.openservices.eas.predict.http.HttpConfig;
import com.aliyun.tpp.service.predict.PredictClient;
import com.aliyun.tpp.service.predict.PredictConfig;

import java.util.concurrent.atomic.AtomicBoolean;

public class PredictClientForTesting implements PredictClient {
    //predictClient配置
    private PredictConfig predictConfig;
    //inner predictClient
    private com.aliyun.openservices.eas.predict.http.PredictClient predictClient;
    //初始化标识
    private transient AtomicBoolean initial = new AtomicBoolean(false);

    public PredictClientForTesting() {
    }

    @Override
    public void setPredictConfig(PredictConfig predictConfig) {
        if (predictConfig.isValid()) {
            this.predictConfig = predictConfig;
        } else {
            throw new IllegalArgumentException("predictConfig is invalid");
        }
    }

    @Override
    public void init() {
        if (initial.compareAndSet(false,true)) {
            HttpConfig httpConfig = new HttpConfig();
            httpConfig.setConnectTimeout(predictConfig.getConnectTimeout());
            httpConfig.setReadTimeout(predictConfig.getReadTimeout());
            httpConfig.setRequestTimeout(predictConfig.getRequestTimeout());
            this.predictClient = new com.aliyun.openservices.eas.predict.http.PredictClient(httpConfig);
            this.predictClient.setEndpoint(predictConfig.getHost());
            this.predictClient.setToken(predictConfig.getToken());
            this.predictClient.setModelName(predictConfig.getModel());
        }
    }

    @Override
    public void shutdown() {
        if (this.predictClient!=null) {
            this.predictClient.shutdown();
        }
        this.initial.compareAndSet(true, false);
    }

    @Override
    public byte[] predict(byte[] requestContent) throws Exception {
        return this.predictClient.predict(requestContent);
    }
}
