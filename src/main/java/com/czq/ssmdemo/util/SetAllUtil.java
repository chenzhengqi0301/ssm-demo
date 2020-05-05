package com.czq.ssmdemo.util;

import com.czq.ssmdemo.pojo.ResponseResult;
import com.czq.ssmdemo.pojo.Taskid;

import java.util.Date;
import java.util.UUID;

/**
 * 用于封装Taskid和ResponseResult的赋值操作
 */
public class SetAllUtil {
    private Taskid Taskid;
    private ResponseResult res;
    private int code;
    private int personid;
    private String method;
    private String message;

    public SetAllUtil(Taskid taskid, ResponseResult res, int code, int personid, String method, String message
    ) {
        this.Taskid = taskid;
        this.res = res;
        this.code = code;
        this.personid = personid;
        this.method = method;
        this.message = message;
    }

    public void setall(){
        String taskid= UUID.randomUUID().toString();
        Date date=new Date(System.currentTimeMillis());
        Taskid.setTaskid(taskid);
        Taskid.setCode(code);
        Taskid.setPersonid(personid);
        Taskid.setMethod(method);
        Taskid.setDate(date);
        res.setCode(code);
        res.setMessage(message);
        res.setTaskid(taskid);
    }

    public com.czq.ssmdemo.pojo.Taskid getTaskid() {
        return Taskid;
    }

    public void setTaskid(com.czq.ssmdemo.pojo.Taskid taskid) {
        Taskid = taskid;
    }

    public ResponseResult getRes() {
        return res;
    }

    public void setRes(ResponseResult res) {
        this.res = res;
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

    public void setPersonid(int personid) {
        this.personid = personid;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getmessage
            () {
        return message
                ;
    }

    public void setmessage
            (String message
            ) {
        this.message
                = message
        ;
    }
}
