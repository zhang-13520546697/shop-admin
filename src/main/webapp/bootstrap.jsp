<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>Insert title here</title>
<link href="<%=request.getContextPath() %>/commons/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="container-fluid">

<div class="row">
	<div class="col-md-12">
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">飞狐教育</a>
				</div>
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li class="active"><a href="#">校园风采 <span class="sr-only">(current)</span></a></li>
						<li><a href="#">师资团队</a></li>
						<li><a href="#">明星学员</a></li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">java课程<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="#">云计算</a></li>
								<li><a href="#">云应用</a></li>
								<li><a href="#">云服务</a></li>
								<li role="separator" class="divider"></li>
								<li><a href="#">大数据</a></li>
								<li role="separator" class="divider"></li>
								<li><a href="#">数据挖掘</a></li>
							</ul>
						</li>
					</ul>

				</div><!-- /.navbar-collapse -->
			</div><!-- /.container-fluid -->
		</nav>
	</div>
</div>

<div class="row">
	<div class="col-md-2" >
		<div class="list-group">
			<button type="button" class="list-group-item">Cras justo odio</button>
			<button type="button" class="list-group-item">Dapibus ac facilisis in</button>
			<button type="button" class="list-group-item">Morbi leo risus</button>
			<button type="button" class="list-group-item">Porta ac consectetur ac</button>
			<button type="button" class="list-group-item">Vestibulum at eros</button>
			<button type="button" class="list-group-item">Vestibulum at eros</button>
			<button type="button" class="list-group-item">Vestibulum at eros</button>
			<button type="button" class="list-group-item">Vestibulum at eros</button>
			<button type="button" class="list-group-item">Vestibulum at eros</button>
		</div>
	</div>

	<div class="col-md-10">
		<div  class="row">
			<div class="page-header">
				<h1>配置数据源 <small>Configure data sources</small></h1>
			</div>

			<form class="form-horizontal">
				<div class="form-group">

					<label for="exampleInputName1" class="col-md-2 control-label">IP地址</label>
					<div class="col-md-3">
						<input type="text" class="form-control" id="exampleInputName1" placeholder="IPURL">
					</div>

					<label for="exampleInputName2" class="col-md-2 control-label">数据库名</label>
					<div class="col-md-3">
						<input type="text" class="form-control" id="exampleInputName2" placeholder="msh">
					</div>

				</div>
				<div class="form-group">

					<label  class="col-md-2 control-label">登录时间</label>
					<div class="col-md-3 ">
						<div class="input-group">
							<input type="text" class="form-control col-md-1"  placeholder="">
							<span class="input-group-addon" id="basic-addon1">
								  <span class="glyphicon glyphicon-calendar"></span>
							</span>
							<input type="text" class="form-control col-md-2" placeholder="">
						</div>
					</div>

					<label  class="col-md-2 control-label">密码</label>
					<div class="col-md-3">
						<input type="text" class="form-control"  placeholder="password">
					</div>
				</div>
				<div align="center">
					<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-ok"></span>Submit</button>
					<button type="reset" class="btn btn-default"><span class="glyphicon glyphicon-repeat"></span>Reset</button>
				</div>
			</form>
		</div>

		<div  class="row">
				<div align="right" style="background-color: #D8D8D8">
					<button type="submit" class="btn btn-info"><span class="glyphicon glyphicon-plus"></span>添加用户</button>
					<button type="reset" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span>批量删除</button>
				</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">用户列表</h3>
				</div>
				<div class="panel-body">
					<div class="container">
						<table class="table table-bordered">
						<tr>
							<td>选择</td>
							<td>用户名</td>
							<td>年龄</td>
							<td>籍贯</td>
							<td>职位</td>
							<td>学历</td>
							<td>公司</td>
							<td>电话</td>
							<td>邮箱</td>
							<td>操作</td>
						</tr>
						<tr>
							<td>
								<input type="checkbox" value="" name="GG"/>
							</td>
							<td>张三</td>
							<td>18</td>
							<td>山东</td>
							<td>经理</td>
							<td>本科</td>
							<td>爱奇艺</td>
							<td>13545</td>
							<td></td>
							<td>
								<div class="btn-group" role="group" aria-label="...">
									<button type="button" class="btn btn-info"><span class="glyphicon glyphicon-pencil"></span>修改</button>
									<button type="button" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span>删除</button>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<input type="checkbox" value="" name="GG"/>
							</td>
							<td>张三</td>
							<td>18</td>
							<td>山东</td>
							<td>经理</td>
							<td>本科</td>
							<td>爱奇艺</td>
							<td>13</td>
							<td>12</td>
							<td>
								<div class="btn-group" role="group" aria-label="...">
									<button type="button" class="btn btn-info"><span class="glyphicon glyphicon-pencil"></span>修改</button>
									<button type="button" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span>删除</button>
								</div>
							</td>
						</tr>
					</table>
					</div>
					<nav aria-label="Page navigation">
						<ul class="pagination">
							<li>
								<a href="#" aria-label="Previous">
									<span aria-hidden="true">&laquo;</span>
								</a>
							</li>
							<li class="active"><a href="#">1</a></li>
							<li><a href="#">2</a></li>
							<li><a href="#">3</a></li>
							<li><a href="#">4</a></li>
							<li><a href="#">5</a></li>
							<li>
								<a href="#" aria-label="Next">
									<span aria-hidden="true">&raquo;</span>
								</a>
							</li>
						</ul>
					</nav>
				</div>
			</div>
		</div>

	</div>
</div>


</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/commons/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/commons/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>