package by.shyshaliaksey.webproject.model.entity;

public class UserFactory {

	private UserFactory() {
	}
	
	public static AbstractUser getInstance(String email, String loginName, String imageUrl, Role role) {
		AbstractUser abstractUser = null;
		switch (role) {
		case ADMIN:
			abstractUser = new Admin(email, loginName, imageUrl, role);
			break;
		case USER:
			abstractUser = new User(email, loginName, imageUrl, role);
			break;
		default:
			throw new IllegalArgumentException("Illegal Argument: " + role);
		}
		return abstractUser;
	}

}
