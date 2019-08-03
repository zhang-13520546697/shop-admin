<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2019/6/13
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>品牌列表</title>

    <jsp:include page="/common/link.jsp"></jsp:include>

</head>
<body>
<jsp:include page="/common/nav.jsp"></jsp:include>
<div class="container">

    <!--条件查询表单-->
    <div class="row">
        <div class="panel panel-default">
            <div class="panel-heading">品牌查询</div>
            <div class="panel-body">
                <form class="form-horizontal " id="formDiv">

                    <div class="form-group">
                        <label  class="col-md-2 control-label">品牌名称:</label>
                        <div class="col-md-3">
                            <input type="text" class="form-control" id="brandName" name="brandName" placeholder="请输入品牌名称...">
                        </div>
                    </div>

                    <div align="center">
                        <button type="button" class="btn btn-primary " onclick="queryBrand()"><span class="glyphicon glyphicon-search"></span>搜索</button>
                        <button type="reset" class="btn btn-default " ><span class="glyphicon glyphicon-refresh"></span>重置</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="row">


        <div class="panel panel-default">
            <div class="panel-heading">品牌列表</div>
            <div class="panel-body">
                <table id="brand" class="display table table-striped table-bordered" style="width:100%"></table>
            </div>
        </div>
    </div>

</div>



<!-- 商品修改 -->
<div id="updateBrandDiv" style="display: none">
    <form class="form-horizontal" >
        <input type="hidden" id="update_brandId"/>
        <div class="form-group ">
            <label class="control-label col-md-2">品牌名称：</label>
            <div class="col-md-3">
                <input type="text" id="update_brandName" class="form-control" placeholder="请输入品牌名称..">
            </div>
        </div>
        <div class="form-group ">
            <label class="control-label col-md-2">创建时间：</label>
            <div class="col-md-3">
                <input type="text" id="update_createDate" class="form-control" placeholder="请输入品牌创建时间..">
            </div>
        </div>
        <div class="form-group ">
            <label class="control-label col-md-2">排序：</label>
            <div class="col-md-3">
                <input type="text" id="update_sort" class="form-control" placeholder="请输入品牌排列顺序..">
            </div>
        </div>
        <div class="form-group ">
            <label class="control-label col-md-2">品牌logo：</label>
            <div class="col-md-6">
                <input type="file" id="updatre_filename" name="logoUpload" class="form-control" placeholder="请输入品牌名称..">
                <input type="text" id="update_brandlogo"  class="form-control"/>
            </div>
        </div>

    </form>
</div>

</body>

<jsp:include page="/common/script.jsp"></jsp:include>

