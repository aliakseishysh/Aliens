<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
<title>Alien Profile</title>
<link rel="stylesheet" href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js'></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<script src='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js'></script>


<link href="<c:url value="${CSS_RAITING}"/>" rel="stylesheet">
<link href="<c:url value="${CSS_MAIN}"/>" rel="stylesheet">
<script type="text/javascript" src="<c:url value='${JS_RAITING}'/>"></script>
<script type="text/javascript" src="<c:url value='${JS_ALIEN_PROFILE}'/>"></script>

</head>
<body>
	<main role="main" class="container">
		<jsp:include page="${TEMPLATE_NAV_JSP}"/>
		<h1>Alien Profile</h1>
		<div class="content-section">
			<div class="media">
				<img id="alien-image" class="rounded-circle account-img"
					src="<c:url value="${alien.imageUrl}"/>" alt="no image">
				
				<div class="media-body">
					<h2 id="alien-name" class="account-heading">${alien.name}</h2>
					<p id="alien-small-description" class="h4">${alien.smallDescription}</p>
				</div>
				<c:choose>
					<c:when test="${currentUser.role == USER}">
						<jsp:include page="${TEMPLATE_ALIEN_RATING_JSP}"/>						
					</c:when>
				</c:choose>
			</div>
			<div class="content-section">
			<p class="border-bottom mb-4 h3">Alien Description</p>
				<p id="alien-big-description" class="h4">${alien.bigDescription}</p>
			</div>
			<c:choose>
				<c:when test="${currentUser.role == ADMIN}">
					<jsp:include page="${FORM_ALIEN_UPDATE_JSP}"></jsp:include>
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${currentUser.role == USER}">
					<jsp:include page="${FORM_NEW_COMMENT}"/>					
				</c:when>
				<c:when test="${currentUser.role == ADMIN}">
					<jsp:include page="${FORM_NEW_COMMENT}"/>
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${alienComments.size() == 0}">
					<div class="content-section">
						<fieldset class="form-group">
							<legend class="border-bottom mb-4">Comments</legend>
						</fieldset>
						<p>No comments...</p>
					</div>
				</c:when>
				<c:otherwise>
					<div class="content-section">
						<fieldset class="form-group">
							<legend class="border-bottom mb-4">Comments</legend>
						</fieldset>
						<div class="col-md-8" id="alien-comments">
							<c:forEach var="alienComment" items="${alienComments}">
								<c:set var="comment" value="${alienComment}" scope="request"/>
								<jsp:include page="${TEMPLATE_COMMENT_JSP}"/>
							</c:forEach>
						</div>
						<jsp:include page="${TEMPLATE_ALIEN_PROFILE_PAGINATION}"/>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</main>
</body>
</html>
