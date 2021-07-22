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
            if (jqXHR.status == 403) {
                url = CONTROLLER + "?" + COMMAND + "=" + OPEN_HOME_PAGE;
                location.assign(url);
            }
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
        }
    });
}

export const navigation = {
    logoutUser: logoutUser,
    changeLocaleEn: changeLocaleEn,
    changeLocaleRu: changeLocaleRu
}

