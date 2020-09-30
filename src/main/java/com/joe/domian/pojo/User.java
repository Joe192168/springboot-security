package com.joe.domian.pojo;

import lombok.Data;
import java.util.List;

@Data
public class User {

    /**
     * 用户id
     */
    private Integer id;
    
    /**
     * 用户密码
     */
    private String password;
    
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 是否被锁定 1:是 0:否
     */
    private Integer isLocked;

    /**
     * 全称
     */
    private String fullname;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 角色
     */
    private String[] roles;

    /**
     * 权限
     */
    private String[] permissions;
}