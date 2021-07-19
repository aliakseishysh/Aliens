function suggestNewAlien() {
    let formAlienSuggest = document.getElementById("form-alien-suggest");
    let name = document.getElementById("form-alien-suggest-name");
    let descriptionSmall = document.getElementById("form-alien-suggest-description-small");
    let descriptionFull = document.getElementById("form-alien-suggest-description-full");
    let image = document.getElementById("form-alien-suggest-image");
    
    let nameInvalidFeedback = document.getElementById("form-alien-suggest-name-invalid-feedback");
    let descriptionSmallInvalidFeedback = document.getElementById("form-alien-suggest-description-small-invalid-feedback");
    let descriptionFullInvalidFeedback = document.getElementById("form-alien-suggest-description-full-invalid-feedback");
    let imageInvalidFeedback = document.getElementById("form-alien-suggest-image-invalid-feedback");
    let imageLabel = document.getElementById("form-alien-suggest-image-label");

    let formData = new FormData();
    formData.append(ALIEN_NAME, name.value);
    formData.append(ALIEN_SMALL_DESCRIPTION, descriptionSmall.value);
    formData.append(ALIEN_FULL_DESCRIPTION, descriptionFull.value);
    formData.append(ALIEN_NEW_IMAGE, image.files[0]);
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
            name.classList.add("is-valid")
            name.classList.remove("is-invalid")
            descriptionSmall.classList.add("is-valid")
            descriptionSmall.classList.remove("is-invalid")
            descriptionFull.classList.add("is-valid")
            descriptionFull.classList.remove("is-invalid")
            image.classList.add("is-valid")
            image.classList.remove("is-invalid")
            formAlienSuggest.classList.add("was-validated")
        },
        error: function (jqXHR, textStatus, errorThrown) {
            formAlienSuggest.classList.remove("was-validated");
            if (jqXHR.responseJSON[ALIEN_NAME_STATUS] == false) {
                name.classList.remove("is-valid");
                name.classList.add("is-invalid");
            } else {
                name.classList.remove("is-invalid");
                name.classList.add("is-valid");
            }
            if (jqXHR.responseJSON[ALIEN_SMALL_DESCRIPTION_STATUS] == false) {
                descriptionSmall.classList.remove("is-valid");
                descriptionSmall.classList.add("is-invalid");
            } else {
                descriptionSmall.classList.remove("is-invalid");
                descriptionSmall.classList.add("is-valid");
            }
            if (jqXHR.responseJSON[ALIEN_FULL_DESCRIPTION_STATUS] == false) {
                descriptionFull.classList.remove("is-valid");
                descriptionFull.classList.add("is-invalid");
            } else {
                descriptionFull.classList.remove("is-invalid");
                descriptionFull.classList.add("is-valid");
            }
            if (jqXHR.responseJSON[IMAGE_STATUS] == false) {
                image.classList.remove("is-valid");
                image.classList.add("is-invalid");
            } else {
                image.classList.remove("is-invalid");
                image.classList.add("is-valid");
            }
            nameInvalidFeedback.innerHTML = jqXHR.responseJSON[ALIEN_NAME_FEEDBACK];
            descriptionSmallInvalidFeedback.innerHTML = jqXHR.responseJSON[ALIEN_SMALL_DESCRIPTION_FEEDBACK];
            descriptionFullInvalidFeedback.innerHTML = jqXHR.responseJSON[ALIEN_FULL_DESCRIPTION_FEEDBACK];
            imageInvalidFeedback.innerHTML = jqXHR.responseJSON[IMAGE_FEEDBACK];
        }
    });
};

function suggestNewAlienImage() {
    let formAlienSuggestImage = document.getElementById("form-alien-suggest-suggest-image");
    let name = document.getElementById("form-alien-suggest-suggest-image-name");
    let image = document.getElementById("form-alien-suggest-suggest-image-image");
    
    let nameInvalidFeedback = document.getElementById("form-alien-suggest-suggest-image-name-invalid-feedback");
    let imageInvalidFeedback = document.getElementById("form-alien-suggest-suggest-image-image-invalid-feedback");
    let imageLabel = document.getElementById("form-alien-suggest-suggest-image-image-label");

    let formData = new FormData();
    formData.append(ALIEN_NAME, name.value);
    formData.append(ALIEN_NEW_IMAGE, image.files[0]);
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
            name.classList.add("is-valid")
            name.classList.remove("is-invalid")
            image.classList.add("is-valid")
            image.classList.remove("is-invalid")
            formAlienSuggestImage.classList.add("was-validated")
        },
        error: function (jqXHR, textStatus, errorThrown) {
            formAlienSuggestImage.classList.remove("was-validated");
            if (jqXHR.responseJSON[ALIEN_NAME_STATUS] == false) {
                name.classList.remove("is-valid");
                name.classList.add("is-invalid");
            } else {
                name.classList.remove("is-invalid");
                name.classList.add("is-valid");
            }
            if (jqXHR.responseJSON[IMAGE_STATUS] == false) {
                image.classList.remove("is-valid");
                image.classList.add("is-invalid");
            } else {
                image.classList.remove("is-invalid");
                image.classList.add("is-valid");
            }
            nameInvalidFeedback.innerHTML = jqXHR.responseJSON[ALIEN_NAME_FEEDBACK];
            imageInvalidFeedback.innerHTML = jqXHR.responseJSON[IMAGE_FEEDBACK];
        }
    });
};


