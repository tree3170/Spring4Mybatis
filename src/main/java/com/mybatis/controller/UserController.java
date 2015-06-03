/** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    ProjectName mybatisdemo1
 *    File Name   UserController.java 
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    Copyright (c) 2015 Darlen . All Rights Reserved. 
 *    注意： 本内容仅限于XXX公司内部使用，禁止转发
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 * */
package com.mybatis.controller;

import com.mybatis.model.User;
import com.mybatis.service.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Description.
 * Created on  2015-02-05 下午10:20
 * -------------------------------------------------------------------------
 * 版本          修改时间              作者               修改内容 
 * 1.0.0        下午10:20              Darlen              create
 * -------------------------------------------------------------------------
 *
 * @author Darlen liu
 */
@Controller
@RequestMapping("/userController")
public class UserController {

    private UserServiceI userServiceI;

    public UserServiceI getUserServiceI() {
        return userServiceI;
    }
    @Autowired
    public void setUserServiceI(UserServiceI userServiceI) {
        this.userServiceI = userServiceI;
    }

    /**
     * 一般形式访问http://localhost:8080/userController/showUser.do?id=3
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/showUser")
    public String showUser(String id, HttpServletRequest request){
        User  user = userServiceI.getUserById(Integer.valueOf(id));
        request.setAttribute("user",user);
        return "showUser";
    }

    /**
     * RESTFUL 形式访问http://localhost:8080/userController/showUser1/1.do
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/showUser1/{id}")
    public String showUser1(@PathVariable String id, HttpServletRequest request){
        User  user = userServiceI.getUserById(Integer.valueOf(id));
        request.setAttribute("user",user);
        return "showUser";
    }

    /**
     * RESTFUL 形式访问http://localhost:8080/userController/1/showUser2.do
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/{id}/showUser2")
    public String showUser2(@PathVariable String id, HttpServletRequest request){
        User  user = userServiceI.getUserById(Integer.valueOf(id));
        request.setAttribute("user",user);
        return "showUser";
    }
}
