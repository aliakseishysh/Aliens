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
});

const WAS_VALIDATED_CLASS = "was-validated";
const IS_VALID_CLASS = "is-valid";
const IS_INVALID_CLASS = "is-invalid";


class BanUnbanForm {

    constructor(){
    }

    setFeedback(isLoginCorrect, isDaysInBanCorrect, loginFeedback, daysInBanFeedback) {
        if (isLoginCorrect) {
            this.#setLoginValid();
            this.#setLoginValidFeedback(loginFeedback);
        } else {
            this.#setLoginInvalid();
            this.#setLoginInvalidFeedback(loginFeedback);
        }
        if (isDaysInBanCorrect == true || isDaysInBanCorrect == null) {
            this.#setDaysInBanValid();
            this.#setDaysInBanValidFeedback(daysInBanFeedback);
        } else if (isDaysInBanCorrect == false) {
            this.#setDaysInBanInvalid();
            this.#setDaysInBanInvalidFeedback(daysInBanFeedback);
        }
    }

    removeLoginValidationClasses(){
        banUnbanForm.classList.remove(WAS_VALIDATED_CLASS);
        banUnbanFormLogin.classList.remove(IS_VALID_CLASS);
        banUnbanFormLogin.classList.remove(IS_INVALID_CLASS);
    }

    removeDaysInBanValidationClasses(){
        banUnbanForm.classList.remove(WAS_VALIDATED_CLASS);
        banUnbanFormDaysInBan.classList.remove(IS_VALID_CLASS);
        banUnbanFormDaysInBan.classList.remove(IS_INVALID_CLASS);
    }

    removeValidationClasses() {
        banUnbanForm.classList.remove(WAS_VALIDATED_CLASS);
        banUnbanFormLogin.classList.remove(IS_VALID_CLASS);
        banUnbanFormDaysInBan.classList.remove(IS_VALID_CLASS);
        banUnbanFormLogin.classList.remove(IS_INVALID_CLASS);
        banUnbanFormDaysInBan.classList.remove(IS_INVALID_CLASS);
    }
    
    #setLoginValid() {
        banUnbanFormLogin.classList.add(IS_VALID_CLASS);
    }

    #setLoginInvalid() {
        banUnbanFormLogin.classList.add(IS_INVALID_CLASS);
    }

    #setDaysInBanValid() {
        banUnbanFormDaysInBan.classList.add(IS_VALID_CLASS);
    }

    #setDaysInBanInvalid() {
        banUnbanFormDaysInBan.classList.add(IS_INVALID_CLASS);
    }

    #setLoginInvalidFeedback(invalidFeedback) {
        banUnbanFormLoginInvalidFeedback.innerHTML = invalidFeedback;
    }

    #setLoginValidFeedback(validFeedback) {

    }

    #setDaysInBanInvalidFeedback(invalidFeedback) {

    }

    #setDaysInBanValidFeedback(validFeedback) {
        
    }

}

class PromoteDemoteForm {

    constructor(){
    }

    setFeedback(isLoginCorrect, loginFeedback) {
        if (isLoginCorrect) {
            this.#setLoginValid();
            this.#setLoginValidFeedback(loginFeedback);
        } else {
            this.#setLoginInvalid();
            this.#setLoginInvalidFeedback(loginFeedback);
        }
    }
    
    removeValidationClasses() {
        promoteDemoteForm.classList.remove(WAS_VALIDATED_CLASS);
        promoteDemoteFormLogin.classList.remove(IS_VALID_CLASS);
        promoteDemoteFormLogin.classList.remove(IS_INVALID_CLASS);
    }

    #setLoginValid() {
        promoteDemoteFormLogin.classList.add(IS_VALID_CLASS);
    }

    #setLoginInvalid() {
        promoteDemoteFormLogin.classList.add(IS_INVALID_CLASS);
    }

    #setLoginInvalidFeedback(invalidFeedback) {
        promoteDemoteFormLoginInvalidFeedback.innerHTML = invalidFeedback;
    }

    #setLoginValidFeedback(validFeedback) {

    }

}

class AlienCreateForm {
    
    constructor(){
    }

    setFeedback(isNameCorrect, isSmallDescriptionCorrect, isFullDescriptionCorrect, isImageCorrect, 
        nameFeedback, smallDescriptionFeedback, fullDescriptionFeedback, imageFeedback) {
        if (isNameCorrect) {
            this.#setNameValid();
            this.#setNameValidFeedback(nameFeedback);
        } else {
            this.#setNameInvalid();
            this.#setNameInvalidFeedback(nameFeedback);
        }
        if (isSmallDescriptionCorrect) {
            this.#setDescriptionSmallValid();
            this.#setDescriptionSmallValidFeedback(smallDescriptionFeedback);
        } else {
            this.#setDescriptionSmallInvalid();
            this.#setDescriptionSmallInvalidFeedback(smallDescriptionFeedback);
        }
        if (isFullDescriptionCorrect) {
            this.#setDescriptionFullValid();
            this.#setDescriptionFullValidFeedback(fullDescriptionFeedback);
        } else {
            this.#setDescriptionFullInvalid();
            this.#setDescriptionFullInvalidFeedback(fullDescriptionFeedback);
        }
        if (isImageCorrect) {
            this.#setImageValid();
            this.#setImageValidFeedback(imageFeedback);
        } else {
            this.#setImageInvalid();
            this.#setImageInvalidFeedback(imageFeedback);
        }
    }
    
