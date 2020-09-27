package com.joe.services;

import java.util.ArrayList;
import java.util.List;
import com.joe.dao.PermissionDao;
import com.joe.dao.UserDao;
import com.joe.domian.pojo.Permission;
import com.joe.domian.pojo.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Log4j2
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDao userDao;
    @Autowired
    PermissionDao permissionDao;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(username);
        /*User user = new User();
        user.setUsername("张三");
        user.setPassword("38f4590d78232aae32a5b3d7d986f394");*/

        User user = userDao.getByUserName(username);
        if (user != null) {
            List<Permission> permissions = permissionDao.getByUserId(user.getId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (Permission permission : permissions) {
                if (permission != null && permission.getCode()!=null) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getCode());
                    //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            user.setGrantedAuthorities(grantedAuthorities);
            return user;
        } else {
            throw new UsernameNotFoundException("user: " + username + " do not exist!");
        }
    }
}