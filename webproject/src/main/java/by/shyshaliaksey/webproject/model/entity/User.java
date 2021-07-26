package by.shyshaliaksey.webproject.model.entity;

import java.util.Date;

import by.shyshaliaksey.webproject.controller.EnumValue;

public class User {

	private int id;
	private String email;
	private String login;
	private String imageUrl;
	private Role role;
	private Status status;
	private Date bannedToDate;
	
	public enum Status {
		NORMAL, BANNED, CONFIRMATION_AWAITING
	}
	
	public enum Role implements EnumValue {
		ADMIN("ADMIN"), USER("USER"), GUEST("GUEST");
		private String value;
		private Role(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
		
	}
	
	public User(int id, String email, String login, String imageUrl, Role role, Status status, Date bannedToDate) {
		this.id = id;
		this.email = email;
		this.login = login;
		this.imageUrl = imageUrl;
		this.role = role;
		this.status = status;
		this.bannedToDate = bannedToDate;
	}
	
	public User(int id, String login, String imageUrl) {
		this.id = id;
		this.login = login;
		this.imageUrl = imageUrl;
	}
	
	public User(Role role) {
		this.role = role;
	}
	
	public User() {
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Status getUserStatus() {
		return status;
	}

	public void setUserStatus(Status status) {
		this.status = status;
	}

	public Date getBannedToDate() {
		return bannedToDate;
	}

	public void setBannedToDate(Date bannedToDate) {
		this.bannedToDate = bannedToDate;
	}
	
}
