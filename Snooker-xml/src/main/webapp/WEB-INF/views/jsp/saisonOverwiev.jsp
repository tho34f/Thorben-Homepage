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
	<div class="container">
		<h1>Saison Überblick</h1>
		<p>
		Hier erhalten Sie einen Überblick über die erzeugten Saisons.
		</p>
		<form method="post">
			<label for="fname">Jahr der Saison eingeben:</label>
			<input type="text" id="fname" name="season">
			<button type="submit">Los!</button>
		</form> 
		<br>
		<table id="seasionOverview" style="border:1 px solid black; border-collapse:collapse">
			<caption>He-Man and Skeletor facts</caption>
			<tr> 
				<th id="erzeugen"> Erzeugte Saison </th> 
				<th id="anzeigen"> Turniere und Spieler anzeigen </th> 
				</tr>
			<c:forEach items="${seasions}" var="seasion">
				<tr> 
					<td id="${seasion.year}"> ${seasion.year} </td> 
					<td id="${seasion.year}_Link"> <a class="btn btn-primary btn-lg" href="saison?id=${seasion.year}" role="button">Turniere und Spieler anzeigen</a> </td> 
				</tr>
			</c:forEach>
		</table>
	</div>


	<hr>
</div>

<jsp:include page="./layout/mainfooter.jsp" flush="true"/>

<script src="resources/core/js/clock.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>