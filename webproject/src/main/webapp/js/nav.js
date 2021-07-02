function logoutUser(CONTROLLER, COMMAND, LOGOUT_USER, OPEN_HOME_PAGE) {
    var url = CONTROLLER + "?" + COMMAND + "=" + LOGOUT_USER;
    $.ajax({
        url: url,
        type: "POST",
        success: function (updateResult) {
            if (updateResult == "true") {
                url = CONTROLLER + "?" + COMMAND + "=" + OPEN_HOME_PAGE;
                location.assign(url);
            } else if (updateResult == "false") {
            
            }
        },
        error: function () {
            // TODO show error
        }
    });
};
