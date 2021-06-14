<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<link href="<c:url value="/css/main.css"/>" rel="stylesheet">
</head>
<h1>Admin Profile</h1>
<div class="content-section">
	<div class="media">
		<img class="rounded-circle account-img"
			src="<c:url value="${currentUser.getImageUrl()}"/>" alt="no image">
		<div class="media-body">
			<h2 class="account-heading">${currentUser.getLogin()}</h2>
			<p class="text-secondary">${ currentUser.getEmail()}</p>
		</div>
	</div>
	<form method="POST" action="" enctype="multipart/form-data">
		<fieldset class="form-group">
			<legend class="border-bottom mb-4">Bans</legend>
			<label class="control-label col-sm-2" for="email">Email:</label>
			<div class="col-sm-10">
   				<input type="email" class="form-control" id="email" name="email" placeholder="Enter email">
   			</div>
		</fieldset>
		<div class="form-group">
			<button class="btn btn-outline-info" type="submit">Block</button>
			<button class="btn btn-outline-info" type="submit">Unblock</button>
		</div>
	</form>
</div>
