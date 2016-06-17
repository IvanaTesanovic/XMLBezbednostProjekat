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
		<li class="nav-item"><a href="<c:url value="/login"/>" class="nav-link"> <fmt:message key="login.title" /></a></li>
		<li class="nav-item"><a href="<c:url value="/signup"/>" class="nav-link"> <fmt:message key="signup.title" /></a></li>
		<li class="nav-item"><a href="<c:url value="/dodajAmandman"/>" class="nav-link"> <fmt:message key="amandmani.dodaj" /></a></li>
		<li class="nav-item"><a href="<c:url value="/dodajAkt"/>" class="nav-link"> <fmt:message key="akati.dodaj" /></a></li>
		<li class="nav-item"><a href="<c:url value="/spisakAkata"/>" class="nav-link"> <fmt:message key="akati.title" /></a></li>
		<li class="nav-item"><a href="<c:url value="/spisakAmandmana"/>" class="nav-link"> <fmt:message key="amandmani.title" /></a></li>
		<li class="nav-item"><a href="<c:url value="/sednica"/>" class="nav-link"> <fmt:message key="sednica.title" /></a></li>
		<li class="nav-item"><a href="<c:url value="j_spring_security_logout" />" class="nav-link"><fmt:message key="home.logout" /></a></li>
	</ul>
	</div>

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
