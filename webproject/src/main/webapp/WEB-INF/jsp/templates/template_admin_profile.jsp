<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js'></script>
<script type="text/javascript" src="<c:url value='${JS_USER_PROFILE}'/>"></script>
<script type="text/javascript" src="<c:url value='${JS_ADMIN_PROFILE}'/>"></script>

<h1>Admin Profile</h1>
<div class="content-section">
	<div class="media">
		<jsp:include page="${TEMPLATE_USER_IMAGE_JSP}"/>
		<div class="media-body">
			<h2 id="user-profile-account-login" class="account-heading">${currentUser.login}</h2>
			<p id="user-profile-account-email" class="text-secondary">${currentUser.email}</p>
		</div>
	</div>
	<jsp:include page="${FORM_ALIEN_CREATE_JSP}"/>
	<jsp:include page="${FORM_BAN_UNBAN_USER_JSP}"/>
	<jsp:include page="${FORM_PROMOTE_DEMOTE_JSP}"/>
	<jsp:include page="${FORM_UPDATE_EMAIL_JSP}"/>
	<jsp:include page="${FORM_UPDATE_LOGIN_JSP}"/>
	<jsp:include page="${FORM_UPDATE_IMAGE_JSP}"/>
	<jsp:include page="${FORM_UPDATE_PASSWORD_JSP}"/>
</div>
