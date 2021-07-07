function updateUserEmail() {
    let userId = document.getElementById("form-update-email-parameter-current-user-id").innerHTML;
    let userEmail = document.getElementById("form-update-email-parameter-current-user-email").innerHTML;
    let enteredEmail = document.getElementById("form-update-email-email").value;
    let data = {};
    data[USER_ID] = userId;
    data[EMAIL] = userEmail;
    data[NEW_EMAIL] = enteredEmail;
    let url = CONTROLLER + "?" + COMMAND + "=" + UPDATE_USER_EMAIL;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            if (jqXHR.responseJSON[UPDATE_EMAIL_RESULT_INFO_STATUS] == true) {
                document.getElementById("user-profile-account-email").innerHTML = enteredEmail;
                document.getElementById("form-update-email-parameter-current-user-email").innerHTML = enteredEmail;
                // document.getElementById("form-update-email").classList.remove('was-validated');
                // document.getElementById("form-update-email-email").classList.remove("is-invalid");
            }

        },
        error: function (jqXHR, textStatus, errorThrown) {
            document.getElementById("form-update-email").classList.remove("was-validated");
            document.getElementById("form-update-email-email-invalid-feedback").innerHTML = jqXHR.responseJSON[UPDATE_EMAIL_RESULT_INFO_EMAIL_FEEDBACK];
            if (jqXHR.responseJSON[UPDATE_EMAIL_RESULT_INFO_STATUS] == false) {
                document.getElementById("form-update-email-email").classList.add("is-invalid");
                document.getElementById("form-update-email-email").classList.remove("is-valid");
            } else {
                document.getElementById("form-update-email-email").classList.remove("is-invalid");
                document.getElementById("form-update-email-email").classList.add("is-valid");
            }
        }
    });
};

function updateUserLogin() {
    let userId = document.getElementById("form-update-login-parameter-current-user-id").innerHTML;
    let userLogin = document.getElementById("form-update-login-parameter-current-user-login").innerHTML;
    let enteredLogin = document.getElementById("form-update-login-login").value;
    let data = {};
    data[USER_ID] = userId;
    data[LOGIN] = userLogin;
    data[NEW_LOGIN] = enteredLogin;
    let url = CONTROLLER + "?" + COMMAND + "=" + UPDATE_USER_LOGIN;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            if (jqXHR.responseJSON[UPDATE_LOGIN_RESULT_INFO_STATUS] == true) {
                document.getElementById("user-profile-account-login").innerHTML = enteredLogin;
                document.getElementById("form-update-login-parameter-current-user-login").innerHTML = enteredLogin;
                // document.getElementById("form-update-LOGIN").classList.remove('was-validated');
                // document.getElementById("form-update-LOGIN-LOGIN").classList.remove("is-invalid");
            }

        },
        error: function (jqXHR, textStatus, errorThrown) {
            document.getElementById("form-update-login").classList.remove("was-validated");
            document.getElementById("form-update-login-login-invalid-feedback").innerHTML = jqXHR.responseJSON[UPDATE_LOGIN_RESULT_INFO_LOGIN_FEEDBACK];
            if (jqXHR.responseJSON[UPDATE_LOGIN_RESULT_INFO_STATUS] == false) {
                document.getElementById("form-update-login-login").classList.add("is-invalid");
                document.getElementById("form-update-login-login").classList.remove("is-valid");
            } else {
                document.getElementById("form-update-login-login").classList.remove("is-invalid");
                document.getElementById("form-update-login-login").classList.add("is-valid");
            }
        }
    });
};


function updateUserPassword() {
    let userId = document.getElementById("form-update-password-parameter-current-user-id").innerHTML;
    let password = document.getElementById("form-update-password-password").value;
    let passwordConfirm = document.getElementById("form-update-password-password-confirmation").value;
    let data = {};
    data[USER_ID] = userId;
    data[PASSWORD] = password;
    data[PASSWORD_CONFIRM] = passwordConfirm;
    let url = CONTROLLER + "?" + COMMAND + "=" + UPDATE_USER_PASSWORD;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            if (jqXHR.responseJSON[UPDATE_PASSWORD_RESULT_INFO_STATUS] == true) {
                document.getElementById("form-update-password-parameter-current-user-id").innerHTML = userId;
                // document.getElementById("form-update-PASSWORD").classList.remove('was-validated');
                // document.getElementById("form-update-PASSWORD-PASSWORD").classList.remove("is-invalid");
            }

        },
        error: function (jqXHR, textStatus, errorThrown) {
            document.getElementById("form-update-password").classList.remove("was-validated");
            document.getElementById("form-update-password-password-invalid-feedback").innerHTML = jqXHR.responseJSON[UPDATE_PASSWORD_RESULT_INFO_PASSWORD_FEEDBACK];
            document.getElementById("form-update-password-password-confirmation-invalid-feedback").innerHTML = jqXHR.responseJSON[UPDATE_PASSWORD_RESULT_INFO_PASSWORD_CONFIRMATION_FEEDBACK];
            if (jqXHR.responseJSON[UPDATE_PASSWORD_RESULT_INFO_PASSWORD_STATUS] == false) {
                document.getElementById("form-update-password-password").classList.add("is-invalid");
                document.getElementById("form-update-password-password").classList.remove("is-valid");
            } else {
                document.getElementById("form-update-password-password").classList.remove("is-invalid");
                document.getElementById("form-update-password-password").classList.add("is-valid");
            }
            if (jqXHR.responseJSON[UPDATE_PASSWORD_RESULT_INFO_PASSWORD_CONFIRMATION_STATUS] == false) {
                document.getElementById("form-update-password-password-confirmation").classList.add("is-invalid");
                document.getElementById("form-update-password-password-confirmation").classList.remove("is-valid");
            } else {
                document.getElementById("form-update-password-password-confirmation").classList.remove("is-invalid");
                document.getElementById("form-update-password-password-confirmation").classList.add("is-valid");
            }
        }
    });
};

