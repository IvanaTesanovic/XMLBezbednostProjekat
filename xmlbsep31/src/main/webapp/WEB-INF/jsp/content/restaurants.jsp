<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/jsp/common/tagLibs.jsp"%>
<html>
<head>
<title><fmt:message key="restaurants.title"></fmt:message></title>
<style type="text/css">
.sc {
    color: #82b440;
	background-color: #888888;
  	border: 2px solid;
	border-color: #82b440;
  	border-radius: 15px;
  	box-shadow: 0 0.1em #27496d;
}
</style>
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

<h3><fmt:message key="restaurant.details"/></h3>
<div id="restaurant">
	<table>
	<tr><td><fmt:message key="restaurant.name"></fmt:message></td><td><c:out value="${restaurant.name }"></c:out></td></tr>
	<tr><td><fmt:message key="restaurant.type"></fmt:message></td><td><c:out value="${restaurant.type }"></c:out></td></tr>
	<tr><td><fmt:message key="signup.address"></fmt:message></td><td><c:out value="${restaurant.address }"></c:out></td></tr>
	</table>
<h3><fmt:message key="home.managers"/></h3>
<table><tr><th><fmt:message key="signup.firstname"/></th><th><fmt:message key="signup.lastname"/></th><th></th></tr>
	<c:forEach items="${managers}" var="man">
	<tr><td><c:out value="${man.firstName }"/></td><td><c:out value="${man.lastName }"/></td>
	</c:forEach>
