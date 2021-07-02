<div class="content-section">
	<form id="promote-demote-form">
		<fieldset class="form-group">
			<legend class="border-bottom mb-4">Promotes/Demotes</legend>
			<label class="control-label col-sm-2" for="promote-demote-user-login">User Login:</label>
			<div class="col-sm-10">
  				<input type="text" class="form-control" id="promote-demote-user-login" name="promote-demote-user-login" placeholder="Enter user login">
  			</div>
		</fieldset>
		<div class="form-group col-sm-10">
			<button class="btn btn-outline-info" 
					type="button" 
					onclick="promoteUser(
					 	`${PROJECT_NAME}`,
						`${CONTROLLER}`,
						`${COMMAND}`,
						`${PROMOTE_USER}`,
						`${LOAD_PROMOTE_DEMOTE_FORM}`,
						`${LOGIN}`)">
				Promote
			</button>
			<button class="btn btn-outline-info" 
					type="button" 
					onclick="demoteAdmin(
					 	`${PROJECT_NAME}`,
						`${CONTROLLER}`,
						`${COMMAND}`,
						`${DEMOTE_ADMIN}`,
						`${LOAD_PROMOTE_DEMOTE_FORM}`,
						`${LOGIN}`)">
				Demote
			</button>
		</div>
	</form>
</div>