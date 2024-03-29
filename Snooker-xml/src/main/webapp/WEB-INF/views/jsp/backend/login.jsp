<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/taglib"%>
<%@ taglib prefix="tho" uri="/thorben"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="author" content="Thorben Dierkes" />
<title>Thorben Dierkes</title>

<header:defaultBackendHeader costumerCSS="backend.css" favicon="thorben.ico"/>

</head>
<body class="backend-login">


<div id="container-login">
	<main id="main">
 	<form method="post" id="loginBackend" action="backendindex">
 		<input type="hidden" name="csrfToken" value="${CSRF_SESSION_TOKEN}">
 		<div class="formbody-login">
 			<h1><tho:out value="global.login"/></h1>
 			<div class="widget_login_wrapper">
		 		<div class="widget_login">
		 			<label for="username" ><tho:out value="global.username"/></label>
		 			<input class="tl_text" type="text" name="username" id="username" value="" placeholder="Benutzername" required autocomplete="off" autocapitalize="off">
		 		</div>
		 		 <div class="widget_login">
		 			<label for="password"><tho:out value="global.password"/></label>
		 			<input class="tl_text" type="password" name="password" id="password" value="" placeholder="Password" required autocomplete="off">
		 		</div>
		 		<div class="submit_container">
		 			<div class="tl_submit_div">
		 				<button class="tl_submit" type="submit" name="login" id="login"><tho:out value="global.login"/></button>
		 			</div>
		 			<div class="tl_submit_div">
		 				<button class="tl_submit" type="submit" name="footer_preview" id="footer_preview"><tho:out value="global.go.to.frontend"/></button>
		 			</div>
		 		</div>
		 		<div class="errormassage"  <c:if test="${isLoginOk eq true}">style="display:none;" </c:if>>
						<p class="errorlogin"> ${errormasage} </p>
		 		</div>
		 	</div>
 		</div>
 	</form>
 	</main>	
</div> 

</body>
</html>