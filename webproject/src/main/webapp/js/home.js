function updateUserEmail(PROJECT_NAME, CONTROLLER, COMMAND, UPDATE_USER_EMAIL, EMAIL, NEW_EMAIL, USER_ID, LOAD_EMAIL_UPDATE_FORM, userId, currentEmail) {
    var enteredEmail = document.getElementById("email-update-form-email").value;
    var data = {};
    data[EMAIL] = currentEmail;
    data[NEW_EMAIL] = enteredEmail;
    data[USER_ID] = userId;
    var url = CONTROLLER + "?" + COMMAND + "=" + UPDATE_USER_EMAIL;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        success: function (updateResult) {
            if (updateResult == "true") {
                document.getElementById("user-profile-account-email").innerHTML = enteredEmail;
                $("#email-update-form").load("/" + PROJECT_NAME + "/" + CONTROLLER + "?" + COMMAND + "=" + LOAD_EMAIL_UPDATE_FORM + " #email-update-form");
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

function previousHomePage(CONTROLLER, COMMAND, OPEN_HOME_PAGE, PAGE, pageToRequest) {
    var url = CONTROLLER + "?" + COMMAND + "=" + OPEN_HOME_PAGE + "&" + PAGE + "=" + pageToRequest;
    location.assign(url);
};

function nextHomePage(CONTROLLER, COMMAND, OPEN_HOME_PAGE, PAGE, pageToRequest) {
    var url = CONTROLLER + "?" + COMMAND + "=" + OPEN_HOME_PAGE + "&" + PAGE + "=" + pageToRequest;
    location.assign(url);
};


// function previousPage(CONTROLLER, COMMAND, PREVIOUS_PAGE, PAGE, currentPage) {
//     var url = CONTROLLER + "?" + COMMAND + "=" + PREVIOUS_PAGE;
//     var data = {};
//     data[PAGE] = currentPage;
//     $.ajax({
//         url: url,
//         data: data,
//         type: "POST",
//         success: function (updateResult) {
//             location.reload();
//             if (updateResult == "true") {
//                 //document.getElementById("user-profile-account-email").innerHTML = enteredEmail;
//                 //$("#email-update-form").load("/" + PROJECT_NAME + "/" + CONTROLLER + "?" + COMMAND + "=" + LOAD_EMAIL_UPDATE_FORM + " #email-update-form");
//                 //set invisible to visible validation green
//             } else if (updateResult == "false") {
//                 // set invisible to visible validation red
//             }
//         },
//         error: function () {
//             // TODO show error
//         }
//     });
// };

// function nextPage(CONTROLLER, COMMAND, NEXT_PAGE, PAGE, currentPage) {
//     var url = CONTROLLER + "?" + COMMAND + "=" + NEXT_PAGE;
//     var data = {};
//     data[PAGE] = currentPage;
//     $.ajax({
//         url: url,
//         type: "POST",
//         data: data,
//         success: function (updateResult) {
//             location.reload();
//             if (updateResult == "true") {
//                 //document.getElementById("user-profile-account-email").innerHTML = enteredEmail;
//                 //$("#email-update-form").load("/" + PROJECT_NAME + "/" + CONTROLLER + "?" + COMMAND + "=" + LOAD_EMAIL_UPDATE_FORM + " #email-update-form");
//                 //set invisible to visible validation green
//             } else if (updateResult == "false") {
//                 // set invisible to visible validation red
//             }
//         },
//         error: function () {
//             // TODO show error
//         }
//     });
// };




