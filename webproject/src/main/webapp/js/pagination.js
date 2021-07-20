function changePage(pageToRequest) {
    let url = CONTROLLER + "?" + COMMAND + "=" + PAGINATION_PAGE_TO_GO + "&" + PAGE + "=" + pageToRequest;
    try {
        if (PAGINATION_PAGE_TO_GO == OPEN_ALIEN_PROFILE_PAGE) {
            // if current page is alien profile
            let alienIdHidden = document.getElementById("alien-id-hidden");
            url = CONTROLLER + "?" + COMMAND + "=" + PAGINATION_PAGE_TO_GO + "&" + ALIEN_ID + "=" + alienIdHidden.innerHTML + "&" + PAGE + "=" + pageToRequest;
        }
    } catch (e) {
        if (e instanceof ReferenceError) {
            // we are not at alien profile page
        } else {
            throw e;
        }
    }   
    location.assign(url);
};

const pagination = {
    changePage: changePage
}
