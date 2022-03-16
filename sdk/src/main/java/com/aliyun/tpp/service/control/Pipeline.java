/**
 * author: oe date:   2021/9/3 comment: step流水线，按顺序执行step
 */
package com.aliyun.tpp.service.control;

import com.aliyun.tpp.service.step.Step;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

/**
 * step流水线，按顺序执行step
 * 先addStep，再init，然后invoke 最后destroy
 */
public abstract class Pipeline<STEP extends Step<CONTEXT, RESULT>, CONTEXT, RESULT> implements
    Control<STEP, CONTEXT, RESULT> {

    @Getter
    private final List<STEP> stepList = new LinkedList<>();

    @Override
    public void addStep(STEP step) {

        stepList.add(step);
    }

    @Override
    public void init() {

        stepList.forEach(Step::init);
    }

    @Override
    public RESULT invoke(CONTEXT context) throws Exception {

        for (STEP step : stepList) {
            if (!step.skip(context)) {
                RESULT result = step.invoke(context);
                stepResult(context, result);
            }
        }
        return null;
    }

    /**
     * 每一个step调用完之后，对result做一些处理
     */
    protected abstract RESULT stepResult(CONTEXT context, RESULT result);

    @Override
    public void destroy() {

        stepList.forEach(Step::destroy);
    }
}
