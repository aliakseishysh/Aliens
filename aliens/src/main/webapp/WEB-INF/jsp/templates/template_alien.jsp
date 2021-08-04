<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="media">
	<img id="alien-image" class="rounded-circle account-img"
		src="<c:url value="${alien.imageUrl}"/>" alt="${TEXT[PAGE_ALIEN_PROFILE_ALIEN_IMAGE_ALT]}">
	<div class="media-body">
		<h2 id="alien-name" class="account-heading">${alien.name}</h2>
		<p id="alien-small-description" class="h4 text-break">${alien.smallDescription}</p>
	</div>
	<c:choose>
		<c:when test="${currentUser.role == USER or currentUser.role == ADMIN}">
			<jsp:include page="${TEMPLATE_ALIEN_RATING_JSP}"/>						
		</c:when>
	</c:choose>
</div>
<div class="content-section">
	<p class="border-bottom mb-4 h3">${TEXT[PAGE_ALIEN_PROFILE_ALIEN_DESCRIPTION]}</p>
	<p id="alien-big-description" class="h4 text-break">${alien.bigDescription}</p>
</div>