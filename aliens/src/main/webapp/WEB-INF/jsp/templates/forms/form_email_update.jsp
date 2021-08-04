<div class="content-section">
	<form id="form-update-email" class="needs-validation" novalidate>
		<fieldset class="form-group">
			<legend class="border-bottom mb-4">${TEXT[FORM_EMAIL_UPDATE_LEGEND]}</legend>
			<label class="control-label col-sm-10" for="form-update-email-email">${TEXT[FORM_EMAIL_UPDATE_EMAIL]}</label>
			<div class="col-sm-10">
  				<input type="email" class="form-control" id="form-update-email-email" name="form-update-email-email"
  					   placeholder="${TEXT[FORM_EMAIL_UPDATE_EMAIL_PLACEHOLDER]}" 
  					   pattern="${VALID_EMAIL}"
  					   required>
  				<div id="form-update-email-email-valid-feedback" class="valid-feedback">
      			</div>	
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
				${TEXT[FORM_EMAIL_UPDATE_SUBMIT]}
			</button>
		</div>
	</form>
</div>