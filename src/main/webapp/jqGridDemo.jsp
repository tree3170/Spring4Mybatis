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

<title>jdGrid Test</title>
   <meta http-equiv="pragma" content="no-cache">
   <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
   <link rel="stylesheet" type="text/css" href="<%=basePath%>/vender/jqgrid4.8.2/css/jquery-ui-custom.css"/>
   <link rel="stylesheet" type="text/css" href="<%=basePath%>/vender/jqgrid4.8.2/css/ui.jqgrid.css"/>
</head>
<body>
<table id="list4"></table>
<!-- jqGrid 分页 div gridPager -->
<div id="gridPager"></div>
<script type="text/javascript" src="<%=basePath%>/vender/jqgrid4.8.2/js/jquery-1.11.0.min.js" ></script>
<script type="text/javascript">

    $(function(){
        pageInit();
    });
    function pageInit(){
        jQuery("#list4").jqGrid(
                {
                    datatype : "local",
                    height : 250,
                    colNames : [ 'Inv No', 'Date', 'Client', 'Amount', 'Tax','Total', 'Notes' ],
                    colModel : [
                        {name : 'id',index : 'id',width : 60,sorttype : "int"},
                        {name : 'invdate',index : 'invdate',width : 90,sorttype : "date"},
                        {name : 'name',index : 'name',width : 100},
                        {name : 'amount',index : 'amount',width : 80,align : "right",sorttype : "float"},
                        {name : 'tax',index : 'tax',width : 80,align : "right",sorttype : "float"},
                        {name : 'total',index : 'total',width : 80,align : "right",sorttype : "float"},
                        {name : 'note',index : 'note',width : 150,sortable : false}
                    ],
                    multiselect : true,
                    caption : "Manipulating Array Data"
                });
        var mydata = [
            {id : "1",invdate : "2007-10-01",name : "test",note : "note",amount : "200.00",tax : "10.00",total : "210.00"},
            {id : "2",invdate : "2007-10-02",name : "test2",note : "note2",amount : "300.00",tax : "20.00",total : "320.00"},
            {id : "3",invdate : "2007-09-01",name : "test3",note : "note3",amount : "400.00",tax : "30.00",total : "430.00"},
            {id : "4",invdate : "2007-10-04",name : "test",note : "note",amount : "200.00",tax : "10.00",total : "210.00"},
            {id : "5",invdate : "2007-10-05",name : "test2",note : "note2",amount : "300.00",tax : "20.00",total : "320.00"},
            {id : "6",invdate : "2007-09-06",name : "test3",note : "note3",amount : "400.00",tax : "30.00",total : "430.00"},
            {id : "7",invdate : "2007-10-04",name : "test",note : "note",amount : "200.00",tax : "10.00",total : "210.00"},
            {id : "8",invdate : "2007-10-03",name : "test2",note : "note2",amount : "300.00",tax : "20.00",total : "320.00"},
            {id : "9",invdate : "2007-09-01",name : "test3",note : "note3",amount : "400.00",tax : "30.00",total : "430.00"}
        ];
        for ( var i = 0; i <= mydata.length; i++){
            jQuery("#list4").jqGrid('addRowData', i + 1, mydata[i]);
        }
    }
</script>

<script type="text/javascript" src="<%=basePath%>/vender/jqgrid4.8.2/js/jquery-ui-custom.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/vender/jqgrid4.8.2/js/i18n/grid.locale-cn.js"></script>
<script type="text/javascript" src="<%=basePath%>/vender/jqgrid4.8.2/js/jquery.jqGrid.min.js"></script>
</body>
</html>
