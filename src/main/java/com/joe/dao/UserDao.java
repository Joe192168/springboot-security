package com.joe.dao;

import com.joe.domian.pojo.User;
import java.util.List;
import java.util.Map;

/**
 * @program: springboot-security
 * @description: 用户接口
 * @author: xiaoqiaohui
 * @create: 2020-09-25 19:19
 **/
public interface UserDao {

    List<User> getByMap(Map<String, Object> map);
    //List<User> getByRoleId(Map<String, Object> map);
    User getById(Integer id);
    Integer create(User user);
    int update(User user);
    User getByUserName(String userName);

}
