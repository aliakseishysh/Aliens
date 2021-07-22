function setRatingValue() {
    let ratingInput = $("input[type='radio']");
    let data = {};
    data[ALIEN_NAME] = alienName;
    $.ajax({
        url: CONTROLLER + "?" + COMMAND + "=" + FIND_USER_RATE,
        data: data,
        success: function(userRating) {
            if (userRating > 0) {
                let star = ratingInput[5-userRating];
                star.checked = true;
            }
        },
        error: function() {
            // TODO show error
        }
    });
};

function updateRating(ratingValue) {
    let data = {};
    data[RATING_VALUE] = ratingValue;
    data[ALIEN_NAME] = alienName;
    $.ajax({
        url: CONTROLLER + "?" + COMMAND + "=" + UPDATE_RATING,
        data: data,
        success: function(averageRate) {
            // let averageRatingText = TEMPLATE_ALIEN_RATING_AVERAGE_RATING;
            let averageRatingElement = document.getElementById("average-rating");
            averageRatingElement.innerText = averageRate;
        },
        error: function() {
            // TODO show error
        }
    });
};

export const rating = {
    setRatingValue: setRatingValue,
    updateRating: updateRating
}

