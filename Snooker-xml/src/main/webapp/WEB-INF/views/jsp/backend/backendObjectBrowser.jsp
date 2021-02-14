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
		<table>
			<tbody>
				<tr>
					<td style="border-top: 1px solid transparent;">
						<table>
							<tbody>
								<tr>
									<th id="obObjectTitle">Title</th>
									<th id="obObjectTeaser">Teaser</th>
								</tr>
								<c:if test="${empty informationList}">
									<tr><td>${errorMessage}</td></tr>
								</c:if>
								<c:if test="${not empty informationList}">
									<c:forEach items="${informationList}" var="element">
										<tr id="object_${element.id}">
											<td>${element.title}</td>
											<td>${element.teaser}</td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>

<jsp:include page="../layout/backendfooter.jsp"/>
<script src="../resources/core/js/backend.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>