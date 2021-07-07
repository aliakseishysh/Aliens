function loginUser() {
    let enteredEmail = document.getElementById("form-login-email").value;
    let enteredPassword =  document.getElementById("form-login-password").value;
    let data = {};
    data[EMAIL] = enteredEmail;
    data[PASSWORD] = enteredPassword;
    let url = CONTROLLER + "?" + COMMAND + "=" + LOGIN_USER;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            if (jqXHR.responseJSON[LOGIN_RESULT_INFO_EMAIL_STATUS] == true && jqXHR.responseJSON[LOGIN_RESULT_INFO_PASSWORD_STATUS] == true) {
                document.getElementById("form-login-email").classList.remove("is-invalid");
                document.getElementById("form-login-password").classList.remove("is-invalid");
                document.getElementById("form-login").classList.add("was-validated");
            }
            url = CONTROLLER + "?" + COMMAND + "=" + OPEN_HOME_PAGE;
            location.assign(url);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            // if (serverError) {};
            document.getElementById("form-login").classList.remove("was-validated");
            document.getElementById("form-login-email-invalid-feedback").innerHTML = jqXHR.responseJSON[LOGIN_RESULT_INFO_EMAIL_FEEDBACK];
            document.getElementById("form-login-password-invalid-feedback").innerHTML = jqXHR.responseJSON[LOGIN_RESULT_INFO_PASSWORD_FEEDBACK];
            if (jqXHR.responseJSON.email_status == false) {
                document.getElementById("form-login-email").classList.remove("is-valid");
                document.getElementById("form-login-email").classList.add("is-invalid");
            } else {
                document.getElementById("form-login-email").classList.remove("is-invalid");
                document.getElementById("form-login-email").classList.add("is-valid");
            }
            if (jqXHR.responseJSON.password_status == false) {
                document.getElementById("form-login-password").classList.remove("is-valid");
                document.getElementById("form-login-password").classList.add("is-invalid");
            } else {
                document.getElementById("form-login-password").classList.remove("is-invalid");
                document.getElementById("form-login-password").classList.add("is-valid");
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
    let formLogin = document.getElementById("form-login");
    formLogin.addEventListener('submit', function(event) {
        if (formLogin.checkValidity() === false) {
            document.getElementById("form-login-email-invalid-feedback").innerHTML = STANDARD_EMAIL_FEEDBACK;
            document.getElementById("form-login-password-invalid-feedback").innerHTML = STANDARD_PASSWORD_FEDDBACK;
            event.preventDefault();
            event.stopPropagation();
        } else {
            event.preventDefault();
            loginPage.loginUser();
        }
        formLogin.classList.add('was-validated');
      }, false);
});


