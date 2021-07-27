package by.shyshaliaksey.webproject.model.entity;

import java.util.Date;

import by.shyshaliaksey.webproject.controller.EnumValue;

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
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		User other = (User) obj;
		if (bannedToDate == null) {
			if (other.bannedToDate != null) {
				return false;
			}
		} else if (!bannedToDate.equals(other.bannedToDate)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (imageUrl == null) {
			if (other.imageUrl != null) {
				return false;
			}
		} else if (!imageUrl.equals(other.imageUrl)) {
			return false;
		}
		if (login == null) {
			if (other.login != null) {
				return false;
			}
		} else if (!login.equals(other.login)) {
			return false;
		}
		if (role != other.role) {
			return false;
		}
		if (status != other.status) {
			return false;
		}
		return true;
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
