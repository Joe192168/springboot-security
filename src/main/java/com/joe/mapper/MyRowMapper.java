package com.joe.mapper;

import com.joe.domian.pojo.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 实现RowMapper接口，返回User对象
 * */
public class MyRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
//        获取结果集中的数据
        String id = resultSet.getString("id");
        String username = resultSet.getString("username");
        Integer islocked = resultSet.getInt("islocked");
        String fullname = resultSet.getString("fullname");
        String mobile = resultSet.getString("mobile");
//        把数据封装成User对象
        User user = new User();
        user.setUsername(username);
        user.setFullname(fullname);
        user.setMobile(mobile);
        user.setIslocked(islocked);
        return user;
    }
}