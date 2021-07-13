function registerUser() {
    let enteredEmail = document.getElementById("form-register-email").value;
    let enteredLogin = document.getElementById("form-register-login").value;
    let enteredPassword = document.getElementById("form-register-password").value;
    let enteredPasswordConfirm = document.getElementById("form-register-password-confirm").value;
    let data = {};
    data[EMAIL] = enteredEmail;
    data[LOGIN] = enteredLogin;
    data[PASSWORD] = enteredPassword;
    data[PASSWORD_CONFIRM] = enteredPasswordConfirm;
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
                document.getElementById("form-register-email").classList.remove("is-invalid");
                document.getElementById("form-register-login").classList.remove("is-invalid");
                document.getElementById("form-register-password").classList.remove("is-invalid");
                document.getElementById("form-register-password-confirm").classList.remove("is-invalid");
                document.getElementById("form-register").classList.add("was-validated");
            }
            url = CONTROLLER + "?" + COMMAND + "=" + OPEN_LOGIN_PAGE;
            location.assign(url);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            // if (serverError) {};
            document.getElementById("form-register").classList.remove("was-validated");
            
            document.getElementById("form-register-email-invalid-feedback").innerHTML = jqXHR.responseJSON[EMAIL_FEEDBACK];
            document.getElementById("form-register-login-invalid-feedback").innerHTML = jqXHR.responseJSON[LOGIN_FEEDBACK];
            document.getElementById("form-register-password-invalid-feedback").innerHTML = jqXHR.responseJSON[PASSWORD_FEEDBACK];
            document.getElementById("form-register-password-confirm-invalid-feedback").innerHTML = jqXHR.responseJSON[PASSWORD_CONFIRMATION_FEEDBACK];
            
            if (jqXHR.responseJSON[EMAIL_STATUS] == false) {
                document.getElementById("form-register-email").classList.add("is-invalid");
                document.getElementById("form-register-email").classList.remove("is-valid");
            } else {
                document.getElementById("form-register-email").classList.remove("is-invalid");
                document.getElementById("form-register-email").classList.add("is-valid");
            }
            if (jqXHR.responseJSON[LOGIN_STATUS] == false) {
                document.getElementById("form-register-login").classList.add("is-invalid");
                document.getElementById("form-register-login").classList.remove("is-valid");
            } else {
                document.getElementById("form-register-login").classList.remove("is-invalid");
                document.getElementById("form-register-login").classList.add("is-valid");
            }
            if (jqXHR.responseJSON[PASSWORD_STATUS] == false) {
                document.getElementById("form-register-password").classList.remove("is-valid");
                document.getElementById("form-register-password").classList.add("is-invalid");
            } else {
                document.getElementById("form-register-password").classList.remove("is-invalid");
                document.getElementById("form-register-password").classList.add("is-valid");
            }
            if (jqXHR.responseJSON[PASSWORD_CONFIRMATION_STATUS] == false) {
                document.getElementById("form-register-password-confirm").classList.remove("is-valid");
                document.getElementById("form-register-password-confirm").classList.add("is-invalid");
            } else {
                document.getElementById("form-register-password-confirm").classList.remove("is-invalid");
                document.getElementById("form-register-password-confirm").classList.add("is-valid");
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
    let formRegister = document.getElementById("form-register");
    formRegister.addEventListener('submit', function(event) {
        if (formRegister.checkValidity() === false || document.getElementById("form-register-password").value != document.getElementById("form-register-password-confirm").value) {
            document.getElementById("form-register-email-invalid-feedback").innerHTML = STANDARD_EMAIL_FEEDBACK;
            document.getElementById("form-register-login-invalid-feedback").innerHTML = STANDARD_LOGIN_FEEDBACK;
            document.getElementById("form-register-password-invalid-feedback").innerHTML = STANDARD_PASSWORD_FEDDBACK;
            document.getElementById("form-register-password-confirm-invalid-feedback").innerHTML = STANDARD_PASSWORD_CONFIRMATION_FEEDBACK;
            
            if (document.getElementById("form-register-password").value != document.getElementById("form-register-password-confirm").value) {
                document.getElementById("form-register-password").setCustomValidity(PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL);
                document.getElementById("form-register-password-confirm").setCustomValidity(PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL);
                document.getElementById("form-register-password-invalid-feedback").innerHTML = PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL;
                document.getElementById("form-register-password-confirm-invalid-feedback").innerHTML = PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL;
            }

            event.preventDefault();
            event.stopPropagation();
        } else {
            event.preventDefault();
            registerPage.registerUser();
        }
        formRegister.classList.add('was-validated');
      }, false);
});

// document.addEventListener("DOMContentLoaded",() => {
//     let formRegister = document.getElementById("form-register");
//     formRegister.addEventListener('submit', function(event) {
//         if (formRegister.checkValidity() === false) {
//             document.getElementById("form-register-email-invalid-feedback").innerHTML = STANDARD_EMAIL_FEEDBACK;
//             document.getElementById("form-register-login-invalid-feedback").innerHTML = STANDARD_LOGIN_FEEDBACK;
//             document.getElementById("form-register-password-invalid-feedback").innerHTML = STANDARD_PASSWORD_FEDDBACK;
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