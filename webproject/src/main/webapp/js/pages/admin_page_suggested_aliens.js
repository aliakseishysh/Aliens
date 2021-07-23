import { sendPost } from "../modules/request.js";

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

$(document).ready(function () {

    buttonsApprove.forEach(button => 
        button.addEventListener('click', function(event) {
            let alienId = button.parentElement.firstElementChild.innerHTML;
            let data = {};
            data[ALIEN_ID] = alienId;
            sendPost(data, approveUrl);
        })
    )

    buttonsDecline.forEach(button => 
        button.addEventListener('click', function(event) {
            let alienId = button.parentElement.firstElementChild.innerHTML;
            let data = {};
            data[ALIEN_ID] = alienId;
            sendPost(data, declineUrl);
        }
    ));

});

