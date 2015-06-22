/** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    ProjectName mybatisdemo1
 *    File Name   UserController.java 
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    Copyright (c) 2015 Darlen . All Rights Reserved. 
 *    注意： 本内容仅限于XXX公司内部使用，禁止转发
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 * */
package com.mybatis.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mybatis.model.User;
import com.mybatis.service.UserServiceI;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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
    private static Logger logger = Logger.getLogger(UserController.class);

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

    @RequestMapping("/searchUser")
    public String getUser(@RequestParam(value="userID") String id,Model model ){
        User  user = new User();
        try{
            user = userServiceI.getUserById(Integer.valueOf(id));
            model.addAttribute("user",user);
        }catch (Exception e) {
            model.addAttribute("errorMsg",e.getMessage());
            logger.error(e);
        }
        logger.info("######################User ID is " + id + ":" + user );
        return "showUser";
    }

    /**
     * RESTFUL 形式访问http://localhost:8080/userController/1/showUser2.do
     * @param request
     * @return
     */
    @RequestMapping("/getAllUserWithPage")
    public String getAllUser2(HttpServletRequest request){

        String str_pageNum = request.getParameter("pageNum");
        String str_pageSize = request.getParameter("pageSize");
        int pageNum = 0,pageSize = 0;
        try {
            pageNum = Integer.parseInt(str_pageNum);
        } catch (NumberFormatException e) {
            request.setAttribute("err", "页码只能是大于0的整数，请重新输入!");
        }
        try {
            pageSize = Integer.parseInt(str_pageSize);
        } catch (NumberFormatException e) {
            request.setAttribute("err", "页面大小只能是大于0的整数，请重新输入!");
        }
        List<User> users = new ArrayList<User>();
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<User> list =  userServiceI.getAllUser();
            PageInfo<User> page = new PageInfo(list,3);
            request.setAttribute("page", page);
        } catch (Exception e) {
            request.setAttribute("err", "查询出错:" + e.getMessage());
        }
        JSON.toJSONString("");
        request.setAttribute("user",users);
        return "fenYeDemo";
    }
}
