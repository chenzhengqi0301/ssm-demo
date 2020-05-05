package com.czq.ssmdemo.Controller;

import com.czq.ssmdemo.Service.ex.RecordNotFoundException;
import com.czq.ssmdemo.pojo.ResponseResult;
import com.czq.ssmdemo.pojo.Taskid;
import com.czq.ssmdemo.util.SetAllUtil;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@ControllerAdvice
public class ErrorExceptionHandler {
    @ExceptionHandler(RecordNotFoundException.class)
    public String HandleRecordNotFoundException(Exception e, HttpServletRequest request) {
        System.out.println("运行至此");
        Taskid Taskid = new Taskid();
        ResponseResult res = new ResponseResult();
        SetAllUtil setAllUtil = new SetAllUtil(Taskid, res, 404, 0, "GET", e.getMessage());
        setAllUtil.setall();
        request.setAttribute("javax.servlet.error.status_code", 404);
        ResponseEntity responseEntity = new ResponseEntity(res, HttpStatus.NOT_FOUND);
        return "forward:/error";
//        return new ResponseEntity(res, HttpStatus.NOT_FOUND);
    }

    @Component
    public class MyErrorAttribute extends DefaultErrorAttributes {
        @Override
        public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
            Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
            ResponseEntity responseEntity = (ResponseEntity) webRequest.getAttribute("res", 0);
            errorAttributes.put("res", responseEntity);
            errorAttributes.remove("trace");
            //errorAttributes.put("response",)
            return errorAttributes;


        }

    }
}
