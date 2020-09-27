package com.joe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: springboot-security
 * @description:
 * @author: xiaoqiaohui
 * @create: 2020-09-24 19:06
 **/
@SpringBootApplication
@MapperScan("com.joe")
public class SpringBootSecurityApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityApp.class,args);
    }

}
