package com.wj.aop;

import com.wj.aop.advised.AdvisedSupport;
import com.wj.aop.advised.MethodMatcher;
import com.wj.aop.advised.advice.AbstractInterceptor;
import com.wj.aop.proxy.TargetSource;
import com.wj.aop.proxy.jdk.JdkDynamicAopProxy;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @Author wujian
 * @Date 2020/2/16 
 * @Version 1.0
 */
public class JdkDynamicAopProxyTest {
    
    @Test
    public void getProxy()throws Exception{
        GraduationService graduationService = new GraduationServiceImpl();
        graduationService.doGraduationProject();
        System.out.println(graduationService.speed("0%"));

        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setMethodInterceptor(new AbstractInterceptor());
        TargetSource targetSource = new TargetSource( graduationService.getClass(), graduationService.getClass().getInterfaces(), graduationService);
        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodMatcher((Method method, Class bean) -> true);
        graduationService = (GraduationService)new JdkDynamicAopProxy(advisedSupport).getProxy();
        graduationService.doGraduationProject();
        System.out.println(graduationService.speed("1%"));
    }
    
}
