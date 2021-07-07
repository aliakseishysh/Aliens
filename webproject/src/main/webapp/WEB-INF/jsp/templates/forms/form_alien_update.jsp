<div class="content-section">
	<form id="alien-update-form" enctype="multipart/form-data">
		<fieldset class="form-group">
			<legend class="border-bottom mb-4">Alien Update</legend>
   			<label class="control-label col-sm-2" for="alien-update-form-name">Name:</label>
			<div class="col-sm-10">
     			<input type="text" class="form-control" id="alien-update-form-name" name="alien-update-form-name" placeholder="Enter Alien Name">
   			</div>
   			<label class="control-label col-sm-2" for="alien-update-form-description-small">Small Description:</label>
			<div class="col-sm-10">
     			<input type="text" class="form-control" id="alien-update-form-description-small" name="alien-update-form-description-small" placeholder="Enter Small Description">
   			</div>
   			<label class="control-label col-sm-2" for="alien-update-form-description-full">Full Description:</label>
			<div class="col-sm-10">
     			<textarea class="form-control" id="alien-update-form-description-full" name="alien-update-form-description-full" placeholder="Enter Full Description"></textarea>
   			</div>
   			<label class="control-label col-sm-2" for="alien-update-form-image">Image:</label>
   			<div class ="col-sm-10">
	   			<div class="custom-file">
	  				<input type="file" class="custom-file-input form-control-file" id="alien-update-form-image">
	  				<label class="custom-file-label" for="alien-update-form-image">Select image</label>
				</div>
   			</div>
		</fieldset>
		<div class ="col-sm-10">
			<div class="form-group">
				<button class="btn btn-outline-info" 
						type="button" 
						onclick="updateAlien(`${alien.id}`)">
					Update
				</button>
			</div>
		</div>
	</form>
</div>