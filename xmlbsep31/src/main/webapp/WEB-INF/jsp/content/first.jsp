<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/jsp/common/tagLibs.jsp"%>
<html>
<head>
<title><fmt:message key="home.title"></fmt:message></title>
</head>
<c:url var="action" value="/home" />
<body>

<div class="navbar navbar-light bg-faded">
<ul class="nav navbar-nav">
	<li class="nav-item"><a href="<c:url value="/login"/>" class="nav-link"> <fmt:message key="login.title" /></a></li>
	<li class="nav-item"><a href="<c:url value="/signup"/>" class="nav-link"> <fmt:message key="signup.title" /></a></li>
	<li class="nav-item"><a href="<c:url value="j_spring_security_logout" />" class="nav-link"><fmt:message key="home.logout" /></a></li>
</ul>
</div>

<div id="hello">
<h1><fmt:message key="home.welcome"/></h1>
</div>
</body>
</html>