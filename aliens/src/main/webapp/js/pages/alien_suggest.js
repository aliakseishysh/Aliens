import { AlienForm } from "../modules/alien.js"
import { changeLocationIfUndefined } from "../modules/util.js"

let formAlienSuggest;
let formAlienSuggestName;
let formAlienSuggestDescriptionSmall;
let formAlienSuggestDescriptionFull;
let formAlienSuggestImage;

let formAlienSuggestNameInvalidFeedback;
let formAlienSuggestDescriptionSmallInvalidFeedback;
let formAlienSuggestDescriptionFullInvalidFeedback;
let formAlienSuggestImageInvalidFeedback;
let formAlienSuggestImageLabel;

let formImageSuggest;
let formImageSuggestName;
let formImageSuggestImage;

let formImageSuggestNameInvalidFeedback;
let formImageSuggestImageInvalidFeedback;
let formImageSuggestImageLabel;

/** @type {AlienForm} */
let alienSuggestForm;
/** @type {AlienForm} */
let alienImageSuggestForm;

/**
 * setup
 */
$(function () {
    formAlienSuggest = document.getElementById("form-alien-suggest");
    formAlienSuggestName = document.getElementById("form-alien-suggest-name");
    formAlienSuggestDescriptionSmall = document.getElementById("form-alien-suggest-description-small");
    formAlienSuggestDescriptionFull = document.getElementById("form-alien-suggest-description-full");
    formAlienSuggestImage = document.getElementById("form-alien-suggest-image");

    formAlienSuggestNameInvalidFeedback = document.getElementById("form-alien-suggest-name-invalid-feedback");
    formAlienSuggestDescriptionSmallInvalidFeedback = document.getElementById("form-alien-suggest-description-small-invalid-feedback");
    formAlienSuggestDescriptionFullInvalidFeedback = document.getElementById("form-alien-suggest-description-full-invalid-feedback");
    formAlienSuggestImageInvalidFeedback = document.getElementById("form-alien-suggest-image-invalid-feedback");
    formAlienSuggestImageLabel = document.getElementById("form-alien-suggest-image-label");

    formImageSuggest = document.getElementById("form-alien-suggest-suggest-image");
    formImageSuggestName = document.getElementById("form-alien-suggest-suggest-image-name");
    formImageSuggestImage = document.getElementById("form-alien-suggest-suggest-image-image");
    
    formImageSuggestNameInvalidFeedback = document.getElementById("form-alien-suggest-suggest-image-name-invalid-feedback");
    formImageSuggestImageInvalidFeedback = document.getElementById("form-alien-suggest-suggest-image-image-invalid-feedback");
    formImageSuggestImageLabel = document.getElementById("form-alien-suggest-suggest-image-image-label");
    
    alienSuggestForm = new AlienForm(formAlienSuggest, formAlienSuggestName, formAlienSuggestDescriptionSmall, formAlienSuggestDescriptionFull, 
        formAlienSuggestImage, formAlienSuggestImageLabel, formAlienSuggestNameInvalidFeedback, formAlienSuggestDescriptionSmallInvalidFeedback,
        formAlienSuggestDescriptionFullInvalidFeedback, formAlienSuggestImageInvalidFeedback, null, null, null, null);


    alienImageSuggestForm = new AlienForm(formImageSuggest, formImageSuggestName, null, null, formImageSuggestImage, formImageSuggestImageLabel,
        formImageSuggestNameInvalidFeedback, null, null, formImageSuggestImageInvalidFeedback, null, null, null, null);


});

