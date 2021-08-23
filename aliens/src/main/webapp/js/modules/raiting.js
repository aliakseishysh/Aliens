import { changeLocationIfUndefined } from "./util.js";

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
        error: function (jqXHR, textStatus, errorThrown) {
            if(!changeLocationIfUndefined(jqXHR)) {
                alert(jqXHR.status + " " + textStatus + " " + errorThrown);
            }
        }
    });
}

function updateRating(ratingValue) {
    let data = {};
    data[RATING_VALUE] = ratingValue;
    data[ALIEN_NAME] = alienName;
    $.ajax({
        url: CONTROLLER + "?" + COMMAND + "=" + UPDATE_RATING,
        data: data,
        success: function(averageRate) {
            let averageRatingElement = document.getElementById("average-rating");
            averageRatingElement.innerText = averageRate;
        },
        error: function (jqXHR, textStatus, errorThrown) {
            if(!changeLocationIfUndefined(jqXHR)) {
                alert(jqXHR.status + " " + textStatus + " " + errorThrown);
            }
        }
    });
}

export const rating = {
    setRatingValue: setRatingValue,
    updateRating: updateRating
}

