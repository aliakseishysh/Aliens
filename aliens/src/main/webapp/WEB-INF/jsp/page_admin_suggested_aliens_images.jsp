<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="alien-tag" uri="aliens" %>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<alien-tag:css />
<title>${TEXT[PAGE_ADMIN_SUGGESTED_ALIENS_IMAGES_TITLE]}</title>
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
								<p>${TEXT[PAGE_ADMIN_SUGGESTED_ALIENS_NO_IMAGES]}</p>
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
	<alien-tag:variables />
	<alien-tag:js />
</body>
</html>