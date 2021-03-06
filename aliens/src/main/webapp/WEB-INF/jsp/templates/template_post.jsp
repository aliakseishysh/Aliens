<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<article class="media content-section">
	<img class="rounded-circle article-img"
		src="<c:url value="${alien.imageUrl}"/>" alt="${TEXT[TEMPLATE_POST_IMAGE_ALT]}">
	<div class="media-body">
		<div class="article-metadata">
			<a class="mr-2" href="<c:url value="/${CONTROLLER}?${COMMAND}=${OPEN_ALIEN_PROFILE_PAGE}&${ALIEN_ID}=${alien.id}"/>">${alien.name}</a> 
		</div>
		<p class="article-content text-break">${alien.smallDescription}</p>
	</div>
</article>