<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<script
	src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js'></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<script src='https://pagination.js.org/dist/2.1.5/pagination.min.js'></script>
<script type="text/javascript" src="<c:url value='${JS_HOME}'/>"></script>

<link href="<c:url value="${CSS_MAIN}"/>" rel="stylesheet">
<body>
	<main role="main" class="container">
		<jsp:include page="${TEMPLATE_NAV_JSP}" />
		<h1>Home Page</h1>


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
					<h3>Alienbar</h3>
					<p class='text-muted'>Some features
					<ul class="list-group">
						<li class="list-group-item list-group-item-light">Announcements</li>
						<li class="list-group-item list-group-item-light">News</li>
						<li class="list-group-item list-group-item-light">Contact
							admin</li>
						<li class="list-group-item list-group-item-light">etc</li>
					</ul>
					</p>
				</div>
			</div>
		</div>
	</main>

</body>
</html>