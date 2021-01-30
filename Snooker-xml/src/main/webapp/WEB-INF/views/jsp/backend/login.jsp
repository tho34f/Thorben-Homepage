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
	 			<input class="tl_text" type="text" name="username" id="username" value placeholder="Benutzername" required oldautocomplete="remove" autocomplete="off" autocapitalize="off">
	 		</div>
	 		 <div class="widget">
	 			<label for="password">Password</label>
	 			<input class="tl_text" type="password" name="password" id="password" value placeholder="Password" required oldautocomplete="remove" autocomplete="off">
	 		</div>
	 		<div class="submit_container">
	 			<button class="tl_submit" type="submit" name="login" id="login">Weiter</button>
	 			<a class="footer_preview">Zum Frontend</a>
	 		</div>
 		</div>
 	</form>
 	</main>	
</div> 

<script src="../resources/core/js/clock.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script>
$(".footer_preview").click(function(){
	$(".footer_preview").attr('href', "../");
});

</script>


</body>
</html>