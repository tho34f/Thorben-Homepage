<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="author" content="Thorben Dierkes" />
<title>Thorben Dierkes</title>

<link href="../resources/core/css/thorben.css" rel="stylesheet" />
<link href="../resources/core/css/bootstrap.css" rel="stylesheet" />
<link href="../resources/core/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body class="backend-login">

<%-- <jsp:include page="../layout/backendhead.jsp" flush="true"/> --%>


<div id="container-login">
	<main id="main">
	<h1>Anmelden</h1>
 	<form method="post">
 		<div class="widget">
 			<label for="username" >Benutzername</label>
 			<input type="text" name="username" id="username" value placeholder="Benutzername" required oldautocomplete="remove" autocomplete="off" autocapitalize="off">
 		</div>
 		 <div class="widget">
 			<label for="password">Password</label>
 			<input type="text" name="password" id="password" value placeholder="Password" required oldautocomplete="remove" autocomplete="off">
 		</div>
 		<div style="margin-top: 12px">
 			<button type="submit" name="login" id="login">Weiter</button>
 		</div>
 	</form>
 	</main>	
</div> 


<%-- <jsp:include page="../layout/backendfooter.jsp" flush="true"/> --%>

<script src="../resources/core/js/clock.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>