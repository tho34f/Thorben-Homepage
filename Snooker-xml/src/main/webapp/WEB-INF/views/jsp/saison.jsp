<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Thorben Dierkes Snooker</title>

<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<spring:url value="/resources/core/css/bootstrap.css" var="bootstrap" />
<link href="${bootstrap}" rel="stylesheet" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body onLoad="startTime()" >
<header class="page-header">
		<nav class="navbar navbar-inverse navbar-expand-sm">
			<ul class="navbar-nav">
				<li class="nav-item">
					<a class="nav-link" href="/spring4">Startseite</a>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
       				Snooker
      				</a>
      				<div class="dropdown-menu">
      					<a class="dropdown-item" href="/spring4/saisonOverwiev">Überblickt erzeugte Saisons</a>
						<a class="dropdown-item" href="/spring4/saison">Season</a>
					</div>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
       				Persönliches
      				</a>
      				<div class="dropdown-menu">
      					<a class="dropdown-item" href="/spring4/saisonOverwiev">Überblickt erzeugte Saisons</a>
						<a class="dropdown-item" href="/spring4/saison">Season</a>
					</div>
				</li>
			</ul>
			<div>
				<form class="example" id="search" method="post" action="search" value="Suchen" style="margin:auto;margin-top: 8px; margin-left: 300px">
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
	<div class="container">
		<c:if test= "${not empty seasion}">
			<h1> Saison ${seasion.year}</h1>
			<p>Auf dieser Seite werden die Turniere und die aktuellen Spieler der Saison dargestellt.</p>
		</c:if>
		<c:if test= "${empty seasion}">
			<h1>Es ist ein Fehler aufgetreten</h1>
			<p>${errormassage}</p>
		</c:if>
	</div>


	<div class="container">
		<c:if test= "${not empty seasion}">
		<div>
			<h2> Turniere in dieser Saison</h2>
			<form>
				<label for="tournamentsearch" >Turnier:</label>
				<input id="tornamentsearch" name="tornamentsearch" type="text"></input>
				<button class="btn btn-primary" id="btn-search1" name="btn-search" type="button" onclick=findTournament()>Turnier suchen</button>
			</form>
			<table id="tour"class="table table-striped">
				<caption>He-Man and Skeletor facts</caption>
				<tr> 
					<th id="tournamentname"> Name </th> 
					<th id="tournamentweight"> Gewicht </th> 
					<th id="roundnumber"> Rundenzahl </th> 
					<th id="playernumber"> Spielerzahl </th> 
					<th id="simulation"> Simulation </th> 
				</tr>
				<c:forEach items="${seasion.tournament_season}" var="tournament">
					<tr id="${tournament.tournamentname}" > 
						<th id="${tournament.tournamentname}"> ${tournament.tournamentname} </th> 
						<th id="${tournament.gewicht}"> ${tournament.gewicht} </th> 
						<th id="${tournament.roundnumber}"> ${tournament.roundnumber} </th> 
						<th id="${tournament.playernumber"> ${tournament.playernumber} </th> 
						<th id="${tournament.tournamentname}-button"> <a class="btn btn-primary btn-lg" href="simulation?year=${seasion.year}&name=${tournament.tournamentname}&weight=${tournament.gewicht}&number1=${tournament.roundnumber}&number2=${tournament.playernumber}" role="button">Dieses Turnier simulieren</a> </th>
					</tr>
				</c:forEach>
			</table>
		</div>
		<br> 	<br>
		<div >
			<h2> Spieler in dieser Saison</h2>
			<form>
				<label for="playersearch" >Spieler:</label>
				<input id="playersearch" name="playersearch" type="text"></input>
				<button class="btn btn-primary" id="btn-search2" name="btn-search" type="button" onclick=findPlayer()>Spieler suchen</button>
			</form>
			<table id="player" class="table table-striped">
				<caption>He-Man and Skeletor facts</caption>
				<tr> 
					<th id="playerbame"> Name </th> 
					<th id="playerranking"> Worldranking </th> 
					<th id="playerprovisionbalranking"> Provisional Ranking </th> 
					<th id="playerage"> Age </th> 
					<th id="playerbreaks"> Century Breaks </th> 
				</tr>
				<c:forEach items="${seasion.player}" var="play">
					<tr > 
						<th id="${play.firstname} ${play.lastname}"> ${play.firstname} ${play.lastname}</th> 
						<th id="${play.worldRanking}"> ${play.worldRanking} </th> 
						<th id="${play.provisionalRanking}"> ${play.provisionalRanking} </th> 
						<th id="${play.age}"> ${play.age} </th> 
						<th id="${play.centuryBreaks}"> ${play.centuryBreaks} </th> 
					</tr>
				</c:forEach>
			</table>
		</div>
		</c:if>
		<c:if test= "${empty seasion}">
			<h3>Erzeugen Sie eine Saison</h3>
			<form method="post">
				<label for="fname">Jahr der Saison eingeben:</label>
				<input type="text" id="fname" name="season">
				<input type="submit" value="Submit">
			</form> 
		</c:if>
	
	
		<hr>
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
<script>

function findTournament(){
	
	var tournament = $("#tornamentsearch").val();
	document.getElementById(tournament).style.backgroundColor = "#296292";
	
}

function findPlayer(){
	
	var player = $("#playersearch").val();
	document.getElementById(player).style.backgroundColor = "#296292";
	
}


</script>

</body>
</html>