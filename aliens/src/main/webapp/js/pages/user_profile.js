import { EmailUpdateForm, LoginUpdateForm, PasswordUpdateForm, ImageUpdateForm } from "../modules/user.js"
import { removeParameterFromUrl, changeLocationIfUndefined, reloadIfBack } from "../modules/util.js"

let userId;
let userEmail;
let userLogin;
let userProfileAccountEmail;
let userProfileAccountLogin;

let formUpdateEmail;
let formUpdateEmailEmail;
let formUpdateEmailValidFeedback;
let formUpdateEmailInvalidFeedback;

let formUpdateLogin;
let formUpdateLoginLogin;
let formUpdateLoginLoginInvalidFeedback;

let formUpdateImage;
let formUpdateImageImage;
let formUpdateImageImageLabel;
let formUpdateImageImageInvalidFeedback;

let formUpdatePassword;
let formUpdatePasswordPassword;
let formUpdatePasswordPasswordConfirmation;
let formUpdatePasswordPasswordInvalidFeedback;
let formUpdatePasswordPasswordConfirmationInvalidFeedback;

/** @type {EmailUpdateForm} */
let emailUpdateForm;
/** @type {LoginUpdateForm} */
let loginUpdateForm;
/** @type {PasswordUpdateForm} */
let passwordUpdateForm;
/** @type {ImageUpdateForm} */
let imageUpdateForm;

/**
 * url clean
 */
 $(function(){
    removeParameterFromUrl("token");
});

$(document).ready(function () {

    userId = document.getElementById("form-update-email-parameter-current-user-id");
    userEmail = document.getElementById("form-update-email-parameter-current-user-email");
    userLogin = document.getElementById("form-update-login-parameter-current-user-login");
    userProfileAccountLogin = document.getElementById("user-profile-account-login");
    userProfileAccountEmail = document.getElementById("user-profile-account-email");
    
    formUpdateEmail =  document.getElementById("form-update-email");
    formUpdateEmailEmail = document.getElementById("form-update-email-email");
    formUpdateEmailValidFeedback = document.getElementById("form-update-email-email-valid-feedback");      
    formUpdateEmailInvalidFeedback = document.getElementById("form-update-email-email-invalid-feedback");
    emailUpdateForm = new EmailUpdateForm(formUpdateEmail, formUpdateEmailEmail, formUpdateEmailInvalidFeedback, formUpdateEmailValidFeedback);

    formUpdateLogin = document.getElementById("form-update-login");
    formUpdateLoginLogin = document.getElementById("form-update-login-login");
    formUpdateLoginLoginInvalidFeedback = document.getElementById("form-update-login-login-invalid-feedback");
    loginUpdateForm = new LoginUpdateForm(formUpdateLogin, formUpdateLoginLogin, formUpdateLoginLoginInvalidFeedback, null);


    formUpdatePassword = document.getElementById("form-update-password");
    formUpdatePasswordPassword = document.getElementById("form-update-password-password");
    formUpdatePasswordPasswordConfirmation = document.getElementById("form-update-password-password-confirmation");
    formUpdatePasswordPasswordInvalidFeedback =  document.getElementById("form-update-password-password-invalid-feedback");
    formUpdatePasswordPasswordConfirmationInvalidFeedback =  document.getElementById("form-update-password-password-confirmation-invalid-feedback");
    passwordUpdateForm = new PasswordUpdateForm(formUpdatePassword, formUpdatePasswordPassword, formUpdatePasswordPasswordConfirmation, 
        formUpdatePasswordPasswordInvalidFeedback, formUpdatePasswordPasswordConfirmationInvalidFeedback, null, null);

    formUpdateImage =  document.getElementById("form-update-image");
    formUpdateImageImage = document.getElementById("form-update-image-image");
    formUpdateImageImageLabel = document.getElementById("form-update-image-image-label");
    formUpdateImageImageInvalidFeedback =  document.getElementById("form-update-image-image-invalid-feedback");
    imageUpdateForm = new ImageUpdateForm(formUpdateImage, formUpdateImageImage, formUpdateImageImageInvalidFeedback, null, formUpdateImageImageLabel);

});

