<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content-section">
	<form id="form-register" class="needs-validation" novalidate>
		<fieldset class="form-group">
			<legend class="border-bottom mb-4">${TEXT[FORM_REGISTER_LEGEND]}</legend>
			<label class="control-label col-sm-10" for="form-register-email">${TEXT[FORM_REGISTER_EMAIL_LABEL]}</label>
			<div class="col-sm-10">
   				<input type="email" class="form-control" id="form-register-email" name="form-register-email" 
   					   placeholder="${TEXT[FORM_REGISTER_EMAIL_PLACEHOLDER]}" 
   					   pattern="${VALID_EMAIL}"
   					   required>
 				<div id="form-register-email-valid-feedback" class="valid-feedback">
      			</div>	
   				<div id="form-register-email-invalid-feedback" class="invalid-feedback">
        			${STANDARD_EMAIL_FEEDBACK}
      			</div>
   			</div>
   			<label class="control-label col-sm-10" for="form-register-login">${TEXT[FORM_REGISTER_LOGIN_LABEL]}</label>
			<div class="col-sm-10">
   				<input type="text" class="form-control" id="form-register-login" name="form-register-login" 
   					   placeholder="${TEXT[FORM_REGISTER_LOGIN_PLACEHOLDER]}" 
   					   pattern="${VALID_LOGIN}"
   					   required>
   				<div id="form-register-login-invalid-feedback" class="invalid-feedback">
        			${STANDARD_LOGIN_FEEDBACK}
      			</div>
   			</div>
   			<label class="control-label col-sm-10" for="form-register-password">${TEXT[FORM_REGISTER_PASSWORD_LABEL]}</label>
   			<div class="col-sm-10">
   				<input type="password" class="form-control" id="form-register-password" name="form-register-password" 
   					   placeholder="${TEXT[FORM_REGISTER_PASSWORD_PLACEHOLDER]}" 
   					   pattern="${VALID_PASSWORD}"
   					   required>
   				<div id="form-register-password-invalid-feedback" class="invalid-feedback">
        			${STANDARD_PASSWORD_FEEDBACK}
      			</div>
   			</div>
   			<label class="control-label col-sm-10" for="form-register-password-confirm">${TEXT[FORM_REGISTER_PASSWORD_CONFIRMATION_LABEL]}</label>
   			<div class="col-sm-10">
   				<input type="password" class="form-control" id="form-register-password-confirm" name="form-register-password-confirm" 
   				       placeholder="${TEXT[FORM_REGISTER_PASSWORD_CONFIRMATION_PLACEHOLDER]}" 
   				       pattern="${VALID_PASSWORD}"
   				       required>
   				<div id="form-register-password-confirm-invalid-feedback" class="invalid-feedback">
        			${STANDARD_PASSWORD_CONFIRMATION_FEEDBACK}
      			</div>
   			</div>
		</fieldset>
		<div class ="col-sm-10">
			<div class="form-group">
				<button class="btn btn-outline-info" 
						type="submit">
					${TEXT[FORM_REGISTER_PASSWORD_SUBMIT]}
				</button>
			</div>
		</div>
	</form>
	<div class="border-top pt-3">
		<small class="text-muted"> 
			${TEXT[FORM_REGISTER_ALREADY_HAVE_AN_ACCOUNT]}
			<a class="ml-2" href="#" 
			onclick="openLoginPage()">
			${TEXT[FORM_REGISTER_SIGN_IN]}</a>
		</small>
	</div>
</div>
