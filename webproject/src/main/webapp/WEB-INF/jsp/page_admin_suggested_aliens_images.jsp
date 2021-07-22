<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<link href="<c:url value="${CSS_MAIN}"/>" rel="stylesheet">
<link href="<c:url value="${CSS_SUGGESTED_IMAGE}"/>" rel="stylesheet">
<title>${TEXT[PAGE_ADMIN_SUGGESTED_ALIENS_IMAGES_TITLE]}</title>
<script>
	var SUGGESTED_ALIENS_IMAGES = `${OPEN_ADMIN_SUGGESTED_ALIENS_IMAGES_PAGE}`;
	var PROJECT_NAME = `${PROJECT_NAME}`;
	var CONTROLLER = `${CONTROLLER}`;
	var COMMAND = `${COMMAND}`;

	var OPEN_ADMIN_SUGGESTED_ALIENS_IMAGES_PAGE = `${OPEN_ADMIN_SUGGESTED_ALIENS_IMAGES_PAGE}`;
	var PAGINATION_PAGE_TO_GO = OPEN_ADMIN_SUGGESTED_ALIENS_IMAGES_PAGE;
	var PAGE = `${PAGE}`;
	
	var ADMIN_APPROVE_ALIEN_IMAGE = `${ADMIN_APPROVE_ALIEN_IMAGE}`;
	var ADMIN_DECLINE_ALIEN_IMAGE = `${ADMIN_DECLINE_ALIEN_IMAGE}`;

	var IMAGE = `${IMAGE}`;	
	var ALIEN_ID = `${ALIEN_ID}`;	
</script>
<script type="module" src="<c:url value='${JS_BUNDLE}'/>"></script>
<body>
	<main role="main" class="container">
		<jsp:include page="${TEMPLATE_NAV_JSP}" />
		<h1>${TEXT[PAGE_ADMIN_SUGGESTED_ALIENS_IMAGES_H1]}</h1>
		<div class="row">
			<div class="col-md-12">
				<div id="aliens-main-content">
					<c:choose>
						<c:when test="${aliensList.size() == 0}">
						<div class="content-section">
							<fieldset class="form-group">
								<legend class="border-bottom mb-4">${TEXT[PAGE_ADMIN_SUGGESTED_ALIENS_IMAGES_H1]}</legend>
							</fieldset>
								<p>No suggested images (TODO internalization)</p>
						</div>
						</c:when>
						<c:otherwise>
							<c:forEach var="entry" items="${aliensList}">
								<c:set var="alien" value="${entry}" scope="request" />
								<article class="content-section" >
									<div class="suggested-alien-image-container">
										<div class="col-md-8">
										<div class="row">
											<a class="mr-2" href="<c:url value="/${CONTROLLER}?${COMMAND}=${OPEN_ALIEN_PROFILE_PAGE}&${ALIEN_ID}=${alien.id}"/>">${alien.name}</a>
										</div>
										<div class="row">
											<div class="content-section">
												<img class="suggested-alien-image"
													src="<c:url value="${alien.imageUrl}"/>" alt="${TEXT[PAGE_ALIEN_PROFILE_ALIEN_IMAGE_ALT]}">
											</div>
										</div>
										<div class="row">
											<p hidden>${alien.id}</p>
											<button class="btn btn-outline-info"
												    name="approve-alien-image"
													type="button">
													${TEXT[PAGE_ADMIN_SUGGESTED_APPROVE_BUTTON]}
											</button>
											<button class="btn btn-outline-danger"
													name="decline-alien-image"
													type="button">
													${TEXT[PAGE_ADMIN_SUGGESTED_DECLINE_BUTTON]}
											</button>
										</div>						
									</div>
									</div>
									
								</article>
							</c:forEach>
							<jsp:include page="${TEMPLATE_PAGINATION_JSP}"/>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</main>

</body>
</html>