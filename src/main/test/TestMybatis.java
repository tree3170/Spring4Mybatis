/** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    ProjectName mybatisdemo1
 *    File Name   TestMybatis.java 
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    Copyright (c) 2015 Darlen . All Rights Reserved. 
 *    注意： 本内容仅限于XXX公司内部使用，禁止转发
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 * */

import com.mybatis.model.User;
import com.mybatis.service.UserServiceI;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description 这是普通的方法做测试
 * Created on  2015-02-03 下午11:31
 * -------------------------------------------------------------------------
 * 版本          修改时间              作者               修改内容 
 * 1.0.0        下午11:31              Darlen              create
 * -------------------------------------------------------------------------
 *
 * @author Darlen liu
 */
public class TestMybatis {
    private  ApplicationContext ac ;
    private UserServiceI userServiceI;
    @Before
    public void before(){
         ac =  new ClassPathXmlApplicationContext( new String[]{"spring.xml","spring-mybatis.xml"});
         userServiceI = (UserServiceI)ac.getBean("userService");
    }

    @Test
    public void test1(){
        ApplicationContext ac =  new ClassPathXmlApplicationContext( new String[]{"spring.xml","spring-mybatis.xml"});
        UserServiceI userServiceI = (UserServiceI)ac.getBean("userService");
        User user = userServiceI.getUserById(1);
        System.out.println(user);
    }

    @Test
    public void test2(){
        User user = userServiceI.getUserById(1);
        System.out.println(user.getName());
    }
}
