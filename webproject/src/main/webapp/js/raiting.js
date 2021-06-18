function setRatingValue(alienName, ALIEN_NAME, CONTROLLER, COMMAND, FIND_USER_RATE) {
    var ratingInput = $("input[type='radio']");
    var data = {};
    data[ALIEN_NAME] = alienName;
    $.ajax({
        url: CONTROLLER + "?" + COMMAND + "=" + FIND_USER_RATE,
        data: data,
        success: function(userRating) {
            if (userRating > 0) {
                var star = ratingInput[5-userRating];
                star.checked = true;
            }
        },
        error: function() {
            // TODO show error
        }
    });
};

function updateRating(alienName, RATING_VALUE, ALIEN_NAME, CONTROLLER, COMMAND, UPDATE_RATING, ratingValue) {
    var data = {};
    data[RATING_VALUE] = ratingValue;
    data[ALIEN_NAME] = alienName;
    $.ajax({
        url: CONTROLLER + "?" + COMMAND + "=" + UPDATE_RATING,
        data: data,
        success: function(averageRate) {
            document.getElementById("average-rating").innerHTML = '"' + averageRate + '"';
        },
        error: function() {
            // TODO show error
        }
    });
};

// document.addEventListener("DOMContentLoaded",() => {
//     if(document.getElementById("ratingStars") != null) {
//       setRatingValue();
//     }
//   });


// $(document).ready(function() {
	
// });