<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="by.shyshaliaksey.webproject.controller.command.PagePath" %>
<c:set var="SESSION_VARIABLES" scope="page" value="${PagePath.SESSION_VARIABLES_JSP.getValue()}"/>
<c:if test="${CONTROLLER == null}">
	<jsp:include page="${SESSION_VARIABLES}"/>
</c:if>
<article class="media content-section">

	<img class="rounded-circle article-img"
		src="<c:url value="${alien.getImageUrl()}"/>" alt="no image">
	<div class="media-body">
		<div class="article-metadata">
			<a class="mr-2" href="<c:url value="/${CONTROLLER}?${COMMAND}=${OPEN_ALIEN_PROFILE_PAGE}&${ALIEN_ID}=${alien.getId()}"/>">${alien.getName()}</a> 
		</div>
		<p class="article-content">${alien.getSmallDescription()}</p>
	</div>
</article>