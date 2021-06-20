<div class="content-section">
	<form id="user-ban-unban-form">
		<fieldset class="form-group">
			<legend class="border-bottom mb-4">Bans</legend>
			<label class="control-label col-sm-2" for="user-login">User Login:</label>
			<div class="col-sm-10">
  				<input type="text" class="form-control" id="user-login" name="user-login" placeholder="Enter user login">
  			</div>
  			<label class="control-label col-sm-2" for="user-login">Days in ban:</label>
			<div class="col-sm-10">
  				<input type="text" class="form-control" id="days-in-ban" name="days-in-ban" placeholder="Enter days in ban">
  			</div>
		</fieldset>
		<div class="form-group">
			<button class="btn btn-outline-info" 
					type="button" 
					onclick="banUser(
					 	`${PROJECT_NAME}`,
						`${CONTROLLER}`,
						`${COMMAND}`,
						`${BAN_USER}`,
						`${LOAD_BAN_UNBAN_FORM}`,
						`${LOGIN}`,
						`${DAYS_TO_BAN}`)">
				Ban
			</button>
			<button class="btn btn-outline-info" 
					type="button" 
					onclick="unbanUser(
					 	`${PROJECT_NAME}`,
						`${CONTROLLER}`,
						`${COMMAND}`,
						`${UNBAN_USER}`,
						`${LOAD_BAN_UNBAN_FORM}`,
						`${LOGIN}`)">
				Unban
			</button>
		</div>
	</form>
</div>