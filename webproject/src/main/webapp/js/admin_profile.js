function banUser(PROJECT_NAME, CONTROLLER, COMMAND, BAN_USER, LOAD_BAN_UNBAN_FORM, LOGIN, DAYS_TO_BAN) {
    var userLogin = document.getElementById("user-login").value;
    var daysToBan = document.getElementById("days-in-ban").value;
    var data = {};
    data[LOGIN] = userLogin;
    data[DAYS_TO_BAN] = daysToBan;
    var url = CONTROLLER + "?" + COMMAND + "=" + BAN_USER;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        success: function (updateResult) {
            if (updateResult == "true") {
                $("#user-ban-unban-form").load("/" + PROJECT_NAME + "/" + CONTROLLER + "?" + COMMAND + "=" + LOAD_BAN_UNBAN_FORM + " #user-ban-unban-form");
                //set invisible to visible validation green
            } else if (updateResult == "false") {
                // set invisible to visible validation red
            }
        },
        error: function () {
            // TODO show error
        }
    });
};

function unbanUser(PROJECT_NAME, CONTROLLER, COMMAND, UNBAN_USER, LOAD_BAN_UNBAN_FORM, LOGIN) {
    var userLogin = document.getElementById("user-login").value;
    var data = {};
    data[LOGIN] = userLogin;
    var url = CONTROLLER + "?" + COMMAND + "=" + UNBAN_USER;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        success: function (updateResult) {
            if (updateResult == "true") {
                $("#user-ban-unban-form").load("/" + PROJECT_NAME + "/" + CONTROLLER + "?" + COMMAND + "=" + LOAD_BAN_UNBAN_FORM + " #user-ban-unban-form");
                //set invisible to visible validation green
            } else if (updateResult == "false") {
                // set invisible to visible validation red
            }
        },
        error: function () {
            // TODO show error
        }
    });
};

