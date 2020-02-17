package com.wj.aop.advised.advisor;

import org.aopalliance.aop.Advice;
/**
 * @Author wujian
 * @Date 2020/2/15 21:03
 * @Version 1.0
 * 通知 + 切入点的适配器
 */
public interface Advisor {

    Advice getAdvice();

}
