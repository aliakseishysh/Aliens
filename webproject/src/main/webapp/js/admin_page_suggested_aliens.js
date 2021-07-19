function approveAlien(alienId, article) {
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
            alert("error");
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
            alert("error");
        }
    });
};


const pageSuggestedAliens = {
    approveAlien: approveAlien,
    declineAlien: declineAlien
}

$(document).ready(function () {

    let buttonsApprove = document.getElementsByName("approve-alien");
    let buttonsDecline = document.getElementsByName("decline-alien");

    buttonsApprove.forEach(button => 
        button.addEventListener('click', function(event) {
            let alienId = button.parentElement.firstElementChild.innerHTML;
            let article = button.closest("article");
            pageSuggestedAliens.approveAlien(alienId, article);
        })
    );

    buttonsDecline.forEach(button => 
        button.addEventListener('click', function(event) {
            let alienId = button.parentElement.firstElementChild.innerHTML;
            let article = button.closest("article");
            pageSuggestedAliens.declineAlien(alienId, article);
        }
    ));

});

