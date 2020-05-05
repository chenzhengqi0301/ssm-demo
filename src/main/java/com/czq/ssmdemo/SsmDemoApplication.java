package com.czq.ssmdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.web.servlet.DispatcherServlet;


@SpringBootApplication
public class SsmDemoApplication {
    /**
     * 设置匹配.do后缀的请求
     * @param dispatcherServlet
     * @return
     */
//    @Bean
    public ServletRegistrationBean servletRegistrationBean(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean bean = new ServletRegistrationBean(dispatcherServlet);
        bean.addUrlMappings("*.do","*.html","/error");
        return bean;
    }

    public static void main(String[] args) {
        SpringApplication.run(SsmDemoApplication.class, args);
    }

}