    removeValidationClasses() {
        alienCreateForm.classList.remove(WAS_VALIDATED_CLASS);
        alienCreateFormName.classList.remove(IS_VALID_CLASS);
        alienCreateFormDescriptionSmall.classList.remove(IS_VALID_CLASS);
        alienCreateFormDescriptionFull.classList.remove(IS_VALID_CLASS);
        alienCreateFormImage.classList.remove(IS_VALID_CLASS);
        alienCreateFormName.classList.remove(IS_INVALID_CLASS);
        alienCreateFormDescriptionSmall.classList.remove(IS_INVALID_CLASS);
        alienCreateFormDescriptionFull.classList.remove(IS_INVALID_CLASS);
        alienCreateFormImage.classList.remove(IS_INVALID_CLASS);
    }

    removeNameValidationClasses() {
        alienCreateForm.classList.remove(WAS_VALIDATED_CLASS);
        alienCreateFormName.classList.remove(IS_VALID_CLASS);
        alienCreateFormName.classList.remove(IS_INVALID_CLASS);
    }

    removeSmallDescriptionValidationClasses(){
        alienCreateForm.classList.remove(WAS_VALIDATED_CLASS);
        alienCreateFormDescriptionSmall.classList.remove(IS_VALID_CLASS);
        alienCreateFormDescriptionSmall.classList.remove(IS_INVALID_CLASS);
    }

    removeFullDescriptionValidationClasses(){
        alienCreateForm.classList.remove(WAS_VALIDATED_CLASS);
        alienCreateFormDescriptionFull.classList.remove(IS_VALID_CLASS);
        alienCreateFormDescriptionFull.classList.remove(IS_INVALID_CLASS);

    }

    removeImageValidationClasses() {
        alienCreateForm.classList.remove(WAS_VALIDATED_CLASS);
        alienCreateFormImage.classList.remove(IS_VALID_CLASS);
        alienCreateFormImage.classList.remove(IS_INVALID_CLASS);
    }

    validateForm() {
        let files = alienCreateFormImage.files;
        let nameCheckResult = false;
        let descriptionSmallCheckResult = false;
        let descriptionFullCheckResult = false;
        let imageCheckResult = false;
        let validExtensions = ["image/jpg", "image/jpeg", "image/png"];

        if (alienCreateFormName.value != "" && alienCreateFormName.value.match(alienCreateFormName.getAttribute("pattern"))) {
            nameCheckResult = true;
            alienCreateFormName.classList.add("is-valid")
        }
        if (alienCreateFormDescriptionSmall.value != "" && alienCreateFormDescriptionSmall.value.match(alienCreateFormDescriptionSmall.getAttribute("pattern"))) {
            descriptionSmallCheckResult = true;
            alienCreateFormDescriptionSmall.classList.add("is-valid")
        }
        if (alienCreateFormDescriptionFull.value != "" && alienCreateFormDescriptionFull.value.replaceAll('\n', '\\n').match(alienCreateFormDescriptionFull.getAttribute("pattern"))) {
            descriptionFullCheckResult = true;
            alienCreateFormDescriptionFull.classList.add("is-valid")
        }
        if (files.length == 1) {
            let file = files[0];
            
            if (file.size <= 1000000) { // bytes
                for (let i = 0; i < validExtensions.length; i++) {
                    if (validExtensions[i] == file.type) {
                        imageCheckResult = true;
                        alienCreateFormImage.classList.add("is-valid");
                        break;
                    }
                }    
            }
        }
        return [nameCheckResult, descriptionSmallCheckResult, descriptionFullCheckResult, imageCheckResult];
    }

    setLabelText(text) {
        alienCreateFormImageLabel.innerHTML = text;
    }

