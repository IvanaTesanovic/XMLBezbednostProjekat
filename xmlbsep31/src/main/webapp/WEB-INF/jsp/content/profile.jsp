<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/jsp/common/tagLibs.jsp"%>
<html>
<head>
<title><fmt:message key="home.profile"></fmt:message></title>
</head>
<c:url var="action" value="/profile" />
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


<div id="profile">

	<table>
	<tr><td><fmt:message key="signup.firstname"></fmt:message></td><td><c:out value="${user.firstName }"></c:out></td></tr>
	<tr><td><fmt:message key="signup.lastname"></fmt:message></td><td><c:out value="${user.lastName }"></c:out></td></tr>
	<tr><td><fmt:message key="signup.address"></fmt:message></td><td><c:out value="${user.address }"></c:out></td></tr>
	<tr><td><fmt:message key="login.email"></fmt:message></td><td><c:out value="${user.username }"></c:out></td></tr>
	</table>
	
	<div class="button">
	<a href="<c:url value="/profile/edit"/>" class="button"><fmt:message key="profile.edit.title"></fmt:message></a>
	</div>
	
	<% 
	String message = (String) request.getAttribute("message");
	if (message != null) {
	%>
	&nbsp;<div id="message">${message}</div>
	<% } %>
	
</div>
</body>
</html>