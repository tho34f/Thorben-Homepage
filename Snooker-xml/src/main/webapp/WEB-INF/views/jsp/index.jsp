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

<jsp:include page="./layout/mainhead.jsp" flush="true"/>

<div id="content" class="jumbotron">
	<div id="contenContainer" class="container">
		<h1>Mathematik - die schönste Nebensache der Welt</h1>
		<div id="inhalt" class="row">
			<div class="col-md-6">
				<p><strong><tho:out value="global.hompage.intro"/></strong></p>
				<p><tho:out value="global.hompage.intro.part.one"/></p>
				<p><tho:out value="global.hompage.intro.part.two"/></p>
				<p>
					<tho:out value="global.name.owner.salutation"/> <br><br>
					<tho:out value="global.name.owner"/>
				</p>
			</div>
			<div class="col-md-6">
				<img src="resources/core/images/thorben.png" alt="Bild Thorben" style="hight:700px; width:300px;" />
			</div>
		</div>
		<div id="inhalt" class="widget-container">
			<div class="content-widget-wrapper">
			 	<div class="content-widget-container">
			 		<div class="content-widget-content">
						<h3><tho:out value="global.personal"/></h3>
						<p><tho:out value="global.personal.desc"/></p>
						<a class="linkButton" href="/thorben-dierkes/personal">#PERSÖNLICHES</a>
					</div>
				</div>
			</div>
			<div class="content-widget-wrapper">
			 	<div class="content-widget-container">
			 		<div class="content-widget-content">
						<h3><tho:out value="global.politic"/></h3>
						<p><tho:out value="global.politic.desc"/></p>
						<a 	class="linkButton" href="/thorben-dierkes/politik">#WirWarburger</a>
					</div>
				</div>
			</div>
			<div class="content-widget-wrapper">
			 	<div class="content-widget-container">
			 		<div class="content-widget-content">
						<h3><tho:out value="global.snooker"/></h3>
						<p><tho:out value="global.snooker.desc"/></p>
						<a class="linkButton" href="/thorben-dierkes/snooker">#SNOOKER</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="./layout/mainfooter.jsp" flush="true"/>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>