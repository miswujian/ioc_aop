package com.wj.ioc.factory;

import com.wj.aop.advised.advisor.BeanPostProcessor;
import com.wj.ioc.BeanDefinition;
import com.wj.ioc.BeanReference;
import com.wj.ioc.PropertyValue;
import com.wj.ioc.reader.XmlBeanDefinitionReader;
import org.springframework.aop.framework.autoproxy.AbstractAdvisorAutoProxyCreator;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Author wujian
 * @Date 2020/2/15 16:51
 * @Version 1.0
 * 类工厂接口的实现
 */
public class XmlBeanFactory implements BeanFactory {
    
    private Set<String> beanDefinitionNames = new HashSet<>();
    
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
    
    private XmlBeanDefinitionReader xmlBeanDefinitionReader;
    
    private List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();
    
    public XmlBeanFactory(String location) throws Exception{
        xmlBeanDefinitionReader = new XmlBeanDefinitionReader();
        loadBeanDefinitions(location);
    }

    /**
     * 加载beanDefinitions
     * @param location
     * @throws Exception
     */
    public void loadBeanDefinitions(String location) throws Exception{
        xmlBeanDefinitionReader.loadBeanDefinitions(location);//通过location加载并解析出beanDefinitions
        registerBeanDefinition();//beanDefinition注册
        postProcessBeanDefinitionRegistry();//调用beanDefinition后置处理器
    }

    /**
     * beanDefinition 导入
     */
    private void registerBeanDefinition(){
        for(Map.Entry<String, BeanDefinition> entry : xmlBeanDefinitionReader.getBeanDefinitionMap().entrySet()){
            String name = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();
            beanDefinitionMap.put(name, beanDefinition);
            beanDefinitionNames.add(name);
        }
    }

    /**
     * 用于beanDefinition的后置处理注册 用于衔接ioc和aop
     */
    private void postProcessBeanDefinitionRegistry() throws Exception{
        List beans = getBeansForType(BeanPostProcessor.class);
        for(Object bean : beans){
            addBeanPostProcessor((BeanPostProcessor)bean);
        }
    }

    /**
     * 增加后置处理器
     * @param beanPostProcessor
     */
    private void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        this.beanPostProcessorList.add(beanPostProcessor);
    }

    /**
     * 根据class类型获取beans
     * @param type
     * @return
     * @throws Exception
     */
    public List getBeansForType(Class type) throws Exception{
        List beans = new ArrayList();
        for(String beanName : beanDefinitionNames){
            //如果是后置处理器 则添加
            if(type.isAssignableFrom(beanDefinitionMap.get(beanName).getBeanClass())){
                beans.add(getBean(beanName));
            }
        }
        return beans;
    }

    /**
     * 获取bean对象
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public Object getBean(String name) throws Exception {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if(beanDefinition == null){
            throw new IllegalArgumentException(name + "不存在");
        }
        //第一次肯定是null 接下来就需要创建和初始化bean
        Object bean = beanDefinition.getBean();
        if(bean == null){//bean的初始化
            bean = createBean(beanDefinition);//创建bean且设置属性
            bean = initializeBeanPostProcessor(bean, name);
            beanDefinition.setBean(bean);//前面beanDefinition还未设置初始化后的bean 现在设置好
        }
        return bean;
    }

    /**
     * 给bean增加后置处理器操作
     * @param bean
     * @param name
     * @return
     * @throws Exception
     */
    private Object initializeBeanPostProcessor(Object bean, String name)throws  Exception{
        //bean初始化之前
        for(BeanPostProcessor beanPostProcessor : beanPostProcessorList){
            bean = beanPostProcessor.postProcessBeforeInitialization(bean, name);
        }
        for(BeanPostProcessor beanPostProcessor : beanPostProcessorList){
            bean = beanPostProcessor.postProcessAfterInitialization(bean, name);
        }
        return bean;
    }

    /**
     * 创建bean
     * @param beanDefinition
     * @return
     * @throws Exception
     */
    private Object createBean(BeanDefinition beanDefinition) throws Exception{
        Object bean = beanDefinition.getBeanClass().newInstance();
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }

    /**
     * 属性转移 首先先判断类的类型 因为继承BeanPostProcessor的子类同时也继承了BeanFactoryAware 
     * @param bean
     * @param beanDefinition
     */
    private void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception{
        if(bean instanceof BeanFactoryAware){
            //设置beanFactory
            ((BeanFactoryAware) bean).setBeanFactory(this);
        }
        for(PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()){
            String name = propertyValue.getName();
            Object value = propertyValue.getValue();
            //判断是否为依赖
            if(value instanceof BeanReference){
                //因为依赖类 只设置了name而没设置bean的值 所以需要获取bean
                value = getBean(((BeanReference) value).getName());
            }
            try {
                //配置method方法 setXXX 和 value的类型 用于给bean设置属性
                Method method = bean.getClass().getDeclaredMethod("set"+name.substring(0,1).toUpperCase()+name.substring(1), value.getClass());
                method.invoke(bean, value);
            }catch (NoSuchMethodException e){
                //如果通过method方法设置属性值失败 则使用直接对属性进行赋值
                Field field = bean.getClass().getDeclaredField(name);
                field.setAccessible(true);
                field.set(bean, value);
            }
        }
    }
    
}
