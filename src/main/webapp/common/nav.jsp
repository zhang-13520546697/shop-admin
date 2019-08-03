<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2019/6/27
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/commons/js/jquery-3.3.1.js"></script>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <a class="navbar-brand"  href="#">电商管理系统</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav" id="menuItems">
                <li class="active" id="item_1"><a href="/product/toQueryPage.jhtml#1">商品管理</a></li>
                <li id="item_2" class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">品牌管理<span class="caret"></a>
                    <ul class="dropdown-menu">
                        <li><a href="/brand/toBrandPage.jhtml#2">品牌列表</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="/brand/toAddBrandPage.jhtml#2">品牌添加</a></li>
                    </ul>
                </li>
                <li id="item_3"><a href="/user/toAddUserPage.jhtml#3">用户管理</a></li>
                <li id="item_4"><a href="/log/toLogPage.jhtml#4">日志管理</a></li>

                <li id="item_5" class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">ztree管理<span class="caret"></a>
                    <ul class="dropdown-menu">
                        <li><a href="/area/toAreaPage.jhtml#5">地区管理</a></li>
                        <li role="separator" class="divider"></li>
                        <li id="item_6"><a href="/category/toCategoryPage.jhtml#6">分类管理</a></li>
                    </ul>
                </li>
                <li id="item_7"><a href="/rubbish/toRubbishPage.jhtml#7">商品垃圾回收站</a></li>
                <li id="item_8"><a href="/product/toHotProduct.jhtml#8">热销商品</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="#">欢迎登录${user.realName}爷
                    <c:if test="${!empty user.logTime}">
                        ，您上一次登录的时间为<fmt:formatDate value="${user.logTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                    </c:if>
                </a></li>
                <li><a href="JavaScript:logOut()">注销</a></li>
            </ul>

        </div>
    </div>
</nav>
<script type="text/javascript">
//注销
    function logOut(){
        $.ajax({
            url:"/user/logOut.jhtml",
            type:"post",
            success:function (data){
                if(data.code==200){
                    location.href="/";
                }
            }
        })
    }

    $(function (){
        initMenuItem();
    })

    function initMenuItem(){
        //hash 截取路径中#符即其后的值
        var hash = window.location.hash;
        if(hash.length>0){
            hash=hash.substring(1);
            //清除其他选项
            $("#menuItems > li").removeClass("active");
            $("#item_"+hash).addClass("active");
        }

    }
</script>