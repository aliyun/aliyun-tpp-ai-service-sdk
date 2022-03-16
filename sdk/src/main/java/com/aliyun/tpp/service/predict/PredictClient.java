/**
 * author: oe
 * date:   2022/2/23
 * comment:
 */
package com.aliyun.tpp.service.predict;

import com.aliyun.openservices.eas.predict.request.*;
import com.aliyun.openservices.eas.predict.response.*;
import com.aliyun.tpp.service.inner.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public interface PredictClient extends Service<PredictConfig> {

    @Override
    default void setConfig(PredictConfig predictConfig) {
        setPredictConfig(predictConfig);
    }

    /**
     * 在init之前设置config
     */
    void setPredictConfig(PredictConfig predictConfig);

    /**
     * 根据config初始化client
     */
    @PostConstruct
    void init();

    /**
     * 关闭
     */
    @PreDestroy
    void shutdown();

    /**
     * 预测
     *
     * @param requestContent
     */
    byte[] predict(byte[] requestContent) throws Exception;

    /**
     * 预测
     *
     * @param runRequest
     * @return BladeResponse
     */
    default BladeResponse predict(BladeRequest runRequest) throws Exception {
        BladeResponse runResponse = new BladeResponse();
        byte[] result = this.predict(runRequest.getRequest().toByteArray());
        if (result != null) {
            runResponse.setContentValues(result);
        }

        return runResponse;
    }

    /**
     * 预测
     *
     * @param runRequest
     * @return TFResponse
     */
    default TFResponse predict(TFRequest runRequest) throws Exception {
        TFResponse runResponse = new TFResponse();
        byte[] result = this.predict(runRequest.getRequest().toByteArray());
        if (result != null) {
            runResponse.setContentValues(result);
        }

        return runResponse;
    }

    /**
     * 预测
     *
     * @param runRequest
     * @return CaffeResponse
     */
    default CaffeResponse predict(CaffeRequest runRequest) throws Exception {
        CaffeResponse runResponse = new CaffeResponse();
        byte[] result = this.predict(runRequest.getRequest().toByteArray());
        if (result != null) {
            runResponse.setContentValues(result);
        }

        return runResponse;
    }

    /**
     * 预测
     *
     * @param requestContent
     * @return JsonResponse
     */
    default JsonResponse predict(JsonRequest requestContent) throws Exception {
        byte[] result = this.predict(requestContent.getJSON().getBytes());
        JsonResponse jsonResponse = new JsonResponse();
        if (result != null) {
            jsonResponse.setContentValues(result);
        }

        return jsonResponse;
    }

    /**
     * 预测
     *
     * @param runRequest
     * @return JsonResponse
     */
    default TorchResponse predict(TorchRequest runRequest) throws Exception {
        TorchResponse runResponse = new TorchResponse();
        byte[] result = this.predict(runRequest.getRequest().toByteArray());
        if (result != null) {
            runResponse.setContentValues(result);
        }

        return runResponse;
    }

    /**
     * 预测
     *
     * @param requestContent
     * @return JsonResponse
     */
    default String predict(String requestContent) throws Exception {
        byte[] result = this.predict(requestContent.getBytes());
        return result != null ? new String(result) : null;
    }
}
