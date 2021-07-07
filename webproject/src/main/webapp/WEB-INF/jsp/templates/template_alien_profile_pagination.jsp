<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<link rel="stylesheet" href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js'></script>
<script src='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js'></script>

<script>
	var CONTROLLER = `${CONTROLLER}`;
	var COMMAND = `${COMMAND}`;
	var OPEN_ALIEN_PROFILE_PAGE = `${OPEN_ALIEN_PROFILE_PAGE}`;
	var PAGE = `${PAGE}`;
	var ALIEN_ID = `${ALIEN_ID}`;
</script>

<script type="text/javascript" src="<c:url value='${JS_ALIEN_PROFILE}'/>"></script>

<nav aria-label="Page Navigation">
<ul class="pagination">
<c:choose>
	<c:when test="${currentCommentPage eq 1}">
		<li class="page-item"
			onclick="previousCommentPage(`${alien.id}`, `${currentCommentPage}`)">
			<a class="page-link" href="#" aria-label="Previous">
				<span aria-hidden="true">&laquo;</span>
			</a>
		</li>
	</c:when>
	<c:otherwise>
		<li class="page-item"
			onclick="previousCommentPage(`${alien.id}`, `${currentCommentPage - 1}`)">
			<a class="page-link" href="#" aria-label="Previous">
				<span aria-hidden="true">&laquo;</span>
			</a>
		</li>
	</c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${pagesCount eq currentCommentPage}">
		<li class="page-item"
			onclick="nextCommentPage(`${alien.id}`, `${currentCommentPage}`)">
			<a class="page-link" href="#" aria-label="Previous">
				<span aria-hidden="true">&raquo;</span>
			</a>
		</li>
	</c:when>
	<c:otherwise>
		<li class="page-item"
			onclick="nextCommentPage(`${alien.id}`, `${currentCommentPage + 1}`)">
			<a class="page-link" href="#" aria-label="Previous">
				<span aria-hidden="true">&raquo;</span>
			</a>
		</li>
	</c:otherwise>
</c:choose>
</ul>
</nav>