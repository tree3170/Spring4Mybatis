<%--
    User:     Darlen liu
    Date:     15-6-22 下午2:40
    Function: 这是一个关于mybatis的分页demo
--%>
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
    $(function(){

    })
        function getme(id){
            //$("#myModal").modal('toggle').on("show.bs.modal", function(e) {
               // var link = $(e.relatedTarget);
                //$(this).find(".modal-body").load(link.attr("href"));
               // alert(1)
           // });
            $.ajax({
                url:"userController/"+id+"/showUser2.do",
                method:"GET",
                //async:"true",
                //dataType:"json",
                success: function(result){
                    //alert(result)
                    var json1 =eval(result);

                    //填充表格
                    alert(result);
                }

            })
           // $('#myModal').modal('toggle').on('show',function(){

            //})

        }
</script>

</head>
<body>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    查询用户
                </h4>
            </div>
            <div class="modal-body">
                <form role="form">
                    <div class="form-group">
                        <label>用户名</label>
                        <input type="text" class="form-control" placeholder="请输入用户名" >
                    </div>
                    <div class="form-group">
                        <label>密码</label>
                        <input type="password" class="form-control" placeholder="请输入密码" >
                    </div>
                    <div class="form-group">
                        <input type="submit" >
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary">
                    提交更改
                </button>
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
                    <td><input type="text" name="pageNum" value="${pageNum}"/></td>
                    <th>页面大小：</th>
                    <td><input type="text" name="pageSize" value="${pageSize}"/></td>
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
            <table class="gridtable" style="width:100%;">
                <thead>
                <tr>
                    <th colspan="8">查询结果</th>
                </tr>
                <tr>
                    <th>ID</th>
                    <th>用户名</th>
                    <th>用户密码</th>
                    <th>创建时间</th>
                    <th>更新时间</th>
                    <th colspan="3">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${page.list}" var="country">
                    <tr>
                        <td>${country.id}</td>
                        <td>${country.name}</td>
                        <td>${country.pwd}</td>
                        <td>${country.createTime}</td>
                        <td>${country.updateTime}</td>
                        <td ><a  class="btn btn-primary" onclick="getme(${country.id})">查询</a></td>
                        <td ><a class="btn btn-primary">更新</a></td>
                        <td ><a class="btn btn-primary" href="${pageContext.request.contextPath}/userController/${country.id}/delUser.do?pageNum=${page.pageNum}&pageSize=${page.pageSize}">删除</a></td>
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
