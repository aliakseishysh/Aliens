<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<link href="<c:url value="/css/main.css"/>" rel="stylesheet">
</head>
<h1>User Profile</h1>
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
			<legend class="border-bottom mb-4">Profile Info</legend>
			<label class="control-label col-sm-2" for="email">Email:</label>
			<div class="col-sm-10">
     				<input type="email" class="form-control" id="email" name="email" placeholder="Enter email">
   			</div>
   			<label class="control-label col-sm-2" for="login">Login:</label>
			<div class="col-sm-10">
     				<input type="text" class="form-control" id="login" name="eloginloginmail" placeholder="Enter login">
   			</div>
   			<label class="control-label col-sm-2" for="image">Image:</label>
			<div class="col-sm-10">
     				<input type="text" class="form-control" id="image" name="image" placeholder="Enter image">
   			</div>
   			<label class="control-label col-sm-2" for="password">Password:</label>
			<div class="col-sm-10">
     				<input type="password" class="form-control" id="password" name="password" placeholder="Enter password">
   			</div>
		</fieldset>
		<div class="form-group">
			<button class="btn btn-outline-info" type="submit">Update</button>
		</div>
	</form>
</div>
