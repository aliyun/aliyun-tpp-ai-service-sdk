/**
 * author: oe
 * date:   2021/9/3
 * comment:可以提交到线程池的执行步骤
 */
package com.aliyun.tpp.service.step;

import lombok.AllArgsConstructor;

import java.util.concurrent.Callable;

@AllArgsConstructor
public class StepCallable<STEP extends Step<CONTEXT,RESULT>,CONTEXT ,RESULT> implements Callable<RESULT> {
    private CONTEXT context;

    private STEP step;

    @Override
    public RESULT call() throws Exception {
        return step.invoke(context);
    }
}
