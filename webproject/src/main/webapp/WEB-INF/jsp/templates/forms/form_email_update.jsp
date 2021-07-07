<div class="content-section">
	<form id="form-update-email" class="needs-validation" novalidate>
		<fieldset class="form-group">
			<legend class="border-bottom mb-4">Email Update</legend>
			<label class="control-label col-sm-2" for="form-update-email-email">Email:</label>
			<div class="col-sm-10">
  				<input type="email" class="form-control" id="form-update-email-email" name="form-update-email-email"
  					   placeholder="Enter email" 
  					   pattern="${VALID_EMAIL}"
  					   required>
  				<div id="form-update-email-email-invalid-feedback" class="invalid-feedback">
        			${STANDARD_EMAIL_FEEDBACK}
      			</div>		   
  			</div>
		</fieldset>
		<div class="form-group col-sm-10">
			<p id="form-update-email-parameter-current-user-id" hidden>${currentUser.id}</p>
			<p id="form-update-email-parameter-current-user-email" hidden>${currentUser.email}</p>
			<button class="btn btn-outline-info" 
					type="submit">
				Update
			</button>
		</div>
	</form>
</div>