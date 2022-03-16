/**
 * author: oe
 * date:   2021/9/7
 * comment:abfs特征存储配置
 */
package com.aliyun.tpp.service.abfs;

import com.aliyun.tpp.service.inner.ServiceConfig;
import lombok.Data;

@Data
public class ABFSConfig implements ServiceConfig {
    private String src;
    private String endpoint;
    private String userName;
    private String userPasswd;

    public boolean isValid() {
        if (src != null && !src.isEmpty() &&
                endpoint != null && !endpoint.isEmpty() &&
                userName != null && !userName.isEmpty() &&
                userPasswd != null && !userPasswd.isEmpty()) {
            return true;
        }
        return false;
    }
}
