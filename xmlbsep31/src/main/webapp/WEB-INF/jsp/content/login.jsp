<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
#error {
	color: red;
	font-weight: bold;
}
</style>
<title><fmt:message key="login.title" /></title>
</head>
<body onload='document.loginForm.username.focus();'>
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

<form:form id="loginForm" action="${action}" method="post" modelAttribute="loginUserDTO">
	<fieldSet> 
		
		<form:label path="username"><fmt:message key="login.username" /></form:label>
		<form:input path="username" cssErrorClass="error" /><form:errors path="username" cssClass="errorMessage" /><br />
		
		<form:label path="password"><fmt:message key="login.password" /></form:label>
		<form:input type ="password" path="password" cssErrorClass="error" /><form:errors path="password" cssClass="errorMessage" /><br />

	</fieldSet>
	
	<div class="signup">
		<button type="submit" name="save" class="button">
			<fmt:message key="login.title" />
		</button>
	</div>
	
</form:form>
<%
	String errorString = (String) request.getAttribute("error");
	if (errorString != null && errorString.trim().equals("true")) {
%>
		<div id="error"><fmt:message key="login.error"/></div>
<%
	} else {
%>
		<div> &nbsp;</div>
<%
	}
%>
</body>
</html>