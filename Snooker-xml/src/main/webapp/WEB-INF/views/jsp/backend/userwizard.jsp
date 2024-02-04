<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/taglib"%>
<%@ taglib prefix="tho" uri="/thorben"%>
<%@ taglib prefix="wizard" tagdir="/WEB-INF/tags/taglib/wizard"%>
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
		
		<wizard:inputarea titleBundle="global.firstname" descriptionBundle="global.firstname.desc" name="firstName"/>
		<wizard:inputarea titleBundle="global.surname" descriptionBundle="global.surname.desc" name="lastName"/>
		<wizard:inputarea titleBundle="global.username" descriptionBundle="global.username.create" name="login"/>
		<wizard:inputarea titleBundle="global.password" descriptionBundle="global.password.create" name="password"/>
		
	</div>
</div>

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