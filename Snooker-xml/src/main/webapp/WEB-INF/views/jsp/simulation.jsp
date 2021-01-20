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
	<div class="container" id="simulation">
		<c:if test= "${not empty seasion}">
			<h1> Simulation der ${tournament.tournamentname} aus der Saison ${seasion.year}</h1>
			<p>Auf dieser Seite soll das Turnier  aus der Saison ${seasion.year} simuliert werden.</p>
			<div id="simulationButton">
				<button class="btn btn-primary" id="btn-simmulation" name="btn-simmulation" type="button"  onclick=simulation()>Turnier Simulieren</button>
			</div>
		</c:if>
		<c:if test= "${empty seasion}">
			<h1>Es ist ein Fehler aufgetreten</h1>
			<p>${errormassage}</p>
		</c:if>
	</div>

	<div class="container">
		
		<div id="tournamentData" style="display:none;">
			<h2>Daten des Turniers</h2>
			<p id="tournamentname">${tournament.tournamentname} <p> 
			<p id="gewicht">${tournament.gewicht}  <p> 
			<p id="roundnumber">${tournament.roundnumber} <p> 
			<p id="playernumber">${tournament.playernumber} <p>  
		</div>
	
		<div id="showWinner" style="display:none;">
			<h2> Gewinner</h2>
			<p>Gewinner des Turnieres ${tournament.tournamentname} ist: ${gewinner.firstname} ${gewinner.lastname}</p>
		</div>
		<br>
		<div >
			<h2> Teilnehmende Spieler</h2>
			<form id="formAddPLayer">
				<label for="addPlayer" >Spieler:</label>
				<input id="addPlayer" name="addPlayer" type="text"></input>
				<button class="btn btn-primary" id="btn-add" name="btn-add" type="button" onclick=addesPlayer()>Spieler hinzufügen</button>
			</form>
			<table id="playerTable" class="table table-striped">
				<caption>He-Man and Skeletor facts</caption>
				<tr> <th id="firstname"> Vorname </th> <th id="lastname"> Nachname </th> </tr>
			</table>
		</div>
	
	
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

var participationPlayer = [];

window.onload = function(){
	$("#simulationButton").hide();
}

var showWinner = $("#showWinner");

function addesPlayer(){
	
	var player = $("#addPlayer").val();
	
	var lengthBefore = participationPlayer.length;
	for(i = 0; i < lengthBefore; i++){
		if(player == participationPlayer[i]){
			alert("Es ist ein Fehler aufgetreten");
			return;
		}
		
	}
	
	participationPlayer[lengthBefore] = player;
	addRow(player);
	var lengthAfter = participationPlayer.length;
	var playerNumber = $("#playernumber").text();
	if(playerNumber == lengthAfter){
		$("#simulationButton").show();
		$("#formAddPLayer").hide();
	}
	
	
}

function addRow(player){
	var playerTableRow = document.createElement("tr");
	playerTableRow.setAttribute("id", player);
	document.getElementById("playerTable").appendChild(playerTableRow);
	addPlayer2Row(player);
}
	
function addPlayer2Row(player){
	var firstNamePlayer = document.createElement("td");
	var lastNamePlayer = document.createElement("td");
	
	var namePlayer = player.split(' ');
	var firstName = namePlayer[0];
	var lastName;
	if(namePlayer[1] === undefined){
		lastName = "XXX"
	} else {
		lastName = namePlayer[1];
	}
	
	var textFistName = document.createTextNode(firstName);
	var textLastFistName = document.createTextNode(lastName);
	firstNamePlayer.appendChild(textFistName);
	lastNamePlayer.appendChild(textLastFistName);
	document.getElementById(player).appendChild(firstNamePlayer);
	document.getElementById(player).appendChild(lastNamePlayer);
	$("#addPlayer").val('');
}

function simulation(){
	
	var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	     $("#showWinner").show();
	    }
	  };
	  
	  xhttp.open("POST", "simulation?participationPlayer=" + participationPlayer, true);
	  xhttp.send();
	
}

</script>

</body>
</html>