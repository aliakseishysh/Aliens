<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
	<link rel="stylesheet" href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>
	<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js'></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<script src='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js'></script>

	
	
	<link href="<c:url value="${CSS_RAITING}"/>" rel="stylesheet">
	<link href="<c:url value="${CSS_MAIN}"/>" rel="stylesheet">
	<script type="text/javascript" src="<c:url value='${JS_RAITING}'/>"></script>
</head>

<div class="justify-content-end">
	<div class="row">
		<p class="" id="average-rating">Average Rating:&nbsp;"${averageRating}"</p>	
	</div>
	<div class="row">
     	<div class="stars" id="ratingStars">
         	<form action="">
				<div class="col-md-12">
					<input class="star star-5" id="star-5" type="radio" name="star" value="5" 
	   					   onclick="updateRating('${alien.name}', '${RATING_VALUE}', '${ALIEN_NAME}', '${CONTROLLER}', '${COMMAND}', '${UPDATE_RATING}', '5')"/> 
					<label class="star star-5" for="star-5"></label> 
					<input class="star star-4" id="star-4" type="radio" name="star" value="4" 
						   onclick="updateRating('${alien.name}', '${RATING_VALUE}', '${ALIEN_NAME}', '${CONTROLLER}', '${COMMAND}', '${UPDATE_RATING}', '4')"/> 
					<label class="star star-4" for="star-4"></label> 
					<input class="star star-3" id="star-3" type="radio" name="star" value="3" 
						   onclick="updateRating('${alien.name}', '${RATING_VALUE}', '${ALIEN_NAME}', '${CONTROLLER}', '${COMMAND}', '${UPDATE_RATING}', '3')"/> 
					<label class="star star-3" for="star-3"></label> 
					<input class="star star-2" id="star-2" type="radio" name="star" value="2"
					 	   onclick="updateRating('${alien.name}', '${RATING_VALUE}', '${ALIEN_NAME}', '${CONTROLLER}', '${COMMAND}', '${UPDATE_RATING}', '2')"/> 
					<label class="star star-2" for="star-2"></label> 
					<input class="star star-1" id="star-1" type="radio" name="star" value="1" 
						   onclick="updateRating('${alien.name}', '${RATING_VALUE}', '${ALIEN_NAME}', '${CONTROLLER}', '${COMMAND}', '${UPDATE_RATING}', '1')"/> 
					<label class="star star-1" for="star-1"></label>
				</div>
       		</form>
       		<c:set var="alienName" scope="page" value="${alien.name}"/>
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
</div>
