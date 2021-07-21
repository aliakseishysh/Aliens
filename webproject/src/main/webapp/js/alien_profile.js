function updateAlienInfo() {

    let formAlienUpdateInfo = document.getElementById("form-alien-update-info")
    let name = document.getElementById("form-alien-update-info-name")
    let descriptionSmall = document.getElementById("form-alien-update-info-description-small")
    let descriptionFull = document.getElementById("form-alien-update-info-description-full")
    
    let nameInvalidFeedback = document.getElementById("form-alien-update-info-name-invalid-feedback");
    let descriptionSmallInvalidFeedback = document.getElementById("form-alien-update-info-description-small-invalid-feedback")
    let descriptionFullInvalidFeedback = document.getElementById("form-alien-update-info-description-full-invalid-feedback")

    let alienId = document.getElementById("alien-id-hidden").innerHTML;

    let data = {};
    data[ALIEN_ID] = alienId;
    data[ALIEN_NAME] = name.value;
    data[ALIEN_SMALL_DESCRIPTION] = descriptionSmall.value;
    data[ALIEN_FULL_DESCRIPTION] = descriptionFull.value;
    let url = CONTROLLER + "?" + COMMAND + "=" + UPDATE_ALIEN_INFO + "&" + ALIEN_ID + "=" + alienId;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            name.classList.add("is-valid")
            name.classList.remove("is-invalid")
            descriptionSmall.classList.add("is-valid")
            descriptionSmall.classList.remove("is-invalid")
            descriptionFull.classList.add("is-valid")
            descriptionFull.classList.remove("is-invalid")
            formAlienUpdate.classList.add("was-validated")
        },
        error: function (jqXHR, textStatus, errorThrown) {
            formAlienUpdate.classList.remove("was-validated");
            if (jqXHR.responseJSON[ALIEN_NAME_STATUS] == false) {
                name.classList.add("is-invalid");
                name.classList.remove("is-valid");
            } else {
                name.classList.remove("is-invalid");
                name.classList.add("is-valid");
                nameInvalidFeedback.innerHTML = jqXHR.responseJSON[ALIEN_NAME_FEEDBACK];
            }
            if (jqXHR.responseJSON[ALIEN_SMALL_DESCRIPTION_STATUS] == false) {
                descriptionSmall.classList.add("is-invalid");
                descriptionSmall.classList.remove("is-valid");
            } else {
                descriptionSmall.classList.remove("is-invalid");
                descriptionSmall.classList.add("is-valid");
                descriptionSmallInvalidFeedback.innerHTML = jqXHR.responseJSON[ALIEN_SMALL_DESCRIPTION_FEEDBACK];
            }
            if (jqXHR.responseJSON[ALIEN_FULL_DESCRIPTION_STATUS] == false) {
                descriptionFull.classList.add("is-invalid");
                descriptionFull.classList.remove("is-valid");
            } else {
                descriptionFull.classList.remove("is-invalid");
                descriptionFull.classList.add("is-valid");
                descriptionFullInvalidFeedback.innerHTML = jqXHR.responseJSON[ALIEN_FULL_DESCRIPTION_FEEDBACK];
            }
            // formAlienUpdateInfo.classList.add("was-validated");
        }
    });
};

function updateAlienImage() {

    let formAlienUpdateImage = document.getElementById("form-alien-update-image")
    let image = document.getElementById("form-alien-update-image-image")
    
    let imageInvalidFeedback = document.getElementById("form-alien-update-image-image-invalid-feedback")
    let imageLabel = document.getElementById("form-alien-update-image-image-label")

    let alienId = document.getElementById("alien-id-hidden").innerHTML;

    let formData = new FormData();
    formData.append(ALIEN_ID, alienId);
    formData.append(ALIEN_NEW_IMAGE, image.files[0]);
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
            image.classList.add("is-valid")
            image.classList.remove("is-invalid")
            formAlienUpdateImage.classList.add("was-validated")
        },
        error: function (jqXHR, textStatus, errorThrown) {
            formAlienUpdateImage.classList.remove("was-validated");
            if (jqXHR.responseJSON[IMAGE_STATUS] == false) {
                image.classList.add("is-invalid");
                image.classList.remove("is-valid");
            } else {
                image.classList.remove("is-invalid");
                image.classList.add("is-valid");
                imageInvalidFeedback.innerHTML = jqXHR.responseJSON[IMAGE_FEEDBACK];
            }
            // formAlienUpdateImage.classList.add("was-validated");
        }
    });
};

