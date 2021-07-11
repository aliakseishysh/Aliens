<div class="content-section">
	<form id="form-new-comment" class="needs-validation" novalidate>
		<fieldset class="form-group">
			<legend class="border-bottom mb-4">${TEXT[FORM_NEW_COMMENT_LEGEND]}</legend>
   			<label class="control-label col-sm-2" for="form-new-comment-comment">${TEXT[FORM_NEW_COMMENT_LABEL]}</label>
			<div class="col-sm-10">
     			<textarea class="form-control" id="form-new-comment-comment" name="form-new-comment-comment" 
     					  placeholder="${TEXT[FORM_NEW_COMMENT_PLACEHOLDER]}"
     					  pattern="${VALID_COMMENT}"
     					  required></textarea>
     			<div id="form-new-comment-comment-invalid-feedback" class="invalid-feedback">
        			${ADD_NEW_COMMENT_STANDARD_COMMENT_FEEDBACK}
      			</div>
   			</div>
		</fieldset>
		<div class ="col-sm-10">
			<div class="form-group">
				<p id="form-new-comment-parameter-current-alien-id" hidden>${alien.id}</p>
				<p id="form-new-comment-parameter-current-user-id" hidden>${currentUser.id}</p>
				<button class="btn btn-outline-info" 
						type="submit">
					${TEXT[FORM_NEW_COMMENT_SUBMIT]}
				</button>
			</div>
		</div>
	</form>
</div>