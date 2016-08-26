<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ulogujte se</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	      <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	      <!--[if lt IE 9]>
	         <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	         <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
	<![endif]-->
	
<style>
.container {
	position: absolute;
   	top: 50%;
    left: 50%;
    width:30em;
    height:18em;
    margin-top: -16em;
    margin-left: -11em
}
#errors {
	padding: 2px;
	border: 1px solid transparent;
	border-radius: 4px;
	background-color:#ff3333;
	color:white;
	margin-top:0.5em;
	width:18em;
	margin-left: 0.1em
}
#submit_button {
	margin-top: -1em;
	margin-left: 6em;
	margin-bottom: 0.5em
}
#usernameDiv {
	margin-top:0.5em;
	margin-left: 1.5em;
	color: red;
	font-weight: bold
}
</style>
</head>
<body id="login_body">
<br>
<br>
	<div class="container">
		<div class="loginFormWrapper">
			<form name="loginForm" id="loginForm" method="post" action="j_spring_security_check" class="css-form data-ng-dirty data-ng-invalid data-ng-invalid-required mediators-search-form" onsubmit="return validateForm()">
				<div>
					<fieldset>
						<!-- Username -->       
			        	<div class="row rvr_bottom_padding">
					        <div class="form-group">
					        	<label class="registerLabel control-label col4" for="j_username">Korisnicko ime<span class="required">*</span></label>
						        <div class="col6">
						            <input id="j_username" class="form-control" style="width:20em" type="text" name="j_username" data-ng-model="user.username" data-ng-maxlength="50">
						        </div>
							</div>
					    </div>
					    <!-- Password -->       
			        	<div class="row rvr_bottom_padding">
					        <div class="form-group">
					        	<label class="registerLabel control-label col4" for="j_password">Lozinka<span class="required">*</span></label>
						        <div class="col6">
						            <input id="j_password" class="form-control" style="width:20em" type="password" name="j_password" data-ng-model="user.password" data-ng-maxlength="30">
						        </div>
							</div>
					    </div>
					</fieldset>
					<br>
					<div>
						<button id="submit_button" class="btn btn-primary" type="submit" name="submit">
							Uloguj se
						</button>
					</div>
				</div>
			</form>
		</div>
		<div id='errorDiv'>
			<c:if test="${param.error == '1' }">
				<div id="errors" >
					Nevalidno korisnicko ime i/ili lozinka!
				</div>
			</c:if>
		</div>
	</div>
	
	
<script type="text/javascript">
		function validateForm() {

			var ret = true;
			var j_username = document.forms["loginForm"]["j_username"].value;
			var j_password = document.forms["loginForm"]["j_password"].value;

			if ($("#usernameDiv")) {
				$("#usernameDiv").remove();
				if(j_username == null || j_username == "")
					document.getElementById("j_username").style.borderColor = "red";
				else
					document.getElementById("j_username").style.borderColor = "#CCCCCC";
				if(j_password == null || j_password == "")
					document.getElementById("j_password").style.borderColor = "red";
				else
					document.getElementById("j_password").style.borderColor = "#CCCCCC";
			}

			if (j_username == null || j_username == "" || j_password == null || j_password == "") {
				$("#errorDiv").append("<p id='usernameDiv'>Polja oznacena sa * su obavezna!</p>");
				if(j_username == null || j_username == "")
					document.getElementById("j_username").style.borderColor = "red";
				if(j_password == null || j_password == "")
					document.getElementById("j_password").style.borderColor = "red";
				ret = false;
			}

			return ret;
		}
	</script>

	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/angular.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/iframeResizer.contentWindow.min.js"></script> 
</body>
</html>