function addNewComment() {
    let formNewComment = document.getElementById("form-new-comment");
    let comment = document.getElementById("form-new-comment-comment");
    let commentInvalidFeedback = document.getElementById("form-new-comment-comment-invalid-feedback");

    let alienId = document.getElementById("form-new-comment-parameter-current-alien-id").innerHTML
    let userId = document.getElementById("form-new-comment-parameter-current-user-id").innerHTML


    var data = {};
    data[NEW_COMMENT] = comment.value;
    data[ALIEN_ID] = alienId;
    data[USER_ID] = userId;


    var url = CONTROLLER + "?" + COMMAND + "=" + ADD_NEW_COMMENT;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        success: function (data, textStatus, jqXHR) {
            comment.classList.remove("is-invalid")
            comment.classList.add("is-valid")
        },
        error: function (jqXHR, textStatus, errorThrown) {
            formNewComment.classList.remove("was-validated");
            commentInvalidFeedback.innerHTML = jqXHR.responseJSON[COMMENT_FEEDBACK];
            if (jqXHR.responseJSON[ALIEN_NAME_STATUS] == false) {
                comment.classList.add("is-invalid");
                comment.classList.remove("is-valid");
            } else {
                comment.classList.remove("is-invalid");
                comment.classList.add("is-valid");
            }
            formNewComment.classList.add("was-validated");
        }
    });
};

function deleteComment(commentId) {
    var data = {};
    data[COMMENT_ID] = commentId;
    var url = CONTROLLER + "?" + COMMAND + "=" + DELETE_COMMENT;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        success: function (updateResult) {
            if (updateResult == "true") {
                var elementId = "comment_" + commentId;
                document.getElementById(elementId).remove();
                //$("#promote-demote-form").load("/" + PROJECT_NAME + "/" + CONTROLLER + "?" + COMMAND + "=" + DELETE_COMMENT + " #promote-demote-form");
                //set invisible to visible validation green
            } else if (updateResult == "false") {
                // set invisible to visible validation red
            }
        },
        error: function () {
            // TODO show error
        }
    });
};

function previousCommentPage(alienId, pageToRequest) {
    var url = CONTROLLER + "?" + COMMAND + "=" + OPEN_ALIEN_PROFILE_PAGE + "&" + ALIEN_ID + "=" + alienId + "&" + PAGE + "=" + pageToRequest;
    location.assign(url);
};

function nextCommentPage(alienId, pageToRequest) {
    var url = CONTROLLER + "?" + COMMAND + "=" + OPEN_ALIEN_PROFILE_PAGE + "&" + ALIEN_ID + "=" + alienId + "&" + PAGE + "=" + pageToRequest;
    location.assign(url);
};

const alienProfile = {
    updateAlienInfo: updateAlienInfo,
    updateAlienImage: updateAlienImage,
    addNewComment: addNewComment,
    deleteComment: deleteComment,
    previousCommentPage: previousCommentPage,
    nextCommentPage: nextCommentPage
}

$(document).ready(function () {
    let formAlienUpdateInfo = document.getElementById("form-alien-update-info");
    if (formAlienUpdateInfo != null) {
        let name = document.getElementById("form-alien-update-info-name");
        let descriptionSmall = document.getElementById("form-alien-update-info-description-small");
        let descriptionFull = document.getElementById("form-alien-update-info-description-full");
        name.value = document.getElementById("alien-name").innerHTML;
        descriptionSmall.value = document.getElementById("alien-small-description").innerHTML;
        descriptionFull.value = document.getElementById("alien-big-description").innerHTML;
    }
});

