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
			<div class="fas fa-newspaper" id="objectIcon"></div>
			<h4 id="objectHeaderWizard">Nachrichten</h4>
		</div>
		<div>
			<form method="POST" action="newswizard">
				<input type="hidden" id="titleWizard" name="titleWizard" min="1" max="255" maxlength="255" value="">
				<input type="hidden" id="teaserWizard" name="teaserWizard" min="1" max="255" maxlength="255" value="">
				<input type="hidden" id="textWizard" name="textWizard" min="1" max="255" maxlength="1000" value="">
				<button id="saveObject" type="submit" title="Speichern" onclick="setValue()">
					<em>Speichern</em>
				</button>
			</form>
		</div>
	</div>
</header>

<div id="content" class="jumbotron">
	<div class="container">
		<c:if test="${empty message}"><h1>Erstellen einer neuen Nachricht</h1></c:if>
		<c:if test="${not empty message}"><h1>Bearbeitung einer vorhandenden Nachricht</h1></c:if>
		
		<div class="wizardRow">
			<div class="wizardRowTitle">Title</div>
			<div class="wizardRowInner">
				<div class="span3">Der Title dient als Überschrift in allen Anzeigen einer Nachricht.</div>
				<div class="span9">
					<input type="text" id="title" name="title" min="1" max="255" maxlength="255" placeholder="Title" value="">
				</div>
			</div>
		</div>
		
		<div class="wizardRow">
			<div class="wizardRowTitle">Teaser</div>
			<div class="wizardRowInner">
				<div class="span3">Der Teaser erscheint als ergänzende Information im Nachrichten-Slider.</div>
				<div class="span9">
					<textarea id="teaser" name="teaser" maxlength="65535" placeholder="Teaser" cols="100" rows="2"></textarea>
				</div>
			</div>
		</div>
		
		<div class="wizardRow">
			<div class="wizardRowTitle">Text</div>
			<div class="wizardRowInner">
				<div class="span3"></div>
				<div class="span9">
					<textarea id="text" name="text" maxlength="16777215" placeholder="Text" cols="100" rows="5"></textarea>
				</div>
			</div>
		</div>
		
		<div class="wizardRow">
			<div class="wizardRowTitle">Bild</div>
			<div class="wizardRowInner">
				<div class="span3">Legen Sie fest, welches Bild für diese Nachricht verwendet werden soll.</div>
				<div class="span9"></div>
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
		$("#textWizard").val($("#text").val());
	}

</script>

</body>
</html>