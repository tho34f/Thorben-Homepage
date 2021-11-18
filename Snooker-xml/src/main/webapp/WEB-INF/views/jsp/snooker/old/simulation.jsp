<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/taglib"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="author" content="Thorben Dierkes" />
<title>Thorben Dierkes</title>

<header:defaultHaeder costumerCSS="resources/core/css/thorben.css" favicon="resources/core/images/thorben.ico"/>

</head>
<body >

<jsp:include page="../../layout/mainhead.jsp" flush="true"/>


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

<jsp:include page="../../layout/mainfooter.jsp" flush="true"/>

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