<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Thorben Dierkes Snooker</title>

<link href="resources/core/css/thorben.css" rel="stylesheet" />
<link href="resources/core/css/bootstrap.css" rel="stylesheet" />
<link href="resources/core/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body onLoad="startTime()" >
<header id="page-header" class="page-header">
		<nav class="navbar navbar-inverse navbar-expand-sm">
			<ul class="navbar-nav">
				<li class="nav-item">
					<a class="nav-link" href="/spring4">Startseite</a>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="/spring4/snooker" id="navbardrop" data-toggle="dropdown"> Snooker </a>
      				<div class="dropdown-menu">
      					<a class="dropdown-item" href="/spring4/saisonOverwiev">Überblickt erzeugte Saisons</a>
						<a class="dropdown-item" href="/spring4/saison">Season</a>
					</div>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="/spring4/personal" id="navbardrop" data-toggle="dropdown"> Persönliches </a>
      				<div class="dropdown-menu">
      					<a class="dropdown-item" href="/spring4/saisonOverwiev">Überblickt erzeugte Saisons</a>
						<a class="dropdown-item" href="/spring4/saison">Season</a>
					</div>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="/spring4/politik" id="navbardrop" data-toggle="dropdown"> Politik </a>
      				<div class="dropdown-menu">
      					<a class="dropdown-item" href="/spring4/saisonOverwiev">Überblickt erzeugte Saisons</a>
						<a class="dropdown-item" href="/spring4/saison">Season</a>
					</div>
				</li>
			</ul>
			<div>
				<form class="example" id="search" method="post" action="search" value="Suchen">
					<input type="text" id="suche" placeholder="Suche..." name="suchen">
					<button type="submit">Los!</button>
				</form>
				<span id="clock"></span>
			</div>
		</nav>
		<div style="text-align:center">
			<img src="resources/core/images/logo_large.png" alt="Bild Thorben" style="hight:50px;width:500px;" />
			<h2>#WIRWARBURGER</h2>
		</div>
</header>


<div id="content" class="jumbotron">
	<div id="contenContainer" class="container">
		<h1>Herzlich Willkommen auf meiner Webseite!</h1>
		<div id="inhalt" class="row">
			<div class="col-md-6">
				<p>Ich begrüßen Sie herzlich und freuen mich über Ihr Interesse. <br>
				
				Auf meiner Seite finden Sie interessante Fakten zur Sportart Snooker.
				</p>
			</div>
			<div class="col-md-6">
				<img src="resources/core/images/thorben.png" alt="Bild Thorben" style="hight:700px; width:350px;" />
			</div>
		</div>
		<div id="inhalt" class="row" style="text-align:center">
			<div class="col-md-4">
				<h3>Persönliches</h3>
				<p>Auf diesen Seiten finden Sie persönliches über mich.</p>
				<a class="linkButton" href="/spring4/personal">#PERSÖNLICHES</a>
			</div>
			<div class="col-md-4">
				<h3>Politik</h3>
				<p>Auf diesen Seiten finden Sie mher über meine politische Agenda.</p>
				<a 	class="linkButton" href="/spring4/politik">#GEMEINSAMFÜRWARBURG</a>
			</div>
			<div class="col-md-4">
				<h3>Snooker</h3>
				<p>Auf diesen Seiten finden Sie mehr zum Thema Snooker.</p>
				<a class="linkButton" href="/spring4/snooker">#SNOOKER</a>
			</div>
		</div>
	</div>
</div>

<footer id="page-footer">
	<div class="scial_media" style="text-align:center">
		<a target="_blank" href="https://www.xing.com/profile/Thorben_Dierkes/cv" class="fa fa-3x fa-xing"></a>    
		<a target="_blank" href="https://www.facebook.com/thorben.dierkes" class="fa fa-3x fa-facebook-square"></a>    
		<a target="_blank" href="https://www.instagram.com/tho34f/?hl=de" class="fa fa-3x fa-instagram"></a>
	</div>
	<div class="contact" style="text-align:center">
		<p>Aktuelles Datum: ${formatDate}</p>
		<p>&copy; Thorben Dierkes 2015</p>
	</div>
</footer>

<script src="resources/core/js/clock.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>