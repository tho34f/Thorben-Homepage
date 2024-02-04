<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tho" uri="/thorben"%>

<%@ attribute name="titleBundle" required="true" rtexprvalue="false"%>
<%@ attribute name="descriptionBundle" required="true" rtexprvalue="false"%>
<%@ attribute name="name" required="true" rtexprvalue="false"%>


<div class="wizardRow">
	<div class="wizardRowTitle"><tho:out value="${titleBundle}"/></div>
	<div class="wizardRowInner">
		<div class="span3 description"><tho:out value="${descriptionBundle}"/></div>
		<div class="span9">
			<input class="textInput" type="text" id="${name}" name="${name}" min="1" max="255" maxlength="255" placeholder="<tho:out value="${titleBundle}"/>" value="">
		</div>
	</div>
</div>