// form-alien-update-info
$(document).ready(function () {
    let formAlienUpdateInfo = document.getElementById("form-alien-update-info");
    if (formAlienUpdateInfo != null) {

        let name = document.getElementById("form-alien-update-info-name");
        let descriptionSmall = document.getElementById("form-alien-update-info-description-small");
        let descriptionFull = document.getElementById("form-alien-update-info-description-full");

        let nameInvalidFeedback = document.getElementById("form-alien-update-info-name-invalid-feedback");
        let descriptionSmallInvalidFeedback = document.getElementById("form-alien-update-info-description-small-invalid-feedback");
        let descriptionFullInvalidFeedback = document.getElementById("form-alien-update-info-description-full-invalid-feedback");
    
        formAlienUpdateInfo.addEventListener('submit', function(event) {
            name.classList.remove("is-invalid");
            name.classList.remove("is-valid");
            descriptionSmall.classList.remove("is-invalid");
            descriptionSmall.classList.remove("is-valid");
            descriptionFull.classList.remove("is-invalid");
            descriptionFull.classList.remove("is-valid");
    
            formAlienUpdateInfo.classList.remove("was-validated");
    
            let nameCheckResult = false;
            let descriptionSmallCheckResult = false;
            let descriptionFullCheckResult = false;
    
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
            if (!nameCheckResult || !descriptionSmallCheckResult || !descriptionFullCheckResult) {
                if (!nameCheckResult) {
                    nameInvalidFeedback.innerHTML = STANDARD_ALIEN_NAME_FEEDBACK;
                    name.classList.add("is-invalid");
                }
                if (!descriptionSmallCheckResult) {
                    descriptionSmallInvalidFeedback.innerHTML = STANDARD_ALIEN_SMALL_DESCRIPTION_FEEDBACK;
                    descriptionSmall.classList.add("is-invalid");
                }
                if (!descriptionFullCheckResult) {
                    descriptionFullInvalidFeedback.innerHTML = STANDARD_ALIEN_FULL_DESCRIPTION_FEEDBACK;
                    descriptionFull.classList.add("is-invalid");
                }
                event.preventDefault();
                event.stopPropagation();
            } else {
                event.preventDefault();
                alienProfile.updateAlienInfo()
            }
          }, false);
          name.addEventListener('input', function(event) {
            formAlienUpdateInfo.classList.remove('was-validated');
            name.classList.remove("is-invalid");
            name.classList.remove("is-valid");
          })
          descriptionSmall.addEventListener('input', function(event) {
            formAlienUpdateInfo.classList.remove('was-validated');
            descriptionSmall.classList.remove("is-invalid");
            descriptionSmall.classList.remove("is-valid");
          })
          descriptionFull.addEventListener('input', function(event) {
            formAlienUpdateInfo.classList.remove('was-validated');
            descriptionFull.classList.remove("is-invalid");
            descriptionFull.classList.remove("is-valid");
          })
    }
});

// form-alien-update-image
$(document).ready(function () {
    let formAlienUpdateImage = document.getElementById("form-alien-update-image");
    if (formAlienUpdateImage != null) {

        let image = document.getElementById("form-alien-update-image-image");
        
        let imageInvalidFeedback = document.getElementById("form-alien-update-image-image-invalid-feedback");
        let imageLabel = document.getElementById("form-alien-update-image-image-label");
    
        formAlienUpdateImage.addEventListener('submit', function(event) {
            image.classList.remove("is-invalid");
            image.classList.remove("is-valid");
    
            formAlienUpdateImage.classList.remove("was-validated");
    
            let files = image.files;
            let imageCheckResult = false;
            let validExtensions = ["image/jpg", "image/jpeg", "image/png"];
    
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
    
            if (!imageCheckResult) {
                imageInvalidFeedback.innerHTML = STANDARD_IMAGE_FEEDBACK;
                image.classList.add("is-invalid");
                event.preventDefault();
                event.stopPropagation();
            } else {
                event.preventDefault();
                document.getElementById("form-alien-update-image-image-label").innerHTML = files[0].name;
                alienProfile.updateAlienImage()
            }
          }, false);
          image.addEventListener('input', function(event) {
            imageLabel.innerHTML = image.files[0].name;
            formAlienUpdateImage.classList.remove('was-validated');
            image.classList.remove("is-invalid");
            image.classList.remove("is-valid");
          })
    }
});


$(document).ready(function () {
    let formNewComment = document.getElementById("form-new-comment");
    if (formNewComment != null) {
        let comment = document.getElementById("form-new-comment-comment");
        let commentInvalidFeedback = document.getElementById("form-new-comment-comment-invalid-feedback");
        formNewComment.addEventListener('submit', function(event) {
            comment.classList.remove("is-invalid");
            comment.classList.remove("is-valid");
    
            formNewComment.classList.remove("was-validated");
    
            let commentCheckResult = false;
    
            if (comment.value != "" && comment.value.replaceAll('\n', '\\n').match(comment.getAttribute("pattern"))) {
                commentCheckResult = true
                comment.classList.add("is-valid")
            }
    
            if (!commentCheckResult) {
                commentInvalidFeedback.innerHTML = STANDARD_COMMENT_FEEDBACK;
                comment.classList.add("is-invalid");
                event.preventDefault();
                event.stopPropagation();
            } else {
                event.preventDefault();
                alienProfile.addNewComment()
            }
          }, false);
         
          comment.addEventListener('input', function(event) {
            formNewComment.classList.remove('was-validated');
            comment.classList.remove("is-invalid");
            comment.classList.remove("is-valid");
          })
    }

});