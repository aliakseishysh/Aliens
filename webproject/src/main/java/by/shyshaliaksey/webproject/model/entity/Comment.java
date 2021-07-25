package by.shyshaliaksey.webproject.model.entity;

public class Comment {

	private int id;
	private String comment;
	private User user;

	public enum CommentStatus {
		NORMAL, DELETED
	}

	public Comment(int id, String comment, User user) {
		this.id = id;
		this.comment = comment;
		this.user = user;
	}
	
	public int getId() {
		return id;
	}
	public String getComment() {
		return comment;
	}
	public User getUser() {
		return user;
	}
	
}
