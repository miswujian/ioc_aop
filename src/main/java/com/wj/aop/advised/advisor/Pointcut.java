package com.wj.aop.advised.advisor;

import com.wj.aop.advised.ClassFilter;
import com.wj.aop.advised.MethodMatcher;

/**
 * @Author wujian
 * @Date 2020/2/16 13:57
 * @Version 1.0
 * 切入点
 */
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
    
}
