function importAndSet(path, name) {
    import(path).then(module => {
        window[name] = module[name];
    });
}

document.addEventListener("DOMContentLoaded",() => {
    let urlParameters = new URLSearchParams(window.location.search);
    let command = urlParameters.get("command");
    switch(command) {
        case "register-page":
            importAndSet("./nav.js", "navigation");
            import("./register.js");
            break;
        case "admin-functional-page":
            importStandard();
            import("./admin_profile.js");
            break;
        case "suggested-aliens-images-page":
            importAndSet("./nav.js", "navigation");
            import("./admin_page_suggested_aliens_images.js");
            importAndSet("./modules/pagination.js", "pagination");
            break;
        case "suggest-alien-page":
            importAndSet("./nav.js", "navigation");
            import("./alien_suggest.js");
            break;
        case "suggested-aliens-page":
            importAndSet("./nav.js", "navigation");
            import("./admin_page_suggested_aliens.js");
            importAndSet("./modules/pagination.js", "pagination");
            break;
        case "alien-profile-page":
            importAndSet("./nav.js", "navigation");
            import("./alien_profile.js");
            break;
        case "banned-page":
            importAndSet("./nav.js", "navigation");
            break;
        case "home-page":
            importAndSet("./nav.js", "navigation");
            importAndSet("./modules/pagination.js", "pagination");
            break;
        case "login-page":
            importAndSet("./nav.js", "navigation");
            import("./login.js");
            break;
        case "user-profile-page":
            importAndSet("./nav.js", "navigation");
            import("./util.js");
            import("./user_profile.js");
            break;
    }
});

