package by.shyshaliaksey.aliens.model.entity;

/**
 * Class {@code Token} designed for storing information about token
 * 
 * @author Aliaksey Shysh
 *
 */
public class Token {

	private int tokenId;
	private String email;
	private String tokenText;
	private Status status;
	private String expirationDate;
	private String newEmail;

	public enum Status {
		NORMAL, EXPIRED
	}

	public Token(int tokenId, String email, String tokenText, Status status, String expirationDate) {
		this.tokenId = tokenId;
		this.email = email;
		this.tokenText = tokenText;
		this.status = status;
		this.expirationDate = expirationDate;
	}

	public Token(int tokenId, String email, String tokenText, Status status, String expirationDate, String newEmail) {
		this.tokenId = tokenId;
		this.email = email;
		this.tokenText = tokenText;
		this.status = status;
		this.expirationDate = expirationDate;
		this.newEmail = newEmail;
	}

	public int getTokenId() {
		return tokenId;
	}

	public void setTokenId(int tokenId) {
		this.tokenId = tokenId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTokenText() {
		return tokenText;
	}

	public void setTokenText(String tokenText) {
		this.tokenText = tokenText;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getNewEmail() {
		return newEmail;
	}

	public void setNewEmail(String newEmail) {
		this.newEmail = newEmail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((expirationDate == null) ? 0 : expirationDate.hashCode());
		result = prime * result + ((newEmail == null) ? 0 : newEmail.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + tokenId;
		result = prime * result + ((tokenText == null) ? 0 : tokenText.hashCode());
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
		Token other = (Token) obj;
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (expirationDate == null) {
			if (other.expirationDate != null) {
				return false;
			}
		} else if (!expirationDate.equals(other.expirationDate)) {
			return false;
		}
		if (newEmail == null) {
			if (other.newEmail != null) {
				return false;
			}
		} else if (!newEmail.equals(other.newEmail)) {
			return false;
		}
		if (status != other.status) {
			return false;
		}
		if (tokenId != other.tokenId) {
			return false;
		}
		if (tokenText == null) {
			if (other.tokenText != null) {
				return false;
			}
		} else if (!tokenText.equals(other.tokenText)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Token [tokenId=");
		builder.append(tokenId);
		builder.append(", email=");
		builder.append(email);
		builder.append(", tokenText=");
		builder.append(tokenText);
		builder.append(", status=");
		builder.append(status);
		builder.append(", expirationDate=");
		builder.append(expirationDate);
		builder.append(", newEmail=");
		builder.append(newEmail);
		builder.append("]");
		return builder.toString();
	}

}
