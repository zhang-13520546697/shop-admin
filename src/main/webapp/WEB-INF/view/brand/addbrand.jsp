<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2019/7/10
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品添加</title>
    <jsp:include page="/common/link.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/common/nav.jsp"></jsp:include>
<!-- 商品添加 -->
    <form class="form-horizontal" >
        <div class="form-group ">
            <label class="control-label col-md-2">品牌名称：</label>
            <div class="col-md-3">
                <input type="text" id="add_brandName" class="form-control" placeholder="请输入品牌名称..">
            </div>
        </div>
        <div class="form-group ">
            <label class="control-label col-md-2">创建时间：</label>
            <div class="col-md-3">
                <input type="text" id="add_createDate" class="form-control" placeholder="请输入品牌创建时间..">
            </div>
        </div>
        <div class="form-group ">
            <label class="control-label col-md-2">排序：</label>
            <div class="col-md-3">
                <input type="text" id="add_sort" class="form-control" placeholder="请输入品牌排列顺序..">
            </div>
        </div>
        <div class="form-group ">
            <label class="control-label col-md-2">品牌logo：</label>
            <div class="col-md-6">
                <input type="file" id="add_filename" name="logoUpload" class="form-control" placeholder="请输入品牌名称..">
                <input type="hidden" id="add_branlogo"  name="logoImgUrl" class="form-control"/>
            </div>
        </div>

        <div align="center">
            <button type="button" class="btn btn-primary btn-lg"  onclick="showAddBrandDlg()"><span class="glyphicon glyphicon-plus"></span>品牌新增</button>
        </div>

    </form>

<jsp:include page="/common/script.jsp"></jsp:include>
</body>
<script type="text/javascript">
    $(function (){
        logoUrl();
        initAddDate();
    })
    //添加方法
    function showAddBrandDlg(){
        var add_brandName = $("#add_brandName").val();
        var add_brandLogo = $("#add_branlogo").val();
        var add_brandCreateDate = $("#add_createDate").val();
        var add_sort = $("#add_sort").val();
        var v_param = {};
        v_param.brandName=add_brandName;
        v_param.logoUrl=add_brandLogo;
        v_param.createDate=add_brandCreateDate;
        v_param.sort = add_sort;
        $.ajax({
            url: "<%=request.getContextPath()%>/brand/addBrand.jhtml",
            type: "post",
            data: v_param,
            success: function (data) {
                if(data.code == 200) {
                    location.href="/brand/toBrandPage.jhtml#2";
                }else{
                    bootbox.alert({
                        title: "提示信息",
                        message: "<span class='glyphicon glyphicon-exclamation-sign'></span>添加失败!!!",
                        size: "small",
                    })
                }
            }
        })
    }

    //增加图片
    function logoUrl(){
        $("#add_filename").fileinput({
            language:"zh",//汉化语言
            uploadUrl:"<%=request.getContextPath()%>/file/UploadBrandImage.jhtml",
            dropZoneEnabled:false,//是否显示拖拽框  默认是true
            //showCaption:false,//是否显示文本框  默认是true
            maxFileCount:1,//同时上传1张
            showPreview:true,

        }).on("fileuploaded",function(event,data,previewId,index){
            if(data != null && data.response.code==200){
                $("[name='logoImgUrl']").val(data.response.data);
            }
        })
    }

    //品牌添加日期插件
    function initAddDate(){
        $('#add_createDate').datetimepicker({
            format: 'YYYY-MM-DD',
            locale: moment.locale('zh-CN'),
            minDate: '1980/01/01', // 最小日期，如'2018/08/15'，则14号及14号前的日期都不可选
            maxDate: false,
            showClear: true,//展示删除键
        });
    }
</script>

</html>
