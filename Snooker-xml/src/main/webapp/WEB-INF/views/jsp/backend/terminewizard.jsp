<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/taglib"%>
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
		<div style="margin-top: 0px;height: 75px;border-right: 1px solid #bbb;float: left;width: 270px;">
			<div class="far fa-calendar-alt" id="objectIcon"></div>
			<h4 id="objectHeaderWizard">Termine</h4>
		</div>
		<div>
			<form method="POST" action="terminewizard">
				<input type="hidden" id="titleWizard" name="titleWizard" min="1" max="255" maxlength="255" value="">
				<input type="hidden" id="teaserWizard" name="teaserWizard" min="1" max="255" maxlength="255" value="">
				<input type="hidden" id="beschreibungWizard" name="beschreibungWizard" min="1" max="255" maxlength="255" value="">
				<button id="saveObject" type="submit" title="Speichern" onclick="setValue()">
					<em>Speichern</em>
				</button>
			</form>
		</div>
	</div>
</header>

<div id="content" class="jumbotron">
	<div class="container">
		<c:if test="${empty termin}"><h1>Erstellen eines neuen Termins</h1></c:if>
		<c:if test="${not empty termin}"><h1>Bearbeitung eines vorhandenden Termins</h1></c:if>
		
		
		<div class="wizardRow">
			<div class="wizardRowTitle">Title</div>
			<div class="wizardRowInner">
				<div class="span3"></div>
				<div class="span9">
					<input type="text" id="title" name="title" min="1" max="255" maxlength="255" placeholder="Title" value="">
				</div>
			</div>
		</div>
		
		<div class="wizardRow">
			<div class="wizardRowTitle">Teaser</div>
			<div class="wizardRowInner">
				<div class="span3"></div>
				<div class="span9">
					<input type="text" id="teaser" name="teaser" min="1" max="255" maxlength="255" placeholder="Teaser" value="">
				</div>
			</div>
		</div>
		
		<div class="wizardRow">
			<div class="wizardRowTitle">Beschreibung</div>
			<div class="wizardRowInner">
				<div class="span3"></div>
				<div class="span9">
					<input type="text" id="beschreibung" name="beschreibung" min="1" max="255" maxlength="255" placeholder="Beschreibung" value="">
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