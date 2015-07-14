/** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    ProjectName mybatisdemo1
 *    File Name   UserServiceImpl.java 
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    Copyright (c) 2015 Darlen . All Rights Reserved. 
 *    注意： 本内容仅限于XXX公司内部使用，禁止转发
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 * */
package com.mybatis.service;

import com.mybatis.dao.UserMapper;
import com.mybatis.exception.CommonException;
import com.mybatis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import java.util.List;

/**
 * Description.
 * Created on  2015-02-03 下午11:06
 * -------------------------------------------------------------------------
 * 版本          修改时间              作者               修改内容 
 * 1.0.0        下午11:06              Darlen              create
 * -------------------------------------------------------------------------
 *
 * @author Darlen liu
 */
@Service("userService")
public class UserServiceImpl implements  UserServiceI{
    @Autowired
    private UserMapper userMapper;


    @Override
    public User getUserById(int id) {
        return userMapper.getObjByID(id);
    }

    @Override
    public User getUserByName(String name) {
        return userMapper.getObjByName(name);
    }

    @Override
    public List<User> getAllUser() {
        return userMapper.getAllObjs();
    }

    @Override
    public int insertUser(User user) throws  Exception{
        int num = 0;
        try{
            num = userMapper.addObjSelective(user);
            //throw new Exception("测试事务回滚：抛出Exception，事务不会回滚####################see roll back or not.");
            throw new CommonException("测试事务回滚：抛出RuntimeException，事务会回滚####################see roll back or not.");
        }catch (Exception e){
            throw e;
        }
       // return num;
    }

    @Override
    public int updByUserID(User user) {
        return userMapper.updObjByIDSelective(user);
    }

    @Override
    public int delUserByID(int id) {
        return userMapper.delObjByID(id);
    }

    @Override
    public int batchDelUserByIDs(List<String> ids) {
        return userMapper.batchDelObjByIDs(ids);
    }



}
