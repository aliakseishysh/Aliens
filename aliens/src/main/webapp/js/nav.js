import { changeLocationIfUndefined } from "./modules/util.js"

function logoutUser() {
    let url = CONTROLLER + "?" + COMMAND + "=" + LOGOUT_USER
    $.ajax({
        url: url,
        type: "POST",
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            url = CONTROLLER + "?" + COMMAND + "=" + OPEN_HOME_PAGE;
            location.assign(url);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            changeLocationIfUndefined(jqXHR);
        }
    });
}

function changeLocaleRu() {
    let url = CONTROLLER + "?" + COMMAND + "=" + CHANGE_LOCALE + "&" + LOCALE + "=" + LOCALIZATION_RU
    $.ajax({
        url: url,
        type: "POST",
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            window.location.reload()
        },
        error: function (jqXHR, textStatus, errorThrown) {
            changeLocationIfUndefined(jqXHR);
        }

    });
}

function changeLocaleEn() {
    let url = CONTROLLER + "?" + COMMAND + "=" + CHANGE_LOCALE + "&" + LOCALE + "=" + LOCALIZATION_EN
    $.ajax({
        url: url,
        type: "POST",
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            window.location.reload()
        },
        error: function (jqXHR, textStatus, errorThrown) {
            changeLocationIfUndefined(jqXHR);
        }
    });
}

export const navigation = {
    logoutUser: logoutUser,
    changeLocaleEn: changeLocaleEn,
    changeLocaleRu: changeLocaleRu
}

