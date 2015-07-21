<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    //session.setAttribute("javax.servlet.jsp.jstl.fmt.localizationContext.session",Locale.US);
    // session.setAttribute("WW_TRANS_I18N_LOCALE", Locale.CHINA);
    //response.setLocale(Locale.CHINESE);
    String path = request.getContextPath();
    String bathPath = request.getScheme() + "://" + request.getServerName() + ":" + request
            .getServerPort() + path + "/";

%>
<!doctype html>
<html>

<head>
<base href="<%=bathPath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>css3立体效果404页面 - 建站特效http://51xuediannao.com</title>
<meta name="keywords" content="404页面,css3,css3立体" />
<meta name="description" content="css3立体效果404页面" />
<meta name="Copyright" content="Copyright @ 懒人建站 http://51xuediannao.com" />



</head>

<body>

    <div style="color:red">

        <c:if test="${exception!= null}">
            ${exception}
        </c:if>

    </div>
  <a href="exceptionDemo/test?param=1">测试exceptionHandler : 10/1</a><br>
  <a href="exceptionDemo/test?param=0">测试exceptionHandler : 10/0</a>

</body>
</html>