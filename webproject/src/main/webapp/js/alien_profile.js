function updateAlien(alienId) {
    var alienName = document.getElementById("alien-update-form-name").value;
    var alienSmallDescription = document.getElementById("alien-update-form-description-small").value;
    var alienFullDescription = document.getElementById("alien-update-form-description-full").value;
    var files = document.getElementById("alien-update-form-image").files;
    var formData = new FormData();
    formData.append(ALIEN_NAME, alienName);
    formData.append(ALIEN_SMALL_DESCRIPTION, alienSmallDescription);
    formData.append(ALIEN_FULL_DESCRIPTION, alienFullDescription);
    formData.append(ALIEN_NEW_IMAGE, files[0]);
    var url = CONTROLLER + "?" + COMMAND + "=" + UPDATE_ALIEN + "&" + ALIEN_ID + "=" + alienId;
    $.ajax({
        url: url,
        type: "POST",
        data: formData,
        cache: false,
        contentType: false,
        processData: false,
        success: function (updateResult) {
            if (updateResult == "true") {
                document.getElementById("alien-name").innerHTML = alienName;
                document.getElementById("alien-small-description").innerHTML = alienSmallDescription;
                document.getElementById("alien-big-description").innerHTML = alienFullDescription;
                // TODO set file to input alien-image
                //document.getElementById("alien-image").files;
                //$("#alien-update-form").load("/" + PROJECT_NAME + "/" + CONTROLLER + "?" + COMMAND + "=" + LOAD_ALIEN_UPDATE_CREATE_FORM + " #alien-update-form");
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

function addNewComment() {
    var comment = document.getElementById("new-comment-form-comment").value;
    var data = {};
    data[NEW_COMMENT] = comment;
    data[ALIEN_ID] = alienId;
    data[USER_ID] = userId;
    var url = CONTROLLER + "?" + COMMAND + "=" + ADD_NEW_COMMENT;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        success: function (updateResult) {
            if (updateResult == "true") {
                // load new comment (add to up)
                //window.location.href = "";
                document.getElementById("new-comment-form-comment").value = "";
                //$("#alien-update-form").load("/" + PROJECT_NAME + "/" + CONTROLLER + "?" + COMMAND + "=" + LOAD_ALIEN_UPDATE_CREATE_FORM + " #alien-update-form");
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

function deleteComment() {
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



$(document).ready(function () {
    if (!document.getElementById("alien-update-form") == null) {
        document.getElementById("alien-update-form-name").value = document.getElementById("alien-name").innerHTML;
        document.getElementById("alien-update-form-description-small").value = document.getElementById("alien-small-description").innerHTML;
        document.getElementById("alien-update-form-description-full").value = document.getElementById("alien-big-description").innerHTML;
    }

    // TODO set file to input alien-image
    //document.getElementById("alien-update-form-image").files;
});