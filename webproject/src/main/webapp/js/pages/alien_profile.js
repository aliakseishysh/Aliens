import { AlienForm } from "../modules/alien.js";
import { Comment } from "../modules/comment.js";
import { initializeCarousel } from "../modules/carousel.js";
import { pagination } from "../modules/pagination.js";
import { rating } from "../modules/raiting.js";
import { changeLocationIfUndefined } from "../modules/util.js"
window.pagination = pagination;
window.rating = rating;

/** @type {AlienForm} */
let alienForm;
/** @type {Comment} */
let commentForm;
let formAlienUpdateInfo;
let formAlienUpdateInfoName;
let formAlienUpdateInfoDescriptionSmall;
let formAlienUpdateInfoDescriptionFull;
let formAlienUpdateInfoNameInvalidFeedback;
let formAlienUpdateInfoDescriptionSmallInvalidFeedback;
let formAlienUpdateInfoDescriptionFullInvalidFeedback;
let formAlienUpdateImage;
let formAlienUpdateImageImage;
let formAlienUpdateImageImageInvalidFeedback;
let formAlienUpdateImageImageLabel;
let formNewComment;
let formNewCommentComment;
let formNewCommentCommentInvalidFeedback;
let currentAlienName;
let currentAlienDescriptionSmall;
let currentAliendDescriptionFull;
let currentAlienImage;

let alienId;
let userId;

let deleteCommentButtons;



$(function() {

    currentAlienName = document.getElementById("alien-name");
    currentAlienDescriptionSmall = document.getElementById("alien-small-description");
    currentAliendDescriptionFull = document.getElementById("alien-big-description");
    currentAlienImage = document.getElementById("alien-image");

    formAlienUpdateInfo = document.getElementById("form-alien-update-info");
    formAlienUpdateInfoName = document.getElementById("form-alien-update-info-name");
    formAlienUpdateInfoDescriptionSmall = document.getElementById("form-alien-update-info-description-small");
    formAlienUpdateInfoDescriptionFull = document.getElementById("form-alien-update-info-description-full");
    
    formAlienUpdateInfoNameInvalidFeedback = document.getElementById("form-alien-update-info-name-invalid-feedback");
    formAlienUpdateInfoDescriptionSmallInvalidFeedback = document.getElementById("form-alien-update-info-description-small-invalid-feedback");
    formAlienUpdateInfoDescriptionFullInvalidFeedback = document.getElementById("form-alien-update-info-description-full-invalid-feedback");

    formAlienUpdateImage = document.getElementById("form-alien-update-image");
    formAlienUpdateImageImage = document.getElementById("form-alien-update-image-image");
    
    formAlienUpdateImageImageInvalidFeedback = document.getElementById("form-alien-update-image-image-invalid-feedback");
    formAlienUpdateImageImageLabel = document.getElementById("form-alien-update-image-image-label");

    alienForm = new AlienForm(formAlienUpdateInfo, formAlienUpdateInfoName, formAlienUpdateInfoDescriptionSmall, 
        formAlienUpdateInfoDescriptionFull, formAlienUpdateImageImage, formAlienUpdateImageImageLabel, formAlienUpdateInfoNameInvalidFeedback, 
        formAlienUpdateInfoDescriptionSmallInvalidFeedback, formAlienUpdateInfoDescriptionFullInvalidFeedback, formAlienUpdateImageImageInvalidFeedback);

    alienId = document.getElementById("alien-id-hidden").innerHTML;
    userId = document.getElementById("currentUserId").innerText;

    formNewComment = document.getElementById("form-new-comment");
    formNewCommentComment = document.getElementById("form-new-comment-comment");
    formNewCommentCommentInvalidFeedback = document.getElementById("form-new-comment-comment-invalid-feedback");

    commentForm = new Comment(formNewComment, formNewCommentComment, formNewCommentCommentInvalidFeedback);
    deleteCommentButtons = document.getElementsByName("delete-comment-button");

});

