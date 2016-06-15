<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/jsp/common/tagLibs.jsp"%>
<html>
<head>
<title></title>
</head>
<c:url var="action" value="/home" />
<body>

<ul id="mainPages">
	<li class="nav-item active"><a href="<c:url value="/home" />" class="nav-link"><fmt:message key="home.title" /></a></li>
	<li class="nav-item"><a href="<c:url value="j_spring_security_logout" />" class="nav-link"><fmt:message key="home.logout" /></a></li>
</ul>

<div id="mainDiv">
	<c:url var="action" value="/home" />
	<form:form id="formSearchAkt" action="${action}" method="post" modelAttribute="searchAktDTO">
		<fieldSet> 
		
			<form:label path="metapodatak"><fmt:message key="home.metapodatak" /></form:label>
			<form:input path="metapodatak" cssErrorClass="error" /><form:errors path="metapodatak" cssClass="errorMessage" /><br />
		
			<form:label path="sadrzaj"><fmt:message key="home.sadrzaj" /></form:label>
			<form:input type ="sadrzaj" path="sadrzaj" cssErrorClass="error" /><form:errors path="sadrzaj" cssClass="errorMessage" /><br />

		</fieldSet>
	
		<div class="signup">
			<button type="submit" name="search" class="button">
				<fmt:message key="home.search" />
			</button>
		</div>							
	</form:form>
</div>


</body>
</html>