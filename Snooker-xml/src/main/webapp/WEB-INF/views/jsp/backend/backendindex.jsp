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

<jsp:include page="../layout/backendhead.jsp"/>


<div id="content" class="jumbotron">
	<div class="container">
		<h1><tho:out value="backend.intro"/></h1>
		<div style="text-align:center">
			<div class="col-md-4">
				<h3><tho:out value="global.user"/></h3>
				<p><tho:out value="global.user.desc"/></p>
				<a class="linkButton" href="/thorben-dierkes/personal">#USER</a>
			</div>
			<div class="col-md-4">
				<h3><tho:out value="global.news"/></h3>
				<p><tho:out value="global.news.desc"/></p>
				<a class="linkButton" href="/thorben-dierkes/personal">#News</a>
			</div>
			<div class="col-md-4">
				<h3><tho:out value="global.events"/></h3>
				<p><tho:out value="global.events.desc"/></p>
				<a class="linkButton" href="/thorben-dierkes/personal">#Kalender</a>
			</div>
		</div>
	</div>
</div>

<jsp:include page="../layout/backendfooter.jsp"/>
<script src="../resources/core/js/backend.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>