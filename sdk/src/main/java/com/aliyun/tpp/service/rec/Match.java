/**
 * author: oe date:   2021/8/30 comment:召回
 */
package com.aliyun.tpp.service.rec;

import com.aliyun.tpp.service.step.Step;

import java.util.List;

public interface Match<CONTEXT, RESULT> extends Step<CONTEXT, List<RESULT>> {

    @Override
    default List<RESULT> invoke(CONTEXT context) throws Exception {

        return match(context);
    }

    List<RESULT> match(CONTEXT context) throws Exception;
}
