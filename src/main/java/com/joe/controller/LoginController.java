package com.joe.controller;

import com.joe.common.entity.Result;
import com.joe.common.entity.ResultCode;
import com.joe.services.MyInvocationSecurityMetadataSourceServiceImpl;
import com.joe.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Administrator
 * @version 1.0
 **/
@RestController
public class LoginController {

    @Autowired
    MyInvocationSecurityMetadataSourceServiceImpl metadataSourceService;

    /**
     * 测试资源1
     * @return
     */
    @GetMapping(value = "/auth/r1")
    //@PreAuthorize("hasAuthority('USER')")
    public Result r1(){
        return new Result(ResultCode.SUCCESS,"访问资源1");
    }

    /**
     * 测试资源2
     * @return
     */
    @GetMapping(value = "/auth/r2")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public Result r2(){
        return new Result(ResultCode.SUCCESS,"访问资源2");
    }

    /**
     * 刷新权限
     * @return
     */
    @GetMapping(value = "/perm/refresh")
    public Result refresh(){
        metadataSourceService.loadResourceDefine();
        return new Result(ResultCode.SUCCESS,"刷新权限成功");
    }
}
