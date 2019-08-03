<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2019/7/5
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>商品垃圾回收站</title>
    <jsp:include page="/common/link.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/common/nav.jsp"></jsp:include>


<div class="container">
    <!--条件查询表单-->
    <div class="row">
        <div class="panel panel-default">
            <div class="panel-heading">商品查询</div>
            <div class="panel-body">
                <form class="form-horizontal " id="formDiv">
                    <input type="hidden" name="gc1" id="gc1"/>
                    <input type="hidden" name="gc2" id="gc2"/>
                    <input type="hidden" name="gc3" id="gc3"/>
                    <div class="form-group">
                        <label  class="col-md-2 control-label">商品名称:</label>
                        <div class="col-md-3">
                            <input type="text" class="form-control" id="productName" name="productName" placeholder="商品名称">
                        </div>

                        <label class="col-md-2 control-label">生产日期:</label>
                        <div class=" col-md-3 " >
                            <div class="input-group">
                                <input type="text" class="form-control"  id="datetimepicker"   name="startTime"  placeholder="date">
                                <span class="input-group-addon" >
						<span class="glyphicon glyphicon-calendar"></span>
					</span>
                                <input type="text"  class="form-control "  id="datetimepicker1"   name="endTime" placeholder="date">
                            </div>
                        </div>
                    </div>

                    <div class="form-group " >
                        <label  class="col-md-2 control-label">商品价格:</label>
                        <div class="col-md-3" >
                            <div class="input-group ">
                                <input type="text" class="form-control"  name="minPrice" placeholder="请输入最小价格" >
                                <span class="input-group-addon" >
							       ￥
					  			</span>
                                <input type="text" class="form-control "  name="maxPrice" placeholder="请输入最大价格">
                            </div>
                        </div>

                        <label class="control-label col-md-2">产品品牌：</label>
                        <div class="col-md-3">
                            <select class="form-control" id="show_brandSelect" name="brandId">
                                <option value="-1">===请选择===</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group" id="categorySelectDiv">
                        <label  class="col-md-2 control-label" >商品分类：</label>

                    </div>

                    <div class="form-group ">
                        <label class="control-label col-md-2">是否热销：</label>
                        <div class="col-md-3">
                            <label><input type="radio" value="1" name="isHot">热销</label>
                            <label><input type="radio" value="0" name="isHot">冷门</label>
                        </div>
                    </div>

                    <div align="center">
                        <button type="button" class="btn btn-primary " onclick="queryProduct()"><span class="glyphicon glyphicon-search"></span>搜索</button>
                        <button type="reset" class="btn btn-default " ><span class="glyphicon glyphicon-refresh"></span>重置</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="row">
        <!--操作按钮-->
        <div  style="background-color:#46b8da ">
            <button type="button" class="btn btn-danger btn-lg"  onclick="deleteAll()"><span class="glyphicon glyphicon-trash"></span>删除</button>
            <button type="button" class="btn btn-success btn-lg"  onclick="restoreAll()"><span class="glyphicon glyphicon-download-alt"></span>还原</button>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">商品垃圾列表</div>
            <div class="panel">
                <table id="rubbishTablie" class="display table table-striped table-bordered" style="width:100%"></table>
            </div>
        </div>
    </div>

