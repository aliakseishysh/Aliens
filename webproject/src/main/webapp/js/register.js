function registerUser(CONTROLLER, COMMAND, REGISTER_USER, OPEN_LOGIN_PAGE, EMAIL, LOGIN, PASSWORD, PASSWORD_CONFIRM) {
    var enteredEmail = document.getElementById("form-register-email").value;
    var enteredLogin = document.getElementById("form-register-login").value;
    var enteredPassword = document.getElementById("form-register-password").value;
    var enteredPasswordConfirm = document.getElementById("form-register-password-confirm").value;
    var data = {};
    data[EMAIL] = enteredEmail;
    data[LOGIN] = enteredLogin;
    data[PASSWORD] = enteredPassword;
    data[PASSWORD_CONFIRM] = enteredPasswordConfirm;
    var url = CONTROLLER + "?" + COMMAND + "=" + REGISTER_USER;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        success: function (updateResult) {
            if (updateResult == "true") {
                url = CONTROLLER + "?" + COMMAND + "=" + OPEN_LOGIN_PAGE;
                location.assign(url);
            } else if (updateResult == "false") {
            
            }
        },
        error: function () {
            // TODO show error
        }
    });
};

function openLoginPage(CONTROLLER, COMMAND, OPEN_LOGIN_PAGE) {
    var url = CONTROLLER + "?" + COMMAND + "=" + OPEN_LOGIN_PAGE;
    location.assign(url);
}   
