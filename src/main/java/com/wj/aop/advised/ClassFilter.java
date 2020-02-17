package com.wj.aop.advised;

/**
 * @Author wujian
 * @Date 2020/2/16 14:00
 * @Version 1.0
 * 类匹配
 */
public interface ClassFilter {

    Boolean matchers(Class beanClass) throws Exception;
    
}
