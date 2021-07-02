function loginUser(CONTROLLER, COMMAND, LOGIN_USER, OPEN_HOME_PAGE, EMAIL, PASSWORD) {
    var enteredEmail = document.getElementById("form-login-email").value;
    var enteredPassword = document.getElementById("form-login-password").value;
    var data = {};
    data[EMAIL] = enteredEmail;
    data[PASSWORD] = enteredPassword;
    var url = CONTROLLER + "?" + COMMAND + "=" + LOGIN_USER;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
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

function restorePassword(CONTROLLER, COMMAND, FORGOT_PASSWORD) {

}

function openRegisterPage(CONTROLLER, COMMAND, OPEN_REGISTER_PAGE) {
    var url = CONTROLLER + "?" + COMMAND + "=" + OPEN_REGISTER_PAGE;
    location.assign(url);
}   
