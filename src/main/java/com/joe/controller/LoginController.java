package com.joe.controller;

import com.joe.common.entity.Result;
import com.joe.common.entity.ResultCode;
import com.joe.config.MyInvocationSecurityMetadataSourceService;
import com.joe.domian.dto.JwtUser;
import com.joe.services.UserDetailsServiceImpl;
import com.joe.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 * @version 1.0
 **/
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    MyInvocationSecurityMetadataSourceService metadataSourceService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

//    @PostMapping(value={"/login"})
//    public Result login(@Validated @RequestBody String username, HttpServletRequest request) {
//        JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(username);
//        final String token = JwtTokenUtils.createToken(jwtUser,true);
//        return new Result(ResultCode.SUCCESS,token);
//    }

    @GetMapping(value = "/login-success",produces = {"text/plain;charset=UTF-8"})
    public Result loginSuccess(){
        return new Result(ResultCode.SUCCESS,"success");
    }

    /**
     * 测试资源1
     * @return
     */
    @GetMapping(value = "/r1",produces = {"text/plain;charset=UTF-8"})
    //@PreAuthorize("hasAuthority('USER')")
    public String r1(){
        return " 访问资源1";
    }

    /**
     * 测试资源2
     * @return
     */
    @GetMapping(value = "/r2",produces = {"text/plain;charset=UTF-8"})
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
