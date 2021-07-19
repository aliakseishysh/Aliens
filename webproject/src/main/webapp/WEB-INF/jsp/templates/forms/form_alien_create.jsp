<div class="content-section">
	<form id="form-alien-create" enctype="multipart/form-data" class="needs-validation" novalidate> 
		<fieldset class="form-group">
			<legend class="border-bottom mb-4">${TEXT[FORM_ALIEN_CREATE_LEGEND]}</legend>
   			<label class="control-label col-sm-2" for="form-alien-create-name">${TEXT[FORM_ALIEN_CREATE_NAME]}</label>
			<div class="col-sm-10">
     			<input type="text" class="form-control" id="form-alien-create-name" name="form-alien-create-name" 
     			placeholder="${TEXT[FORM_ALIEN_CREATE_NAME_PLACEHOLDER]}"
     			pattern="${VALID_ALIEN_NAME}"
  				required>
	   			<div id="form-alien-create-name-invalid-feedback" class="invalid-feedback">
        			${STANDARD_ALIEN_NAME_FEEDBACK}
      			</div>
   			</div>
   			<label class="control-label col-sm-2" for="form-alien-create-description-small">${TEXT[FORM_ALIEN_CREATE_SMALL_DESCRIPTION]}</label>
			<div class="col-sm-10">
     			<input type="text" class="form-control" id="form-alien-create-description-small" 
     			name="form-alien-create-description-small" placeholder="${TEXT[FORM_ALIEN_CREATE_SMALL_DESCRIPTION_PLACEHOLDER]}"
     			pattern="${VALID_ALIEN_SMALL_DESCRIPTION}"
  				required>
	   			<div id="form-alien-create-description-small-invalid-feedback" class="invalid-feedback">
        			${STANDARD_ALIEN_SMALL_DESCRIPTION_FEEDBACK}
      			</div>
   			</div>
   			<label class="control-label col-sm-2" for="form-alien-create-description-full">${TEXT[FORM_ALIEN_CREATE_FULL_DESCRIPTION]}</label>
			<div class="col-sm-10">
     			<textarea class="form-control" id="form-alien-create-description-full" name="form-alien-create-description-full" 
     					  placeholder="${TEXT[FORM_ALIEN_CREATE_FULL_DESCRIPTION_PLACEHOLDER]}"
     					  pattern="${VALID_ALIEN_FULL_DESCRIPTION}"
  						  required></textarea>
 				  <!-- textarea does not support the pattern attribute -->
     			<div id="form-alien-create-description-full-invalid-feedback" class="invalid-feedback">
        			${STANDARD_ALIEN_FULL_DESCRIPTION_FEEDBACK}
      			</div>
   			</div>
   			<label class="control-label col-sm-2" for="form-alien-create-image">${TEXT[FORM_ALIEN_CREATE_IMAGE]}</label>
   			<div class ="col-sm-10">
	   			<div class="custom-file">
	  				<input type="file" class="custom-file-input form-control-file" id="form-alien-create-image" required>
	  				<div id="form-alien-create-image-invalid-feedback" class="invalid-feedback">
	       				${STANDARD_IMAGE_FEEDBACK}
	   				</div>
	  				<label class="custom-file-label text-muted" for="form-alien-create-image" id="form-alien-create-image-label">${TEXT[FORM_ALIEN_CREATE_IMAGE_LABEL]}</label>
				</div>
   			</div>
		</fieldset>
		<div class ="col-sm-10">
			<div class="form-group">
				<button class="btn btn-outline-info" 
						type="submit">
					${TEXT[FORM_ALIEN_CREATE_SUBMIT]}
				</button>
			</div>
		</div>
	</form>
</div>