function updateUserImage(image) {
    let userId = document.getElementById("form-update-image-parameter-current-user-id").innerHTML;
    let formData = new FormData();
    formData.append(USER_ID, userId);
    formData.append(NEW_IMAGE, image);
    let url = CONTROLLER + "?" + COMMAND + "=" + UPDATE_USER_IMAGE;

    $.ajax({
        url: url,
        type: "POST",
        data: formData,
        cache: false,
        contentType: false,
        processData: false,
        dataType: "json", // TODO will this work?
        success: function (data, textStatus, jqXHR) {
            if (jqXHR.responseJSON[UPDATE_IMAGE_RESULT_INFO_IMAGE_STATUS] == true) {
                // TODO set image to avatar
                document.getElementById("form-update-image-image").classList.add("is-valid");
                document.getElementById("form-update-image-image").classList.remove("is-invalid");
                document.getElementById("form-update-image").classList.add("was-validated");

                // document.getElementById("form-update-login-parameter-current-user-login").innerHTML = enteredLogin;
                // document.getElementById("form-update-LOGIN").classList.remove('was-validated');
                // document.getElementById("form-update-LOGIN-LOGIN").classList.remove("is-invalid");
            }

        },
        error: function (jqXHR, textStatus, errorThrown) {
            document.getElementById("form-update-image").classList.remove("was-validated");
            document.getElementById("form-update-image-image-invalid-feedback").innerHTML = jqXHR.responseJSON[UPDATE_IMAGE_RESULT_INFO_IMAGE_FEEDBACK];
            if (jqXHR.responseJSON[UPDATE_IMAGE_RESULT_INFO_IMAGE_STATUS] == false) {
                document.getElementById("form-update-image-image").classList.add("is-invalid");
                document.getElementById("form-update-image-image").classList.remove("is-valid");
            } else {
                document.getElementById("form-update-image-image").classList.remove("is-invalid");
                document.getElementById("form-update-image-image").classList.add("is-valid");
            }
            document.getElementById("form-update-image").classList.add("was-validated");
        }
    });
};

const userProfile = {
    updateUserEmail: updateUserEmail,
    updateUserLogin: updateUserLogin,
    updateUserPassword: updateUserPassword,
    updateUserImage: updateUserImage
}

$(document).ready(function () {
    let formUpdateEmail = document.getElementById("form-update-email");
    formUpdateEmail.addEventListener('submit', function(event) {
        document.getElementById("form-update-email-email").classList.remove("is-invalid");
        document.getElementById("form-update-email-email").classList.remove("is-valid");
        if (formUpdateEmail.checkValidity() === false) {
            document.getElementById("form-update-email-email-invalid-feedback").innerHTML = UPDATE_STANDARD_EMAIL_FEEDBACK;
            event.preventDefault();
            event.stopPropagation();
        } else {
            event.preventDefault();
            userProfile.updateUserEmail();
        }
        formUpdateEmail.classList.add('was-validated');
      }, false);
      document.getElementById("form-update-email-email").addEventListener('input', function(event) {
        formUpdateEmail.classList.remove('was-validated');
        document.getElementById("form-update-email-email").classList.remove("is-invalid");
        document.getElementById("form-update-email-email").classList.remove("is-valid");
      })
});

$(document).ready(function () {
    let formUpdateLogin = document.getElementById("form-update-login");
    formUpdateLogin.addEventListener('submit', function(event) {
        document.getElementById("form-update-login-login").classList.remove("is-invalid");
        document.getElementById("form-update-login-login").classList.remove("is-valid");
        if (formUpdateLogin.checkValidity() === false) {
            document.getElementById("form-update-login-login-invalid-feedback").innerHTML = UPDATE_LOGIN_STANDARD_LOGIN_FEEDBACK;
            event.preventDefault();
            event.stopPropagation();
        } else {
            event.preventDefault();
            userProfile.updateUserLogin();
        }
        formUpdateLogin.classList.add('was-validated');
      }, false);
      document.getElementById("form-update-login-login").addEventListener('input', function(event) {
        formUpdateLogin.classList.remove('was-validated');
        document.getElementById("form-update-login-login").classList.remove("is-invalid");
        document.getElementById("form-update-login-login").classList.remove("is-valid");
      })
});

