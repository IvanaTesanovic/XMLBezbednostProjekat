<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@ include file="/WEB-INF/jsp/common/tagLibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title><fmt:message key="akati.spisak"/></title>
</head>
<body>
	<div>
		<h3><fmt:message key="akati.spisak"/></h3>
	</div>

	<div class="navbar navbar-light bg-faded">
	<ul class="nav navbar-nav">
		<li class="nav-item"><a href="<c:url value="/home"/>" class="nav-link"><fmt:message key="home.title" /></a></li>
		<li class="nav-item"><a href="<c:url value="/login"/>" class="nav-link"> <fmt:message key="login.title" /></a></li>
		<li class="nav-item"><a href="<c:url value="/signup"/>" class="nav-link"> <fmt:message key="signup.title" /></a></li>
		<li class="nav-item active"><a href="<c:url value="/spisakAkata"/>" class="nav-link"> <fmt:message key="akati.spisak" /></a></li>
		<li class="nav-item"><a href="<c:url value="/spisakAmandmana"/>" class="nav-link"> <fmt:message key="amandmani.spisak" /></a></li>
		<li class="nav-item"><a href="<c:url value="/dodajAkt"/>" class="nav-link"> <fmt:message key="akati.dodaj" /></a></li>
		<li class="nav-item"><a href="<c:url value="/dodajAmandman"/>" class="nav-link"> <fmt:message key="amandmani.dodaj" /></a></li>
		<li class="nav-item"><a href="<c:url value="/sednica"/>" class="nav-link"> <fmt:message key="sednica.title" /></a></li>
		<li class="nav-item"><a href="<c:url value="j_spring_security_logout" />" class="nav-link"><fmt:message key="home.logout" /></a></li>
	</ul>
	</div>
	
	<div>
	<table border="2">
		<tr>
			<th>ID</th>
			<th>Naziv</th>
			<th>Datum</th>
		</tr>
		
		<c:forEach items="${spisakZakona}" var = "zakon">
			<tr>
				<td><c:out value="${zakon.ID}"/></td>
				<td><c:out value="${zakon.naziv}"/></td>
				<td><c:out value="${zakon.datum}"/></td>
				<td><a href="<c:url value="/sednica/${zakon.ID}"/>"><fmt:message key="akt.usvoji"/></a></td>
				<td><a href="<c:url value="/spisakAkata/${zakon.ID}"/>"><fmt:message key="akt.odbij"/></a></td>
			</tr>
		</c:forEach>
	</table>
	</div>
	
</body>
</html>