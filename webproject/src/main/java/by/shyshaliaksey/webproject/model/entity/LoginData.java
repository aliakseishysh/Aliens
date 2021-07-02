package by.shyshaliaksey.webproject.model.entity;

public class LoginData {

	private String hashedPasswordHex;
	private String saltHex;
	
	public LoginData(String hashedPasswordHex, String saltHex) {
		this.hashedPasswordHex = hashedPasswordHex;
		this.saltHex = saltHex;
	}
	
	public String getHashedPasswordHex() {
		return hashedPasswordHex;
	}
	public String getSaltHex() {
		return saltHex;
	}
	
}
