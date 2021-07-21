<div class="content-section">
	<form id="form-alien-suggest" enctype="multipart/form-data" class="needs-validation" novalidate> 
		<fieldset class="form-group">
			<legend class="border-bottom mb-4">${TEXT[FORM_ALIEN_SUGGEST_LEGEND]}</legend>
   			<label class="control-label col-sm-10" for="form-alien-suggest-name">${TEXT[FORM_ALIEN_CREATE_NAME]}</label>
			<div class="col-sm-10">
     			<input type="text" class="form-control" id="form-alien-suggest-name" name="form-alien-suggest-name" 
     			placeholder="${TEXT[FORM_ALIEN_CREATE_NAME_PLACEHOLDER]}"
     			pattern="${VALID_ALIEN_NAME}"
  				required>
	   			<div id="form-alien-suggest-name-invalid-feedback" class="invalid-feedback">
        			${STANDARD_ALIEN_NAME_FEEDBACK}
      			</div>
   			</div>
   			<label class="control-label col-sm-10" for="form-alien-suggest-description-small">${TEXT[FORM_ALIEN_CREATE_SMALL_DESCRIPTION]}</label>
			<div class="col-sm-10">
     			<input type="text" class="form-control" id="form-alien-suggest-description-small" 
     			name="form-alien-suggest-description-small" placeholder="${TEXT[FORM_ALIEN_CREATE_SMALL_DESCRIPTION_PLACEHOLDER]}"
     			pattern="${VALID_ALIEN_SMALL_DESCRIPTION}"
  				required>
	   			<div id="form-alien-suggest-description-small-invalid-feedback" class="invalid-feedback">
        			${STANDARD_ALIEN_SMALL_DESCRIPTION_FEEDBACK}
      			</div>
   			</div>
   			<label class="control-label col-sm-10" for="form-alien-suggest-description-full">${TEXT[FORM_ALIEN_CREATE_FULL_DESCRIPTION]}</label>
			<div class="col-sm-10">
     			<textarea class="form-control" id="form-alien-suggest-description-full" name="form-alien-suggest-description-full" 
     					  placeholder="${TEXT[FORM_ALIEN_CREATE_FULL_DESCRIPTION_PLACEHOLDER]}"
     					  pattern="${VALID_ALIEN_FULL_DESCRIPTION}"
  						  required></textarea>
 				  <!-- textarea does not support the pattern attribute -->
     			<div id="form-alien-suggest-description-full-invalid-feedback" class="invalid-feedback">
        			${STANDARD_ALIEN_FULL_DESCRIPTION_FEEDBACK}
      			</div>
   			</div>
   			<label class="control-label col-sm-10" for="form-alien-suggest-image">${TEXT[FORM_ALIEN_CREATE_IMAGE]}</label>
   			<div class ="col-sm-10">
	   			<div class="custom-file">
	  				<input type="file" class="custom-file-input form-control-file" id="form-alien-suggest-image" lang="${CURRENT_LOCALE_ABBREVIATION}" required>
	  				<div id="form-alien-suggest-image-invalid-feedback" class="invalid-feedback">
	       				${STANDARD_IMAGE_FEEDBACK}
	   				</div>
	  				<label class="custom-file-label text-muted" for="form-alien-suggest-image" id="form-alien-suggest-image-label">${TEXT[FORM_ALIEN_CREATE_IMAGE_LABEL]}</label>
				</div>
   			</div>
		</fieldset>
		<div class ="col-sm-10">
			<div class="form-group">
				<button class="btn btn-outline-info" 
						type="submit">
					${TEXT[PAGE_SUGGEST_ALIEN_SUBMIT]}
				</button>
			</div>
		</div>
	</form>
</div>








