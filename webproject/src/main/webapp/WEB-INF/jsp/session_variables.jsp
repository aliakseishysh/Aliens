<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<c:set var="PROJECT_NAME" scope="session" value="${sessionScope[PROJECT_NAME]}"/>
<c:set var="CONTROLLER" scope="session" value="${sessionScope[CONTROLLER]}"/>
<c:set var="ERROR_INFORMATION" scope="session" value="${sessionScope[ERROR_INFO]}"/>
<!-- Command values -->
<c:set var="OPEN_HOME_PAGE" scope="session" value="${sessionScope[OPEN_HOME_PAGE]}"/>
<c:set var="OPEN_ABOUT_PAGE" scope="session" value="${sessionScope[OPEN_ABOUT_PAGE]}"/>
<c:set var="OPEN_LOGIN_PAGE" scope="session" value="${sessionScope[OPEN_LOGIN_PAGE]}"/>
<c:set var="OPEN_REGISTER_PAGE" scope="session" value="${sessionScope[OPEN_REGISTER_PAGE]}"/>
<c:set var="OPEN_USER_PROFILE_PAGE" scope="session" value="${sessionScope[OPEN_USER_PROFILE_PAGE]}"/>
<c:set var="OPEN_ALIEN_PROFILE_PAGE" scope="session" value="${sessionScope[OPEN_ALIEN_PROFILE_PAGE]}"/>
<c:set var="REGISTER_USER" scope="session" value="${sessionScope[REGISTER_USER]}"/>
<c:set var="LOGIN_USER" scope="session" value="${sessionScope[LOGIN_USER]}"/>
<c:set var="LOGOUT_USER" scope="session" value="${sessionScope[LOGOUT_USER]}"/>
<c:set var="UPDATE_RATING" scope="session" value="${sessionScope[UPDATE_RATING]}"/>
<c:set var="UPDATE_USER_EMAIL" scope="session" value="${sessionScope[UPDATE_USER_EMAIL]}"/>
<c:set var="UPDATE_USER_LOGIN" scope="session" value="${sessionScope[UPDATE_USER_LOGIN]}"/>
<c:set var="UPDATE_USER_IMAGE" scope="session" value="${sessionScope[UPDATE_USER_IMAGE]}"/>
<c:set var="UPDATE_USER_PASSWORD" scope="session" value="${sessionScope[UPDATE_USER_PASSWORD]}"/>
<c:set var="FIND_USER_RATE" scope="session" value="${sessionScope[FIND_USER_RATE]}"/>
<c:set var="LOAD_EMAIL_UPDATE_FORM" scope="session" value="${sessionScope[LOAD_EMAIL_UPDATE_FORM]}"/>
<c:set var="LOAD_LOGIN_UPDATE_FORM" scope="session" value="${sessionScope[LOAD_LOGIN_UPDATE_FORM]}"/>
<c:set var="LOAD_IMAGE_UPDATE_FORM" scope="session" value="${sessionScope[LOAD_IMAGE_UPDATE_FORM]}"/>
<c:set var="LOAD_PASSWORD_UPDATE_FORM" scope="session" value="${sessionScope[LOAD_PASSWORD_UPDATE_FORM]}"/>

<!--  Request parameters -->
<c:set var="COMMAND" scope="session" value="${sessionScope[COMMAND]}"/>
<c:set var="ALIEN_ID" scope="session" value="${sessionScope[ALIEN_ID]}"/>
<c:set var="ALIEN_NAME" scope="session" value="${sessionScope[ALIEN_NAME]}"/>
<c:set var="RATING_VALUE" scope="session" value="${sessionScope[RATING_VALUE]}"/>
<c:set var="EMAIL" scope="session" value="${sessionScope[EMAIL]}"/>
<c:set var="NEW_EMAIL" scope="session" value="${sessionScope[NEW_EMAIL]}"/>
<c:set var="LOGIN" scope="session" value="${sessionScope[LOGIN]}"/>
<c:set var="NEW_LOGIN" scope="session" value="${sessionScope[NEW_LOGIN]}"/>
<c:set var="PASSWORD" scope="session" value="${sessionScope[PASSWORD]}"/>
<c:set var="PASSWORD_CONFIRM" scope="session" value="${sessionScope[PASSWORD_CONFIRM]}"/>
<c:set var="USER_ID" scope="session" value="${sessionScope[USER_ID]}"/>


