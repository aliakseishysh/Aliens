function logoutUser() {
    let url = CONTROLLER + "?" + COMMAND + "=" + LOGOUT_USER
    $.ajax({
        url: url,
        type: "POST",
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            url = CONTROLLER + "?" + COMMAND + "=" + OPEN_HOME_PAGE
            location.assign(url)
        },
        error: function (jqXHR, textStatus, errorThrown) {
            // TODO can't logout or something
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

const navigation = {
    logoutUser: logoutUser,
    changeLocaleEn: changeLocaleEn,
    changeLocaleRu: changeLocaleRu
}

$(document).ready(function () {
    let dropdownMenuButton = document.getElementById("dropdownMenuButton")
    dropdownMenuButton.addEventListener('click', function(event) {
        document.getElementById("nav-dropdawn-container").classList.toggle("show");
        document.getElementById("nav-dropdown-menu").classList.toggle("show");
        if (dropdownMenuButton.getAttribute("aria-expanded") == true) {
            dropdownMenuButton.setAttribute("aria-expanded", false);
        } else {
            dropdownMenuButton.setAttribute("aria-expanded", true);
        };
      }, false);
});