function updateAlienInfo() {
    let data = {};
    data[ALIEN_ID] = alienId;
    data[ALIEN_NAME] = formAlienUpdateInfoName.value;
    data[ALIEN_SMALL_DESCRIPTION] = formAlienUpdateInfoDescriptionSmall.value;
    data[ALIEN_FULL_DESCRIPTION] = formAlienUpdateInfoDescriptionFull.value;
    let url = CONTROLLER + "?" + COMMAND + "=" + UPDATE_ALIEN_INFO + "&" + ALIEN_ID + "=" + alienId;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            alienForm.setFeedbackInfo(jqXHR.responseJSON[ALIEN_NAME_STATUS], jqXHR.responseJSON[ALIEN_SMALL_DESCRIPTION_STATUS],
                jqXHR.responseJSON[ALIEN_FULL_DESCRIPTION_STATUS], jqXHR.responseJSON[ALIEN_NAME_FEEDBACK], 
                jqXHR.responseJSON[ALIEN_SMALL_DESCRIPTION_FEEDBACK], jqXHR.responseJSON[ALIEN_FULL_DESCRIPTION_FEEDBACK]);
            currentAlienName.innerHTML = formAlienUpdateInfoName.value;
            currentAlienDescriptionSmall.innerHTML = formAlienUpdateInfoDescriptionSmall.value;
            currentAliendDescriptionFull.innerHTML = formAlienUpdateInfoDescriptionFull.value;
        },
        error: function (jqXHR, textStatus, errorThrown) {
            changeLocationIfUndefined(jqXHR);
            alienForm.removeInfoValidationClasses();
            alienForm.setFeedbackInfo(jqXHR.responseJSON[ALIEN_NAME_STATUS], qXHR.responseJSON[ALIEN_SMALL_DESCRIPTION_STATUS],
                jqXHR.responseJSON[ALIEN_FULL_DESCRIPTION_STATUS], jqXHR.responseJSON[ALIEN_NAME_FEEDBACK], 
                jqXHR.responseJSON[ALIEN_SMALL_DESCRIPTION_FEEDBACK], jqXHR.responseJSON[ALIEN_FULL_DESCRIPTION_FEEDBACK]);
        }
    });
};

function updateAlienImage() {
    let formData = new FormData();
    formData.append(ALIEN_ID, alienId);
    formData.append(ALIEN_NEW_IMAGE, formAlienUpdateImageImage.files[0]);
    let url = CONTROLLER + "?" + COMMAND + "=" + UPDATE_ALIEN_IMAGE + "&" + ALIEN_ID + "=" + alienId;
    $.ajax({
        url: url,
        type: "POST",
        data: formData,
        cache: false,
        contentType: false,
        processData: false,
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            alienForm.setFeedbackImage(jqXHR.responseJSON[IMAGE_STATUS], 
                jqXHR.responseJSON[IMAGE_FEEDBACK], 
                jqXHR.responseJSON[IMAGE_FEEDBACK]
            );
            currentAlienImage.src = jqXHR.responseJSON[IMAGE_PATH];
        },
        error: function (jqXHR, textStatus, errorThrown) {
            changeLocationIfUndefined(jqXHR);
            alienForm.removeImageValidationClasses();
            alienForm.setFeedbackImage(jqXHR.responseJSON[IMAGE_STATUS], 
                jqXHR.responseJSON[IMAGE_FEEDBACK], 
                jqXHR.responseJSON[IMAGE_FEEDBACK]
            );
        }
    });
};

function addNewComment() {
    let data = {};
    data[NEW_COMMENT] = formNewCommentComment.value;
    data[ALIEN_ID] = alienId;
    let url = CONTROLLER + "?" + COMMAND + "=" + ADD_NEW_COMMENT;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        success: function (data, textStatus, jqXHR) {
        },
        error: function (jqXHR, textStatus, errorThrown) {
            changeLocationIfUndefined(jqXHR);
            commentForm.removeCommentValidationClasses();
            commentForm.setCommentFeedback(jqXHR.responseJSON[ALIEN_NAME_STATUS], jqXHR.responseJSON[COMMENT_FEEDBACK]);
        }
    });
};

function deleteComment(commentId) {
    let data = {};
    data[COMMENT_ID] = commentId;
    let url = CONTROLLER + "?" + COMMAND + "=" + DELETE_COMMENT;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        success: function (data, textStatus, jqXHR) {
            alert("success");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            changeLocationIfUndefined(jqXHR);
        }
    });
};

