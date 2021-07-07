<div class="content-section">
	<form id="form-user-ban-unban" class="needs-validation" novalidate>
		<fieldset class="form-group">
			<legend class="border-bottom mb-4">Bans</legend>
			<label class="control-label col-sm-2" for="form-user-ban-unban-login">User Login:</label>
			<div class="col-sm-10">
  				<input type="text" class="form-control" id="form-user-ban-unban-login" name="form-user-ban-unban-login" 
  				placeholder="Enter user login"
  				pattern="${VALID_LOGIN}"
  				required>
  				<div id="form-user-ban-unban-login-invalid-feedback" class="invalid-feedback">
        			${BAN_UNBAN_USER_STANDARD_LOGIN_FEEDBACK}
      			</div>
  			</div>
  			<label class="control-label col-sm-2" for="form-user-ban-unban-days-in-ban">Days in ban:</label>
			<div class="col-sm-10">
  				<input type="text" class="form-control" id="form-user-ban-unban-days-in-ban" name="form-user-ban-unban-days-in-ban" 
  				placeholder="Enter days in ban" 
  				pattern="${VALID_DIGIT}">
  				<!-- required when banning user -->
  				<!-- integer greater then 0 (check in js)-->
  				<div id="form-user-ban-unban-days-in-ban-invalid-feedback" class="invalid-feedback">
        			${BAN_UNBAN_USER_STANDARD_DAYS_TO_BAN_FEEDBACK}
      			</div>
  			</div>
		</fieldset>
		<div class="form-group col-sm-10">
			<button class="btn btn-outline-info" 
					type="submit"
					id="form-user-ban-unban-button-banuser">
				Ban
			</button>
			<button class="btn btn-outline-info" 
					type="submit" 
					id="form-user-ban-unban-button-unbanuser">
				Unban
			</button>
		</div>
	</form>
</div>