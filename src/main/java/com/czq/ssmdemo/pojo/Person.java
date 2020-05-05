package com.czq.ssmdemo.pojo;

import java.util.Date;

public class Person {
    public Integer id;
    public String name;
    public String temperature;
    public Date date;

    public Person(Integer id, String name, String temperature, Date date) {
        this.id = id;
        this.name = name;
        this.temperature = temperature;
        this.date = date;
    }

    /*public Person(String name, String temperature, Date date) {
        this.name = name;
        this.temperature = temperature;
        this.date = date;
    }*/

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
