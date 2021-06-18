<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content-section">
	<form enctype="multipart/form-data">
		<fieldset class="form-group">
			<legend class="border-bottom mb-4">Image Update</legend>
   			<label class="control-label col-sm-2" for="image">Image:</label>
			<div class="col-sm-10">
     			<input type="file" class="form-control" id="image" name="image" placeholder="Enter image">
   			</div>
		</fieldset>
		<div class="form-group">
			<button class="btn btn-outline-info" type="submit">Update</button>
		</div>
	</form>
</div>