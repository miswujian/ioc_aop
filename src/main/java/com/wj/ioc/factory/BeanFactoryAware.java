package com.wj.ioc.factory;

import com.wj.ioc.factory.BeanFactory;

/**
 * @Author wujian
 * @Date 2020/2/16 17:52
 * @Version 1.0
 * 使得Bean在容器中有意识 就一个方法 setFactory
 */
public interface BeanFactoryAware {
    void setBeanFactory(BeanFactory beanFactory) throws Exception;
}

