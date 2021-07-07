package by.shyshaliaksey.webproject.model.entity.feedback;

public class EmailUpdateResultInfo {

	private boolean emailCorrect;
	private String emailErrorInfo;
	
	public boolean isEmailCorrect() {
		return emailCorrect;
	}
	public String getEmailErrorInfo() {
		return emailErrorInfo;
	}
	public void setEmailCorrect(boolean emailCorrect) {
		this.emailCorrect = emailCorrect;
	}
	public void setEmailErrorInfo(String emailErrorInfo) {
		this.emailErrorInfo = emailErrorInfo;
	}
	
}
