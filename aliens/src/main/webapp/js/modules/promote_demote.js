import { WAS_VALIDATED_CLASS, IS_INVALID_CLASS, IS_VALID_CLASS } from "./bootstrap_classes.js";

export class PromoteDemoteForm {

    #form;
    #login;
    #loginInvalid;

    constructor(form, login, loginInvalid) {
        this.#form = form;
        this.#login = login;
        this.#loginInvalid = loginInvalid;
    }

    setFeedback(isLoginCorrect, loginFeedback) {
        if (isLoginCorrect) {
            this.#setLoginValid();
            this.#setLoginValidFeedback(loginFeedback);
        } else {
            this.#setLoginInvalid();
            this.#setLoginInvalidFeedback(loginFeedback);
        }
    }
    
    removeValidationClasses() {
        this.#form.classList.remove(WAS_VALIDATED_CLASS);
        this.#login.classList.remove(IS_VALID_CLASS);
        this.#login.classList.remove(IS_INVALID_CLASS);
    }

    #setLoginValid() {
        this.#login.classList.add(IS_VALID_CLASS);
    }

    #setLoginInvalid() {
        this.#login.classList.add(IS_INVALID_CLASS);
    }

    #setLoginInvalidFeedback(invalidFeedback) {
        this.#loginInvalid.innerHTML = invalidFeedback;
    }

    #setLoginValidFeedback(validFeedback) {

    }

}