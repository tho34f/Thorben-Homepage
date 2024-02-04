<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/taglib"%>
<%@ taglib prefix="wizard" tagdir="/WEB-INF/tags/taglib/wizard"%>
<%@ taglib prefix="tho" uri="/thorben"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="author" content="Thorben Dierkes" />
<title>Thorben Dierkes</title>

<header:defaultBackendHeader costumerCSS="backend.css" favicon="thorben.ico"/>

</head>
<body>

<header>
	<div class="headerPanel">
		<div class="typeWrapper">
			<div class="far fa-calendar-alt" id="objectIcon"></div>
			<h4 id="objectHeader"><tho:out value="global.events"/></h4>
		</div>
		<div>
			<form method="POST" action="terminewizard/submit">
				<input type="hidden" name="csrfToken" value="${CSRF_SESSION_TOKEN}">
				<input type="hidden" id="titleWizard" name="titleWizard" min="1" max="255" maxlength="255" value="">
				<input type="hidden" id="teaserWizard" name="teaserWizard" min="1" max="255" maxlength="255" value="">
				<input type="hidden" id="beschreibungWizard" name="beschreibungWizard" min="1" max="255" maxlength="1000" value="">
				<button id="saveObject" type="submit" title="Speichern" onclick="setValue()">
					<em>Speichern</em>
				</button>
			</form>
		</div>
	</div>
</header>

<div id="content" class="jumbotron">
	<div class="container">
		<c:if test="${empty termin}"><h1><tho:out value="backend.events.wizard.new"/></h1></c:if>
		<c:if test="${not empty termin}"><h1><tho:out value="backend.events.wizard.old"/></h1></c:if>
		
		<wizard:inputarea titleBundle="backend.wizard.title" descriptionBundle="backend.wizard.title.desc.events" name="title"/>
		<wizard:textArea titleBundle="backend.wizard.teaser" descriptionBundle="backend.wizard.teaser.desc.events" name="teaser" maxlength="65535" rows="2"/>
		<wizard:textArea titleBundle="backend.wizard.description" descriptionBundle="backend.wizard.text.desc.events" name="beschreibung" maxlength="16777215" rows="5"/>
		
	</div>
</div>

<script>

	function setValue(){
		$("#titleWizard").val($("#title").val());
		$("#teaserWizard").val($("#teaser").val());
		$("#beschreibungWizard").val($("#beschreibung").val());
	}

</script>

</body>
</html>