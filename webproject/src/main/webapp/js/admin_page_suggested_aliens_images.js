function approveAlienImage(alienImageUrl, article) {
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
            alert("error");
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
            alert("error");
        }
    });
};


const pageSuggestedAliens = {
    approveAlienImage: approveAlienImage,
    declineAlienImage: declineAlienImage
}

$(document).ready(function () {

    let buttonsApprove = document.getElementsByName("approve-alien-image");
    let buttonsDecline = document.getElementsByName("decline-alien-image");

    buttonsApprove.forEach(button => 
        button.addEventListener('click', function(event) {
            let alienImageUrl = button.parentElement.parentElement.children[1].children[0].children[0].getAttribute("src");
            alienImageUrl = alienImageUrl.substring(1 + PROJECT_NAME.length);
            let article = button.closest("article"); // TODO some error message
            pageSuggestedAliens.approveAlienImage(alienImageUrl, article);
        })
    );

    buttonsDecline.forEach(button => 
        button.addEventListener('click', function(event) {
            let alienImageUrl = button.parentElement.parentElement.children[1].children[0].children[0].getAttribute("src");
            alienImageUrl = alienImageUrl.substring(1 + PROJECT_NAME.length);
            let article = button.closest("article"); // TODO some error message
            pageSuggestedAliens.declineAlienImage(alienImageUrl, article);
        }
    ));

});

