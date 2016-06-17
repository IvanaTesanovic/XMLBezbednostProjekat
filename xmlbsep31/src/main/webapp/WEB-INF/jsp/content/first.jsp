<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/jsp/common/tagLibs.jsp"%>
<html>
<head>
<title></title>
</head>
<c:url var="action" value="/home" />
<body>

	<div class="navbar navbar-light bg-faded">
	<ul class="nav navbar-nav">
		<li class="nav-item"><a href="<c:url value="/login"/>" class="nav-link"> <fmt:message key="login.title" /></a></li>
		<li class="nav-item"><a href="<c:url value="/signup"/>" class="nav-link"> <fmt:message key="signup.title" /></a></li>
		<li class="nav-item"><a href="<c:url value="/spisakAkata"/>" class="nav-link"> <fmt:message key="akati.title" /></a></li>
		<li class="nav-item"><a href="<c:url value="/spisakAmandmana"/>" class="nav-link"> <fmt:message key="amandmani.title" /></a></li>
		<li class="nav-item"><a href="<c:url value="j_spring_security_logout" />" class="nav-link"><fmt:message key="home.logout" /></a></li>
	</ul>
	</div>
	
	<div id="hello">
	<h3>&nbsp;</h3>
	<h3><fmt:message key="home.welcome"/></h3>
	</div>

</body>
</html>