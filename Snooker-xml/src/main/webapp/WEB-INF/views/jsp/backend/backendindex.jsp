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
<body>

<jsp:include page="../layout/backendhead.jsp"/>


<div id="content" class="jumbotron">
	<div class="container">
		<h1>Herzlich Willkommen im Backend!</h1>
		<div style="text-align:center">
			<div class="col-md-4">
				<h3>Benutzer</h3>
				<p>Erhalten Sie eine Übersicht von allen erstellten Benutzern.</p>
				<a class="linkButton" href="/thorben-dierkes/personal">#USER</a>
			</div>
			<div class="col-md-4">
				<h3>Nachrichten</h3>
				<p>Erhalten Sie eine Übersicht von allen erstellten Nachrichten.</p>
				<a class="linkButton" href="/thorben-dierkes/personal">#News</a>
			</div>
			<div class="col-md-4">
				<h3>Termine</h3>
				<p>Erhalten Sie eine Übersicht von allen erstellten Terminen.</p>
				<a class="linkButton" href="/thorben-dierkes/personal">#Kalender</a>
			</div>
		</div>
	</div>
</div>

<jsp:include page="../layout/backendfooter.jsp"/>
<script src="../resources/core/js/backend.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>