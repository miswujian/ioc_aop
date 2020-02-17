package com.wj.aop.advised.advisor;

import org.aopalliance.aop.Advice;

/**
 * @Author wujian
 * @Date 2020/2/16 21:23
 * @Version 1.0
 */
public class AspectJPointcutAdvisor implements PointcutAdvisor{
    
    private AspectJPointcut aspectJPointcut = new AspectJPointcut();
    
    private Advice advice;

    public void setExpression(String expression) {
        aspectJPointcut.setExpression(expression);
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
    
    @Override
    public Pointcut getPointcut() {
        return aspectJPointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }
}
