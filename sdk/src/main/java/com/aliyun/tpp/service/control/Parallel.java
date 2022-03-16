/**
 * author: oe date:   2021/9/3 comment:step并发，同时执行多个可并发的step
 */
package com.aliyun.tpp.service.control;


import com.aliyun.tpp.service.current.NamedThreadFactory;
import com.aliyun.tpp.service.current.NamedThreadPoolExecutorConfig;
import com.aliyun.tpp.service.proxy.ProxyEdenLoader;
import com.aliyun.tpp.service.step.Step;
import com.aliyun.tpp.service.step.StepCallable;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;

import lombok.Getter;

/**
 * comment:step并发，同时执行多个可并发的step 先addStep，再init，然后invoke 最后destroy
 */
public abstract class Parallel<STEP extends Step<CONTEXT, RESULT>, CONTEXT, RESULT> implements
    Control<STEP, CONTEXT, RESULT> {

    @Getter
    private final List<STEP> stepList = new LinkedList<>();

    private final NamedThreadPoolExecutorConfig config;

    //并发线程池
    private ExecutorService pool;

    public Parallel(NamedThreadPoolExecutorConfig config) {

        this.config = config;
    }

    @Override
    public void addStep(STEP step) {

        stepList.add(step);
    }

    @Override
    public void init() {

        pool = AccessController.doPrivileged((PrivilegedAction<ThreadPoolExecutor>) () -> new ThreadPoolExecutor(
            config.getCorePoolSize(),
            config.getMaximumPoolSize(),
            config.getKeepAliveTime(),
            config.getTimeUnit(),
            new ArrayBlockingQueue<>(config.getWorkerSize()),
            new NamedThreadFactory(config.getName())));

        stepList.forEach(Step::init);
    }

    @Override
    public RESULT invoke(CONTEXT context) throws Exception {
        //记录future
        Map<String, Future<RESULT>> futureMap = new TreeMap<>();
        stepList.stream()
            .filter(step -> !step.skip(context))
            .forEach(step -> {
                final RunnableFuture<RESULT> wrapFuture = ProxyEdenLoader.proxyEden.createMonitorTask(
                    new StepCallable<>(context, step));
                pool.execute(wrapFuture);
                futureMap.put(step.getClass().getName(), wrapFuture);
            });
        return getResult(context, futureMap);
    }

    @Override
    public void destroy() {

        pool.shutdown();
        stepList.forEach(Step::destroy);
    }

    /**
     * 每一个step提交到线程池后，记录下future，最后从future中获取结果，做合并或者其它操作
     */
    protected abstract RESULT getResult(CONTEXT context, Map<String, Future<RESULT>> futureMap) throws Exception;
}
