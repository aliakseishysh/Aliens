<div class="content-section">
	<form id="form-user-ban-unban" class="needs-validation" novalidate>
		<fieldset class="form-group">
			<legend class="border-bottom mb-4">${TEXT[FORM_USER_BAN_UNBAN_LEGEND]}</legend>
			<label class="control-label col-sm-2" for="form-user-ban-unban-login">${TEXT[FORM_USER_BAN_UNBAN_LOGIN]}</label>
			<div class="col-sm-10">
  				<input type="text" class="form-control" id="form-user-ban-unban-login" name="form-user-ban-unban-login" 
  				placeholder="${TEXT[FORM_USER_BAN_UNBAN_LOGIN_PLACEHOLDER]}"
  				pattern="${VALID_LOGIN}"
  				required>
  				<div id="form-user-ban-unban-login-invalid-feedback" class="invalid-feedback">
        			${STANDARD_LOGIN_FEEDBACK}
      			</div>
  			</div>
  			<label class="control-label col-sm-2" for="form-user-ban-unban-days-in-ban">${TEXT[FORM_USER_BAN_UNBAN_DAYS_IN_BAN]}</label>
			<div class="col-sm-10">
  				<input type="text" class="form-control" id="form-user-ban-unban-days-in-ban" name="form-user-ban-unban-days-in-ban" 
  				placeholder="${TEXT[FORM_USER_BAN_UNBAN_DAYS_IN_BAN_PLACEHOLDER]}" 
  				pattern="${VALID_DIGIT}">
  				<!-- required when banning user -->
  				<!-- integer greater then 0 (check in js)-->
  				<div id="form-user-ban-unban-days-in-ban-invalid-feedback" class="invalid-feedback">
        			${STANDARD_DAYS_TO_BAN_FEEDBACK}
      			</div>
  			</div>
		</fieldset>
		<div class="form-group col-sm-10">
			<button class="btn btn-outline-info" 
					type="submit"
					id="form-user-ban-unban-button-banuser">
				${TEXT[FORM_USER_BAN_UNBAN_SUBMIT_BAN]}
			</button>
			<button class="btn btn-outline-info" 
					type="submit" 
					id="form-user-ban-unban-button-unbanuser">
				${TEXT[FORM_USER_BAN_UNBAN_SUBMIT_UNBAN]}
			</button>
		</div>
	</form>
</div>