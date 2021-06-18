<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8"
contentType="text/html;charset=UTF-8" 
language="java"
import="by.shyshaliaksey.webproject.controller.command.CommandValue,
 		by.shyshaliaksey.webproject.controller.command.RequestAttribute,
 		by.shyshaliaksey.webproject.controller.command.RequestParameter,
 		by.shyshaliaksey.webproject.controller.command.PagePath,
 		by.shyshaliaksey.webproject.controller.command.FilePath,by.shyshaliaksey.webproject.controller.command.SessionAttribute,
 		by.shyshaliaksey.webproject.model.entity.Role" %>
 
<c:set var="PROJECT_NAME" scope="session" value="webproject"/>
<c:set var="CONTROLLER" scope="session" value="${SessionAttribute.CONTROLLER.getValue()}"/>
<c:set var="ERROR_INFORMATION" scope="session" value="${SessionAttribute.ERROR_INFO.getValue()}"/>
<!-- Command values -->
<c:set var="OPEN_HOME_PAGE" scope="session" value="${CommandValue.OPEN_HOME_PAGE.getValue()}"/>
<c:set var="OPEN_ABOUT_PAGE" scope="session" value="${CommandValue.OPEN_ABOUT_PAGE.getValue()}"/>
<c:set var="OPEN_LOGIN_PAGE" scope="session" value="${CommandValue.OPEN_LOGIN_PAGE.getValue()}"/>
<c:set var="OPEN_REGISTER_PAGE" scope="session" value="${CommandValue.OPEN_REGISTER_PAGE.getValue()}"/>
<c:set var="OPEN_USER_PROFILE_PAGE" scope="session" value="${CommandValue.OPEN_USER_PROFILE_PAGE.getValue()}"/>
<c:set var="OPEN_ALIEN_PROFILE_PAGE" scope="session" value="${CommandValue.OPEN_ALIEN_PROFILE_PAGE.getValue()}"/>
<c:set var="REGISTER_USER" scope="session" value="${CommandValue.REGISTER_USER.getValue()}"/>
<c:set var="LOGIN_USER" scope="session" value="${CommandValue.LOGIN_USER.getValue()}"/>
<c:set var="LOGOUT_USER" scope="session" value="${CommandValue.LOGOUT_USER.getValue()}"/>
<c:set var="UPDATE_RATING" scope="session" value="${CommandValue.UPDATE_RATING.getValue()}"/>
<c:set var="UPDATE_USER_EMAIL" scope="session" value="${CommandValue.UPDATE_USER_EMAIL.getValue()}"/>
<c:set var="UPDATE_USER_LOGIN" scope="session" value="${CommandValue.UPDATE_USER_LOGIN.getValue()}"/>
<c:set var="UPDATE_USER_IMAGE" scope="session" value="${CommandValue.UPDATE_USER_IMAGE.getValue()}"/>
<c:set var="UPDATE_USER_PASSWORD" scope="session" value="${CommandValue.UPDATE_USER_PASSWORD.getValue()}"/>
<c:set var="FIND_USER_RATE" scope="session" value="${CommandValue.FIND_USER_RATE.getValue()}"/>
<c:set var="LOAD_EMAIL_UPDATE_FORM" scope="session" value="${CommandValue.LOAD_EMAIL_UPDATE_FORM.getValue()}"/>
<c:set var="LOAD_LOGIN_UPDATE_FORM" scope="session" value="${CommandValue.LOAD_LOGIN_UPDATE_FORM.getValue()}"/>
<c:set var="LOAD_IMAGE_UPDATE_FORM" scope="session" value="${CommandValue.LOAD_IMAGE_UPDATE_FORM.getValue()}"/>
<c:set var="LOAD_PASSWORD_UPDATE_FORM" scope="session" value="${CommandValue.LOAD_PASSWORD_UPDATE_FORM.getValue()}"/>

