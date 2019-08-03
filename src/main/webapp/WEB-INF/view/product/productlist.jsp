<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品列表</title>

	<jsp:include page="/common/link.jsp"></jsp:include>

</head>
<body>
<jsp:include page="/common/nav.jsp"></jsp:include>
<div class="container">

	<div class="row">
		<!--条件查询表单-->
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

						<label class="control-label col-md-2">商品状态：</label>
						<div class="col-md-3">
							<label><input type="radio" value="1" name="status">上架</label>
							<label><input type="radio" value="2" name="status">下架</label>
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
			<button type="button" class="btn btn-primary btn-lg"  onclick="showAddProductDlg()"><span class="glyphicon glyphicon-plus"></span>商品新增</button>
			<button type="button" class="btn btn-danger btn-lg"  onclick="deleteAll()"><span class="glyphicon glyphicon-trash"></span>批量删除</button>
			<button type="button" class="btn btn-success btn-lg"  onclick="exportExcel()"><span class="glyphicon glyphicon-download-alt"></span>导出excel</button>
			<button type="button" class="btn btn-success btn-lg"  onclick="exportPDF()"><span class="glyphicon glyphicon-download-alt"></span>导出pdf</button>
			<button type="button" class="btn btn-success btn-lg"  onclick="exportWORD()"><span class="glyphicon glyphicon-download-alt"></span>导出word</button>
			<button type="button" class="btn btn-success btn-lg"  onclick="uploadExcel()"><span class="glyphicon glyphicon-upload"></span>导入excel</button>
			<button type="button" class="btn btn-warning btn-lg"  onclick="clearCache()"><span class="glyphicon glyphicon-repeat"></span>清除缓存</button>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">商品列表</div>
			<div class="panel">
				<table id="example" class="display table table-striped table-bordered" style="width:100%"></table>
			</div>
		</div>

 	</div>
</div>
<!-- 导入excel -->
<div id="importExcelDiv" style="display: none">
	<form class="form-horizontal" >

		<div class="form-group">
			<label  class="col-md-2 control-label">Excel导入：</label>
			<div class="col-md-6">
				<input  type ="file"  id ="fileexcel" name="importExcel"/>
				<input type ="text"  id="excelPath" class="form-control"/>
			</div>
		</div>

	</form>
</div>
<!-- 商品添加 -->
<div id="addProductDiv" style="display: none">
	<form class="form-horizontal" >
		<div class="form-group ">
			<label class="control-label col-md-2">产品名称：</label>
			<div class="col-md-3">
				<input type="text" id="add_productName" class="form-control" placeholder="请输入产品名称..">
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-md-2">产品品牌：</label>
			<div class="col-md-2">
				<select class="form-control" id="add_brandSelect">
					<option value="-1">===请选择===</option>
				</select>
			</div>
		</div>
		<div class="form-group ">
			<label class="control-label col-md-2">生产日期：</label>
			<div class="col-md-3">
				<input type="text" id="add_createTime" class="form-control" placeholder="请输入产品生产日期..">
			</div>
		</div>
		<div class="form-group ">
			<label class="control-label col-md-2">产品价格：</label>
			<div class="col-md-3">
				<input type="text" id="add_productPrice" class="form-control" placeholder="请输入产品价格..">
			</div>
		</div>
		<div class="form-group ">
			<label class="control-label col-md-2">是否热销：</label>
			<div class="col-md-3">
				<label><input type="radio" value="1" name="isHot">热销</label>
				<label><input type="radio" value="0" name="isHot">冷门</label>
			</div>
		</div>
		<div class="form-group">
			<label  class="col-md-2 control-label">产品图片：</label>
			<div class="col-md-6">
				<input  type ="file"  id ="add_filename" name="productPhoto"/>
				<input type ="text"  id="add_productPhoto" />
			</div>
		</div>

		<div class="form-group" id="categoryDiv">
			<label  class="col-md-2 control-label">商品分类：</label>

		</div>
	</form>
</div>

