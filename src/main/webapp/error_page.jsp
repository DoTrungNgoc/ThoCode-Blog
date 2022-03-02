<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="include/lib_top.jsp" %>
<title>Sorry ! Some thing went wrong</title>
</head>
<body>
	<div class="container text-center">
		<img alt="" src="img/err.png" class="img-fluid">
		<h3 class="display-3">Sorry ! Something went wrong</h3>
		<a href="index.jsp" class="btn btn-primary btn-lg text-white mt3">Home</a>
	</div>
	
	
	<%@ include file="include/lib_bottom.jsp" %>
</body>
</html>