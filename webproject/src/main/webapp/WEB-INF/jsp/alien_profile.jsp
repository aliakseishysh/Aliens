<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<html>
<head>
<title>Alien Profile</title>
<link rel="stylesheet" href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js'></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<script src='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js'></script>
<link href="<c:url value="${CSS_RAITING}"/>" rel="stylesheet">
<link href="<c:url value="${CSS_MAIN}"/>" rel="stylesheet">
<script type="text/javascript" src="<c:url value='${JS_RAITING}'/>"></script>
</head>
<body>
	<main role="main" class="container">
		<jsp:include page="${NAV_JSP}"/>
		<h1>Alien Profile</h1>
		<div class="content-section">
			<div class="media">
				<img class="rounded-circle account-img"
					src="<c:url value="${alien.getImageUrl()}"/>" alt="no image">
				
				<div class="media-body">
					<h2 class="account-heading" id="alien-name" >${alien.getName()}</h2>
					<p class="h4">${alien.getSmallDescription()}</p>
				</div>
				<c:choose>
					<c:when test="${currentUser.getRole().getValue() == 'user'}">
						<div class="justify-content-end">
			    			<div class="row">
			            			<div class="stars" id="ratingStars">
			                		<form action="">
			                			<label class="control-label" for="star">Rating:</label>
										<div class="col-md-13">
											<input class="star star-5" id="star-5" type="radio" name="star" value="5" 
												   onclick="updateRating('${alien.getName()}', '${RATING_VALUE}', '${ALIEN_NAME}', '${CONTROLLER}', '${COMMAND}', '${UPDATE_RATING}', '5')"/> 
											<label class="star star-5" for="star-5"></label> 
											<input class="star star-4" id="star-4" type="radio" name="star" value="4" 
												   onclick="updateRating('${alien.getName()}', '${RATING_VALUE}', '${ALIEN_NAME}', '${CONTROLLER}', '${COMMAND}', '${UPDATE_RATING}', '4')"/> 
											<label class="star star-4" for="star-4"></label> 
											<input class="star star-3" id="star-3" type="radio" name="star" value="3" 
												   onclick="updateRating('${alien.getName()}', '${RATING_VALUE}', '${ALIEN_NAME}', '${CONTROLLER}', '${COMMAND}', '${UPDATE_RATING}', '3')"/> 
											<label class="star star-3" for="star-3"></label> 
											<input class="star star-2" id="star-2" type="radio" name="star" value="2"
											 	   onclick="updateRating('${alien.getName()}', '${RATING_VALUE}', '${ALIEN_NAME}', '${CONTROLLER}', '${COMMAND}', '${UPDATE_RATING}', '2')"/> 
											<label class="star star-2" for="star-2"></label> 
											<input class="star star-1" id="star-1" type="radio" name="star" value="1" 
												   onclick="updateRating('${alien.getName()}', '${RATING_VALUE}', '${ALIEN_NAME}', '${CONTROLLER}', '${COMMAND}', '${UPDATE_RATING}', '1')"/> 
											<label class="star star-1" for="star-1"></label>
			    						</div>
			                		</form>
			                		<c:set var="alienName" scope="page" value="${alien.getName()}"/>
			                		<script>
										var alienName = "${pageScope.alienName}";
										var ALIEN_NAME = "${sessionScope.ALIEN_NAME}";
										var controller = "${sessionScope.CONTROLLER}";
										var command = "${sessionScope.COMMAND}";
										var findUserRate = "${sessionScope.FIND_USER_RATE}";
										setRatingValue(alienName, ALIEN_NAME, controller, command, findUserRate);
									</script>
			           				</div>
			    			</div>
		    				<div class="row">
		    					<label class="control-label" for="average-rating">Average Rating:&nbsp;</label>
		    					<p class="" id="average-rating">"${averageRating}"</p>	
			    			</div>
						</div>
						
					</c:when>
				</c:choose>
			</div>
			
			<c:choose>
				<c:when test="${currentUser.getRole().getValue() == 'admin'}">
					<form method="POST" action="" enctype="multipart/form-data">
						<fieldset class="form-group">
							<legend class="border-bottom mb-4">Admin features</legend>
							<p>
								Admin features
							</p>
						</fieldset>
						<div class="form-group">
							<button class="btn btn-outline-info" type="submit">Update</button>
							<button class="btn btn-outline-info" type="submit">Delete</button>
						</div>
					</form>
				</c:when>
			</c:choose>
			<p class="border-bottom mb-4 h3">User features</p>
			<div class="media-body">
				<p class="h4">${alien.getBigDescription()}</p>
			</div>
			
		</div>
	</main>
</body>
</html>
