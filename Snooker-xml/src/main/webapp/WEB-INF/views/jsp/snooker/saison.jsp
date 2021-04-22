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
</head>
<body onLoad="startTime()" >

<jsp:include page="../layout/mainhead.jsp" flush="true"/>

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
				<c:forEach items="${seasion.tournamentSeason}" var="tournament">
					<tr id="${tournament.tournamentname}" > 
						<th id="${tournament.tournamentname}"> ${tournament.tournamentname} </th> 
						<th id="${tournament.gewicht}"> ${tournament.gewicht} </th> 
						<th id="${tournament.roundnumber}"> ${tournament.roundnumber} </th> 
						<th id="${tournament.playernumber}"> ${tournament.playernumber} </th> 
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

<jsp:include page="../layout/mainfooter.jsp" flush="true"/>

<script src="resources/core/js/clock.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script>

function findTournament(){
	
	var tournament = $("#tornamentsearch").val();
	alert(tournament);
	document.getElementById(tournament).style.backgroundColor = "#296292";
	
}

function findPlayer(){
	
	var player = $("#playersearch").val();
	document.getElementById(player).style.backgroundColor = "#296292";
	
}


</script>

</body>
</html>