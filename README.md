# Spring4Mybatis
这是一个Spring MVC + Mybatis 的项目，不仅仅只是简单的demo或是增删改查，她将含括很多的方面：尽量抽取basedao，baseService公共部分，做出一个简单的framework，为以后分布式开发提供基础。 

##内容
>1. Spring的国际化
>2. Spring事务管理（默认只对RuntimeException或是其子类回滚）
>3. 异常处理（ExceptionHandler）
>4. JMS消息处理机制
>5. Mybatis与Spring的整合，并且
>6. Junit
>7. Log4j
>8. SpringMVC整合页面所有操作，form，file，pic，input，radio，checkbox...

##遇到问题：
>1. Date类型丢失时分秒-->把date类型定义为String类型，并在插入db时由SimpleDateFormat转换为相应格式
>2. 事务不回滚-->默认只对RuntimeException或是其子类回滚，如果抛出Exception，不会回滚
>3. 静态资源的重写（http://localhost/foo.css 转为http://localhost/static/foo.css ）


访问：
http://localhost:8080/mybatisdemo1/userController/getAllUserWithPage  得到所有的user以分页的方式
http://localhost:8080/mybatisdemo1/fenYeDemo.jsp  直接访问分页页面
http://localhost:8080/mybatisdemo1/userController/globalI18n?lang=zh   国际化


测试：set contextPath = mybatisdemo1
一、分页页面：http://localhost:8080/<contextPath>/fenYeDemo.jsp

二、终极页面：

三、国际化：http://localhost:8080/<contextPath>/userController/globalI18n?lang=zh


四、 测试ExceptionHandler
主页面：http://localhost:8080/<contextPath>/exceptionDemo
1.仅仅作用于当前class
ExceptionHandlerDemoController.java-->HandlerExceptionForCurClass()
具体实现：http://localhost:8080/mybatisdemo1/exceptionDemo/test?param=0
在当前class中加@ExceptionHandler注解，那么当当前class出现异常的时候就会去找ExceptionHanlder的Method，这样就可以处理异常了
e.g.
/**
	 * 1. 在 @ExceptionHandler 方法的入参中可以加入 Exception 类型的参数, 该参数即对应发生的异常对象
	 * 2. @ExceptionHandler 方法的入参中不能传入 Map. 若希望把异常信息传导页面上, 需要使用 ModelAndView 作为返回值
	 * 3. @ExceptionHandler 方法标记的异常有优先级的问题（默认会去找最接近的方法.e.g. 10/0,发生异常时会找ArithmeticException，如果没有再找RuntimeException）.
	 * 4. @ControllerAdvice: 如果在当前 Handler 中找不到 @ExceptionHandler 方法来出来当前方法出现的异常,
	 * 则将去 @ControllerAdvice 标记的类中查找 @ExceptionHandler 标记的方法来处理异常.
*/
@ExceptionHandler({ArithmeticException.class})
public String HandlerExceptionForCurClass(Exception ex,HttpServletRequest request){
	logger.error("出异常了："+ex);
	request.setAttribute("exception",ex);
	return "WEB-INF/jsp/testExceptionHandlerDemo";
}
2.作用于整个项目用@ControllerAdvice
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





