package com.joe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: springboot-security
 * @description:
 * @author: xiaoqiaohui
 * @create: 2020-09-27 17:18
 **/
@Controller
public class IndexController {

    @RequestMapping(value={"/user/login", "/"})
    public String login() {
        return "/user/login";
    }

    @RequestMapping(value = "/login-success",produces = {"text/plain;charset=UTF-8"})
    public String loginSuccess(){
        return "/user/success";
    }

}
