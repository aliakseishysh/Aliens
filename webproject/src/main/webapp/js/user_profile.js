function updateUserEmail() {
    let userId = document.getElementById("form-update-email-parameter-current-user-id");
    let userEmail = document.getElementById("form-update-email-parameter-current-user-email");
    let formUpdateEmailEmail = document.getElementById("form-update-email-email");
    let userProfileAccountEmail = document.getElementById("user-profile-account-email");
    let formUpdateEmail =  document.getElementById("form-update-email");
    let formUpdateEmailInvalidFeedback = document.getElementById("form-update-email-email-invalid-feedback");
    let data = {};
    data[USER_ID] = userId.innerHTML;
    data[EMAIL] = userEmail.innerHTML;
    data[NEW_EMAIL] = formUpdateEmailEmail.value;
    let url = CONTROLLER + "?" + COMMAND + "=" + UPDATE_USER_EMAIL;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            if (jqXHR.responseJSON[EMAIL_STATUS] == true) {
                userProfileAccountEmail.innerHTML = formUpdateEmailEmail.value;
                userEmail.innerHTML = formUpdateEmailEmail.value;
            }

        },
        error: function (jqXHR, textStatus, errorThrown) {
            formUpdateEmail.classList.remove("was-validated");
            formUpdateEmailInvalidFeedback.innerHTML = jqXHR.responseJSON[EMAIL_FEEDBACK];
            if (jqXHR.responseJSON[EMAIL_STATUS] == false) {
                formUpdateEmailEmail.classList.remove("is-valid");
                formUpdateEmailEmail.classList.add("is-invalid");
            } else {
                formUpdateEmailEmail.classList.remove("is-invalid");
                formUpdateEmailEmail.classList.add("is-valid");
            }
        }
    });
};

function updateUserLogin() {
    let userId = document.getElementById("form-update-login-parameter-current-user-id");
    let userLogin = document.getElementById("form-update-login-parameter-current-user-login");
    let formUpdateLoginLogin = document.getElementById("form-update-login-login");
    let formUpdateLoginLoginInvalidFeedback = document.getElementById("form-update-login-login-invalid-feedback");
    let formUpdateLogin = document.getElementById("form-update-login");
    let userProfileAccountLogin = document.getElementById("user-profile-account-login");
    let data = {};
    data[USER_ID] = userId.innerHTML;
    data[LOGIN] = userLogin.innerHTML;
    data[NEW_LOGIN] = formUpdateLoginLogin.value;
    let url = CONTROLLER + "?" + COMMAND + "=" + UPDATE_USER_LOGIN;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            if (jqXHR.responseJSON[LOGIN_STATUS] == true) {
                userProfileAccountLogin.innerHTML = formUpdateLoginLogin.value;
                userLogin.innerHTML = formUpdateLoginLogin.value;
            }

        },
        error: function (jqXHR, textStatus, errorThrown) {
            formUpdateLogin.classList.remove("was-validated");
            formUpdateLoginLoginInvalidFeedback.innerHTML = jqXHR.responseJSON[LOGIN_FEEDBACK];
            if (jqXHR.responseJSON[LOGIN_STATUS] == false) {
                formUpdateLoginLogin.classList.remove("is-valid");
                formUpdateLoginLogin.classList.add("is-invalid");
            } else {
                formUpdateLoginLogin.classList.remove("is-invalid");
                formUpdateLoginLogin.classList.add("is-valid");
            }
        }
    });
};


