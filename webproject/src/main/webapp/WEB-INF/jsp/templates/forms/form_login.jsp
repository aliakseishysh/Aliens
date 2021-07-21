<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content-section">
<form id="form-login" class="needs-validation" novalidate>
	<fieldset class="form-group">
		<legend class="border-bottom mb-4">${TEXT[FORM_LOGIN_LEGEND]}</legend>
		<label class="control-label col-sm-10" for="form-login-email">${TEXT[FORM_LOGIN_EMAIL]}</label>
		<div class="col-sm-10">
  				<input type="email" class="form-control" id="form-login-email" name="form-login-email" 
  					   placeholder="${TEXT[FORM_LOGIN_PLACEHOLDER]}" 
  					   pattern="${VALID_EMAIL}"
  					   required>
      			<div id="form-login-email-invalid-feedback" class="invalid-feedback">
        			${STANDARD_EMAIL_FEEDBACK}
      			</div>
  			</div>
  			<label class="control-label col-sm-10" for="form-login-password">${TEXT[FORM_LOGIN_PASSWORD]}</label>
  			<div class="col-sm-10">
  				<input type="password" class="form-control" id="form-login-password" name="form-login-password" 
  					   placeholder="${TEXT[FORM_LOGIN_PASSWORD_PLACEHOLDER]}" 
  					   pattern="${VALID_PASSWORD}"
  					   required>
  				<div id="form-login-password-invalid-feedback" class="invalid-feedback">
        			${STANDARD_PASSWORD_FEEDBACK}
      			</div>
  			</div>
	</fieldset>
	<div class="form-group col-sm-10">
		<button class="btn btn-outline-info" type="submit">
			${TEXT[FORM_LOGIN_SUBMIT]}
		</button>
		<!--
			<small class="text-muted">
				TODO if have time
				<a class="ml-2" href="#" onclick="loginPage.restorePassword()">Forgot Password?</a>
			</small>
		-->
	</div>
</form>

<div class="border-top pt-3">
	<small class="text-muted"> 
		${TEXT[FORM_LOGIN_NEED_AN_ACCOUNT]}
		<a class="ml-2" href="#" onclick="loginPage.openRegisterPage()">${TEXT[FORM_LOGIN_SIGN_UP_NOW]}</a>
	</small>
</div>
</div>
