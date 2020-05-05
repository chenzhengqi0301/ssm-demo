package com.czq.ssmdemo.Controller;

import com.czq.ssmdemo.pojo.Person;
import com.czq.ssmdemo.pojo.ResponseResult;
import com.czq.ssmdemo.pojo.Taskid;
import com.czq.ssmdemo.Service.Person_Service;
import com.czq.ssmdemo.Service.Taskid_Service;
import com.czq.ssmdemo.Service.ex.RecordNotFoundException;
import com.czq.ssmdemo.util.SetAllUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Map;

@Controller
public class MainController {
    private static Logger logger=Logger.getLogger(String.valueOf(MainController.class));
    @Autowired
    private Person_Service person_service;
    @Autowired
    private Taskid_Service taskid_Service;

    @RequestMapping(value = "/click_on.do",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity click_on(HttpServletRequest request){
        logger.info("收到GET请求");
        ResponseResult res=new ResponseResult();//包装返回体
        Taskid Taskid=new Taskid();//taskid_click的实体类
        SetAllUtil setAllUtil=null;//封装一系列操作的接口类
        Map<String,String[]> map=request.getParameterMap();//用于计算参数个数
        /*
        * 根据name查所有记录以及根据taskid查单条记录共用GET请求，根据参数进行区分
        * 需满足情况：name唯一非空或taskid唯一非空*/
        if(map.get("name")==null&&map.get("taskid")==null||map.size()!=1){
            setAllUtil=new SetAllUtil(Taskid,res,400,0,"GET","参数错误！");
            setAllUtil.setall();
            logger.warn("错误的GET请求：参数错误");
            logger.info("记录taskid："+Taskid.getTaskid()+" method：GET");
            taskid_Service.generate_task(Taskid);
            return new ResponseEntity<ResponseResult>(res, HttpStatus.BAD_REQUEST);
        }else if(request.getParameter("name")!=null){
            String out= null;
                out = person_service.getRecord_by_name(request.getParameter("name"));//执行select操作
                setAllUtil=new SetAllUtil(Taskid,res,200,0,"GET","success");
                setAllUtil.setall();//生成date、taskid、Taskid类、封装返回体等
//                setAllUtil=new SetAllUtil(Taskid,res,404,0,"GET",e.getMessage());
//                setAllUtil.setall();
//                logger.warn("错误的GET请求："+e.getMessage());
//                return new ResponseEntity(res,HttpStatus.NOT_FOUND);
//            finally {
//                logger.info("记录taskid：" + Taskid.getTaskid() + " method：GET");
//                //taskid_Service.generate_task(Taskid);//向数据库添加taskid记录
//            }
                return ResponseEntity.ok(out);//返回name对应所有记录

        }
        else{
            String taskid=request.getParameter("taskid");
            boolean isPost = false;
            try {
                isPost=taskid_Service.isPost(taskid);//判断是否为post记录
            } catch (RecordNotFoundException e) {//taskid不存在报异常
                logger.warn("错误的GET请求："+e.getMessage());
                setAllUtil=new SetAllUtil(Taskid,res,404,0,"GET",e.getMessage());
                setAllUtil.setall();
                return new ResponseEntity(res,HttpStatus.NOT_FOUND);
            }
            if(!isPost){//非POST请求记录的taskid，无效
                logger.warn("错误的POST请求：非post记录的taskid-- "+taskid);
                setAllUtil=new SetAllUtil(Taskid,res,406,0,"GET","无效的taskid！");
                setAllUtil.setall();
                logger.info("记录taskid："+Taskid.getTaskid()+" method：GET");
                taskid_Service.generate_task(Taskid);
                return new ResponseEntity<ResponseResult>(res, HttpStatus.NOT_ACCEPTABLE);
            }else if (0==taskid_Service.getPersonid(taskid)){//是POST请求，但是记录已被DELETE删除
                logger.warn("错误的GET请求：记录已被删除");
                setAllUtil=new SetAllUtil(Taskid,res,404,0,"GET","该记录已被删除！");
                setAllUtil.setall();
                logger.info("记录taskid："+Taskid.getTaskid()+" method：GET");
                taskid_Service.generate_task(Taskid);
                return new ResponseEntity<ResponseResult>(res, HttpStatus.NOT_FOUND);
            }else{
                String result=person_service.getRecord_by_taskid(taskid);
                setAllUtil=new SetAllUtil(Taskid,res,200,0,"GET",result);
                setAllUtil.setall();
                logger.info("记录taskid："+Taskid.getTaskid()+" method：GET");
                taskid_Service.generate_task(Taskid);
                String out=res.getMessage();
                return ResponseEntity.ok(out);
            }
        }



    }
    @RequestMapping(value = "/click_on.do",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity click_on(Person person,HttpServletRequest request) throws ParseException {
        logger.info("收到POST请求");
        Taskid Taskid=new Taskid();
        SetAllUtil setAllUtil=null;
        ResponseResult res=new ResponseResult();
        Map<String,String[]> map=request.getParameterMap();
        /*post请求限制唯二两个参数*/
        if(map.size()!=2||person.getName()==null||person.getTemperature()==null){
            logger.warn("错误的POST请求：参数错误");
            setAllUtil=new SetAllUtil(Taskid,res,400,0,"POST","参数错误！");
            setAllUtil.setall();
            logger.info("记录taskid："+Taskid.getTaskid()+" method：POST");
            taskid_Service.generate_task(Taskid);
            return new ResponseEntity<ResponseResult>(res, HttpStatus.BAD_REQUEST);
        }
        /*添加person记录
        * 获取personid，添加taskid记录*/
        int result=person_service.addRecord(person);//insert操作
        int userid=person.getId();  //获取自增主键
        if(result==1){
            setAllUtil=new SetAllUtil(Taskid,res,200,userid,"POST","打卡成功！");
            setAllUtil.setall();
        }
        logger.info("记录taskid："+Taskid.getTaskid()+" method：POST");
        taskid_Service.generate_task(Taskid);
        return ResponseEntity.ok(res);
    }

    @RequestMapping(value = "/click_on.do",method=RequestMethod.DELETE)
    public ResponseEntity deleteRecord(HttpServletRequest request){
        logger.info("收到DELETE请求");
        SetAllUtil setAllUtil=null;
        ResponseResult res=new ResponseResult();
        Taskid Taskid=new Taskid();
        Map<String,String[]> map=request.getParameterMap();
        if(map.size()!=1||map.get("taskid")==null){
            logger.warn("错误的DELETE请求：参数错误");
            setAllUtil=new SetAllUtil(Taskid,res,400,0,"DELETE","参数错误！");
            setAllUtil.setall();
            logger.info("记录taskid："+Taskid.getTaskid()+" method：DELETE");
            taskid_Service.generate_task(Taskid);
            return new ResponseEntity<ResponseResult>(res, HttpStatus.BAD_REQUEST);
        }
        String taskid=request.getParameter("taskid");
        try {
            person_service.deleteRecord_by_taskid(taskid);
            setAllUtil=new SetAllUtil(Taskid,res,200,0,"DELETE","删除记录成功！");
            taskid_Service.clearPersonid(taskid);
            setAllUtil.setall();
        } catch (RecordNotFoundException e) {//taskid不存在，或者非POST请求生成，导致返回记录为空
            logger.warn("错误的DELETE请求：该taskid不存在或非post"+taskid);
            setAllUtil=new SetAllUtil(Taskid,res,406,0,"DELETE",e.getMessage());
            setAllUtil.setall();
            return new ResponseEntity<ResponseResult>(res,HttpStatus.NOT_ACCEPTABLE);
        }finally {
            logger.info("记录taskid："+Taskid.getTaskid()+" method：DELETE");
            taskid_Service.generate_task(Taskid);
        }
            return ResponseEntity.ok(res);
    }
}
