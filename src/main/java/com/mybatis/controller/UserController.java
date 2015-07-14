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
import com.mybatis.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
     ********return JSON.toJSONString(user);
     ********因为在spring-mvc.xml配置了返回JSON格式，所以不用再另外需要转换为JSON格式了，
     ********不然在前台获取的时候需要多转换一次JSON对象$.parseJSON()
     * @param id
     * @param request
     * @return 用户对象：后台会直接给转换了JSON对象然后再传到前台
     */
    @ResponseBody
    @RequestMapping("/{id}/showUser2")
    public User showUser2(@PathVariable String id, HttpServletRequest request){
        User  user = userServiceI.getUserById(Integer.valueOf(id));

        return user;
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
     * RESTFUL 形式访问http://localhost:8080/mybatisdemo1/userController/getAllUserWithPage.do?pageNum=1&pageSize=10
     * @param request
     * @return
     */
    @RequestMapping("/getAllUserWithPage")
    public String getAllUserWithPage(HttpServletRequest request){

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
            request.setAttribute("errorMsg", "查询出错:" + e.getMessage());
        }
        JSON.toJSONString("");
        request.setAttribute("pageNum", pageNum);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("user",users);
        return "fenYeDemo";
    }

    /**
     * 更新用户
     * @return 1:success;0 fail
     */
    @ResponseBody
    @RequestMapping("/updateUser")
    public String updateUser(HttpServletRequest request){
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        String returnValue = "1";
        try {
            User oldUser = userServiceI.getUserById(Integer.valueOf(id));
            logger.info("OldUser:"+JSON.toJSONStringWithDateFormat(oldUser,"yyyy-MM-dd HH:mm:ss"));
            oldUser.setName(name);
            oldUser.setPwd(pwd);
            //oldUser.setUpdateTime(new Date().getTime());
            int num = userServiceI.updByUserID(oldUser);

          if(1!= num){
              returnValue="0";
          }
        } catch (Exception e) {
            //TODO 加上删除用户的详细信息
            logger.error("更新用户失败",e);
            request.setAttribute("errorMsg", "查询出错:" + e.getMessage());
            returnValue = e.getMessage();
        }
        return returnValue;
    }

    /**
     * RESTFUL 形式访问http://localhost:8080/mybatisdemo1/userController/29/delUser.do?pageNum=1&pageSize=10
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/{id}/delUser")
    public String delUser(@PathVariable String id, HttpServletRequest request){
        String str_pageNum = request.getParameter("pageNum");
        String str_pageSize = request.getParameter("pageSize");
        try {
            PageHelper.startPage(Integer.valueOf(str_pageNum), Integer.valueOf(str_pageSize));
            int num =  userServiceI.delUserByID(Integer.valueOf(id));
            if(1 == num) {
                //TODO 加上删除用户的详细信息
                logger.info("删除用户成功："+id);
                List<User> list =  userServiceI.getAllUser();
                PageInfo<User> page = new PageInfo(list,3);
                request.setAttribute("page", page);
            }else{
                logger.error("删除用户失败："+id);
                throw new Exception("删除用户数量为"+num);
            }
        } catch (Exception e) {
            //TODO 加上删除用户的详细信息
            logger.error("删除用户失败",e);
            request.setAttribute("errorMsg", "删除出错:" + e.getMessage());
        }
        return "fenYeDemo";
    }

    /**
     * RESTFUL 形式访问http://localhost:8080/mybatisdemo1/userController/batchDelUsers.do
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/batchDelUsers")
    public int batchDelUsers( HttpServletRequest request){
       int retVal = 1;
        try {
            String str_pageNum = request.getParameter("pageNum");
            String str_pageSize = request.getParameter("pageSize");
            String userIDs = request.getParameter("userIDs");
            if(StringUtil.isEmpty(str_pageNum) || StringUtil.isEmpty(str_pageSize)
                    || StringUtil.isEmpty(userIDs)){
                logger.error("pageNum 或 pageSize 或 UserIDs不能为空。");
                throw new Exception("pageNum 或 pageSize 或 UserIDs不能为空。");
            }
            PageHelper.startPage(Integer.valueOf(str_pageNum), Integer.valueOf(str_pageSize));
            int num =  userServiceI.batchDelUserByIDs(Arrays.asList(userIDs.split(",")));
            if(num > 0 ) {
                logger.info("删除"+num+"个用户成功" + userIDs);
                List<User> list =  userServiceI.getAllUser();
                PageInfo<User> page = new PageInfo(list,3);
                request.setAttribute("page", page);
            }else{
                logger.error("删除多个用户失败");
                throw new Exception("删除用户数量为"+num);
            }
        } catch (Exception e) {
            //TODO 加上删除用户的详细信息
            logger.error("删除多个用户失败",e);
            retVal = 0;
            request.setAttribute("errorMsg", "查询出错:" + e.getMessage());
        }
        return retVal;
    }

    /**
     * 为国际化设置session级别的locale
     * @param request
     * @param model
     * @param langType  默认是en
     * @return
     */
    @RequestMapping(value ="/globalI18n",method = {RequestMethod.GET})
    public String globalI18n(HttpServletRequest request,Model model, @RequestParam(value="lang", defaultValue="en") String langType){
        Object obj = WebUtils.getSessionAttribute(request, SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
        if(null != request.getParameter("lang") ){//如果传递了参数lang,则需要设置locale into session
            if(langType.equals("zh")){
                Locale locale = new Locale("zh", "CN");
                request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,locale);
            }
            else if(langType.equals("en")){
                Locale locale = new Locale("en", "US");
                request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,locale);
            }
            else{
                request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, LocaleContextHolder.getLocale());
            }
        }else if(null == obj){//如果session is empty,则默认设置英文为当前locale
            Locale locale = new Locale("en", "US");
            request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,locale);
        }
        return "WEB-INF/jsp/finalExample";
    }



}
