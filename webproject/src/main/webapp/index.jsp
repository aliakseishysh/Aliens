<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="by.shyshaliaksey.webproject.controller.command.PagePath" %>

<c:set var="SESSION_VARIABLES" scope="page" value="${PagePath.SESSION_VARIABLES_JSP.getValue()}"/>

<c:if test="${CONTROLLER == null}">
	<jsp:include page="${SESSION_VARIABLES}"/>
</c:if>

<c:redirect url="/${CONTROLLER}?${COMMAND}=${OPEN_HOME_PAGE}"/>


