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
	<title><fmt:message key="akati.dodaj" /></title>
</head>
<body onload='document.dodajAKtForm.focus();'>
<c:url var="action" value="/dodajAkt" />

<h2><fmt:message key="akati.dodaj" /></h2>

<div class="navbar navbar-light bg-faded">
<ul class="nav navbar-nav">
	<li class="nav-item"><a href="<c:url value="/home" />" class="nav-link"><fmt:message key="home.title" /></a></li>
	<li class="nav-item"><a href="<c:url value="/spisakAkata"/>" class="nav-link"> <fmt:message key="akati.spisak" /></a></li>
	<li class="nav-item"><a href="<c:url value="/spisakAmandmana"/>" class="nav-link"> <fmt:message key="amandmani.spisak" /></a></li>
	<li class="nav-item active"><a href="<c:url value="/dodajAkt"/>" class="nav-link"> <fmt:message key="akati.dodaj" /></a></li>
	<li class="nav-item"><a href="<c:url value="/dodajAmandman"/>" class="nav-link"> <fmt:message key="amandmani.dodaj" /></a></li>
	<li class="nav-item"><a href="<c:url value="j_spring_security_logout" />" class="nav-link"><fmt:message key="home.logout" /></a></li>
</ul>
</div>

<form:form id="dodajAktForm" action="${action}" method="post" modelAttribute="predlogDTO">
	<form:errors path="*" cssClass="errorblock" element="div"/>
	<table>
		<tr>
			<td><form:textarea path="text" width="50em" height="25em"/></td>
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