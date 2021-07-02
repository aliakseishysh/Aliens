<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content-section">
<form id="form-login">
	<fieldset class="form-group">
		<legend class="border-bottom mb-4">Log In</legend>
		<label class="control-label col-sm-2" for="${EMAIL}">Email:</label>
		<div class="col-sm-10">
  				<input type="email" class="form-control" id="form-login-email" name="form-login-email" placeholder="Enter email">
  			</div>
  			<label class="control-label col-sm-2" for="form-login-password">Password:</label>
  			<div class="col-sm-10">
  				<input type="password" class="form-control" id="form-login-password" name="form-login-password" placeholder="Enter password">
  			</div>
	</fieldset>
	<div class="form-group col-sm-10">
		<button class="btn btn-outline-info" type="button"
				onclick="loginUser(
						'${CONTROLLER}',
						'${COMMAND}',
						'${LOGIN_USER}',
						'${OPEN_HOME_PAGE}',
						'${EMAIL}',
						'${PASSWORD}'
					)">
			Login
		</button>
		<small class="text-muted">
			<a class="ml-2" href="#" onclick="restorePassword(
								'${CONTROLLER}',
								'${COMMAND}',
								'${FORGOT_PASSWORD}')">Forgot Password?
			</a>
		</small>
	</div>
</form>

<div class="border-top pt-3">
	<small class="text-muted"> 
		Need An Account? 
		<a class="ml-2" href="#" onclick="openRegisterPage(
							'${CONTROLLER}',
							'${COMMAND}',
							'${OPEN_REGISTER_PAGE}')">Sign Up Now
		</a>
	</small>
</div>
</div>
