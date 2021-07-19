<div class="content-section">
	<form id="form-alien-suggest-suggest-image" enctype="multipart/form-data"  class="needs-validation" novalidate>
		<fieldset class="form-group">
			<legend class="border-bottom mb-4">${TEXT[FORM_ALIEN_SUGGEST_IMAGE_LEGEND]}</legend>
			<label class="control-label col-sm-2" for="form-alien-suggest-suggest-image-name">${TEXT[FORM_ALIEN_CREATE_NAME]}</label>
			<div class="col-sm-10">
     			<input type="text" class="form-control" id="form-alien-suggest-suggest-image-name" name="form-alien-suggest-suggest-image-name" 
     			placeholder="${TEXT[FORM_ALIEN_CREATE_NAME_PLACEHOLDER]}"
     			pattern="${VALID_ALIEN_NAME}"
  				required>
	   			<div id="form-alien-suggest-suggest-image-name-invalid-feedback" class="invalid-feedback">
        			${STANDARD_ALIEN_NAME_FEEDBACK}
      			</div>
   			</div>
   			<label class="control-label col-sm-2" for="form-alien-suggest-suggest-image-image">${TEXT[FORM_IMAGE_UPDATE_IMAGE]}</label>
   			<div class ="col-sm-10">
	   			<div class="custom-file">
	  				<input type="file" class="custom-file-input form-control-file" id="form-alien-suggest-suggest-image-image" required>
					<div id="form-alien-suggest-suggest-image-image-invalid-feedback" class="invalid-feedback">
	       				${STANDARD_IMAGE_FEEDBACK}
	   				</div>
	  				<label id="form-alien-suggest-suggest-image-image-label" class="custom-file-label text-muted" for="form-alien-suggest-suggest-image-image">${TEXT[FORM_IMAGE_UPDATE_IMAGE_LABEL]}</label>
				</div>
   			</div>
		</fieldset>
		<div class="form-group col-sm-10">
			<button class="btn btn-outline-info" 
					type="submit">
					${TEXT[PAGE_SUGGEST_ALIEN_SUBMIT]}
			</button>
		</div>
	</form>
</div>
