package com.czq.ssmdemo.Service;

import com.alibaba.fastjson.JSON;
import com.czq.ssmdemo.Mapper.PersonMapper;
import com.czq.ssmdemo.pojo.Person;
import com.czq.ssmdemo.Service.ex.RecordNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class Person_Service {
    private static Logger logger=Logger.getLogger(String.valueOf(Person_Service.class));
    @Autowired(required = false)
    private PersonMapper PersonMapper;

    public Integer addRecord(Person person){
        person.setDate(new Date(System.currentTimeMillis()));//插入日期
        int result=PersonMapper.addRecord(person);
        return  result;
    }

    public String getRecord_by_name(String name)throws RecordNotFoundException{
        /*未查到记录时，抛出RecordNotFoundException异常
        * 将结果集转换为JSON字符串输出，并格式化日期*/
        List list=PersonMapper.getRecord_by_name(name);
        if(list.size()==0) throw new RecordNotFoundException("用户不存在！");
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(int i = 0; i<list.size(); i++){
            Person p= (Person) list.get(i);
            HashMap map=new HashMap();
            map.put("name",p.getName());
            map.put("temperature",p.getTemperature());
            map.put("date",sf.format(p.getDate()));
            list.set(i,map);
            // p.setDate(sf.parse(sf.format(p.getDate())));
        }
        String out= JSON.toJSONString(list);
        return out;
    }

    public void deleteRecord_by_taskid(String taskid)throws RecordNotFoundException{
         int result=PersonMapper.deleteRecord_by_taskid(taskid);
         if(result==0) throw new RecordNotFoundException("无效的taskid！");
    }

    public String getRecord_by_taskid(String taskid)throws RecordNotFoundException{
        List list= PersonMapper.getRecord_by_taskid(taskid);
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(list.size()==0) throw new RecordNotFoundException("该taskid不存在！");
        for(int i = 0; i<list.size(); i++){
            Person p= (Person) list.get(i);
            HashMap map=new HashMap();
            map.put("name",p.getName());
            map.put("temperature",p.getTemperature());
            map.put("date",sf.format(p.getDate()));
            list.set(i,map);
        }
        return JSON.toJSONString(list);
    }
}
