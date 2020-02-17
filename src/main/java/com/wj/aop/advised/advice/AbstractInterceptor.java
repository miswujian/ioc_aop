package com.wj.aop.advised.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @Author wujian
 * @Date 2020/2/16 13:44
 * @Version 1.0
 * 通知拦截器
 */
public class AbstractInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.err.println(invocation.getMethod().getName()+"前置通知");
        Object obj = invocation.proceed();
        System.err.println(invocation.getMethod().getName()+"后置通知");
        return obj;
    }
}
