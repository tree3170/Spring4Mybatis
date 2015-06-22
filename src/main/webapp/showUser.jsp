<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show User</title>
</head>
<body>

userName:${user.name}
<br><%=JSON.toJSONString(request.getAttribute("user"))%>
<%=JSON.toJSONStringWithDateFormat(request.getAttribute("user"),"yyyy-MM-dd HH:mm:ss")%>

<hr width=80% size=1 color=#00ffff style="FILTER: alpha(opacity=100,finishopacity=0,style=3)"

</body>
</html>