function updateUserPassword() {
    let userId = document.getElementById("form-update-password-parameter-current-user-id");
    let password = document.getElementById("form-update-password-password");
    let passwordConfirm = document.getElementById("form-update-password-password-confirmation");
    let formUpdatePassword = document.getElementById("form-update-password");
    let formUpdatePasswordPassword = document.getElementById("form-update-password-password");
    let formUpdatePasswordPasswordConfirmation = document.getElementById("form-update-password-password-confirmation");
    let formUpdatePasswordPasswordInvalidFeedback = document.getElementById("form-update-password-password-invalid-feedback");
    let formUpdatePasswordPasswordConfirmationInvalidFeedback = document.getElementById("form-update-password-password-confirmation-invalid-feedback");
    let data = {};
    data[USER_ID] = userId.innerHTML;
    data[PASSWORD] = password.value;
    data[PASSWORD_CONFIRM] = passwordConfirm.value;
    let url = CONTROLLER + "?" + COMMAND + "=" + UPDATE_USER_PASSWORD;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            if (jqXHR.responseJSON[PASSWORD_STATUS] == true) {
                // document.getElementById("form-update-password-parameter-current-user-id").innerHTML = userId;
            }

        },
        error: function (jqXHR, textStatus, errorThrown) {
            formUpdatePassword.classList.remove("was-validated");
            formUpdatePasswordPasswordInvalidFeedback.innerHTML = jqXHR.responseJSON[PASSWORD_FEEDBACK];
            formUpdatePasswordPasswordConfirmationInvalidFeedback.innerHTML = jqXHR.responseJSON[PASSWORD_CONFIRMATION_FEEDBACK];
            if (jqXHR.responseJSON[PASSWORD_STATUS] == false) {
                formUpdatePasswordPassword.classList.add("is-invalid");
                formUpdatePasswordPassword.classList.remove("is-valid");
            } else {
                formUpdatePasswordPassword.classList.remove("is-invalid");
                formUpdatePasswordPassword.classList.add("is-valid");
            }
            if (jqXHR.responseJSON[PASSWORD_CONFIRMATION_STATUS] == false) {
                formUpdatePasswordPasswordConfirmation.classList.add("is-invalid");
                formUpdatePasswordPasswordConfirmation.classList.remove("is-valid");
            } else {
                formUpdatePasswordPasswordConfirmation.classList.remove("is-invalid");
                formUpdatePasswordPasswordConfirmation.classList.add("is-valid");
            }
        }
    });
};

function updateUserImage(image) {
    let userId = document.getElementById("form-update-image-parameter-current-user-id");
    let formUpdateImage =  document.getElementById("form-update-image");
    let formUpdateImageImage = document.getElementById("form-update-image-image");
    let formUpdateImageImageInvalidFeedback =  document.getElementById("form-update-image-image-invalid-feedback");
    let formData = new FormData();
    formData.append(USER_ID, userId.innerHTML);
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
            if (jqXHR.responseJSON[IMAGE_STATUS] == true) {
                // TODO set image to avatar
                formUpdateImageImage.classList.remove("is-invalid");
                formUpdateImageImage.classList.add("is-valid");
                formUpdateImage.classList.add("was-validated");

                let imageContainer = document.getElementById("account-image");
                let newImagePath = jqXHR.responseJSON[IMAGE_PATH];
                imageContainer.src = newImagePath;


                // let files;
                // downloadImage(newImagePath, "account_image").then(downloaded => {
                //     files = downloaded;
                // });
                // imageContainer.files = files;


            }

        },
        error: function (jqXHR, textStatus, errorThrown) {
            formUpdateImage.classList.remove("was-validated");
            formUpdateImageImageInvalidFeedback.innerHTML = jqXHR.responseJSON[IMAGE_FEEDBACK];
            if (jqXHR.responseJSON[IMAGE_STATUS] == false) {
                formUpdateImageImage.classList.remove("is-valid");
                formUpdateImageImage.classList.add("is-invalid");
            } else {
                formUpdateImageImage.classList.remove("is-invalid");
                formUpdateImageImage.classList.add("is-valid");
            }
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
    let formUpdateEmailEmail = document.getElementById("form-update-email-email");
    let formUpdateEmailEmailInvalidFeedback = document.getElementById("form-update-email-email-invalid-feedback");
    formUpdateEmail.addEventListener('submit', function(event) {
        formUpdateEmailEmail.classList.remove("is-invalid");
        formUpdateEmailEmail.classList.remove("is-valid");
        if (formUpdateEmail.checkValidity() === false) {
            formUpdateEmailEmailInvalidFeedback.innerHTML = STANDARD_EMAIL_FEEDBACK;
            event.preventDefault();
            event.stopPropagation();
        } else {
            event.preventDefault();
            userProfile.updateUserEmail();
        }
        formUpdateEmail.classList.add('was-validated');
      }, false);
      formUpdateEmailEmail.addEventListener('input', function(event) {
        formUpdateEmail.classList.remove('was-validated');
        formUpdateEmailEmail.classList.remove("is-invalid");
        formUpdateEmailEmail.classList.remove("is-valid");
      })
});

$(document).ready(function () {
    let formUpdateLogin = document.getElementById("form-update-login");
    let formUpdateLoginLogin = document.getElementById("form-update-login-login");
    let formUpdateLoginLoginInvalidFeedback = document.getElementById("form-update-login-login-invalid-feedback");
    formUpdateLogin.addEventListener('submit', function(event) {
        formUpdateLoginLogin.classList.remove("is-invalid");
        formUpdateLoginLogin.classList.remove("is-valid");
        if (formUpdateLogin.checkValidity() === false) {
            formUpdateLoginLoginInvalidFeedback.innerHTML = STANDARD_LOGIN_FEEDBACK;
            event.preventDefault();
            event.stopPropagation();
        } else {
            event.preventDefault();
            userProfile.updateUserLogin();
        }
        formUpdateLogin.classList.add('was-validated');
      }, false);
      formUpdateLoginLogin.addEventListener('input', function(event) {
        formUpdateLogin.classList.remove('was-validated');
        formUpdateLoginLogin.classList.remove("is-invalid");
        formUpdateLoginLogin.classList.remove("is-valid");
      })
});

