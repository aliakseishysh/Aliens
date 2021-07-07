package by.shyshaliaksey.webproject.model.entity.feedback;

public class LoginResultInfo {

	private boolean emailCorrect;
	private boolean passwordCorrect;
	private String emailErrorInfo;
	private String passwordErrorInfo;
	
	public boolean isEmailCorrect() {
		return emailCorrect;
	}
	public boolean isPasswordCorrect() {
		return passwordCorrect;
	}
	public void setEmailCorrect(boolean emailCorrect) {
		this.emailCorrect = emailCorrect;
	}
	public void setPasswordCorrect(boolean passwordCorrect) {
		this.passwordCorrect = passwordCorrect;
	}
	public String getEmailErrorInfo() {
		return emailErrorInfo;
	}
	public String getPasswordErrorInfo() {
		return passwordErrorInfo;
	}
	public void setEmailErrorInfo(String emailErrorInfo) {
		this.emailErrorInfo = emailErrorInfo;
	}
	public void setPasswordErrorInfo(String passwordErrorInfo) {
		this.passwordErrorInfo = passwordErrorInfo;
	}
	
}
