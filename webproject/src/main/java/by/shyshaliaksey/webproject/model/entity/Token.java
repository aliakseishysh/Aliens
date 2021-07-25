package by.shyshaliaksey.webproject.model.entity;

public class Token {
	
	private int tokenId;
	private String email;
	private String token;
	private Status status;
	private String expirationDate;
	private String newEmail;

	public enum Status {
		NORMAL, EXPIRED
	}

	public Token(int tokenId, String email, String token, Status status, String expirationDate) {
		this.tokenId = tokenId;
		this.email = email;
		this.token = token;
		this.status = status;
		this.expirationDate = expirationDate;
	}
	
	public Token(int tokenId, String email, String token, Status status, String expirationDate, String newEmail) {
		this.tokenId = tokenId;
		this.email = email;
		this.token = token;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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
	
}
