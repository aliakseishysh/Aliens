<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="alien-tag" uri="aliens" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
<title>${TEXT[PAGE_ALIEN_PROFILE_TITLE]}</title>
<alien-tag:css />
</head>
<body>
	<main role="main" class="container">
		<span id="currentUserId" hidden>${currentUser.id}</span>
		<jsp:include page="${TEMPLATE_NAV_JSP}"/>
		<h1>${TEXT[PAGE_ALIEN_PROFILE_H1]}</h1>
		<div class="content-section">
		    <p id="alien-id-hidden" hidden>${alien.id}</p>
			<jsp:include page="${TEMPLATE_ALIEN_JSP}"/>	
			<jsp:include page="${TEMPLATE_CAROUSEL}"/>	
			<c:choose>
				<c:when test="${currentUser.role == ADMIN}">
					<jsp:include page="${FORM_ALIEN_UPDATE_INFO_JSP}"/>
					<jsp:include page="${FORM_ALIEN_UPDATE_IMAGE_JSP}"/>
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${currentUser.role == USER or currentUser.role == ADMIN}">
					<jsp:include page="${FORM_NEW_COMMENT}"/>					
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${alienComments.size() == 0}">
					<div class="content-section">
						<fieldset class="form-group">
							<legend class="border-bottom mb-4">${TEXT[PAGE_ALIEN_PROFILE_COMMENTS]}</legend>
						</fieldset>
						<p>${TEXT[PAGE_ALIEN_PROFILE_NO_COMMENTS]}</p>
					</div>
				</c:when>
				<c:otherwise>
					<div class="content-section">
						<fieldset class="form-group">
							<legend class="border-bottom mb-4">${TEXT[PAGE_ALIEN_PROFILE_COMMENTS]}</legend>
						</fieldset>
						<div class="col-md-8" id="alien-comments">
							<c:forEach var="alienComment" items="${alienComments}">
								<c:set var="comment" value="${alienComment}" scope="request"/>
								<jsp:include page="${TEMPLATE_COMMENT_JSP}"/>
							</c:forEach>
						</div>
						<jsp:include page="${TEMPLATE_PAGINATION_JSP}"/>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</main>
	<alien-tag:variables />
	<alien-tag:js />
</body>
</html>
