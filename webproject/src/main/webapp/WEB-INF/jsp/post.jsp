<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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