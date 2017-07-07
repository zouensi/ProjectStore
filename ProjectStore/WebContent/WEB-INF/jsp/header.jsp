<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container-fluid">
			<div class="col-md-4">
				<img src="${pageContext.request.contextPath}/img/logo2.png" />
			</div>
			<div class="col-md-5">
				<img src="${pageContext.request.contextPath}/img/header.png" />
			</div>
			<div class="col-md-3" style="padding-top: 20px">
				<ol class="list-inline">
					<c:if test="${ empty sessionScope.user }">
						<li><a
						href="${pageContext.request.contextPath}/UserServlet?method=login">登录</a></li>
						<li><a
							href="${pageContext.request.contextPath}/UserServlet?method=register">注册</a></li>
						<li><a href="${pageContext.request.contextPath}/CartServlet?method=cart">购物车</a></li>
					</c:if>
					<c:if test="${not empty sessionScope.user }">
						<li>欢迎您:${ sessionScope.user.name }</li>
						<li><a
							href="${pageContext.request.contextPath}/OrderServlet?method=orderList">订单</a></li>
						<li><a href="${pageContext.request.contextPath}/CartServlet?method=cart">购物车</a></li>
						<li><a href="${pageContext.request.contextPath}/UserServlet?method=quit">退出</a></li>
					</c:if>
				</ol>
			</div>
		</div>
</body>
</html>