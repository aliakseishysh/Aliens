<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content-section">
	<form id="form-register" class="needs-validation" novalidate>
		<fieldset class="form-group">
			<legend class="border-bottom mb-4">Sign Up</legend>
			<label class="control-label col-sm-2" for="form-register-email">Email:</label>
			<div class="col-sm-10">
   				<input type="email" class="form-control" id="form-register-email" name="form-register-email" 
   					   placeholder="Enter email" 
   					   pattern="${VALID_EMAIL}"
   					   required>
   				<div id="form-register-email-invalid-feedback" class="invalid-feedback">
        			${REGISTER_STANDARD_EMAIL_FEEDBACK}
      			</div>
   			</div>
   			<label class="control-label col-sm-2" for="form-register-login">Login:</label>
			<div class="col-sm-10">
   				<input type="text" class="form-control" id="form-register-login" name="form-register-login" 
   					   placeholder="Enter login" 
   					   pattern="${VALID_LOGIN}"
   					   required>
   				<div id="form-register-login-invalid-feedback" class="invalid-feedback">
        			${REGISTER_STANDARD_LOGIN_FEEDBACK}
      			</div>
   			</div>
   			<label class="control-label col-sm-2" for="form-register-password">Password:</label>
   			<div class="col-sm-10">
   				<input type="password" class="form-control" id="form-register-password" name="form-register-password" 
   					   placeholder="Enter password" 
   					   pattern="${VALID_PASSWORD}"
   					   required>
   				<div id="form-register-password-invalid-feedback" class="invalid-feedback">
        			${REGISTER_STANDARD_PASSWORD_FEEDBACK}
      			</div>
   			</div>
   			<label class="control-label col-sm-2" for="form-register-password-confirm">Password confirm:</label>
   			<div class="col-sm-10">
   				<input type="password" class="form-control" id="form-register-password-confirm" name="form-register-password-confirm" 
   				       placeholder="Enter password" 
   				       pattern="${VALID_PASSWORD}"
   				       required>
   				<div id="form-register-password-confirm-invalid-feedback" class="invalid-feedback">
        			${REGISTER_STANDARD_PASSWORD_CONFIRM_FEEDBACK}
      			</div>
   			</div>
		</fieldset>
		<div class="form-group">
			<button class="btn btn-outline-info" type="submit">
				Register
			</button>
		</div>
	</form>
	<div class="border-top pt-3">
		<small class="text-muted"> 
		Already Have An Account? 
			<a class="ml-2" href="#" 
			onclick="openLoginPage()">
			Sign In</a>
		</small>
	</div>
</div>
