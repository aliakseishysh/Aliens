<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content-section">
<form id="form-login" class="needs-validation" novalidate>
	<fieldset class="form-group">
		<legend class="border-bottom mb-4">Log In</legend>
		<label class="control-label col-sm-2" for="form-login-email">Email:</label>
		<div class="col-sm-10">
  				<input type="email" class="form-control" id="form-login-email" name="form-login-email" 
  					   placeholder="Enter email" 
  					   pattern="${VALID_EMAIL}"
  					   required>
      			<div id="form-login-email-invalid-feedback" class="invalid-feedback">
        			${STANDARD_EMAIL_FEEDBACK}
      			</div>
  			</div>
  			<label class="control-label col-sm-2" for="form-login-password">Password:</label>
  			<div class="col-sm-10">
  				<input type="password" class="form-control" id="form-login-password" name="form-login-password" 
  					   placeholder="Enter password" 
  					   pattern="${VALID_PASSWORD}"
  					   required>
  				<div id="form-login-password-invalid-feedback" class="invalid-feedback">
        			${STANDARD_PASSWORD_FEEDBACK}
      			</div>
  			</div>
	</fieldset>
	<div class="form-group col-sm-10">
		<button class="btn btn-outline-info" type="submit">
			Login
		</button>
		<small class="text-muted">
			<a class="ml-2" href="#" onclick="loginPage.restorePassword()">Forgot Password?</a>
		</small>
	</div>
</form>

<div class="border-top pt-3">
	<small class="text-muted"> 
		Need An Account? 
		<a class="ml-2" href="#" onclick="loginPage.openRegisterPage()">Sign Up Now</a>
	</small>
</div>
</div>
