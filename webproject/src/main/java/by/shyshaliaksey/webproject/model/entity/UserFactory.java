package by.shyshaliaksey.webproject.model.entity;

public class UserFactory {

	private UserFactory() {
	}
	
	public static AbstractUser getInstance(int id, String email, String loginName, String imageUrl, Role role) {
		AbstractUser abstractUser = null;
		switch (role) {
		case ADMIN:
			abstractUser = new Admin(id, email, loginName, imageUrl, role);
			break;
		case USER:
			abstractUser = new User(id, email, loginName, imageUrl, role);
			break;
		default:
			throw new IllegalArgumentException("Illegal Argument: " + role);
		}
		return abstractUser;
	}

}
