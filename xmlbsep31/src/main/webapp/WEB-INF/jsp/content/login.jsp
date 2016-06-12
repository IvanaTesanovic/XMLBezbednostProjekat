<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
#usernameError, #passwordError, #error {
	color: red;
	font-weight: bold;
}
</style>
<title><fmt:message key="login.title" /></title>
</head>
<body onload='document.loginForm.j_username.focus();'>
<c:url var="action" value="/login" />

<div class="navbar navbar-light bg-faded">
<ul class="nav navbar-nav">
	<li class="nav-item active"><a href="<c:url value="/login"/>" class="nav-link"> <fmt:message key="login.title" /></a></li>
	<li class="nav-item"><a href="<c:url value="/signup"/>" class="nav-link"> <fmt:message key="signup.title" /></a></li>
	<li class="nav-item"><a href="<c:url value="j_spring_security_logout" />" class="nav-link"><fmt:message key="home.logout" /></a></li>
</ul>
</div>

<h2><fmt:message key="login.welcome" /></h2>
<h4><fmt:message key="login.message" /></h4>

<form:form id="signupForm" action="${action}" method="post" modelAttribute="loginUserDTO">
	<fieldSet> 
		
		<form:label path="username"><fmt:message key="login.email" /></form:label>
		<form:input path="username" cssErrorClass="error" /><form:errors path="username" cssClass="errorMessage" /><br />
		
		<form:label path="password"><fmt:message key="login.password" /></form:label>
		<form:input type ="password" path="password" cssErrorClass="error" /><form:errors path="password" cssClass="errorMessage" /><br />

	</fieldSet>
	
	<div class="signup">
		<button type="submit" name="save" class="button">
			<fmt:message key="signup.title" />
		</button>
	</div>
	
</form:form>
<%
	String errorString = (String) request.getAttribute("error");
	if (errorString != null && errorString.trim().equals("true")) {
%>
		<div><fmt:message key="login.error"/></div>
<%
	} else {
%>
		<div> &nbsp;</div>
<%
	}
%>

<script type="text/javascript">
		function validateForm() {

			var ret = true;
			var j_username = document.forms["loginForm"]["j_username"].value;
			var j_password = document.forms["loginForm"]["j_password"].value;

			if ($("#error"))
				$("#error").remove();

			if ($("#usernameError")) {
				$("#usernameError").remove();
				document.getElementById("j_username").style.borderColor = "#CCCCCC";
			}

			if ($("#passwordError")) {
				$("#passwordError").remove();
				document.getElementById("j_password").style.borderColor = "#CCCCCC";
			}

			if (j_username == null || j_username == "") {
				$("#j_username")
						.after(
								"<td id='usernameError'> <fmt:message key='error.username.null'/> </td> ");
				document.getElementById("j_username").style.borderColor = "red";
				ret = false;
			}

			if (j_password == null || j_password == "") {
				$("#j_password")
						.after(
								"<td id='passwordError'> <fmt:message key='error.password.null'/> </td> ");
				document.getElementById("j_password").style.borderColor = "red";
				ret = false;
			}

			return ret;
		}
	</script>


</body>
</html>