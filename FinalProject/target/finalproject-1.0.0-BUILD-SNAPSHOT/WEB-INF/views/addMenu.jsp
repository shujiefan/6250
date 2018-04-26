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
			<form action="${contextPath}/restaurant/addMenu.htm" method="POST" class="form-horizontal">
				<div class="form-group">
		            <label class="col-lg-3 control-label">Menu Name:</label>
		                <div class="col-lg-5">
		                    <input type="text" class="form-control" name="name"
		                        data-bv-message="The name is not valid"
					            required
					            data-bv-notempty-message="The name is required and cannot be empty"	
		                        pattern="[a-zA-Z0-9]+"
		                        data-bv-regexp-message="The name should follow the valid type" />
		                </div>	                
		            </div>
		            
	            <div class="form-group">
	                <label class="col-lg-3 control-label">Price:</label>
	                <div class="col-lg-5">
	                    <input type="text" class="form-control" name="price"
	                        data-bv-message="The price is not valid"
				            required
				            data-bv-notempty-message="The price is required and cannot be empty"	
	                        pattern="[0-9]*\.?[0-9]*"
	                        data-bv-regexp-message="The price should follow the valid type" />
	                </div>	                
	            </div>
	            
	            <div class="form-group">
	                <label class="col-lg-3 control-label">Stock:</label>
	                <div class="col-lg-5">
	                    <input type="text" class="form-control" name="stock"
	                        data-bv-message="The stock is not valid"
				            required
				            data-bv-notempty-message="The stock is required and cannot be empty"	
	                        pattern="[0-9]+"
	                        data-bv-regexp-message="The stock should follow the valid type" />
	                </div>	                
	            </div>
	            
	            <div style="text-align:center">
				    <input type="submit" value="Add Menu" class="btn btn-primary" />
			    </div>
			</form>
		</div>
	</div>
</body>
</html>