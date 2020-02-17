package com.wj.aop;

/**
 * @Author wujian
 * @Date 2020/2/16 23:12
 * @Version 1.0
 */
public class GraduationServiceImpl implements GraduationService {
    @Override
    public void doGraduationProject() {
        System.out.println("毕设好难啊");
    }

    @Override
    public String speed(String str) {
        return str;
    }
}
