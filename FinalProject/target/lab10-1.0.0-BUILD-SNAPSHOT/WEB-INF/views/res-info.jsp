<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Restaurant Information</title>
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
		  <li><a href="${contextPath}/restaurant/menu.htm">Menu</a></li>
		  <li class="active"><a href="${contextPath}/restaurant/res-info.htm">Restaurant Information</a></li>
		</ul>
	</div>


	<div class="panel panel-default">
		<div class="panel-heading">
			Enter Restaurant Information
		</div>
		<div class="panel-body">
			<c:set var="contextPath" value="${pageContext.request.contextPath}" />
			<form action="${contextPath}/restaurant/res-info.htm" method="POST">
				<table>
					<tr>
					    <td>Restaurant Name:</td>
					    <td><input type="text" name="name" size="30" required="required" /></td>
					</tr>
					<tr><td></td><td></td></tr>
					<tr>
					    <td>Phone Number:</td>
					    <td><input type="text" name="phoneNumber" size="30" required="required" /></td>
					</tr>
					<tr><td></td><td></td></tr>
					<tr>
					    <td>Address:</td>
					    <td><input type="text" name="address" size="30" required="required" /></td>
					</tr>
					<tr><td></td><td></td></tr>
					<tr>
					    <td colspan="2"><input type="submit" value="Update Information" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>