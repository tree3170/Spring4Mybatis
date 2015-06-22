package com.mybatis.dao;


import com.mybatis.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Description. 类似于DAO，主要是负责操纵数据库增删改查
 * Created on  2015-06-13 下午1:15
 * -----------------------------------------------------------------------------------
 * 版本          修改时间              作者               修改内容 
 * 1.0.0              下午1:15                    Darlen                    create
 * -----------------------------------------------------------------------------------
 *
 * @author Darlen liu
 */
public interface UserMapper extends BaseDao<User>{


}