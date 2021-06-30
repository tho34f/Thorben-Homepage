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

<jsp:include page="../layout/mainhead.jsp" flush="true"/>


<div id="content" class="jumbotron">
	<div id="contenContainer" class="container">
		<h1 class="headingPersonal">Geschichte, Regeln und Mehr</h1>
		<div id="inhalt">
				<p><strong>Ich begrüßen Sie herzlich und freuen mich über Ihr Interesse.</strong> <br><br>
				Auf meiner Seite finden Sie Informationen zu meiner Person. Zudem können Sie sich über meine politischen Ziele und Interessen informieren.
				Weiterhin finden Sie interessante Fakten zur Sportart Snooker. <br><br>
				Habe ich Ihr Interesse gewäckt? Oder haben Sie konkrete Fragen zu meiner Person, meinen Zielen oder zur Sportart Snooker? Dann sprechen Sie mich an.<br><br>
				Ihr <br><br>
				Thorben Dierkes
				</p>
		</div>
		<div id="inhalt" class="row" style="text-align:center">
			<div class="col-md-4">
				<h3>Geschichte</h3>
				<p>Auf dieser Seite finden Sie persönliches über die Geschichte von Snooker.</p>
				<a class="linkButton" href="/thorben-dierkes/snookerhistory">#History</a>
			</div>
			<div class="col-md-4">
				<h3>Material</h3>
				<p>Auf dieser Seite finden Sie mehr über die verwendeten Materialien.</p>
				<a 	class="linkButton" href="/thorben-dierkes/snookermaterials">#Materials</a>
			</div>
			<div class="col-md-4">
				<h3>Regeln</h3>
				<p>Auf dieser Seite finden Sie mehr zu den Regeln, die für Snooker gelten.</p>
				<a class="linkButton" href="/thorben-dierkes/snookerrules">#Rules</a>
			</div>
		</div>
	</div>
</div>

<jsp:include page="../layout/mainfooter.jsp" flush="true"/>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>