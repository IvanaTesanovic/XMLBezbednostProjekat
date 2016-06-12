<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/jsp/common/tagLibs.jsp"%>
<html>
<head>
<title><fmt:message key="restaurant.editmenu"/></title>
</head>
<body>

<ul id="mainPages">
				<sec:authorize access="hasAnyRole('ROLE_ADMIN, ROLE_MANAGER, ROLE_USER')"><li><a href="<c:url value="/home"/>"> <fmt:message key="home.title" /></a></li></sec:authorize>
				<sec:authorize access="hasAnyRole('ROLE_ADMIN, ROLE_MANAGER, ROLE_USER')"><li><a href="<c:url value="/profile"/>"> <fmt:message key="home.profile" /></a></li></sec:authorize>
				<sec:authorize access="hasAnyRole('ROLE_ADMIN, ROLE_MANAGER, ROLE_USER')"><li>
				<div class="dropdown">
				<a href="#"><fmt:message key="home.restaurants"></fmt:message></a>
					<div class="dropdown-content">
					<c:forEach items="${restaurants}" var="restaurant">
						<a href="<c:url value="/restaurants/${restaurant.id}"/>"><c:out value="${restaurant.name}" /></a>
					</c:forEach>
					<sec:authorize access="hasRole('ROLE_ADMIN')">
					<a href="<c:url value="/addRestaurant"/>"><fmt:message key="restaurant.add"></fmt:message></a>
					</sec:authorize>
					</div>
				</div>
				</li></sec:authorize>
				<sec:authorize access="hasRole('ROLE_USER')">
				<li><a href="<c:url value="/friends"/>"> <fmt:message key="home.friends" /></a></li>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROLE_ADMIN, ROLE_MANAGER, ROLE_USER')"><li><a href="<c:url value="j_spring_security_logout" />"><fmt:message key="home.logout" /></a></li></sec:authorize>
</ul>


<h3>${menu.name }</h3>
<h3><fmt:message key="restaurant.dishes">:</fmt:message></h3>
<table><tr><th><fmt:message key="restaurant.name"/></th><th><fmt:message key="dish.description"/></th><th><fmt:message key="dish.price"/></th></tr>
	<c:forEach items="${dishes}" var="dd">
	<tr><td><c:out value="${dd.name }"/></td><td><c:out value="${dd.description }"/></td><td><c:out value="${dd.price }"/></td></tr>
	</c:forEach>
</table>


<form:form id="dishForm" action="${action}" method="post" modelAttribute="dishDTO">
	<fieldSet> 
		
		<form:label path="name"><fmt:message key="restaurant.name" /></form:label>
		<form:input path="name" cssErrorClass="error" /><form:errors path="name" cssClass="errorMessage" /><br />		
		
		<form:label path="description"><fmt:message key="dish.description" /></form:label>
		<form:input path="description" cssErrorClass="error" /><form:errors path="description" cssClass="errorMessage" /><br />
		
		<form:label path="price"><fmt:message key="dish.price" /></form:label>
		<form:input path="price" cssErrorClass="error" /><form:errors path="price" cssClass="errorMessage" /><br />	
		
	</fieldSet>
	
	<div class="signup">
		<button type="submit" name="save" class="button">
			<fmt:message key="menu.adddish" />
		</button>
	</div>
	
</form:form>


</body>
</html>