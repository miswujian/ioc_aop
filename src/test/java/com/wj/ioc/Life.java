package com.wj.ioc;

/**
 * @Author wujian
 * @Date 2020/2/17 0:27
 * @Version 1.0
 */
public class Life {
    private People people;
    
    private String direction;
    
    private String daily;

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDaily() {
        return daily;
    }

    public void setDaily(String daily) {
        this.daily = daily;
    }

    @Override
    public String toString() {
        return "Life{" +
                "people=" + people +
                ", direction='" + direction + '\'' +
                ", daily='" + daily + '\'' +
                '}';
    }
}
