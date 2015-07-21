/** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    ProjectName mybatisdemo1
 *    File Name   GloabalHandlerException.java 
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    Copyright (c) 2015 Darlen . All Rights Reserved. 
 *    注意： 本内容仅限于XXX公司内部使用，禁止转发
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 * */
package com.mybatis.exception;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Description. 这是一个Spring全局的处理异常的类，用@ControllerAdvice，
 * 当发生异常时，在当前controller中找不到处理异常时
 * （@ExceptionHandler，详见ExceptionHandlerDemoController.java-->HandlerExceptionForCurClass()）
 * 则会在全局查找@ControllerAdvice，然后再处理异常
 * Created on  2015-07-21 下午11:23
 * -------------------------------------------------------------------------
 * 版本          修改时间              作者               修改内容 
 * 1.0.0        下午11:23              Darlen              create
 * -------------------------------------------------------------------------
 *
 * @author Darlen liu
 */
@ControllerAdvice
public class GloabalHandlerException {
    private final static Logger logger = Logger.getLogger(GloabalHandlerException.class);

    @ExceptionHandler({RuntimeException.class})
    public String HandlerExceptionForCurClass(Exception ex,HttpServletRequest request){
        logger.error("出异常了,采用Spring的@ControllerAdvice处理异常："+ex);
        request.setAttribute("exception",ex);
        return "WEB-INF/jsp/testExceptionHandlerDemo";
    }
}
