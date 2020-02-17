package com.wj.ioc.reader;

/**
 * @Author wujian
 * @Date 2020/2/16 17:10
 * @Version 1.0
 */
public interface BeanDefinitionReader {
    public void loadBeanDefinitions(String location)throws Exception;
}