function suggestNewAlien() {
    let formData = new FormData();
    formData.append(ALIEN_NAME, formAlienSuggestName.value);
    formData.append(ALIEN_SMALL_DESCRIPTION, formAlienSuggestDescriptionSmall.value);
    formData.append(ALIEN_FULL_DESCRIPTION, formAlienSuggestDescriptionFull.value);
    formData.append(ALIEN_NEW_IMAGE, formAlienSuggestImage.files[0]);
    let url = CONTROLLER + "?" + COMMAND + "=" + SUGGEST_ALIEN;
    $.ajax({
        url: url,
        type: "POST",
        data: formData,
        cache: false,
        contentType: false,
        processData: false,
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            alienSuggestForm.setFeedbackInfo(jqXHR.responseJSON[ALIEN_NAME_STATUS], 
                jqXHR.responseJSON[ALIEN_SMALL_DESCRIPTION_STATUS], 
                jqXHR.responseJSON[ALIEN_FULL_DESCRIPTION_STATUS], 
                jqXHR.responseJSON[ALIEN_NAME_FEEDBACK], 
                jqXHR.responseJSON[ALIEN_SMALL_DESCRIPTION_FEEDBACK], 
                jqXHR.responseJSON[ALIEN_FULL_DESCRIPTION_FEEDBACK],
                jqXHR.responseJSON[ALIEN_NAME_FEEDBACK], 
                jqXHR.responseJSON[ALIEN_SMALL_DESCRIPTION_FEEDBACK], 
                jqXHR.responseJSON[ALIEN_FULL_DESCRIPTION_FEEDBACK]
            );
            alienSuggestForm.setServerFeedbackImage(jqXHR.responseJSON[IMAGE_STATUS], 
                jqXHR.responseJSON[IMAGE_FEEDBACK],
                jqXHR.responseJSON[IMAGE_FEEDBACK]
            );
        },
        error: function (jqXHR, textStatus, errorThrown) {
            changeLocationIfUndefined(jqXHR);
            alienSuggestForm.removeValidationClasses();
            alienSuggestForm.setFeedbackInfo(jqXHR.responseJSON[ALIEN_NAME_STATUS], 
                jqXHR.responseJSON[ALIEN_SMALL_DESCRIPTION_STATUS], 
                jqXHR.responseJSON[ALIEN_FULL_DESCRIPTION_STATUS], 
                jqXHR.responseJSON[ALIEN_NAME_FEEDBACK], 
                jqXHR.responseJSON[ALIEN_SMALL_DESCRIPTION_FEEDBACK],
                jqXHR.responseJSON[ALIEN_FULL_DESCRIPTION_FEEDBACK],
                jqXHR.responseJSON[ALIEN_NAME_FEEDBACK], 
                jqXHR.responseJSON[ALIEN_SMALL_DESCRIPTION_FEEDBACK],
                jqXHR.responseJSON[ALIEN_FULL_DESCRIPTION_FEEDBACK]
            );
            alienSuggestForm.setFeedbackImage(jqXHR.responseJSON[IMAGE_STATUS], 
                jqXHR.responseJSON[IMAGE_FEEDBACK],
                jqXHR.responseJSON[IMAGE_FEEDBACK]
            );
        }
    });
};

function suggestNewAlienImage() {
    let formData = new FormData();
    formData.append(ALIEN_NAME, formImageSuggestName.value);
    formData.append(ALIEN_NEW_IMAGE, formImageSuggestImage.files[0]);
    let url = CONTROLLER + "?" + COMMAND + "=" + SUGGEST_ALIEN_IMAGE;
    $.ajax({
        url: url,
        type: "POST",
        data: formData,
        cache: false,
        contentType: false,
        processData: false,
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            alienImageSuggestForm.setFeedbackName(jqXHR.responseJSON[ALIEN_NAME_STATUS], 
                jqXHR.responseJSON[ALIEN_NAME_FEEDBACK],
                jqXHR.responseJSON[ALIEN_NAME_FEEDBACK]
            );
            alienImageSuggestForm.setFeedbackImage(jqXHR.responseJSON[IMAGE_STATUS], 
                jqXHR.responseJSON[IMAGE_FEEDBACK],
                jqXHR.responseJSON[IMAGE_FEEDBACK]
            );
        },
        error: function (jqXHR, textStatus, errorThrown) {
            changeLocationIfUndefined(jqXHR);
            alienImageSuggestForm.removeNameValidationClasses();
            alienImageSuggestForm.removeImageValidationClasses();
            alienImageSuggestForm.setFeedbackName(jqXHR.responseJSON[ALIEN_NAME_STATUS], 
                jqXHR.responseJSON[ALIEN_NAME_FEEDBACK],
                jqXHR.responseJSON[ALIEN_NAME_FEEDBACK]
            );
            alienImageSuggestForm.setFeedbackImage(jqXHR.responseJSON[IMAGE_STATUS], 
                jqXHR.responseJSON[IMAGE_FEEDBACK],
                jqXHR.responseJSON[IMAGE_FEEDBACK]
            );
        }
    });
};