<!--  Request attributes -->
<c:set var="ALIEN" scope="session" value="${sessionScope[ALIEN]}"/>
<c:set var="AVERAGE_RATING" scope="session" value="${sessionScope[AVERAGE_RATING]}"/>
<c:set var="ALIEN_LIST" scope="session" value="${sessionScope[ALIEN_LIST]}"/>
<c:set var="LOGIN_NAME" scope="session" value="${sessionScope[LOGIN_NAME]}"/>
<c:set var="CURRENT_USER" scope="session" value="${sessionScope[CURRENT_USER]}"/>

<!-- Page paths -->
<c:set var="NAV_JSP" scope="session" value="${sessionScope[NAV_JSP]}"/>
<c:set var="INDEX_JSP" scope="session" value="${sessionScope[INDEX_JSP]}"/>
<c:set var="HOME_JSP" scope="session" value="${sessionScope[HOME_JSP]}"/>
<c:set var="POST_JSP" scope="session" value="${sessionScope[POST_JSP]}"/>
<c:set var="ABOUT_JSP" scope="session" value="${sessionScope[ABOUT_JSP]}"/>
<c:set var="LOGIN_JSP" scope="session" value="${sessionScope[LOGIN_JSP]}"/>
<c:set var="REGISTER_JSP" scope="session" value="${sessionScope[REGISTER_JSP]}"/>
<c:set var="PROFILE_JSP" scope="session" value="${sessionScope[PROFILE_JSP]}"/>
<c:set var="USER_PROFILE_JSP" scope="session" value="${sessionScope[USER_PROFILE_JSP]}"/>
<c:set var="ADMIN_PROFILE_JSP" scope="session" value="${sessionScope[ADMIN_PROFILE_JSP]}"/>
<c:set var="ALIEN_PROFILE_JSP" scope="session" value="${sessionScope[ALIEN_PROFILE_JSP]}"/>
<c:set var="ERROR_PAGE_JSP" scope="session" value="${sessionScope[ERROR_PAGE_JSP]}"/>
<c:set var="EMAIL_UPDATE_FORM" scope="session" value="${sessionScope[UPDATE_EMAIL_FORM_JSP]}"/>
<c:set var="LOGIN_UPDATE_FORM" scope="session" value="${sessionScope[UPDATE_LOGIN_FORM_JSP]}"/>
<c:set var="IMAGE_UPDATE_FORM" scope="session" value="${sessionScope[UPDATE_IMAGE_FORM_JSP]}"/>
<c:set var="PASSWORD_UPDATE_FORM" scope="session" value="${sessionScope[UPDATE_PASSWORD_FORM_JSP]}"/>

<!-- File paths -->
<c:set var="IMAGE_DEFAULT" scope="session" value="${sessionScope[IMAGE_DEFAULT]}"/>
<c:set var="CSS_MAIN" scope="session" value="${sessionScope[CSS_MAIN]}"/>
<c:set var="CSS_RAITING" scope="session" value="${sessionScope[CSS_RAITING]}"/>
<c:set var="JS_RAITING" scope="session" value="${sessionScope[JS_RAITING]}"/>
<c:set var="JS_USER_PROFILE" scope="session" value="${sessionScope[JS_USER_PROFILE]}"/>

<!-- Roles -->
<c:set var="ROLE_USER" scope="session" value="${sessionScope[USER]}"/>
<c:set var="ROLE_ADMIN" scope="session" value="${sessionScope[ADMIN]}"/>