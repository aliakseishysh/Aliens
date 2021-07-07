package by.shyshaliaksey.webproject.model.entity.feedback;

public class PromoteDemoteUserResultInfo {

	private boolean loginCorrect;
	private String loginErrorInfo;
	
	public boolean isLoginCorrect() {
		return loginCorrect;
	}
	public String getLoginErrorInfo() {
		return loginErrorInfo;
	}
	public void setLoginCorrect(boolean loginCorrect) {
		this.loginCorrect = loginCorrect;
	}
	public void setLoginErrorInfo(String loginErrorInfo) {
		this.loginErrorInfo = loginErrorInfo;
	}
	
}
