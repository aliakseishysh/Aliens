<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js'></script>

<script src='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js'></script>

<script type="text/javascript" src="<c:url value='${JS_HOME}'/>"></script>

<nav aria-label="Page Navigation">
<ul class="pagination">
<c:choose>
	<c:when test="${currentHomePage eq 1}">
		<li class="page-item"
			onclick="previousHomePage(
			'${CONTROLLER}',
			'${COMMAND}',
		 	'${OPEN_HOME_PAGE}',
			'${PAGE}',
			'${currentHomePage}'
			)">
			<a class="page-link" href="#" aria-label="Previous">
				<span aria-hidden="true">&laquo;</span>
			</a>
		</li>
	</c:when>
	<c:otherwise>
		<li class="page-item"
			onclick="previousHomePage(
			'${CONTROLLER}',
			'${COMMAND}',
		 	'${OPEN_HOME_PAGE}',
			'${PAGE}',
			'${currentHomePage - 1}'
			)">
			<a class="page-link" href="#" aria-label="Previous">
				<span aria-hidden="true">&laquo;</span>
			</a>
		</li>
	</c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${pagesCount eq currentHomePage}">
		<li class="page-item"
			onclick="nextHomePage(
			'${CONTROLLER}',
			'${COMMAND}',
			'${OPEN_HOME_PAGE}',
			'${PAGE}',
			'${currentHomePage}'
			)">
			<a class="page-link" href="#"aria-label="Next">
				<span aria-hidden="true">&raquo;</span>
			</a>
		</li>
</c:when>
	<c:otherwise>
		<li class="page-item"
			onclick="nextHomePage(
			'${CONTROLLER}',
			'${COMMAND}',
			'${OPEN_HOME_PAGE}',
			'${PAGE}',
			'${currentHomePage + 1}'
			)">
			<a class="page-link" href="#"aria-label="Next">
				<span aria-hidden="true">&raquo;</span>
			</a>
		</li>
	</c:otherwise>
</c:choose>
</ul>
</nav>