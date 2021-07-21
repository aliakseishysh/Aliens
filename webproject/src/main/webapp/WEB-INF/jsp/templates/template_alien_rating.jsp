<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
	<link rel="stylesheet" href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>
	<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js'></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<!-- 
<script src='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js'></script>
 -->
<link href="<c:url value="${CSS_RAITING}"/>" rel="stylesheet">
<link href="<c:url value="${CSS_MAIN}"/>" rel="stylesheet">
<script>
	var CONTROLLER = `${CONTROLLER}`;
	var COMMAND = `${COMMAND}`;
	var FIND_USER_RATE = `${FIND_USER_RATE}`;
	var ALIEN_NAME = `${ALIEN_NAME}`;
	var alienId = `${alien.id}`;
	var alienName = `${alien.name}`;
	var RATING_VALUE = `${RATING_VALUE}`;
	var UPDATE_RATING = `${UPDATE_RATING}`;
	var COMMAND = `${COMMAND}`;
</script>
<script type="text/javascript" src="<c:url value='${JS_RAITING}'/>"></script>
</head>

<div class="justify-content-end">
	<div class="row row-margin-average-rating">
		<h3>${TEXT[TEMPLATE_ALIEN_RATING_AVERAGE_RATING]}<span id="average-rating">${averageRating}</span></h3>	
	</div>
	<div class="flex-row-reverse">
     	<div class="stars" id="ratingStars">
         	<form action="">
				<div class="col-md-12">
					<input class="star star-5" id="star-5" type="radio" name="star" value="5" 
	   					   onclick="updateRating('5')"/> 
					<label class="star star-5" for="star-5"></label> 
					<input class="star star-4" id="star-4" type="radio" name="star" value="4" 
						   onclick="updateRating('4')"/> 
					<label class="star star-4" for="star-4"></label> 
					<input class="star star-3" id="star-3" type="radio" name="star" value="3" 
						   onclick="updateRating('3')"/> 
					<label class="star star-3" for="star-3"></label> 
					<input class="star star-2" id="star-2" type="radio" name="star" value="2"
					 	   onclick="updateRating('2')"/> 
					<label class="star star-2" for="star-2"></label> 
					<input class="star star-1" id="star-1" type="radio" name="star" value="1" 
						   onclick="updateRating('1')"/> 
					<label class="star star-1" for="star-1"></label>
				</div>
       		</form>
		</div>
	</div>
</div>
