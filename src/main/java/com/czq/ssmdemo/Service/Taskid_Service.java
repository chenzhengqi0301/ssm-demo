package com.czq.ssmdemo.Service;

import com.czq.ssmdemo.Mapper.TaskidMapper;
import com.czq.ssmdemo.pojo.Taskid;
import com.czq.ssmdemo.Service.ex.RecordNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Taskid_Service {
    private static Logger logger=Logger.getLogger(String.valueOf(Taskid_Service.class));
    @Autowired(required = false)
    private TaskidMapper taskidMapper;

    public Integer generate_task(Taskid taskid){
        return taskidMapper.generate_task(taskid);
    }

    public void clearPersonid(String taskid){
        taskidMapper.clearPersonid(taskid);
    }

    public boolean isPost(String taskid)throws RecordNotFoundException{
        List<Taskid> list=taskidMapper.getTask_by_taskid(taskid);
        if(list.size()==0) throw new RecordNotFoundException("该taskid不存在！");
        return ("POST".equals(list.get(0).getMethod()));
    }

    public int getPersonid(String taskid)throws RecordNotFoundException{
        List list=taskidMapper.getTask_by_taskid(taskid);
        if(list.size()==0) throw new RecordNotFoundException("该记录已被删除！");
        return taskidMapper.getTask_by_taskid(taskid).get(0).getPersonid();
    }
}