const suggestAlienPage = {
    suggestNewAlien: suggestNewAlien,
    suggestNewAlienImage: suggestNewAlienImage
}

$(document).ready(function () {
    let formAlienSuggest = document.getElementById("form-alien-suggest");
    let name = document.getElementById("form-alien-suggest-name");
    let descriptionSmall = document.getElementById("form-alien-suggest-description-small");
    let descriptionFull = document.getElementById("form-alien-suggest-description-full");
    let image = document.getElementById("form-alien-suggest-image");
    
    let nameInvalidFeedback = document.getElementById("form-alien-suggest-name-invalid-feedback");
    let descriptionSmallInvalidFeedback = document.getElementById("form-alien-suggest-description-small-invalid-feedback");
    let descriptionFullInvalidFeedback = document.getElementById("form-alien-suggest-description-full-invalid-feedback");
    let imageInvalidFeedback = document.getElementById("form-alien-suggest-image-invalid-feedback");
    let imageLabel = document.getElementById("form-alien-suggest-image-label");

    formAlienSuggest.addEventListener('submit', function(event) {
        name.classList.remove("is-invalid");
        name.classList.remove("is-valid");
        descriptionSmall.classList.remove("is-invalid");
        descriptionSmall.classList.remove("is-valid");
        descriptionFull.classList.remove("is-invalid");
        descriptionFull.classList.remove("is-valid");
        image.classList.remove("is-invalid");
        image.classList.remove("is-valid");

        formAlienSuggest.classList.remove("was-validated");

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
            if (!imageCheckResult) {
                imageInvalidFeedback.innerHTML = STANDARD_IMAGE_FEEDBACK;
                image.classList.add("is-invalid");
            }
        // formAlienSuggest.classList.add('was-validated');
           // document.getElementById("form-alien-suggest-image").setCustomValidity(IMAGE_FEEDBACK_INVALID);
            event.preventDefault();
            event.stopPropagation();
        } else {
            event.preventDefault();
            imageLabel.innerHTML = files[0].name;
            suggestAlienPage.suggestNewAlien();
        }
      }, false);
      name.addEventListener('input', function(event) {
        formAlienSuggest.classList.remove('was-validated');
        name.classList.remove("is-invalid");
        name.classList.remove("is-valid");
      })
      descriptionSmall.addEventListener('input', function(event) {
        formAlienSuggest.classList.remove('was-validated');
        descriptionSmall.classList.remove("is-invalid");
        descriptionSmall.classList.remove("is-valid");
      })
      descriptionFull.addEventListener('input', function(event) {
        formAlienSuggest.classList.remove('was-validated');
        descriptionFull.classList.remove("is-invalid");
        descriptionFull.classList.remove("is-valid");
      })
      image.addEventListener('input', function(event) {
        imageLabel.innerHTML = image.files[0].name;
        formAlienSuggest.classList.remove('was-validated');
        image.classList.remove("is-invalid");
        image.classList.remove("is-valid");
      })
});

$(document).ready(function () {
    let formAlienSuggestImage = document.getElementById("form-alien-suggest-suggest-image");
    let name = document.getElementById("form-alien-suggest-suggest-image-name");
    let image = document.getElementById("form-alien-suggest-suggest-image-image");
    
    let nameInvalidFeedback = document.getElementById("form-alien-suggest-suggest-image-name-invalid-feedback");
    let imageInvalidFeedback = document.getElementById("form-alien-suggest-suggest-image-image-invalid-feedback");
    let imageLabel = document.getElementById("form-alien-suggest-suggest-image-image-label");

    formAlienSuggestImage.addEventListener('submit', function(event) {
        name.classList.remove("is-invalid");
        name.classList.remove("is-valid");
        image.classList.remove("is-invalid");
        image.classList.remove("is-valid");

        formAlienSuggestImage.classList.remove("was-validated");

        let files = image.files;
        let nameCheckResult = false;
        let imageCheckResult = false;
        let validExtensions = ["image/jpg", "image/jpeg", "image/png"];

        if (name.value != "" && name.value.match(name.getAttribute("pattern"))) {
            nameCheckResult = true
            name.classList.add("is-valid")
        }
        if (files.length == 1) {
            let file = files[0];
            
            if (file.size <= 1000000) { // bytes
                for (let i = 0; i < validExtensions.length; i++) {
                    if (validExtensions[i] == file.type) {
                        imageCheckResult = true;
                        image.classList.add("is-valid");
                        break;
                    }
                }    
            }
        }

        if (!nameCheckResult || !imageCheckResult) {
            if (!nameCheckResult) {
                nameInvalidFeedback.innerHTML = STANDARD_ALIEN_NAME_FEEDBACK;
                name.classList.add("is-invalid");
            }
            if (!imageCheckResult) {
                imageInvalidFeedback.innerHTML = STANDARD_IMAGE_FEEDBACK;
                image.classList.add("is-invalid");
            }
            event.preventDefault();
            event.stopPropagation();
        } else {
            event.preventDefault();
            imageLabel.innerHTML = files[0].name;
            suggestAlienPage.suggestNewAlienImage();
        }
      }, false);
      name.addEventListener('input', function(event) {
        formAlienSuggestImage.classList.remove('was-validated');
        name.classList.remove("is-invalid");
        name.classList.remove("is-valid");
      })
      image.addEventListener('input', function(event) {
        imageLabel.innerHTML = image.files[0].name;
        formAlienSuggestImage.classList.remove('was-validated');
        image.classList.remove("is-invalid");
        image.classList.remove("is-valid");
      })
});