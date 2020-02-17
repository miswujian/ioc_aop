package com.wj.ioc;

/**
 * @Author wujian
 * @Date 2020/2/15 14:02
 * @Version 1.0
 * 外部依赖类键值对
 */
public class BeanReference {
    
    private String name;
    
    private Object bean;
    
    public BeanReference(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }
}
