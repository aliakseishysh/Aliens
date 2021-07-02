<div class="content-section">
	<form id="image-update-form" enctype="multipart/form-data">
		<fieldset class="form-group">
			<legend class="border-bottom mb-4">Image Update</legend>
   			<label class="control-label col-sm-2" for="image-update-form-image">Image:</label>
   			<div class ="col-sm-10">
	   			<div class="custom-file">
	  				<input type="file" class="custom-file-input form-control-file" id="image-update-form-image">
	  				<label class="custom-file-label" for="image-update-form-image">Select image</label>
				</div>
   			</div>
		</fieldset>
		<div class="form-group col-sm-10">
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
						`${currentUser.id}`)">
				Update
			</button>
		</div>
	</form>
</div>