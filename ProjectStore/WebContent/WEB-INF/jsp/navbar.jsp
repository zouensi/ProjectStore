<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
		<script type="text/javascript">
			$(function() {
				//请求分类信息栏信息
				$.ajax({
					type:"POST",
					url:"${pageContext.request.contextPath}/CategoryServlet",
					dataType:"json",
					data:"method=getCategory",
					success:function(msg) {
						var $ul = $("#ul");
						$(msg).each(function(index,ele) {
							/* <li><a href="#">电脑办公</a></li> */
							//创建li标签对象(获取js对象)
							var liObj = document.createElement("li");
							//创建li的jquery对象
							var $li = $(liObj);
							//创建a标签对象
							var aObj = document.createElement("a");
							//创建a的jquery对象
							var $a = $(aObj);
							//设置属性
							$a.attr("href","${pageContext.request.contextPath}/ProductServlet?method=productForword&cid="+ele.cid);
							//设置内容
							$a.text(ele.cname);
							//将a拼接到li中
							$li.append($a);
							//将li拼接到ul中
							$ul.append($li);
						});
					},
					error:function() {
						
					}
				});
			});
		</script>
</head>
<body>
		<!--
            	时间：2015-12-30
            	描述：导航条
            -->
		<div class="container-fluid">
			<nav class="navbar navbar-inverse">
				<div class="container-fluid">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">首页</a>
					</div>

					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav" id="ul">
							<!-- <li class="active"><a href="product_list.htm">手机数码<span
									class="sr-only">(current)</span></a></li>
							<li><a href="#">电脑办公</a></li>
							<li><a href="#">电脑办公</a></li>
							<li><a href="#">电脑办公</a></li> -->
						</ul>
						<form class="navbar-form navbar-right" role="search">
							<div class="form-group">
								<input type="text" class="form-control" placeholder="Search">
							</div>
							<button type="submit" class="btn btn-default">Submit</button>
						</form>

					</div>
					<!-- /.navbar-collapse -->
				</div>
				<!-- /.container-fluid -->
			</nav>
		</div>
</body>
</html>