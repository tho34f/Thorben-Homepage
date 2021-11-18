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

<header:defaultHaeder costumerCSS="thorben.css" favicon="thorben.ico"/>

</head>
<body>

<jsp:include page="../layout/mainhead.jsp" flush="true"/>


<div id="content" class="jumbotron">
	<div id="contenContainer" class="container">
		<h1><tho:out value="global.contact.details"/></h1>
		<h3><tho:out value="global.contact.address"/></h3>
		<p> <tho:out value="global.name.owner"/> <br/> Kollwitztsraﬂe 3 <br/> 34414 Warburg </p>
		
		<h3><tho:out value="global.contact.digital"/></h3>
		<p> E-Mail: thorben.dierkes@gmail.com <br/> Telefon: +49 15781539604 </p>
	</div>
</div>

<jsp:include page="../layout/mainfooter.jsp" flush="true"/>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>