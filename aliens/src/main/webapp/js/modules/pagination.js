function changePage(pageToRequest) {
    let urlParameters = new URLSearchParams(window.location.search);
    urlParameters.set(PAGE, pageToRequest);
    window.location.search = urlParameters.toString();
}

export const pagination = {
    changePage: changePage
}
