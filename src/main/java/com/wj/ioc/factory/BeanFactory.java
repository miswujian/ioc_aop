package com.wj.ioc.factory;

/**
 * @Author wujian
 * @Date 2020/2/15 17:21
 * @Version 1.0
 * 类的工厂类 获取类对象
 */
public interface BeanFactory {
    public Object getBean(String name) throws Exception;
}
