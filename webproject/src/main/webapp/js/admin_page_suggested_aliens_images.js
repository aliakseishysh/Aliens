const buttonsApprove = document.getElementsByName("approve-alien-image");
const buttonsDecline = document.getElementsByName("decline-alien-image");

function approveAlienImage(alienImageUrl) {
    let data = {};
    data[IMAGE] = alienImageUrl;
    let url = CONTROLLER + "?" + COMMAND + "=" + ADMIN_APPROVE_ALIEN_IMAGE;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            alert("success");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + " "  + textStatus + " " + errorThrown);
        }
    });
};

function declineAlienImage(alienImageUrl, article) {
    let data = {};
    data[IMAGE] = alienImageUrl;
    let url = CONTROLLER + "?" + COMMAND + "=" + ADMIN_DECLINE_ALIEN_IMAGE;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            alert("success");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + " "  + textStatus + " " + errorThrown);
        }
    });
};

const pageSuggestedAliens = {
    approveAlienImage: approveAlienImage,
    declineAlienImage: declineAlienImage
}

$(document).ready(function () {

    buttonsApprove.forEach(button => 
        button.addEventListener('click', function(event) {
            let alienImageUrl = button.parentElement.parentElement.children[1].children[0].children[0].getAttribute("src");
            alienImageUrl = alienImageUrl.substring(1 + PROJECT_NAME.length);
            pageSuggestedAliens.approveAlienImage(alienImageUrl);
        })
    );

    buttonsDecline.forEach(button => 
        button.addEventListener('click', function(event) {
            let alienImageUrl = button.parentElement.parentElement.children[1].children[0].children[0].getAttribute("src");
            alienImageUrl = alienImageUrl.substring(1 + PROJECT_NAME.length);
            pageSuggestedAliens.declineAlienImage(alienImageUrl);
        }
    ));

});

