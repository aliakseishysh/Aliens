package by.shyshaliaksey.webproject.model.entity.feedback;

public class PasswordUpdateResultInfo {

	private boolean passwordCorrect;
	private boolean passwordConfirmationCorrect;
	private String passwordErrorInfo;
	private String passwordConfirmationErrorInfo;
	
	public boolean isPasswordCorrect() {
		return passwordCorrect;
	}
	public boolean isPasswordConfirmationCorrect() {
		return passwordConfirmationCorrect;
	}
	public String getPasswordErrorInfo() {
		return passwordErrorInfo;
	}
	public String getPasswordConfirmationErrorInfo() {
		return passwordConfirmationErrorInfo;
	}
	public void setPasswordCorrect(boolean passwordCorrect) {
		this.passwordCorrect = passwordCorrect;
	}
	public void setPasswordConfirmationCorrect(boolean passwordConfirmationCorrect) {
		this.passwordConfirmationCorrect = passwordConfirmationCorrect;
	}
	public void setPasswordErrorInfo(String passwordErrorInfo) {
		this.passwordErrorInfo = passwordErrorInfo;
	}
	public void setPasswordConfirmationErrorInfo(String passwordConfirmationErrorInfo) {
		this.passwordConfirmationErrorInfo = passwordConfirmationErrorInfo;
	}
	
}
