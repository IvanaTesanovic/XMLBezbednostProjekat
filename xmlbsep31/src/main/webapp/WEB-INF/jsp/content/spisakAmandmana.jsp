<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@ include file="/WEB-INF/jsp/common/tagLibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title><fmt:message key="amandmani.spisak"/></title>
</head>
<body>
	<div>
		<h3><fmt:message key="amandmani.spisak"/></h3>
	</div>

	<div class="navbar navbar-light bg-faded">
	<ul class="nav navbar-nav">
		<li class="nav-item"><a href="<c:url value="/home"/>" class="nav-link"><fmt:message key="home.title" /></a></li>
		<li class="nav-item"><a href="<c:url value="/spisakAkata"/>" class="nav-link"> <fmt:message key="akati.spisak" /></a></li>
		<li class="nav-item active"><a href="<c:url value="/spisakAmandmana"/>" class="nav-link"> <fmt:message key="amandmani.spisak" /></a></li>
		<li class="nav-item"><a href="<c:url value="/dodajAkt"/>" class="nav-link"> <fmt:message key="akati.dodaj" /></a></li>
		<li class="nav-item"><a href="<c:url value="/dodajAmandman"/>" class="nav-link"> <fmt:message key="amandmani.dodaj" /></a></li>
		<li class="nav-item"><a href="<c:url value="j_spring_security_logout" />" class="nav-link"><fmt:message key="home.logout" /></a></li>
	</ul>
	</div>
	
	<div>
	<table border="2">
		<tr>
			<th> Predlozeno resenje </th>
			<th> Sadrzaj resenja </th>
			<th> ID referencirajuceg akta </th>
			<th> ID referencirajuce odredbe </th>
		</tr>
		
		<c:forEach items="${spisakAman}" var = "aman">
			<tr>
				<td><c:out value="${aman.predlozenoResenje}"/></td>
				<td><c:out value="${aman.sadrzajResenja}"/></td>
				<td><c:out value="${aman.ID_akta}"/></td>
				<td><c:out value="${aman.ID_odredbe}"/></td>
			</tr>
		</c:forEach>
	</table>
	</div>

</body>
</html>