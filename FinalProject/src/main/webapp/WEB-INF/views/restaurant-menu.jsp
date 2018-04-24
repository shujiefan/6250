<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<div class="page-header">
	    <h1>Need something to eat?</h1>
	    <br>
	    <ul class="nav nav-pills">
		  <li class="active"><a href="${contextPath}/customer/restaurants-list.htm">Restaurants</a></li>
		  <li><a href="${contextPath}/customer/my-orders.htm">My Orders</a></li>
		  <li><a href="${contextPath}/customer/customer-info.htm">My Information</a></li>
		  <li><a href="${contextPath}/customer/viewCart.htm">My Cart</a></li>
		  <li><a href="${contextPath}/">Logout</a></li>
		</ul>
	</div>
	
	<p>Welcome to ${sessionScope.restaurant}</p>

    <c:forEach var="menu" items="${sessionScope.menuList}">
		<form action="${contextPath}/customer/addToCart.htm" method="GET">
			<div class="list-group">
				<div class="list-group-item">
					<div class="list-group-item-heading">
						<strong>${menu.name}</strong> &nbsp;&nbsp;&nbsp; $${menu.price}					
					</div>
					<select name="quantity">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
					</select> 
					<input type="hidden" name="menuId" value="${menu.menuId}">&nbsp;&nbsp;
					<input type="submit" class="btn btn-default" value="Add To Cart"/>
				</div>
			</div>
		</form>
    </c:forEach>
</body>
</html>