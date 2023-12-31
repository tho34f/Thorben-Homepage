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
			<div class="fas fa-newspaper" id="objectIcon"></div>
			<h4 id="objectHeader"><tho:out value="global.user"/></h4>
		</div>
		<div>
			<form method="POST" action="newswizard/submit">
				<input type="hidden" name="csrfToken" value="${CSRF_SESSION_TOKEN}">
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
		<c:if test="${empty message}"><h1><tho:out value="backend.user.wizard.new"/></h1></c:if>
		<c:if test="${not empty message}"><h1><tho:out value="backend.user.wizard.old"/></h1></c:if>
		
		<div class="wizardRow">
			<div class="wizardRowTitle"><tho:out value="global.firstname"/></div>
			<div class="wizardRowInner">
				<div class="span3 description"><tho:out value="global.firstname.desc"/></div>
				<div class="span9">
					<input class="textInput" type="text" id="firstName" name="firstName" min="1" max="255" maxlength="255" placeholder="<tho:out value="global.firstname"/>" value="">
				</div>
			</div>
		</div>
		
		<div class="wizardRow">
			<div class="wizardRowTitle"><tho:out value="global.surname"/></div>
			<div class="wizardRowInner">
				<div class="span3 description"><tho:out value="global.surname.desc"/></div>
				<div class="span9">
					<input class="textInput" type="text" id="lastName" name="lastName" min="1" max="255" maxlength="255" placeholder="<tho:out value="global.surname"/>" value="">
				</div>
			</div>
		</div>
		
		<div class="wizardRow">
			<div class="wizardRowTitle"><tho:out value="global.username"/></div>
			<div class="wizardRowInner">
				<div class="span3 description"><tho:out value="global.username.create"/></div>
				<div class="span9">
					<input class="textInput" type="text" id="login" name="login" min="1" max="255" maxlength="255" placeholder="<tho:out value="global.username"/>" value="">
				</div>
			</div>
		</div>
		
		<div class="wizardRow">
			<div class="wizardRowTitle"><tho:out value="global.password"/></div>
			<div class="wizardRowInner">
				<div class="span3 description"><tho:out value="global.password.create"/></div>
				<div class="span9">
					<input class="textInput" type="text" id="password" name="password" min="1" max="255" maxlength="255" placeholder="<tho:out value="global.password"/>" value="">
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