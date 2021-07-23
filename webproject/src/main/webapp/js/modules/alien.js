import { validateInput, validateTextarea, validateImage } from "./validation.js";
import { Validator, ValidationCleaner, Feedback } from "./util.js";


export class AlienForm {

    #form;
    #name;
    #descriptionSmall;
    #descriptionFull;
    #image;
    #imageLabel;
    #nameInvalid;
    #descriptionSmallInvalid;
    #descriptionFullInvalid;
    #imageInvalid;
    #nameValid;
    #descriptionSmallValid;
    #descriptionFullValid;
    #imageValid;
    
    constructor(form, name, descriptionSmall, descriptionFull, image, imageLabel, nameInvalid, descriptionSmallInvalid, 
        descriptionFullInvalid, imageInvalid, nameValid, descriptionSmallValid, descriptionFullValid, imageValid){
        this.#form = form;
        this.#name = name;
        this.#descriptionSmall = descriptionSmall;
        this.#descriptionFull = descriptionFull;
        this.#image = image;

        this.#imageLabel = imageLabel;
        this.#nameInvalid = nameInvalid;
        this.#descriptionSmallInvalid = descriptionSmallInvalid;
        this.#descriptionFullInvalid = descriptionFullInvalid;
        this.#imageInvalid = imageInvalid;

        this.#nameValid = nameValid;
        this.#descriptionSmallValid = descriptionSmallValid;
        this.#descriptionFullValid = descriptionFullValid;
        this.#imageValid = imageValid;

    }

    setFeedbackInfo(isNameCorrect, isSmallDescriptionCorrect, isFullDescriptionCorrect, nameValidFeedback, smallDescriptionValidFeedback, 
        fullDescriptionValidFeedback, nameInvalidFeedback, smallDescriptionInvalidFeedback, fullDescriptionInvalidFeedback) {
            Feedback.setElementFeedback(this.#name, this.#nameValid, this.#nameInvalid, isNameCorrect, nameValidFeedback, nameInvalidFeedback);
            Feedback.setElementFeedback(this.#descriptionSmall, this.#descriptionSmallValid, this.#descriptionSmallInvalid, 
                isSmallDescriptionCorrect, smallDescriptionValidFeedback, smallDescriptionInvalidFeedback);
            Feedback.setElementFeedback(this.#descriptionFull, this.#descriptionFullValid, this.#descriptionFullInvalid, 
                isFullDescriptionCorrect, fullDescriptionValidFeedback, fullDescriptionInvalidFeedback);
    }

    setServerFeedbackInfo(isNameCorrect, isSmallDescriptionCorrect, isFullDescriptionCorrect, nameFeedback, smallDescriptionFeedback, 
        fullDescriptionFeedback) {
        Feedback.setElementFeedback(this.#name, this.#nameValid, this.#nameInvalid, isNameCorrect, nameFeedback, nameFeedback);
        Feedback.setElementFeedback(this.#descriptionSmall, this.#descriptionSmallValid, this.#descriptionSmallInvalid, 
            isSmallDescriptionCorrect, smallDescriptionFeedback, smallDescriptionFeedback);
        Feedback.setElementFeedback(this.#descriptionFull, this.#descriptionFullValid, this.#descriptionFullInvalid, 
            isFullDescriptionCorrect, fullDescriptionFeedback, fullDescriptionFeedback);
    }

    setServerFeedbackName(isNameCorrect, nameFeedback) {
        Feedback.setElementFeedback(this.#name, this.#nameValid, this.#nameInvalid, isNameCorrect, nameFeedback, nameFeedback);
    }

    setFeedbackName(isNameCorrect, nameValidFeedback, nameInvalidFeedback) {
        Feedback.setElementFeedback(this.#name, this.#nameValid, this.#nameInvalid, isNameCorrect, nameValidFeedback, nameInvalidFeedback);
    }

    setServerFeedbackImage(isImageCorrect, imageFeedback) {
        Feedback.setElementFeedback(this.#image, this.#imageValid, this.#imageInvalid, isImageCorrect, imageFeedback, imageFeedback);
    }

    setFeedbackImage(isImageCorrect, imageValidFeedback, imageInvalidFeedback) {
        Feedback.setElementFeedback(this.#image, this.#imageValid, this.#imageInvalid, isImageCorrect, imageValidFeedback, imageInvalidFeedback);
    }
    
    removeValidationClasses() {
        ValidationCleaner.removeElementValidation(this.#form);
        ValidationCleaner.removeElementValidation(this.#name);
        ValidationCleaner.removeElementValidation(this.#descriptionSmall);
        ValidationCleaner.removeElementValidation(this.#descriptionFull);
        ValidationCleaner.removeElementValidation(this.#image);
    }

    removeInfoValidationClasses() {
        ValidationCleaner.removeElementValidation(this.#form);
        ValidationCleaner.removeElementValidation(this.#name);
        ValidationCleaner.removeElementValidation(this.#descriptionSmall);
        ValidationCleaner.removeElementValidation(this.#descriptionFull);
    }

    removeNameValidationClasses() {
        ValidationCleaner.removeElementValidation(this.#form);
        ValidationCleaner.removeElementValidation(this.#name);
    }

    removeSmallDescriptionValidationClasses(){
        ValidationCleaner.removeElementValidation(this.#form);
        ValidationCleaner.removeElementValidation(this.#descriptionSmall);
    }

    removeFullDescriptionValidationClasses(){
        ValidationCleaner.removeElementValidation(this.#form);
        ValidationCleaner.removeElementValidation(this.#descriptionFull);

    }

    removeImageValidationClasses() {
        ValidationCleaner.removeElementValidation(this.#form);
        ValidationCleaner.removeElementValidation(this.#image);
    }

    validateInfo() {
        let nameCheckResult = Validator.validateElement(this.#name, validateInput);
        let descriptionSmallCheckResult = Validator.validateElement(this.#descriptionSmall, validateInput);
        let descriptionFullCheckResult = Validator.validateElement(this.#descriptionFull, validateTextarea);
        return [nameCheckResult, descriptionSmallCheckResult, descriptionFullCheckResult];
    }

    validateName() {
        let nameCheckResult = Validator.validateElement(this.#name, validateInput);
        return nameCheckResult;
    }

    validateImage() {
        let imageCheckResult = Validator.validateElement(this.#image, validateImage);
        return imageCheckResult;
    }

    validate() {
        let nameCheckResult = Validator.validateElement(this.#name, validateInput);
        let descriptionSmallCheckResult = Validator.validateElement(this.#descriptionSmall, validateInput);
        let descriptionFullCheckResult = Validator.validateElement(this.#descriptionFull, validateTextarea);
        let imageCheckResult = Validator.validateElement(this.#image, validateImage);
        return [nameCheckResult, descriptionSmallCheckResult, descriptionFullCheckResult, imageCheckResult];
    }

    setLabelText() {
        this.#imageLabel.innerHTML = this.#image.files[0].name;
    }

}