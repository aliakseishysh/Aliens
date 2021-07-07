function banUser() {
    let banUnbanForm = document.getElementById("form-user-ban-unban")
    let banUnbanFormLogin = document.getElementById("form-user-ban-unban-login")
    let banUnbanFormLoginInvalidFeedback = document.getElementById("form-user-ban-unban-login-invalid-feedback")
    let banUnbanFormDaysInBan = document.getElementById("form-user-ban-unban-days-in-ban")
    let banUnbanFormDaysInBanInvalidFeedback = document.getElementById("form-user-ban-unban-days-in-ban-invalid-feedback")
    let enteredLogin = banUnbanFormLogin.value;
    let daysToBan = banUnbanFormDaysInBan.value;
    let data = {};
    data[LOGIN] = enteredLogin;
    data[DAYS_TO_BAN] = daysToBan;
    let url = CONTROLLER + "?" + COMMAND + "=" + BAN_USER;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            if (jqXHR.responseJSON[UPDATE_EMAIL_RESULT_INFO_STATUS] == true) {

            }

        },
        error: function (jqXHR, textStatus, errorThrown) {
            banUnbanForm.classList.remove("was-validated");
            document.getElementById("form-update-email-email-invalid-feedback").innerHTML = jqXHR.responseJSON[UPDATE_EMAIL_RESULT_INFO_EMAIL_FEEDBACK];
            if (jqXHR.responseJSON[BAN_UNBAN_USER_LOGIN_STATUS] == false) {
                banUnbanFormLogin.classList.add("is-invalid");
                banUnbanFormLogin.classList.remove("is-valid");
            } else {
                banUnbanFormLoginInvalidFeedback.innerHTML = jqXHR.responseJSON[BAN_UNBAN_USER_RESULT_INFO_LOGIN_FEEDBACK]
                banUnbanFormLogin.classList.remove("is-invalid");
                banUnbanFormLogin.classList.add("is-valid");
            }
            if (jqXHR.responseJSON[BAN_UNBAN_USER_DAYS_TO_BAN_STATUS] == false) {
                banUnbanFormDaysInBan.classList.add("is-invalid");
                banUnbanFormDaysInBan.classList.remove("is-valid");
            } else {
                banUnbanFormDaysInBanInvalidFeedback  = jqXHR.responseJSON[BAN_UNBAN_USER_RESULT_INFO_DAYS_TO_BAN_FEEDBACK]
                banUnbanFormDaysInBan.classList.remove("is-invalid");
                banUnbanFormDaysInBan.classList.add("is-valid");
            }
        }
    });
};

function unbanUser() {
    let banUnbanForm = document.getElementById("form-user-ban-unban")
    let banUnbanFormLogin = document.getElementById("form-user-ban-unban-login")
    let banUnbanFormLoginInvalidFeedback = document.getElementById("form-user-ban-unban-login-invalid-feedback")
    let banUnbanFormDaysInBan = document.getElementById("form-user-ban-unban-days-in-ban")
    let banUnbanFormDaysInBanInvalidFeedback = document.getElementById("form-user-ban-unban-days-in-ban-invalid-feedback")
    let enteredLogin = banUnbanFormLogin.value;
    let daysToBan = banUnbanFormDaysInBan.value;
    let data = {};
    data[LOGIN] = enteredLogin;
    data[DAYS_TO_BAN] = daysToBan;
    var url = CONTROLLER + "?" + COMMAND + "=" + UNBAN_USER;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            if (jqXHR.responseJSON[UPDATE_EMAIL_RESULT_INFO_STATUS] == true) {
        
            }

        },
        error: function (jqXHR, textStatus, errorThrown) {
            banUnbanForm.classList.remove("was-validated");
            if (jqXHR.responseJSON[BAN_UNBAN_USER_LOGIN_STATUS] == false) {
                banUnbanFormLogin.classList.add("is-invalid");
                banUnbanFormLogin.classList.remove("is-valid");
            } else {
                banUnbanFormLoginInvalidFeedback.innerHTML = jqXHR.responseJSON[BAN_UNBAN_USER_RESULT_INFO_LOGIN_FEEDBACK]
                banUnbanFormLogin.classList.remove("is-invalid");
                banUnbanFormLogin.classList.add("is-valid");
            }
        }
    });
};

