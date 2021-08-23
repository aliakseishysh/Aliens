package by.shyshaliaksey.aliens.model.entity;

import java.util.Date;
import java.util.Objects;

import by.shyshaliaksey.aliens.controller.EnumValue;

/**
 * Class {@code User} designed for storing information about user
 * 
 * @author Aliaksey Shysh
 *
 */
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

		private final String value;

		Role(String value) {
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

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@SuppressWarnings("unused")
	public String getImageUrl() {
		return imageUrl;
	}

	public Role getRole() {
		return role;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getBannedToDate() {
		return bannedToDate;
	}

	public void setBannedToDate(Date bannedToDate) {
		this.bannedToDate = bannedToDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bannedToDate == null) ? 0 : bannedToDate.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;

		User user = (User) o;

		if (id != user.id) return false;
		if (!Objects.equals(email, user.email)) return false;
		if (!Objects.equals(login, user.login)) return false;
		if (!Objects.equals(imageUrl, user.imageUrl)) return false;
		if (role != user.role) return false;
		if (status != user.status) return false;
		return Objects.equals(bannedToDate, user.bannedToDate);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", email=");
		builder.append(email);
		builder.append(", login=");
		builder.append(login);
		builder.append(", imageUrl=");
		builder.append(imageUrl);
		builder.append(", role=");
		builder.append(role);
		builder.append(", status=");
		builder.append(status);
		builder.append(", bannedToDate=");
		builder.append(bannedToDate);
		builder.append("]");
		return builder.toString();
	}

}
