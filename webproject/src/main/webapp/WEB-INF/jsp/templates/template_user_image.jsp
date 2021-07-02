<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<img id="account-image" class="rounded-circle account-img"
			src="<c:url value="${currentUser.imageUrl}"/>" alt="no image">