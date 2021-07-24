<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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