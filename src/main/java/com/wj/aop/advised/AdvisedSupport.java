package com.wj.aop.advised;

import com.wj.aop.proxy.TargetSource;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * @Author wujian
 * @Date 2020/2/15 20:17
 * @Version 1.0
 * aop核心的配置  包含所有的Advisor 和 Advice
 */
public class AdvisedSupport {

    /**
     * 目标源
     */
    private TargetSource targetSource;

    /**
     * 方法拦截器
     */
    private MethodInterceptor methodInterceptor;

    /**
     * 方法匹配机制
     */
    private MethodMatcher methodMatcher;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
