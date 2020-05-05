package com.czq.ssmdemo.pojo;

import java.util.Date;

public class Taskid {
    private String taskid;
    private int code;
    private int personid;
    private String method;
    private Date date;

    public Taskid(String taskid, int code, int personid, String method, Date date) {
        this.taskid = taskid;
        this.code = code;
        this.personid = personid;
        this.method = method;
        this.date = date;
    }

    public Taskid() {

    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getPersonid() {
        return personid;
    }

    public void setPersonid(int person_id) {
        this.personid = person_id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
