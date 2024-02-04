<%@ tag language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="tho" uri="/thorben"%>

<%@ attribute name="titleBundle" required="true" rtexprvalue="false"%>
<%@ attribute name="descriptionBundle" required="true" rtexprvalue="false"%>
<%@ attribute name="name" required="true" rtexprvalue="false"%>
<%@ attribute name="maxlength" required="true" rtexprvalue="false"%>
<%@ attribute name="rows" required="true" rtexprvalue="false"%>

<div class="wizardRow">
	<div class="wizardRowTitle"><tho:out value="${titleBundle}"/></div>
	<div class="wizardRowInner">
		<div class="span3 description"><tho:out value="${descriptionBundle}"/></div>
			<div class="span9">
				<textarea class="textInput" id="${name}" name="${name}" maxlength="${maxlength}" placeholder="<tho:out value="${titleBundle}"/>" cols="100" rows="${rows}"></textarea>
		</div>
	</div>
</div>