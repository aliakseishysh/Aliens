<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<article class="media content-section">

	<img class="rounded-circle article-img"
		src="<c:url value="${alien.getImageUrl()}"/>" alt="no image">
	<div class="media-body">
		<div class="article-metadata">
			<a class="mr-2" href="<c:url value="/controller?command=REDIRECT_ALIEN_PROFILE&alien_id=${alien.getId()}"/>">${alien.getName()}</a> 
		</div>
		<p class="article-content">${alien.getSmallDescription()}</p>
	</div>
</article>