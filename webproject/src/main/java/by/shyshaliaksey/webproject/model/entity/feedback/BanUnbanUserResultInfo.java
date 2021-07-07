package by.shyshaliaksey.webproject.model.entity.feedback;

public class BanUnbanUserResultInfo {

	private boolean loginCorrect;
	private boolean daysToBanCorrect;
	private String loginErrorInfo;
	private String daysToBanErrorInfo;
	
	public boolean isLoginCorrect() {
		return loginCorrect;
	}
	public boolean isDaysToBanCorrect() {
		return daysToBanCorrect;
	}
	public String getLoginErrorInfo() {
		return loginErrorInfo;
	}
	public String getDaysToBanErrorInfo() {
		return daysToBanErrorInfo;
	}
	public void setLoginCorrect(boolean loginCorrect) {
		this.loginCorrect = loginCorrect;
	}
	public void setDaysToBanCorrect(boolean daysToBanCorrect) {
		this.daysToBanCorrect = daysToBanCorrect;
	}
	public void setLoginErrorInfo(String loginErrorInfo) {
		this.loginErrorInfo = loginErrorInfo;
	}
	public void setDaysToBanErrorInfo(String daysToBanErrorInfo) {
		this.daysToBanErrorInfo = daysToBanErrorInfo;
	}
	
	
}
