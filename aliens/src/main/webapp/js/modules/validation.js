const validExtensions = ["image/jpg", "image/jpeg", "image/png"];
const imageMaxSize = parseInt(MAX_VALID_IMAGE_SIZE); // in bytes

export function validateEmail(email) {
    let result = false;
    if (email.value !== "" && email.value.match(email.getAttribute("pattern"))) {
        result = true;
    }
    return result;
}

export function validateLogin(login) {
    let result = false;
    if (login.value !== "" && login.value.match(login.getAttribute("pattern"))) {
        result = true;
    }
    return result;
}

export function validatePassword(password) {
    let result = false;
    if (password.value !== "" && password.value.match(password.getAttribute("pattern"))) {
        result = true;
    }
    return result;
}

export function validatePasswordConfirmation(passwordConfirmation) {
    let result = false;
    if (passwordConfirmation.value !== "" && passwordConfirmation.value.match(passwordConfirmation.getAttribute("pattern"))) {
        result = true;
    }
    return result;
}

export function validatePasswordEquality(password, passwordConfirmation) {
    return password.value === passwordConfirmation.value;
}

export function validateImage(image) {
    let imageCheckResult = false;
    let files = image.files;
    if (files.length === 1) {
        let file = files[0];
        
        if (file.size <= imageMaxSize) {
            for (let i = 0; i < validExtensions.length; i++) {
                if (validExtensions[i] === file.type) {
                    imageCheckResult = true;
                    break;
                }
            }    
        }
    }
    return imageCheckResult;
}


export function validateInput(input) {
    let result = false;
    if (input.value !== "" && input.value.match(input.getAttribute("pattern"))) {
        result = true;
    }
    return result;
}

export function validateTextarea(textarea) {
    let result = false;
    if (textarea.value !== "" && textarea.value.replaceAll('\n', '\\n').match(textarea.getAttribute("pattern"))) {
        result = true;
    }
    return result;
}