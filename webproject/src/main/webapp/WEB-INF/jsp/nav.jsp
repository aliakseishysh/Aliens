<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header class="site-header">
	<nav class="navbar navbar-expand-md navbar-dark bg-steel fixed-top">
		<div class="container">
			<a class="navbar-brand mr-4" href='<c:url value="/controller?command=REDIRECT_HOME"/>'>Aliens</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarToggle" aria-controls="navbarToggle"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarToggle">
				<div class="navbar-nav mr-auto">
					<a class="nav-item nav-link" href="<c:url value="/controller?command=REDIRECT_HOME"/>">Home</a>
					<a class="nav-item nav-link" href="<c:url value="/controller?command=REDIRECT_ABOUT"/>">About</a>
				</div>
				<div class="navbar-nav">
					<c:choose>
						<c:when test="${sessionScope.login_name == null}">
							<a class="nav-item nav-link" href='<c:url value="/controller?command=REDIRECT_LOGIN"/>'>Login</a>
							<a class="nav-item nav-link" href='<c:url value="/controller?command=REDIRECT_REGISTER"/>'>Register</a>
						</c:when>
						<c:when test="${sessionScope.login_name != null}">
							<a class="nav-item nav-link" href='<c:url value="/controller?command=REDIRECT_USER_PROFILE"/>'>Profile</a>
							<a class="nav-item nav-link" href='<c:url value="/controller?command=LOGOUT_USER"/>'>Logout</a>
						</c:when>
					</c:choose>
				</div>
			</div>
		</div>
	</nav>
</header>