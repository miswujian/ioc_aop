package com.wj.aop.advised;

import com.wj.aop.advised.advisor.AspectJPointcutAdvisor;
import com.wj.aop.advised.advisor.BeanPostProcessor;
import com.wj.aop.advised.advisor.PointcutAdvisor;
import com.wj.aop.proxy.ProxyFactory;
import com.wj.aop.proxy.TargetSource;
import com.wj.ioc.factory.BeanFactory;
import com.wj.ioc.factory.BeanFactoryAware;
import com.wj.ioc.factory.XmlBeanFactory;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.List;

/**
 * @Author wujian
 * @Date 2020/2/16 18:00
 * @Version 1.0
 * 通用自动代理创建器，它基于检测到的每个顾问程序为特定bean构建AOP代理
 */
public abstract class AbstractAutoProxyCreator implements BeanPostProcessor, BeanFactoryAware {
    private BeanFactory beanFactory;
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        System.err.println(beanName+" 初始化前回调入口");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        System.err.println(beanName+" 初始化后回调入口");
        //这个属于后置处理器  禁止套娃
        if(bean instanceof PointcutAdvisor){
            return bean;
        }
        //这个属于通知拦截器 同意禁止套娃
        if(bean instanceof MethodInterceptor){
            return bean;
        }
        /**
         * 通过getBeansForType获取切面顾问
         */
        List<AspectJPointcutAdvisor> aspectJPointcutAdvisors = ((XmlBeanFactory)beanFactory).getBeansForType(AspectJPointcutAdvisor.class);
        for(AspectJPointcutAdvisor aspectJPointcutAdvisor : aspectJPointcutAdvisors){
            //通过类匹配机制进行匹配 成功则进行通知织入
            if(aspectJPointcutAdvisor.getPointcut().getClassFilter().matchers(bean.getClass())){
                //这里proxy的设置为了提供给JdkDynamicAopProxy使用
                ProxyFactory proxy = new ProxyFactory();
                //设置代理对象需要执行的通知拦截器
                proxy.setMethodInterceptor((MethodInterceptor) aspectJPointcutAdvisor.getAdvice());
                //设置方法匹配机制 为了给invoke里作判断
                proxy.setMethodMatcher(aspectJPointcutAdvisor.getPointcut().getMethodMatcher());
                //生成目标源类对象
                TargetSource targetSource = new TargetSource(bean.getClass(), bean.getClass().getInterfaces(), bean);
                proxy.setTargetSource(targetSource);
                //todo 这里的代理还只能jdk动态代理 如果 出现People这种没有接口的 无法进行cglib代理 则会报错
                Object obj = proxy.getProxy();
                return obj;
            }
        }
        //匹配失败 没有一个匹配机制符合
        return bean;
    }
}
