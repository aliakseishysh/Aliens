<div class="content-section">
	<form id="login-update-form">
		<fieldset class="form-group">
			<legend class="border-bottom mb-4">Login Update</legend>
			<label class="control-label col-sm-2" for="login-update-form-login">Login:</label>
			<div class="col-sm-10">
  				<input type="text" class="form-control" id="login-update-form-login" name="login-update-form-login" placeholder="Enter login">
	  		</div>
		</fieldset>
		<div class="form-group col-sm-10">
			<button class="btn btn-outline-info" 
					type="button" 
					onclick="updateUserLogin(
						`${PROJECT_NAME}`,
						`${CONTROLLER}`,
						`${COMMAND}`,
						`${UPDATE_USER_LOGIN}`,
						`${LOGIN}`,
						`${NEW_LOGIN}`,
						`${USER_ID}`,
						`${LOAD_LOGIN_UPDATE_FORM}`,
						`${currentUser.id}`,
						`${currentUser.login}`)">
				Update
			</button>
		</div>
	</form>
</div>