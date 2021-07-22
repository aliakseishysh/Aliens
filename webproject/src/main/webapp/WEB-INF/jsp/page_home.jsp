<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${TEXT[PAGE_HOME_TITLE]}</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<link href="<c:url value="${CSS_MAIN}"/>" rel="stylesheet">
 <script>
	var HOME_PAGE = `${OPEN_HOME_PAGE}`;
	var PROJECT_NAME = `${PROJECT_NAME}`;
	var CONTROLLER = `${CONTROLLER}`;
	var COMMAND = `${COMMAND}`;

	var OPEN_HOME_PAGE = `${OPEN_HOME_PAGE}`;
	var PAGINATION_PAGE_TO_GO = OPEN_HOME_PAGE;
	var PAGE = `${PAGE}`;
</script>
<script type="module" src="<c:url value='${JS_BUNDLE}'/>"></script>
<body>
	<main role="main" class="container">
		<jsp:include page="${TEMPLATE_NAV_JSP}" />
		<h1>${TEXT[PAGE_HOME_H1]}</h1>

		<div class="row">
			<div class="col-md-8">
				<div id="aliens-main-content">
					<c:forEach var="entry" items="${aliensList}">
						<c:set var="alien" value="${entry}" scope="request" />
						<jsp:include page="${TEMPLATE_POST_JSP}" />
					</c:forEach>
					<jsp:include page="${TEMPLATE_PAGINATION_JSP}"/>
				</div>
			</div>
			<jsp:include page="${TEMPLATE_ALIENBAR_JSP}" />
		</div>
	</main>

</body>
</html>