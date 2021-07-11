<div class="content-section">
	<form id="form-update-image" enctype="multipart/form-data"  class="needs-validation" novalidate>
		<fieldset class="form-group">
			<legend class="border-bottom mb-4">${TEXT[FORM_IMAGE_UPDATE_LEGEND]}</legend>
   			<label class="control-label col-sm-2" for="form-update-image-image">${TEXT[FORM_IMAGE_UPDATE_IMAGE]}</label>
   			<div class ="col-sm-10">
	   			<div class="custom-file">
	  				<input type="file" class="custom-file-input form-control-file" id="form-update-image-image" required>
					<div id="form-update-image-image-invalid-feedback" class="invalid-feedback">
	       				${UPDATE_IMAGE_STANDARD_IMAGE_FEEDBACK}
	   				</div>
	  				<label id="form-update-image-image-label" class="custom-file-label text-muted" for="form-update-image-image">${TEXT[FORM_IMAGE_UPDATE_IMAGE_LABEL]}</label>
				</div>
   			</div>
		</fieldset>
		<div class="form-group col-sm-10">
			<p id="form-update-image-parameter-current-user-id" hidden>${currentUser.id}</p>
			<!-- TODO change this with something else (error ) -->
			<button class="btn btn-outline-info" 
					type="submit">
				${TEXT[FORM_IMAGE_UPDATE_SUBMIT]}
			</button>
		</div>
	</form>
</div>
