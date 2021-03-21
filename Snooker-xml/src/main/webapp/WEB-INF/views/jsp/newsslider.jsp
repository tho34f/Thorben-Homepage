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

<jsp:include page="./layout/mainhead.jsp" flush="true"/>


<div id="content" class="jumbotron">
	<div id="contenContainer" class="container">
		<h1>News</h1>
		<div id="inhalt" class="row">
			<c:if test="${not empty newsList}">
				<c:forEach items="${newsList}" var="element">
				<article class="news-image-teaser">
					<div class="news-image-teaser-meta"><time datetime="${element.creationDateForSlider}">${element.creationDateForSlider}</time></div>
					<h2><a href="#">${element.title}</a></h2>
					<div>${element.teaser}</div>
					<p><a href="#"><span >Weiterlesen... </span></a></p>
				</article>
				</c:forEach>
			</c:if>
				<nav aria-label="...">
				  <ul class="pagination justify-content-center">
				    <li class="page-item disabled">
				      <a class="page-link" href="#" tabindex="-1">Previous</a>
				    </li>
				    <li class="page-item active">
				    	<a class="page-link" href="#">1 <span class="sr-only">(current)</span></a>
				    </li>
				    <li class="page-item">
				      <a class="page-link" href="#">2</a>
				    </li>
				    <li class="page-item">
				    	<a class="page-link" href="#">3</a>
				    </li>
				    <li class="page-item">
				      <a class="page-link" href="#">Next</a>
				    </li>
				  </ul>
				</nav>
		</div>
	</div>
</div>

<jsp:include page="./layout/mainfooter.jsp" flush="true"/>

<script src="resources/core/js/clock.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript">
$('li').click(function() {
	  $(this).addClass('active').siblings().removeClass('active');
});
</script>

</body>
</html>