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