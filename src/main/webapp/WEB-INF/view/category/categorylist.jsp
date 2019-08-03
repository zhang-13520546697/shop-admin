<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2019/6/23
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>分类管理</title>
    <jsp:include page="/common/link.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/common/nav.jsp"></jsp:include>
<div class="container">

        <div class="panel panel-info">
            <div class="panel-heading">
                分类管理
                <button type="button" class="btn btn-primary" onclick="addCategory()"><span class="glyphicon glyphicon-plus" arin-hidden="trun"></span>新增</button>
                <button type="button" class="btn btn-info" onclick="updateCategory()"><span class="glyphicon glyphicon-pencil" arin-hidden="trun"></span>修改</button>
                <button type="button" class="btn btn-danger" onclick="deleteBathCategory()"><span class="glyphicon glyphicon-trash" arin-hidden="trun"></span>删除</button>
            </div>
            <div class="panel-body">
                <ul id="categoryTree" class="ztree"></ul>
            </div>
        </div>

</div>
<!--添加树节点-->
<div id="addCategoryDiv" style="display: none">
    <form class="form-horizontal" >
        <div class="form-group ">
            <label class="control-label col-md-2">种类名称：</label>
            <div class="col-md-3">
                <input type="text" id="add_categoryName" class="form-control" placeholder="请输入种类名称..">
            </div>
        </div>
    </form>
</div>

<!--修改树节点-->
<div id="updateCategoryDiv" style="display: none">
    <form class="form-horizontal" >
        <div class="form-group ">
            <label class="control-label col-md-2">种类名称：</label>
            <div class="col-md-3">
                <input type="text" id="update_categoryName" class="form-control" placeholder="请输入种类名称..">
            </div>
        </div>
    </form>
</div>



<jsp:include page="/common/script.jsp"></jsp:include>

</body>
<script type="text/javascript">
    var v_addCategorySource;
    var v_updateCategorySource;
    $(function (){
       v_addCategorySource=$("#addCategoryDiv").html();
        v_updateCategorySource=$("#updateCategoryDiv").html();
        //初始化树
        showCategoryTree();

    })
    //展示树
    function showCategoryTree() {
        $.ajax({
            url:"/category/queryCategory.jhtml",
            type:"post",
            success:function (data){
                var setting = {
                    data: {
                        simpleData: {
                            enable: true,
                            pIdKey: "fid",
                        },
                        key: {
                            name: "categoryName"
                        }
                    }
                };
                $(document).ready(function(){
                    $.fn.zTree.init($("#categoryTree"), setting, data.data);
                });
            }
        })
    }

    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 增加树节点
     * 创建时间： 2019/6/24 
     * @Param 
     * @return 
     **/
    function addCategory(){
        var treeObj = $.fn.zTree.getZTreeObj("categoryTree");
        var nodes = treeObj.getSelectedNodes();
        if(nodes.length==1){
            var v_addCategoryDialog = bootbox.dialog({
                title: "添加节点",
                message: $("#addCategoryDiv form"),
                size: "large",
                buttons: {
                    confirm: {
                        label: '<span class="glyphicon glyphicon-ok"></span>提交',
                        className: 'btn-info',
                        callback: function () {
                            var v_categoryName = $("#add_categoryName",v_addCategoryDialog).val();
                            var v_fid = nodes[0].id;//添加出的节点是此节点的子节点

                            $.ajax({
                                url:"/category/addCategory.jhtml",
                                type:"post",
                                data:{"categoryName":v_categoryName,"fid":v_fid},
                                success:function (data){
                                   if(data.code==200){
                                       var newNodes = [{categoryName:v_categoryName}];
                                       newNodes = treeObj.addNodes(nodes[0], newNodes);
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
        }else if(nodes.length>1){
            bootbox.alert({
                title: "提示信息",
                message: "<span class='glyphicon glyphicon-exclamation-sign'></span>只能选择一项！",
                size: "small",
            })
        }else if(nodes.length==0){
            bootbox.alert({
                title: "提示信息",
                message: "<span class='glyphicon glyphicon-exclamation-sign'></span>请至少选择一项！",
                size: "small",
            })
        }
        //还原数据
        $("#addCategoryDiv").html(v_addCategorySource);
    }

    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 修改树节点
     * 创建时间： 2019/6/24
     * @Param
     * @return
     **/
    function updateCategory(){
        var treeObj = $.fn.zTree.getZTreeObj("categoryTree");
        var nodes = treeObj.getSelectedNodes();
        if(nodes.length==1){
            //回填数据
             $("#update_categoryName",v_updateCategoryDialog).val(nodes[0].categoryName);
            var v_updateCategoryDialog = bootbox.dialog({
                title: "修改节点",
                message: $("#updateCategoryDiv form"),
                size: "large",
                buttons: {
                    confirm: {
                        label: '<span class="glyphicon glyphicon-ok"></span>提交',
                        className: 'btn-info',
                        callback: function () {
                            //更新
                            var v_id = nodes[0].id;
                            var v_categoryName = $("#update_categoryName",v_updateCategoryDialog).val();
                            $.ajax({
                                url:"/category/updateCategory.jhtml",
                                type:"post",
                                data:{"categoryName":v_categoryName,"id":v_id},
                                success:function (data){
                                    if(data.code==200){
                                        if (nodes.length>0) {
                                            nodes[0].categoryName = v_categoryName;
                                            treeObj.updateNode(nodes[0]);
                                        }
                                    }
                                }
                            })
                        },
                        cancel: {
                            label: "<span class='glyphicon glyphicon-remove'></span>取消",
                            className: 'btn-danger',
                            callback: function () {
                            }
                        }
                    }
                }
            })
            //还原数据
            $("#updateCategoryDiv").html(v_updateCategorySource);
        }else if(nodes.length>0){
            bootbox.alert({
                title: "提示信息",
                message: "<span class='glyphicon glyphicon-exclamation-sign'></span>对不起,只能选择一项！",
                size: "small",
            })
        }else if(nodes==0){
            bootbox.alert({
                title: "提示信息",
                message: "<span class='glyphicon glyphicon-exclamation-sign'></span>请至少选择一项！",
                size: "small",
            })
        }
    }

    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 删除
     * 创建时间： 2019/6/24
     * @Param
     * @return
     **/
    function deleteBathCategory(){
        var treeObj = $.fn.zTree.getZTreeObj("categoryTree");
        var nodes = treeObj.getSelectedNodes();
        var transformToArray = treeObj.transformToArray(nodes);

        if(nodes.length>0){
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
                                ids=[];
                                for (var i=0; i <transformToArray.length; i++) {
                                    ids.push(transformToArray[i].id);
                                }
                                $.ajax({
                                    url:"/category/deleteCategory.jhtml",
                                    type:"post",
                                    data:{"ids":ids},
                                    success:function (data){
                                        if(data.code==200){
                                            for (var i=0; i <nodes.length; i++) {
                                                treeObj.removeNode(nodes[i]);
                                            }
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
        }else if(nodes.length==0){
            bootbox.alert({
                title: "提示信息",
                message: "<span class='glyphicon glyphicon-exclamation-sign'></span>请至少选择一项！",
                size: "small",
            })
        }

    }
</script>
</html>
