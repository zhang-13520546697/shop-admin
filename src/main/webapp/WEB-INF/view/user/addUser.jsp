<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2019/6/20
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>用户添加列表</title>
    <jsp:include page="/common/link.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/common/nav.jsp"></jsp:include>
<div class="container">
    <form class="form-horizontal" >
        <div class="form-group ">
            <label class="control-label col-md-2">用户名：</label>
            <div class="col-md-3">
                <input type="text" id="add_userName" name="userName" onblur="checkUserName(this.value)" class="form-control" placeholder="请输入用户名..">
                <span id="userNameTip"></span>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-md-2">密码：</label>
            <div class="col-md-3">
                <input type="text" id="add_userPwd" name="userPwd" class="form-control" placeholder="请输入密码..">
            </div>
        </div>
        <div class="form-group ">
            <label class="control-label col-md-2">确认密码：</label>
            <div class="col-md-3">
                <input type="text" id="userPwdAgain" name="userPwdAgain" class="form-control" placeholder="请再次输入密码..">
            </div>
        </div>

        <div class="form-group ">
            <label class="control-label col-md-2">电话号：</label>
                <div class="col-md-3">
                <input type="text" id="add_phoneNumber" name="phoneNumber" class="form-control" placeholder="请输入电话号码..">
            </div>
        </div>

        <div class="form-group ">
            <label class="control-label col-md-2">邮箱地址：</label>
            <div class="col-md-3">
                <input type="text" id="add_email" name="email" class="form-control" placeholder="请输入邮箱地址..">
            </div>
        </div>

        <div class="form-group ">
            <label class="control-label col-md-2">用户头像：</label>
            <div class="col-md-3">
                <input type="file" id="headImage" name="headImagePath" class="form-control" placeholder="请输入用户头像..">
                <input type="hidden" id="add_headImagePath" name="headImagePath" class="form-control" placeholder="请输入用户头像..">
            </div>
        </div>

        <div class="form-group ">
            <label class="control-label col-md-2">性别：</label>
            <div class="col-md-3">
                <label><input type="radio"  name="sex" value="0">男</label>
                <label><input type="radio"  name="sex" value="1">女</label>
            </div>
        </div>

        <div class="form-group ">
            <label class="control-label col-md-2">出生年月：</label>
            <div class="col-md-3">
                <input type="text" id="add_birthday" name="birthday" class="form-control" placeholder="请输入您的出生年月..">
            </div>
        </div>

        <div class="form-group ">
            <label class="control-label col-md-2">真实学历：</label>
            <div class="col-md-3">
                <input type="text" id="add_education" name="education" class="form-control" placeholder="请输入真实学历..">
            </div>
        </div>

        <div class="form-group ">
            <label class="control-label col-md-2">民族：</label>
            <div class="col-md-3">
                <input type="text" id="add_nation" name="nation" class="form-control" placeholder="请输入您的民族..">
            </div>
        </div>

        <div class="form-group ">
            <label class="control-label col-md-2">身份证号：</label>
            <div class="col-md-3">
                <input type="text" id="add_idNumber" name="idNumber" class="form-control" placeholder="请输入您的身份证号..">
            </div>
        </div>

        <div class="form-group ">
            <label class="control-label col-md-2">省市县：</label>

        </div>

        <div class="form-group ">
            <label class="control-label col-md-2">详细住址：</label>
            <div class="col-md-3">
                <input type="text" id="add_address" name="address" class="form-control" placeholder="请输入您的详细住址..">
            </div>
        </div>

        <div align="center">
            <button type="submit" name="submit" class="btn btn-primary "id="add_button" onclick="addUser()"><span class="glyphicon glyphicon-plus"></span>添加</button>
            <button type="reset" class="btn btn-default " ><span class="glyphicon glyphicon-refresh"></span>重置</button>
        </div>

    </form>
</div>

    <jsp:include page="/common/script.jsp"></jsp:include>
</body>


