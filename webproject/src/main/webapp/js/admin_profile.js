function banUser(PROJECT_NAME, CONTROLLER, COMMAND, BAN_USER, LOAD_BAN_UNBAN_FORM, LOGIN, DAYS_TO_BAN) {
    var userLogin = document.getElementById("user-ban-unban-form-login").value;
    var daysToBan = document.getElementById("user-ban-unban-form-days-in-ban").value;
    var data = {};
    data[LOGIN] = userLogin;
    data[DAYS_TO_BAN] = daysToBan;
    var url = CONTROLLER + "?" + COMMAND + "=" + BAN_USER;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        success: function (updateResult) {
            if (updateResult == "true") {
                $("#user-ban-unban-form").load("/" + PROJECT_NAME + "/" + CONTROLLER + "?" + COMMAND + "=" + LOAD_BAN_UNBAN_FORM + " #user-ban-unban-form");
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

function unbanUser(PROJECT_NAME, CONTROLLER, COMMAND, UNBAN_USER, LOAD_BAN_UNBAN_FORM, LOGIN) {
    var userLogin = document.getElementById("user-ban-unban-form-login").value;
    var data = {};
    data[LOGIN] = userLogin;
    var url = CONTROLLER + "?" + COMMAND + "=" + UNBAN_USER;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        success: function (updateResult) {
            if (updateResult == "true") {
                $("#user-ban-unban-form").load("/" + PROJECT_NAME + "/" + CONTROLLER + "?" + COMMAND + "=" + LOAD_BAN_UNBAN_FORM + " #user-ban-unban-form");
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

function promoteUser(PROJECT_NAME, CONTROLLER, COMMAND, PROMOTE_USER, LOAD_PROMOTE_DEMOTE_FORM, LOGIN) {
    var userLogin = document.getElementById("promote-demote-user-login").value;
    var data = {};
    data[LOGIN] = userLogin;
    var url = CONTROLLER + "?" + COMMAND + "=" + PROMOTE_USER;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        success: function (updateResult) {
            if (updateResult == "true") {
                $("#promote-demote-form").load("/" + PROJECT_NAME + "/" + CONTROLLER + "?" + COMMAND + "=" + LOAD_PROMOTE_DEMOTE_FORM + " #promote-demote-form");
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

function demoteAdmin(PROJECT_NAME, CONTROLLER, COMMAND, DEMOTE_ADMIN, LOAD_PROMOTE_DEMOTE_FORM, LOGIN) {
    var userLogin = document.getElementById("promote-demote-user-login").value;
    var data = {};
    data[LOGIN] = userLogin;
    var url = CONTROLLER + "?" + COMMAND + "=" + DEMOTE_ADMIN;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        success: function (updateResult) {
            if (updateResult == "true") {
                $("#promote-demote-form").load("/" + PROJECT_NAME + "/" + CONTROLLER + "?" + COMMAND + "=" + LOAD_PROMOTE_DEMOTE_FORM + " #promote-demote-form");
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

function addNewAlien(PROJECT_NAME, CONTROLLER, COMMAND, ADD_NEW_ALIEN, ALIEN_NAME, ALIEN_SMALL_DESCRIPTION, ALIEN_FULL_DESCRIPTION, ALIEN_NEW_IMAGE, LOAD_ALIEN_CREATE_FORM) {
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

