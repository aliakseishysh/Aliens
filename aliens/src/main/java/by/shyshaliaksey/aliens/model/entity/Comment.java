package by.shyshaliaksey.aliens.model.entity;

import java.util.Objects;

/**
 * Class {@code Comment} designed for storing information about comment
 * 
 * @author Aliaksey Shysh
 *
 */
public class Comment {

	private final int id;
	private final String text;
	private final User user;

	public enum Status {
		NORMAL, DELETED
	}

	public Comment(int id, String text, User user) {
		this.id = id;
		this.text = text;
		this.user = user;
	}

	@SuppressWarnings("unused")
	public int getId() {
		return id;
	}

	@SuppressWarnings("unused")
	public String getText() {
		return text;
	}

	@SuppressWarnings("unused")
	public User getUser() {
		return user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Comment)) return false;

		Comment comment = (Comment) o;

		if (id != comment.id) return false;
		if (!Objects.equals(text, comment.text)) return false;
		return Objects.equals(user, comment.user);
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
		builder.append("]");
		return builder.toString();
	}

}
