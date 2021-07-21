<!-- TODO if have time -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content-section">
<form id="form-restore-password">
	<fieldset class="form-group">
		<legend class="border-bottom mb-4">Request link to restore password</legend>
		<label class="control-label col-sm-10" for="form-restore-password-email">Email:</label>
		<div class="col-sm-10">
			<input type="email" class="form-control" id="form-restore-password-email" name="form-restore-password-email" placeholder="Enter email">
		</div>
	</fieldset>
	<div class="form-group col-sm-10">
		<button class="btn btn-outline-info" type="button"
				onclick="restorePassword()">
			Restore
		</button>
	</div>
</form>
</div>
