package com.joe.controller;

import com.joe.config.MyInvocationSecurityMetadataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @version 1.0
 **/
@RestController
public class LoginController {

    @Autowired
    MyInvocationSecurityMetadataSourceService metadataSourceService;

    /**
     * 测试资源1
     * @return
     */
    @GetMapping(value = "/r/r1",produces = {"text/plain;charset=UTF-8"})
    //@PreAuthorize("hasAuthority('USER')")
    public String r1(){
        return " 访问资源1";
    }

    /**
     * 测试资源2
     * @return
     */
    @GetMapping(value = "/r/r2",produces = {"text/plain;charset=UTF-8"})
    //@PreAuthorize("hasAuthority('ADMIN')")
    public String r2(){
        return " 访问资源2";
    }

    /**
     * 刷新权限
     * @return
     */
    @GetMapping(value = "/refresh/",produces = {"text/plain;charset=UTF-8"})
    public String refresh(){
        metadataSourceService.loadResourceDefine();
        return "刷新权限成功";
    }
}