<!-- 商品修改 -->
<div id="updateProductDiv" style="display: none">
	<form class="form-horizontal" >
		<input type="hidden" id="productId" />
		<div class="form-group ">
			<label class="control-label col-md-2">产品名称：</label>
			<div class="col-md-3">
				<input type="text" id="update_productName" class="form-control" placeholder="请输入产品名称..">
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-md-2">产品品牌：</label>
			<div class="col-md-3">
				<select class="form-control" id="update_brandSelect">
					<option value="-1">===请选择===</option>
				</select>
			</div>
		</div>

		<div class="form-group ">
			<label class="control-label col-md-2">产品价格：</label>
			<div class="col-md-3">
				<input type="text" id="update_productPrice" class="form-control" placeholder="请输入产品价格..">
			</div>
		</div>

		<div class="form-group ">
			<label class="control-label col-md-2">生产日期：</label>
			<div class="col-md-3">
				<input type="text" id="update_createTime" class="form-control" placeholder="请输入产品生产日期..">
			</div>
		</div>

		<div class="form-group" id="update_categoryDiv">
			<label  class="col-md-2 control-label" id="categoryLable">商品分类：</label>

		</div>

		<div class="form-group ">
			<label class="control-label col-md-2">是否热销：</label>
			<div class="col-md-3">
				<label><input type="radio" value="1" name="update_isHot">热销</label>
				<label><input type="radio" value="0" name="update_isHot">冷门</label>
			</div>
		</div>

        <div class="form-group">
            <label  class="col-md-2 control-label">产品图片：</label>
            <div class="col-md-6">
                <input  type ="file"  id ="update_filename" name="productPhoto"/>
                <input type ="text"  id="update_OldProductPhoto" /><br/>
                <input type ="text"  id="update_productPhoto" />
            </div>
        </div>

	</form>
</div>

