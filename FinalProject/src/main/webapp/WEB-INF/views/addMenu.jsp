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
		  <li class="active"><a href="${contextPath}/restaurant/menu.htm">Menu</a></li>
		  <li><a href="${contextPath}/restaurant/res-info.htm">Restaurant Information</a></li>
		  <li><a href="${contextPath}/restaurant/received-orders.htm">Received Orders</a></li>
		  <li><a href="${contextPath}/">Logout</a></li>
		</ul>
	</div>


	<div class="panel panel-default">
		<div class="panel-heading">
			Enter Menu Information
		</div>
		<div class="panel-body">
			<c:set var="contextPath" value="${pageContext.request.contextPath}" />
			<form action="${contextPath}/restaurant/addMenu.htm" method="POST">
				<table>
					<tr>
					    <td>Menu Name:</td>
					    <td><input type="text" name="name" size="30" required="required" /></td>
					</tr>
					<tr><td></td><td></td></tr>
					<tr>
					    <td>Price:</td>
					    <td><input type="text" name="price" size="30" required="required" /></td>
					</tr>
					<tr><td></td><td></td></tr>
					<tr>
					    <td>Stock:</td>
					    <td><input type="text" name="stock" size="30" required="required" /></td>
					</tr>
					<tr><td></td><td></td></tr>
					<tr>
					    <td colspan="2"><input type="submit" value="Add Menu" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>