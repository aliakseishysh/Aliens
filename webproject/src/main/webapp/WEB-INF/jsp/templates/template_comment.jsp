<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="<c:url value="${CSS_COMMENT}"/>" rel="stylesheet">
<article id="comment_${comment.id}" class="media content-section">
	<img class="rounded-circle article-img" src="<c:url value="${comment.userImage}"/>" alt="${TEXT[TEMPLATE_COMMENT_IMAGE_ALT]}">
	<div class="media-body">
		<div class="article-metadata">
			<div class="row row-comment">
				<p>${comment.userLogin}</p>
				<c:choose>
					<c:when test="${comment.userLogin eq currentUser.login or currentUser.role eq ADMIN}">
						<div class="justify-content-end-comment">
							<span hidden>${comment.id}</span>
							<button type="button" class="close" name="delete-comment-button" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
					</c:when>
				</c:choose>
			</div>

		</div>
		<p class="article-content text-break">${comment.comment}</p>
	</div>
</article>