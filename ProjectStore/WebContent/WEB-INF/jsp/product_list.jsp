<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@ include file="navbar.jsp" %>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>会员登录</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />

		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
				width: 100%;
			}
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
		</style>
		<script type="text/javascript">
			var cid =${requestScope.cid};
			$(function() {
				/* 默认调用一次(传入第一页和cid); */
				forword(1,cid);
			});
			/* ajax跳转 */
			function forword(pageNumber,cid) {
				$.ajax({
					type:"GET",
					url:"${pageContext.request.contextPath}/ProductServlet",
					data:"method=findProductLimit&pageNumber="+pageNumber+"&cid="+cid,
					dataType:"json",
					success:function(msg) {
						/* 成功后调用拼接方法 */
						append(msg,pageNumber);
					},
					error:function() {
						alert("信息出错了");
					}
					
				});
			}
			function append(msg,pageNumber) {
				/* 获取商品列表父div */
				var $div = $("#div");
				/* 清空div内容 */
				$div.html("");
				/* 获取按钮列表ul */
				var $ul = $("#ulPage");
				$ul.html("");
				/* 如果pageNumber==1上一页不能点击 */
				if(msg.list.length!=0) {
					if(pageNumber==1) {
						$ul.append("<li class=disabled>"
								+"<a href=# aria-label=Previous onclick=forword("+(pageNumber-1)+",'"+cid+"'>"
								+"<span aria-hidden=true>&laquo;</span>"
								+"</a>"
								+"</li>");
					}else {
						$ul.append("<li >"
								+"<a href=# aria-label=Previous  onclick=forword("+(pageNumber-1)+",'"+cid+"')>"
								+"<span aria-hidden=true>&laquo;</span>"
								+"</a>"
								+"</li>");
					}
				}
				
				//获取list数据
				var list = msg.list;
				/* 拼接商品信息 */
				$(list).each(function(index,ele) {
					$div.append(
							"<div class='col-md-2'  style='text-align: center'>"
							+"<a href=${pageContext.request.contextPath}/ProductServlet?method=showProductInfo&pid="+ele.pid+"><img src=${pageContext.request.contextPath}/"+ele.pimage+" width='100' height='100' style='display: inline-block;'></a>"
							+"<p><a href=product_info.html style=color:green>"+ ele.pname+"</a></p>"
							+"<p><font color=#FF0000>商城价：&yen;"+ele.shop_price+"</font></p>"
							+"</div>"	
					);
				}); 
				/* 拼接按钮信息 */
				var page = msg.totalPage;
				for(var i=1;i<=page;i++) {
					var li = document.createElement("li");
					if(i==pageNumber) {
						//选中页面和i一致的话标记按钮为选中
						$(li).addClass("active");
						$(li).append("<a >"+i+"</a>");
					}else {
						$(li).append("<a onclick=forword("+i+",'"+cid+"')>"+i+"</a>");
					}
					
					$ul.append($(li));
				}
				/* 如果最已经是最后一页下一页按钮不能点击 */
				if(msg.list!=0) {
					if(pageNumber==msg.totalPage) {
						$ul.append("<li class=disabled>"
								+"<a href=# aria-label=Next  onclick=forword("+(pageNumber+1)+",'"+cid+"')>"
								+"<span aria-hidden='true'>&raquo;</span>"
								+"</a>"
								+"</li>");
					}else {
						$ul.append("<li >"
								+"<a href=# aria-label=Next  onclick=forword("+(pageNumber+1)+",'"+cid+"')>"
								+"<span aria-hidden='true'>&raquo;</span>"
								+"</a>"
								+"</li>");
					}
				}
				
			}
			
		</script>
	</head>

	<body>
		<div id="div" class="row" style="width:1210px;margin:0 auto;" onclick="">

			<%-- <div class="col-md-2" style="text-align: center">
				<a href="product_info.htm">
					<img src="${pageContext.request.contextPath}/products/1/cs10001.jpg" width="170" height="170" style="display: inline-block;">
				</a>
				<p><a href="product_info.html" style='color:green'>冬瓜</a></p>
				<p><font color="#FF0000">商城价：&yen;299.00</font></p>
			</div> --%>

		</div>

		<!--分页 -->
		<div style="width:380px;margin:0 auto;margin-top:50px; text-align: center">
			<ul id="ulPage" class="pagination" style="text-align:center; margin-top:10px;">
				<!-- <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li> -->
			<!-- <li class="active"><a href="#">1</a></li>  -->
				<!-- <li><a href="#">2</a></li>
			 -->
				
			</ul>
		</div>
		<!-- 分页结束=======================        -->

		<!--
       		商品浏览记录:
        -->
		<div style="width:1210px;margin:0 auto; padding: 0 9px;border: 1px solid #ddd;border-top: 2px solid #999;height: 246px;">

			<h4 style="width: 50%;float: left;font: 14px/30px " 微软雅黑">浏览记录</h4>
			<div style="width: 50%;float: right;text-align: right;"><a href="">more</a></div>
			<div style="clear: both;"></div>

			<div style="overflow: hidden;">

				<ul style="list-style: none;">
					<li style="width: 150px;height: 216;float: left;margin: 0 8px 0 0;padding: 0 18px 15px;text-align: center;"><img src="${pageContext.request.contextPath}/products/1/cs10001.jpg" width="130px" height="130px" /></li>
				</ul>

			</div>
		</div>
<%@ include file="footer.jsp" %>
	</body>

</html>