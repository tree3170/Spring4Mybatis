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