    #setNameValid() {
        alienCreateFormName.classList.add(IS_VALID_CLASS);
    }

    #setNameInvalid() {
        alienCreateFormName.classList.add(IS_INVALID_CLASS);
    }

    #setNameInvalidFeedback(invalidFeedback) {
        alienCreateFormNameInvalidFeedback.innerHTML = invalidFeedback;
    }

    #setNameValidFeedback(validFeedback) {
    }

    #setDescriptionSmallValid() {
        alienCreateFormDescriptionSmall.classList.add(IS_VALID_CLASS);
    }

    #setDescriptionSmallInvalid() {
        alienCreateFormDescriptionSmall.classList.add(IS_INVALID_CLASS);
    }

    #setDescriptionSmallInvalidFeedback(invalidFeedback) {
        alienCreateFormDescriptionSmallInvalidFeedback.innerHTML = invalidFeedback;
    }

    #setDescriptionSmallValidFeedback(validFeedback) {
    }

    #setDescriptionFullValid() {
        alienCreateFormDescriptionFull.classList.add(IS_VALID_CLASS);
    }

    #setDescriptionFullInvalid() {
        alienCreateFormDescriptionFull.classList.add(IS_INVALID_CLASS);
    }

    #setDescriptionFullInvalidFeedback(invalidFeedback) {
        alienCreateFormDescriptionFullInvalidFeedback.innerHTML = invalidFeedback;
    }

    #setDescriptionFullValidFeedback(validFeedback) {
    }

    #setImageValid() {
        alienCreateFormImage.classList.add(IS_VALID_CLASS);
    }

    #setImageInvalid() {
        alienCreateFormImage.classList.add(IS_INVALID_CLASS);
    }

    #setImageInvalidFeedback(invalidFeedback) {
        alienCreateFormImageInvalidFeedback.innerHTML = invalidFeedback;
    }

    #setImageValidFeedback(validFeedback) {
    }


}

const banUnbanFormObject = new BanUnbanForm();
const promoteDemoteFormObject = new PromoteDemoteForm();
const alienCreateFormObject = new AlienCreateForm();

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
            alienCreateFormObject.setFeedback(jqXHR.responseJSON[ALIEN_NAME_STATUS], qXHR.responseJSON[ALIEN_SMALL_DESCRIPTION_STATUS],
                jqXHR.responseJSON[ALIEN_FULL_DESCRIPTION_STATUS], jqXHR.responseJSON[IMAGE_STATUS],
                jqXHR.responseJSON[ALIEN_NAME_FEEDBACK], jqXHR.responseJSON[ALIEN_SMALL_DESCRIPTION_FEEDBACK],
                jqXHR.responseJSON[ALIEN_FULL_DESCRIPTION_FEEDBACK], jqXHR.responseJSON[IMAGE_FEEDBACK]);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alienCreateFormObject.removeValidationClasses();
            alienCreateFormObject.setFeedback(jqXHR.responseJSON[ALIEN_NAME_STATUS], qXHR.responseJSON[ALIEN_SMALL_DESCRIPTION_STATUS],
                jqXHR.responseJSON[ALIEN_FULL_DESCRIPTION_STATUS], jqXHR.responseJSON[IMAGE_STATUS],
                jqXHR.responseJSON[ALIEN_NAME_FEEDBACK], jqXHR.responseJSON[ALIEN_SMALL_DESCRIPTION_FEEDBACK],
                jqXHR.responseJSON[ALIEN_FULL_DESCRIPTION_FEEDBACK], jqXHR.responseJSON[IMAGE_FEEDBACK]);
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
            banUnbanForm.classList.add(WAS_VALIDATED_CLASS);
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
    })
    banUnbanFormDaysInBan.addEventListener('input', function(event) {
        banUnbanFormObject.removeDaysInBanValidationClasses();
    })
});

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

$(document).ready(function () {
    alienCreateForm.addEventListener('submit', function(event) {
        alienCreateFormObject.removeValidationClasses();
        let validationResult = alienCreateFormObject.validateForm();

        if (!validationResult[0] || !validationResult[1] || !validationResult[2] || !validationResult[3]) {
            alienCreateFormObject.setFeedback(validationResult[0], validationResult[1], validationResult[2], validationResult[3],
                STANDARD_ALIEN_NAME_FEEDBACK, STANDARD_ALIEN_SMALL_DESCRIPTION_FEEDBACK, STANDARD_ALIEN_FULL_DESCRIPTION_FEEDBACK,
                STANDARD_IMAGE_FEEDBACK);
            event.preventDefault();
            event.stopPropagation();
        } else {
            event.preventDefault();
           //  alienCreateFormObject.setLabelText(alienCreateFormImage.files[0].name);
            adminPage.addNewAlien();
        }
      }, false);
      alienCreateFormName.addEventListener('input', function(event) {
        alienCreateFormObject.removeNameValidationClasses();
      })
      alienCreateFormDescriptionSmall.addEventListener('input', function(event) {
        alienCreateFormObject.removeSmallDescriptionValidationClasses();
      })
      alienCreateFormDescriptionFull.addEventListener('input', function(event) {
        alienCreateFormObject.removeFullDescriptionValidationClasses();
      })
      alienCreateFormImage.addEventListener('input', function(event) {
        alienCreateFormObject.setLabelText(alienCreateFormImage.files[0].name);
        alienCreateFormObject.removeImageValidationClasses();
      })
});