<!--  Request parameters -->
<c:set var="COMMAND" scope="session" value="${RequestParameter.COMMAND.getValue()}"/>
<c:set var="ALIEN_ID" scope="session" value="${RequestParameter.ALIEN_ID.getValue()}"/>
<c:set var="ALIEN_NAME" scope="session" value="${RequestParameter.ALIEN_NAME.getValue()}"/>
<c:set var="RATING_VALUE" scope="session" value="${RequestParameter.RATING_VALUE.getValue()}"/>
<c:set var="EMAIL" scope="session" value="${RequestParameter.EMAIL.getValue()}"/>
<c:set var="NEW_EMAIL" scope="session" value="${RequestParameter.NEW_EMAIL.getValue()}"/>
<c:set var="LOGIN" scope="session" value="${RequestParameter.LOGIN.getValue()}"/>
<c:set var="NEW_LOGIN" scope="session" value="${RequestParameter.NEW_LOGIN.getValue()}"/>
<c:set var="PASSWORD" scope="session" value="${RequestParameter.PASSWORD.getValue()}"/>
<c:set var="PASSWORD_CONFIRM" scope="session" value="${RequestParameter.PASSWORD_CONFIRM.getValue()}"/>
<c:set var="USER_ID" scope="session" value="${RequestParameter.USER_ID.getValue()}"/>


<!--  Request attributes -->
<c:set var="ALIEN" scope="session" value="${RequestAttribute.ALIEN.getValue()}"/>
<c:set var="AVERAGE_RATING" scope="session" value="${RequestAttribute.AVERAGE_RATING.getValue()}"/>
<c:set var="ALIEN_LIST" scope="session" value="${RequestAttribute.ALIEN_LIST.getValue()}"/>
<c:set var="LOGIN_NAME" scope="session" value="${RequestAttribute.LOGIN_NAME.getValue()}"/>
<c:set var="CURRENT_USER" scope="session" value="${RequestAttribute.CURRENT_USER.getValue()}"/>

<!-- Page paths -->
<c:set var="NAV_JSP" scope="session" value="${PagePath.NAV_JSP.getValue()}"/>
<c:set var="INDEX_JSP" scope="session" value="${PagePath.INDEX_JSP.getValue()}"/>
<c:set var="HOME_JSP" scope="session" value="${PagePath.HOME_JSP.getValue()}"/>
<c:set var="POST_JSP" scope="session" value="${PagePath.POST_JSP.getValue()}"/>
<c:set var="ABOUT_JSP" scope="session" value="${PagePath.ABOUT_JSP.getValue()}"/>
<c:set var="LOGIN_JSP" scope="session" value="${PagePath.LOGIN_JSP.getValue()}"/>
<c:set var="REGISTER_JSP" scope="session" value="${PagePath.REGISTER_JSP.getValue()}"/>
<c:set var="PROFILE_JSP" scope="session" value="${PagePath.PROFILE_JSP.getValue()}"/>
<c:set var="USER_PROFILE_JSP" scope="session" value="${PagePath.USER_PROFILE_JSP.getValue()}"/>
<c:set var="ADMIN_PROFILE_JSP" scope="session" value="${PagePath.ADMIN_PROFILE_JSP.getValue()}"/>
<c:set var="ALIEN_PROFILE_JSP" scope="session" value="${PagePath.ALIEN_PROFILE_JSP.getValue()}"/>
<c:set var="ERROR_PAGE_JSP" scope="session" value="${PagePath.ERROR_PAGE_JSP.getValue()}"/>
<c:set var="EMAIL_UPDATE_FORM" scope="session" value="${PagePath.UPDATE_EMAIL_FORM_JSP.getValue()}"/>
<c:set var="LOGIN_UPDATE_FORM" scope="session" value="${PagePath.UPDATE_LOGIN_FORM_JSP.getValue()}"/>
<c:set var="IMAGE_UPDATE_FORM" scope="session" value="${PagePath.UPDATE_IMAGE_FORM_JSP.getValue()}"/>
<c:set var="PASSWORD_UPDATE_FORM" scope="session" value="${PagePath.UPDATE_PASSWORD_FORM_JSP.getValue()}"/>
 


<!-- File paths -->
<c:set var="IMAGE_DEFAULT" scope="session" value="${FilePath.IMAGE_DEFAULT.getValue()}"/>
<c:set var="CSS_MAIN" scope="session" value="${FilePath.CSS_MAIN.getValue()}"/>
<c:set var="CSS_RAITING" scope="session" value="${FilePath.CSS_RAITING.getValue()}"/>
<c:set var="JS_RAITING" scope="session" value="${FilePath.JS_RAITING.getValue()}"/>
<c:set var="JS_USER_PROFILE" scope="session" value="${FilePath.JS_USER_PROFILE.getValue()}"/>

<!-- Roles -->
<c:set var="ROLE_USER" scope="session" value="${Role.USER}"/>
<c:set var="ROLE_ADMIN" scope="session" value="${Role.ADMIN}"/>