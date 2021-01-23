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
	<div id="contenContainer" class="container">
		<h1>Impressum</h1>
		<h3>Angaben gemäß § 5 TMG</h3>
		<p> Thorben Dierkes <br/> Kollwitztsraße 3 <br/> 34414 Warburg </p>
		<p> E-Mail: thorben.dierkes@gmail.com <br/> Telefon: +49 15781539604 </p>
		<h3>Verantwortlich für den Inhalt nach § 55 Abs. 2 RStV </h3>
		<p> Thorben Dierkes <br/> Kollwitztsraße 3 <br/> 34414 Warburg </p>
		<p> E-Mail: thorben.dierkes@gmail.com <br/> Telefon: +49 15781539604 </p>
		<h3>Rechtlicher Hinweis</h3>
		<p>Im Hinblick auf die technischen Eigenschaften des Internet kann keine Gewähr für die Authentizität, Richtigkeit und Vollständigkeit der im Internet zur Verfügung gestellten Informationen übernommen werden. Es wird auch keine Gewähr für die Verfügbarkeit oder den Betrieb der gegenständlichen Webseite und ihrer Inhalte übernommen.</p>
		<p>Jede Haftung für unmittelbare, mittelbare oder sonstige Schäden, unabhängig von deren Ursachen, die aus der Benutzung oder Nichtverfügbarkeit der Daten und Informationen dieser Homepage erwachsen, wird, soweit rechtlich zulässig, ausgeschlossen.</p>
		<p>Unser Angebot enthält Links zu externen Webseiten Dritter, auf deren Inhalte wir keinen Einfluss haben. Deshalb können wir für diese fremden Inhalte auch keine Gewähr übernehmen. Für die Inhalte der verlinkten Seiten ist stets der jeweilige Anbieter oder Betreiber der Seiten verantwortlich. Die verlinkten Seiten wurden zum Zeitpunkt der Verlinkung auf mögliche Rechtsverstöße überprüft. Rechtswidrige Inhalte waren zum Zeitpunkt der Verlinkung nicht erkennbar.</p>
		<p>Der Inhalt dieser Webseite ist urheberrechtlich geschützt. Verlinkungen auf unsere Webseite sind herzlich willkommen!</p>
		<h3>Google Analytics</h3>
		<p>Diese Website benutzt Google Analytics, einen Webanalysedienst der Google Inc., um eine Analyse der Benutzung der Website zu ermöglichen. Die durch den Einsatz von Google Analytics erzeugten Informationen über Ihre Nutzung diese Website (einschließlich Ihrer IP-Adresse) werden an einen Server der Google Inc. in den USA übertragen und dort gespeichert. Google wird diese Informationen benutzen, um Ihre Nutzung der Website auszuwerten, um Reports über die Websiteaktivitäten für die Websitebetreiber zusammenzustellen und um weitere mit der Websitenutzung und der Internetnutzung verbundene Dienstleistungen zu erbringen. Google wird in keinem Fall Ihre IP-Adresse mit anderen Daten von Google in Verbindung bringen. Durch die Nutzung dieser Website erklären Sie sich mit der Bearbeitung der über Sie erhobenen Daten durch Google in der zuvor beschriebenen Art und Weise und zu dem zuvor benannten Zweck einverstanden.</p>
		<p>Bitte beachten Sie auch die <a href="http://www.google.com/intl/de/analytics/tos.html">Nutzungsbedingungen</a> von Google Analytics.</p>
	</div>
</div>

<jsp:include page="./layout/mainfooter.jsp" flush="true"/>

<script src="resources/core/js/clock.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>