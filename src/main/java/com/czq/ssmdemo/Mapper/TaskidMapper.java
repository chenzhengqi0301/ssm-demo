package com.czq.ssmdemo.Mapper;

import com.czq.ssmdemo.pojo.Taskid;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.log4j.Logger;

import java.util.List;

@Mapper
public interface TaskidMapper {
    @Insert("insert into taskid_click(taskid,code,personid,method,date) values(#{taskid},#{code},#{personid},#{method},#{date})")
    Integer generate_task(Taskid taskid);

    @Update("update taskid_click set personid=0 where taskid=#{taskid}")
    Integer clearPersonid(String taskid);

    @Select("select * from taskid_click where taskid=#{taskid}")
    List<Taskid> getTask_by_taskid(String taskid);
}