<script type="text/javascript">
    var v_brandAddSource;
    var v_brandUpdateSource;
    $(function (){
        //初始化查询方法
        query();
         //初始化修改品牌div
        v_brandUpdateSource = $("#updateBrandDiv").html();

        //品牌修改日期插件
        initUpdateDate();
    })
    //条件查询
    function queryBrand(){
        var v_brandName = $("#brandName").val();
        var v_params = {};
        v_params.brandName = v_brandName;
        datatable.settings()[0].ajax.data=v_params;
        datatable.ajax.reload();
    }
    //查询方法
    function query(){
        datatable= $('#brand').DataTable({
            "info":true,//是否显示在左下角
            "lengthChange":true,//是否允许用户改变每页条数
            "lengthMenu":[3,5,10],//每页展示条数
            "paging":true,//是否开启分页
            "processing":true,
            "searching":false,//是否开启本地搜索
            "serverSide": true,//是否开启服务器模式，ordering是否开启本地排序

            "ajax":{
                "url":"<%=request.getContextPath()%>/brand/queryBrand.jhtml",
                "type":"post",
                "dataSrc":function(result){
                    return result.data;
                }
            },
            "language": {
                "url": "<%=request.getContextPath()%>/commons/DataTables/Chinese.json"
            },
            "columns": [
                {"data": "id",title:"<input type='checkbox' name='id'/>选择","render":function(data,type,row,meta){
                        return "<input type='checkbox' name='GG' value='"+data+"'/>"
                    }
                },
                {"data": "logoUrl",title:"Logo",render:function (data,type,row,meta) {
                    if(data !=null && data !=""){
                        return "<img alt='图片加载失败' src='"+data+"' class='img-rounded' height='80px' width='100px'/>"
                    }
                    return "";
                }},
                {"data": "brandName",title:"品牌名称"},
                {
                    "data": "isRecommend",title:"是否推荐",
                    render:function (data,type,row,meta) {
                        if(data==1){
                            return '<button type="button" class="btn btn-primary" onclick="updateBrandIsRecommend('+data+','+row.id+')">推荐</button>';
                        }
                        if(data==0){
                            return '<button type="button" class="btn btn-default" onclick="updateBrandIsRecommend('+data+','+row.id+')">推荐</button>';
                        }
                        return ;
                    }
                },
                {"data": "createDate",title:"创建日期"},
                {"data": "sort",title:"排序"},
                {"data": "id",title:"操作",render:function (data,type,row,meta){
                        var id = row.id;
                        var buttons = " <div class=\"btn-group\" role=\"group\" aria-label=\"...\">\n" +
                            "               <button type='button' onclick='updateBrand("+id+")' class='btn btn-info'><span class='glyphicon glyphicon-pencil'>修改</span></button> \n" +
                            "             \t <button type='button' onclick='deleteBrand("+id+")' class='btn btn-danger'><span class='glyphicon glyphicon-trash'>删除</span></button>\n" +
                            "             </div>";
                        return buttons;
                    } },

            ]
        });
    }
    //品牌是否推荐
    function updateBrandIsRecommend(isRecommend,id){
        var v_isRecommend = isRecommend==1?0:1;
       $.ajax({
           url:"/brand/updateBrandIsRecommend.jhtml",
           type:"post",
           data:{"isRecommend":v_isRecommend,"id":id},
           success:function (data){
               if(data.code==200){
                   queryBrand();
               }
           }
       })
    }

    //回显+修改
    var v_updateBrand;
    function updateBrand(id){
        $.ajax({
            url:"/brand/getBrandById.jhtml",
            type:"post",
            data:{"id":id},
            success:function (data){
                //回填数据
                if(data.code==200){
                    var v_data = data.data;
                    $("#update_brandId").val(v_data.id);
                    $("#update_brandName").val(v_data.brandName);
                    $("#update_createDate").val(v_data.createDate);
                    $("#update_brandlogo").val(v_data.logoUrl);
                    $("#update_sort").val(v_data.sort);
                    var previewArr = [];
                    if(v_data.logoUrl != null && v_data.logoUrl != ""){
                        previewArr.push(v_data.logoUrl);
                    }
                    //修改图片上传
                    updateLog(previewArr);
                }
                 v_updateBrand = bootbox.dialog({
                    title: "品牌修改",
                    message: $("#updateBrandDiv form"),
                    size: "large",
                    buttons: {
                        confirm: {
                            label: '<span class="glyphicon glyphicon-ok"></span>提交',
                            className: 'btn-info',
                            callback: function () {
                                var update_brandId =$("#update_brandId",v_updateBrand).val();
                                var update_brandName = $("#update_brandName",v_updateBrand).val();
                                var update_brandLogo = $("#update_brandlogo",v_updateBrand).val();
                                var update_brandCreateDate = $("#update_createDate",v_updateBrand).val();
                                var update_sort = $("#update_sort",v_updateBrand).val();
                                var v_param = {};
                                v_param.id= update_brandId;
                                v_param.brandName=update_brandName;
                                v_param.logoUrl=update_brandLogo;
                                v_param.createDate=update_brandCreateDate;
                                v_param.sort = update_sort;
                                $.ajax({
                                    url: "<%=request.getContextPath()%>/brand/updateBrand.jhtml",
                                    type: "post",
                                    data: v_param,
                                    success: function (data) {
                                        if(data.code == 200) {
                                            queryBrand();
                                        }else{
                                            bootbox.alert({
                                                title: "提示信息",
                                                message: "<span class='glyphicon glyphicon-exclamation-sign'></span>修改失败!!!",
                                                size: "small",
                                            })
                                        }
                                    }
                                })
                            }
                        },
                        cancel: {
                            label: "<span class='glyphicon glyphicon-remove'></span>取消",
                            className: 'btn-danger',
                            callback: function () {

                            }
                        }
                    }
                })
                //还原数据
                $("#updateBrandDiv").html(v_brandUpdateSource);
                //品牌修改日期插件
                initUpdateDate();
            }
        })

    }

    //修改图片
    function  updateLog(previewArr) {

        $("#updatre_filename").fileinput({
            language:"zh",//汉化语言
            uploadUrl:"<%=request.getContextPath()%>/file/UploadBrandImage.jhtml",
            //dropZoneEnabled:false,//是否显示拖拽框  默认是true
            //showCaption:false,//是否显示文本框  默认是true
            maxFileCount:1,//同时上传1张
            showPreview:true,
            uploadAsync: true, //默认异步上传
            initialPreviewAsData: true, // 初始化回显的图片数据    特别重要
            initialPreview:previewArr,//要回显的的图片路径 而且是个数组(不仅可以回显单个  还可以回显多个)
            //异步上传返回结果处理
            //event 当前事件  data 数据信息  previewId 预览id   index 第几张图片
        }).on("fileuploaded",function(event, data, previewId, index){
            if(data !=null && data.response.code==200){
                $("#update_brandlogo",v_updateBrand).val(data.response.data);
            }
        })
    }

    //品牌修改日期插件
    function initUpdateDate(){
        $('#update_createDate').datetimepicker({
            format: 'YYYY-MM-DD',
            locale: moment.locale('zh-CN'),
            minDate: '1980/01/01', // 最小日期，如'2018/08/15'，则14号及14号前的日期都不可选
            maxDate: false,
            showClear: true,//展示删除键
        });
    }
    //删除
    function deleteBrand(id) {
        bootbox.dialog({
            message: "你确定要删除吗?",
            size: "small",
            title: "提示信息",
            buttons: {
                confirm: {
                    label: "确定",
                    className: 'btn-info',
                    callback: function (result) {
                        if (result) {
                            $.ajax({
                                url: "<%=request.getContextPath()%>/brand/deletebrand.jhtml",
                                type: "post",
                                data: {id: id},//往后台传值
                                success: function (del) {
                                    if (del.code == 200) {
                                        queryBrand();
                                    } else {
                                        bootbox.alert({
                                            title: "提示信息",
                                            message: "<span class='glyphicon glyphicon-exclamation-sign'></span>删除失败！",
                                            size: "small",
                                        })
                                    }

                                }
                            })
                        }
                    }
                },
                cancel: {
                    label: "取消",
                    className: 'btn-danger',
                }
            },
        })

    }

</script>
</html>
