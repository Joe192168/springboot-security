package com.joe.dao;

import com.joe.domian.pojo.Role;

import java.util.List;

/**
 * 查询角色
 */
public interface RoleDao {

    List<Role> getRoleByUserName(String username);

}
