<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2019/6/20
  Time: 7:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>日志信息查询列表</title>

    <jsp:include page="/common/link.jsp"></jsp:include>

</head>
<body>
<jsp:include page="/common/nav.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="panel panel-default">
            <div class="panel-heading">日志记录查询</div>
            <div class="panel-body">
                <form class="form-horizontal " id="formDiv">
                    <div class="form-group">
                        <label  class="col-md-2 control-label">操作人:</label>
                        <div class="col-md-3">
                            <input type="text" class="form-control" id="userName" name="userName" placeholder="在此输入操作人名称....">
                        </div>

                        <label class="col-md-1 control-label">操作时间:</label>
                        <div class=" col-md-4 " >
                            <div class="input-group">
                                <input type="text" class="form-control"  id="startDate"   name="startDate"  placeholder="在此输入操作时间区间开始....">
                                <span class="input-group-addon" >
						<span class="glyphicon glyphicon-calendar"></span>
					</span>
                                <input type="text"  class="form-control "  id="endDate"   name="endDate" placeholder="在此输入操作时间区间结束....">
                            </div>
                        </div>
                    </div>

                    <div class="form-group " >
                        <label  class="col-md-2 control-label">方法执行状态:</label>
                        <div class="col-md-3" >
                            <label><input type="radio"  value="1" id="success" name="status">成功</label>
                            <label><input type="radio"  value="0" id="error" name="status" >失败</label>
                        </div>

                        <label  class="col-md-2 control-label">操作信息:</label>
                        <div class="col-md-3">
                            <input type="text" class="form-control" id="info" name="info" placeholder="在此输入操作信息....">
                        </div>
                    </div>


                    <div align="center">
                        <button type="button" class="btn btn-primary " onclick="showLog()"><span class="glyphicon glyphicon-search"></span>搜索</button>
                        <button type="reset" class="btn btn-default " ><span class="glyphicon glyphicon-refresh"></span>重置</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="panel panel-default">
            <div class="panel-heading">日志记录表</div>
            <div class="panel-body">
                <table id="log" class="display table table-striped table-bordered" style="width:100%"></table>
            </div>
        </div>
    </div>

</div>



<jsp:include page="/common/script.jsp"></jsp:include>

</body>
<script type="text/javascript">
    $(function (){
        //初始化查询方法
        queryLog();
        //初始化日期插件
        init();
    })

    function queryLog(){
        datatable= $('#log').DataTable({
            "info":true,//是否显示在左下角
            "lengthChange":true,//是否允许用户改变每页条数
            "lengthMenu":[5,10,20],//每页展示条数
            "paging":true,//是否开启分页
            "processing":true,
            "searching":false,//是否开启本地搜索
            "serverSide": true,//是否开启服务器模式，ordering是否开启本地排序
            "ajax":{
                "url":"<%=request.getContextPath()%>/log/queryLog.jhtml",
                "type":"post",
                "dataSrc":function(result){
                    return result.data;
                }
            },
            "language": {
                "url": "<%=request.getContextPath()%>/commons/DataTables/Chinese.json"
            },
            "columns": [
                {"data": "id",title:"id"},
                {"data": "userName",title:"操作人"},
                {"data": "content",title:"操作内容"},
                {"data": "status",title:"执行状态",render:function (data,type,row,meta){
                    if (data==1){
                        return "<font color='green'>成功</font>";
                    } if (data==0){
                            return "<font color='red'>失败</font>";
                        }
                        return "";
                    }
                },
                {"data": "detailDesc",title:"详细描述"},
                {"data": "info",title:"方法信息"},
                {"data": "createDate",title:"创建日期"},
            ]
        });
    }
    //条件查询
    function showLog(){
        var v_userName = $("#userName").val();
        var v_status = $("[name=status]:checked").val();
        var v_info = $("#info").val();
        var v_startDate = $("[name=startDate]").val();
        var v_endDate = $("[name=endDate]").val();
        var v_params={};
        v_params.userName=v_userName;
        v_params.status = v_status;
        v_params.info = v_info;
        v_params.startDate = v_startDate;
        v_params.endDate = v_endDate;
        datatable.settings()[0].ajax.data=v_params;
        datatable.ajax.reload();
    }
    //日期插件
    function init(){
        $('#startDate').datetimepicker({
            format: 'YYYY-MM-DD HH:00:00',
            locale: moment.locale('zh-CN'),
            minDate: '1980/01/01', // 最小日期，如'2018/08/15'，则14号及14号前的日期都不可选
            maxDate: false,
            showClear: true,//展示删除键
        });
        $('#endDate').datetimepicker({
            format: 'YYYY-MM-DD HH:00:00',
            locale: moment.locale('zh-CN'),
            minDate: '1980/01/01', // 最小日期，如'2018/08/15'，则14号及14号前的日期都不可选
            maxDate: false,
            showClear: true,//展示删除键
        });
    }
</script>

</html>
