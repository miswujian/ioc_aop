package com.wj.aop.proxy;

import com.wj.aop.advised.AdvisedSupport;
import com.wj.aop.proxy.cglib.CglibDynamicAopProxy;
import com.wj.aop.proxy.jdk.JdkDynamicAopProxy;

/**
 * @Author wujian
 * @Date 2020/2/16 22:31
 * @Version 1.0
 * 代理工厂
 */
public class ProxyFactory extends AdvisedSupport implements AopProxy {

    /**
     * 获取代理对象 用于与Jdk动态代理解耦 后序实现了cglib动态代理 可以加个判断 如果类没有接口，则使用cglib
     * @return
     */
    @Override
    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    /**
     * 代理对象的生成都在JdkDynamicAopProxy
     * @return
     */
    private AopProxy createAopProxy() {
        if(((AdvisedSupport)this).getTargetSource().getInterfaces().length == 0 || 
                ((AdvisedSupport)this).getTargetSource().getInterfaces().length == 1 
                        && AopProxy.class.isAssignableFrom(((AdvisedSupport)this).getTargetSource().getInterfaces()[0])){
            return new CglibDynamicAopProxy(this);
        }else {
            return new JdkDynamicAopProxy(this);
        }
    }
    
}
