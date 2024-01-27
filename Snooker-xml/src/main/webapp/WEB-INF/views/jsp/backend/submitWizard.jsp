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

<header:saveBackendHeader costumerCSS="backend.css" favicon="thorben.ico"/>

</head>
<body>

<header>
	<div class="headerPanel">
		<div style="margin-top: 0px;height: 75px;border-right: 1px solid #bbb;float: left;width: 270px;">
			<div class="fas fa-newspaper" id="objectIcon"></div>
			<h4 id="objectHeaderWizard"><tho:out value="global.news"/></h4>
		</div>
	</div>
</header>

<div id="content" class="jumbotron">
	<div class="container">
		<c:if test="${empty errormasage}"><h1><tho:out value="${errormasage}"/></h1></c:if>
		<c:if test="${not empty errormasage}"><h1><tho:out value="${errormasage}"/></h1></c:if>
	</div>
</div>

</body>
</html>