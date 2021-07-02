<div class="content-section">
	<form id="email-update-form">
		<fieldset class="form-group">
			<legend class="border-bottom mb-4">Email Update</legend>
			<label class="control-label col-sm-2" for="email-update-form-email">Email:</label>
			<div class="col-sm-10">
  				<input type="email" class="form-control" id="email-update-form-email" name="email-update-form-email" placeholder="Enter email">
  			</div>
		</fieldset>
		<div class="form-group col-sm-10">
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
						`${currentUser.id}`,
						`${currentUser.email}`)">
				Update
			</button>
		</div>
	</form>
</div>