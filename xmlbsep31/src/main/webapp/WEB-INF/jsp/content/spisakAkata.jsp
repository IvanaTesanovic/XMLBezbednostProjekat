<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@ include file="/WEB-INF/jsp/common/tagLibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head></head>
<body>

<div class="navbar navbar-light bg-faded">
<ul class="nav navbar-nav">
	<li class="nav-item"><a href="<c:url value="/home" />" class="nav-link"><fmt:message key="home.logout" /></a></li>
	<li class="nav-item active"><a href="<c:url value="/spisakAkata"/>" class="nav-link"> <fmt:message key="login.title" /></a></li>
	<li class="nav-item"><a href="<c:url value="/dodajAkt"/>" class="nav-link"> <fmt:message key="signup.title" /></a></li>
	<li class="nav-item"><a href="<c:url value="j_spring_security_logout" />" class="nav-link"><fmt:message key="home.logout" /></a></li>
</ul>
</div>

</body>
</html>