<script type="text/javascript">

    $(function (){
        //初始化表单验证
        initVerify();
        //用户头像上传
        initHeadImage();
        //初始化日期插件
        initBirthdayDate();
        //初始化地区三级联动
        initArea();

    })

    //新增用户
    function addUser (){
       var v_userName=$("#add_userName").val();
       var v_userPwd=$("#add_userPwd").val();
       var v_realName=$("#add_realName").val();
       var md5Pwd = hex_md5(v_userPwd);
       var v_email = $("#add_email").val();
       var v_headImagePath = $("#add_headImagePath").val();
       var v_sex = $("[name=sex]:checked").val();
       var v_birthday = $("#add_birthday").val();
       var v_education = $("#add_education").val();
       var v_nation = $("#add_nation").val();
       var v_idNumber = $("#add_idNumber").val();
       var v_address = $("#add_address").val();

       var v_params = {};
        v_params.userName=v_userName;
        v_params.userPwd=md5Pwd;
        v_params.realName=v_realName;

        $.ajax({
            url:"/user/addUser.jhtml",
            type:"post",
            data:v_params,
            success:function (data) {
                if (data.code==200) {
                    bootbox.alert({
                        title: "提示信息",
                        message: "<span class='glyphicon glyphicon-exclamation-sign'></span>添加成功!",
                        size: "small",
                    })
                    location.href="../login.jsp"
                }else {
                    bootbox.alert({
                        title: "提示信息",
                        message: "<span class='glyphicon glyphicon-exclamation-sign'></span>添加失败!",
                        size: "small",
                    })
                }
            }
        })

    }
    //用户名唯一验证
    function checkUserName(userName){
        if(userName.length>0){
            $.ajax({
                url:"/user/checkUserName.jhtml",
                type:"get",
                data:{"userName":userName},
                success:function (data) {
                    if(data.code!=200){
                        //用户名已存在
                        $("#userNameTip").html(data.msg)
                        //禁用按钮
                        $("#add_button").attr("disabled","disabled");
                    }else{
                        //清除样式
                        $("#userNameTip").html("")
                        //禁用按钮
                        $("#add_button").removeAttr("disabled");
                    }
                }
            })
        }else{
            bootbox.alert({
                title: "提示信息",
                message: "<span class='glyphicon glyphicon-exclamation-sign'></span>用户名为空!",
                size: "small",
            })
        }
    }


    //表单验证
    function initVerify(){
        $('form').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                userName: {
                    message: '用户名验证失败',
                    validators: {
                        notEmpty: {
                            message: '用户名不能为空'
                        },
                        stringLength: {
                            min: 3,
                            max: 18,
                            message: '用户名长度必须在3到18位之间'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_]+$/,
                            message: '用户名只能包含大写、小写、数字和下划线'
                        }
                    }
                },
                userPwd:{
                    message:"密码无效",
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        },
                    identical: {//相同
                        field: 'userPwd', //需要进行比较的input name值
                        message: '两次密码不一致'
                    },
                    different: {//不能和用户名相同
                        field: 'userName',//需要进行比较的input name值
                        message: '不能和用户名相同'
                    }
                    }
                },
                userPwdAgain: {
                    message: '密码无效',
                    validators: {
                        notEmpty: {
                            message: '确认密码不能为空'
                        },
                        identical: {//相同
                            field: 'userPwd',
                            message: '两次密码不一致'
                        },
                        different: {//不能和用户名相同
                            field: 'userName',//需要进行比较的input name值
                            message: '不能和用户名相同'
                        }
                    }
                },
                phoneNumber: {
                    message: '无效电话',
                    validators: {
                        notEmpty: {
                            message: '手机号码不能为空'
                        },
                        stringLength: {
                            min: 11,
                            max: 11,
                            message: '请输入11位手机号码'
                        },
                        regexp: {
                            regexp: /^1[3|2|5|8]{1}[0-9]{9}$/,
                            message: '请输入正确的手机号码'
                        }
                    }
                },
                email: {
                    validators: {
                        notEmpty: {
                            message: '邮箱不能为空'
                        },
                        emailAddress: {
                            message: '邮箱地址格式有误'
                        }
                    }
                }
            }
        });
    }

    //用户头像上传
    function initHeadImage(){
        $("#headImage").fileinput({
            uploadUrl:"<%=request.getContextPath()%>/file/uploadUserHeadImage.jhtml", //上传方法地址
            language:"zh",//汉化语言
            dropZoneEnabled:false,//是否显示拖拽框  默认是true
            //showCaption:false,//是否显示文本框  默认是true
            maxFileCount:1,//同时上传1张
            //showPreview:true,
            //initialPreviewAsData: true, // 初始化回显的图片数据    特别重要
            //initialPreview:previewArr,//要回显的的图片路径 而且是个数组(不仅可以回显单个  还可以回显多个)
            //异步上传返回结果处理
            //event 当前事件  data 数据信息  previewId 预览id   index 第几张图片
        }).on("fileuploaded",function(event, data, previewId, index){
            if(data !=null && data.response.code==200){
                $("#add_headImagePath").val(data.response.data);
            }
        });
    }

    //初始化日期插件
    function initBirthdayDate(){
        $("#add_birthday").datetimepicker({
            format: 'YYYY-MM-DD',
            locale: moment.locale('zh-CN'),
            minDate: '1980/01/01', // 最小日期，如'2018/08/15'，则14号及14号前的日期都不可选
            maxDate: false,
            showClear: true,//展示删除键
        });
    }
    //省市县三级联动
    function initArea(){

    }
</script>
</html>
