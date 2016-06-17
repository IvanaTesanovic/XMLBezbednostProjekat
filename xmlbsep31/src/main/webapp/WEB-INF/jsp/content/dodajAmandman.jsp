<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/jsp/common/tagLibs.jsp"%>
<html>
<head>
	<style type="text/css">
	#error {
		color: red;
		font-weight: bold;
	}
	</style>
	<title><fmt:message key="amandmani.dodaj" /></title>
</head>
<body onload='document.dodajAmandmanForm.focus();'>
<c:url var="action" value="/dodajAmandman" />

<h2><fmt:message key="amandmani.dodaj" /></h2>

<div class="navbar navbar-light bg-faded">
<ul class="nav navbar-nav">
	<li class="nav-item"><a href="<c:url value="/home" />" class="nav-link"><fmt:message key="home.logout" /></a></li>
	<li class="nav-item active"><a href="<c:url value="/spisakAkata"/>" class="nav-link"> <fmt:message key="login.title" /></a></li>
	<li class="nav-item active"><a href="<c:url value="/spisakAmandmana"/>" class="nav-link"> <fmt:message key="login.title" /></a></li>
	<li class="nav-item"><a href="<c:url value="/dodajAkt"/>" class="nav-link"> <fmt:message key="signup.title" /></a></li>
	<li class="nav-item"><a href="<c:url value="/dodajAmandman"/>" class="nav-link"> <fmt:message key="signup.title" /></a></li>
	<li class="nav-item"><a href="<c:url value="j_spring_security_logout" />" class="nav-link"><fmt:message key="home.logout" /></a></li>
</ul>
</div>

<form:form id="dodajAmandmanForm" action="${action}" method="post" modelAttribute="predlogDTO">
	<form:errors path="*" cssClass="errorblock" element="div"/>
	<table>
		<tr>
			<td><form:textarea path="text" width="500em" height="250em"/></td>
			<%
			String errorString = (String) request.getAttribute("error");
			if (errorString != null && errorString.trim().equals("true")) {
			%>
			<td id="error"><fmt:message key="predlog.neuspesan"/></td>
			<%
			} else {
			%>
			<td> &nbsp;</td>
			<%
			}
			%>
		</tr>
		<tr>
			<td colspan="3"><input type="submit"/></td>
		</tr>
	</table>	
	
</form:form>

</body>
</html>