function updateUserEmail() {
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
            emailUpdateForm.setFeedback(jqXHR.responseJSON[EMAIL_STATUS],
                jqXHR.responseJSON[EMAIL_FEEDBACK],
                jqXHR.responseJSON[EMAIL_FEEDBACK]
            );
            userProfileAccountEmail.innerHTML = formUpdateEmailEmail.value;
            userEmail.innerHTML = formUpdateEmailEmail.value;
        },
        error: function (jqXHR, textStatus, errorThrown) {
            changeLocationIfUndefined(jqXHR);
            emailUpdateForm.removeValidation();
            emailUpdateForm.setFeedback(jqXHR.responseJSON[EMAIL_STATUS], 
                jqXHR.responseJSON[EMAIL_FEEDBACK],
                jqXHR.responseJSON[EMAIL_FEEDBACK]
            );
        }
    });
};

function updateUserLogin() {
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
            loginUpdateForm.setFeedback(jqXHR.responseJSON[LOGIN_STATUS],
                jqXHR.responseJSON[LOGIN_FEEDBACK],
                jqXHR.responseJSON[LOGIN_FEEDBACK]
            );
            userProfileAccountLogin.innerHTML = formUpdateLoginLogin.value;
            userLogin.innerHTML = formUpdateLoginLogin.value;
        },
        error: function (jqXHR, textStatus, errorThrown) {
            changeLocationIfUndefined(jqXHR);
            loginUpdateForm.removeValidation();
            loginUpdateForm.setFeedback(jqXHR.responseJSON[LOGIN_STATUS], 
                jqXHR.responseJSON[LOGIN_FEEDBACK],
                jqXHR.responseJSON[LOGIN_FEEDBACK]
            );
        }
    });
};

function updateUserPassword() {
    let data = {};
    data[USER_ID] = userId.innerHTML;
    data[PASSWORD] = passwordUpdateForm.getPasswordValue();
    data[PASSWORD_CONFIRM] = passwordUpdateForm.getPasswordConfirmationValue();
    let url = CONTROLLER + "?" + COMMAND + "=" + UPDATE_USER_PASSWORD;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            passwordUpdateForm.setFeedback(jqXHR.responseJSON[PASSWORD_STATUS], 
                jqXHR.responseJSON[PASSWORD_FEEDBACK],
                jqXHR.responseJSON[PASSWORD_FEEDBACK],
                jqXHR.responseJSON[PASSWORD_CONFIRMATION_STATUS],
                jqXHR.responseJSON[PASSWORD_CONFIRMATION_FEEDBACK],
                jqXHR.responseJSON[PASSWORD_CONFIRMATION_FEEDBACK]
            );
        },
        error: function (jqXHR, textStatus, errorThrown) {
            changeLocationIfUndefined(jqXHR);
            passwordUpdateForm.removeValidation();
            passwordUpdateForm.setFeedback(jqXHR.responseJSON[PASSWORD_STATUS], 
                jqXHR.responseJSON[PASSWORD_FEEDBACK], 
                jqXHR.responseJSON[PASSWORD_FEEDBACK], 
                jqXHR.responseJSON[PASSWORD_CONFIRMATION_STATUS], 
                jqXHR.responseJSON[PASSWORD_CONFIRMATION_FEEDBACK],
                jqXHR.responseJSON[PASSWORD_CONFIRMATION_FEEDBACK]
            );
        }
    });
};

function updateUserImage(image) {
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
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            imageUpdateForm.setFeedback(jqXHR.responseJSON[IMAGE_STATUS], "");
            let imageContainer = document.getElementById("account-image");
            let newImagePath = jqXHR.responseJSON[IMAGE_PATH];
            imageContainer.src = newImagePath;
        },
        error: function (jqXHR, textStatus, errorThrown) {
            changeLocationIfUndefined(jqXHR);
            imageUpdateForm.removeValidation();
            imageUpdateForm.setFeedback(jqXHR.responseJSON[IMAGE_STATUS], jqXHR.responseJSON[IMAGE_FEEDBACK]);
        }
    });
};

