/**
 * author: oe
 * date:   2022/2/23
 * comment:
 */
package com.aliyun.tpp.service.inner;

public interface Service<CONFIG extends ServiceConfig> {
    void setConfig(CONFIG config);
}
