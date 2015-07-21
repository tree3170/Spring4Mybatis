/** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    ProjectName mybatisdemo1
 *    File Name   MessageSourceHelper.java 
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    Copyright (c) 2015 Darlen . All Rights Reserved. 
 *    注意： 本内容仅限于XXX公司内部使用，禁止转发
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 * */
package com.mybatis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * Description. 国际化信息工具类
 * Created on  2015-07-02 上午8:11
 * -------------------------------------------------------------------------
 * 版本          修改时间              作者               修改内容 
 * 1.0.0        上午8:11              Darlen              create
 * -------------------------------------------------------------------------
 *
 * @author Darlen liu
 */
@Service(value = "messageSourceHelper" )
public class MessageSourceHelper {
    @Autowired
    private static MessageSource messageSource;

    public void setMessageSource(ResourceBundleMessageSource messageSource) {
        MessageSourceHelper.messageSource = messageSource;
    }

    public static String  getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        String msg = messageSource.getMessage(code, args, defaultMessage, locale);
        return msg != null ? msg.trim() : defaultMessage;
    }
}
