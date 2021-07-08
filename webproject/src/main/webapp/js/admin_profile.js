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

    let formAlienCreate = document.getElementById("form-alien-create");
    let name = document.getElementById("form-alien-create-name");
    let descriptionSmall = document.getElementById("form-alien-create-description-small");
    let descriptionFull = document.getElementById("form-alien-create-description-full");
    let image = document.getElementById("form-alien-create-image");
    
    let nameInvalidFeedback = document.getElementById("form-alien-create-name-invalid-feedback");
    let descriptionSmallInvalidFeedback = document.getElementById("form-alien-create-description-small-invalid-feedback");
    let descriptionFullInvalidFeedback = document.getElementById("form-alien-create-description-full-invalid-feedback");
    let imageInvalidFeedback = document.getElementById("form-alien-create-image-invalid-feedback");
    let imageLabel = document.getElementById("form-alien-create-image-label");

    let formData = new FormData();
    formData.append(ALIEN_NAME, name.value);
    formData.append(ALIEN_SMALL_DESCRIPTION, descriptionSmall.value);
    formData.append(ALIEN_FULL_DESCRIPTION, descriptionFull.value);
    formData.append(ALIEN_NEW_IMAGE, image.files[0]);
    let url = CONTROLLER + "?" + COMMAND + "=" + ADD_NEW_ALIEN;
    $.ajax({
        url: url,
        type: "POST",
        data: formData,
        cache: false,
        contentType: false,
        processData: false,
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            name.classList.add("is-valid")
            name.classList.remove("is-invalid")
            descriptionSmall.classList.add("is-valid")
            descriptionSmall.classList.remove("is-invalid")
            descriptionFull.classList.add("is-valid")
            descriptionFull.classList.remove("is-invalid")
            image.classList.add("is-valid")
            image.classList.remove("is-invalid")
            formAlienCreate.classList.add("was-validated")
            // document.getElementById("form-update-login-parameter-current-user-login").innerHTML = enteredLogin;
            // document.getElementById("form-update-LOGIN").classList.remove('was-validated');
            // document.getElementById("form-update-LOGIN-LOGIN").classList.remove("is-invalid");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            document.getElementById("form-update-image").classList.remove("was-validated");
            if (jqXHR.responseJSON[ADD_NEW_UPDATE_ALIEN_ALIEN_NAME_STATUS] == false) {
                name.classList.add("is-invalid");
                name.classList.remove("is-valid");
            } else {
                name.classList.remove("is-invalid");
                name.classList.add("is-valid");
                nameInvalidFeedback.innerHTML = jqXHR.responseJSON[ADD_NEW_UPDATE_ALIEN_RESULT_INFO_ALIEN_NAME_FEEDBACK];
            }
            if (jqXHR.responseJSON[ADD_NEW_UPDATE_ALIEN_ALIEN_SMALL_DESCRIPTION_STATUS] == false) {
                descriptionSmall.classList.add("is-invalid");
                descriptionSmall.classList.remove("is-valid");
            } else {
                descriptionSmall.classList.remove("is-invalid");
                descriptionSmall.classList.add("is-valid");
                descriptionSmallInvalidFeedback.innerHTML = jqXHR.responseJSON[ADD_NEW_UPDATE_ALIEN_RESULT_INFO_ALIEN_SMALL_DESCRIPTION_FEEDBACK];
            }
            if (jqXHR.responseJSON[ADD_NEW_UPDATE_ALIEN_ALIEN_FULL_DESCRIPTION_STATUS] == false) {
                descriptionFull.classList.add("is-invalid");
                descriptionFull.classList.remove("is-valid");
            } else {
                descriptionFull.classList.remove("is-invalid");
                descriptionFull.classList.add("is-valid");
                descriptionFullInvalidFeedback.innerHTML = jqXHR.responseJSON[ADD_NEW_UPDATE_ALIEN_RESULT_INFO_ALIEN_FULL_DESCRIPTION_FEEDBACK];
            }
            if (jqXHR.responseJSON[ADD_NEW_UPDATE_ALIEN_ALIEN_IMAGE_STATUS] == false) {
                image.classList.add("is-invalid");
                image.classList.remove("is-valid");
            } else {
                image.classList.remove("is-invalid");
                image.classList.add("is-valid");
                imageInvalidFeedback.innerHTML = jqXHR.responseJSON[ADD_NEW_UPDATE_ALIEN_RESULT_INFO_ALIEN_IMAGE_FEEDBACK];
            }
            formAlienCreate.classList.add("was-validated");
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

$(document).ready(function () {
    let formAlienCreate = document.getElementById("form-alien-create");
    let name = document.getElementById("form-alien-create-name");
    let descriptionSmall = document.getElementById("form-alien-create-description-small");
    let descriptionFull = document.getElementById("form-alien-create-description-full");
    let image = document.getElementById("form-alien-create-image");
    
    let nameInvalidFeedback = document.getElementById("form-alien-create-name-invalid-feedback");
    let descriptionSmallInvalidFeedback = document.getElementById("form-alien-create-description-small-invalid-feedback");
    let descriptionFullInvalidFeedback = document.getElementById("form-alien-create-description-full-invalid-feedback");
    let imageInvalidFeedback = document.getElementById("form-alien-create-image-invalid-feedback");
    let imageLabel = document.getElementById("form-alien-create-image-label");

    formAlienCreate.addEventListener('submit', function(event) {
        imageLabel.innerHTML = "Select image"; // TODO change to variable

        name.classList.remove("is-invalid");
        name.classList.remove("is-valid");
        descriptionSmall.classList.remove("is-invalid");
        descriptionSmall.classList.remove("is-valid");
        descriptionFull.classList.remove("is-invalid");
        descriptionFull.classList.remove("is-valid");
        image.classList.remove("is-invalid");
        image.classList.remove("is-valid");

        formAlienCreate.classList.remove("was-validated");

        let files = image.files;
        let nameCheckResult = false;
        let descriptionSmallCheckResult = false;
        let descriptionFullCheckResult = false;
        let imageCheckResult = false;
        let validExtensions = ["image/jpg", "image/jpeg", "image/png"];

        if (name.value != "" && name.value.match(name.getAttribute("pattern"))) {
            nameCheckResult = true
            name.classList.add("is-valid")
        }
        if (descriptionSmall.value != "" && descriptionSmall.value.match(descriptionSmall.getAttribute("pattern"))) {
            descriptionSmallCheckResult = true
            descriptionSmall.classList.add("is-valid")
        }
        if (descriptionFull.value != "" && descriptionFull.value.replaceAll('\n', '\\n').match(descriptionFull.getAttribute("pattern"))) {
            descriptionFullCheckResult = true
            descriptionFull.classList.add("is-valid")
        }
        if (files.length == 1) {
            let file = files[0];
            
            if (file.size <= 1000000) { // bytes
                for (let i = 0; i < validExtensions.length; i++) {
                    if (validExtensions[i] == file.type) {
                        imageCheckResult = true
                        image.classList.add("is-valid")
                        break
                    }
                }    
            }
        }

        if (!nameCheckResult || !descriptionSmallCheckResult || !descriptionFullCheckResult || !imageCheckResult) {
            if (!nameCheckResult) {
                nameInvalidFeedback.innerHTML = ADD_NEW_UPDATE_ALIEN_STANDARD_ALIEN_NAME_FEEDBACK;
                name.classList.add("is-invalid");
            }
            if (!descriptionSmallCheckResult) {
                descriptionSmallInvalidFeedback.innerHTML = ADD_NEW_UPDATE_ALIEN_STANDARD_ALIEN_SMALL_DESCRIPTION_FEEDBACK;
                descriptionSmall.classList.add("is-invalid");
            }
            if (!descriptionFullCheckResult) {
                descriptionFullInvalidFeedback.innerHTML = ADD_NEW_UPDATE_ALIEN_STANDARD_ALIEN_FULL_DESCRIPTION_FEEDBACK;
                descriptionFull.classList.add("is-invalid");
            }
            if (!imageCheckResult) {
                imageInvalidFeedback.innerHTML = ADD_NEW_UPDATE_ALIEN_STANDARD_ALIEN_IMAGE_FEEDBACK;
                image.classList.add("is-invalid");
            }
        // formAlienCreate.classList.add('was-validated');
           // document.getElementById("form-alien-create-image").setCustomValidity(UPDATE_IMAGE_RESULT_INFO_FEEDBACK_INVALID_IMAGE);
            event.preventDefault();
            event.stopPropagation();
        } else {
            event.preventDefault();
            document.getElementById("form-alien-create-image-label").innerHTML = files[0].name;
            adminPage.addNewAlien();
        }
      }, false);
      name.addEventListener('input', function(event) {
        formAlienCreate.classList.remove('was-validated');
        name.classList.remove("is-invalid");
        name.classList.remove("is-valid");
      })
      descriptionSmall.addEventListener('input', function(event) {
        formAlienCreate.classList.remove('was-validated');
        descriptionSmall.classList.remove("is-invalid");
        descriptionSmall.classList.remove("is-valid");
      })
      descriptionFull.addEventListener('input', function(event) {
        formAlienCreate.classList.remove('was-validated');
        descriptionFull.classList.remove("is-invalid");
        descriptionFull.classList.remove("is-valid");
      })
      image.addEventListener('input', function(event) {
        imageLabel.innerHTML = image.files[0].name;
        formAlienCreate.classList.remove('was-validated');
        image.classList.remove("is-invalid");
        image.classList.remove("is-valid");
      })
});