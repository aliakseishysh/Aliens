function previousHomePage(pageToRequest) {
    var url = CONTROLLER + "?" + COMMAND + "=" + OPEN_HOME_PAGE + "&" + PAGE + "=" + pageToRequest;
    location.assign(url);
};

function nextHomePage(pageToRequest) {
    var url = CONTROLLER + "?" + COMMAND + "=" + OPEN_HOME_PAGE + "&" + PAGE + "=" + pageToRequest;
    location.assign(url);
};