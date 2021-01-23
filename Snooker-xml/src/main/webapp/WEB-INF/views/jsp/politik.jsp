<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="author" content="Thorben Dierkes" />
<title>Thorben Dierkes</title>

<link href="resources/core/css/thorben.css" rel="stylesheet" />
<link href="resources/core/css/bootstrap.css" rel="stylesheet" />
<link href="resources/core/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body onLoad="startTime()" >

<jsp:include page="./layout/mainhead.jsp" flush="true"/>


<div id="content" class="jumbotron">
	<div id="contenContainer" class="container">
		<h1>Herzlich Willkommen auf meiner Webseite!</h1>
		<div id="inhalt" class="row">
			<div class="col-md-6"><p>Ich begrüßen Sie herzlich und freuen mich über Ihr Interesse</p></div>
			<div class="col-md-6"><img src="resources/core/images/thorben.png" alt="Bild Thorben" style="hight:400px; width:200px;" /></div>
		</div>
		<div id="inhalt" class="row" style="text-align:center">
			<div class="col-md-4">
				<h4>Persönliches</h4>
				<p>Auf diesen Seiten finden Sie persönliches über mich.</p>
				<a class="linkButton" href="#">#PERSÖNLICHES</a>
			</div>
			<div class="col-md-4">
				<h4>Politik</h4>
				<p>Auf diesen Seiten finden Sie mher über meine politische Agenda.</p>
				<a 	class="linkButton" href="#">#GEMEINSAMFÜRWARBURG</a>
			</div>
			<div class="col-md-4">
				<h4>Snooker</h4>
				<p>Auf diesen Seiten finden Sie mehr zum Thema Snooker.</p>
				<a class="linkButton" href="/spring4/saisonOverwiev">#SNOOKER</a>
			</div>
		</div>
	</div>
</div>

<jsp:include page="./layout/mainfooter.jsp" flush="true"/>

<script src="resources/core/js/clock.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>