/** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    ProjectName mybatisdemo1
 *    File Name   TestSpringMybatis.java 
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    Copyright (c) 2015 Darlen . All Rights Reserved. 
 *    注意： 本内容仅限于XXX公司内部使用，禁止转发
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 * */

import com.alibaba.fastjson.JSON;
import com.mybatis.model.User;
import com.mybatis.service.UserServiceI;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Description 这是利用spring test的方法做测试
 *
 * Created on  2015-02-05 下午10:04
 * -------------------------------------------------------------------------
 * 版本          修改时间              作者               修改内容 
 * 1.0.0        下午10:04              Darlen              create
 * -------------------------------------------------------------------------
 *
 * @author Darlen liu
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml","classpath:spring-mybatis.xml"})
public class TestSpringMybatis {
    private static final Logger logger = Logger.getLogger(TestSpringMybatis.class);
    private UserServiceI userServiceI;

    public UserServiceI getUserServiceI() {
        return userServiceI;
    }

    @Autowired
    public void setUserServiceI(UserServiceI userServiceI) {
        this.userServiceI = userServiceI;
    }


    @Test
    public void test(){
        User user = userServiceI.getUserById(1);
        logger.info(JSON.toJSONString(user));
        logger.info(JSON.toJSONStringWithDateFormat(user,"yyyy-MM-dd HH:mm:ss"));
    }

    @Test
    public void testGetAllUser(){
        List<User> userList = userServiceI.getAllUser();
        logger.info(JSON.toJSONStringWithDateFormat(userList,"yyyy-MM-dd HH:mm:ss"));
    }
}
