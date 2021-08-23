import { changeLocationIfUndefined } from "../modules/util.js";

let buttonsApprove;
let buttonsDecline;

let approveUrl;
let declineUrl;

$(document).ready(function () {
    buttonsApprove = document.getElementsByName("approve-alien-image");
    buttonsDecline = document.getElementsByName("decline-alien-image");
    approveUrl = CONTROLLER + "?" + COMMAND + "=" + ADMIN_APPROVE_ALIEN_IMAGE;
    declineUrl = CONTROLLER + "?" + COMMAND + "=" + ADMIN_DECLINE_ALIEN_IMAGE;
});

function sendPost(data, url, div) {
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            div.remove();
            alert("success");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            if(!changeLocationIfUndefined(jqXHR)) {
                alert("error");
            }
        }
    });
}

function setApproveDeclineEvent(button, func) {
    button.addEventListener('click', function(event) {
        let alienImageUrl = button.parentElement.parentElement.children[1].children[0].children[0].getAttribute("src");
        let urlArray = alienImageUrl.split("/");
        alienImageUrl = urlArray[urlArray.length - 1];
        let data = {};
        data[IMAGE] = alienImageUrl;
        sendPost(data, func, button.parentElement.parentElement.parentElement.parentElement);
    })
}

$(document).ready(function () {
    buttonsApprove.forEach(button => 
        setApproveDeclineEvent(button, approveUrl)
    );
    buttonsDecline.forEach(button =>
        setApproveDeclineEvent(button, declineUrl)
    );
});

