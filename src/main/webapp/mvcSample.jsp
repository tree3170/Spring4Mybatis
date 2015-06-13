<%--
    User:     Darlen liu
    Date:     15-6-11 下午9:40
    Function: ThiS is a Frond-End Sample JSP for Spring MVC
    这是一个SpringMVC前台的小例子,后期会不断完善，争取包括所有的可能性。
    简单表单（inputbox,dropdown,checkbox,radiobutton,file,textare...）
    Add,delete,update,search,分页
    pdf,excel,download
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String bathPath = request.getScheme() + "://" + request.getServerName() + ":" + request
            .getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=bathPath%>">
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <!--<link rel="shortcut icon" href="" type="image/x-icon" />-->
</head>
<body>
<h1>Spring MVC Frond End Demo</h1><br>
<div style="border: 1px solid">
    <h3>Search User</h3>
    <form id="searchUser" action="userController/searchUser.do" >
        <fieldset>
            <label for="bdId">User ID</label>
            <input id="bdId" type="text" name="userID"/>
        </fieldset>
        <input type="submit" value="Submit"/>
    </form>
</div>
<br><br>

<div style="border: 1px solid">
    <h3>Batch Delete BD</h3>

    <form name="batchDeleteBD" action="userController/bd/batchDelete">
        <fieldset>
            <table>
                <tbody>
                <tr>
                    <th><label for="objType">Object Type</label></th>
                    <td>
                        <input id="objType" type="text" name="objType"/>
                    </td>
                </tr>
                <tr>
                    <th><label for="bdIds">BD ID</label></th>
                    <td>
                        <input id="bdIds" type="text" name="objectIds"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </fieldset>
        <input type="submit" value="Submit"/>
    </form>
</div>
<br><br>

<div style="border: 1px solid">
    <h3>Batch Delete BD with Ajax</h3>

    <form name="batchDeleteBDWithAjax">
        <fieldset>
            <table>
                <tbody id="idTable">
                <tr id="0">
                    <th><label for="bdId_0">BD ID</label></th>
                    <td>
                        <input id="bdId_0" type="text" name="bdId_0"/>
                        <a href="javascript:void(0)" onclick="addNewRow(1)">+</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </fieldset>
    </form>
    <button onclick="test()">Submit</button>
</div>

<br><br>

<div style="border: 1px solid">
    <h3>Upload Image</h3>

    <form action="userController/bd/uploadImg"
          method="POST" enctype="multipart/form-data">
        <input type="file" name="img">
        <input type="submit" value="Submit">
    </form>
</div>
<script src="vender/jquery/jquery-1.7.2.min.js"></script>
</body>
</html>