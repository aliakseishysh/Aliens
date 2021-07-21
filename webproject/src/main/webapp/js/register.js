function registerUser() {
    let enteredEmailElement = document.getElementById("form-register-email");
    let enteredLoginElement = document.getElementById("form-register-login");
    let enteredPasswordElement = document.getElementById("form-register-password");
    let enteredPasswordConfirmElement = document.getElementById("form-register-password-confirm");
    let formRegisterElement = document.getElementById("form-register");
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
            if (jqXHR.responseJSON[EMAIL_STATUS] == true 
                && jqXHR.responseJSON[LOGIN_STATUS] == true
                && jqXHR.responseJSON[PASSWORD_STATUS] == true
                && jqXHR.responseJSON[PASSWORD_CONFIRMATION_STATUS] == true) {
                    enteredEmailElement.classList.remove("is-invalid");
                    enteredLoginElement.classList.remove("is-invalid");
                    enteredPasswordElement.classList.remove("is-invalid");
                    enteredPasswordConfirmElement.classList.remove("is-invalid");
                    formRegisterElement.classList.add("was-validated");
            }
            url = CONTROLLER + "?" + COMMAND + "=" + OPEN_LOGIN_PAGE;
            location.assign(url);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            // if (serverError) {};
            formRegisterElement.classList.remove("was-validated");
            
            enteredEmailElement.innerHTML = jqXHR.responseJSON[EMAIL_FEEDBACK];
            enteredLoginElement.innerHTML = jqXHR.responseJSON[LOGIN_FEEDBACK];
            enteredPasswordElement.innerHTML = jqXHR.responseJSON[PASSWORD_FEEDBACK];
            enteredPasswordConfirmElement.innerHTML = jqXHR.responseJSON[PASSWORD_CONFIRMATION_FEEDBACK];
            
            if (jqXHR.responseJSON[EMAIL_STATUS] == false) {
                enteredEmailElement.classList.add("is-invalid");
                enteredEmailElement.classList.remove("is-valid");
            } else {
                enteredEmailElement.classList.remove("is-invalid");
                enteredEmailElement.classList.add("is-valid");
            }
            if (jqXHR.responseJSON[LOGIN_STATUS] == false) {
                enteredLoginElement.classList.add("is-invalid");
                enteredLoginElement.classList.remove("is-valid");
            } else {
                enteredLoginElement.classList.remove("is-invalid");
                enteredLoginElement.classList.add("is-valid");
            }
            if (jqXHR.responseJSON[PASSWORD_STATUS] == false) {
                enteredPasswordElement.classList.remove("is-valid");
                enteredPasswordElement.classList.add("is-invalid");
            } else {
                enteredPasswordElement.classList.remove("is-invalid");
                enteredPasswordElement.classList.add("is-valid");
            }
            if (jqXHR.responseJSON[PASSWORD_CONFIRMATION_STATUS] == false) {
                enteredPasswordConfirmElement.classList.remove("is-valid");
                enteredPasswordConfirmElement.classList.add("is-invalid");
            } else {
                enteredPasswordConfirmElement.classList.remove("is-invalid");
                enteredPasswordConfirmElement.classList.add("is-valid");
            }
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

$(document).ready(function () {
    let formRegisterElement = document.getElementById("form-register");
    formRegisterElement.addEventListener('submit', function(event) {

        let enteredEmailElement = document.getElementById("form-register-email");
        let enteredLoginElement = document.getElementById("form-register-login");
        let enteredPasswordElement = document.getElementById("form-register-password");
        let enteredPasswordConfirmElement = document.getElementById("form-register-password-confirm");

        let enteredEmailInvalidFeedback = document.getElementById("form-register-email-invalid-feedback");
        let enteredLoginInvalidFeedback = document.getElementById("form-register-login-invalid-feedback");
        let enteredPasswordInvalidFeedback = document.getElementById("form-register-password-invalid-feedback");
        let enteredPasswordConfirmationInvalidFeedback = document.getElementById("form-register-password-confirm-invalid-feedback");

        enteredPasswordElement.setCustomValidity("");
        enteredPasswordConfirmElement.setCustomValidity("");


        if (formRegisterElement.checkValidity() === false || enteredPasswordElement.value != enteredPasswordConfirmElement.value) {
            enteredEmailInvalidFeedback.innerHTML = STANDARD_EMAIL_FEEDBACK;
            enteredLoginInvalidFeedback.innerHTML = STANDARD_LOGIN_FEEDBACK;
            enteredPasswordInvalidFeedback.innerHTML = STANDARD_PASSWORD_FEEDBACK;
            enteredPasswordConfirmationInvalidFeedback.innerHTML = STANDARD_PASSWORD_CONFIRMATION_FEEDBACK;
            
            if (enteredPasswordElement.value != enteredPasswordConfirmElement.value) {
                enteredPasswordElement.setCustomValidity(PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL);
                enteredPasswordConfirmElement.setCustomValidity(PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL);
                enteredPasswordInvalidFeedback.innerHTML = PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL;
                enteredPasswordConfirmationInvalidFeedback.innerHTML = PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL;
            }

            event.preventDefault();
            event.stopPropagation();
        } else {
            event.preventDefault();
            registerPage.registerUser();
        }
        formRegisterElement.classList.add('was-validated');
      }, false);
});

// document.addEventListener("DOMContentLoaded",() => {
//     let formRegister = document.getElementById("form-register");
//     formRegister.addEventListener('submit', function(event) {
//         if (formRegister.checkValidity() === false) {
//             document.getElementById("form-register-email-invalid-feedback").innerHTML = STANDARD_EMAIL_FEEDBACK;
//             document.getElementById("form-register-login-invalid-feedback").innerHTML = STANDARD_LOGIN_FEEDBACK;
//             document.getElementById("form-register-password-invalid-feedback").innerHTML = STANDARD_PASSWORD_FEEDBACK;
//             document.getElementById("form-register-password-confirm-invalid-feedback").innerHTML = STANDARD_PASSWORD_CONFIRMATION_FEEDBACK;
//             event.preventDefault();
//             event.stopPropagation();
//         } else {
//             event.preventDefault();
//             registerPage.registerUser();
//         }
//         formRegister.classList.add('was-validated');
//       }, false);
// });