$(document).ready(function () {
    let formUpdatePassword = document.getElementById("form-update-password");
    formUpdatePassword.addEventListener('submit', function(event) {
        document.getElementById("form-update-password-password").classList.remove("is-invalid");
        document.getElementById("form-update-password-password").classList.remove("is-valid");
        document.getElementById("form-update-password-password-confirmation").classList.remove("is-invalid");
        document.getElementById("form-update-password-password-confirmation").classList.remove("is-valid");
        if (formUpdatePassword.checkValidity() === false || document.getElementById("form-update-password-password").value != document.getElementById("form-update-password-password-confirmation").value) {
            document.getElementById("form-update-password-password-invalid-feedback").innerHTML = UPDATE_PASSWORD_STANDARD_PASSWORD_FEEDBACK;
            document.getElementById("form-update-password-password-confirmation-invalid-feedback").innerHTML = UPDATE_PASSWORD_STANDARD_PASSWORD_FEEDBACK;
           
            if (document.getElementById("form-update-password-password").value != document.getElementById("form-update-password-password-confirmation").value) {
                document.getElementById("form-update-password-password").setCustomValidity(UPDATE_PASSWORD_RESULT_INFO_FEEDBACK_INVALID_PASSWORDS_NOT_EQUALS);
                document.getElementById("form-update-password-password-confirmation").setCustomValidity(UPDATE_PASSWORD_RESULT_INFO_FEEDBACK_INVALID_PASSWORDS_NOT_EQUALS);
                document.getElementById("form-update-password-password-invalid-feedback").innerHTML = UPDATE_PASSWORD_RESULT_INFO_FEEDBACK_INVALID_PASSWORDS_NOT_EQUALS;
                document.getElementById("form-update-password-password-confirmation-invalid-feedback").innerHTML = UPDATE_PASSWORD_RESULT_INFO_FEEDBACK_INVALID_PASSWORDS_NOT_EQUALS;
            }
            
            event.preventDefault();
            event.stopPropagation();
        } else {
            event.preventDefault();
            userProfile.updateUserPassword();
        }
        formUpdatePassword.classList.add('was-validated');
      }, false);
      document.getElementById("form-update-password-password").addEventListener('input', function(event) {
        formUpdatePassword.classList.remove('was-validated');
        document.getElementById("form-update-password-password").classList.remove("is-invalid");
        document.getElementById("form-update-password-password").classList.remove("is-valid");
      })
      document.getElementById("form-update-password-password-confirmation").addEventListener('input', function(event) {
        formUpdatePassword.classList.remove('was-validated');
        document.getElementById("form-update-password-password-confirmation").classList.remove("is-invalid");
        document.getElementById("form-update-password-password-confirmation").classList.remove("is-valid");
      })
});

$(document).ready(function () {
    let formUpdateImage = document.getElementById("form-update-image");
    formUpdateImage.addEventListener('submit', function(event) {
        document.getElementById("form-update-image-image-label").innerHTML = "Select image"; // change to variable


        document.getElementById("form-update-image-image").classList.remove("is-invalid");
        document.getElementById("form-update-image-image").classList.remove("is-valid");
        document.getElementById("form-update-image").classList.remove("was-validated");

        let files = document.getElementById("form-update-image-image").files;
        let result = false;
        let validExtensions = ["image/jpg", "image/jpeg", "image/png"];
        
        if (files.length == 1) {
            let file = files[0];
            
            if (file.size <= 1000000) { // bytes
                for (let i = 0; i < validExtensions.length; i++) {
                    if (validExtensions[i] == file.type) {
                        result = true;
                        break;
                    }
                }    
            }
        }

        if (result == false) {
            document.getElementById("form-update-image-image-invalid-feedback").innerHTML = UPDATE_IMAGE_STANDARD_IMAGE_FEEDBACK;
            document.getElementById("form-update-image-image").classList.add("is-invalid");
            formUpdateImage.classList.add('was-validated');
           // document.getElementById("form-update-image-image").setCustomValidity(UPDATE_IMAGE_RESULT_INFO_FEEDBACK_INVALID_IMAGE);
            event.preventDefault();
            event.stopPropagation();
        } else {
            event.preventDefault();
            document.getElementById("form-update-image-image-label").innerHTML = files[0].name;
            userProfile.updateUserImage(files[0]);
        }
      }, false);
      document.getElementById("form-update-image-image").addEventListener('input', function(event) {
        document.getElementById("form-update-image-image-label").innerHTML = document.getElementById("form-update-image-image").files[0].name;
        formUpdateImage.classList.remove('was-validated');
        document.getElementById("form-update-image-image").classList.remove("is-invalid");
        document.getElementById("form-update-image-image").classList.remove("is-valid");
      })
});

