<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%-- <security:authorize var="loggedIn" access="isAuthenticated()" /> --%>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" id="ng-app" data-ng-app="XmlBsep31">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Skupstina Grada Novog Sada</title>
	<link href="<spring:url value='${version}/css/bootstrap.min.css' />" rel="stylesheet">
</head>
<body>

	<div class="container" id="appContainer">
		
		<div data-ng-view>
			<!-- Pages will be inserted here -->
		</div>
		
		<div style="margin-top:0.5em;">
			<c:choose>
				<c:when test="${loggedIn}">
					<a class="btn btn-danger" id="logout" href="j_spring_security_logout"><spring:message code='logout'/></a>
				</c:when>
			</c:choose>
		</div>
		
		<input type="hidden" value='<c:out value="${pageContext.response.locale}"></c:out>' id="localeCode">
		<input type="hidden" value='<c:out value="${loggedIn}"></c:out>' id="isLogged">
		<input type="hidden" value='<c:out value="${version}"></c:out>' id="version">
		
	</div>
	
	<!-- LIBRARIES -->
	<script type="text/javascript" src="<spring:url value='${version}/js/lib/angular.min.js' />"></script>
	<script type="text/javascript" src="<spring:url value='${version}/js/lib/angular-route.min.js' />"></script>
	<script type="text/javascript" src="<spring:url value='${version}/js/lib/jquery-1.11.1.min.js' />"></script>
	<script type="text/javascript" src="<spring:url value='${version}/js/lib/ui-bootstrap-tpls-1.3.3.min.js' />"></script>
	<script type="text/javascript" src="<spring:url value='${version}/js/lib/iframeResizer.contentWindow.min.js' />"></script>
	
	<!-- APP -->
	<script type="text/javascript" src="<spring:url value='${version}/js/app.js' />"></script>
	
	<!-- CONTROLLERS -->
	<script type="text/javascript" src="<spring:url value='${version}/js/controllers/homeController.js' />"></script>
	<script type="text/javascript" src="<spring:url value='${version}/js/controllers/aktiController.js' />"></script>
	
	<!-- SERVICES -->
	<script type="text/javascript" src="<spring:url value='${version}/js/services/homeService.js' />"></script>
	<script type="text/javascript" src="<spring:url value='${version}/js/services/aktiService.js' />"></script>
	
</body>
</html>