package com.wj.aop.advised;

import com.wj.ioc.factory.BeanFactory;

/**
 * @Author wujian
 * @Date 2020/2/16 18:40
 * @Version 1.0
 * 公开了AspectJ的调用上下文，并弄清楚来自同一切面的多个Advisor在AspectJ中的优先级规则
 */
public class AspectJAwareAdvisorAutoProxyCreator extends  AbstractAutoProxyCreator{
    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        super.setBeanFactory(beanFactory);
    }
}
