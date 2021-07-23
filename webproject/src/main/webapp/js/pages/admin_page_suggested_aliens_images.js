import { sendPost } from "../modules/request.js";

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

$(document).ready(function () {

    buttonsApprove.forEach(button => 
        button.addEventListener('click', function(event) {
            let alienImageUrl = button.parentElement.parentElement.children[1].children[0].children[0].getAttribute("src");
            alienImageUrl = alienImageUrl.substring(1 + PROJECT_NAME.length);
            let data = {};
            data[IMAGE] = alienImageUrl;
            sendPost(data, approveUrl);
        })
    );

    buttonsDecline.forEach(button => 
        button.addEventListener('click', function(event) {
            let alienImageUrl = button.parentElement.parentElement.children[1].children[0].children[0].getAttribute("src");
            alienImageUrl = alienImageUrl.substring(1 + PROJECT_NAME.length);
            let data = {};
            data[IMAGE] = alienImageUrl;
            sendPost(data, declineUrl);
        }
    ));

});

