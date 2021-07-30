import { changeLocationIfUndefined } from "../modules/util.js";

let buttonsApprove;
let buttonsDecline;

let approveUrl;
let declineUrl;

$(document).ready(function () {
    buttonsApprove = document.getElementsByName("approve-alien");
    buttonsDecline = document.getElementsByName("decline-alien");
    approveUrl = CONTROLLER + "?" + COMMAND + "=" + ADMIN_APPROVE_ALIEN;
    declineUrl = CONTROLLER + "?" + COMMAND + "=" + ADMIN_DECLINE_ALIEN;
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

$(document).ready(function () {

    buttonsApprove.forEach(button => 
        button.addEventListener('click', function(event) {
            let alienId = button.parentElement.firstElementChild.innerHTML;
            let data = {};
            data[ALIEN_ID] = alienId;
            sendPost(data, approveUrl, button.parentElement);
        })
    );

    buttonsDecline.forEach(button => 
        button.addEventListener('click', function(event) {
            let alienId = button.parentElement.firstElementChild.innerHTML;
            let data = {};
            data[ALIEN_ID] = alienId;
            sendPost(data, declineUrl, button.parentElement);
        }
    ));

});

