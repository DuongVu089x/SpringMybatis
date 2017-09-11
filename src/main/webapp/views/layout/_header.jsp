<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<base href="${pageContext.request.contextPath}/">
	<meta charset="UTF-8">
	<title>Student</title>
	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
	<c:if test="${jwt!=null}">
		<meta name="jwt" content="${jwt}" />
	</c:if>
<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link href="/css/custom.css" rel="stylesheet" type="text/css" />
	<c:if test="${pageContext.request.requestURI == '/views/student.jsp'}">
		<!--Font Awesome (added because you use icons in your prepend/append)-->
		<link rel="stylesheet" href="https://formden.com/static/cdn/font-awesome/4.4.0/css/font-awesome.min.css" />
	</c:if>
</head>
<body>
	<div class="header">
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="/student/">WebSiteName</a>
					<c:if test="${username !=null}">
						<a class="navbar-brand" href="/student/">${username}</a>
					</c:if>
				</div>
				<ul class="nav navbar-nav">
					<li class="active"><a href="/student/">Home</a></li>
				</ul>
				<c:if test="${jwt!=null}">
					<form action="/logout" method="post">
						<input type="submit" class="btn btn-danger navbar-btn" value="Logout" /> 
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					</form>
				</c:if>
			</div>
		</nav>
	</div>