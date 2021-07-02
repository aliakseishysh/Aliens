<div class="content-section">
	<form id="password-update-form">
		<fieldset class="form-group">
			<legend class="border-bottom mb-4">Password Update</legend>
   			<label class="control-label col-sm-2" for="password-update-form-password">Password:</label>
   			<div class="col-sm-10">
    			<input type="password" class="form-control" id="password-update-form-password" name="password-update-form-password" placeholder="Enter password">
   			</div>
   			<label class="control-label col-sm-2" for="password-update-form-password-confirm">Password confirm:</label>
   			<div class="col-sm-10">
     			<input type="password" class="form-control" id="password-update-form-password-confirm" name="password-update-form-password-confirm" placeholder="Enter password">
   			</div>
		</fieldset>
		<div class="form-group col-sm-10">
			<button class="btn btn-outline-info" 
					type="button" 
					onclick="updateUserPassword(
						`${PROJECT_NAME}`,
						`${CONTROLLER}`,
						`${COMMAND}`,
						`${UPDATE_USER_PASSWORD}`,
						`${PASSWORD}`,
						`${PASSWORD_CONFIRM}`,
						`${USER_ID}`,
						`${LOAD_PASSWORD_UPDATE_FORM}`,
						`${currentUser.id}`)">
				Update
			</button>
		</div>
	</form>
</div>