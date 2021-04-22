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
<body onLoad="startTime()" >

<jsp:include page="../layout/mainhead.jsp" flush="true"/>


<div id="content" class="jumbotron">
	<div id="contenContainer" class="container">
		<h1 class="headingPersonal">Meine politsichen Ziele</h1>
		<h2 class="headingPersonal">Links zu weiteren SPD-Seiten</h2>
		<div id="inhalt" class="row">
			<div class="col-md-3">
				<a class="linkButton" href="https://spd-warburg.de/">SPD Warburg</a>
			</div>
			<div class="col-md-3">
				<a 	class="linkButton" href="https://spd-hoexter.de/">Spd Höxter</a>
			</div>
			<div class="col-md-3">
				<a class="linkButton" href="https://www.nrwspd.de/">SPD NRW</a>
			</div>
			<div class="col-md-3">
				<a class="linkButton" href="https://www.spd.de/">Bundes SPD</a>
			</div>
		</div>
	</div>
</div>

<jsp:include page="../layout/mainfooter.jsp" flush="true"/>

<script src="resources/core/js/clock.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>