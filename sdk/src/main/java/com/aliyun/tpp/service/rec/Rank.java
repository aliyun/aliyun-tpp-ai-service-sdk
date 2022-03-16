/**
 * author: oe date:   2021/8/30 comment:排序
 */
package com.aliyun.tpp.service.rec;


import com.aliyun.tpp.service.step.Step;

import java.util.List;

public interface Rank<CONTEXT, RESULT> extends Step<CONTEXT, List<RESULT>> {

    @Override
    default List<RESULT> invoke(CONTEXT context) throws Exception {

        return rank(context);
    }

    List<RESULT> rank(CONTEXT context) throws Exception;
}
