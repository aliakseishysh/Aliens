<div class="content-section">
	<form id="form-alien-update-info" enctype="multipart/form-data" class="needs-validation" novalidate>
		<fieldset class="form-group">
			<legend class="border-bottom mb-4">${TEXT[FORM_ALIEN_UPDATE_LEGEND]}</legend>
   			<label class="control-label col-sm-10" for="form-alien-update-info-name">${TEXT[FORM_ALIEN_UPDATE_NAME]}</label>
			<div class="col-sm-10">
     			<input type="text" class="form-control" id="form-alien-update-info-name" name="form-alien-update-info-name" 
     			placeholder="${TEXT[FORM_ALIEN_UPDATE_NAME_PLACEHOLDER]}"
     			pattern="${VALID_ALIEN_NAME}"
  				required>
  				<div id="form-alien-update-info-name-invalid-feedback" class="invalid-feedback">
        			${STANDARD_ALIEN_NAME_FEEDBACK}
      			</div>
   			</div>
   			<label class="control-label col-sm-10" for="form-alien-update-info-description-small">${TEXT[FORM_ALIEN_UPDATE_SMALL_DESCRIPTION]}</label>
			<div class="col-sm-10">
     			<input type="text" class="form-control" id="form-alien-update-info-description-small" 
     			name="form-alien-update-info-description-small" placeholder="${TEXT[FORM_ALIEN_UPDATE_SMALL_DESCRIPTION_PLACEHOLDER]}"
     			pattern="${VALID_ALIEN_SMALL_DESCRIPTION}"
  				required>
	   			<div id="form-alien-update-info-description-small-invalid-feedback" class="invalid-feedback">
        			${STANDARD_ALIEN_SMALL_DESCRIPTION_FEEDBACK}
      			</div>
   			</div>
   			<label class="control-label col-sm-10" for="form-alien-update-info-description-full">${TEXT[FORM_ALIEN_UPDATE_FULL_DESCRIPTION]}</label>
			<div class="col-sm-10">
     			<textarea class="form-control" id="form-alien-update-info-description-full" 
     			name="form-alien-update-info-description-full" 
     			placeholder="${TEXT[FORM_ALIEN_UPDATE_FULL_DESCRIPTION_PLACEHOLDER]}"
     			pattern="${VALID_ALIEN_FULL_DESCRIPTION}"
  				required></textarea>
 				  <!-- textarea does not support the pattern attribute -->
     			<div id="form-alien-update-info-description-full-invalid-feedback" class="invalid-feedback">
        			${STANDARD_ALIEN_FULL_DESCRIPTION_FEEDBACK}
      			</div>
   			</div>
		</fieldset>
		<div class ="col-sm-10">
			<div class="form-group">
				<p id="form-alien-update-info-alien-id-parameter-current-alien-id" hidden>${alien.id}</p>
				<button class="btn btn-outline-info" 
						type="submit">
					${TEXT[FORM_ALIEN_UPDATE_SUBMIT]}
				</button>
			</div>
		</div>
	</form>
</div>