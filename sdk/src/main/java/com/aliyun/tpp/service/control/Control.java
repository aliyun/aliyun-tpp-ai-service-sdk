/**
 * author: oe
 * date:   2021/9/3
 * comment:控制型step，是一种特殊的step
 */
package com.aliyun.tpp.service.control;

import com.aliyun.tpp.service.step.Step;

public interface Control<STEP extends Step<CONTEXT, RESULT>, CONTEXT, RESULT> extends Step<CONTEXT, RESULT> {

    /**
     * 添加step，用来控制
     */
    void addStep(STEP step);

}
