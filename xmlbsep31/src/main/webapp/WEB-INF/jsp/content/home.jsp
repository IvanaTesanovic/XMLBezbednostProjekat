<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/jsp/common/tagLibs.jsp"%>
<html>
<head>
<title><fmt:message key="home.title"></fmt:message></title>
</head>
<c:url var="action" value="/home" />
<body>

<ul id="mainPages">
				<li><a href="<c:url value="j_spring_security_logout" />"><fmt:message key="home.logout" /></a></li>
</ul>

<div id="hello">
home page, vuhu
</div>
</body>
</html>