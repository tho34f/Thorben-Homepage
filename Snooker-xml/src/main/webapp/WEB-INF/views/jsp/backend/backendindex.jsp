<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/taglib"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="author" content="Thorben Dierkes" />
<title>Thorben Dierkes</title>

<header:defaultBackendHeader costumerCSS="thorben.css" favicon="thorben.ico"/>

</head>
<body onLoad="startTime()" >

<jsp:include page="../layout/backendhead.jsp"/>


<div id="content" class="jumbotron">
	<div class="container">
		<h1>Ergebniss der Suche</h1>
		<c:if test="${ isLoginOk eq true}"> 
			<p> Login-Prozess Erfolgreich </p>
		</c:if>
		<c:if test="${ isLoginOk eq false}">  
			<p> ${errormasage} </p>
		</c:if>
	</div>
</div>

<jsp:include page="../layout/backendfooter.jsp"/>
<script src="resources/core/js/clock.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>