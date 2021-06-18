function updateUserEmail(PROJECT_NAME, CONTROLLER, COMMAND, UPDATE_USER_EMAIL, EMAIL, NEW_EMAIL, USER_ID, LOAD_EMAIL_UPDATE_FORM, userId, currentEmail) {
    var enteredEmail = document.getElementById("email").value;
    var data = {};
    data[EMAIL] = currentEmail;
    data[NEW_EMAIL] = enteredEmail;
    data[USER_ID] = userId;
    var url = CONTROLLER + "?" + COMMAND + "=" + UPDATE_USER_EMAIL;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        success: function(updateResult) {
            if (updateResult == "true") {
                document.getElementById("account-email").innerHTML = enteredEmail;
                $("#email-update-form").load("/" + PROJECT_NAME + "/" + CONTROLLER + "?" + COMMAND + "=" + LOAD_EMAIL_UPDATE_FORM + " #email-update-form");
                //set invisible to visible validation green
            } else if (updateResult == "false") {
                // set invisible to visible validation red
            }
        },
        error: function() {
            // TODO show error
        }
    });
};

function updateUserLogin(PROJECT_NAME, CONTROLLER, COMMAND, UPDATE_USER_LOGIN, LOGIN, NEW_LOGIN, USER_ID, LOAD_LOGIN_UPDATE_FORM ,userId, currentLogin) {
    var enteredLogin = document.getElementById("login").value;
    var data = {};
    data[LOGIN] = currentLogin;
    data[NEW_LOGIN] = enteredLogin;
    data[USER_ID] = userId;
    var url = CONTROLLER + "?" + COMMAND + "=" + UPDATE_USER_LOGIN;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        success: function(updateResult) {
            if (updateResult == "true") {
                document.getElementById("account-login").innerHTML = enteredLogin;
                // $("#login-update-form").load("/" + PROJECT_NAME + "/" + CONTROLLER + "?" + COMMAND + "=" + LOAD_LOGIN_UPDATE_FORM + " #login-update-form");
                $("#login-update-form").load("/webproject/controller?command=login-update-form #login-update-form");
                //set invisible to visible validation green
            } else if (updateResult == "false") {
                // set invisible to visible validation red
            }
        },
        error: function() {
            // TODO show error
        }
    });
};

