<div class="content-section">
	<form id="alien-create-form" enctype="multipart/form-data">
		<fieldset class="form-group">
			<legend class="border-bottom mb-4">Alien Add</legend>
   			<label class="control-label col-sm-2" for="alien-create-form-name">Name:</label>
			<div class="col-sm-10">
     			<input type="text" class="form-control" id="alien-create-form-name" name="alien-create-form-name" placeholder="Enter Alien Name">
   			</div>
   			<label class="control-label col-sm-2" for="alien-create-form-description-small">Small Description:</label>
			<div class="col-sm-10">
     			<input type="text" class="form-control" id="alien-create-form-description-small" name="alien-create-form-description-small" placeholder="Enter Small Description">
   			</div>
   			<label class="control-label col-sm-2" for="alien-create-form-description-full">Full Description:</label>
			<div class="col-sm-10">
     			<textarea class="form-control" id="alien-create-form-description-full" name="alien-create-form-description-full" placeholder="Enter Full Description"></textarea>
   			</div>
   			<label class="control-label col-sm-2" for="alien-create-form-image">Image:</label>
   			<div class ="col-sm-10">
	   			<div class="custom-file">
	  				<input type="file" class="custom-file-input form-control-file" id="alien-create-form-image">
	  				<label class="custom-file-label" for="alien-create-form-image">Select image</label>
				</div>
   			</div>
		</fieldset>
		<div class ="col-sm-10">
			<div class="form-group">
				<button class="btn btn-outline-info" 
						type="button" 
						onclick="addNewAlien(
							`${PROJECT_NAME}`,
							`${CONTROLLER}`,
							`${COMMAND}`,
							`${ADD_NEW_ALIEN}`,
							`${ALIEN_NAME}`,
							`${ALIEN_SMALL_DESCRIPTION}`,
							`${ALIEN_FULL_DESCRIPTION}`,
							`${ALIEN_NEW_IMAGE}`,
							`${LOAD_ALIEN_CREATE_FORM}`)">
					Update
				</button>
			</div>
		</div>
	</form>
</div>