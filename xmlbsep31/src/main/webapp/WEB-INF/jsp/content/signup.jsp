<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@ include file="/WEB-INF/jsp/common/tagLibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="signup.title" /></title>
</head>
<body>
<c:url var="action" value="/signup" />

<div class="navbar navbar-light bg-faded">
<ul class="nav navbar-nav">
	<li class="nav-item"><a href="<c:url value="/login"/>" class="nav-link"> <fmt:message key="login.title" /></a></li>
	<li class="nav-item active"><a href="<c:url value="/signup"/>" class="nav-link"> <fmt:message key="signup.title" /></a></li>
	<li class="nav-item"><a href="<c:url value="j_spring_security_logout" />" class="nav-link"><fmt:message key="home.logout" /></a></li>
</ul>
</div>


<form:form id="signupForm" action="${action}" method="post" modelAttribute="userDTO">
	<fieldSet> 
		
		<form:label path="firstName"><fmt:message key="signup.firstname" /></form:label>
		<form:input path="firstName" cssErrorClass="error" /><form:errors path="firstName" cssClass="errorMessage" /><br />		
		
		<form:label path="lastName"><fmt:message key="signup.lastname" /></form:label>
		<form:input path="lastName" cssErrorClass="error" /><form:errors path="lastName" cssClass="errorMessage" /><br />
		
		<form:label path="address"><fmt:message key="signup.address" /></form:label>
		<form:input path="address" cssErrorClass="error" /><form:errors path="address" cssClass="errorMessage" /><br />	
		
		<form:label path="username"><fmt:message key="login.email" /></form:label>
		<form:input path="username" cssErrorClass="error" /><form:errors path="username" cssClass="errorMessage" /><br />
		
		<form:label path="password"><fmt:message key="login.password" /></form:label>
		<form:input type ="password" path="password" cssErrorClass="error" /><form:errors path="password" cssClass="errorMessage" /><br />
		
		<form:label path="password2"><fmt:message key="signup.password2" /></form:label>
		<form:input type ="password" path="password2" cssErrorClass="error" /><form:errors path="password2" cssClass="errorMessage" /><br />
		
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

</body>
</html>