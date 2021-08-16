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

<jsp:include page="../layout/backendhaederwithbuttons.jsp"/>


<div id="content" class="jumbotron">
	<div class="container">
		<div id="objectBrowserDataTableDiv">
			<table >
				<caption style="display:none">OB3 second table</caption>
				<tbody>
					<tr class="obTableHeader">
						<th class="redThorben ob_title_datacolumn_on" id="obObjectTitle">Title</th>
						<th class="redThorben ob_title_datacolumn_on" id="obObjectTeaser">Teaser/Beschreibung</th>
						<th class="redThorben ob_title_datacolumn_on" id="obObjectTeaser">Erstellungsdatum</th>
						<th class="redThorben ob_title_datacolumn_on" id="obObjectTeaser">Änderungsdatum</th>
					</tr>
					<c:if test="${empty informationList}">
						<tr><td>${errorMessage}</td></tr>
					</c:if>
					<c:if test="${not empty informationList}">
						<c:forEach items="${informationList}" var="element">
							<tr id="object_${element.id}">
								<td class="ob_row">
									<a id="${element.id}" title="${element.title}" href="#" onclick="openWizard('${element.id}', ${Objectbrowser.objectType});return false;">${element.title}</a>
								</td>
								<td class="ob_row">
									<c:if test="${empty element.teaser}"> ${element.description} </c:if>
									<c:if test="${not empty element.teaser}"> ${element.teaser} </c:if>
								</td>
								<td class="ob_row">${element.creationDateAsString}</td>
								<td class="ob_row">
									<c:if test="${empty element.changeDateAsString}">-</c:if>
									<c:if test="${not empty element.changeDateAsString}">${element.changeDateAsString}</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
		
	</div>
</div>

<jsp:include page="../layout/backendfooter.jsp"/>
<script src="../resources/core/js/backend.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>