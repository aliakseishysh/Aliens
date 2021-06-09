package by.shyshaliaksey.webproject.model.entity;

public abstract class AbstractUser {

	private String email;
	private String login;
	private String imageUrl;
	private Role role;
	
	protected AbstractUser(String email, String login, String imageUrl, Role role) {
		this.email = email;
		this.login = login;
		this.imageUrl = imageUrl;
		this.role = role;
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
