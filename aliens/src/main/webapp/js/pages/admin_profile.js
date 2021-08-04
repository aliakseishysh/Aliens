import { AlienForm } from "../modules/alien.js";
import { BanUnbanForm } from "../modules/ban_unban.js";
import { PromoteDemoteForm } from "../modules/promote_demote.js";
import { changeLocationIfUndefined } from "../modules/util.js"

let banUnbanForm;
let banUnbanFormLogin;
let banUnbanFormLoginInvalidFeedback;
let banUnbanFormDaysInBan;
let banUnbanFormDaysInBanInvalidFeedback;

let promoteDemoteForm;
let promoteDemoteFormLogin;
let promoteDemoteFormLoginInvalidFeedback;

let alienCreateForm;
let alienCreateFormName;
let alienCreateFormDescriptionSmall;
let alienCreateFormDescriptionFull;
let alienCreateFormImage;
let alienCreateFormImageLabel;
let alienCreateFormNameInvalidFeedback;
let alienCreateFormDescriptionSmallInvalidFeedback;
let alienCreateFormDescriptionFullInvalidFeedback;
let alienCreateFormImageInvalidFeedback;

/** @type {AlienForm} */
let alienForm;
/** @type {BanUnbanForm} */
let banUnbanFormObject;
/** @type {PromoteDemoteForm} */
let promoteDemoteFormObject;

$(document).ready(function () {
    banUnbanForm = document.getElementById("form-user-ban-unban");
    banUnbanFormLogin = document.getElementById("form-user-ban-unban-login");
    banUnbanFormLoginInvalidFeedback = document.getElementById("form-user-ban-unban-login-invalid-feedback");
    banUnbanFormDaysInBan = document.getElementById("form-user-ban-unban-days-in-ban");
    banUnbanFormDaysInBanInvalidFeedback = document.getElementById("form-user-ban-unban-days-in-ban-invalid-feedback");

    promoteDemoteForm = document.getElementById("form-promote-demote-user");
    promoteDemoteFormLogin = document.getElementById("form-promote-demote-user-login");
    promoteDemoteFormLoginInvalidFeedback = document.getElementById("form-promote-demote-user-login-invalid-feedback");

    alienCreateForm = document.getElementById("form-alien-create");
    alienCreateFormName = document.getElementById("form-alien-create-name");
    alienCreateFormDescriptionSmall = document.getElementById("form-alien-create-description-small");
    alienCreateFormDescriptionFull = document.getElementById("form-alien-create-description-full");
    alienCreateFormImage = document.getElementById("form-alien-create-image");
    alienCreateFormImageLabel = document.getElementById("form-alien-create-image-label");
    alienCreateFormNameInvalidFeedback = document.getElementById("form-alien-create-name-invalid-feedback");
    alienCreateFormDescriptionSmallInvalidFeedback = document.getElementById("form-alien-create-description-small-invalid-feedback");
    alienCreateFormDescriptionFullInvalidFeedback = document.getElementById("form-alien-create-description-full-invalid-feedback");
    alienCreateFormImageInvalidFeedback = document.getElementById("form-alien-create-image-invalid-feedback");

    alienForm = new AlienForm(alienCreateForm, alienCreateFormName, alienCreateFormDescriptionSmall, alienCreateFormDescriptionFull, 
        alienCreateFormImage, alienCreateFormImageLabel, alienCreateFormNameInvalidFeedback, alienCreateFormDescriptionSmallInvalidFeedback, 
        alienCreateFormDescriptionFullInvalidFeedback, alienCreateFormImageInvalidFeedback, null, null, null, null);
    banUnbanFormObject = new BanUnbanForm(banUnbanForm, banUnbanFormLogin, banUnbanFormDaysInBan, banUnbanFormLoginInvalidFeedback, 
        banUnbanFormDaysInBanInvalidFeedback);
    promoteDemoteFormObject = new PromoteDemoteForm(promoteDemoteForm, promoteDemoteFormLogin, promoteDemoteFormLoginInvalidFeedback);
});

function banUser() {
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
            banUnbanFormObject.setFeedback(jqXHR.responseJSON[LOGIN_STATUS], jqXHR.responseJSON[DAYS_TO_BAN_STATUS], 
                jqXHR.responseJSON[LOGIN_FEEDBACK], jqXHR.responseJSON[DAYS_TO_BAN_FEEDBACK]);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            changeLocationIfUndefined(jqXHR);
            banUnbanFormObject.removeValidationClasses();
            banUnbanFormObject.setFeedback(jqXHR.responseJSON[LOGIN_STATUS], jqXHR.responseJSON[DAYS_TO_BAN_STATUS], 
                jqXHR.responseJSON[LOGIN_FEEDBACK], jqXHR.responseJSON[DAYS_TO_BAN_FEEDBACK]);
        }
    });
};

