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
		<h1>Herzlich Willkommen auf meiner Webseite!</h1>
		<h2 class="headingPersonal">Links zu interessanten Seiten</h2>
		<div id="inhalt" class="row">
			<div class="col-md-3">
				<a class="linkButton" href="https://wst.tv/">World Snooker Tour</a>
			</div>
			<div class="col-md-3">
				<a 	class="linkButton" href="https://www.highbreak.de/">Snooker Club Hofgeismar</a>
			</div>
			<div class="col-md-3">
				<a class="linkButton" href="https://www.eurosport.de/snooker/">Eurosport</a>
			</div>
			<div class="col-md-3">
				<a class="linkButton" href="https://mcbillard.de/">McBillard Online-Shop</a>
			</div>
		</div>
	</div>
</div>

<jsp:include page="../layout/mainfooter.jsp" flush="true"/>

<script src="resources/core/js/clock.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>