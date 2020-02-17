package com.wj.aop.proxy.cglib;

import com.wj.aop.advised.AdvisedSupport;
import com.wj.aop.advised.MethodMatcher;
import com.wj.aop.advised.advice.AbstractMethodInvocation;
import com.wj.aop.proxy.AbstractAopProxy;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author wujian
 * @Date 2020/2/17 13:32
 * @Version 1.0
 * cglib动态代理
 */
public class CglibDynamicAopProxy extends AbstractAopProxy implements MethodInterceptor {
    public CglibDynamicAopProxy(AdvisedSupport advisedSupport) {
        super(advisedSupport);
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(advisedSupport.getTargetSource().getTargerClass());
        enhancer.setCallback(this);//设置回调入口 会执行回调对象的intercept方法
        return enhancer.create();
    }

    /**
     * 回调地址
     * @param o
     * @param method
     * @param objects
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        MethodMatcher methodMatcher = advisedSupport.getMethodMatcher();
        if(methodMatcher != null && methodMatcher.matcher(method, advisedSupport.getTargetSource().getTargerClass())){
            return advisedSupport.getMethodInterceptor().invoke(new AbstractMethodInvocation(advisedSupport.getTargetSource().getBena(), method, objects));
        }
        return methodProxy.invoke(advisedSupport.getTargetSource().getBena(), objects);
    }
}
