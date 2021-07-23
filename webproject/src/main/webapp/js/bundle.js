function importAndSet(path, name) {
    import(path).then(module => {
        window[name] = module[name];
    });
}

document.addEventListener("DOMContentLoaded",() => {
    let urlParameters = new URLSearchParams(window.location.search);
    let command = urlParameters.get("command");
    importAndSet("./nav.js", "navigation");
    switch(command) {
        case "register-page":
            import("./pages/register.js");
            break;
        case "admin-functional-page":
            import("./pages/admin_profile.js");
            break;
        case "suggested-aliens-images-page":
            import("./pages/admin_page_suggested_aliens_images.js");
            importAndSet("./modules/pagination.js", "pagination");
            break;
        case "suggest-alien-page":
            import("./pages/alien_suggest.js");
            break;
        case "suggested-aliens-page":
            import("./pages/admin_page_suggested_aliens.js");
            importAndSet("./modules/pagination.js", "pagination");
            break;
        case "alien-profile-page":
            import("./pages/alien_profile.js");
            break;
        case "banned-page":
            break;
        case "home-page":
            importAndSet("./modules/pagination.js", "pagination");
            break;
        case "login-page":
            import("./pages/login.js");
            break;
        case "user-profile-page":
            import("./util.js");
            import("./pages/user_profile.js");
            break;
    }
});

