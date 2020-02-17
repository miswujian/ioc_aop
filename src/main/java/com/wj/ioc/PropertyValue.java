package com.wj.ioc;

/**
 * @Author wujian
 * @Date 2020/2/15 14:00
 * @Version 1.0
 * 属性键值对
 */
public class PropertyValue {

    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

}
