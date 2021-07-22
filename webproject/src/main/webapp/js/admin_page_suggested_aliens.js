let buttonsApprove;
let buttonsDecline;

$(document).ready(function () {
    buttonsApprove = document.getElementsByName("approve-alien-image");
    buttonsDecline = document.getElementsByName("decline-alien-image");
});

function approveAlien(alienId) {
    let data = {};
    data[ALIEN_ID] = alienId;
    let url = CONTROLLER + "?" + COMMAND + "=" + ADMIN_APPROVE_ALIEN;
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

function declineAlien(alienId, article) {
    let data = {};
    data[ALIEN_ID] = alienId;
    let url = CONTROLLER + "?" + COMMAND + "=" + ADMIN_DECLINE_ALIEN;
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
    approveAlien: approveAlien,
    declineAlien: declineAlien
}

$(document).ready(function () {

    buttonsApprove.forEach(button => 
        button.addEventListener('click', function(event) {
            let alienId = button.parentElement.firstElementChild.innerHTML;
            pageSuggestedAliens.approveAlien(alienId);
        })
    )

    buttonsDecline.forEach(button => 
        button.addEventListener('click', function(event) {
            let alienId = button.parentElement.firstElementChild.innerHTML;
            pageSuggestedAliens.declineAlien(alienId);
        }
    ));

});