</div>
<jsp:include page="/common/script.jsp"></jsp:include>
</body>
<script type="text/javascript">

    var productArr = [];
    var photoPathArr = [];
    $(function (){
        //品牌下拉框初始化
        showBrandSelect();
        //商品分类三级联动
        initCagetory(0);
        //查询方法
        initQuery();
        //点击行变色
        changeTrColor();
        //初始化日期插件
        initDate();
    })
    //查询方法
    function initQuery(){
        datatable= $('#rubbishTablie').DataTable({
            "info":true,//是否显示在左下角
            "lengthChange":true,//是否允许用户改变每页条数
            "lengthMenu":[5,10,20],//每页展示条数
            "paging":true,//是否开启分页
            "processing":true,
            "searching":false,//是否开启本地搜索
            "serverSide": true,//是否开启服务器模式，ordering是否开启本地排序
            "ajax":{
                "url":"/rubbish/queryProductRubbish.jhtml",
                "type":"post",
                "dataSrc":function(result){
                    if(result.code==200){
                        return result.data;
                    }else {
                        bootbox.alert({
                            title: "提示信息",
                            message: "<span class='glyphicon glyphicon-exclamation-sign'></span>商品信息查询失败！",
                            size: "small",
                        })
                    }
                }
            },
            "language": {
                "url": "<%=request.getContextPath()%>/commons/DataTables/Chinese.json"
            },
            //上下翻页时调用此函数
            "drawCallback": function(a) {
                //将当前表格中中出现的唯一标识与数组中唯一标识进行对比  一致则回填
                $("#rubbishTablie tbody tr").each(function(){
                    //这一行所有数据
                    var v_checkbox = $(this).find("input[type='checkbox']")[0];
                    if(v_checkbox !=null ){
                        //取id
                        var id = v_checkbox.value;
                        if(isExist(id)){
                            //颜色加亮
                            $(this).css({"background-color":"red"});
                            //没有被选中则默认选中
                            v_checkbox.checked=true;
                        }
                    }

                })
            },
            "columns": [
                {"data":"id",title:"<input type='checkbox' name='id'/>选择","render":function(data,type,row,meta){
                        return "<input type='checkbox' name='GG' value='"+data+"' photo='"+row.productPhoto+"'/>"
                    }
                },
                {
                    "data": "status",title:"商品状态",
                    render:function (data,type,row,meta){
                        if(data==1){
                            return "<font style='font-size: 18px' color='green'>正常</font>";
                        }if(data==2){
                            return "<font style='font-size: 18px' color='red'>下架</font>";
                        }if(data==3){
                            return "<font style='font-size: 18px' color='gray'>删除</font>";
                        }
                        return  "";
                    }
                },
                {"data": "productName",title:"商品名称"},
                {"data": "brandName",title:"商品品牌"},
                {"data": "productPrice",title:"商品价格"},
                {"data": "categoryName",title:"分类"},
                {
                    "data": "isHot",title:"是否热销",
                    render:function (data,type,row,meta){
                        if(data==1){
                            return "<font style='font-size: 18px' color='red'>热销</font>";
                        }if(data==0){
                            return "<font style='font-size: 18px' color='blue'>冷门</font>";
                        }
                        return "";
                    }
                },
                {"data": "createTime",title:"生产日期"},
                {
                    "data": "productPhoto",title:"商品图片",
                    render:function (data,type,row,meta){
                        return "<img alt='图片加载失败' src='"+data+"' class='img-rounded' height='80px' width='90px'/>";
                    }
                },
            ],

        });
    }
    //翻页判定是否选中
    function isExist(id){
        for (var i = 0; i < productArr.length; i++) {
            if(id==productArr[i]){
                return true;
            }
        }
        return false;
    }
    //点击行变色
    function changeTrColor(){
        //动态绑定事件
        $('#rubbishTablie tbody').on('click', 'tr',function() {
            //$(this)指当前行  v_checkbox指这一行代表的所有信息
            var v_checkbox = $(this).find("input[type='checkbox']")[0];
            if(v_checkbox.checked){
                //取消选中状态
                v_checkbox.checked=false;
                //取消行变色
                $(this).css({"background-color":""});
                // 从数组中删除指定id
                if(v_checkbox.value.length>0){
                    deleteId();
                }

            }else{
                //颜色加亮
                $(this).css({"background-color":"red"});
                //没有被选中则默认选中
                v_checkbox.checked=true;
                //将选中的id放到数组中
                productArr.push(v_checkbox.value);
                //将选中的图片放到工具类中
                photoPathArr.push(v_checkbox.photo);
                //console.log($(this).find("input[type='checkbox']")[0])
                //console.log(v_checkbox.photo);
            }
        });
    }
    //取消行变色
    function deleteId(id){
        for (var i = productArr.length-1; i >=0; i--) {
            if(id=productArr[i]){
                productArr.splice(i,1);//如果有相同id 一次删除一个
            }
        }
    }

    //条件查询
    function queryProduct(){
        var productName = $("[name=productName]").val();
        var minPrice = $("[name=minPrice]").val();
        var maxPrice = $("[name=maxPrice]").val();
        var startTime =  $("[name=startTime]").val();
        var endTime = $("[name=endTime]").val();
        var brandId = $("#show_brandSelect").val();
        var v_isHot = $("[name=isHot]:checked").val();
        var v_status= $("[name=status]:checked").val();
        var v_gc1 = $($("select[name='categorySelect']",$("#formDiv"))[0]).val();
        var v_gc2 = $($("select[name='categorySelect']",$("#formDiv"))[1]).val();
        var v_gc3 = $($("select[name='categorySelect']",$("#formDiv"))[2]).val();
        var params = {
            "productName":productName,
            "minPrice":minPrice,
            "maxPrice":maxPrice,
            "startTime":startTime,
            "endTime":endTime,
            "brandId":brandId,
            "isHot":v_isHot,
            "status":v_status,
            "gc1":v_gc1,
            "gc2":v_gc2,
            "gc3":v_gc3,
        }
        datatable.settings()[0].ajax.data=params;
        datatable.ajax.reload();
    }
    //品牌下拉框初始化
    function  showBrandSelect(){
        $.ajax({
            url:"/brand/findBrand.jhtml",
            type:"post",
            success:function (data){
                if(data.code==200){
                    var v_brandData = data.data;
                    var v_brandSel = "";
                    for (var i = 0; i < v_brandData.length; i++) {
                        v_brandSel += '<option value="'+v_brandData[i].id+'">'+v_brandData[i].brandName+'</option>';
                    }
                    $("#show_brandSelect").append(v_brandSel);
                }else {
                    bootbox.alert({
                        title: "提示信息",
                        message: "<span class='glyphicon glyphicon-exclamation-sign'></span>品牌信息加载失败！",
                        size: "small",
                    })
                }
            }
        })
    }

    //条件查询日期插件
    function initDate(){
        $('#datetimepicker').datetimepicker({
            format: 'YYYY-MM-DD',
            locale: moment.locale('zh-CN'),
            minDate: '1980/01/01', // 最小日期，如'2018/08/15'，则14号及14号前的日期都不可选
            maxDate: false,
            showClear: true,//展示删除键
        });
        $('#datetimepicker1').datetimepicker({
            format: 'YYYY-MM-DD',
            locale: moment.locale('zh-CN'),
            minDate: '1980/01/01', // 最小日期，如'2018/08/15'，则14号及14号前的日期都不可选
            maxDate: false,
            showClear: true,//展示删除键
        });
    }
    //商品分类三级联动
    function initCagetory(fid,obj){
        //找到父级div之后的同级元素进行删除
        if(obj){
            $(obj).parent().nextAll().remove();
            //给动态下拉框赋自定义属性   obj.options[obj.selectedIndex].text 被选中项的文本
            $(obj).attr("data-cagetoryname",obj.options[obj.selectedIndex].text);
        }
        $.ajax({
            url:"/category/findChildCategoryList.jhtml",
            type:"post",
            data:{"id":fid},
            success:function (data){
                if(data.code==200){
                    //清除最后一个空下拉框
                    if(data.data.length==0){
                        return;
                    }
                    var v_cagetory = data.data;
                    var v_cagetorySel ="";
                    v_cagetorySel += '<div class="col-md-2"><select class="form-control" onchange="initCagetory(this.value,this)" name="categorySelect">';
                    v_cagetorySel += '<option value="-1">===请选择===</option>';
                        for (var i = 0; i < v_cagetory.length; i++) {
                            v_cagetorySel += '<option value="'+v_cagetory[i].id+'">'+v_cagetory[i].categoryName+'</option>';
                        }
                    v_cagetorySel += '</select></div>';
                        $("#categorySelectDiv").append(v_cagetorySel);

                    }
                }
        })
    }
    //批量删除
    function deleteAll() {
        if (productArr.length != 0) {
            bootbox.dialog({
                message: "你确定要删除吗?",
                size: "small",
                title:"提示信息",
                buttons: {
                    confirm: {
                        label: "确定",
                        className: 'btn-info',
                        callback: function () {
                            $.ajax({
                                url: "<%=request.getContextPath()%>/rubbish/deleteProductAll.jhtml",
                                type: "post",
                                data: {
                                    "checkedIds": productArr
                                },
                                success: function (data) {
                                    if (data.code == 200) {
                                        //清空数组
                                        productArr = [];
                                        //刷新页面
                                        queryProduct();
                                    }
                                },
                                error: function (data) {
                                    bootbox.alert({
                                        size: "small",
                                        title: "提示信息",
                                        message: "<span class='glyphicon glyphicon-error' aria-hidden='true'></span>删除失败"
                                    })
                                }
                            });
                        }
                    },
                    cancel: {
                        label: "取消",
                        className: 'btn-danger',
                    }
                },
            })
        }else {
            bootbox.alert({
                size: "small",
                title: "提示信息",
                message: "<span class='glyphicon glyphicon-error' aria-hidden='true'></span>请选择一项！！！"
            })
        }
    }
    //还原数据
    function restoreAll(){
        if (productArr.length != 0) {
            bootbox.dialog({
                message: "你确定要还原吗?",
                size: "small",
                title:"提示信息",
                buttons: {
                    confirm: {
                        label: "确定",
                        className: 'btn-info',
                        callback: function () {
                            $.ajax({
                                url: "/rubbish/restoretAll.jhtml",
                                type: "post",
                                data: {
                                    "checkedIds": productArr
                                },
                                success: function (data) {
                                    if (data.code == 200) {
                                        //清空数组
                                        productArr = [];
                                        //刷新页面
                                        queryProduct();
                                    }
                                },
                                error: function (data) {
                                    bootbox.alert({
                                        size: "small",
                                        title: "提示信息",
                                        message: "<span class='glyphicon glyphicon-error' aria-hidden='true'></span>还原失败！！！"
                                    })
                                }
                            });
                        }
                    },
                    cancel: {
                        label: "取消",
                        className: 'btn-danger',
                    }
                }
            })
        }else {
            bootbox.alert({
                size: "small",
                title: "提示信息",
                message: "<span class='glyphicon glyphicon-error' aria-hidden='true'></span>请选择一项！！！"
            })
        }
    }

</script>
</html>