function unbanUser() {
    let enteredLogin = banUnbanFormLogin.value;
    let daysToBan = banUnbanFormDaysInBan.value;
    let data = {};
    data[LOGIN] = enteredLogin;
    data[DAYS_TO_BAN] = daysToBan;
    let url = CONTROLLER + "?" + COMMAND + "=" + UNBAN_USER;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            banUnbanFormObject.setFeedback(jqXHR.responseJSON[LOGIN_STATUS], null, 
                jqXHR.responseJSON[LOGIN_FEEDBACK], null);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            changeLocationIfUndefined(jqXHR);
            banUnbanFormObject.removeValidationClasses();
            banUnbanFormObject.setFeedback(jqXHR.responseJSON[LOGIN_STATUS], null, 
                jqXHR.responseJSON[LOGIN_FEEDBACK], null);
        }
    });
};

function promoteUser() {
    let enteredLogin = promoteDemoteFormLogin.value;
    let data = {};
    data[LOGIN] = enteredLogin;
    let url = CONTROLLER + "?" + COMMAND + "=" + PROMOTE_USER;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            promoteDemoteFormObject.setFeedback(jqXHR.responseJSON[LOGIN_STATUS], jqXHR.responseJSON[LOGIN_FEEDBACK]);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            changeLocationIfUndefined(jqXHR);
            promoteDemoteFormObject.removeValidationClasses();
            promoteDemoteFormObject.setFeedback(jqXHR.responseJSON[LOGIN_STATUS], jqXHR.responseJSON[LOGIN_FEEDBACK]);
        }
    });
};

function demoteAdmin() {
    let enteredLogin = promoteDemoteFormLogin.value;
    let data = {};
    data[LOGIN] = enteredLogin;
    let url = CONTROLLER + "?" + COMMAND + "=" + DEMOTE_ADMIN;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            promoteDemoteFormObject.setFeedback(jqXHR.responseJSON[LOGIN_STATUS], jqXHR.responseJSON[LOGIN_FEEDBACK]);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            changeLocationIfUndefined(jqXHR);
            promoteDemoteFormObject.removeValidationClasses();
            promoteDemoteFormObject.setFeedback(jqXHR.responseJSON[LOGIN_STATUS], jqXHR.responseJSON[LOGIN_FEEDBACK]);
        }
    });
};

