<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2019/7/11
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>热销商品</title>
    <jsp:include page="/common/link.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/common/nav.jsp"></jsp:include>

<!-- 热销商品 -->
<div class="panel panel-default">
    <div class="panel-heading">热销产品</div>
    <div class="panel-body">
        <div class="row" id="showProduct">

        </div>
        <div style="display: none" id="productTemplate">
            <div class="col-sm-6 col-md-4" >
                <div class="thumbnail" align="center">
                    <img  src="##image##" alt="图片加载失败"   style="height: 100px">
                    <div class="caption">
                        <h3>##productName##</h3>
                        <span><font color="red">##price##元</font></span>
                        <p>
                            <a href="#" class="btn btn-primary" role="button">点击购买</a>
                            <a href="#" class="btn btn-default" role="button">放入购物车</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/common/script.jsp"></jsp:include>
</body>
<script type="text/javascript">
    $(function (){
        initProductList();
    })
    //初始化热销商品展示
    function initProductList(){
        $.ajax({
            url:"/product/findHotList.jhtml",
            type:"get",
            success:function (data){
                if(data.code==200){
                    var v_data = data.data;
                    for (var i = 0; i < v_data.length; i++) {
                        var v_template =  $("#productTemplate").html();
                        var v_product = v_data[i];
                        var v_div=  v_template.replace(/##productName##/g,v_product.productName)
                            .replace(/##price##/g,v_product.productPrice)
                            .replace(/##image##/g,v_product.productPhoto);
                        $("#showProduct").append(v_div)
                    }
                }
            }
        })
    }

</script>
</html>
