function setRatingValue() {
    var ratingInput = $("input[type='radio']");
    var alienName = $("#alien-name").text();
    $.ajax({
        url: "controller?command=FIND_USER_RATE",
        data: {"alienName": alienName},
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
    // var star1 = ratingInput[4];
    // var star2 = ratingInput[3];
    // var star3 = ratingInput[2];
    // var star4 = ratingInput[1];
    // var star5 = ratingInput[0];
    // $.ajax({

    // })
};

document.addEventListener("DOMContentLoaded",() => {
    if(document.getElementById("ratingStars") != null) {
      setRatingValue();
    }
  });


$(document).ready(function() {
	$("input[type='radio']").click(function() {
		var ratingValue = $("input[type='radio']:checked").val();
        var alienName = $("#alien-name").text();
        $.ajax({
            url: "controller?command=UPDATE_RATING",
            data: {"ratingValue": ratingValue, "alienName": alienName},
            success: function(averageRate) {
                document.getElementById("average-rating").innerHTML = '"' + averageRate + '"';
            },
            error: function() {
                // TODO show error
            }
        });
	});
});