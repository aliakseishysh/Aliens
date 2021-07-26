import { changeLocationIfUndefined } from "./util.js"

export function sendPost(data, url) {
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            alert("success");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            if(!changeLocationIfUndefined(jqXHR)) {
                alert("error");
            }
        }
    });
}