<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/taglib"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="author" content="Thorben Dierkes" />
<title>Thorben Dierkes</title>

<header:defaultHaeder costumerCSS="thorben.css" favicon="thorben.ico"/>

</head>
<body onLoad="startTime()" >

<jsp:include page="./layout/mainhead.jsp" flush="true"/>


<div id="content" class="jumbotron">
	<div id="contenContainer" class="container">
		<h1>News</h1>
		<div id="inhalt" class="row">
			<c:if test="${not empty newsList}">
				<c:forEach items="${newsList}" var="element">
				<article class="news-image-teaser">
					<div class="news-image-teaser-meta"><time> ${element.creationDateAsString}</time></div>
					<div class="ob_row"><a id="${element.id}" href="" onclick="openWizard('${element.id}', ${Objectbrowser.objectType})">${element.title}</a></div>
					<div class="ob_row">${element.teaser}</div>
					<div class="ob_row">
						<c:if test="${empty element.changeDateAsString}">-</c:if>
						<c:if test="${not empty element.changeDateAsString}">${element.changeDateAsString}</c:if>
					</div>
				</article>
				</c:forEach>
			</c:if>
		</div>
	</div>
</div>

<jsp:include page="./layout/mainfooter.jsp" flush="true"/>

<script src="resources/core/js/clock.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>