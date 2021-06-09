package by.shyshaliaksey.webproject.model.entity;

public class User extends AbstractUser {

	public User(int id, String email, String login, String imageUrl, Role role) {
		super(id, email, login, imageUrl, role);
	}

}
