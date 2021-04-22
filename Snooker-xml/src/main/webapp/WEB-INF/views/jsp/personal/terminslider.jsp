<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/taglib"%>
<%@ taglib prefix="do" tagdir="/WEB-INF/tags/taglib/eigeneTags.tld"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="author" content="Thorben Dierkes" />
<title>Thorben Dierkes</title>

<header:defaultHaeder costumerCSS="thorben.css" favicon="thorben.ico"/>

</head>
<body onLoad="startTime()" >

<jsp:include page="../layout/mainhead.jsp" flush="true"/>


<div id="content" class="jumbotron">
	<div id="contenContainer" class="container">
		<h1>Meine Termine</h1>
		<div id="inhalt" class="row">
			<c:if test="${not empty newsList}">
				<c:forEach items="${newsList}" var="element">
				<article class="news-image-teaser">
					<div class="news-image-teaser-meta"><time datetime="${element.creationDateForSlider}">${element.creationDateForSlider}</time></div>
					<h2><a href="newsreader?id=${element.id}">${element.title}</a></h2>
					<div>${element.teaser}</div>
					<p><a href="newsreader?id=${element.id}"><span >Weiterlesen... </span></a></p>
				</article>
				</c:forEach>
			</c:if>
				<nav aria-label="...">
					<ul class="pagination justify-content-center">
						<li class="page-item">
					      <a class="page-link" href="newsslider?action=back" tabindex="-1">Previous</a>
					    </li>
					    <li <c:if test="${activePage eq 1}"> class="page-item active" </c:if> <c:if test="${activePage ne 1}"> class="page-item" </c:if>>
					    	<a class="page-link" href="newsslider?page=1">1 <span class="sr-only">(current)</span></a>
					    </li>
						<c:forEach var = "i" begin ="2" end ="${sliderlenght}">
	         				<li <c:if test="${activePage eq i}"> class="page-item active" </c:if> <c:if test="${activePage ne i}"> class="page-item" </c:if>>
					    		<a class="page-link" href="newsslider?page=${i}">${i}</a>
					    	</li>
	      				</c:forEach>
					    <li class="page-item">
					      <a class="page-link" href="newsslider?action=next">Next</a>
					    </li>
				  </ul>
				</nav>
		</div>
	</div>
</div>

<jsp:include page="../layout/mainfooter.jsp" flush="true"/>

<script src="resources/core/js/clock.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>