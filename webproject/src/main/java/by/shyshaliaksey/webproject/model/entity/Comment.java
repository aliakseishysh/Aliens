package by.shyshaliaksey.webproject.model.entity;

/**
 * Class {@code Comment} designed for storing information about comment
 * 
 * @author Aliaksey Shysh
 *
 */
public class Comment {

	private int id;
	private String text;
	private User user;
	private Status status;

	public enum Status {
		NORMAL, DELETED
	}

	public Comment(int id, String text, User user, Status status) {
		super();
		this.id = id;
		this.text = text;
		this.user = user;
		this.status = status;
	}

	public Comment(int id, String text, User user) {
		this.id = id;
		this.text = text;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public User getUser() {
		return user;
	}

	public Status getStatus() {
		return status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Comment other = (Comment) obj;
		if (id != other.id) {
			return false;
		}
		if (status != other.status) {
			return false;
		}
		if (text == null) {
			if (other.text != null) {
				return false;
			}
		} else if (!text.equals(other.text)) {
			return false;
		}
		if (user == null) {
			if (other.user != null) {
				return false;
			}
		} else if (!user.equals(other.user)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Comment [id=");
		builder.append(id);
		builder.append(", text=");
		builder.append(text);
		builder.append(", user=");
		builder.append(user);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

}
