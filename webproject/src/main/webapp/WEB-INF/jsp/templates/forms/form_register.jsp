<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js'></script>
<div class="content-section">
	<form id="form-register">
		<fieldset class="form-group">
			<legend class="border-bottom mb-4">Sign Up</legend>
			<label class="control-label col-sm-2" for="form-register-email">Email:</label>
			<div class="col-sm-10">
   				<input type="email" class="form-control" id="form-register-email" name="form-register-email" placeholder="Enter email">
   			</div>
   			<label class="control-label col-sm-2" for="form-register-login">Login:</label>
			<div class="col-sm-10">
   				<input type="text" class="form-control" id="form-register-login" name="form-register-login" placeholder="Enter login">
   			</div>
   			<label class="control-label col-sm-2" for="form-register-password">Password:</label>
   			<div class="col-sm-10">
   				<input type="password" class="form-control" id="form-register-password" name="form-register-password" placeholder="Enter password">
   			</div>
   			<label class="control-label col-sm-2" for="form-register-password-confirm">Password confirm:</label>
   			<div class="col-sm-10">
   				<input type="password" class="form-control" id="form-register-password-confirm" name="form-register-password-confirm" placeholder="Enter password">
   			</div>
		</fieldset>
		<div class="form-group">
			<button class="btn btn-outline-info" type="button"
					onclick="registerUser(
							'${CONTROLLER}',
							'${COMMAND}',
							'${REGISTER_USER}',
							'${OPEN_LOGIN_PAGE}',
							'${EMAIL}',
							'${LOGIN}',
							'${PASSWORD}',
							'${PASSWORD_CONFIRM}'
						)">Register
			</button>
		</div>
	</form>
	<div class="border-top pt-3">
		<small class="text-muted"> 
		Already Have An Account? 
			<a class="ml-2" href="#" 
			onclick="openLoginPage(
					'${CONTROLLER}',
					'${COMMAND}',
					'${OPEN_LOGIN_PAGE}'
				)">
			Sign In</a>
		</small>
	</div>
</div>
