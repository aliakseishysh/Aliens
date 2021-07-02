package by.shyshaliaksey.webproject.model.entity;

import java.util.Date;

public class User {

	private int id;
	private String email;
	private String login;
	private String imageUrl;
	private Role role;
	private UserStatus userStatus;
	private Date bannedToDate;
	
	public User(int id, String email, String login, String imageUrl, Role role, UserStatus userStatus, Date bannedToDate) {
		this.id = id;
		this.email = email;
		this.login = login;
		this.imageUrl = imageUrl;
		this.role = role;
		this.userStatus = userStatus;
		this.bannedToDate = bannedToDate;
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

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public Date getBannedToDate() {
		return bannedToDate;
	}

	public void setBannedToDate(Date bannedToDate) {
		this.bannedToDate = bannedToDate;
	}
	
}