</table>

	<% 
	String message = (String) request.getAttribute("message");
	if (message != null) {
	%>
	&nbsp;<div id="message">${message}</div>
	<% } %>
	
	
	<div id="seating">
	<h3><fmt:message key="restaurant.seating"></fmt:message></h3>
	
	<%--<sec:authorize access="hasRole('ROLE_MANAGER')"> --%>
	<% String arerest = (String)request.getAttribute("areRest");
		if(arerest.equals("nope")) { 
	%>
	<table id="scForm">
	
	<tr><td><input type="button" class="sc" id="11" value="11"  /></td><td><input type="button" class="sc" id="12" value="12"  /></td>
	<td><input type="button" class="sc" id="13" value="13"  /></td><td><input type="button" class="sc" id="14" value="14"  /></td>
	<td><input type="button" class="sc" id="15" value="15"  /></td><td><input type="button" class="sc" id="16" value="16"  /></td></tr>
	
	<tr><td><input type="button" class="sc" id="21" value="21"  /></td><td><input type="button" class="sc" id="22" value="22"  /></td>
	<td><input type="button" class="sc" id="23" value="23"  /></td><td><input type="button" class="sc" id="24" value="24"  /></td>
	<td><input type="button" class="sc" id="25" value="25"  /></td><td><input type="button" class="sc" id="26" value="26"  /></td></tr>
	
	<tr><td><input type="button" class="sc" id="31" value="31"  /></td><td><input type="button" class="sc" id="32" value="32"  /></td>
	<td><input type="button" class="sc" id="33" value="33"  /></td><td><input type="button" class="sc" id="34" value="34"  /></td>
	<td><input type="button" class="sc" id="35" value="35"  /></td><td><input type="button" class="sc" id="36" value="36"  /></td></tr>
	
	<tr><td><input type="button" class="sc" id="41" value="41"  /></td><td><input type="button" class="sc" id="42" value="42"  /></td>
	<td><input type="button" class="sc" id="43" value="43"  /></td><td><input type="button" class="sc" id="44" value="44"  /></td>
	<td><input type="button" class="sc" id="45" value="45"  /></td><td><input type="button" class="sc" id="46" value="46"  /></td></tr>
	
	<tr><td><input type="button" class="sc" id="51" value="51"  /></td><td><input type="button" class="sc" id="52" value="52"  /></td>
	<td><input type="button" class="sc" id="53" value="53"  /></td><td><input type="button" class="sc" id="54" value="54"  /></td>
	<td><input type="button" class="sc" id="55" value="55"  /></td><td><input type="button" class="sc" id="56" value="56"  /></td></tr>
	
	</table>
	
	<div class="signup">
		<button type="submit" id="saveAjax" name="save" class="button">
			<fmt:message key="action.save" />
		</button>
	</div>
	<% } else {%>
	
	<table>
	<c:forEach items="${restTables }" var="rt">
	<c:set var="y" value="${rt.y }"></c:set>
	<c:set var="rest" value="${rt.restaurant }"></c:set>
	<%
		String y = (String)pageContext.getAttribute("y");
		Object rest = pageContext.getAttribute("rest");
		if(y.equals("1")) {
	%>
		<tr>
	<% }  if(rest != null) {
	%>
		<td><input type="button" disabled="disabled" style="color: #fff;
  															background-color: #6496c8;
  															border: none;
  															border-radius: 15px;
  															box-shadow: 0 0.1em #27496d"
  			class="rt" id="${rt.x}${rt.y}" value="${rt.x}${rt.y}"/></td>
	<% } else { %>
		<td><input type="button" disabled="disabled" class="sc" id="${rt.x}${rt.y}" value="${rt.x}${rt.y}"/></td>
	<%}
		if(y.equals("6")) { %>
		</tr>
		<% } %>
	
	</c:forEach>
	</table>
	<% } %>
	
	<%-- </sec:authorize> --%>

	</div>
	
	
	
	<sec:authorize access="hasRole('ROLE_ADMIN')"><div class="button">
	<a href="<c:url value="/restaurants/${restaurant.id }/edit"/>" class="button"><fmt:message key="restaurant.edit"></fmt:message></a>
	</div></sec:authorize>
	
	<%--<sec:authorize access="hasRole('ROLE_MANAGER')">--%>
	<% Object menu = request.getAttribute("menu");
		if(menu == null) { 
	%>
	<div class="button">
	<a href="<c:url value="/restaurants/${restaurant.id }/addMenu"/>" class="button"><fmt:message key="restaurant.addmenu"></fmt:message></a>
	</div>
	<%} else {%>
	<div class="button">
	<a href="<c:url value="/restaurants/${restaurant.id }/editMenu"/>" class="button"><fmt:message key="restaurant.menu"></fmt:message></a>
	</div>
	<% } %>
	<%--</sec:authorize>--%>
	
	<sec:authorize access="hasRole('ROLE_ADMIN')"><div class="button">
	<a href="<c:url value="/restaurants/${restaurant.id }/addRestManager"/>" class="button"><fmt:message key="restaurant.addmanager"></fmt:message></a>
	</div></sec:authorize>
	
	<%--<sec:authorize access="hasRole('ROLE_USER')">--%>
	<div class="button">
	<a href="<c:url value="/restaurants/${restaurant.id }/reserve1"/>" class="button"><fmt:message key="restaurant.reserve"></fmt:message></a>
	</div>
	<%--</sec:authorize>--%>
	
</div>
<script>
$(document).ready(function() {
	
	$("input").click(function() {
		var id = this.id;
		
		if(this.className == "sc") { //if it wasn't a table
			this.className = "nosc"; //it is a table
			$("#" + id).css({
				'color': '#fff',
  				'background-color': '#6496c8',
  				'border': 'none',
  				'border-radius': '15px',
  				'box-shadow': '0 0.1em #27496d'
			});
		}
		else if(this.className == "nosc") { //if it was a table
			this.className = "sc"; //it's not a table
			$("#" + id).css({
				'color': '#82b440',
  				'background-color': '#888888',
  				'border': '2px solid #82b440',
  				'border-radius': '15px',
  				'box-shadow': '0 0.1em #27496d'
			});
		}
    });
    	
    $("#saveAjax").click(function() {
		var tables = "";
		
		$("#scForm input[type=button]").each(function() {
		if(this.className == "nosc") {
            	tables += this.id + "+";
            }
		});
		
    	$.ajax({
			type: "POST",
			url: "http://localhost:8080/isaproject/restaurants/${restaurant.id}",
			data: {
				'tables':tables
			},
			success: handleData ,
			error: function(msg) {
				alert("error");
			}
		});
    });
    
    function handleData(data) {
        window.location.replace("http://localhost:8080/isaproject/restaurants/${restaurant.id}");
    }
    
});
</script>
</body>
</html>