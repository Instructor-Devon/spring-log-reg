<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Login and Registration</title>
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="css/main.css" />
</head>
<body>
	<div class="container">
		<form:form class="user-form" action="/" method="POST" modelAttribute="buster">
			<h2>Register</h2>
			<div class="form-group">
				<form:label path="firstName">First Name</form:label>
				<form:errors path="firstName" />
				<form:input path="firstName" class="form-control"/>
			</div>
			<div class="form-group">
				<form:label path="lastName">Last Name</form:label>
				<form:errors path="lastName" />
				<form:input path="lastName" class="form-control"/>
			</div>
			<div class="form-group">
				<form:label path="email">Email</form:label>
				<form:errors path="email" />
				<form:input type="email" path="email" class="form-control"/>
			</div>
			<div class="form-group">
				<form:label path="password">Password</form:label>
				<form:errors path="password" />
				<form:input path="password" class="form-control"/>
			</div>
			<div class="form-group">
				<form:label path="confirmPassword">Confirm Password</form:label>
				<form:errors path="confirmPassword" />
				<form:input path="confirmPassword" class="form-control"/>
			</div>
			<button>Register</button>
		</form:form>
		<form class="user-form" action="/login" method="post">
			<h2>Login</h2>
			<p>${ error }</p>
			<div class="form-group">
				<label for="email">Email</label>
				<input type="email" name="email" id="email" class="form-control" />
			</div>
			<div class="form-group">
				<label for="password">Password</label>
				<input type="password" name="password" id="password"  class="form-control"/>
			</div>
			<button>Login</button>
		</form>
	</div>
</body>
</html>