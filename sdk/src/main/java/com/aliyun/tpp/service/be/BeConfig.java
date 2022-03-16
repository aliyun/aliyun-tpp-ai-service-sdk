/**
 * author: oe
 * date:   2021/9/4
 * comment:
 */
package com.aliyun.tpp.service.be;

import com.aliyun.tpp.service.inner.ServiceConfig;
import lombok.Data;

@Data
public class BeConfig implements ServiceConfig {

    //be实例的连接地址
    //如果代码运行环境和redis在同一vpc的同一可用区，可以使用[专有网络]
    //否则请开通[公网访问]，使用[公网访问]地址
    private String domain;
    //be实例的连接账号
    private String username;
    //be实例的连接密码
    private String password;
    //be连接端口
    private int port = 80;

    public boolean isValid() {
        if (domain != null && !domain.isEmpty() &&
                username != null && !username.isEmpty() &&
                password != null && !password.isEmpty() && port > 0) {
            return true;
        }
        return false;
    }
}
