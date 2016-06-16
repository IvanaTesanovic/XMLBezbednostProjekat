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
<ul id="mainPages" class="nav navbar-nav">
	<li class="nav-item active"><a href="<c:url value="/home" />" class="nav-link"><fmt:message key="home.title" /></a></li>
	<li class="nav-item"><a href="<c:url value="/dodajAmandman"/>" class="nav-link"> <fmt:message key="amandmani.dodaj" /></a></li>
	<li class="nav-item"><a href="<c:url value="/dodajAkt"/>" class="nav-link"> <fmt:message key="akati.dodaj" /></a></li>
	<li class="nav-item"><a href="<c:url value="j_spring_security_logout" />" class="nav-link"><fmt:message key="home.logout" /></a></li>
</ul>
</div>

<div id="hello">
home page, vuhu
</div>
</body>
</html>