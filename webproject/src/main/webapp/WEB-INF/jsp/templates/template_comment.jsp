<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="<c:url value='${JS_ALIEN_PROFILE}'/>"></script>
<link href="<c:url value="${CSS_COMMENT_RATING}"/>" rel="stylesheet">
<article id="comment_${comment.id}" class="media content-section">

	<img class="rounded-circle article-img" src="<c:url value="${comment.userImage}"/>" alt="no image">
	<div class="media-body">
		<div class="article-metadata">
			<div class="row">
				<p>${comment.userLogin}</p>
				<c:choose>
					<c:when test="${comment.userLogin eq currentUser.login or currentUser.role eq ADMIN}">
						<div class="justify-content-end">
							<button type="button" class="close" aria-label="Close"
									onclick="deleteComment('${CONTROLLER}', '${COMMAND}', '${DELETE_COMMENT}', '${COMMENT_ID}', '${comment.id}')">
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