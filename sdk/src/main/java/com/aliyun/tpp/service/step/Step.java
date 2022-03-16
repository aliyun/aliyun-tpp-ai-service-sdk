/**
 * author: oe date:   2021/9/3 comment: 执行步骤
 */
package com.aliyun.tpp.service.step;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public interface Step<CONTEXT, RESULT> {

    @PostConstruct
    default void init() {

    }

    //业务逻辑
    RESULT invoke(CONTEXT context) throws Exception;

    //是否跳过 true-跳过 不执行invoke
    default boolean skip(CONTEXT context) {

        return false;
    }

    @PreDestroy
    default void destroy() {

    }

}
