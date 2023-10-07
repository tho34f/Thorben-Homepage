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
	 		<div class="widget">
	 			<label for="username" ><tho:out value="global.username"/></label>
	 			<input class="tl_text" type="text" name="username" id="username" value="" placeholder="Benutzername" required autocomplete="off" autocapitalize="off">
	 		</div>
	 		 <div class="widget">
	 			<label for="password"><tho:out value="global.password"/></label>
	 			<input class="tl_text" type="password" name="password" id="password" value="" placeholder="Password" required autocomplete="off">
	 		</div>
	 		<div class="submit_container">
	 			<button class="tl_submit" type="submit" name="login" id="login"><tho:out value="global.login"/></button>
	 			<a class="footer_preview">Zum Frontend</a>
	 		</div>
	 		<div class="errormassage"  <c:if test="${isLoginOk eq true}">style="display:none;" </c:if>>
					<p class="errorlogin"> ${errormasage} </p>
	 		</div>
 		</div>
 	</form>
 	</main>	
</div> 

<script src="../resources/core/js/backend.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>