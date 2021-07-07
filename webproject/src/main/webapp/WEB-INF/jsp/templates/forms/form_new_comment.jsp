<div class="content-section">
	<form id="new-comment-form" enctype="multipart/form-data">
		<fieldset class="form-group">
			<legend class="border-bottom mb-4">Add Comment</legend>
   			<label class="control-label col-sm-2" for="new-comment-form-comment">New comment:</label>
			<div class="col-sm-10">
     			<textarea class="form-control" id="new-comment-form-comment" name="new-comment-form-comment" placeholder="Enter comment"></textarea>
   			</div>
		</fieldset>
		<div class ="col-sm-10">
			<div class="form-group">
				<button class="btn btn-outline-info" 
						type="button" 
						onclick="addNewComment()">
					Add
				</button>
			</div>
		</div>
	</form>
</div>