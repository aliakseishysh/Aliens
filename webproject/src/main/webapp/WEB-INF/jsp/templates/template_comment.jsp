<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="<c:url value="${CSS_COMMENT}"/>" rel="stylesheet">
<script>
	var CONTROLLER = `${CONTROLLER}`;
	var COMMAND = `${COMMAND}`;
	var DELETE_COMMENT = `${DELETE_COMMENT}`;
	var COMMENT_ID = `${COMMENT_ID}`;
	var commentId = `${comment.id}`;
</script>

<!-- 
<script type="text/javascript" src="<c:url value='${JS_ALIEN_PROFILE}'/>"></script>
 -->
<article id="comment_${comment.id}" class="media content-section">

	<img class="rounded-circle article-img" src="<c:url value="${comment.userImage}"/>" alt="${TEXT[TEMPLATE_COMMENT_IMAGE_ALT]}">
	<div class="media-body">
		<div class="article-metadata">
			<div class="row row-comment">
				<p>${comment.userLogin}</p>
				<c:choose>
					<c:when test="${comment.userLogin eq currentUser.login or currentUser.role eq ADMIN}">
						<div class="justify-content-end-comment">
							<button type="button" class="close" aria-label="Close"
									onclick="deleteComment()">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
					</c:when>
				</c:choose>
			</div>

		</div>
		<p class="article-content">${comment.comment}</p>
	</div>
</article>