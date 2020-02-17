package com.wj.aop.proxy.jdk;

import com.wj.aop.advised.AdvisedSupport;
import com.wj.aop.advised.advice.AbstractMethodInvocation;
import com.wj.aop.advised.MethodMatcher;
import com.wj.aop.proxy.AbstractAopProxy;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author wujian
 * @Date 2020/2/15 20:07
 * @Version 1.0
 * jdk代理 对象生成器
 */
public class JdkDynamicAopProxy extends AbstractAopProxy implements InvocationHandler {
    
    public JdkDynamicAopProxy(AdvisedSupport advisedSupport) {
        super(advisedSupport);
    }

    /**
     * 代理对象获取的实现
     * @return
     */
    @Override
    public Object getProxy() {
        //创新代理对象 当调用代理对象的方法时会回调第三个参数的invoke方法
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), advisedSupport.getTargetSource().getInterfaces(), this);
    }

    /**
     * 通知的织入 符合匹配规则则执行通知和方法 否则只执行方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MethodMatcher methodMatcher = advisedSupport.getMethodMatcher();
        //判断是否存在匹配机制 同时测试method是否符合匹配规则
        if(methodMatcher != null && methodMatcher.matcher(method, advisedSupport.getTargetSource().getTargerClass())){
            MethodInterceptor methodInterceptor = advisedSupport.getMethodInterceptor();
            // 将bean原始的method封装成methodInvocation 将生成的对象传给 Adivce（Interceptor） 实现类对象，执行通知逻辑  会调用proceed方法
            return methodInterceptor.invoke(new AbstractMethodInvocation(advisedSupport.getTargetSource().getBena(), method, args));
        }
        return method.invoke(advisedSupport.getTargetSource().getBena(),args);
    }
    
}
