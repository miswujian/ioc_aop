package com.wj.ioc;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wujian
 * @Date 2020/2/15 13:59
 * @Version 1.0
 * 属性集合
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue propertyValue){
        this.propertyValueList.add(propertyValue);
    }

    public List<PropertyValue> getPropertyValues(){
        return this.propertyValueList;
    }

}