function promoteUser() {
    let promoteDemoteForm = document.getElementById("form-promote-demote-user")
    let promoteDemoteFormLogin = document.getElementById("form-promote-demote-user-login")
    let promoteDemoteFormLoginInvalidFeedback = document.getElementById("form-promote-demote-user-login-invalid-feedback")
    let enteredLogin = promoteDemoteFormLogin.value;
    var data = {};
    data[LOGIN] = enteredLogin;
    var url = CONTROLLER + "?" + COMMAND + "=" + PROMOTE_USER;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            if (jqXHR.responseJSON[PROMOTE_DEMOTE_USER_LOGIN_STATUS] == true) {
        
            }

        },
        error: function (jqXHR, textStatus, errorThrown) {
            promoteDemoteForm.classList.remove("was-validated");
            if (jqXHR.responseJSON[PROMOTE_DEMOTE_USER_LOGIN_STATUS] == false) {
                promoteDemoteFormLogin.classList.add("is-invalid");
                promoteDemoteFormLogin.classList.remove("is-valid");
            } else {
                promoteDemoteFormLoginInvalidFeedback.innerHTML = jqXHR.responseJSON[PROMOTE_DEMOTE_USER_RESULT_INFO_LOGIN_FEEDBACK]
                promoteDemoteFormLogin.classList.remove("is-invalid");
                promoteDemoteFormLogin.classList.add("is-valid");
            }
        }
    });
};

function demoteAdmin() {
    let promoteDemoteForm = document.getElementById("form-promote-demote-user")
    let promoteDemoteFormLogin = document.getElementById("form-promote-demote-user-login")
    let promoteDemoteFormLoginInvalidFeedback = document.getElementById("form-promote-demote-user-login-invalid-feedback")
    let enteredLogin = promoteDemoteFormLogin.value;
    var data = {};
    data[LOGIN] = enteredLogin;
    var url = CONTROLLER + "?" + COMMAND + "=" + DEMOTE_ADMIN;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            if (jqXHR.responseJSON[PROMOTE_DEMOTE_USER_LOGIN_STATUS] == true) {
        
            }

        },
        error: function (jqXHR, textStatus, errorThrown) {
            promoteDemoteForm.classList.remove("was-validated");
            if (jqXHR.responseJSON[PROMOTE_DEMOTE_USER_LOGIN_STATUS] == false) {
                promoteDemoteFormLogin.classList.add("is-invalid");
                promoteDemoteFormLogin.classList.remove("is-valid");
            } else {
                promoteDemoteFormLoginInvalidFeedback.innerHTML = jqXHR.responseJSON[PROMOTE_DEMOTE_USER_RESULT_INFO_LOGIN_FEEDBACK]
                promoteDemoteFormLogin.classList.remove("is-invalid");
                promoteDemoteFormLogin.classList.add("is-valid");
            }
        }
    });
};

function addNewAlien() {
    var alienName = document.getElementById("alien-create-form-name").value;
    var alienSmallDescription = document.getElementById("alien-create-form-description-small").value;
    var alienFullDescription = document.getElementById("alien-create-form-description-full").value;
    var files = document.getElementById("alien-create-form-image").files;
    var formData = new FormData();
    formData.append(ALIEN_NAME, alienName);
    formData.append(ALIEN_SMALL_DESCRIPTION, alienSmallDescription);
    formData.append(ALIEN_FULL_DESCRIPTION, alienFullDescription);
    formData.append(ALIEN_NEW_IMAGE, files[0]);
    var url = CONTROLLER + "?" + COMMAND + "=" + ADD_NEW_ALIEN;
    $.ajax({
        url: url,
        type: "POST",
        data: formData,
        cache: false,
        contentType: false,
        processData: false,
        success: function (updateResult) {
            if (updateResult == "true") {
                //$("#alien-create-form").load("/" + PROJECT_NAME + "/" + CONTROLLER + "?" + COMMAND + "=" + LOAD_ALIEN_CREATE_FORM + " #alien-create-form");
                //set invisible to visible validation green
                document.getElementById("alien-create-form-name").value = "";
                document.getElementById("alien-create-form-description-small").value = "";
                document.getElementById("alien-create-form-description-full").value = "";
                document.getElementById("alien-create-form-image").value = "";
            } else if (updateResult == "false") {
                // set invisible to visible validation red
            }
        },
        error: function () {
            // TODO show error
        }
    });
};


