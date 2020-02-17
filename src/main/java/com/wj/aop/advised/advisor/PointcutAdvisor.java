package com.wj.aop.advised.advisor;

/**
 * @Author wujian
 * @Date 2020/2/16 21:24
 * @Version 1.0
 */
public interface PointcutAdvisor extends Advisor{
    
    Pointcut getPointcut();
    
}
