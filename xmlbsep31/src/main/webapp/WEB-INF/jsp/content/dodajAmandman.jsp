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
	<title><fmt:message key="amandmani.dodaj" /></title>
</head>
<body onload='document.dodajAmandmanForm.focus();'>
<c:url var="action" value="/dodajAmandman" />

<h2><fmt:message key="amandmani.dodaj" /></h2>

<div class="navbar navbar-light bg-faded">
<ul class="nav navbar-nav">
	<li class="nav-item"><a href="<c:url value="/home" />" class="nav-link"><fmt:message key="home.logout" /></a></li>
	<li class="nav-item"><a href="<c:url value="/spisakAkata"/>" class="nav-link"> <fmt:message key="akati.spisak" /></a></li>
	<li class="nav-item"><a href="<c:url value="/spisakAmandmana"/>" class="nav-link"> <fmt:message key="amandmani.spisak" /></a></li>
	<li class="nav-item"><a href="<c:url value="/dodajAkt"/>" class="nav-link"> <fmt:message key="akati.dodaj" /></a></li>
	<li class="nav-item active"><a href="<c:url value="/dodajAmandman"/>" class="nav-link"> <fmt:message key="amandmani.dodaj" /></a></li>
	<li class="nav-item"><a href="<c:url value="j_spring_security_logout" />" class="nav-link"><fmt:message key="home.logout" /></a></li>
</ul>
</div>

<form:form id="dodajAmandmanForm" action="${action}" method="post" modelAttribute="amandmanDTO">
	<fieldSet> 
		
		<form:label path="idAkta"><fmt:message key="amandmani.idakta" /></form:label>
		<form:input path="idAkta" cssErrorClass="error" /><form:errors path="idAkta" cssClass="errorMessage" /><br />
		
		<form:label path="putanjaOdredbe"><fmt:message key="amandmani.putanjaodredbe" /></form:label>
		<form:input path="putanjaOdredbe" cssErrorClass="error" /><form:errors path="putanjaOdredbe" cssClass="errorMessage" /><br />
		
		<form:label path="predlozenoResenje"><fmt:message key="amandmani.predlozenoresenje" /></form:label>
		<form:input path="predlozenoResenje" cssErrorClass="error" /><form:errors path="predlozenoResenje" cssClass="errorMessage" /><br />
		
		<form:label path="sadrzajResenja"><fmt:message key="amandmani.sadrzajresenja" /></form:label>
		<form:input path="sadrzajResenja" cssErrorClass="error" /><form:errors path="sadrzajResenja" cssClass="errorMessage" /><br />

	</fieldSet>
	
	<div class="signup">
		<button type="submit" name="save" class="button">
			<fmt:message key="amandmani.dodaj" />
		</button>
	</div>
	
</form:form>

</body>
</html>