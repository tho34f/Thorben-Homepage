<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/taglib"%>
<%@ taglib prefix="tho" uri="/thorben"%>
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
		<h1><tho:out value="global.name.owner.abou"/></h1>
		<div id="inhalt" class="row">
			<div class="col-md-6">
				<p>Ich bin ein waschechter Warburger mit vielen, aber nicht allen Eigenarten, die man diesem selbstbewussten Völkchen nachsagt. 
				Am 18.10.1994 wurde ich in Warburg geboren. Aufgewachsen bin ich ebenfalls dort und bin bis heute verwurzelt geblieben.
				</p>
			</div>
			<div class="col-md-6">
				<img src="resources/core/images/thorben.png" alt="Bild Thorben" style="hight:700px; width:300px;" />
			</div>
		</div>
		<div id="inhalt" class="row">
			<div class="col-md-6">
				<h2 class="headingPersonal"><em class="fas fa-briefcase"></em><tho:out value="global.personal.professional.background"/></h2>
				<ul>
					<li><p>Seit August 2018: Ausbildung zum Fachinformatiker; Fachrichtung: Softwareentwicklung</p></li>
					<li><p>Oktober 2017 - August 2019: Wissenschaftliche Hilfskraft an der Universität Paderborn</p></li>
					<li><p>Oktober 2016 - Juli 2017: Studentische Hilfskraft an der Universität Paderborn</p></li>
					<li><p>Oktober 2014 - August 2016: Nachhilfelehrer bei der Schülerhilfe Warburg</p></li>
				</ul>
			</div>
			<div class="col-md-6">
				<h2 class="headingPersonal"><em class="fas fa-graduation-cap"></em> Schulische/Universitälle Ausbildung</h2>
				<ul>
					<li><p>April 2017 - November 2019: Master-Studium Mathematik an der Universität Paderborn; Schwerpunkt: Optimierung und Funktionalanalysis, 
					insbesondere Differentialrechnung in lokal konvexen, topologischen Räumen und Banachräumen;</p></li>
					<li><p>Oktober 2013 - März 2017: Bachelor-Studium der Mathematik an der Universität Paderborn; Schwerpunkte: Grundlagen der Physik, Optimierung, Analysis</p></li>
					<li><p>Juni 2013: Abitur am Gymnasium Marianum</p></li>
					<li><p>Mai 2012 - Mai 2013: Propädeutikum Physik an der Young Business School Heidelberg</p></li>
				</ul>
			</div>
		</div>
		<div id="inhalt" class="row">
			<div class="col-md-6">
				<h2 class="headingPersonal"><em class="fas fa-handshake"></em> Sonstiges Engagement</h2>
				<ul>
					<li><p>Seit November 2019: sachkundiger Bürger im Bezirksausschuss Warburg</p></li>
					<li><p>September 2017 - September 2019: Mitglied in Gremien der Universität Paderborn: Studienbeirat Mathematik,
					Institutsvorstand Mathematik, Promotionsausschuss Mathematik, Qualitätsverbesserungskommission
					(seit 09/2018)</p></li>
					<li><p>2009 - 2012: Teamer bei der Evangelischen Kirchengemeinde Waburg-Herlinghausen</p></li>
				</ul>
			</div>
			<div class="col-md-6">
				<h2 class="headingPersonal "><em class="fas fa-users"></em> Mitgliedschaften</h2>
				<ul>
					<li><p>Seit 2005: DCCV e.V.</p></li>
					<li><p>Seit 2007: FC Schalke 04 e.V., passives Mitglied</p></li>
					<li><p>Seit Oktober 2009: 1. Snooker-Club-Hofgeismar e. V.</p></li>
					<li><p>Seit September 2014: SPD-Ortsverein Warburg</p></li>
				</ul>
			</div>
		</div>
	</div>
</div>

<jsp:include page="../layout/mainfooter.jsp" flush="true"/>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>