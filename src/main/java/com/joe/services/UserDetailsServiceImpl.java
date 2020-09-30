package com.joe.services;

import java.util.ArrayList;
import java.util.List;
import com.joe.dao.PermissionDao;
import com.joe.dao.UserDao;
import com.joe.domian.dto.JwtUser;
import com.joe.domian.pojo.Permission;
import com.joe.domian.pojo.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Log4j2
@Service
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
            List<String> permList = new ArrayList<>();
            List<Permission> permissions = permissionDao.getByUserId(user.getId());
            for (Permission perm:permissions){
                permList.add(perm.getCode());
            }
            String[] array = new String[permissions.size()];
            permList.toArray(array);
            user.setPermissions(array);
            return new JwtUser(user);
        } else {
            throw new UsernameNotFoundException("user: " + username + " do not exist!");
        }
    }
}