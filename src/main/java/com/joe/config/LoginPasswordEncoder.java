package com.joe.config;

import com.joe.utils.MD5Util;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * <p>Description: 自定义加密方式</p>
 */
public class LoginPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return MD5Util.encode((String)rawPassword);
    }

    //登陆时，输入的密码和数据库中的md5加密进行比对
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(MD5Util.encode((String)rawPassword));
    }
}