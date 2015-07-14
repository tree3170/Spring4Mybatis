<%@ page import="javax.servlet.jsp.jstl.core.Config" %>
<%@ page import="java.util.Locale" %>
<%--
    Desc:  完整bootstrap模式页面、mybatis分页功能、restful的spring mvc功能，将尽可能含括表单的
        所有操作的很多功能，抽出去公共JS API，同时包含增删改查的所有功能。
    User:     Darlen liu
    Date:     15-6-27 下午12:43
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    //session.setAttribute("javax.servlet.jsp.jstl.fmt.localizationContext.session",Locale.US);
   // session.setAttribute("WW_TRANS_I18N_LOCALE", Locale.CHINA);
    //response.setLocale(Locale.CHINESE);
    String path = request.getContextPath();
    String bathPath = request.getScheme() + "://" + request.getServerName() + ":" + request
            .getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=bathPath%>">
    <title>最终样例</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <link rel="shortcut icon" href="pic/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" type="text/css" href="vender/bootstrap-3.3.5/css/bootstrap.css">
    <style>
        h1,h2,h3,h4,h5,h6{text-align:center;}
        body{margin: 0 auto;width: 90%;}
        thead th{background-color: #eee;}
        [class*="col-"]{border: 1px solid #ddd;}
        /*div{min-width: 300px}*/
        .table-hover > tbody > tr:hover {
            background-color: #ededed;
        }
        @media (max-width:767px) {
            .pagination-form{min-width: 200px;}
        }
        @media (min-width:768px)  {
            .pagination-form{padding:0 70px 0 12px;}
        }
        .table-responsive span{float:right;padding-right: 20px;}
        .btn-xs{padding: 3px 6px;}
    </style>

</head>
<body>
<%--<fmt:setLocale value="en_US"></fmt:setLocale>--%>
<h3>最终bootstrap之table布局</h3>
<hr style="border: 1px solid #aaa">
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<form role="form">
    <!--<div class="form-group">
        <label for="objName">对象</label>
        <input class="form-control" type="text" placeholder="请输入对象名" name="objName" id = "objName">
    </div>-->
    <span style="float: right">
	<a href="userController/globalI18n?lang=en">en</a>
	|
	<a href="userController/globalI18n?lang=zh">zh</a>
</span>
    <!--操作按钮（button）-->
    <div class="table-responsive " style="width:100%;border: 0px solid #ccc;float: left;margin-bottom: 10px;">
        <!--<caption>最终bootstrap之table布局</caption>-->
        <div>
            <span><input type="button" class="btn btn-primary" value="<fmt:message key="btn.searchBtn"/>" id="sehBtn"></span>
            <span><input type="button" class="btn btn-primary" value="<fmt:message key="btn.delBtn"/>" id="delBtn"></span>
        </div>
    </div><!-- /button  .table-responsive-->

    <!-- 数据表格（Table）-->
    <div class="table-responsive" style="min-width: 300px">
        <table class="table table-hover table-bordered ">
            <thead>
                <tr><th><input type="checkbox"></th>
                    <th><fmt:message key="user.userID"/></th>
                    <th><fmt:message key="user.userName"/></th>
                    <th><fmt:message key="user.pwd"/></th>
                    <th><fmt:message key="user.createdDate"/></th>
                    <th><fmt:message key="user.updatedDate"/></th>
                </tr>
            </thead><!-- /thead-->
            <tbody>
                <tr>
                    <td><input type="checkbox" name="objChkObjs"></td>
                    <td>Tanmay</td>
                    <td>Bangalore</td>
                    <td>560001</td>
                    <td>Bangalore</td>
                    <td>560001</td>
                </tr>
                <tr>
                    <td><input type="checkbox" name="objChkObjs"></td>
                    <td>Tanmay</td>
                    <td>Bangalore</td>
                    <td>560001</td>
                    <td>Bangalore</td>
                    <td>560001</td>
                </tr>
                <tr>
                    <td><input type="checkbox" name="objChkObjs"></td>
                    <td>Tanmay</td>
                    <td>Bangalore</td>
                    <td>560001</td>
                    <td>Bangalore</td>
                    <td>560001</td>
                </tr>
            </tbody><!-- /tbody-->
        </table> <!-- /table -->
    </div><!-- /Table .table-responsive-->

    <!--分页（pagination）-->
    <div class="" style="min-width: 768px">
        <div class="form pagination-form" style="margin: 20px 0;line-height: 34px;display: inline;float: right;">
            <span class="" style="color: #999"><fmt:message key="page.total" ><fmt:param value="100"/> </fmt:message></span>
            <input type="number" placeholder="页数" style="line-height: normal;width: 50px;">
            <span class="" style="color: #999" ><fmt:message key="page"/></span>
            <input type="button" value="<fmt:message key="btn.confirmBtn"/>" class="btn btn-xs btn-primary">
        </div><!-- /form pagination-->

        <ul class="pagination" style="float: right;min-width: 400px">
            <li><a href="javascript:;">&laquo;&nbsp;<fmt:message key="page.first"/></a></li>
            <li><a href="javascript:;">&lt;&nbsp;<fmt:message key="page.previous"/></a></li>
            <li><a href="#">1</a></li>
            <li><a href="#">...</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">...</a></li>
            <li><a href="javascript:;"><fmt:message key="page.next"/>&nbsp; &gt;</a></li>
            <li><a href="javascript:;"><fmt:message key="page.last"/>&nbsp;&raquo;</a></li>
        </ul><!-- /.pagination-->
    </div><!-- /div  pagination-->
</form>


<script type="text/javascript" src="../../vender/jquery/jquery-2.1.4.js"></script>
<script type="text/javascript" src="../../vender/bootstrap-3.3.5/js/bootstrap.js"></script>
<script type="text/javascript" >
    $(function(){
        $("#myModal").on('hidden.bs.modal',function(event){
            //删除缓存，不然下次点击不会再发请求，比如说不会再去重新拿userModal.html，
            // 导致userModal.html不能更新
            $( this ).removeData( "bs.modal" );
            $(".modal-content").empty();
        });
        $("#sehBtn").click(function(){
            $("#myModal").modal({ Toggle:'toggle',remote: "../../finalModal.html" });
        })
        $("#delBtn").click(function(){
            if(confirm("你确认要删除这（些）对象？")){

            }
        })

    })

</script>
</body>
</html>