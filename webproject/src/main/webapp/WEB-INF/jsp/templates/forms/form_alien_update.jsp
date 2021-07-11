<div class="content-section">
	<form id="form-alien-update" enctype="multipart/form-data" class="needs-validation" novalidate>
		<fieldset class="form-group">
			<legend class="border-bottom mb-4">${TEXT[FORM_ALIEN_UPDATE_LEGEND]}</legend>
   			<label class="control-label col-sm-2" for="form-alien-update-name">${TEXT[FORM_ALIEN_UPDATE_NAME]}</label>
			<div class="col-sm-10">
     			<input type="text" class="form-control" id="form-alien-update-name" name="form-alien-update-name" 
     			placeholder="${TEXT[FORM_ALIEN_UPDATE_NAME_PLACEHOLDER]}"
     			pattern="${VALID_ALIEN_NAME}"
  				required>
  				<div id="form-alien-update-name-invalid-feedback" class="invalid-feedback">
        			${ADD_NEW_UPDATE_ALIEN_STANDARD_ALIEN_NAME_FEEDBACK}
      			</div>
   			</div>
   			<label class="control-label col-sm-2" for="form-alien-update-description-small">${TEXT[FORM_ALIEN_UPDATE_SMALL_DESCRIPTION]}</label>
			<div class="col-sm-10">
     			<input type="text" class="form-control" id="form-alien-update-description-small" 
     			name="form-alien-update-description-small" placeholder="${TEXT[FORM_ALIEN_UPDATE_SMALL_DESCRIPTION_PLACEHOLDER]}"
     			pattern="${VALID_ALIEN_SMALL_DESCRIPTION}"
  				required>
	   			<div id="form-alien-update-description-small-invalid-feedback" class="invalid-feedback">
        			${ADD_NEW_UPDATE_ALIEN_STANDARD_ALIEN_SMALL_DESCRIPTION_FEEDBACK}
      			</div>
   			</div>
   			<label class="control-label col-sm-2" for="form-alien-update-description-full">${TEXT[FORM_ALIEN_UPDATE_FULL_DESCRIPTION]}</label>
			<div class="col-sm-10">
     			<textarea class="form-control" id="form-alien-update-description-full" 
     			name="form-alien-update-description-full" 
     			placeholder="${TEXT[FORM_ALIEN_UPDATE_FULL_DESCRIPTION_PLACEHOLDER]}"
     			pattern="${VALID_ALIEN_FULL_DESCRIPTION}"
  				required></textarea>
 				  <!-- textarea does not support the pattern attribute -->
     			<div id="form-alien-update-description-full-invalid-feedback" class="invalid-feedback">
        			${ADD_NEW_UPDATE_ALIEN_STANDARD_ALIEN_FULL_DESCRIPTION_FEEDBACK}
      			</div>
   			</div>
   			<label class="control-label col-sm-2" for="form-alien-update-image">${TEXT[FORM_ALIEN_UPDATE_IMAGE]}</label>
   			<div class ="col-sm-10">
	   			<div class="custom-file">
	  				<input type="file" class="custom-file-input form-control-file" id="form-alien-update-image">
	  				<div id="form-alien-update-image-invalid-feedback" class="invalid-feedback">
	       				${ADD_NEW_UPDATE_ALIEN_STANDARD_ALIEN_IMAGE_FEEDBACK}
	   				</div>
	  				<label class="custom-file-label" for="form-alien-update-image" id="form-alien-update-image-label">${TEXT[FORM_ALIEN_UPDATE_IMAGE_LABEL]}</label>
				</div>
   			</div>
		</fieldset>
		<div class ="col-sm-10">
			<div class="form-group">
				<p id="form-alien-update-alien-id-parameter-current-alien-id" hidden>${alien.id}</p>
				<button class="btn btn-outline-info" 
						type="submit">
					${TEXT[FORM_ALIEN_UPDATE_SUBMIT]}
				</button>
			</div>
		</div>
	</form>
</div>