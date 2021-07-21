<div class="content-section">
	<form id="form-update-login" class="needs-validation" novalidate>
		<fieldset class="form-group">
			<legend class="border-bottom mb-4">${TEXT[FORM_LOGIN_UPDATE_LEGEND]}</legend>
			<label class="control-label col-sm-10" for="form-update-login-login">${TEXT[FORM_LOGIN_UPDATE_LOGIN]}</label>
			<div class="col-sm-10">
  				<input type="text" class="form-control" id="form-update-login-login" name="form-update-login-login" 
  					   placeholder="${TEXT[FORM_LOGIN_UPDATE_LOGIN_PLACEHOLDER]}"
  					   pattern="${VALID_LOGIN}"
  					   required>
  				<div id="form-update-login-login-invalid-feedback" class="invalid-feedback">
        			${STANDARD_LOGIN_FEEDBACK}
      			</div>
	  		</div>
		</fieldset>
		<div class="form-group col-sm-10">
			<p id="form-update-login-parameter-current-user-id" hidden>${currentUser.id}</p>
			<p id="form-update-login-parameter-current-user-login" hidden>${currentUser.login}</p>
			<button class="btn btn-outline-info" 
					type="submit">
				${TEXT[FORM_LOGIN_UPDATE_SUBMIT]}
			</button>
		</div>
	</form>
</div>