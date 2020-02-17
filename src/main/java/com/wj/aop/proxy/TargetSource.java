package com.wj.aop.proxy;

/**
 * @Author wujian
 * @Date 2020/2/15 21:25
 * @Version 1.0
 * 目标源类
 */
public class TargetSource {

    /**
     * 目标类
     */
    private Class<?> targerClass;

    /**
     * 目标接口
     */
    private Class<?>[] targerInterfaces;

    /**
     * bean
     */
    private Object bena;

    public TargetSource(Class<?> targerClass, Class<?>[] targerInterfaces, Object bena) {
        this.targerClass = targerClass;
        this.targerInterfaces = targerInterfaces;
        this.bena = bena;
    }

    public Class<?> getTargerClass() {
        return targerClass;
    }

    public Class<?>[] getInterfaces() {
        return targerInterfaces;
    }


    public Object getBena() {
        return bena;
    }

}
