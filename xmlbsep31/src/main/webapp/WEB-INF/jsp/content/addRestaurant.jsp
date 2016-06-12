<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@ include file="/WEB-INF/jsp/common/tagLibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><fmt:message key="restaurant.add" /></title>
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


<form:form id="addRestForm" action="${action}" method="post" modelAttribute="editRestaurantDTO">
	<fieldSet> 
		
		<form:label path="name"><fmt:message key="restaurant.name" /></form:label>
		<form:input path="name" cssErrorClass="error" /><form:errors path="name" cssClass="errorMessage" /><br />
		
		<form:label path="type"><fmt:message key="restaurant.type" /></form:label>
		<form:input path="type" cssErrorClass="error" /><form:errors path="type" cssClass="errorMessage" /><br />
		
		<form:label path="address"><fmt:message key="signup.address" /></form:label>
		<form:input path="address" cssErrorClass="error" /><form:errors path="address" cssClass="errorMessage" /><br />	
		
	</fieldSet>
	
	<div class="addRest">
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