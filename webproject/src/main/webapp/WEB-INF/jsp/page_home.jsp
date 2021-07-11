<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${TEXT[PAGE_HOME_TITLE]}</title>
<!-- 
<script src='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js'></script>
 -->
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js'></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<script type="text/javascript" src="<c:url value='${JS_HOME}'/>"></script>

<link href="<c:url value="${CSS_MAIN}"/>" rel="stylesheet">
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
					<jsp:include page="${TEMPLATE_PAGE_HOME_PAGINATION}"/>
				</div>
			</div>
			<div class="col-md-4">
				<div class="content-section">
					<h3>${TEXT[PAGE_HOME_ALIENBAR]}</h3>
					<p class='text-muted'>${TEXT[PAGE_HOME_CONTACT_ADMIN]}
					<ul class="list-group">
						<li class="list-group-item list-group-item-light">${TEXT[PAGE_HOME_ADMIN_EMAIL]}</li>
						<li class="list-group-item list-group-item-light">${TEXT[PAGE_HOME_YEAR]}</li>
					</ul>
					</p>
				</div>
			</div>
		</div>
	</main>

</body>
</html>