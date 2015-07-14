/** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    ProjectName mybatisdemo1
 *    File Name   Log4jInitServlet.java 
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    Copyright (c) 2015 Darlen . All Rights Reserved. 
 *    注意： 本内容仅限于XXX公司内部使用，禁止转发
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 * */
package com.mybatis.servlet;

import com.mybatis.constansts.MybatisConstants;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Description：这是一个Servlet类，目的是用于设置输出log文件的目录
 * Created on  2015-06-05 上午8:19
 * -------------------------------------------------------------------------
 * 版本          修改时间              作者               修改内容 
 * 1.0.0        上午8:19              Darlen              create
 * -------------------------------------------------------------------------
 *
 * @author Darlen liu
 */
public class Log4jInitServlet extends HttpServlet{

    private static Logger logger = Logger.getLogger(Log4jInitServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        String prefix = config.getServletContext().getRealPath("/");
        String log4jLocation = config.getInitParameter("log4jInit");
        String absLog4jLocation = prefix+log4jLocation;
        logger.debug("###########The absolute log4j location is " + absLog4jLocation);
        Properties pro = new Properties();
        try {
            FileInputStream fi = new FileInputStream(absLog4jLocation);
            pro.load(fi);
            fi.close();
            //set the log file location(设置log位置)
            String logFileInfoLocation = prefix + pro.getProperty(MybatisConstants.log4jInfoLocation);
            String logFileDebugLocation = prefix + pro.getProperty(MybatisConstants.log4jDebugLocation);
            String logFileErrorLocation = prefix + pro.getProperty(MybatisConstants.log4jErrorLocation);
            pro.setProperty("log4j.appender.Info_File.File",logFileInfoLocation);
            pro.setProperty("log4j.appender.Info_File.File",logFileDebugLocation);
            pro.setProperty("log4j.appender.Info_File.File",logFileErrorLocation);
            logger.debug("##################Log File location:"+logFileInfoLocation);
            //设置log4j property
//            PropertyConfigurator.configure(pro);
        }catch (IOException e) {
           logger.error("####################Could not read the log4j.properties file ["+absLog4jLocation+"]");
           logger.error("####################Ignoring the configuration file ["+absLog4jLocation+"]");
        }
    }
}
