<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/taglib"%>
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
 		<div class="formbody-login">
 			<h1>Anmelden</h1>
	 		<div class="widget">
	 			<label for="username" >Benutzername</label>
	 			<input class="tl_text" type="text" name="username" id="username" value="" placeholder="Benutzername" required autocomplete="off" autocapitalize="off">
	 		</div>
	 		 <div class="widget">
	 			<label for="password">Password</label>
	 			<input class="tl_text" type="password" name="password" id="password" value="" placeholder="Password" required autocomplete="off">
	 		</div>
	 		<div class="submit_container">
	 			<button class="tl_submit" type="submit" name="login" id="login">Weiter</button>
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