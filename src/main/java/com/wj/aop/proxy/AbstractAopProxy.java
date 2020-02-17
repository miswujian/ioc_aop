package com.wj.aop.proxy;

import com.wj.aop.advised.AdvisedSupport;

/**
 * @Author wujian
 * @Date 2020/2/15 20:06
 * @Version 1.0
 * 默认Aop代理实现 配置aop支持
 */
public abstract class AbstractAopProxy implements AopProxy {

    /**
     * 给实现此类的子类调用
     */
    protected AdvisedSupport advisedSupport;
    
    public AbstractAopProxy(AdvisedSupport advisedSupport){
        this.advisedSupport = advisedSupport;
    }
    
}
