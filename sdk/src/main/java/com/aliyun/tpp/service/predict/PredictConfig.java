/**
 * author: oe
 * date:   2021/8/30
 * comment:
 */
package com.aliyun.tpp.service.predict;

import com.aliyun.tpp.service.inner.ServiceConfig;
import lombok.Data;

@Data
public class PredictConfig implements ServiceConfig {
    //http://${host}/api/predict/${model}
    public String host ;
    public String model ;
    public String token ;
    private int connectTimeout = 1000;//ms
    private int readTimeout = 1000;//ms
    private int requestTimeout = 0;//ms

    public boolean isValid(){
        return this!=null &&
                host!=null && host.trim().length()>0 &&
                model!=null && model.trim().length()>0 &&
                token!=null && token.trim().length()>0 ;
    }
}
