package com.czq.ssmdemo.log;

import org.apache.log4j.Logger;

public class LogTest {
    private static Logger logger=Logger.getLogger(String.valueOf(LogTest.class));
    public void logMethod(){

        logger.info("这是info级别错误");
        logger.warn("这是warning级别错误");
    }

    public static void main(String[] args) {
        //logger.info("spring boot App is starting.......");
        //SpringApplication.run(SsmDemoApplication.class,args);
        //logger.info("spring boot App is running.......");
        LogTest test=new LogTest();
        test.logMethod();

    }
}
