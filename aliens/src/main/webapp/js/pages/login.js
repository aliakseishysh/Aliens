import { LoginForm } from "../modules/user.js"
import { removeParameterFromUrl, changeLocationIfUndefined } from "../modules/util.js"
/** @type {LoginForm} */
let loginForm;

let formLogin;
let email;
let password;
let emailInvalidFeedback;
let passwordInvalidFeedback;

/**
 * url clean
 */
$(function(){
    removeParameterFromUrl("token");
});

/**
 * Setup
 */
$(function() {
    email = document.getElementById("form-login-email");
    password = document.getElementById("form-login-password");
    formLogin = document.getElementById("form-login");
    emailInvalidFeedback = document.getElementById("form-login-email-invalid-feedback");
    passwordInvalidFeedback =  document.getElementById("form-login-password-invalid-feedback");
    loginForm = new LoginForm(formLogin, email, null, emailInvalidFeedback, password, null, passwordInvalidFeedback);
});


function loginUser() {
    let data = {}
    data[EMAIL] = email.value
    data[PASSWORD] = password.value
    let url = CONTROLLER + "?" + COMMAND + "=" + LOGIN_USER
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            loginForm.setFeedback(jqXHR.responseJSON[EMAIL_STATUS], 
                jqXHR.responseJSON[EMAIL_FEEDBACK], 
                jqXHR.responseJSON[EMAIL_FEEDBACK],
                jqXHR.responseJSON[PASSWORD_STATUS],
                jqXHR.responseJSON[PASSWORD_FEEDBACK],
                jqXHR.responseJSON[PASSWORD_FEEDBACK]
            );
            url = CONTROLLER + "?" + COMMAND + "=" + OPEN_HOME_PAGE;
            location.assign(url);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            changeLocationIfUndefined(jqXHR);
            loginForm.removeValidation();
            loginForm.setFeedback(jqXHR.responseJSON[EMAIL_STATUS], 
                jqXHR.responseJSON[EMAIL_FEEDBACK], 
                jqXHR.responseJSON[EMAIL_FEEDBACK],
                jqXHR.responseJSON[PASSWORD_STATUS],
                jqXHR.responseJSON[PASSWORD_FEEDBACK],
                jqXHR.responseJSON[PASSWORD_FEEDBACK]
            );
        }
    });
}

function openRegisterPage() {
    let url = CONTROLLER + "?" + COMMAND + "=" + OPEN_REGISTER_PAGE;
    location.assign(url);
}

const loginPage = {
    loginUser: loginUser,
    openRegisterPage: openRegisterPage
};

window.loginPage = loginPage;

/**
 * Login processing
 */
$(document).ready(function () {
    formLogin.addEventListener('submit', function(event) {
        loginForm.removeValidation();
        let validationResult = loginForm.validate();
        if (!validationResult.some(result => result === false)) {
            event.preventDefault();
            loginPage.loginUser();
        } else {
            loginForm.setFeedback(validationResult[0], "", STANDARD_EMAIL_FEEDBACK, validationResult[1], "", STANDARD_PASSWORD_FEEDBACK);
            event.preventDefault();
            event.stopPropagation();
        }
    });
    email.addEventListener("input", function(event) {
        loginForm.removeEmailValidation();
    });
    password.addEventListener("input", function(event) {
        loginForm.removePasswordValidation();
    });
});


