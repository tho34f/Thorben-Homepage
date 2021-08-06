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
		<h1 class="headingPersonal">Geschichte</h1>
		<div id="accordion">
    		<div class="card">
      			<div class="card-header">
       				<a class="card-link" data-toggle="collapse" href="#collapseOne">
          				Ursprung test test
        			</a>
      			</div>
     			<div id="collapseOne" class="collapse show" data-parent="#accordion">
        			<div class="card-body">
         				<p> 
	         				Snooker wurde im Jahr 1875 aus der zu dieser Zeit beliebten Billiardvariante <em>Black Pool</em> vom zu dieser Zeit in Jabalpur stationierten britischen
							Oberleutnant Neville Francis Fitzgerald Chamberlain entwickelt. Chamberlain f�gte zu den im <em>Black Pool</em> verwendeten f�nfzehn Roten und einer Schwarzen
							weiterer farbige B�lle  hinzu, da das <em>Black Pool</em> langweilig geworden war und zus�tzliche B�lle das Spiel wieder interessanter machten.
						</p>
						<p>
							Eine weitere popul�re Billiardvariante war das <em>English-Billiards</em>, welches im 18. Jahrhundert in England entstand. 1885 machte der englischer <em>English-Billiards</em>-Spieler
							John Roberts Jr. Bekanntschaft mit Chamberlain und dem Snookerspiel. Roberts gefiehl das Spiel. Daher f�hrte er es nach seiner R�ckkehr in Gro�britannien ein. Wegen seiner
							Geselligkeit erfreute sich das Spiel unter der britischen Bev�lkerung schnell einer gewissen Beliebtheit.
						</p>
        			</div>
      			</div>
    		</div>
    		<div class="card">
      			<div class="card-header">
       				<a class="card-link" data-toggle="collapse" href="#collapseTwo">
          				Der Weg zur Professionalisierung
        			</a>
      			</div>
     			<div id="collapseTwo" class="collapse" data-parent="#accordion">
        			<div class="card-body">
         				<p>
							Wegen der zunehmenden Industralisierung hatten die Menschen in England mehr Zeit f�r Freizeitaktivit�ten. Dies war der Grund daf�r, warum die Popularit�t von Snooker
							weiterhin schnell anstieg. In den 1910er-Jahren wurden die erste h�here Breaks gespielt und ab 1916 wurde die erste Meisterschaft im Snooker ausgetragen, aus der sp�ter die English Amateur
							Championship wurde. <br/>
							Die bis dahin uneinheitlichen Snookerregeln wurden im Jahr 1916 durch den Billiards Association and Control Council erstmals zusammengefasst. Dem enstandenden Regelwerk wurden weitere
							Regeln, wie z.B. die <em>Re-spotted black </em> hinzugef�gt.
						</p>
        			</div>
      			</div>
    		</div>
    		<div class="card">
      			<div class="card-header">
       				<a class="card-link" data-toggle="collapse" href="#collapseThree">
          				Globalisierung des Sports
        			</a>
      			</div>
     			<div id="collapseThree" class="collapse" data-parent="#accordion">
        			<div class="card-body">
						<p>
							Mit dem Start des neuen Jahrzehnts l�ste der Schotte Stephen Hendry im Jahr 1990 Davis als dominierenden Spieler ab. Zwischen 1990 und 1999 gewann Hendry insgesamt sieben Weltmeisterschaften.
							Dar�ber hinaus konnte er zahlreiche und teilweise bis heute bestehende Rekorde aufstellten. <br/>
							Weiterhin ist es Hendry zu verdanken, dass sich die bis dahin eher auf Sicherheit bedachte Spielweise zu einer weit offensiveren Spielart weiter entwickelte. Der heute 
							standardisierte <em>lange rote Einsteiger</em> sowie die Nutzung der Mitteltaschen als Lochm�glichkeit aus viel spitzeren Winkeln gehen auf seine neuere Spielweise 
							zur�ck.
						</p>
        			</div>
      			</div>
    		</div>
		
		</div>

		<div class="buttonToText">
			<a class="linkButton" href="/thorben-dierkes/overview">Zur�ck zur �bersicht</a>
		</div>
	</div>
</div>

<jsp:include page="../layout/mainfooter.jsp" flush="true"/>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>