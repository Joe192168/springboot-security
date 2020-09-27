package com.joe.domian.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class User  implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */
    private String id;
    
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
    private Integer islocked;

    /**
     * 全称
     */
    private String fullname;

    /**
     * 手机号
     */
    private String mobile;

}