<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
<title>${TEXT[PAGE_ALIEN_PROFILE_TITLE]}</title>
<link rel="stylesheet" href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<link href="<c:url value="${CSS_RAITING}"/>" rel="stylesheet">
<link href="<c:url value="${CSS_MAIN}"/>" rel="stylesheet">
<link href="<c:url value="${CSS_CUSTOM_FILE}"/>" rel="stylesheet">

<script>
	var ALIEN_PROFILE_PAGE = `${OPEN_ALIEN_PROFILE_PAGE}`;
	var PROJECT_NAME = `${PROJECT_NAME}`;
	var CONTROLLER = `${CONTROLLER}`;
	var COMMAND = `${COMMAND}`;
	var UPDATE_ALIEN_INFO = `${UPDATE_ALIEN_INFO}`;
	var UPDATE_ALIEN_IMAGE = `${UPDATE_ALIEN_IMAGE}`;
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
	
	var STANDARD_ALIEN_NAME_FEEDBACK = `${STANDARD_ALIEN_NAME_FEEDBACK}`;
	var STANDARD_ALIEN_SMALL_DESCRIPTION_FEEDBACK = `${STANDARD_ALIEN_SMALL_DESCRIPTION_FEEDBACK}`;
	var STANDARD_ALIEN_FULL_DESCRIPTION_FEEDBACK = `${STANDARD_ALIEN_FULL_DESCRIPTION_FEEDBACK}`;
	var STANDARD_IMAGE_FEEDBACK = `${STANDARD_IMAGE_FEEDBACK}`;
	var ALIEN_NAME_STATUS = `${ALIEN_NAME_STATUS}`;
	var ALIEN_SMALL_DESCRIPTION_STATUS = `${ALIEN_SMALL_DESCRIPTION_STATUS}`;
	var ALIEN_FULL_DESCRIPTION_STATUS = `${ALIEN_FULL_DESCRIPTION_STATUS}`;
	var IMAGE_STATUS = `${IMAGE_STATUS}`;
	var ALIEN_NAME_FEEDBACK = `${ALIEN_NAME_FEEDBACK}`;
	var ALIEN_SMALL_DESCRIPTION_FEEDBACK = `${ALIEN_SMALL_DESCRIPTION_FEEDBACK}`;
	var ALIEN_FULL_DESCRIPTION_FEEDBACK = `${ALIEN_FULL_DESCRIPTION_FEEDBACK}`;
	var IMAGE_FEEDBACK = `${IMAGE_FEEDBACK}`;

	var STANDARD_COMMENT_FEEDBACK = `${STANDARD_COMMENT_FEEDBACK}`;
	var COMMENT_STATUS = `${COMMENT_STATUS}`;
	var COMMENT_FEEDBACK = `${COMMENT_FEEDBACK}`;

	var OPEN_ALIEN_PROFILE_PAGE = `${OPEN_ALIEN_PROFILE_PAGE}`;
	var PAGINATION_PAGE_TO_GO = OPEN_ALIEN_PROFILE_PAGE;
	var PAGE = `${PAGE}`;

	var ALIEN_IMAGES = `${alienImages}`;

	var FIND_USER_RATE = `${FIND_USER_RATE}`;
	var ALIEN_NAME = `${ALIEN_NAME}`;
	var alienId = `${alien.id}`;
	var alienName = `${alien.name}`;
	var RATING_VALUE = `${RATING_VALUE}`;
	var UPDATE_RATING = `${UPDATE_RATING}`;
</script>
<script type="module" src="<c:url value='${JS_BUNDLE}'/>"></script>
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
						<jsp:include page="${TEMPLATE_PAGINATION_JSP}"/>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</main>
</body>
</html>
