<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
       String path = request.getContextPath();
       String basePath = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
               + path ;
    %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>Uikit Test</title>
   <meta http-equiv="pragma" content="no-cache">
   <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
   <link rel="stylesheet" type="text/css" href="<%=basePath%>/vender/uikit-2.17.0/css/uikit.gradient.min.css">
   <link rel="stylesheet" type="text/css" href="<%=basePath%>/vender/uikit-2.17.0/css/components/notify.gradient.min.css">
</head>
<body>
<div
    style="width:800px;margin-top:10px;margin-left: auto;margin-right: auto;text-align: center;">
    <h2>Uikit Test</h2>
</div>
<div style="width:800px;margin-left: auto;margin-right: auto;">
    <fieldset class="uk-form">
         <legend>Uikit表单渲染测试</legend>
        <div class="uk-form-row">
             <input type="text" class="uk-width-1-1">
       </div>
            <div class="uk-form-row">
                <input type="text" class="uk-width-1-1 uk-form-success">
         </div>
           <div class="uk-form-row">
               <input type="text" class="uk-width-1-1 uk-form-danger">
           </div>
           <div class="uk-form-row">
              <input type="text" class="uk-width-1-1">
            </div>
            <div class="uk-form-row">
                <select id="form-s-s">
                    <option>---请选择---</option>
                    <option>是</option>
                 <option>否</option>
             </select>
          </div>
         <div class="uk-form-row">
               <input type="date" id="form-h-id" />
         </div>
       </fieldset>
   <fieldset class="uk-form">
          <legend>基于Restful架构风格的资源请求测试</legend>
           <button class="uk-button uk-button-primary uk-button-large" id="btnGet">获取人员GET</button>
         <button class="uk-button uk-button-primary uk-button-large" id="btnAdd">添加人员POST</button>
          <button class="uk-button uk-button-primary uk-button-large" id="btnUpdate">更新人员PUT</button>
         <button class="uk-button uk-button-danger uk-button-large" id="btnDel">删除人员DELETE</button>
         <button class="uk-button uk-button-primary uk-button-large" id="btnList">查询列表PATCH</button>
   </fieldset>
</div>

<script type="text/javascript" src="<%=basePath%>/vender/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/vender/uikit-2.17.0/js/uikit.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/vender/uikit-2.17.0/js/components/notify.min.js"></script>
<script type="text/javascript">
    (function(window,$){

       var dekota={

            url:'',

            init:function(){
               dekota.url='<%=basePath%>';
               $.UIkit.notify("页面初始化完成", {status:'info',timeout:500});
                $("#btnGet").click(dekota.getPerson);
               $("#btnAdd").click(dekota.addPerson);
               $("#btnDel").click(dekota.delPerson);
               $("#btnUpdate").click(dekota.updatePerson);
               $("#btnList").click(dekota.listPerson);
            },
            getPerson:function(){
                $.ajax({
                   url: dekota.url + 'person/101/',
                    type: 'GET',
                    dataType: 'json'
                }).done(function(data, status, xhr) {
                   $.UIkit.notify("获取人员信息成功", {status:'success',timeout:1000});
               }).fail(function(xhr, status, error) {
                   $.UIkit.notify("请求失败！", {status:'danger',timeout:2000});
                });
           },
           addPerson:function(){
               $.ajax({
                   url: dekota.url + 'person',
                    type: 'POST',
                   dataType: 'json',
                   data: {id: 1,name:'张三',sex:'男',age:23}
                }).done(function(data, status, xhr) {
                    $.UIkit.notify(data.msg, {status:'success',timeout:1000});
                }).fail(function(xhr, status, error) {
                    $.UIkit.notify("请求失败！", {status:'danger',timeout:2000});
                });
            },
          delPerson:function(){
                $.ajax({
                   url: dekota.url + 'person/109',
                    type: 'DELETE',
                    dataType: 'json'
                }).done(function(data, status, xhr) {
                   $.UIkit.notify(data.msg, {status:'success',timeout:1000});
               }).fail(function(xhr, status, error) {
                   $.UIkit.notify("请求失败！", {status:'danger',timeout:2000});
               });
            },
           updatePerson:function(){
               $.ajax({
                 url: dekota.url + 'person',
                   type: 'POST',//注意在传参数时，加：_method:'PUT'　将对应后台的PUT请求方法
                   dataType: 'json',
                    data: {_method:'PUT',id: 221,name:'王五',sex:'男',age:23}
              }).done(function(data, status, xhr) {
                   $.UIkit.notify(data.msg, {status:'success',timeout:1000});
                }).fail(function(xhr, status, error) {
                    $.UIkit.notify("请求失败！", {status:'danger',timeout:2000});
                });
           },
           listPerson:function(){
                $.ajax({
                    url: dekota.url + 'person',
                    type: 'POST',//注意在传参数时，加：_method:'PATCH'　将对应后台的PATCH请求方法
                 dataType: 'json',
                   data: {_method:'PATCH',name: '张三'}
                }).done(function(data, status, xhr) {
                   $.UIkit.notify("查询人员信息成功", {status:'success',timeout:1000});
               }).fail(function(xhr, status, error) {
                  $.UIkit.notify("请求失败！", {status:'danger',timeout:2000});
               });
            }
        };
       window.dekota=(window.dekota)?window.dekota:dekota;
       $(function(){
            dekota.init();
       });
  })(window,jQuery);

</script>
</body>
</html>
