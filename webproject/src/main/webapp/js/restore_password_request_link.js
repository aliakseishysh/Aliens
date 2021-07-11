function restorePassword() {
    // var enteredEmail = document.getElementById("email-update-form-email").value;
    // var data = {};
    // data[EMAIL] = currentEmail;
    // data[NEW_EMAIL] = enteredEmail;
    // data[USER_ID] = userId;
    // var url = CONTROLLER + "?" + COMMAND + "=" + UPDATE_USER_EMAIL;
    // $.ajax({
    //     url: url,
    //     type: "POST",
    //     data: data,
    //     success: function (updateResult) {
    //         if (updateResult == "true") {
    //             document.getElementById("user-profile-account-email").innerHTML = enteredEmail;
    //             $("#email-update-form").load("/" + PROJECT_NAME + "/" + CONTROLLER + "?" + COMMAND + "=" + LOAD_EMAIL_UPDATE_FORM + " #email-update-form");
    //             //set invisible to visible validation green
    //         } else if (updateResult == "false") {
    //             // set invisible to visible validation red
    //         }
    //     },
    //     error: function () {
    //         // TODO show error
    //     }
    // });
};

const forgotPasswordPage = {
    restorePassword: restorePassword
}