const adminPage = {
    banUser: banUser,
    unbanUser: unbanUser,
    promoteUser: promoteUser,
    demoteAdmin: demoteAdmin,
    addNewAlien: addNewAlien
}

$(document).ready(function () {
    let formBanUnban = document.getElementById("form-user-ban-unban")
    let loginField = document.getElementById("form-user-ban-unban-login")
    let daysInBanField = document.getElementById("form-user-ban-unban-days-in-ban")
    let loginFileldInvalidFeedback = document.getElementById("form-user-ban-unban-login-invalid-feedback")
    let daysInBanFieldInvalidFeedback = document.getElementById("form-user-ban-unban-days-in-ban-invalid-feedback")
    formBanUnban.addEventListener('submit', function(event) {
        loginField.classList.remove("is-invalid");
        loginField.classList.remove("is-valid");
        daysInBanField.classList.remove("is-invalid");
        daysInBanField.classList.remove("is-valid");
    
        if (event.submitter.id == "form-user-ban-unban-button-banuser") {
            // in this case need to check 2 form fields

            daysInBanField.setAttribute("required", "")

            if (formBanUnban.checkValidity() === false || daysInBanField.validity.valid && daysInBanField.value < 0) {
                loginFileldInvalidFeedback.innerHTML = BAN_UNBAN_USER_STANDARD_LOGIN_FEEDBACK;
                daysInBanFieldInvalidFeedback.innerHTML = BAN_UNBAN_USER_STANDARD_DAYS_TO_BAN_FEEDBACK;
                event.preventDefault();
                event.stopPropagation();
            } else {
                event.preventDefault();
                adminPage.banUser();
            }
            formBanUnban.classList.add('was-validated');
        } else if (event.submitter.id = "form-user-ban-unban-button-unbanuser") {
            // in this case need to ckeck only login form field

            daysInBanField.removeAttribute("required")

            if (formBanUnban.checkValidity() === false) {
                loginFileldInvalidFeedback.innerHTML = BAN_UNBAN_USER_STANDARD_LOGIN_FEEDBACK;
                loginField.classList.add("is-valid");
                event.preventDefault();
                event.stopPropagation();
            } else {
                event.preventDefault();
                adminPage.unbanUser();
            }
            formBanUnban.classList.add('was-validated');
        }
    })
    formBanUnban.addEventListener('input', function(event) {
        formBanUnban.classList.remove('was-validated');
        loginField.classList.remove("is-invalid");
        loginField.classList.remove("is-valid");
    })
    formBanUnban.addEventListener('input', function(event) {
        formBanUnban.classList.remove('was-validated');
        daysInBanField.classList.remove("is-invalid");
        daysInBanField.classList.remove("is-valid");
    })
});

$(document).ready(function () {
    let formPromoteDemote = document.getElementById("form-promote-demote-user")
    let loginField = document.getElementById("form-promote-demote-user-login")
    let loginFileldInvalidFeedback = document.getElementById("form-promote-demote-user-login-invalid-feedback")
    formPromoteDemote.addEventListener('submit', function(event) {
        loginField.classList.remove("is-invalid");
        loginField.classList.remove("is-valid");
    
        if (event.submitter.id == "form-promote-demote-user-button-promoteuser") {
            if (formPromoteDemote.checkValidity() === false) {
                loginFileldInvalidFeedback.innerHTML = BAN_UNBAN_USER_STANDARD_LOGIN_FEEDBACK;
                event.preventDefault();
                event.stopPropagation();
            } else {
                event.preventDefault();
                adminPage.promoteUser();
            }
            formPromoteDemote.classList.add('was-validated');
        } else if (event.submitter.id = "form-promote-demote-user-button-demoteadmin") {

            if (formPromoteDemote.checkValidity() === false) {
                loginFileldInvalidFeedback.innerHTML = BAN_UNBAN_USER_STANDARD_LOGIN_FEEDBACK;
                event.preventDefault();
                event.stopPropagation();
            } else {
                event.preventDefault();
                adminPage.demoteAdmin();
            }
            formPromoteDemote.classList.add('was-validated');
        }
    })
    formPromoteDemote.addEventListener('input', function(event) {
        formPromoteDemote.classList.remove('was-validated');
        loginField.classList.remove("is-invalid");
        loginField.classList.remove("is-valid");
    })
});