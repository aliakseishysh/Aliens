package by.shyshaliaksey.webproject.model.entity.feedback;

public class RegisterResultInfo {

	private boolean registrationSuccessful;
	private boolean emailCorrect;
	private boolean loginCorrect;
	private boolean passwordCorrect;
	private boolean passwordConfirmationCorrect;
	private String emailErrorInfo;
	private String loginErrorInfo;
	private String passwordErrorInfo;
	private String passwordConfirmationErrorInfo;
	
	public boolean isEmailCorrect() {
		return emailCorrect;
	}
	public boolean isLoginCorrect() {
		return loginCorrect;
	}
	public boolean isPasswordCorrect() {
		return passwordCorrect;
	}
	public boolean isPasswordConfirmationCorrect() {
		return passwordConfirmationCorrect;
	}
	public String getEmailErrorInfo() {
		return emailErrorInfo;
	}
	public String getLoginErrorInfo() {
		return loginErrorInfo;
	}
	public String getPasswordErrorInfo() {
		return passwordErrorInfo;
	}
	public String getPasswordConfirmationErrorInfo() {
		return passwordConfirmationErrorInfo;
	}
	public void setEmailCorrect(boolean emailCorrect) {
		this.emailCorrect = emailCorrect;
	}
	public void setLoginCorrect(boolean loginCorrect) {
		this.loginCorrect = loginCorrect;
	}
	public void setPasswordCorrect(boolean passwordCorrect) {
		this.passwordCorrect = passwordCorrect;
	}
	public void setPasswordConfirmationCorrect(boolean passwordConfirmationCorrect) {
		this.passwordConfirmationCorrect = passwordConfirmationCorrect;
	}
	public void setEmailErrorInfo(String emailErrorInfo) {
		this.emailErrorInfo = emailErrorInfo;
	}
	public void setLoginErrorInfo(String loginErrorInfo) {
		this.loginErrorInfo = loginErrorInfo;
	}
	public void setPasswordErrorInfo(String passwordErrorInfo) {
		this.passwordErrorInfo = passwordErrorInfo;
	}
	public void setPasswordConfirmationErrorInfo(String passwordConfirmationErrorInfo) {
		this.passwordConfirmationErrorInfo = passwordConfirmationErrorInfo;
	}
	public boolean isRegistrationSuccessful() {
		return registrationSuccessful;
	}
	public void setRegistrationSuccessful(boolean registrationSuccessful) {
		this.registrationSuccessful = registrationSuccessful;
	}
	
	
}
