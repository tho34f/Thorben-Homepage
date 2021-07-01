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
		<h1 class="headingPersonal">Spielablauf</h1>
		<div id="accordion">
    		<div class="card">
      			<div class="card-header">
       				<a class="card-link" data-toggle="collapse" href="#collapseOne">
          				Ziel des Spiels
        			</a>
      			</div>
     			<div id="collapseOne" class="collapse show" data-parent="#accordion">
        			<div class="card-body">
						<p>
							Bei Snooker ist es das Ziel, so viele Punkte zu erreichen, dass der Gegner nicht mehr Punkte erzielen kann. Punkte können erzielt werden, in dem mindestens
							einer der Kugeln in eine der sechs Taschen des Tisches gelocht werden kann. Hierfür wird mit dem Queue der weiße Spielball so gestoßen, dass dieser mindestens 
							eine weitere Kugel berührt.
						</p>
        			</div>
      			</div>
      			<div class="card-header">
       				<a class="card-link" data-toggle="collapse" href="#collapseTwo">
          				Spielablauf
        			</a>
      			</div>
     			<div id="collapseTwo" class="collapse" data-parent="#accordion">
        			<div class="card-body">
						<p>
							Der Spieler, der am Ende eines Frames die meisten Punkte sammeln konnte, gewinnt diesen. Ein Spiel besteht oft aus mehreren Frames. Derjenige von den beiden 
							Spielern, der die meisten Frames gewinnt, hat das Spiel gewonnen.
						</p>
        			</div>
      			</div>
      			<div class="card-header">
       				<a class="card-link" data-toggle="collapse" href="#collapseThree">
          				Tatik Test
        			</a>
      			</div>
     			<div id="collapseThree" class="collapse" data-parent="#accordion">
        			<div class="card-body">
						<p>
							Taktische Aspekte spielen beim Snooker ebenfalls eine entscheidene Rolle. Wer seinem Gegner taktisch überlegen ist, kann oft einen Frame für sich entscheiden.
						</p>
        			</div>
      			</div>
    		</div>
    	</div>
		<div class="buttonToText">
			<a class="linkButton" href="/thorben-dierkes/overview">Zurück zur Übersicht</a>
		</div>
	</div>
</div>

<jsp:include page="../layout/mainfooter.jsp" flush="true"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>