/** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    ProjectName mybatisdemo1
 *    File Name   TestI18n.java 
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    Copyright (c) 2015 Darlen . All Rights Reserved. 
 *    注意： 本内容仅限于XXX公司内部使用，禁止转发
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 * */

import org.apache.log4j.Logger;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Description.
 * Created on  2015-07-01 下午9:41
 * -------------------------------------------------------------------------
 * 版本          修改时间              作者               修改内容 
 * 1.0.0        下午9:41              Darlen              create
 * -------------------------------------------------------------------------
 *
 * @author Darlen liu
 */
public class TestI18n {
    private static Logger logger = Logger.getLogger(TestI18n.class);
    //basename 必需是完整的路径
    private static String BASENAME = "com.mybatis.i18n.ssm";
    public  static void main(String args[]){
        try {
            //获取中问环境
            Locale locale1 = new Locale("zh", "CN");
            //通过web.xml中的基名信息取得ResourceBundle对象
            ResourceBundle resourceBundle1 = ResourceBundle.getBundle(BASENAME, locale1);
            //从文件中取得信息
            System.out.println("中国大陆环境："+resourceBundle1.getString("userName"));
            //美国
            Locale locale2 = new Locale("en", "US");
            ResourceBundle resourceBundle2 = ResourceBundle.getBundle(BASENAME, locale2);
            System.out.println("美国环境："+resourceBundle2.getString("userName"));
            //系统默认
            ResourceBundle resourceBundle3 = ResourceBundle.getBundle(BASENAME, Locale.getDefault());
            System.out.println("系统默认环境："+resourceBundle3.getString("userName"));
        }catch (Exception e){
            logger.error(e);
        }
    }
}
