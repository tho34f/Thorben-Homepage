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
		
		
		<div class="wizardRow">
			<div class="wizardRowTitle"><tho:out value="backend.wizard.title"/></div>
			<div class="wizardRowInner">
				<div class="span3"><tho:out value="backend.wizard.title.desc.events"/></div>
				<div class="span9">
					<input class="textInput" type="text" id="title" name="title" min="1" max="255" maxlength="255" placeholder="Title" value="">
				</div>
			</div>
		</div>
		
		<div class="wizardRow">
			<div class="wizardRowTitle"><tho:out value="backend.wizard.teaser"/></div>
			<div class="wizardRowInner">
				<div class="span3"><tho:out value="backend.wizard.teaser.desc.events"/></div>
				<div class="span9">
					<textarea class="textInput" id="teaser" name="teaser" maxlength="65535" placeholder="Teaser" cols="100" rows="2"></textarea>
				</div>
			</div>
		</div>
		
		<div class="wizardRow">
			<div class="wizardRowTitle"><tho:out value="backend.wizard.description"/></div>
			<div class="wizardRowInner">
				<div class="span3"><tho:out value="backend.wizard.text.desc.events"/></div>
				<div class="span9">
					<textarea class="textInput" id="beschreibung" name="beschreibung" maxlength="16777215" placeholder="Text" cols="100" rows="5"></textarea>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="../resources/core/js/backend.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script>

	function setValue(){
		$("#titleWizard").val($("#title").val());
		$("#teaserWizard").val($("#teaser").val());
		$("#beschreibungWizard").val($("#beschreibung").val());
	}

</script>

</body>
</html>