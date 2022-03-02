<%@page import="com.thocode.blog.entities.Message"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<%@ include file="include/lib_top.jsp" %>
</head>
<body>
	<%@ include file="include/normal_navbar.jsp"%>

	<main
		class="d-flex align-items-center primary-background banner-background"
		style="height: 80vh">
		<div class="container">
			<div class="row">
				<div class="col-md-4 offset-md-4">
					<div class="card">
						<div class="card-header primary-background text-center text-white">
							<span class="fa fa-user-plus fa-3x"></span>
							<p>Login here</p>
						</div>

						<%
							Message msg = (Message)  session.getAttribute("msg");
							if (msg != null){
						%>
						<div class="alert <%= msg.getCssClass() %>" role="alert">
							<%= msg.getContent() %>
						</div>
						<%
								session.removeAttribute("msg");
							}
						%>

						<div class="card-body">
							<form action="login" method="POST">
								<div class="form-group">
									<label for="exampleInputEmail1">Email address</label> <input
										name="email" required type="email" class="form-control"
										id="exampleInputEmail1" aria-describedby="emailHelp"
										placeholder="Enter email"> <small id="emailHelp"
										class="form-text text-muted">We'll never share your
										email with anyone else.</small>
								</div>
								<div class="form-group">
									<label for="exampleInputPassword1">Password</label> <input
										name="password" required type="password" class="form-control"
										id="exampleInputPassword1" placeholder="Password">
								</div>

								<div class="container text-center">
									<button type="submit" class="btn btn-primary">Submit</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>


	<%@ include file="include/lib_bottom.jsp" %>
</body>
</html>