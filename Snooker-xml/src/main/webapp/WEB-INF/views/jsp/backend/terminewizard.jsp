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

<header>
	<div class="headerPanel">
		<div style="margin-top: 0px;height: 75px;border-right: 1px solid #bbb;float: left;width: 270px;">
			<div class="far fa-calendar-alt" id="objectIcon"></div>
			<h4 id="objectHeaderWizard">Termine</h4>
		</div>
		<div>
			<button id="saveObject" type="button" title="Speichern">
				<em>Speichern</em>
			</button>
		</div>
	</div>
</header>

<div id="content" class="jumbotron">
	<div class="container">
		<h1>Ergebniss der Suche</h1>
		<p> Login-Prozess Erfolgreich </p>
	</div>
</div>

<jsp:include page="../layout/backendfooter.jsp"/>
<script src="../resources/core/js/backend.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>