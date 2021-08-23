import { WAS_VALIDATED_CLASS, IS_INVALID_CLASS, IS_VALID_CLASS } from "./bootstrap_classes.js";

export class BanUnbanForm {

    #form;
    #login;
    #loginInvalid;
    #daysInBan;
    #daysInBanInvalid;

    constructor(form, login, daysInBan, loginInvalid, daysInBanInvalid){
        this.#form = form;
        this.#login = login;
        this.#daysInBan = daysInBan;
        this.#loginInvalid = loginInvalid;
        this.#daysInBanInvalid = daysInBanInvalid;
    }

    setFeedback(isLoginCorrect, isDaysInBanCorrect, loginFeedback, daysInBanFeedback) {
        if (isLoginCorrect) {
            this.#setLoginValid();
            this.#setLoginValidFeedback(loginFeedback);
        } else {
            this.#setLoginInvalid();
            this.#setLoginInvalidFeedback(loginFeedback);
        }
        if (isDaysInBanCorrect === true || isDaysInBanCorrect == null) {
            this.#setDaysInBanValid();
            this.#setDaysInBanValidFeedback(daysInBanFeedback);
        } else if (isDaysInBanCorrect === false) {
            this.#setDaysInBanInvalid();
            this.#setDaysInBanInvalidFeedback(daysInBanFeedback);
        }
    }

    removeLoginValidationClasses(){
        this.#form.classList.remove(WAS_VALIDATED_CLASS);
        this.#login.classList.remove(IS_VALID_CLASS);
        this.#login.classList.remove(IS_INVALID_CLASS);
    }

    removeDaysInBanValidationClasses(){
        this.#form.classList.remove(WAS_VALIDATED_CLASS);
        this.#daysInBan.classList.remove(IS_VALID_CLASS);
        this.#daysInBan.classList.remove(IS_INVALID_CLASS);
    }

    removeValidationClasses() {
        this.removeLoginValidationClasses();
        this.removeDaysInBanValidationClasses();
    }
    
    #setLoginValid() {
        this.#login.classList.add(IS_VALID_CLASS);
    }

    #setLoginInvalid() {
        this.#login.classList.add(IS_INVALID_CLASS);
    }

    #setDaysInBanValid() {
        this.#daysInBan.classList.add(IS_VALID_CLASS);
    }

    #setDaysInBanInvalid() {
        this.#daysInBan.classList.add(IS_INVALID_CLASS);
    }

    #setLoginInvalidFeedback(invalidFeedback) {
        this.#loginInvalid.innerHTML = invalidFeedback;
    }

    #setLoginValidFeedback(validFeedback) {

    }

    #setDaysInBanInvalidFeedback(invalidFeedback) {
        this.#daysInBanInvalid.innerHTML = invalidFeedback;
    }

    #setDaysInBanValidFeedback(validFeedback) {
        
    }

}