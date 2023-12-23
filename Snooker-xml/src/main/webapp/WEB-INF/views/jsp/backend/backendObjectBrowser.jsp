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
						<c:forEach items="${Objectbrowser.ob3Data.title}" var="title">
							<th class="redThorben ob_title_datacolumn_on" id="obObject_${title.description}">${title.description}</th>
						</c:forEach>
					</tr>
					<c:if test="${empty Objectbrowser.ob3Data.objectList}">
						<tr><td>${errorMessage}</td></tr>
					</c:if>
					<c:if test="${not empty Objectbrowser.ob3Data.objectList}">
						<c:forEach items="${Objectbrowser.ob3Data.objectList}" var="element">
							<tr id="object_${element.id}">
								<td class="ob_row">
									<c:if test="${Objectbrowser.objectType eq 38}">
										<a id="${element.id}" title="${element.userLogin}" href="#" onclick="openWizard('${element.id}', ${Objectbrowser.objectType});return false;">${element.userLogin}</a>
									</c:if>
									<c:if test="${Objectbrowser.objectType ne 38}">
										<a id="${element.id}" title="${element.title}" href="#" onclick="openWizard('${element.id}', ${Objectbrowser.objectType});return false;">${element.title}</a>
									</c:if>
								</td>
								<c:if test="${Objectbrowser.objectType eq 39 or  Objectbrowser.objectType eq 40}">
									<td class="ob_row">
										${element.teaser}
									</td>
								</c:if>
								<c:if test="${Objectbrowser.objectType eq 41}">
									 <td class="ob_row">
									 	${element.description}
									 </td>
								</c:if>
								<c:if test="${Objectbrowser.objectType eq 38}">
									 <td class="ob_row">
									 	${element.firstName}
									 </td>
									 <td class="ob_row">
									 	${element.lastName}
									 </td>
								</c:if>
								<td class="ob_row">${element.creationDateAsString}</td>
								<c:if test="${Objectbrowser.objectType eq 39 or  Objectbrowser.objectType eq 40}">
									<td class="ob_row">
										<c:if test="${empty element.changeDateAsString}">-</c:if>
										<c:if test="${not empty element.changeDateAsString}">${element.changeDateAsString}</c:if>
									</td>
								</c:if>
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