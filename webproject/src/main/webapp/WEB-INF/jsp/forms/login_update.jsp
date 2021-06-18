<div class="content-section">
	<form id="login-update-form">
		<fieldset class="form-group">
			<legend class="border-bottom mb-4">Login Update</legend>
			<label class="control-label col-sm-2" for="login">Login:</label>
			<div class="col-sm-10">
  				<input type="email" class="form-control" id="login" name="login" placeholder="Enter login">
	  		</div>
		</fieldset>
		<div class="form-group">
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
						`${sessionScope[CURRENT_USER].getId()}`,
						`${sessionScope[CURRENT_USER].getLogin()}`)">
				Update
			</button>
		</div>
	</form>
</div>