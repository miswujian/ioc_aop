package com.wj.aop;

import com.wj.aop.advised.advisor.AspectJPointcut;
import com.wj.ioc.Life;
import com.wj.ioc.People;
import org.junit.Test;

/**
 * @Author wujian
 * @Date 2020/2/16 
 * @Version 1.0
 */
public class AspectJTest {
    
    @Test
    public void matchers() throws  Exception{
        String expression = "execution(* com.wj.aop.*.*(..))";
        AspectJPointcut aspectJPointcut = new AspectJPointcut();
        aspectJPointcut.setExpression(expression);
        System.err.println(aspectJPointcut.matchers(Life.class));
    }
    
    @Test
    public void matcher() throws  Exception{
        String expression = "execution(* com.wj.ioc.*.*(..))";
        AspectJPointcut aspectJPointcut = new AspectJPointcut();
        aspectJPointcut.setExpression(expression);
        System.err.println(aspectJPointcut.matcher(GraduationService.class.getDeclaredMethod("doGraduationProject"), GraduationService.class));
    }
    
}
