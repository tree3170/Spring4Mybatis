<%--
    User:     Darlen liu
    Date:     15-6-22 下午2:40
    Function: 这是一个关于mybatis的分页demo
--%>
<%@ page import="com.mybatis.service.UserServiceI" %>
<%@ page import="com.mybatis.service.UserServiceImpl" %>
<%@ page import="com.mybatis.model.User" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String bathPath = request.getScheme() + "://" + request.getServerName() + ":" + request
            .getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=bathPath%>">
    <title>Mybatis分页插件 - 测试页面</title>
    <style type="text/css">
        * {
            margin: 0;
        }

        html, body {
            height: 100%;
        }

        .wrapper {
            min-height: 100%;
            height: auto !important;
            height: 100%;
            margin: 0 auto -155px;
        }

        .footer, .push {
            height: 155px;
        }

        table.gridtable {
            font-family: verdana, arial, sans-serif;
            font-size: 11px;
            color: #333333;
            border-width: 1px;
            border-color: #666666;
            border-collapse: collapse;
            margin: 5px auto;
        }

        table.gridtable th {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #666666;
            background-color: #dedede;
        }

        table.gridtable td {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #666666;
            background-color: #ffffff;
        }

        .middle {
            text-align: center;
            margin: 0 auto;
            width: 80%;
            height: auto;
        }

        .info {
            font-size: 12px;
            text-align: center;
            line-height: 20px;
            padding: 40px;
        }

        .info a {
            margin: 0 10px;
            text-decoration: none;
            color: green;
        }
    </style>
    <link rel="stylesheet" type="text/css" href="vender/bootstrap-3.3.5/css/bootstrap.css">
    <script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.js"></script>

    <script type="text/javascript">
        var pageNum = "";
        var pageSize = "";
        $(function(){
            pageNum = $("#tableFrm input[name=pageNum]").val();
            pageSize = $("#tableFrm input[name=pageSize]").val();
            /*
            *注意：当调用modal事件在jQuery事件里面时，如果多次调用jQuery事件，modal事件也会多次调用
            * 1.on事件，必需off()事件remove监听，不然触发多次,比如当test点击第二次时，alert也会弹出2次
            * $("#test").click(function(){
            *     $("#myModal").modal('toggle').off('hidden.bs.modal').on('hidden.bs.modal',function(event){
            *         alert(1);
            *     })
            * })
            *
            * 2.one事件，可以完美解决多次调用问题
            * */
            //for search event
            $(".search").click(function(event){
                var userID = $(this).parent().parent().find("td:eq(1)").text();
                $("#myModal").modal({ Toggle:'toggle',remote: "userModal.html" })
                        .one('shown.bs.modal',function(){
                    $.ajax({
                        url:"userController/"+userID+"/showUser2.do",
                        method:"GET",
                        dataType:"json",
                        success: function(result){
                            var retVal =result;
                            $("#idForSearch").val(retVal.id);
                            $("#nameForSearch").val(retVal.name).attr("disabled","disabled");
                            $("#pwdForSearch").val(retVal.pwd).attr("disabled","disabled");

                        }
                    })
                }).off('hidden.bs.modal').on('hidden.bs.modal',function(event){
                    //删除缓存，不然下次点击不会再发请求，比如说不会再去重新拿userModal.html，导致userModal.html不能更新
                    $( this ).removeData( "bs.modal" );
                    $(".modal-content").empty();
                });
            });
            //for update event
            $("#updateModal").click(function(event){
                var user ={
                            'id' : $("#idForSearch").val(),
                            'name' : $("#nameForSearch").val(),
                            'pwd' : $("#pwdForSearch").val()
                 };
                $.ajax({
                    url:"userController/updateUser.do",
                    data:user,
                    method:"POST",
                    dataType:"JSON",
                    success: function(result){
                        if("1" ==  result) {
                            alert("更新成功");
                            $("#myModal").modal('toggle');
                            window.location.reload()
                        }else{
                            alert("更新失败:"+result);
                            $("#myModal").modal('toggle');
                        }
                    }
                })
            });

            $("#closeModal").click(function(){
                $("#myModal").on("hidden.bs.model",function(e){$(this).removeData();});
            })

            //for check all
            $("#chkAll").click(function(){
                var isChecked = $(this).is(":checked");
                if(isChecked){
                    $("input[name=userCB]").prop("checked","true");
                    //$("input[name=userCB]").each(function(){
                      //  $(this).attr("checked","true");
                    //})
                }else{
                    $("input[name=userCB]").removeProp("checked");
                }
            })
            //for delete one record
            $(".delOne").click(function(){
                if(confirm("你确认要删除这条数据吗？")){
                    var userID = $(this).parent().parent().find("[name=objID]").val();
                    window.location= "userController/"+userID+"/delUser.do?pageNum="+pageNum+"&pageSize="+pageSize;
                }
            })

            //for delete multiple users
            $("#delMultp").click(function(){
                if(confirm("你确认要删除这些数据吗？")){
                    var data = {
                      "userIDs":getSeltChkBoxValue(),
                       "pageNum":pageNum,
                        "pageSize":pageSize
                    };
                    $.post("userController/batchDelUsers.do",data,function(result){
                        if("1" ==  result) {
                            alert("删除成功");
                            $("#myModal").modal('toggle');
                            window.location.reload()
                        }else{
                            alert("删除失败:"+result);
                            $("#myModal").modal('toggle');
                        }
                    },"json");
                }
             })
        })

        function getSeltChkBoxValue(){
            var seltValues = "";
            $("input[name=userCB]:checked").each(function(index,curObj){
                seltValues +=  $(this).next("[name=objID]").val() + ",";
                //seltValues += $(this).parent().find("input[name=objID]").val();
            })
            console.info("" != seltValues ? seltValues.substr(0,seltValues.length-1):"");
            return "" != seltValues ? seltValues.substr(0,seltValues.length-1):"";
        }