const userProfile = {
    updateUserEmail: updateUserEmail,
    updateUserLogin: updateUserLogin,
    updateUserPassword: updateUserPassword,
    updateUserImage: updateUserImage
}

/**
 * Reset forms
 */
$(function() {
    formUpdateEmail.reset();
    formUpdateLogin.reset();
    formUpdateImage.reset();
    formUpdatePassword.reset();
});

/**
 * form update email form event listeners
 */
$(document).ready(function () {
    formUpdateEmail.addEventListener('submit', function(event) {
        emailUpdateForm.removeValidation();
        if (emailUpdateForm.validate()) {
            emailUpdateForm.setFeedback(true, "", "");
            event.preventDefault();
            userProfile.updateUserEmail();
        } else {
            emailUpdateForm.setFeedback(false, "", STANDARD_EMAIL_FEEDBACK);
            event.preventDefault();
            event.stopPropagation();
        }
    });

    formUpdateEmailEmail.addEventListener('input', function(event) {
        emailUpdateForm.removeValidation();
    });
});

/**
 * update login form event listeners
 */
$(document).ready(function () {
    formUpdateLogin.addEventListener('submit', function(event) {
        loginUpdateForm.removeValidation();
        if (loginUpdateForm.validate()) {
            loginUpdateForm.setFeedback(true, "", "");
            event.preventDefault();
            userProfile.updateUserLogin();
        } else {
            loginUpdateForm.setFeedback(false, "", STANDARD_LOGIN_FEEDBACK);
            event.preventDefault();
            event.stopPropagation();
        }
    });
    formUpdateLoginLogin.addEventListener('input', function(event) {
        loginUpdateForm.removeValidation();
    })
});

/**
 * Password update form event listeners
 */
$(document).ready(function () {
    formUpdatePassword.addEventListener('submit', function(event) {
        passwordUpdateForm.removeValidation();
        let validationResult = passwordUpdateForm.validate();
        if (!validationResult.some(element => element == false)) {
            event.preventDefault();
            userProfile.updateUserPassword();
        } else {
            if (validationResult[0] && validationResult[1] && !validationResult[2]) {
                passwordUpdateForm.setFeedback(false, "",
                    PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL, 
                    false, "",
                    PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL);
            } else {
                passwordUpdateForm.setElementFeedback(passwordUpdateForm.getPassword(), 
                    passwordUpdateForm.getPasswordValid(),
                    passwordUpdateForm.getPasswordInvalid(), 
                    validationResult[0], "", STANDARD_PASSWORD_FEEDBACK
                );
                passwordUpdateForm.setElementFeedback(passwordUpdateForm.getPasswordConfirmation(), 
                    passwordUpdateForm.getPasswordConfirmationValid(), 
                    passwordUpdateForm.getPasswordConfirmationInvalid(), 
                    validationResult[0], "", STANDARD_PASSWORD_FEEDBACK
                );
            }
            event.preventDefault();
            event.stopPropagation();
        }
    });

    formUpdatePasswordPassword.addEventListener('input', function(event) {
        passwordUpdateForm.removeValidation();
    });
    formUpdatePasswordPasswordConfirmation.addEventListener('input', function(event) {
        passwordUpdateForm.removeValidation();
    });
});

/**
 * update image form event listeners
 */
$(document).ready(function () {
    formUpdateImage.addEventListener('submit', function(event) {
        imageUpdateForm.removeValidation();
        let validationResult = imageUpdateForm.validate();
        if (validationResult) {
            event.preventDefault();
            userProfile.updateUserImage(imageUpdateForm.getImage());
        } else {
            imageUpdateForm.setFeedback(false, "", STANDARD_IMAGE_FEEDBACK);
            event.preventDefault();
            event.stopPropagation();
        }
    });
    formUpdateImageImage.addEventListener('input', function(event) {
        imageUpdateForm.removeValidation();
        imageUpdateForm.setLabelText();
    });
});
