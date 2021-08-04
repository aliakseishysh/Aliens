<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>${TEXT[TEMPLATE_USER_PROFILE_H1]}</h1>
<div class="content-section">
	<div class="media">
		<jsp:include page="${TEMPLATE_USER_IMAGE_JSP}"/>
		<div class="media-body">
			<h2 id="user-profile-account-login" class="account-heading text-break">${currentUser.login}</h2>
			<p id="user-profile-account-email" class="text-secondary text-break">${currentUser.email}</p>
		</div>
	</div>
	<jsp:include page="${FORM_UPDATE_EMAIL_JSP}"/>
	<jsp:include page="${FORM_UPDATE_LOGIN_JSP}"/>
	<jsp:include page="${FORM_UPDATE_IMAGE_JSP}"/>
	<jsp:include page="${FORM_UPDATE_PASSWORD_JSP}"/>
</div>

