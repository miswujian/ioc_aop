package com.wj.aop.advised;

import java.lang.reflect.Method;

/**
 * @Author wujian
 * @Date 2020/2/16 13:12
 * @Version 1.0
 * 方法匹配接口
 */
public interface MethodMatcher {
    public boolean matcher(Method method, Class bean);
}
