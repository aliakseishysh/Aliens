<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${TEXT[PAGE_ADMIN_SUGGESTED_ALIENS_TITLE]}</title>
<script>
	var PROJECT_NAME = `${PROJECT_NAME}`;
	var CONTROLLER = `${CONTROLLER}`;
	var COMMAND = `${COMMAND}`;

	var OPEN_ADMIN_SUGGESTED_ALIENS_PAGE = `${OPEN_ADMIN_SUGGESTED_ALIENS_PAGE}`;
	var PAGINATION_PAGE_TO_GO = OPEN_ADMIN_SUGGESTED_ALIENS_PAGE;
	var PAGE = `${PAGE}`;
	
	var ADMIN_APPROVE_ALIEN = `${ADMIN_APPROVE_ALIEN}`;
	var ADMIN_DECLINE_ALIEN = `${ADMIN_DECLINE_ALIEN}`;
	var ALIEN_STATUS = `${ALIEN_STATUS}`;
	var ALIEN_ID = `${ALIEN_ID}`;	
</script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js'></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<script type="text/javascript" src="<c:url value='${JS_SUGGESTED_ALIENS}'/>"></script>
<script type="text/javascript" src="<c:url value='${JS_PAGINATION}'/>"></script>

<link href="<c:url value="${CSS_MAIN}"/>" rel="stylesheet">
<body>
	<main role="main" class="container">
		<jsp:include page="${TEMPLATE_NAV_JSP}" />
		<h1>${TEXT[PAGE_ADMIN_SUGGESTED_ALIENS_H1]}</h1>
		<div class="row">
			<div class="col-md-12">
				<div id="aliens-main-content">
					<c:choose>
						<c:when test="${aliensList.size() == 0 }">
							<div class="content-section">
							<fieldset class="form-group">
								<legend class="border-bottom mb-4">${TEXT[PAGE_ADMIN_SUGGESTED_ALIENS_H1]}</legend>
							</fieldset>
								<p>No suggested aliens (TODO internalization)</p>
						</div>
						</c:when>
						<c:otherwise>
								<c:forEach var="entry" items="${aliensList}">
									<c:set var="alien" value="${entry}" scope="request" />
									<div class="content-section">
										<p hidden>${alien.id}</p>
										<jsp:include page="${TEMPLATE_ALIEN_JSP}"/>
										<button class="btn btn-outline-info"
												name="approve-alien"
												type="submit">
												${TEXT[PAGE_ADMIN_SUGGESTED_APPROVE_BUTTON]}
										</button>
										<button class="btn btn-outline-danger" 
												name="decline-alien"
												type="submit">
												${TEXT[PAGE_ADMIN_SUGGESTED_DECLINE_BUTTON]}
										</button>
									</div>
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