function addNewAlien() {
    let formData = new FormData();
    formData.append(ALIEN_NAME, alienCreateFormName.value);
    formData.append(ALIEN_SMALL_DESCRIPTION, alienCreateFormDescriptionSmall.value);
    formData.append(ALIEN_FULL_DESCRIPTION, alienCreateFormDescriptionFull.value);
    formData.append(ALIEN_NEW_IMAGE, alienCreateFormImage.files[0]);
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
            alienForm.setFeedbackInfo(jqXHR.responseJSON[ALIEN_NAME_STATUS], 
                jqXHR.responseJSON[ALIEN_SMALL_DESCRIPTION_STATUS],
                jqXHR.responseJSON[ALIEN_FULL_DESCRIPTION_STATUS],
                jqXHR.responseJSON[ALIEN_NAME_FEEDBACK], 
                jqXHR.responseJSON[ALIEN_SMALL_DESCRIPTION_FEEDBACK],
                jqXHR.responseJSON[ALIEN_FULL_DESCRIPTION_FEEDBACK],
                jqXHR.responseJSON[ALIEN_NAME_FEEDBACK], 
                jqXHR.responseJSON[ALIEN_SMALL_DESCRIPTION_FEEDBACK],
                jqXHR.responseJSON[ALIEN_FULL_DESCRIPTION_FEEDBACK]
            );
            alienForm.setFeedbackImage(jqXHR.responseJSON[IMAGE_STATUS], 
                jqXHR.responseJSON[IMAGE_FEEDBACK],
                jqXHR.responseJSON[IMAGE_FEEDBACK]
            );
        },
        error: function (jqXHR, textStatus, errorThrown) {
            changeLocationIfUndefined(jqXHR);
            alienForm.removeValidationClasses();
            alienForm.setFeedbackInfo(jqXHR.responseJSON[ALIEN_NAME_STATUS], 
                jqXHR.responseJSON[ALIEN_SMALL_DESCRIPTION_STATUS],
                jqXHR.responseJSON[ALIEN_FULL_DESCRIPTION_STATUS],
                jqXHR.responseJSON[ALIEN_NAME_FEEDBACK], 
                jqXHR.responseJSON[ALIEN_SMALL_DESCRIPTION_FEEDBACK],
                jqXHR.responseJSON[ALIEN_FULL_DESCRIPTION_FEEDBACK],
                jqXHR.responseJSON[ALIEN_NAME_FEEDBACK], 
                jqXHR.responseJSON[ALIEN_SMALL_DESCRIPTION_FEEDBACK],
                jqXHR.responseJSON[ALIEN_FULL_DESCRIPTION_FEEDBACK]
            );
            alienForm.setFeedbackImage(jqXHR.responseJSON[IMAGE_STATUS], 
                jqXHR.responseJSON[IMAGE_FEEDBACK],
                jqXHR.responseJSON[IMAGE_FEEDBACK]
            );
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

/**
 * reset forms
 */
$(function() {
    banUnbanForm.reset();
    promoteDemoteForm.reset();
    alienCreateForm.reset();
});

/**
 * BanUnban form processing
 */
$(document).ready(function () {
    banUnbanForm.addEventListener('submit', function(event) {
        banUnbanFormObject.removeValidationClasses();
        if (event.submitter.id == "form-user-ban-unban-button-banuser") {
            // in this case need to check 2 form fields
            banUnbanFormDaysInBan.setAttribute("required", "");
            if (banUnbanForm.checkValidity() === false || banUnbanFormDaysInBan.validity.valid && banUnbanFormDaysInBan.value <= 0) {
                banUnbanFormObject.setFeedback(banUnbanFormLogin.validity.valid, banUnbanFormDaysInBan.validity.valid && banUnbanFormDaysInBan.value <= 0, 
                    STANDARD_LOGIN_FEEDBACK, STANDARD_DAYS_TO_BAN_FEEDBACK);
                event.preventDefault();
                event.stopPropagation();
            } else {
                event.preventDefault();
                adminPage.banUser();
            }
        } else if (event.submitter.id = "form-user-ban-unban-button-unbanuser") {
            // in this case need to ckeck only login form field
            banUnbanFormDaysInBan.removeAttribute("required")
            if (banUnbanForm.checkValidity() === false) {
                banUnbanFormObject.setFeedback(false, true, STANDARD_LOGIN_FEEDBACK, "");
                event.preventDefault();
                event.stopPropagation();
            } else {
                event.preventDefault();
                adminPage.unbanUser();
            }
        }
    })
    banUnbanFormLogin.addEventListener('input', function(event) {
        banUnbanFormObject.removeLoginValidationClasses();
    });
    banUnbanFormDaysInBan.addEventListener('input', function(event) {
        banUnbanFormObject.removeDaysInBanValidationClasses();
    })
});

/**
 * Promote demote processing
 */
$(document).ready(function () {
    promoteDemoteForm.addEventListener('submit', function(event) {
        promoteDemoteFormObject.removeValidationClasses();
        if (promoteDemoteForm.checkValidity() === false) {
            promoteDemoteFormObject.setFeedback(false, STANDARD_LOGIN_FEEDBACK);
            event.preventDefault();
            event.stopPropagation();
        } else {
            event.preventDefault();
            if (event.submitter.id == "form-promote-demote-user-button-promoteuser") {
                adminPage.promoteUser();
            } else if (event.submitter.id = "form-promote-demote-user-button-demoteadmin") {
                adminPage.demoteAdmin();
            }
        }
    })
    promoteDemoteForm.addEventListener('input', function(event) {
        promoteDemoteFormObject.removeValidationClasses();
    })
});


/**
 * Add new alien processing
 */
$(document).ready(function () {
    alienCreateForm.addEventListener('submit', function(event) {
        alienForm.removeInfoValidationClasses();
        alienForm.removeImageValidationClasses();
        let validationInfoResult = alienForm.validateInfo();
        let validationImageResult = alienForm.validateImage();
        if(!validationInfoResult.some(element => element == false) && validationImageResult) {
            event.preventDefault();
            adminPage.addNewAlien();
        } else {
            alienForm.setFeedbackInfo(validationInfoResult[0], validationInfoResult[1], validationInfoResult[2], "", "", "", 
            STANDARD_ALIEN_NAME_FEEDBACK, STANDARD_ALIEN_SMALL_DESCRIPTION_FEEDBACK, STANDARD_ALIEN_FULL_DESCRIPTION_FEEDBACK);
            alienForm.setFeedbackImage(validationImageResult, "", STANDARD_IMAGE_FEEDBACK);
            event.preventDefault();
            event.stopPropagation();
        }
    });
    alienCreateFormName.addEventListener('input', function(event) {
        alienForm.removeNameValidationClasses();
    })
    alienCreateFormDescriptionSmall.addEventListener('input', function(event) {
        alienForm.removeSmallDescriptionValidationClasses();
    })
    alienCreateFormDescriptionFull.addEventListener('input', function(event) {
        alienForm.removeFullDescriptionValidationClasses();
    })
    alienCreateFormImage.addEventListener('input', function(event) {
        alienForm.setLabelText();
        alienForm.removeImageValidationClasses();
    })
});
