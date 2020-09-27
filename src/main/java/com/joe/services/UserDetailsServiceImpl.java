package com.joe.services;

import java.util.ArrayList;
import java.util.List;

import com.joe.config.SecurityUserDetails;
import com.joe.dao.UserDao;
import com.joe.domian.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
    protected static Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserDao userDao;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info(username);
        /*User user = new User();
        user.setUsername("张三");
        user.setPassword("38f4590d78232aae32a5b3d7d986f394");*/

        User user = userDao.getUserByuserName(username);

        logger.info("user={}", user);
        
        if (user != null) {
            //权限，应从数据库取这里写死
            List<GrantedAuthority> authorities= new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            SecurityUserDetails u = new SecurityUserDetails(user, authorities);
            
            logger.info(u.getPassword());
            return u;
        }
        throw new UsernameNotFoundException("用户(" + username + ")不存在");
    }
}