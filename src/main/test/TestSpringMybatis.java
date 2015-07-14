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
import com.mybatis.util.DateUtil;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @Before
    public void before(){
        System.setProperty("springmvc.root","F:\\java_workspace\\ssm\\mybatisdemo1\\src\\main\\webapp");
    }


    @Test
    public void testGetUserByID(){
        User user = userServiceI.getUserById(1);
        logger.info(JSON.toJSONString(user));
        logger.info(JSON.toJSONStringWithDateFormat(user,"yyyy-MM-dd HH:mm:ss"));
    }

    @Test
    public void testGetUserByName(){
        User user = userServiceI.getUserByName("test1");
        logger.info(JSON.toJSONStringWithDateFormat(user,"yyyy-MM-dd HH:mm:ss"));
    }

    @Test
    public void testGetAllUser(){
        try {
            List<User> userList = userServiceI.getAllUser();
            logger.info("============="+JSON.toJSONStringWithDateFormat(userList,"yyyy-MM-dd HH:mm:ss"));
            logger.error("#############"+userList.size());
            logger.info("############"+ System.getProperty("springmvc.root"));
          //  logger.info("############"+ System.getProperty("log4j.appender.Error_File.File"));
        }catch (Exception e){
            logger.info("############");
            //logger.info("############"+ System.getProperty("log4j.appender.Error_File.File"));
            logger.error(e);
        }
    }

    @Test
    public void testInsertUser(){
        int count = 0;
        User user = new User();
        try {
           // for(int i =40;i<41;i++){
                user.setName("testinsert"+41);
                user.setPwd("123");
               // user.setCreateTime(DateUtil.getCurDate(DateUtil.DEFAULT_DATETIME_FORMAT_SEC));
                logger.info("######DATETIME:"+new Date().getTime());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                user.setUpdateTime(sdf.format(new Date()));//new Timestamp(new Date().getTime())
                count = userServiceI.insertUser(user);
           // }

           // Assert.assertEquals("########not the expect result",1,count);
        }catch (Exception e){
           // Assert.assertEquals("########not the expect result",1,count);
            logger.info("############");
            //logger.info("############"+ System.getProperty("log4j.appender.Error_File.File"));
            logger.error(e);
        }
        logger.info("#############count:"+count);

    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = userServiceI.getUserByName("testinsert40");
        user.setPwd("125");
        //user.setCreateTime(new java.sql.Date(new Date().getTime()));
        user.setCreateTime(new java.sql.Date(new Date().getTime()));
       // user.setUpdateTime(DateUtil.getCurDate(DateUtil.DEFAULT_DATETIME_FORMAT_SEC));
       // user.setUpdateTime(new Timestamp(newUserRoleMapper Date().getTime()));
        //updatetime 是string类型，mybatis配置文件不配类型，这样可以解决丢失时分秒的问题
        user.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        int num = userServiceI.updByUserID(user);
        logger.info("###########num="+num);
    }

    @Test
    public void testDelUser(){
        User user = userServiceI.getUserByName("testinsert");
        logger.info("============="+JSON.toJSONStringWithDateFormat(user,"yyyy-MM-dd HH:mm:ss"));
        int num = userServiceI.delUserByID(user.getId());
        logger.info("###########num="+num);
    }

    @Test
    public void testBatchDelUsers(){
        List ids = new ArrayList<String>();
        ids.add("7");
        ids.add("8");
        int num = userServiceI.batchDelUserByIDs(ids);
        logger.info("###########num="+num);
//         int num = userServiceI.delUserByID(user.getId());
//         logger.info("###########num="+num);
    }
}