const suggestAlienPage = {
    suggestNewAlien: suggestNewAlien,
    suggestNewAlienImage: suggestNewAlienImage
}

/**
 * Reset forms
 */
$(function() {
    formAlienSuggest.reset();
    formImageSuggest.reset();
});

/**
 * Suggest Alien Processing
 */
$(function () {
    formAlienSuggest.addEventListener('submit', function(event) {
        alienSuggestForm.removeValidationClasses();
        let validationResult = alienSuggestForm.validate();
        if(!validationResult.some(element => element == false)) {
            event.preventDefault();
            alienSuggestForm.setFeedbackInfo(true, true, true, "", "", "", 
                STANDARD_ALIEN_NAME_FEEDBACK, STANDARD_ALIEN_SMALL_DESCRIPTION_FEEDBACK, STANDARD_ALIEN_FULL_DESCRIPTION_FEEDBACK
            );
            alienSuggestForm.setFeedbackImage(true, "", STANDARD_IMAGE_FEEDBACK);
            alienSuggestForm.setLabelText();
            suggestAlienPage.suggestNewAlien();
        } else {
            alienSuggestForm.setFeedbackInfo(validationResult[0], validationResult[1], validationResult[2], "", "", "", 
                STANDARD_ALIEN_NAME_FEEDBACK, STANDARD_ALIEN_SMALL_DESCRIPTION_FEEDBACK, STANDARD_ALIEN_FULL_DESCRIPTION_FEEDBACK
            );
            alienSuggestForm.setFeedbackImage(validationResult[3], "", STANDARD_IMAGE_FEEDBACK);
            event.preventDefault();
            event.stopPropagation();
        }
    });

    formAlienSuggestName.addEventListener('input', function(event) {
        alienSuggestForm.removeNameValidationClasses();
    });
    formAlienSuggestDescriptionSmall.addEventListener('input', function(event) {
        alienSuggestForm.removeSmallDescriptionValidationClasses();
    });
    formAlienSuggestDescriptionFull.addEventListener('input', function(event) {
        alienSuggestForm.removeFullDescriptionValidationClasses();
    });
    formAlienSuggestImage.addEventListener('input', function(event) {
        alienSuggestForm.setLabelText();
        alienSuggestForm.removeImageValidationClasses();
    });
});

/**
 * Suggest Alien Image Processing
 */
$(document).ready(function () {
    formImageSuggest.addEventListener('submit', function(event) {
        alienImageSuggestForm.removeNameValidationClasses();
        alienImageSuggestForm.removeImageValidationClasses();
        let nameValidationResult = alienImageSuggestForm.validateName();
        let imageValidationResult = alienImageSuggestForm.validateImage();

        if (nameValidationResult && imageValidationResult) {
            event.preventDefault();
            alienImageSuggestForm.setLabelText();
            suggestAlienPage.suggestNewAlienImage();
        } else {
            alienImageSuggestForm.setFeedbackName(nameValidationResult, "", STANDARD_ALIEN_NAME_FEEDBACK);
            alienImageSuggestForm.setFeedbackImage(imageValidationResult, "", STANDARD_IMAGE_FEEDBACK);
            event.preventDefault();
            event.stopPropagation();
        }
    });
    formImageSuggestName.addEventListener('input', function(event) {
        alienImageSuggestForm.removeNameValidationClasses();
    })
    formImageSuggestImage.addEventListener('input', function(event) {
        alienImageSuggestForm.removeImageValidationClasses();
        alienImageSuggestForm.setLabelText();
    })
});