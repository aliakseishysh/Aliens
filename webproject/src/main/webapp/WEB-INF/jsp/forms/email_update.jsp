<div class="content-section">
	<form id="email-update-form">
		<fieldset class="form-group">
			<legend class="border-bottom mb-4">Email Update</legend>
			<label class="control-label col-sm-2" for="email">Email:</label>
			<div class="col-sm-10">
  				<input type="email" class="form-control" id="email" name="email" placeholder="Enter email">
  			</div>
		</fieldset>
		<div class="form-group">
			<button class="btn btn-outline-info" 
					type="button" 
					onclick="updateUserEmail(
					 	`${PROJECT_NAME}`,
						`${CONTROLLER}`,
						`${COMMAND}`,
						`${UPDATE_USER_EMAIL}`,
						`${EMAIL}`,
						`${NEW_EMAIL}`,
						`${USER_ID}`,
						`${LOAD_EMAIL_UPDATE_FORM}`,
						`${currentUser.getId()}`,
						`${currentUser.getEmail()}`)">
				Update
			</button>
		</div>
	</form>
</div>