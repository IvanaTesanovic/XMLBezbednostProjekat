<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
</head>
<body>

<div class="navbar navbar-light bg-faded">
<ul class="nav navbar-nav">
	<li class="nav-item"><a href="<c:url value="/home" />" class="nav-link"><fmt:message key="home.logout" /></a></li>
	<li class="nav-item"><a href="<c:url value="/spisakAkata"/>" class="nav-link"> <fmt:message key="login.title" /></a></li>
	<li class="nav-item active"><a href="<c:url value="/dodajAkt"/>" class="nav-link"> <fmt:message key="signup.title" /></a></li>
	<li class="nav-item"><a href="<c:url value="j_spring_security_logout" />" class="nav-link"><fmt:message key="home.logout" /></a></li>
</ul>
</div>

<form:form id="dodavanjeForma" action="${action}" method="post" modelAttribute="aktDTO">
	<fieldSet> 
		
		<form:label path="name"><fmt:message key="restaurant.name" /></form:label>
		<form:input path="name" cssErrorClass="error" /><form:errors path="name" cssClass="errorMessage" /><br />
		
		<form:label path="type"><fmt:message key="restaurant.type" /></form:label>
		<form:input path="type" cssErrorClass="error" /><form:errors path="type" cssClass="errorMessage" /><br />
		
		<form:label path="address"><fmt:message key="signup.address" /></form:label>
		<form:input path="address" cssErrorClass="error" /><form:errors path="address" cssClass="errorMessage" /><br />	
		
	</fieldSet>
	
	<div class="dodavanjeKlasa">
		<button type="submit" name="save" class="button">
			<fmt:message key="restaurant.add" />
		</button>
		<button type="submit" name="cancel" class="button">
			<fmt:message key="action.cancel" />
		</button>
	</div>
	
</form:form>

</body>
</html>