function loginUser() {
    let email = document.getElementById("form-login-email")
    let password = document.getElementById("form-login-password")
    let formLogin = document.getElementById("form-login")
    let emailInvalidFeedback = document.getElementById("form-login-email-invalid-feedback")
    let passwordInvalidFeedback =  document.getElementById("form-login-password-invalid-feedback")
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
            if (jqXHR.responseJSON[EMAIL_STATUS] == true && jqXHR.responseJSON[PASSWORD_STATUS] == true) {
                email.classList.remove("is-invalid");
                password.classList.remove("is-invalid");
                formLogin.classList.add("was-validated");
            }
            url = CONTROLLER + "?" + COMMAND + "=" + OPEN_HOME_PAGE;
            location.assign(url);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            // if (serverError) {};
            formLogin.classList.remove("was-validated");
            emailInvalidFeedback.innerHTML = jqXHR.responseJSON[LOGIN_FEEDBACK];
            passwordInvalidFeedback.innerHTML = jqXHR.responseJSON[PASSWORD_FEEDBACK];
            if (jqXHR.responseJSON.email_status == false) {
                email.classList.remove("is-valid");
                email.classList.add("is-invalid");
            } else {
                email.classList.remove("is-invalid");
                email.classList.add("is-valid");
            }
            if (jqXHR.responseJSON.password_status == false) {
                password.classList.remove("is-valid");
                password.classList.add("is-invalid");
            } else {
                password.classList.remove("is-invalid");
                password.classList.add("is-valid");
            }
        }
    });
};

function restorePassword() {
    let url = CONTROLLER + "?" + COMMAND + "=" + OPEN_FORGOT_PASSWORD_PAGE;
    location.assign(url);
}

function openRegisterPage() {
    let url = CONTROLLER + "?" + COMMAND + "=" + OPEN_REGISTER_PAGE;
    location.assign(url);
}

const loginPage = {
    loginUser: loginUser,
    restorePassword: restorePassword,
    openRegisterPage: openRegisterPage
};

$(document).ready(function () {
    let formLogin = document.getElementById("form-login")
    let emailInvalidFeedback = document.getElementById("form-login-email-invalid-feedback")
    let passwordInvalidFeedback =  document.getElementById("form-login-password-invalid-feedback")
    formLogin.addEventListener('submit', function(event) {
        if (formLogin.checkValidity() === false) {
            emailInvalidFeedback.innerHTML = STANDARD_EMAIL_FEEDBACK
            passwordInvalidFeedback.innerHTML = STANDARD_PASSWORD_FEDDBACK
            event.preventDefault();
            event.stopPropagation();
        } else {
            event.preventDefault();
            loginPage.loginUser();
        }
        formLogin.classList.add('was-validated');
      }, false);
});


