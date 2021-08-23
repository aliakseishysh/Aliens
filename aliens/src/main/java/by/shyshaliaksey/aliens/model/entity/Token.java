package by.shyshaliaksey.aliens.model.entity;

import java.util.Objects;

/**
 * Class {@code Token} designed for storing information about token
 * 
 * @author Aliaksey Shysh
 *
 */
public class Token {

	private final String email;
	private final String expirationDate;
	private final String newEmail;

	public enum Status {
		NORMAL, EXPIRED
	}

	public Token(String email, String expirationDate, String newEmail) {
		this.email = email;
		this.expirationDate = expirationDate;
		this.newEmail = newEmail;
	}

	public String getEmail() {
		return email;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public String getNewEmail() {
		return newEmail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((expirationDate == null) ? 0 : expirationDate.hashCode());
		result = prime * result + ((newEmail == null) ? 0 : newEmail.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Token token = (Token) o;

		if (!Objects.equals(email, token.email)) return false;
		if (!Objects.equals(expirationDate, token.expirationDate))
			return false;
		return Objects.equals(newEmail, token.newEmail);
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Token [email=");
		builder.append(email);
		builder.append(", expirationDate=");
		builder.append(expirationDate);
		builder.append(", newEmail=");
		builder.append(newEmail);
		builder.append("]");
		return builder.toString();
	}

}
