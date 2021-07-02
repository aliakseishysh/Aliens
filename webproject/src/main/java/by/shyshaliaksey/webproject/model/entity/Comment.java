package by.shyshaliaksey.webproject.model.entity;

public class Comment {

	private int id;
	private String comment;
	private String userLogin;
	private String userImage;

	public Comment(int id, String comment, String userLogin, String userImage) {
		this.id = id;
		this.comment = comment;
		this.userLogin = userLogin;
		this.userImage = userImage;
	}
	
	public int getId() {
		return id;
	}
	public String getComment() {
		return comment;
	}
	public String getUserLogin() {
		return userLogin;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	
	public enum CommentStatus {
		NORMAL, DELETED
	}
}
