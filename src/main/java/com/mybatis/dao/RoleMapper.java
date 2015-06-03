package com.mybatis.dao;


import com.mybatis.model.Role;

public interface RoleMapper {
    int insert(Role record);

    int insertSelective(Role record);
}