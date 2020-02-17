package com.wj.ioc;

/**
 * @Author wujian
 * @Date 2020/2/17 0:27
 * @Version 1.0
 */
public class People {
    private String name;

    private String age;

    private String skill;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", skill='" + skill + '\'' +
                '}';
    }
}
