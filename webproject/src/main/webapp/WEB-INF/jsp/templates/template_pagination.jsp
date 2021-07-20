<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<nav aria-label="Page Navigation">
<ul class="pagination">
<c:choose>
	<c:when test="${currentPage eq 1}">
		<li class="page-item"
			onclick="pagination.changePage(`${currentPage}`)">
			<a class="page-link" href="#" aria-label="Previous">
				<span aria-hidden="true">&laquo;</span>
			</a>
		</li>
	</c:when>
	<c:otherwise>
		<li class="page-item"
			onclick="pagination.changePage(`${currentPage - 1}`)">
			<a class="page-link" href="#" aria-label="Previous">
				<span aria-hidden="true">&laquo;</span>
			</a>
		</li>
	</c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${pagesCount eq currentPage}">
		<li class="page-item"
			onclick="pagination.changePage(`${currentPage}`)">
			<a class="page-link" href="#" aria-label="Previous">
				<span aria-hidden="true">&raquo;</span>
			</a>
		</li>
	</c:when>
	<c:otherwise>
		<li class="page-item"
			onclick="pagination.changePage(`${currentPage + 1}`)">
			<a class="page-link" href="#" aria-label="Previous">
				<span aria-hidden="true">&raquo;</span>
			</a>
		</li>
	</c:otherwise>
</c:choose>
</ul>
</nav>