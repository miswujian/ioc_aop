package com.wj.aop.advised.advice;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * @Author wujian
 * @Date 2020/2/16 13:31
 * @Version 1.0
 * 默认方法调用器 proceed
 */
public class AbstractMethodInvocation implements MethodInvocation {
    
    private Object targerBean;
    
    private Method method;
    
    private Object[] args;

    public AbstractMethodInvocation(Object targerBean, Method method, Object[] args) {
        this.targerBean = targerBean;
        this.method = method;
        this.args = args;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return args;
    }

    @Override
    public Object proceed() throws Throwable {
        return method.invoke(targerBean, args);
    }

    @Override
    public Object getThis() {
        return targerBean;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }
}
