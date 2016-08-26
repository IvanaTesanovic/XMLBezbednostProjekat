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
<title><fmt:message key="signup.title" /></title>
</head>

<body onload='document.signppForm.username.focus();'>
<c:url var="action" value="/signup" />

	<div class="navbar navbar-light bg-faded">
	<ul class="nav navbar-nav">
		<li class="nav-item"><a href="<c:url value="/login"/>" class="nav-link"> <fmt:message key="login.title" /></a></li>
		<li class="nav-item active"><a href="<c:url value="/signup"/>" class="nav-link"> <fmt:message key="signup.title" /></a></li>
	</ul>
	</div>

<form:form id="signupForm" action="${action}" method="post" modelAttribute="signUpUserDTO">
	<fieldSet> 
		<form:label path="username"><fmt:message key="signup.username" /></form:label>
		<form:input path="username" cssErrorClass="error" /><form:errors path="username" cssClass="errorMessage" /><br />
		
		<form:label path="name"><fmt:message key="signup.name" /></form:label>
		<form:input path="name" cssErrorClass="error" /><form:errors path="name" cssClass="errorMessage" /><br />		
		
		<form:label path="lastname"><fmt:message key="signup.lastname" /></form:label>
		<form:input path="lastname" cssErrorClass="error" /><form:errors path="lastname" cssClass="errorMessage" /><br />
		
		<form:label path="role"><fmt:message key="signup.role" /></form:label>
		<form:input path="role" cssErrorClass="error" /><form:errors path="role" cssClass="errorMessage" /><br />	
		
		<form:label path="email"><fmt:message key="signup.email" /></form:label>
		<form:input type ="email" path="email" cssErrorClass="error" /><form:errors path="email" cssClass="errorMessage" /><br />
		
		<form:label path="password"><fmt:message key="signup.password" /></form:label>
		<form:input type ="password" path="password" cssErrorClass="error" /><form:errors path="password" cssClass="errorMessage" /><br />
		
	</fieldSet>
	
	<div class="signup">
		<button type="submit" name="save" class="button">
			<fmt:message key="signup.title" />
		</button>
		<button type="submit" name="cancel" class="button">
			<fmt:message key="action.cancel" />
		</button>
	</div>
	
</form:form>
<%
	String errorString = (String) request.getAttribute("error");
	if (errorString != null && errorString.trim().equals("true")) {
%>
		<div id="error"><fmt:message key="signup.error"/></div>
<%
	} else {
%>
		<div> &nbsp;</div>
<%
	}
%>
</body>
</html>