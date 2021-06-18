<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content-section">
	<form>
		<fieldset class="form-group">
			<legend class="border-bottom mb-4">Password Update</legend>
   			<label class="control-label col-sm-2" for="password">Password:</label>
   			<div class="col-sm-10">
     				<input type="password" class="form-control" id="password" name="password" placeholder="Enter password">
   			</div>
   			<label class="control-label col-sm-2" for="password_confirm">Password confirm:</label>
   			<div class="col-sm-10">
     				<input type="password" class="form-control" id="password_confirm" name="password_confirm" placeholder="Enter password">
   			</div>
		</fieldset>
		<div class="form-group">
			<button class="btn btn-outline-info" type="submit">Update</button>
		</div>
	</form>
</div>