<jsp:include page="/common/script.jsp"></jsp:include>
</body>
<script type="text/javascript">
	var v_addProductSource;
	var v_updateProductSource;
	var v_importExcelSource;
    var v_importExcelDialog
    var productArr = [];

    //初始化代码
    $(function (){
        //获取添加商品div中的原始数据
        v_addProductSource = $("#addProductDiv").html();
        //获取修改商品div中的原始数据
        v_updateProductSource = $("#updateProductDiv").html();
        //获取导入文件div
        v_importExcelSource = $("#importExcelDiv").html();
        //初始化查询方法
        query();
        //初始化日期插件
        initDate();
        //初始化商品添加日期插件
		initAddDate();
		//初始化商品修改日期插件
		initUpdateDate();
		//添加品牌下拉框
        brandSelect("add_brandSelect");
        //修改品牌下拉框
        brandSelect("update_brandSelect");
        //条件查询品牌下拉框
        brandSelect("show_brandSelect");
        //新增图片上传
        productImgUrl();

        //点击行变色
        changeTrColor();
        //初始化上传excel
        showImportExcel();
        //初始化查询时候的分类三级联动
        initCategory("categorySelectDiv",0);
    })
	//商品修改日期插件
	function initUpdateDate(){
        $('#update_createTime').datetimepicker({
            format: 'YYYY-MM-DD',
            locale: moment.locale('zh-CN'),
            minDate: '1980/01/01', // 最小日期，如'2018/08/15'，则14号及14号前的日期都不可选
            maxDate: false,
            showClear: true,//展示删除键
        });
	}
	//商品添加日期插件
	function initAddDate(){
        $("#add_createTime").datetimepicker({
            format: 'YYYY-MM-DD',
            locale: moment.locale('zh-CN'),
            minDate: '1980/01/01', // 最小日期，如'2018/08/15'，则14号及14号前的日期都不可选
            maxDate: false,
            showClear: true,//展示删除键
        });

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

	//查询方法
	function query(){
		 datatable= $('#example').DataTable({

				"info":true,//是否显示在左下角
				"lengthChange":true,//是否允许用户改变每页条数
				"lengthMenu":[5,10,20],//每页展示条数
				"paging":true,//是否开启分页
				"processing":true,
				"searching":false,//是否开启本地搜索
				"serverSide": true,//是否开启服务器模式，ordering是否开启本地排序
	            "ajax":{
				 	"url":"<%=request.getContextPath()%>/product/queryProduct.jhtml",
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
                 $("#example tbody tr").each(function(){
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
	     					return "<input type='checkbox' name='GG' value='"+data+"'/>"
	 					}
	     					 },
	     				 {
	     				     "data": "status",title:"商品状态",
                             render:function (data,type,row,meta){
	     				         if(data==1){
                                     return "<font style='font-size: 18px' color='green'>正常</font>";
								 }if(data==2){
                                     return "<font style='font-size: 18px' color='red'>下架</font>";
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
	     				         return "<img alt='图片加载失败' src='"+data+"' class='img-rounded' height='80px' width='100px'/>";
                             }
                         },
	     				 {"data": "id",title:"操作",render:function (data,type,row,meta){
	     				     var status = "";
	     				     var className="";
	     				     var statusName="";
	     				    if(row.status==1){
	     				        className = "btn btn-warning";
	     				        statusName = "下架";
                                status = 2;
							}if(row.status==2){
	     				        className = "btn btn-success";
	     				        statusName = "上架";
	     				        status = 1;
	     				    }
							 var buttons = " <div class='btn-group' role='group' aria-label='...'>\n" +
                                 "               <button type='button' onclick='getProductById("+ data +")' class='btn btn-info'>修改</button> \n" +
                                 "            	 <button type='button' onclick='deleteProduct("+ data +")' class='btn btn-danger'>删除</button>\n" +
                                 "            	 <button type='button' onclick='productStatus("+data+","+status+")' class='"+className+"'>"+statusName+"</button>\n" +
                                 "               </div>"
							 return buttons;
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
        $('#example tbody').on('click', 'tr',function() {
            //$(this)指当前行  v_checkbox指这一行代表的所有信息
            var v_checkbox = $(this).find("input[type='checkbox']")[0];
                if(v_checkbox.checked){
                    //取消选中状态
                    v_checkbox.checked=false;
                    //取消颜色加亮
                    $(this).css({"background-color":""});
                    //从数组中删除指定id
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
                }
            });
    }
    //从数组中删除
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
	//新增
    var v_addDeptDialog;
	function showAddProductDlg(){
         v_addDeptDialog = bootbox.dialog({
           title: "添加商品",
           message: $("#addProductDiv form"),
           size: "large",
           buttons: {
               confirm: {
                   label: '<span class="glyphicon glyphicon-ok"></span>提交',
                   className: 'btn-info',
                   callback: function () {
                       var v_isHot = $("[name='isHot']:checked",v_addDeptDialog).val();
                       var v_productName = $("#add_productName", v_addDeptDialog).val();
                       var v_productPrice = $("#add_productPrice", v_addDeptDialog).val();
                       var v_createDate = $("#add_createTime", v_addDeptDialog).val();
                       var v_brandId = $("#add_brandSelect",v_addDeptDialog).val();
                       var v_productPhoto = $("#add_productPhoto",v_addDeptDialog).val();
                       var v_gc1 = $($("select[name='categorySelect']",v_addDeptDialog)[0]).val();
                       var v_gc2 = $($("select[name='categorySelect']",v_addDeptDialog)[1]).val();
                       var v_gc3 = $($("select[name='categorySelect']",v_addDeptDialog)[2]).val();
                       var v_gc1Name = $($("select[name='categorySelect']",v_addDeptDialog)[0]).attr("data-categoryname");
                       var v_gc2Name = $($("select[name='categorySelect']",v_addDeptDialog)[1]).attr("data-categoryname");
                       var v_gc3Name = $($("select[name='categorySelect']",v_addDeptDialog)[2]).attr("data-categoryname");
                       var v_cateName=v_gc1Name+"-->"+v_gc2Name+"-->"+v_gc3Name;
                       var v_param = {};
                       v_param.productName = v_productName;
                       v_param.productPrice = v_productPrice;
                       v_param.createTime = v_createDate;
                       v_param.productPhoto = v_productPhoto;
                       v_param.gc1 = v_gc1;
                       v_param.gc2 = v_gc2;
                       v_param.gc3 = v_gc3;
                       v_param.categoryName=v_cateName;
                       v_param.isHot = v_isHot;
                       v_param["brand.id"] = v_brandId;

                       $.ajax({
                           url: "/product/addProductNow.jhtml",
                           type: "post",
                           data: v_param,
                           success: function (data) {
                               if(data.code == 200) {
                                   //刷新页面
                                   queryProduct();
                               }else {
                                   bootbox.alert({
                                       title: "提示信息",
                                       message: "<span class='glyphicon glyphicon-exclamation-sign'></span>添加失败!!",
                                       size: "small"
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
        $("#addProductDiv").html(v_addProductSource);
        initAddDate();//日期插件
        brandSelect("add_brandSelect");//商品下拉框
        productImgUrl();//调用fileinput
        initCategory("categoryDiv",0);//商品分类
	}
	//删除
	function deleteProduct(id){
	    //阻止事件冒泡
	    event.stopPropagation();
        bootbox.dialog({
            message: "你确定要删除吗?",
            size: "small",
			title:"提示信息",
            buttons: {
                confirm: {
                    label: "确定",
                    className: 'btn-info',
                    callback: function (result) {
                        if(result){
                            $.ajax({
                                url:"<%=request.getContextPath()%>/product/deleteproduct.jhtml",
                                type:"post",
                                data:{id:id},//往后台传值
                                success:function (del){
                                    if(del.code==200){
                                        queryProduct();
                                    }else {
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

	//回显
    var v_updateDeptDialog;
    function getProductById(id,obj){
        //阻止事件冒泡
        event.stopPropagation();
        $.ajax({
            url:"<%=request.getContextPath()%>/product/getProductById.jhtml",
            type:"post",
            data:{id:id},//往后台传值
            success:function (result){
                if(result.code==200){
                    //回显数据
                    var v_result = result.data;
                    $("#productId").val(v_result.id);
                    $("#update_productName").val(v_result.productName);
                    $("#update_productPrice").val(v_result.productPrice);
                    $("#update_createTime").val(v_result.createTime);
                    $("#update_brandSelect").val(v_result.brandId);
                    $("#update_OldProductPhoto").val(v_result.productPhoto);
                    $("[name=update_isHot]").each(function (){
                        if(v_result.isHot==this.value){
                            this.checked = true;
                            return;
						}
					});
                    $("#update_categoryDiv").append("<label  class='col-md-3 control-label' id='categoryLabel'>"+v_result.categoryName+"</label>");
                    $("#update_categoryDiv").append("<button type=\"button\" class=\"btn btn-primary \" onclick=\"eidt_cate('"+v_result.categoryName+"')\"><span class=\"glyphicon glyphicon-pencil\"></span><span id=\"cateButtonText\">编辑</span></button>");
                    var previewArr = [];
                    if(v_result.productPhoto != null && v_result.productPhoto != ""){
                        previewArr.push(v_result.productPhoto);
                    }
                    //修改图片上传
                    updateproductImgUrl(previewArr);

                     v_updateDeptDialog = bootbox.dialog({
                        title: "修改商品",
                        message: $("#updateProductDiv form"),
                        size: "large",
                        buttons: {
                            confirm: {
                                label: '<span class="glyphicon glyphicon-ok"></span>提交',
                                className: 'btn-info',
                                callback: function () {
                                    //归零
                                    v_buttonFlag==0;
                                    //获取数据
									var v_id = $("#productId",v_updateDeptDialog).val();
									var v_productName = $("#update_productName",v_updateDeptDialog).val();
									var v_productPrice = $("#update_productPrice",v_updateDeptDialog).val();
									var v_createTime = $("#update_createTime",v_updateDeptDialog).val();
									var v_brandId = $("#update_brandSelect",v_updateDeptDialog).val();
                                    var v_OldProductPhoto = $("#update_OldProductPhoto",v_updateDeptDialog).val();
                                    var v_productPhoto = $("#update_productPhoto",v_updateDeptDialog).val();
                                    var v_isHot = $("[name=update_isHot]:checked",v_updateDeptDialog).val();
									var v_gc1;
									var v_gc2;
									var v_gc3;
									var v_categoryName;
									if(v_buttonFlag==0){
									    //用户没有编辑
                                        v_gc1 = v_result.gc1;
                                        v_gc2 = v_result.gc2;
                                        v_gc3 = v_result.gc4;
                                        v_categoryName = v_result.categoryName;
									}else{
									    //用户编辑了
                                         v_gc1 = $($("select[name='categorySelect']",v_updateDeptDialog)[0]).val();
                                         v_gc2 = $($("select[name='categorySelect']",v_updateDeptDialog)[1]).val();
                                         v_gc3 = $($("select[name='categorySelect']",v_updateDeptDialog)[2]).val();
                                        var v_gc1Name = $($("select[name='categorySelect']",v_updateDeptDialog)[0]).attr("data-categoryname");
                                        var v_gc2Name = $($("select[name='categorySelect']",v_updateDeptDialog)[1]).attr("data-categoryname");
                                        var v_gc3Name = $($("select[name='categorySelect']",v_updateDeptDialog)[2]).attr("data-categoryname");
                                        v_categoryName=v_gc1Name+"-->"+v_gc2Name+"-->"+v_gc3Name;
									}


									var params = {};
                                    params.id = v_id;
                                    params.productName = v_productName;
                                    params.productPrice = v_productPrice;
                                    params.createTime = v_createTime;
                                    params["brand.id"] = v_brandId;
                                    params.productPhoto = v_productPhoto;
                                    params.oldProductPhoto = v_OldProductPhoto;
                                    params.gc1 = v_gc1;
                                    params.gc2 = v_gc2;
                                    params.gc3 = v_gc3;
                                    params.categoryName = v_categoryName;
                                    params.isHot = v_isHot;
                                    $.ajax({
                                        url: "/product/updateProduct.jhtml",
                                        type: "post",
                                        data: params,
                                        success: function (data) {
                                            if(data.code == 200) {
                                                datatable.draw(false);
                                                //刷新页面
                                                //queryProduct();
                                            }else {
                                                bootbox.alert({
                                                    title: "提示信息",
                                                    message: "<span class='glyphicon glyphicon-exclamation-sign'></span>修改失败！",
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
                                    v_buttonFlag=0;
                                }
                            }
                        }
                    })
					//还原数据
                    $("#updateProductDiv").html(v_updateProductSource);
                    initUpdateDate();//日期插件
                    brandSelect("update_brandSelect");//商品下拉框

				}
            }
        })
	}

	//品牌下拉框
	function brandSelect(elementId){

		$.ajax({
			url:"<%=request.getContextPath()%>/brand/findBrand.jhtml",
			type:"post",
			success:function (result){
			    if(result.code==200){
			        var v_brandList = result.data;
			        var info = "";
                    for (var i = 0; i < v_brandList.length;i++) {
						info += "<option value='"+v_brandList[i].id+"'>"+v_brandList[i].brandName+"</option>";
                    }
                    $("#"+elementId).append(info);
				}else{
                    bootbox.alert({
                        title: "提示信息",
                        message: "<span class='glyphicon glyphicon-exclamation-sign'></span>品牌信息加载失败！",
                        size: "small",
                    })
				}
			}
		})
	}

	//新增商品图片上传
	function  productImgUrl(){
        $("#add_filename").fileinput({
            uploadUrl:"<%=request.getContextPath()%>/file/uploadProductImage.jhtml", //上传方法地址
            language:"zh",//汉化语言
            dropZoneEnabled:false,//是否显示拖拽框  默认是true
            //showCaption:false,//是否显示文本框  默认是true
            maxFileCount:1,//同时上传1张
            showPreview:true,
            initialPreviewAsData: true, // 初始化回显的图片数据    特别重要
            //initialPreview:previewArr,//要回显的的图片路径 而且是个数组(不仅可以回显单个  还可以回显多个)
            //异步上传返回结果处理
            //event 当前事件  data 数据信息  previewId 预览id   index 第几张图片
        }).on("fileuploaded",function(event, data, previewId, index){

            if(data !=null && data.response.code==200){
             $("#add_productPhoto",v_addDeptDialog).val(data.response.data);
            }
        });
	}

    //修改商品图片上传
    function  updateproductImgUrl(previewArr){

        $("#update_filename").fileinput({
            uploadUrl:"<%=request.getContextPath()%>/file/uploadProductImage.jhtml", //上传方法地址
            language:"zh",//汉化语言
            //dropZoneEnabled:true,//是否显示拖拽框  默认是true
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
                $("#update_productPhoto",v_updateDeptDialog).val(data.response.data);
            }
        });
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
                                url: "<%=request.getContextPath()%>/product/deleteProductAll.jhtml",
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
    //导出pdf文件
    function exportPDF(){
        var v_procuctExcel = document.getElementById("formDiv");
        v_procuctExcel.action="/product/exportPDF.jhtml";
        v_procuctExcel.method="post";
        setGCInfo();
        v_procuctExcel.submit();
	}
	//条件查询回显
	function setGCInfo(){
        var v_gc1 = $($("select[name='categorySelect']",$("#formDiv"))[0]).val();
        var v_gc2 = -1;
        var v_gc3 = -1;
        console.log($("select[name='categorySelect']",$("#formDiv"))[1]);
        if($("select[name='categorySelect']",$("#formDiv"))[1]){
            v_gc2 = $($("select[name='categorySelect']",$("#formDiv"))[1]).val();
        }
        if($("select[name='categorySelect']",$("#formDiv"))[2]){
            v_gc3 = $($("select[ name='categorySelect']",$("#formDiv"))[2]).val();
        }
        $("#gc1").val(v_gc1);
        $("#gc2").val(v_gc2);
        $("#gc3").val(v_gc3);
	}
	//导出Excel
	function exportExcel(){
        var v_procuctExcel = document.getElementById("formDiv");
        v_procuctExcel.action="/product/exportExcel.jhtml";
        v_procuctExcel.method="post";
        setGCInfo();
        v_procuctExcel.submit();
	}
	//导出word
	function exportWORD(){
        var v_procuctExcel = document.getElementById("formDiv");
        v_procuctExcel.action="/product/exportWord.jhtml";
        v_procuctExcel.method="post";
        setGCInfo();
        v_procuctExcel.submit();
	}

	//导入excel
	function uploadExcel(){
         v_importExcelDialog = bootbox.dialog({
            title: "导入Excel",
            message: $("#importExcelDiv form"),
            size: "large",
            buttons: {
                confirm: {
                    label: '<span class="glyphicon glyphicon-ok"></span>提交',
                    className: 'btn-info',
                    callback: function () {
                        var v_path = $("#excelPath",v_importExcelDialog).val();
                        $.ajax({
							url:"/product/importExcel.jhtml",
							type:"post",
							data:{"path":v_path},
							success:function (data){
							    if(data.code==200){
							        //刷新页面
                                    queryProduct();
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
		$("#importExcelDiv").html(v_importExcelSource);
        showImportExcel();
	}


	function showImportExcel(){
        $("#fileexcel").fileinput({
            uploadUrl:"<%=request.getContextPath()%>/file/uploadFile.jhtml", //上传方法地址
            language:"zh",//汉化语言
            showUpload:false,
            showRemove:false,
            allowedFileExtensions : ["xlsx","xls"],/*上传文件格式限制*/
        }).on("fileuploaded",function(event, data, previewId, index){
            if(data.response.code==200){

                $("#excelPath",v_importExcelDialog).val(data.response.data);
            }
        });
	}

	//商品分类三级联动回显附
	var  v_buttonFlag = 0;
    function eidt_cate(categoryName){
        if(v_buttonFlag==0){
            //清空分类名label
            $("#categoryLabel").remove();
            //改变按扭文字
            $("#cateButtonText").html("取消编辑");
            v_buttonFlag=1;
			//加载下拉框
            initCategory('update_categoryDiv',0);
        }else{
            //清除select
            $("#update_categoryDiv div",v_updateDeptDialog).remove();
            //改变按扭文字
            $("#cateButtonText").html("编辑");
            //回填数据
            //$("#update_categoryDiv",v_updateDeptDialog).append("<label  class='col-md-3 control-label' id='categoryLabel'>"+categoryName+"</label>");
            $("#categoryLable",v_updateDeptDialog).after("<label class='col-md-3 control-label' id='categoryLabel'>"+categoryName+"</label>");
			v_buttonFlag=0;
        }
	}
    //三级联动商品分类三级联动
	function initCategory(elementDiv,cateId,obj){
	    //找到父级div之后的同级元素进行删除
	    if(obj){
            $(obj).parent().nextAll().remove();
            //给动态下拉框赋自定义属性   obj.options[obj.selectedIndex].text 被选中项的文本
            $(obj).attr("data-categoryname",obj.options[obj.selectedIndex].text);
		}
        $.ajax({
			url:"/category/findChildCategoryList.jhtml",
			type:"post",
			data:{"id":cateId},
			success:function (data){
			    if(data.code==200){
			        //如果过没有查出数据 直接返回
                    if(data.data.length==0){
                        return ;
                    }
					var v_select = "<div class='col-md-2'><select name='categorySelect' class='form-control' onchange='initCategory(\""+elementDiv+"\",this.value,this)'><option value='-1'>==请选择==</option>"
					var  selectArr = data.data;
                    for (var i = 0; i < selectArr.length; i++) {
                        v_select += "<option value='"+selectArr[i].id+"' >"+selectArr[i].categoryName+"</option>";
                    }
					v_select += "</select></div>"

					if(elementDiv=="categorySelectDiv"){//条件查询下拉框
                        $("#"+elementDiv).append(v_select);
					}else if(elementDiv=="categoryDiv"){//添加页面下拉框
                        $("#"+elementDiv,v_addDeptDialog).append(v_select);
					}else if(elementDiv=="update_categoryDiv"){//修改页面下拉框
                        $("#"+elementDiv,v_updateDeptDialog).append(v_select);
					}
				}
			}
		})
	}
	//商品状态
	function productStatus(id,status){
        window.event.stopPropagation();
        var v_statusName="";
        if(status==1){
            v_statusName="上架";
		}if(status==2){
            v_statusName="下架";
		}
        bootbox.dialog({
            message: "你确定要"+v_statusName+"吗?",
            size: "small",
            title:"提示信息",
            buttons: {
                confirm: {
                    label: "确定",
                    className: 'btn-info',
                    callback: function () {
                        $.ajax({
                            url:"/product/updateProductStatus.jhtml",
                            type:"post",
                            data:{"id":id,"status":status},
                            success:function (data){
                                if(data.code==200){
                                    //刷新页面
                                    queryProduct();
                                }
                            }
                        })
                    }
                },
                cancel: {
                    label: "取消",
                    className: 'btn-danger',
                }
            }
        })
	}
	//清除所有缓存
	function clearCache(){
        var str = [];
        var brandList = "brandList";//品牌列表查询
		var isRecommendBrandList = "isRecommendBrandList";//查找推荐品牌
		var hotProductList = "hotProductList";//热销品牌
        str.push(brandList);
        str.push(isRecommendBrandList);
        str.push(hotProductList);
        bootbox.dialog({
            message: "你确定要清除缓存吗?",
            size: "small",
            title:"提示信息",
            buttons: {
                confirm: {
                    label: "确定",
                    className: 'btn-info',
                    callback: function () {
                        $.ajax({
                            url:"/product/clearCache.jhtml",
                            type:"post",
                            data:{"str":str},
                            success:function (data){
                                if(data.code==200){
                                    //刷新页面
                                    queryProduct();
                                }
                            }
                        })
                    }
                },
                cancel: {
                    label: "取消",
                    className: 'btn-danger',
                }
            }
        })
	}

</script>
</html>