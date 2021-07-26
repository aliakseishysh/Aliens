import { RegisterForm } from "../modules/user.js"
import { changeLocationIfUndefined } from "../modules/util.js"
/** @type {RegisterForm} */
let registerForm;

let formRegisterElement;
let enteredEmailElement;
let enteredLoginElement;
let enteredPasswordElement;
let enteredPasswordConfirmElement;
let enteredEmailInvalidFeedback;
let enteredEmailValidFeedback;
let enteredLoginInvalidFeedback;
let enteredPasswordInvalidFeedback;
let enteredPasswordConfirmationInvalidFeedback;

/**
 * Setup
 */
$(function() {
    formRegisterElement = document.getElementById("form-register");
    enteredEmailElement = document.getElementById("form-register-email");
    enteredLoginElement = document.getElementById("form-register-login");
    enteredPasswordElement = document.getElementById("form-register-password");
    enteredPasswordConfirmElement = document.getElementById("form-register-password-confirm");
    enteredEmailInvalidFeedback = document.getElementById("form-register-email-invalid-feedback");
    enteredEmailValidFeedback = document.getElementById("form-register-email-valid-feedback");
    
    enteredLoginInvalidFeedback = document.getElementById("form-register-login-invalid-feedback");
    enteredPasswordInvalidFeedback = document.getElementById("form-register-password-invalid-feedback");
    enteredPasswordConfirmationInvalidFeedback = document.getElementById("form-register-password-confirm-invalid-feedback");



    registerForm = new RegisterForm(formRegisterElement, enteredEmailElement, enteredEmailValidFeedback, enteredEmailInvalidFeedback, 
        enteredLoginElement, null, enteredLoginInvalidFeedback, enteredPasswordElement, null, enteredPasswordInvalidFeedback,
        enteredPasswordConfirmElement, null, enteredPasswordConfirmationInvalidFeedback);

});

function registerUser() {
    let data = {};
    data[EMAIL] = enteredEmailElement.value;
    data[LOGIN] = enteredLoginElement.value;
    data[PASSWORD] = enteredPasswordElement.value;
    data[PASSWORD_CONFIRM] = enteredPasswordConfirmElement.value;
    let url = CONTROLLER + "?" + COMMAND + "=" + REGISTER_USER;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            registerForm.setEmailFeedback(jqXHR.responseJSON[EMAIL_STATUS], jqXHR.responseJSON[EMAIL_FEEDBACK], jqXHR.responseJSON[EMAIL_FEEDBACK]);
            registerForm.setLoginFeedback(jqXHR.responseJSON[LOGIN_STATUS], jqXHR.responseJSON[LOGIN_FEEDBACK], jqXHR.responseJSON[LOGIN_FEEDBACK]);
            registerForm.setPasswordFeedback(
                jqXHR.responseJSON[PASSWORD_STATUS],
                jqXHR.responseJSON[PASSWORD_FEEDBACK],
                jqXHR.responseJSON[PASSWORD_FEEDBACK]
            );
            registerForm.setPasswordConfirmationFeedback(
                jqXHR.responseJSON[PASSWORD_CONFIRMATION_STATUS],
                jqXHR.responseJSON[PASSWORD_CONFIRMATION_FEEDBACK],
                jqXHR.responseJSON[PASSWORD_CONFIRMATION_FEEDBACK]
            );
        },
        error: function (jqXHR, textStatus, errorThrown) {
            changeLocationIfUndefined(jqXHR);
            registerForm.removeValidation();
            registerForm.setEmailFeedback(jqXHR.responseJSON[EMAIL_STATUS], jqXHR.responseJSON[EMAIL_FEEDBACK], jqXHR.responseJSON[EMAIL_FEEDBACK]);
            registerForm.setLoginFeedback(jqXHR.responseJSON[LOGIN_STATUS], jqXHR.responseJSON[LOGIN_FEEDBACK], jqXHR.responseJSON[LOGIN_FEEDBACK]);
            registerForm.setPasswordFeedback(
                jqXHR.responseJSON[PASSWORD_STATUS],
                jqXHR.responseJSON[PASSWORD_FEEDBACK],
                jqXHR.responseJSON[PASSWORD_FEEDBACK]
            );
            registerForm.setPasswordConfirmationFeedback(
                jqXHR.responseJSON[PASSWORD_CONFIRMATION_STATUS],
                jqXHR.responseJSON[PASSWORD_CONFIRMATION_FEEDBACK],
                jqXHR.responseJSON[PASSWORD_CONFIRMATION_FEEDBACK]
            );
        }
    });
};

function openLoginPage() {
    let url = CONTROLLER + "?" + COMMAND + "=" + OPEN_LOGIN_PAGE;
    location.assign(url);
}

const registerPage = {
    registerUser: registerUser,
    openLoginPage: openLoginPage
}

/**
 * Register processing
 */
$(document).ready(function () {
    formRegisterElement.addEventListener('submit', function(event) {
        registerForm.removeValidation();
        let validationResult = registerForm.validate();
        if(!validationResult.some(result => result == false)) {
            event.preventDefault();
            registerPage.registerUser();
        } else {
            registerForm.setEmailFeedback(validationResult[0], "", STANDARD_EMAIL_FEEDBACK);
            registerForm.setLoginFeedback(validationResult[1], "", STANDARD_LOGIN_FEEDBACK);

            if (validationResult[2] && validationResult[3] && !validationResult[4]) {
                registerForm.setPasswordFeedback(false, "", PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL);
                registerForm.setPasswordConfirmationFeedback(false, "", PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL);
            } else {
                registerForm.setPasswordFeedback(validationResult[2], "", STANDARD_PASSWORD_FEEDBACK);
                registerForm.setPasswordConfirmationFeedback(validationResult[3], "", STANDARD_PASSWORD_FEEDBACK);
            }
            event.preventDefault();
            event.stopPropagation();
        }
    });
    enteredEmailElement.addEventListener("submit", function(event) {
        registerForm.removeEmailValidation();
    });
    enteredLoginElement.addEventListener("submit", function(event) {
        registerForm.removeLoginValidation();
    });
    enteredPasswordElement.addEventListener("submit", function(event) {
        registerForm.removePasswordValidation();
        registerForm.removePasswordConfirmationValidation();
    });
    enteredPasswordConfirmElement.addEventListener("submit", function(event) {
        registerForm.removePasswordValidation();
        registerForm.removePasswordConfirmationValidation();
    });
});