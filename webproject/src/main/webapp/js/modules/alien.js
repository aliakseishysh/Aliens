import { WAS_VALIDATED_CLASS, IS_INVALID_CLASS, IS_VALID_CLASS } from "./bootstrap_classes.js";

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
    
    constructor(form, name, descriptionSmall, descriptionFull, image, imageLabel, nameInvalid, descriptionSmallInvalid, descriptionFullInvalid, imageInvalid){
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
    }

    setFeedbackInfo(isNameCorrect, isSmallDescriptionCorrect, isFullDescriptionCorrect, nameFeedback, smallDescriptionFeedback, fullDescriptionFeedback) {
        if (isNameCorrect) {
            this.#setNameValid();
            this.#setNameValidFeedback(nameFeedback);
        } else {
            this.#setNameInvalid();
            this.#setNameInvalidFeedback(nameFeedback);
        }
        if (isSmallDescriptionCorrect) {
            this.#setDescriptionSmallValid();
            this.#setDescriptionSmallValidFeedback(smallDescriptionFeedback);
        } else {
            this.#setDescriptionSmallInvalid();
            this.#setDescriptionSmallInvalidFeedback(smallDescriptionFeedback);
        }
        if (isFullDescriptionCorrect) {
            this.#setDescriptionFullValid();
            this.#setDescriptionFullValidFeedback(fullDescriptionFeedback);
        } else {
            this.#setDescriptionFullInvalid();
            this.#setDescriptionFullInvalidFeedback(fullDescriptionFeedback);
        }
    }

    setFeedbackImage(isImageCorrect, imageFeedback) {
        if (isImageCorrect) {
            this.#setImageValid();
            this.#setImageValidFeedback(imageFeedback);
        } else {
            this.#setImageInvalid();
            this.#setImageInvalidFeedback(imageFeedback);
        }
    }
    
    removeValidationClasses() {
        this.#form.classList.remove(WAS_VALIDATED_CLASS);
        this.#name.classList.remove(IS_VALID_CLASS);
        this.#descriptionSmall.classList.remove(IS_VALID_CLASS);
        this.#descriptionFull.classList.remove(IS_VALID_CLASS);
        this.#image.classList.remove(IS_VALID_CLASS);
        this.#name.classList.remove(IS_INVALID_CLASS);
        this.#descriptionSmall.classList.remove(IS_INVALID_CLASS);
        this.#descriptionFull.classList.remove(IS_INVALID_CLASS);
        this.#image.classList.remove(IS_INVALID_CLASS);
    }

    removeInfoValidationClasses() {
        this.#form.classList.remove(WAS_VALIDATED_CLASS);
        this.#name.classList.remove(IS_VALID_CLASS);
        this.#descriptionSmall.classList.remove(IS_VALID_CLASS);
        this.#descriptionFull.classList.remove(IS_VALID_CLASS);
        this.#name.classList.remove(IS_INVALID_CLASS);
        this.#descriptionSmall.classList.remove(IS_INVALID_CLASS);
        this.#descriptionFull.classList.remove(IS_INVALID_CLASS);
    }

    removeNameValidationClasses() {
        this.#form.classList.remove(WAS_VALIDATED_CLASS);
        this.#name.classList.remove(IS_VALID_CLASS);
        this.#name.classList.remove(IS_INVALID_CLASS);
    }

    removeSmallDescriptionValidationClasses(){
        this.#form.classList.remove(WAS_VALIDATED_CLASS);
        this.#descriptionSmall.classList.remove(IS_VALID_CLASS);
        this.#descriptionSmall.classList.remove(IS_INVALID_CLASS);
    }

    removeFullDescriptionValidationClasses(){
        this.#form.classList.remove(WAS_VALIDATED_CLASS);
        this.#descriptionFull.classList.remove(IS_VALID_CLASS);
        this.#descriptionFull.classList.remove(IS_INVALID_CLASS);

    }

    removeImageValidationClasses() {
        this.#form.classList.remove(WAS_VALIDATED_CLASS);
        this.#image.classList.remove(IS_VALID_CLASS);
        this.#image.classList.remove(IS_INVALID_CLASS);
    }

    validateInfo() {
        let nameCheckResult = false;
        let descriptionSmallCheckResult = false;
        let descriptionFullCheckResult = false;

        if (this.#name.value != "" && this.#name.value.match(this.#name.getAttribute("pattern"))) {
            nameCheckResult = true;
            this.#setNameValid();
        }
        if (this.#descriptionSmall.value != "" && this.#descriptionSmall.value.match(this.#descriptionSmall.getAttribute("pattern"))) {
            descriptionSmallCheckResult = true;
            this.#setDescriptionSmallValid();
        }
        if (this.#descriptionFull.value != "" && this.#descriptionFull.value.replaceAll('\n', '\\n').match(this.#descriptionFull.getAttribute("pattern"))) {
            descriptionFullCheckResult = true;
            this.#setDescriptionFullValid();
        }
        return [nameCheckResult, descriptionSmallCheckResult, descriptionFullCheckResult];
    }

    validateImage() {
        let files = this.#image.files;
        
        let imageCheckResult = false;
        let validExtensions = ["image/jpg", "image/jpeg", "image/png"];
       
        if (files.length == 1) {
            let file = files[0];
            
            if (file.size <= 1000000) { // bytes
                for (let i = 0; i < validExtensions.length; i++) {
                    if (validExtensions[i] == file.type) {
                        imageCheckResult = true;
                        this.#setImageValid();
                        break;
                    }
                }    
            }
        }
        return imageCheckResult;
    }

    setLabelText(text) {
        this.#imageLabel.innerHTML = text;
    }

    #setNameValid() {
        this.#name.classList.add(IS_VALID_CLASS);
    }

    #setNameInvalid() {
        this.#name.classList.add(IS_INVALID_CLASS);
    }

    #setNameInvalidFeedback(invalidFeedback) {
        this.#nameInvalid.innerHTML = invalidFeedback;
    }

    #setNameValidFeedback(validFeedback) {
    }

    #setDescriptionSmallValid() {
        this.#descriptionSmall.classList.add(IS_VALID_CLASS);
    }

    #setDescriptionSmallInvalid() {
        this.#descriptionSmall.classList.add(IS_INVALID_CLASS);
    }

    #setDescriptionSmallInvalidFeedback(invalidFeedback) {
        this.#descriptionSmallInvalid.innerHTML = invalidFeedback;
    }

    #setDescriptionSmallValidFeedback(validFeedback) {
    }

    #setDescriptionFullValid() {
        this.#descriptionFull.classList.add(IS_VALID_CLASS);
    }

    #setDescriptionFullInvalid() {
        this.#descriptionFull.classList.add(IS_INVALID_CLASS);
    }

    #setDescriptionFullInvalidFeedback(invalidFeedback) {
        this.#descriptionFullInvalid.innerHTML = invalidFeedback;
    }

    #setDescriptionFullValidFeedback(validFeedback) {
    }

    #setImageValid() {
        this.#image.classList.add(IS_VALID_CLASS);
    }

    #setImageInvalid() {
        this.#image.classList.add(IS_INVALID_CLASS);
    }

    #setImageInvalidFeedback(invalidFeedback) {
        this.#imageInvalid.innerHTML = invalidFeedback;
    }

    #setImageValidFeedback(validFeedback) {
    }

}