<div class="content-section">
	<form id="form-promote-demote-user" class="needs-validation" novalidate>
		<fieldset class="form-group">
			<legend class="border-bottom mb-4">Promotes/Demotes</legend>
			<label class="control-label col-sm-2" for="form-promote-demote-user-login">User Login:</label>
			<div class="col-sm-10">
  				<input type="text" class="form-control" id="form-promote-demote-user-login" name="form-promote-demote-user-login" 
  				placeholder="Enter user login" 
  				pattern="${VALID_LOGIN}"
  				required>
  				<div id="form-promote-demote-user-login-invalid-feedback" class="invalid-feedback">
        			${PROMOTE_DEMOTE_USER_STANDARD_LOGIN_FEEDBACK}
      			</div>
  			</div>
		</fieldset>
		<div class="form-group col-sm-10">
			<button class="btn btn-outline-info" 
					type="submit" 
					id="form-promote-demote-user-button-promoteuser">
				Promote
			</button>
			<button class="btn btn-outline-info" 
					type="submit" 
					id="form-promote-demote-user-button-demoteadmin">
				Demote
			</button>
		</div>
	</form>
</div>