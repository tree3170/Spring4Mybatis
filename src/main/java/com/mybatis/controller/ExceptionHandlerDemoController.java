/** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    ProjectName mybatisdemo1
 *    File Name   ExceptionHandlerDemoController.java 
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    Copyright (c) 2015 Darlen . All Rights Reserved. 
 *    注意： 本内容仅限于XXX公司内部使用，禁止转发
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 * */
package com.mybatis.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Description. 这是一个测试ExceptionHandler的controller，当出现异常该如何处理
 * Created on  2015-07-21 下午10:28
 * -------------------------------------------------------------------------
 * 版本          修改时间              作者               修改内容 
 * 1.0.0        下午10:28              Darlen              create
 * -------------------------------------------------------------------------
 *
 * @author Darlen liu
 */
@Controller
@RequestMapping("/exceptionDemo")
public class ExceptionHandlerDemoController {
    private final static Logger logger = Logger.getLogger(ExceptionHandlerDemoController.class);

    /**
     * 1. 在 @ExceptionHandler 方法的入参中可以加入 Exception 类型的参数, 该参数即对应发生的异常对象
     * 2. @ExceptionHandler 方法的入参中不能传入 Map. 若希望把异常信息传导页面上, 需要使用 ModelAndView 作为返回值
     * 3. @ExceptionHandler 方法标记的异常有优先级的问题（默认会去找最接近的方法.e.g. 10/0,发生异常时会找ArithmeticException，如果没有再找RuntimeException）.
     * 4. @ControllerAdvice: 如果在当前 Handler 中找不到 @ExceptionHandler 方法来出来当前方法出现的异常,
     * 则将去 @ControllerAdvice 标记的类中查找 @ExceptionHandler 标记的方法来处理异常.
     */
//    @ExceptionHandler({ArithmeticException.class})
//    public String HandlerExceptionForCurClass(Exception ex,HttpServletRequest request){
//        logger.error("出异常了,采用Spring的@ControllerAdvice处理异常："+ex);
//        request.setAttribute("exception",ex);
//        return "WEB-INF/jsp/testExceptionHandlerDemo";
//    }

    @RequestMapping("/test")
    public String testHandlerException(@RequestParam(value = "param") int i ){
        logger.info("result [i]="+10/i);
        return "WEB-INF/jsp/testExceptionHandlerDemo";
    }

}
