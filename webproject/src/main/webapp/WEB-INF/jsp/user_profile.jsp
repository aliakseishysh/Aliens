<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<link href="<c:url value="/css/main.css"/>" rel="stylesheet">
<script type="text/javascript" src="<c:url value='/js/user_profile.js'/>"></script>
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
	<form method="POST" 
	action="<c:url value="/controller?command=UPDATE_USER_INFO"/>">
		<fieldset class="form-group">
			<legend class="border-bottom mb-4">User Info Update</legend>
			<label class="control-label col-sm-2" for="email">Email:</label>
			<div class="col-sm-10">
   				<input type="email" class="form-control" id="email" name="email" placeholder="Enter email">
   			</div>
   			<label class="control-label col-sm-2" for="login">Login:</label>
			<div class="col-sm-10">
   				<input type="text" class="form-control" id="login" name="eloginloginmail" placeholder="Enter login">
   			</div>
		</fieldset>
		<div class="form-group">
			<button class="btn btn-outline-info" type="submit">Update</button>
		</div>
	</form>
	<form method="POST" 
	action="<c:url value="/controller?command=UPDATE_USER_IMAGE"/>"
	enctype="multipart/form-data">
		<fieldset class="form-group">
			<legend class="border-bottom mb-4">Profile Image Update</legend>
   			<label class="control-label col-sm-2" for="image">Image:</label>
			<div class="col-sm-10">
     			<input type="file" class="form-control" id="image" name="image" placeholder="Enter image">
   			</div>
		</fieldset>
		<div class="form-group">
			<button class="btn btn-outline-info" type="submit">Update</button>
		</div>
	</form>
	<form method="POST" 
	action="<c:url value="/controller?command=UPDATE_USER_PASSWORD"/>">
		<fieldset class="form-group">
			<legend class="border-bottom mb-4">Profile Password Update</legend>
   			<label class="control-label col-sm-2" for="password">Password:</label>
   			<div class="col-sm-10">
     				<input type="password" class="form-control" id="password" name="password" placeholder="Enter password">
   			</div>
   			<label class="control-label col-sm-2" for="password_confirm">Password confirm:</label>
   			<div class="col-sm-10">
     				<input type="password" class="form-control" id="password_confirm" name="password_confirm" placeholder="Enter password">
   			</div>
		</fieldset>
		<div class="form-group">
			<button class="btn btn-outline-info" type="submit">Update</button>
		</div>
	</form>
</div>

