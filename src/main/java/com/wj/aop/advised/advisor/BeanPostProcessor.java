package com.wj.aop.advised.advisor;

/**
 * @Author wujian
 * @Date 2020/2/16 17:38
 * @Version 1.0
 * bean的后置处理器 是一个监听器 可以监听容器触发的事件 在aop中的作用是为了跟ioc协作 需要在ioc中注册
 * 两个方法的回调的触发都是和容器管理bean的声明周期相关的 接下来就看getBean秀操作了
 */
public interface BeanPostProcessor {
    /**
     * 在bean的初始化之前提供回调的入口
     * @param bean 实例化对象
     * @param beanName 对象名称
     * @return
     * @throws Exception
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception;

    /**
     * 在bean的初始化之后提供入口
     * @param bean 实例化对象
     * @param beanName 对象名称
     * @return
     * @throws Exception
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws Exception;
}
