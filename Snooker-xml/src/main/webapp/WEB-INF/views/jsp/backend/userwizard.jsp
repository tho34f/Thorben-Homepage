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
				<input type="hidden" id="firstNameWizard" name="titleWizard" min="1" max="255" maxlength="255" value="">
				<input type="hidden" id="lastNameWizard" name="teaserWizard" min="1" max="255" maxlength="255" value="">
				<input type="hidden" id="loginWizard" name="textWizard" min="1" max="255" maxlength="1000" value="">
				<input type="hidden" id="passwordWizard" name="textWizard" min="1" max="255" maxlength="1000" value="">
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
			<div class="wizardRowTitle">Vorname</div>
			<div class="wizardRowInner">
				<div class="span3"></div>
				<div class="span9">
					<input type="text" id="firstName" name="title" min="1" max="255" maxlength="255" placeholder="Title" value="">
				</div>
			</div>
		</div>
		
		<div class="wizardRow">
			<div class="wizardRowTitle">Nachname</div>
			<div class="wizardRowInner">
				<div class="span3"></div>
				<div class="span9">
					<textarea id="lastName" name="teaser" maxlength="65535" placeholder="Teaser" cols="100" rows="2"></textarea>
				</div>
			</div>
		</div>
		
		<div class="wizardRow">
			<div class="wizardRowTitle">Login</div>
			<div class="wizardRowInner">
				<div class="span3">Legen Sie einen Login-Namen fest.</div>
				<div class="span9">
					<textarea id="login" name="text" maxlength="16777215" placeholder="Text" cols="100" rows="5"></textarea>
				</div>
			</div>
		</div>
		
		<div class="wizardRow">
			<div class="wizardRowTitle">Password</div>
			<div class="wizardRowInner">
				<div class="span3">Legen Sie ein Password für den Benutzer fest</div>
				<div class="span9">
					<textarea id="password" name="text" maxlength="16777215" placeholder="Text" cols="100" rows="5"></textarea>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="../resources/core/js/backend.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script>
	
	function setValue(){
		$("#firstNameWizard").val($("#firstName").val());
		$("#lastNameWizard").val($("#lastName").val());
		$("#loginWizard").val($("#login").val());
		$("#passwordWizard").val($("#password").val());
	}

</script>

</body>
</html>