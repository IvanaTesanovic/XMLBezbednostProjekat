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
<title><fmt:message key="sednica.title" /></title>
</head>
<body onload='document.sednicaForm.username.focus();'>
<c:url var="action" value="/sednica" />

<div class="navbar navbar-light bg-faded">
<ul class="nav navbar-nav">
	<li class="nav-item active"><a href="<c:url value="/sednica"/>" class="nav-link"> <fmt:message key="sednica.title" /></a></li>
	<li class="nav-item"><a href="<c:url value="/signup"/>" class="nav-link"> <fmt:message key="signup.title" /></a></li>
	<li class="nav-item"><a href="<c:url value="j_spring_security_logout" />" class="nav-link"><fmt:message key="home.logout" /></a></li>
</ul>
</div>

<h2><fmt:message key="sednica.welcome" /></h2>
<h4><fmt:message key="sednica.message" /></h4>

<form:form id="sednicaForm" action="${action}" method="post" modelAttribute="sednicaDTO">
	<fieldSet> 
		
		<form:label path="za"><fmt:message key="sednica.za" /></form:label>
		<form:input path="za" cssErrorClass="error" /><form:errors path="za" cssClass="errorMessage" /><br />
		
		<form:label path="protiv"><fmt:message key="sednica.protiv" /></form:label>
		<form:input type ="protiv" path="protiv" cssErrorClass="error" /><form:errors path="protiv" cssClass="errorMessage" /><br />
		
		<form:label path="suzdrzani"><fmt:message key="sednica.suzdrzani" /></form:label>
		<form:input type ="suzdrzani" path="suzdrzani" cssErrorClass="error" /><form:errors path="suzdrzani" cssClass="errorMessage" /><br />

	</fieldSet>
	
	<div class="signup">
		<button type="submit" name="odobriBtn" class="button">
			<fmt:message key="sednica.odobri" />
		</button>
	</div>
	
</form:form>

</body>
</html>