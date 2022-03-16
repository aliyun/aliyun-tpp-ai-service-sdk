/**
 * author: oe date:   2021/8/31 comment: 重排
 */
package com.aliyun.tpp.service.rec;


import com.aliyun.tpp.service.step.Step;

import java.util.List;

public interface ReRank<CONTEXT, RESULT> extends Step<CONTEXT, List<RESULT>> {

    @Override
    default List<RESULT> invoke(CONTEXT context) throws Exception {

        return reRank(context);
    }

    List<RESULT> reRank(CONTEXT recommendContext) throws Exception;
}
