import { WAS_VALIDATED_CLASS, IS_INVALID_CLASS, IS_VALID_CLASS } from "./bootstrap_classes.js";

export class Comment {
    
    #form;
    #comment;
    #commentInvalid;

    constructor (form, comment, commentInvalid) {
        this.#form = form;
        this.#comment = comment;
        this.#commentInvalid = commentInvalid;
    }

    setCommentFeedback(commentStatus, commentFeedback) {
        if (commentStatus === false) {
            this.#setCommentInvalid();
            this.#setCommentInvalidFeedback(commentFeedback);
            
        } else {
            this.#setCommentValid();
            this.#setCommentValidFeedback(commentFeedback);
        }
    }

    validateComment() {
        let commentCheckResult = false;
        if (this.#comment.value !== "" && this.#comment.value.replaceAll('\n', '\\n').match(this.#comment.getAttribute("pattern"))) {
            commentCheckResult = true;
            this.#setCommentValid();
        }
        return commentCheckResult;
    }

    removeCommentValidationClasses() {
        this.#form.classList.remove(WAS_VALIDATED_CLASS);
        this.#comment.classList.remove(IS_VALID_CLASS);
        this.#comment.classList.remove(IS_INVALID_CLASS);
    }

    #setCommentValid() {
        this.#comment.classList.add(IS_VALID_CLASS);
        this.#comment.classList.remove(IS_INVALID_CLASS);
    }

    #setCommentInvalid() {
        this.#comment.classList.add(IS_INVALID_CLASS);
        this.#comment.classList.remove(IS_VALID_CLASS);
    }

    #setCommentInvalidFeedback(invalidFeedback) {
        this.#commentInvalid.innerHTML = invalidFeedback;
    }

    #setCommentValidFeedback() {
    }



}