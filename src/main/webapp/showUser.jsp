<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${!empty errorMsg}">
    Error Message:${errorMsg}
</c:if>
<br>userName:${user.name}
<br><%=JSON.toJSONString(request.getAttribute("user"))%>
</body>
</html>