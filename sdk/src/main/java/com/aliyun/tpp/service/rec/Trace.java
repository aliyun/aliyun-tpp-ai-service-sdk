/**
 * author: oe date:   2021/8/31 comment:埋点
 */
package com.aliyun.tpp.service.rec;


import com.aliyun.tpp.service.step.Step;

public interface Trace<CONTEXT> extends Step<CONTEXT, Boolean> {

    @Override
    default Boolean invoke(CONTEXT context) throws Exception {

        return trace(context);
    }

    boolean trace(CONTEXT context) throws Exception;
}
