package by.shyshaliaksey.webproject.model.entity;

public class User {

	private int id;
	private String email;
	private String login;
	private String imageUrl;
	private Role role;
	
	public User(int id, String email, String login, String imageUrl, Role role) {
		this.id = id;
		this.email = email;
		this.login = login;
		this.imageUrl = imageUrl;
		this.role = role;
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
	
}
