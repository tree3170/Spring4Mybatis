/** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *     ProjectName mybatisdemo1
 *    File Name   UserServiceI.java 
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    Copyright (c) 2015 Darlen . All Rights Reserved. 
 *    注意： 本内容仅限于XXX公司内部使用，禁止转发
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 * */
package com.mybatis.service;


import com.mybatis.model.User;

import java.util.List;

/**
 * Description.
 *
 * Created on  2015-02-03 下午11:02
 * -------------------------------------------------------------------------
 * 版本          修改时间              作者               修改内容 
 * 1.0.0        下午11:02              Darlen              create
 * -------------------------------------------------------------------------
 *
 * @author Darlen liu
 */
public interface UserServiceI {
    public User getUserById(int id);
    public User getUserByName(String name);
    public List<User> getAllUser();
    public int insertUser(User user) throws Exception;
    public int updByUserID(User user);
    public int delUserByID(int id);
    public int batchDelUserByIDs(List<String> ids);

}
