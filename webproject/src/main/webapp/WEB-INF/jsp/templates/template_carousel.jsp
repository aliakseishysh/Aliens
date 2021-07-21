<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- TODO to constant -->
<link href="<c:url value="${CSS_CAROUSEL}"/>" rel="stylesheet">
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js'></script>
<script>
	var CONTROLLER = `${CONTROLLER}`;
	var COMMAND = `${COMMAND}`;
	
	var ALIEN_IMAGES = `${alienImages}`;
</script>
<script type="text/javascript" src="<c:url value='${JS_CAROUSEL}'/>"></script>
<article class="content-section">
<div id="aliens-carousel" class="carousel slide" data-interval="false">
  <div id="aliens-carousel-inner" class="carousel-inner">

  </div>
  <a class="carousel-control-prev" href="#aliens-carousel" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" href="#aliens-carousel" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
</article>