<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<img id="account-image" class="rounded-circle account-img"
			src="<c:url value="${currentUser.imageUrl}"/>" alt="${TEXT[TEMPLATE_USER_IMAGE_ALT]}">