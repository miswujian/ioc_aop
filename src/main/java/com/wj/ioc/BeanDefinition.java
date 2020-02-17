package com.wj.ioc;

/**
 * @Author wujian
 * @Date 2020/2/15 14:01
 * @Version 1.0
 * 类的定义
 */
public class BeanDefinition {

    private String beanClassName;

    private Class beanClass;

    private Object bean;

    private PropertyValues propertyValues = new PropertyValues();

    public String getBeanClassName() {
        return beanClassName;
    }

    /**
     * 设置完beanClassName同时也把beanClass设置了
     * @param beanClassName
     */
    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
        try{
            this.beanClass = Class.forName(beanClassName);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}