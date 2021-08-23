import { validateEmail, validateLogin, validatePassword, validatePasswordConfirmation, validatePasswordEquality, validateImage } from "./validation.js";
import { Validator, ValidationCleaner, Feedback } from "./util.js";

export class EmailUpdateForm {
    #form;
    #email;
    #emailInvalid;
    #emailValid;

    constructor(form, email, emailInvalid, emailValid) {
        this.#form = form;
        this.#email = email;
        this.#emailInvalid = emailInvalid;
        this.#emailValid = emailValid;
    }

    setFeedback(isEmailCorrect, emailValidFeedback, emailInvalidFeedback) {
        Feedback.setElementFeedback(this.#email, this.#emailValid, this.#emailInvalid, isEmailCorrect, emailValidFeedback, emailInvalidFeedback);
    }

    removeValidation() {
        ValidationCleaner.removeElementValidation(this.#form);
        ValidationCleaner.removeElementValidation(this.#email);
    }

    validate() {
        return Validator.validateElement(this.#email, validateEmail);
    }

}

export class LoginUpdateForm {
    #form;
    #login;
    #loginInvalid;
    #loginValid;

    constructor(form, login, loginInvalid, loginValid) {
        this.#form = form;
        this.#login = login;
        this.#loginInvalid = loginInvalid;
        this.#loginValid = loginValid;
    }

    setFeedback(isLoginCorrect, loginValidFeedback, loginInvalidFeedback) {
        Feedback.setElementFeedback(this.#login, this.#loginValid, this.#loginInvalid, isLoginCorrect, loginValidFeedback, loginInvalidFeedback);
    }

    removeValidation() {
        ValidationCleaner.removeElementValidation(this.#form);
        ValidationCleaner.removeElementValidation(this.#login);
    }

    validate() {
        return Validator.validateElement(this.#login, validateLogin);
    }

}

export class PasswordUpdateForm {
    
    #form;
    #password;
    #passwordConfirmation;
    #passwordInvalid;
    #passwordConfirmationInvalid;
    #passwordValid;
    #passwordConfirmationValid;

    constructor(form, password, passwordConfirmation, passwordInvalid, passwordConfirmationInvalid, passwordValid, passwordConfirmationValid) {
        this.#form = form;
        this.#password = password;
        this.#passwordConfirmation = passwordConfirmation;
        this.#passwordInvalid = passwordInvalid;
        this.#passwordConfirmationInvalid = passwordConfirmationInvalid;
        this.#passwordValid = passwordValid;
        this.#passwordConfirmationValid = passwordConfirmationValid;
    }

    setFeedback(isPasswordCorrect, passwordValidFeedback, passwordInvalidFeedback, 
        isPasswordConfirmationCorrect,  passwordConfirmationValidFeedback, passwordConfirmationInvalidFeedback) {

        Feedback.setElementFeedback(this.#password, this.#passwordValid, this.#passwordInvalid, 
            isPasswordCorrect, passwordValidFeedback, passwordInvalidFeedback);
        Feedback.setElementFeedback(this.#passwordConfirmation, this.#passwordConfirmationValid, this.#passwordConfirmationInvalid, 
            isPasswordConfirmationCorrect, passwordConfirmationValidFeedback, passwordConfirmationInvalidFeedback);
    }

    setPasswordFeedback(isPasswordCorrect, passwordValidFeedback, passwordInvalidFeedback) {
        Feedback.setElementFeedback(this.#password, this.#passwordValid, this.#passwordInvalid, 
            isPasswordCorrect, passwordValidFeedback, passwordInvalidFeedback);
    }

    setPasswordConfirmationFeedback(isPasswordConfirmationCorrect, passwordConfirmationValidFeedback, passwordConfirmationInvalidFeedback) {
        Feedback.setElementFeedback(this.#passwordConfirmation, this.#passwordConfirmationValid, this.#passwordConfirmationInvalid, 
            isPasswordConfirmationCorrect, passwordConfirmationValidFeedback, passwordConfirmationInvalidFeedback);
    }

    setElementFeedback(element, elementValid, elementInvalid, isElementCorrect, elementFeedbackValid, elementFeedbackInvalid) {
        Feedback.setElementFeedback(element, elementValid, elementInvalid, isElementCorrect, elementFeedbackValid, elementFeedbackInvalid);
    }

    removeValidation() {
        ValidationCleaner.removeElementValidation(this.#form);
        ValidationCleaner.removeElementValidation(this.#password);
        ValidationCleaner.removeElementValidation(this.#passwordConfirmation);
    }

    removePasswordValidation() {
        ValidationCleaner.removeElementValidation(this.#form);
        ValidationCleaner.removeElementValidation(this.#password);
    }

    removePasswordConfirmationValidation() {
        ValidationCleaner.removeElementValidation(this.#form);
        ValidationCleaner.removeElementValidation(this.#passwordConfirmation);
    }

    validate() {
        let isPasswordCorrect = Validator.validateElement(this.#password, validatePassword);
        let isPasswordConfirmationCorrect = Validator.validateElement(this.#passwordConfirmation, validatePasswordConfirmation);
        let isPasswordsEqual = Validator.validateEquality(this.#password, this.#passwordConfirmation, validatePasswordEquality);
        return [isPasswordCorrect, isPasswordConfirmationCorrect, isPasswordsEqual];
    }

    validatePassword() {
        return Validator.validateElement(this.#password, validatePassword);
    }

    getPassword() {
        return this.#password;
    }

    getPasswordInvalid() {
        return this.#passwordInvalid;
    }

    getPasswordValid() {
        return this.#passwordValid;
    }

    getPasswordConfirmationInvalid() {
        return this.#passwordConfirmationInvalid;
    }

    getPasswordConfirmationValid() {
        return this.#passwordConfirmationValid;
    }

    getPasswordValue() {
        return this.#password.value;
    }

    getPasswordConfirmation() {
        return this.#passwordConfirmation;
    }

    getPasswordConfirmationValue() {
        return this.#passwordConfirmation.value;
    }

}

export class ImageUpdateForm {

    #form;
    #image;
    #imageInvalid;
    #imageValid;
    #imageLabel;

    constructor(form, image, imageInvalid, imageValid, imageLabel) {
        this.#form = form;
        this.#image = image;
        this.#imageInvalid = imageInvalid;
        this.#imageValid = imageValid;
        this.#imageLabel = imageLabel;
    }

    setFeedback(isImageCorrect, imageValidFeedback, imageInvalidFeedback) {
        Feedback.setElementFeedback(this.#image, this.#imageValid, this.#imageInvalid, isImageCorrect, imageValidFeedback, imageInvalidFeedback);
    }

    removeValidation() {
        ValidationCleaner.removeElementValidation(this.#form);
        ValidationCleaner.removeElementValidation(this.#image);
    }

    validate() {
        return Validator.validateElement(this.#image, validateImage);
    }

    getImage() {
        return this.#image.files[0];
    }

    setLabelText() {
        this.#imageLabel.innerHTML = this.#image.files[0].name;
    }

}

export class LoginForm {

    #emailUpdateForm;
    #passwordUpdateForm;

    constructor(form, email, emailValid, emailInvalid, password, passwordValid, passwordInvalid) {
        this.#emailUpdateForm = new EmailUpdateForm(form, email, emailInvalid, emailValid);
        this.#passwordUpdateForm = new PasswordUpdateForm(form, password, null, passwordInvalid, null, passwordValid, null);
    }

    setFeedback(isEmailCorrect, emailValidFeedback, emailInvalidFeedback, isPasswordCorrect, passwordValidFeedback, passwordInvalidFeedback) {
        this.#emailUpdateForm.setFeedback(isEmailCorrect, emailValidFeedback, emailInvalidFeedback);
        this.#passwordUpdateForm.setPasswordFeedback(isPasswordCorrect, passwordValidFeedback, passwordInvalidFeedback);
    }

    removeValidation() {
        this.#emailUpdateForm.removeValidation();
        this.#passwordUpdateForm.removePasswordValidation();
    }

    removeEmailValidation() {
        this.#emailUpdateForm.removeValidation();
    }

    removePasswordValidation() {
        this.#passwordUpdateForm.removePasswordValidation();
    }

    validate() {
        let emailValidationResult = this.#emailUpdateForm.validate();
        let passwordValidationResult = this.#passwordUpdateForm.validatePassword();
        return [emailValidationResult, passwordValidationResult];
    }

}

export class RegisterForm {

    #emailUpdateForm;
    #loginUpdateForm;
    #passwordUpdateForm;

    constructor(form, email, emailValid, emailInvalid, login, loginValid, loginInvalid, password, passwordValid, passwordInvalid,
        passwordConfirmation, passwordConfirmationValid, passwordConfirmationInvalid) {
        this.#emailUpdateForm = new EmailUpdateForm(form, email, emailInvalid, emailValid);
        this.#loginUpdateForm = new LoginUpdateForm(form, login, loginInvalid, loginValid);
        this.#passwordUpdateForm = new PasswordUpdateForm(form, password, passwordConfirmation, 
            passwordInvalid, passwordConfirmationInvalid, passwordValid, passwordConfirmationValid);
    }

    setFeedback(isEmailCorrect, emailValidFeedback, emailInvalidFeedback,
        isLoginCorrect, loginValidFeedback, loginInvalidFeedback,
        isPasswordCorrect, passwordValidFeedback, passwordInvalidFeedback,
        isPasswordConfirmationCorrect, passwordConfirmationValidFeedback, passwordConfirmationInvalidFeedback) {
        this.#emailUpdateForm.setFeedback(isEmailCorrect, emailValidFeedback, emailInvalidFeedback);
        this.#loginUpdateForm.setFeedback(isLoginCorrect, loginValidFeedback, loginInvalidFeedback);
        this.#passwordUpdateForm.setFeedback(isPasswordCorrect, passwordValidFeedback, passwordInvalidFeedback, 
            isPasswordConfirmationCorrect, passwordConfirmationValidFeedback, passwordConfirmationInvalidFeedback);
    }

    setEmailFeedback(isEmailCorrect, emailValidFeedback, emailInvalidFeedback) {
        this.#emailUpdateForm.setFeedback(isEmailCorrect, emailValidFeedback, emailInvalidFeedback);
    }

    setLoginFeedback(isLoginCorrect, loginValidFeedback, loginInvalidFeedback) {
        this.#loginUpdateForm.setFeedback(isLoginCorrect, loginValidFeedback, loginInvalidFeedback);
    }

    setPasswordFeedback(isPasswordCorrect, passwordValidFeedback, passwordInvalidFeedback) {
        this.#passwordUpdateForm.setPasswordFeedback(isPasswordCorrect, passwordValidFeedback, passwordInvalidFeedback);
    }

    setPasswordConfirmationFeedback(isPasswordConfirmationCorrect, passwordConfirmationValidFeedback, passwordConfirmationInvalidFeedback) {
        this.#passwordUpdateForm.setPasswordConfirmationFeedback(isPasswordConfirmationCorrect, passwordConfirmationValidFeedback, passwordConfirmationInvalidFeedback);
    }

    removeValidation() {
        this.#emailUpdateForm.removeValidation();
        this.#loginUpdateForm.removeValidation();
        this.#passwordUpdateForm.removeValidation();
    }

    removeEmailValidation() {
        this.#emailUpdateForm.removeValidation();
    }

    removeLoginValidation() {
        this.#loginUpdateForm.removeValidation();
    }

    removePasswordValidation() {
        this.#passwordUpdateForm.removePasswordValidation();
    }

    removePasswordConfirmationValidation() {
        this.#passwordUpdateForm.removePasswordConfirmationValidation();
    }

    validate() {
        let emailValidationResult = this.#emailUpdateForm.validate();
        let loginValidationResult = this.#loginUpdateForm.validate();
        let passwordsValidations = this.#passwordUpdateForm.validate();
        let passwordValidationResult = passwordsValidations[0];
        let passwordConfirmationValidationResult = passwordsValidations[1];
        let passwordsEqual = passwordsValidations[2];
        return [emailValidationResult, loginValidationResult, passwordValidationResult, passwordConfirmationValidationResult, passwordsEqual];
    }

}