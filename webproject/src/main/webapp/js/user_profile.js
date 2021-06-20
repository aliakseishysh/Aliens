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
        success: function (updateResult) {
            if (updateResult == "true") {
                document.getElementById("account-email").innerHTML = enteredEmail;
                $("#email-update-form").load("/" + PROJECT_NAME + "/" + CONTROLLER + "?" + COMMAND + "=" + LOAD_EMAIL_UPDATE_FORM + " #email-update-form");
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

function updateUserLogin(PROJECT_NAME, CONTROLLER, COMMAND, UPDATE_USER_LOGIN, LOGIN, NEW_LOGIN, USER_ID, LOAD_LOGIN_UPDATE_FORM, userId, currentLogin) {
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
        success: function (updateResult) {
            if (updateResult == "true") {
                document.getElementById("account-login").innerHTML = enteredLogin;
                $("#login-update-form").load("/" + PROJECT_NAME + "/" + CONTROLLER + "?" + COMMAND + "=" + LOAD_LOGIN_UPDATE_FORM + " #login-update-form");

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

function updateUserPassword(PROJECT_NAME, CONTROLLER, COMMAND, UPDATE_USER_PASSWORD, PASSWORD, PASSWORD_CONFIRM, USER_ID, LOAD_PASSWORD_UPDATE_FORM, userId) {
    var password = document.getElementById("password").value;
    var passwordConfirm = document.getElementById("password_confirm").value;
    var data = {};
    data[PASSWORD] = password;
    data[PASSWORD_CONFIRM] = passwordConfirm;
    data[USER_ID] = userId;
    var url = CONTROLLER + "?" + COMMAND + "=" + UPDATE_USER_PASSWORD;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        success: function (updateResult) {
            if (updateResult == "true") {
                $("#password-update-form").load("/" + PROJECT_NAME + "/" + CONTROLLER + "?" + COMMAND + "=" + LOAD_PASSWORD_UPDATE_FORM + " #password-update-form");

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

function updateUserImage(PROJECT_NAME, CONTROLLER, COMMAND, UPDATE_USER_IMAGE, NEW_IMAGE, USER_ID, LOAD_IMAGE_UPDATE_FORM, LOAD_USER_IMAGE, WEB_SITE_NAME, userId) {

    var formData = new FormData();
    var files = document.getElementById("image").files;
    if(files.length > 0 ){
        var file = files[0];
        var fileSize = file.size;
        formData.append(USER_ID, userId);
        formData.append(NEW_IMAGE, file);
    } else {
        return;
    }
    var url = CONTROLLER + "?" + COMMAND + "=" + UPDATE_USER_IMAGE;
    $.ajax({
        url: url,
        type: "POST",
        data: formData,
        cache: false,
        contentType: false,
        processData: false,
        success: function (updateResult) {
            if (updateResult == "true") {
                $("#image-update-form").load("/" + PROJECT_NAME + "/" + CONTROLLER + "?" + COMMAND + "=" + LOAD_IMAGE_UPDATE_FORM + " #image-update-form");


                //set invisible to visible validation green
            } else if (updateResult == "false") {
                // set invisible to visible validation red rrr
            }
        },
        error: function () {
            // TODO show error
        }
    });
};

