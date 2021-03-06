import { WAS_VALIDATED_CLASS, IS_INVALID_CLASS, IS_VALID_CLASS } from "./bootstrap_classes.js";

export class Validator {

    static validateElement(element, validationFunction) {
        let validationResult = validationFunction(element);
        if (validationResult) {
            Feedback.setElementValid(element);
        } else {
            Feedback.setElementInvalid(element);
        }
        return validationResult;
    }

    static validateEquality(element1, element2, validationFunction) {
        let validationResult = validationFunction(element1, element2);
        if (validationResult) {
            Feedback.setElementValid(element1);
            Feedback.setElementValid(element2);
        } else {
            Feedback.setElementInvalid(element1);
            Feedback.setElementInvalid(element2);
        }
        return validationResult;
    }

}

export class ValidationCleaner  {

    static removeElementValidation(element) {
        element.classList.remove(WAS_VALIDATED_CLASS);
        element.classList.remove(IS_INVALID_CLASS);
        element.classList.remove(IS_VALID_CLASS);
    }

}

export class Feedback {

    static setElementFeedback(element, elementValid, elementInvalid, isElementCorrect, elementValidFeedback, elementInvalidFeedback) {
        if (isElementCorrect) {
            Feedback.setElementValid(element);
            Feedback.setElementValidFeedback(elementValid, elementValidFeedback);
        } else {
            Feedback.setElementInvalid(element);
            Feedback.setElementInvalidFeedback(elementInvalid, elementInvalidFeedback);
        }
    }

    static setElementValid(element) {
        element.classList.add(IS_VALID_CLASS);
    }

    static setElementInvalid(element) {
        element.classList.add(IS_INVALID_CLASS);
    }

    static setElementInvalidFeedback(elementInvalid, invalidFeedback) {
        elementInvalid.innerHTML = invalidFeedback;
    }

    static setElementValidFeedback(elementValid, validFeedback) {
        if (elementValid != null) {
            elementValid.innerHTML = validFeedback;
        }
    }

}

export function removeParameterFromUrl(parameter) {
    let urlParameters = new URLSearchParams(window.location.search);
    if (urlParameters.has(parameter)) {
        urlParameters.delete(parameter);
        let newUrl = urlParameters.toString();
        window.history.replaceState({}, document.title, "?" + newUrl);
    }
}

export function changeLocationIfUndefined(jqXHR) {
    if (jqXHR.responseJSON === undefined) {
        let baseUrl = CONTROLLER + "?" + COMMAND + "=";
        switch(jqXHR.status) {
            case 400:
                location.assign(baseUrl + OPEN_400_ERROR_PAGE);
                break;
            case 403:
                location.assign(baseUrl + OPEN_403_ERROR_PAGE);
                break;
            case 404:
                location.assign(baseUrl + OPEN_404_ERROR_PAGE);
                break;
            case 500:
                location.assign(baseUrl + OPEN_500_ERROR_PAGE);
                break;
            default:
                location.assign(baseUrl + OPEN_500_ERROR_PAGE);
                break;
        }
        return true;
    }
    return false;
}