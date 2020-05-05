package com.czq.ssmdemo.Mapper;


import com.czq.ssmdemo.pojo.Person;
import org.apache.ibatis.annotations.*;
import org.apache.log4j.Logger;

import java.util.List;

@Mapper
public interface PersonMapper {

    @Insert("insert into person(name,temperature,date) values(#{name},#{temperature},#{date})")
    @Options(useGeneratedKeys=true, keyColumn="id",keyProperty = "id")
    Integer addRecord(Person person);

    @Select("select name,temperature,date from person where name=#{name}")
    List<Person> getRecord_by_name(String name);

    @Delete("delete from person where id=(select personid from taskid_click where taskid=#{taskid})")
    Integer deleteRecord_by_taskid(String taskid);

    @Select("select name,temperature,date from person where id=(select personid from taskid_click where taskid=#{taskid})")
    List<Person> getRecord_by_taskid(String taskid);
}
