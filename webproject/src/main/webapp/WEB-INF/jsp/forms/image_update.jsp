<div class="content-section">
	<form id="image-update-form" enctype="multipart/form-data">
		<fieldset class="form-group">
			<legend class="border-bottom mb-4">Image Update</legend>
   			<label class="control-label col-sm-2" for="image">Image:</label>
			<div class="col-sm-10">
     			<input type="file" class="form-control" id="image" name="image" placeholder="Enter image">
   			</div>
		</fieldset>
		<div class="form-group">
			<button class="btn btn-outline-info" 
					type="button" 
					onclick="updateUserImage(
						`${PROJECT_NAME}`,
						`${CONTROLLER}`,
						`${COMMAND}`,
						`${UPDATE_USER_IMAGE}`,
						`${NEW_IMAGE}`,
						`${USER_ID}`,
						`${LOAD_IMAGE_UPDATE_FORM}`,
						`${LOAD_USER_IMAGE}`,
						`${WEB_SITE_NAME}`,
						`${currentUser.getId()}`)">
				Update
			</button>
		</div>
	</form>
</div>