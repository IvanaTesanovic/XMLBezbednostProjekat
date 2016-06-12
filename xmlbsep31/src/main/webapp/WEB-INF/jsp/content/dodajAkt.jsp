<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
</head>
<body>

<form:form id="dodavanjeForma" action="${action}" method="post" modelAttribute="aktDTO">
	<fieldSet> 
		
		<form:label path="name"><fmt:message key="restaurant.name" /></form:label>
		<form:input path="name" cssErrorClass="error" /><form:errors path="name" cssClass="errorMessage" /><br />
		
		<form:label path="type"><fmt:message key="restaurant.type" /></form:label>
		<form:input path="type" cssErrorClass="error" /><form:errors path="type" cssClass="errorMessage" /><br />
		
		<form:label path="address"><fmt:message key="signup.address" /></form:label>
		<form:input path="address" cssErrorClass="error" /><form:errors path="address" cssClass="errorMessage" /><br />	
		
	</fieldSet>
	
	<div class="dodavanjeKlasa">
		<button type="submit" name="save" class="button">
			<fmt:message key="restaurant.add" />
		</button>
		<button type="submit" name="cancel" class="button">
			<fmt:message key="action.cancel" />
		</button>
	</div>
	
</form:form>

</body>
</html>