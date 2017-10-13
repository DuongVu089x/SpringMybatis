<%@page pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="layout/_header.jsp" />

<div class="login-page">
	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<strong> Sign in to continue</strong>
					</div>
					<div class="panel-body">
						 <form:form method="POST" modelAttribute="userForm" class="form-signin">
					        <h2 class="form-signin-heading">Create your account</h2>
					        <spring:bind path="username">
					            <div class="form-group ${status.error ? 'has-error' : ''}">
					                <form:input type="text" path="username" class="form-control" placeholder="Username" autofocus="true"></form:input>
					                <form:errors path="username"></form:errors>
					            </div>
					        </spring:bind>
					
					        <spring:bind path="password">
					            <div class="form-group ${status.error ? 'has-error' : ''}">
					                <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
					                <form:errors path="password"></form:errors>
					            </div>
					        </spring:bind>
					
					        <spring:bind path="passwordConfirm">
					            <div class="form-group ${status.error ? 'has-error' : ''}">
					                <form:input type="password" path="passwordConfirm" class="form-control" placeholder="Confirm your password"></form:input>
					                <form:errors path="passwordConfirm"></form:errors>
					            </div>
					        </spring:bind>
					        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
					    </form:form>
					</div>
					
                </div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="layout/_footer.jsp" />