</script>

</head>
<body>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" >
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title col-sm-10 " id="myModalLabel" style="top: -10;">
                    查询用户
                </h4>

            </div>
            <div class="modal-body">

                <form role="form">
                    <input id="idForSearch"  name="id" type="hidden">
                    <div class="form-group">
                        <label>用户名</label>
                        <input type="text" class="form-control" id = "nameForSearch" name="userName"placeholder="请输入用户名" >
                    </div>
                    <div class="form-group">
                        <label>密码</label>
                        <input type="password" class="form-control" id = "pwdForSearch" name="pwd" placeholder="请输入密码" >
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <!--data-dismiss="modal"-->
                <button type="button" class="btn btn-default" data-dismiss="modal" id= "closeModal">关闭</button>
                <input type="button" class="btn col-sm-2 pull-right btn-primary" value="编辑" id="editModal" >
                <button type="button" class="btn btn-primary" id="updateModal" style="display:none">提交更改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div class="wrapper">
    <div class="middle">
        <c:if test="${page==null}">
            <h1 style="padding: 150px 0 20px;">查询用户列表</h1>
        </c:if>
        <c:if test="${page!=null}">
            <h1 style="padding: 50px 0 20px;">查询用户列表</h1>
        </c:if>

        <c:if test="${errorMsg!=null}">
            <h3 style="color:red;">${errorMsg}</h3>
        </c:if>
        <form action="userController/getAllUserWithPage.do" method="post">
            <table class="gridtable" style="width:100%;">
                <tr>
                    <th>页码：</th>
                    <td><input type="text" name="pageNum" value="${page.pageNum}"/></td>
                    <th>页面大小：</th>
                    <td><input type="text" name="pageSize" value="${page.pageSize}"/></td>
                    <td><input type="submit" value="查询"/></td>
                </tr>
            </table>
        </form>
        <c:if test="${page!=null}">
            <table class="gridtable" style="width:100%;">
                <tr>
                    <th colspan="4">分页信息</th>
                </tr>
                <tr>
                    <th style="width: 300px;">当前页号</th>
                    <td>${page.pageNum}</td>
                    <th style="width: 300px;">页面大小</th>
                    <td>${page.pageSize}</td>
                </tr>
                <tr>
                    <th>起始行号(>)</th>
                    <td>${page.startRow}</td>
                    <th>终止行号(<=)</th>
                    <td>${page.endRow}</td>
                </tr>
                <tr>
                    <th>总结果数</th>
                    <td>${page.total}</td>
                    <th>总页数</th>
                    <td>${page.pages}</td>
                </tr>
                <tr>
                    <th>第一页</th>
                    <td>${page.firstPage}</td>
                    <th>前一页</th>
                    <td>${page.prePage}</td>
                </tr>
                <tr>
                    <th>下一页</th>
                    <td>${page.nextPage}</td>
                    <th>最后一页</th>
                    <td>${page.lastPage}</td>
                </tr>
                <tr>
                    <th>是否为第一页</th>
                    <td>${page.isFirstPage}</td>
                    <th>是否为最后一页</th>
                    <td>${page.isLastPage}</td>
                </tr>
                <tr>
                    <th>是否有前一页</th>
                    <td>${page.hasPreviousPage}</td>
                    <th>是否有下一页</th>
                    <td>${page.hasNextPage}</td>
                </tr>
            </table>
            <form name = "tableFrm" id="tableFrm">
                <input type="hidden" name="pageNum" value="${page.pageNum}"/>
                <input type="hidden" name="pageSize" value="${page.pageSize}"/>
                <table class="gridtable" style="width:100%;">
                    <thead>
                    <tr>
                        <th colspan="8">查询结果</th>
                    </tr>
                    <tr>
                        <th colspan="8"><input type="button" value="批量删除" name="delMultp" id="delMultp" class="btn btn-primary"></th>
                    </tr>
                    <tr>
                        <th><input type="checkbox" name="userCBs" id="chkAll"> </th>
                        <th>ID</th>
                        <th>用户名</th>
                        <th>用户密码</th>
                        <th>创建时间</th>
                        <th>更新时间</th>
                        <th colspan="3">操作</th>
                    </tr>
                    </thead>
                    <tbody id="dataTable">
                    <c:forEach items="${page.list}" var="user" varStatus="v">
                        <tr>
                            <td><input type="checkbox" name="userCB" id="checkBox${v.index}"><input type="hidden" name="objID" value="${user.id}"></td>
                            <td>${user.id}</td>
                            <td>${user.name}</td>
                            <td>${user.pwd}</td>
                            <td>${user.createTime}</td>
                            <td>${user.updateTime}</td>
                            <td ><a  class="btn btn-primary search" >查询</a></td>
                            <td ><a class="btn btn-primary delOne"  >删除</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <table class="gridtable" style="width:100%;text-align: center;">
                    <tr>
                        <c:if test="${nav != page.pageNum}">
                            <td><a href="${pageContext.request.contextPath}/userController/getAllUserWithPage.do?pageNum=1&pageSize=${page.pageSize}">首页</a></td>
                        </c:if>
                        <c:if test="${page.hasPreviousPage}">
                            <td><a href="${pageContext.request.contextPath}/userController/getAllUserWithPage.do?pageNum=${page.prePage}&pageSize=${page.pageSize}">前一页</a></td>
                        </c:if>
                        <c:forEach items="${page.navigatepageNums}" var="nav">
                            <c:if test="${nav == page.pageNum}">
                                <td style="font-weight: bold;">${nav}</td>
                            </c:if>
                            <c:if test="${nav != page.pageNum}">
                                <td><a href="${pageContext.request.contextPath}/userController/getAllUserWithPage.do?pageNum=${nav}&pageSize=${page.pageSize}">${nav}</a></td>
                            </c:if>
                        </c:forEach>
                        <c:if test="${page.hasNextPage}">
                            <td><a href="${pageContext.request.contextPath}/userController/getAllUserWithPage.do?pageNum=${page.nextPage}&pageSize=${page.pageSize}">下一页</a></td>
                        </c:if>
                        <c:if test="${nav != page.pageNum}">
                            <td><a href="${pageContext.request.contextPath}/userController/getAllUserWithPage.do?pageNum=${page.pages}&pageSize=${page.pageSize}">最后一页</a></td>
                        </c:if>
                    </tr>
                </table>
            </form>
        </c:if>
    </div>
    <div class="push"></div>
</div>
<hr width="80%" size="1" color="#00ffff" style="margin: 0 auto;">
<div class="footer">
    <div class="info">
        <a href="http://git.oschina.net/free/Mybatis_PageHelper">Mybatis分页插件</a>
        <a href="http://git.oschina.net/free/Mybatis-Sample">Mybatis-Sample</a>
        <br/>
        作者：<a href="http://blog.csdn.net/isea533" style="margin: 0;">@Isea533/abel533</a>
    </div>
</div>





<script type="text/javascript" src="vender/bootstrap-3.3.5/js/bootstrap.js"></script>
</body>
</html>
