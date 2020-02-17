package com.wj.aop.advised.advisor;

import com.wj.aop.advised.ClassFilter;
import com.wj.aop.advised.MethodMatcher;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;
import org.aspectj.weaver.tools.ShadowMatch;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author wujian
 * @Date 2020/2/16 13:57
 * @Version 1.0
 * 切面 具体的匹配流程这里没实现 直接使用了现成的aspectj
 */
public class AspectJPointcut implements Pointcut, MethodMatcher, ClassFilter {

    /**
     * 切入点分析器 可以通过规则表达式获取切入表达式
     */
    private PointcutParser pointcutParser;

    /**
     * 规则表达式  execution(<修饰符模式>?<返回类型模式><方法名模式>(<参数模式>)<异常模式>?)  除了返回类型模式、方法名模式和参数模式外，其它项都是可选的
     */
    private String expression;

    /**
     * 切入表达式
     */
    private PointcutExpression pointcutExpression;

    /**
     * 封装好的元语 用作匹配的
     */
    private static final Set<PointcutPrimitive> PRIMITIVES = new HashSet<>();

    /**
     * 初始化默认受支持的元素
     */
    static {
        PRIMITIVES.add(PointcutPrimitive.EXECUTION);
        PRIMITIVES.add(PointcutPrimitive.ARGS);
        PRIMITIVES.add(PointcutPrimitive.REFERENCE);
        PRIMITIVES.add(PointcutPrimitive.THIS);
        PRIMITIVES.add(PointcutPrimitive.TARGET);
        PRIMITIVES.add(PointcutPrimitive.WITHIN);
        PRIMITIVES.add(PointcutPrimitive.AT_ANNOTATION);
        PRIMITIVES.add(PointcutPrimitive.AT_WITHIN);
        PRIMITIVES.add(PointcutPrimitive.AT_ARGS);
        PRIMITIVES.add(PointcutPrimitive.AT_TARGET);
    }

    public AspectJPointcut() {
        this(PRIMITIVES);
    }
    
    public AspectJPointcut(Set<PointcutPrimitive> primitives){
        pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(primitives);
    }

    /**
     * 匹配之前的操作
     */
    public void beforeMatche(){
        if(getExpression() == null){
            throw new IllegalStateException("缺少expression属性值");
        }
        /**
         * 如果切入表达式为空 则先获取
         */
        if(pointcutExpression == null){
            pointcutExpression = pointcutParser.parsePointcutExpression(expression);
        }
    }

    /**
     * 匹配方法
     * @param beanClass
     * @return
     * @throws Exception
     */
    @Override
    public Boolean matchers(Class beanClass) throws Exception {
        beforeMatche();
        return pointcutExpression.couldMatchJoinPointsInType(beanClass);
    }

    /**
     * 匹配方法
     * @param method
     * @param bean
     * @return
     */
    @Override
    public boolean matcher(Method method, Class bean) {
        beforeMatche();
        ShadowMatch shadowMatch = pointcutExpression.matchesMethodExecution(method);
        if (shadowMatch.alwaysMatches()) {
            return true;
        }
        else if (shadowMatch.neverMatches()) {
            return false;
        }
        return false;
    }

    @Override
    public ClassFilter getClassFilter() {
        return this;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
