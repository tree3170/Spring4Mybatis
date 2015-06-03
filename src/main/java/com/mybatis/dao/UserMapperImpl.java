package com.mybatis.dao;


import com.mybatis.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserMapperImpl implements UserMapper{
    public int deleteByPrimaryKey(Integer id){
        return 1;
    }

    public int insert(User record){
        return 1;
    }

    public  int insertSelective(User record){
        return 1;
    }

    public User selectByPrimaryKey(Integer id){
        return new User();
    }
    public int updateByPrimaryKeySelective(User record){
        return 1;
    }

    public int updateByPrimaryKey(User record){
        return 1;
    }

    @Override
    public List<User> getAllUser() {
        return  new ArrayList<User>();
    }


}