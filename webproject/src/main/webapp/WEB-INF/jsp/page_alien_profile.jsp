<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
<title>${TEXT[PAGE_ALIEN_PROFILE_TITLE]}</title>
<link rel="stylesheet" href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>
<!-- 
<script src='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js'></script>
 -->
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js'></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">


<link href="<c:url value="${CSS_RAITING}"/>" rel="stylesheet">
<link href="<c:url value="${CSS_MAIN}"/>" rel="stylesheet">
<link href="<c:url value="${CSS_USER_PROFILE}"/>" rel="stylesheet">

<script>
	var PROJECT_NAME = `${PROJECT_NAME}`;
	var CONTROLLER = `${CONTROLLER}`;
	var COMMAND = `${COMMAND}`;
	var UPDATE_ALIEN = `${UPDATE_ALIEN}`;
	var ALIEN_NAME = `${ALIEN_NAME}`;
	var ALIEN_SMALL_DESCRIPTION = `${ALIEN_SMALL_DESCRIPTION}`;
	var ALIEN_FULL_DESCRIPTION = `${ALIEN_FULL_DESCRIPTION}`;
	var ALIEN_NEW_IMAGE = `${ALIEN_NEW_IMAGE}`;
	var LOAD_ALIEN_UPDATE_FORM = `${LOAD_ALIEN_UPDATE_FORM}`;
	var RESTORE_PASSWORD = `${RESTORE_PASSWORD}`;
	var ADD_NEW_COMMENT = `${ADD_NEW_COMMENT}`;
	var NEW_COMMENT = `${NEW_COMMENT}`;
	var DELETE_COMMENT = `${DELETE_COMMENT}`;
	var USER_ID = `${USER_ID}`;
	var ALIEN_ID = `${ALIEN_ID}`;
	
	var ADD_NEW_UPDATE_ALIEN_STANDARD_ALIEN_NAME_FEEDBACK = `${ADD_NEW_UPDATE_ALIEN_STANDARD_ALIEN_NAME_FEEDBACK}`;
	var ADD_NEW_UPDATE_ALIEN_STANDARD_ALIEN_SMALL_DESCRIPTION_FEEDBACK = `${ADD_NEW_UPDATE_ALIEN_STANDARD_ALIEN_SMALL_DESCRIPTION_FEEDBACK}`;
	var ADD_NEW_UPDATE_ALIEN_STANDARD_ALIEN_FULL_DESCRIPTION_FEEDBACK = `${ADD_NEW_UPDATE_ALIEN_STANDARD_ALIEN_FULL_DESCRIPTION_FEEDBACK}`;
	var ADD_NEW_UPDATE_ALIEN_STANDARD_ALIEN_IMAGE_FEEDBACK = `${ADD_NEW_UPDATE_ALIEN_STANDARD_ALIEN_IMAGE_FEEDBACK}`;
	var ADD_NEW_UPDATE_ALIEN_ALIEN_NAME_STATUS = `${ADD_NEW_UPDATE_ALIEN_ALIEN_NAME_STATUS}`;
	var ADD_NEW_UPDATE_ALIEN_ALIEN_SMALL_DESCRIPTION_STATUS = `${ADD_NEW_UPDATE_ALIEN_ALIEN_SMALL_DESCRIPTION_STATUS}`;
	var ADD_NEW_UPDATE_ALIEN_ALIEN_FULL_DESCRIPTION_STATUS = `${ADD_NEW_UPDATE_ALIEN_ALIEN_FULL_DESCRIPTION_STATUS}`;
	var ADD_NEW_UPDATE_ALIEN_ALIEN_IMAGE_STATUS = `${ADD_NEW_UPDATE_ALIEN_ALIEN_IMAGE_STATUS}`;
	var ADD_NEW_UPDATE_ALIEN_RESULT_INFO_ALIEN_NAME_FEEDBACK = `${ADD_NEW_UPDATE_ALIEN_RESULT_INFO_ALIEN_NAME_FEEDBACK}`;
	var ADD_NEW_UPDATE_ALIEN_RESULT_INFO_ALIEN_SMALL_DESCRIPTION_FEEDBACK = `${ADD_NEW_UPDATE_ALIEN_RESULT_INFO_ALIEN_SMALL_DESCRIPTION_FEEDBACK}`;
	var ADD_NEW_UPDATE_ALIEN_RESULT_INFO_ALIEN_FULL_DESCRIPTION_FEEDBACK = `${ADD_NEW_UPDATE_ALIEN_RESULT_INFO_ALIEN_FULL_DESCRIPTION_FEEDBACK}`;
	var ADD_NEW_UPDATE_ALIEN_RESULT_INFO_ALIEN_IMAGE_FEEDBACK = `${ADD_NEW_UPDATE_ALIEN_RESULT_INFO_ALIEN_IMAGE_FEEDBACK}`;

	var ADD_NEW_COMMENT_STANDARD_COMMENT_FEEDBACK = `${ADD_NEW_COMMENT_STANDARD_COMMENT_FEEDBACK}`;
	var ADD_NEW_COMMENT_COMMENT_STATUS = `${ADD_NEW_COMMENT_COMMENT_STATUS}`;
	var ADD_NEW_COMMENT_RESULT_INFO_COMMENT_FEEDBACK = `${ADD_NEW_COMMENT_RESULT_INFO_COMMENT_FEEDBACK}`;
	
</script>


<script type="text/javascript" src="<c:url value='${JS_RAITING}'/>"></script>
<script type="text/javascript" src="<c:url value='${JS_ALIEN_PROFILE}'/>"></script>

</head>
<body>
	<main role="main" class="container">
		<jsp:include page="${TEMPLATE_NAV_JSP}"/>
		<h1>${TEXT[PAGE_ALIEN_PROFILE_H1]}</h1>
		<div class="content-section">
			<div class="media">
				<img id="alien-image" class="rounded-circle account-img"
					src="<c:url value="${alien.imageUrl}"/>" alt="${TEXT[PAGE_ALIEN_PROFILE_ALIEN_IMAGE_ALT]}">
				
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
			<p class="border-bottom mb-4 h3">${TEXT[PAGE_ALIEN_PROFILE_ALIEN_DESCRIPTION]}</p>
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
						<jsp:include page="${TEMPLATE_ALIEN_PROFILE_PAGINATION}"/>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</main>
</body>
</html>
