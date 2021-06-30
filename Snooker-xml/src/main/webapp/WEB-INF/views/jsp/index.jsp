<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/taglib"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="author" content="Thorben Dierkes" />
<title>Thorben Dierkes</title>

<header:defaultHaeder costumerCSS="thorben.css" favicon="thorben.ico"/>

</head>
<body>

<jsp:include page="./layout/mainhead.jsp" flush="true"/>


<div id="content" class="jumbotron">
	<div id="contenContainer" class="container">
		<h1>Herzlich Willkommen auf meiner Webseite!</h1>
		<div id="inhalt" class="row">
			<div class="col-md-6">
				<p><strong>Ich begrüßen Sie herzlich und freuen mich über Ihr Interesse.</strong></p>
				<p>
					Auf meiner Seite finden Sie Informationen zu meiner Person. Zudem können Sie sich über meine politischen Ziele und Interessen informieren.
					Weiterhin finden Sie interessante Fakten zur Sportart Snooker.
				</p>
				<p>
					Habe ich Ihr Interesse gewäckt? Oder haben Sie konkrete Fragen zu meiner Person, meinen Zielen oder zur Sportart Snooker? Dann sprechen Sie mich an.
				</p>
				<p>
					Ihr <br><br>
					Thorben Dierkes
				</p>
			</div>
			<div class="col-md-6">
				<img src="resources/core/images/thorben.png" alt="Bild Thorben" style="hight:700px; width:300px;" />
			</div>
		</div>
		<div id="inhalt" class="row" style="text-align:center">
			<div class="col-md-4">
				<h3>Persönliches</h3>
				<p>Auf diesen Seiten finden Sie persönliches über mich.</p>
				<a class="linkButton" href="/thorben-dierkes/personal">#PERSÖNLICHES</a>
			</div>
			<div class="col-md-4">
				<h3>Politik</h3>
				<p>Auf diesen Seiten finden Sie mher über meine politische Agenda.</p>
				<a 	class="linkButton" href="/thorben-dierkes/politik">#GEMEINSAMFÜRWARBURG</a>
			</div>
			<div class="col-md-4">
				<h3>Snooker</h3>
				<p>Auf diesen Seiten finden Sie mehr zum Thema Snooker.</p>
				<a class="linkButton" href="/thorben-dierkes/snooker">#SNOOKER</a>
			</div>
		</div>
	</div>
</div>

<jsp:include page="./layout/mainfooter.jsp" flush="true"/>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>