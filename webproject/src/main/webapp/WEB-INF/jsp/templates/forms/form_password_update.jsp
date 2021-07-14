<div class="content-section">
	<form id="form-update-password" class="needs-validation" novalidate>
		<fieldset class="form-group">
			<legend class="border-bottom mb-4">${TEXT[FORM_PASSWORD_UPDATE_LEGEND]}</legend>
   			<label class="control-label col-sm-2" for="form-update-password-password">${TEXT[FORM_PASSWORD_UPDATE_PASSWORD_LABEL]}</label>
   			<div class="col-sm-10">
    			<input type="password" class="form-control" id="form-update-password-password" 
    				   name="form-update-password-password" placeholder="${TEXT[FORM_PASSWORD_UPDATE_PASSWORD_PLACEHOLDER]}"
    				   pattern="${VALID_PASSWORD}"
    				   required>
			   <div id="form-update-password-password-invalid-feedback" class="invalid-feedback">
        			${STANDARD_PASSWORD_FEEDBACK}
      			</div>
   			</div>
   			<label class="control-label col-sm-2" for="form-update-password-password-confirmation">${TEXT[FORM_PASSWORD_UPDATE_PASSWORD_CONFIRMATION_LABEL]}</label>
   			<div class="col-sm-10">
     			<input type="password" class="form-control" id="form-update-password-password-confirmation" 
     				   name="form-update-password-password-confirmation" placeholder="${TEXT[FORM_PASSWORD_UPDATE_PASSWORD_CONFIRMATION_PLACEHOLDER]}"
     				   pattern="${VALID_PASSWORD}"
    				   required>
		   		<div id="form-update-password-password-confirmation-invalid-feedback" class="invalid-feedback">
        			${STANDARD_PASSWORD_CONFIRMATION_FEEDBACK}
      			</div>
   			</div>
		</fieldset>
		<div class="form-group col-sm-10">
			<p id="form-update-password-parameter-current-user-id" hidden>${currentUser.id}</p>
			<button class="btn btn-outline-info" 
					type="submit">
				${TEXT[FORM_PASSWORD_UPDATE_SUBMIT]}
			</button>
		</div>
	</form>
</div>