const alienProfile = {
    updateAlienInfo: updateAlienInfo,
    updateAlienImage: updateAlienImage,
    addNewComment: addNewComment,
    deleteComment: deleteComment
}

/**
 * Setting values to alien info input
 */
$(function() {
    if (formAlienUpdateInfo != null) {
        formAlienUpdateInfoName.value = document.getElementById("alien-name").innerHTML;
        formAlienUpdateInfoDescriptionSmall.value = document.getElementById("alien-small-description").innerHTML;
        formAlienUpdateInfoDescriptionFull.value = document.getElementById("alien-big-description").innerHTML;
    }
});

/**
 * Reset forms
 */
$(function() {
    formAlienUpdateImage.reset();
    formNewComment.reset();
});

/**
 * Alien update info processing
 */
$(function() {
    if (formAlienUpdateInfo != null) {
        formAlienUpdateInfo.addEventListener('submit', function(event) {
            alienForm.removeInfoValidationClasses();
            let validationResult = alienForm.validateInfo();

            if (!validationResult[0] || !validationResult[1] || !validationResult[2]) {
                alienForm.setFeedbackInfo(validationInfoResult[0], validationInfoResult[1], validationInfoResult[2], "", "", "", 
                STANDARD_ALIEN_NAME_FEEDBACK, STANDARD_ALIEN_SMALL_DESCRIPTION_FEEDBACK, STANDARD_ALIEN_FULL_DESCRIPTION_FEEDBACK);
                event.preventDefault();
                event.stopPropagation();
            } else {
                event.preventDefault();
                alienProfile.updateAlienInfo()
            }
          }, false);
            formAlienUpdateInfoName.addEventListener('input', function(event) {
                alienForm.removeNameValidationClasses();
            })
            formAlienUpdateInfoDescriptionSmall.addEventListener('input', function(event) {
                alienForm.removeSmallDescriptionValidationClasses();
            })
            formAlienUpdateInfoDescriptionFull.addEventListener('input', function(event) {
                alienForm.removeFullDescriptionValidationClasses();
          })
    }
});

/**
 * Alien update image processing
 */
$(function() {
    if (formAlienUpdateImage != null) {
        formAlienUpdateImage.addEventListener('submit', function(event) {
            alienForm.removeImageValidationClasses();
            let validationResult = alienForm.validateImage();
    
            if (validationResult) {
                event.preventDefault();
                alienProfile.updateAlienImage()
            } else {
                alienForm.setFeedbackImage(validationResult, "", STANDARD_IMAGE_FEEDBACK);
                event.preventDefault();
                event.stopPropagation();
            }
        }, false);
        
        formAlienUpdateImageImage.addEventListener('input', function(event) {
        alienForm.setLabelText(formAlienUpdateImageImage.files[0].name);
            alienForm.removeImageValidationClasses();
        })
    }
});

/**
 * Add new comment processing
 */
$(function() {
    if (formNewComment != null) {
        formNewComment.addEventListener('submit', function(event) {
            commentForm.removeCommentValidationClasses();
            let validationResult = commentForm.validateComment();
            if (!validationResult) {
                commentForm.setCommentFeedback(false, STANDARD_COMMENT_FEEDBACK);
                event.preventDefault();
                event.stopPropagation();
            } else {
                event.preventDefault();
                alienProfile.addNewComment()
            }
          }, false);
         
        formNewCommentComment.addEventListener('input', function(event) {
            commentForm.removeCommentValidationClasses();
        })
    }

});

/**
 * Delete comment event listeners
 */
$(function() {
    deleteCommentButtons.forEach(button => 
        button.addEventListener('click', function(event) {
            let commentId = button.parentElement.children[0].innerText;
            alienProfile.deleteComment(commentId, userId);
        })
    );
});

/**
 * Carousel initialization
 */
$(function() {
    initializeCarousel();
});

/**
 * set rating value
 */

$(function() {
    if(document.getElementById("ratingStars") != null) {
        rating.setRatingValue();
    }
});