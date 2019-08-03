<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户登录页面</title>
</head>
<link href="<%=request.getContextPath()%>/commons/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<body>
<div class="container">

    <div class="panel panel-default">
        <div class="panel-heading">
            <div class="page-header">
                <h1>用户登陆页面<small>User login page</small></h1>
            </div>
        </div>
        <div class="panel-body">
            <form class="form-horizontal">
                <div class="form-group">
                    <label  class="col-sm-2 control-label">用户名：</label>
                    <div class="col-md-6">
                        <input type="email" class="form-control" id="userName" placeholder="UserName">
                    </div>
                </div>
                <div class="form-group">
                    <label  class="col-sm-2 control-label">密码：</label>
                    <div class="col-md-6">
                        <input type="password" class="form-control" id="passWord" placeholder="Password">
                    </div>
                </div>
                <div class="form-group">
                    <label  class="col-sm-2 control-label">验证码：</label>
                    <div class="col-md-4">
                        <input type="text" class="form-control" id="imgCode" placeholder="Code">
                        <img src="/fh/code" id="code"><a href="#" onclick="imgCode()">看不清请换一张</a>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="button" onclick="login()" class="btn btn-default">登录</button>
                    </div>
                </div>
            </form>
        </div>
    </div>


</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/commons/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/commons/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/commons/bootbox/bootbox.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/commons/js/md5.js"></script>
</body>
<script type="text/javascript">


function imgCode(){
    var time = new Date().getTime();
    $("#code").attr("src","/fh/code?"+time);
}

function login (){
    var v_userName = $("#userName").val();
    var v_passWord = $("#passWord").val();
    var v_imgCode = $("#imgCode").val();
    var md5Pwd = hex_md5(v_passWord);
    $.ajax({
        url:"<%=request.getContextPath()%>/user/login.jhtml",
        type:"post",
        data:{"userName":v_userName,"userPwd":md5Pwd,"imgCode":v_imgCode},
        success:function (data) {
           if(data.code==200){
               location.href="/product/toQueryPage.jhtml";
           }else{
               bootbox.alert({
                   title: "提示信息",
                   message:"<span class='glyphicon glyphicon-exclamation-sign'></span><font style='color: red'>"+ data.msg+"</font>",
                   size: "small",
               })
           }
        }
    })
}

</script>
</html>
