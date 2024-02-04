<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tho" uri="/thorben"%>

<%@ attribute name="action" required="true" rtexprvalue="false"%>

<div>
	<ul class="sliderNavigation pagination justify-content-center">
		<li class="page-item">
	      <a class="page-link" href="${action}?action=back" tabindex="-1"><tho:out value="global.previous"/></a>
	    </li>
	    <li <c:if test="${activePage eq 1}"> class="page-item active" </c:if> <c:if test="${activePage ne 1}"> class="page-item" </c:if>>
	    	<a class="page-link" href="${action}?page=1">1 <span class="sr-only">(current)</span></a>
	    </li>
		<c:forEach var = "i" begin ="2" end ="${sliderlenght}">
      		<li <c:if test="${activePage eq i}"> class="page-item active" </c:if> <c:if test="${activePage ne i}"> class="page-item" </c:if>>
	    		<a class="page-link" href="${action}?page=${i}">${i}</a>
	    	</li>
   		</c:forEach>
	    <li class="page-item">
	      <a class="page-link" href="${action}?action=next"><tho:out value="global.next"/></a>
	    </li>
  </ul>
</div>