$(document).ready(function () {
    let formUpdatePassword = document.getElementById("form-update-password");
    let formUpdatePasswordPassword = document.getElementById("form-update-password-password");
    let formUpdatePasswordPasswordConfirmation = document.getElementById("form-update-password-password-confirmation");
    let formUpdatePasswordPasswordInvalidFeedback =  document.getElementById("form-update-password-password-invalid-feedback");
    let formUpdatePasswordPasswordConfirmationInvalidFeedback =  document.getElementById("form-update-password-password-confirmation-invalid-feedback");
   

    formUpdatePassword.addEventListener('submit', function(event) {
        formUpdatePasswordPassword.classList.remove("is-invalid");
        formUpdatePasswordPassword.classList.remove("is-valid");
        formUpdatePasswordPasswordConfirmation.classList.remove("is-invalid");
        formUpdatePasswordPasswordConfirmation.classList.remove("is-valid");
        if (formUpdatePassword.checkValidity() === false || formUpdatePasswordPassword.value != formUpdatePasswordPasswordConfirmation.value) {
            formUpdatePasswordPasswordInvalidFeedback.innerHTML = STANDARD_PASSWORD_FEEDBACK;
            formUpdatePasswordPasswordConfirmationInvalidFeedback.innerHTML = STANDARD_PASSWORD_FEEDBACK;
           
            if (formUpdatePasswordPassword.value != formUpdatePasswordPasswordConfirmation.value) {
                formUpdatePasswordPassword.setCustomValidity(PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL);
                formUpdatePasswordPasswordConfirmation.setCustomValidity(PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL);
                formUpdatePasswordPasswordInvalidFeedback.innerHTML = PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL;
                formUpdatePasswordPasswordConfirmationInvalidFeedback.innerHTML = PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL;
            }
            
            event.preventDefault();
            event.stopPropagation();
        } else {
            event.preventDefault();
            userProfile.updateUserPassword();
        }
        formUpdatePassword.classList.add('was-validated');
      }, false);
      formUpdatePasswordPassword.addEventListener('input', function(event) {
        formUpdatePassword.classList.remove('was-validated');
        formUpdatePasswordPassword.classList.remove("is-invalid");
        formUpdatePasswordPassword.classList.remove("is-valid");
      })
      formUpdatePasswordPasswordConfirmation.addEventListener('input', function(event) {
        formUpdatePassword.classList.remove('was-validated');
        formUpdatePasswordPasswordConfirmation.classList.remove("is-invalid");
        formUpdatePasswordPasswordConfirmation.classList.remove("is-valid");
      })
});

$(document).ready(function () {
    let formUpdateImage = document.getElementById("form-update-image");
    let formUpdateImageImage = document.getElementById("form-update-image-image");
    let formUpdateImageImageInvalidFeedback = document.getElementById("form-update-image-image-invalid-feedback");

    formUpdateImage.addEventListener('submit', function(event) {
        document.getElementById("form-update-image-image").classList.remove("is-invalid");
        document.getElementById("form-update-image-image").classList.remove("is-valid");
        formUpdateImage.classList.remove("was-validated");

        let files = formUpdateImageImage.files;
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
            formUpdateImageImageInvalidFeedback.innerHTML = STANDARD_IMAGE_FEEDBACK;
            formUpdateImageImage.classList.add("is-invalid");
            formUpdateImage.classList.add('was-validated');
           // formUpdateImageImage.setCustomValidity(IMAGE_FEEDBACK_INVALID);
            event.preventDefault();
            event.stopPropagation();
        } else {
            event.preventDefault();
            document.getElementById("form-update-image-image-label").innerHTML = files[0].name;
            userProfile.updateUserImage(files[0]);
        }
      }, false);
      formUpdateImageImage.addEventListener('input', function(event) {
        document.getElementById("form-update-image-image-label").innerHTML = formUpdateImageImage.files[0].name;
        formUpdateImage.classList.remove('was-validated');
        formUpdateImageImage.classList.remove("is-invalid");
        formUpdateImageImage.classList.remove("is-valid");
      })
});

