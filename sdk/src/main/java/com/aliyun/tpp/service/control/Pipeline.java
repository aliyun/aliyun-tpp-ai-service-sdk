/**
 * author: oe date:   2021/9/3 comment: step流水线，按顺序执行step
 */
package com.aliyun.tpp.service.control;

import com.aliyun.tpp.service.step.Step;
import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        //记录流水线产出的所有result
        List<RESULT> resultList = new ArrayList<>(stepList.size());
        //执行流水线
        for (STEP step : stepList) {
            if (!step.skip(context)) {
                RESULT result = step.invoke(context);
                stepResult(context, result);
                resultList.add(result);
            }
        }
        //处理resultList
        RESULT result = getResult(context, resultList);
        return result;
    }

    /**
     * 每一个step调用完之后，对result做一些处理
     */
    protected abstract RESULT stepResult(CONTEXT context, RESULT result);

    /**
     * 所有step调用完后，对resultList做些处理
     * 注意：默认啥也不做，返回最后一个步骤的result
     */
    protected RESULT getResult(CONTEXT context, List<RESULT> resultList) {
        if (resultList == null) {
            return null;
        }
        List<RESULT> nonNullList = resultList.stream().filter(Objects::nonNull).collect(Collectors.toList());
        if (nonNullList == null || nonNullList.isEmpty()) {
            return null;

        }
        //返回最后一个步骤的result
        return nonNullList.get(nonNullList.size() - 1);
    }

    @Override
    public void destroy() {

        stepList.forEach(Step::destroy);
    }
}
