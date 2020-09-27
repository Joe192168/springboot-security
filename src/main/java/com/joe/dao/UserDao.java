package com.joe.dao;

import com.joe.domian.pojo.User;
import com.joe.mapper.MyRowMapper;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @program: springboot-security
 * @description:
 * @author: xiaoqiaohui
 * @create: 2020-09-25 19:19
 **/
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User getUserByuserName(String userName){
        String sql = "select * from t_user username = ?";
        User user =  jdbcTemplate.queryForObject(sql,new MyRowMapper(),userName);
        if (user == null){
            return null;
        }
        return user;
    }

}
