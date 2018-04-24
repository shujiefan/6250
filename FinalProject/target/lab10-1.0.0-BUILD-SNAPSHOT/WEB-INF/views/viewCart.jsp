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
		  <li><a href="${contextPath}/customer/restaurants-list.htm">Restaurants</a></li>
		  <li><a href="${contextPath}/customer/my-orders.htm">My Orders</a></li>
		  <li><a href="${contextPath}/customer/customer-info.htm">My Information</a></li>
		  <li class="active"><a href="${contextPath}/customer/viewCart.htm">My Cart</a></li>
		</ul>
	</div>
	
	<div class="panel panel-default">
		<div class="panel-heading">
			My Cart
		</div>
		<div class="panel-body">
			<c:set var="contextPath" value="${pageContext.request.contextPath}" />
			<form action="${contextPath}/customer/placeOrder.htm" method="POST">
				<c:forEach var="item" items="${sessionScope.itemSet}">
					<div class="list-group">
						<div class="list-group-item">
							<h4 class="list-group-item-heading">
								${item.menu.name}
							</h4>
							$${item.menu.price}   Quantity:${item.quantity}
						</div>
						<a href="${contextPath}/customer/removeItem.htm?itemId=${item.itemId}" class="list-group-item">
							<h4 class="list-group-item-heading">
								Remove
							</h4>
						</a>
					</div>
				</c:forEach>
				<input type="submit" value="Place Order" />
			</form>
		</div>
	</div>

</body>
</html>