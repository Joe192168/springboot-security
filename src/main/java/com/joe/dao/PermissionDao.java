package com.joe.dao;

import com.joe.domian.pojo.Permission;

import java.util.List;
import java.util.Map;

/**
 * @program: springboot-security
 * @description: 查询权限
 **/
public interface PermissionDao {

    List<Permission> findAll();

    List<Permission> getByMap(Map<String, Object> map);

    Permission getById(Integer id);

    Integer create(Permission permission);

    int update(Permission permission);

    List<Permission> getByUserId(Integer userId);

}
