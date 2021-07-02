package by.shyshaliaksey.webproject.controller;

public class ErrorAttribute {
	
	private String errorInfo;
	private String banInfo;
	
	public enum Name{
		ERROR_INFO,
		BAN_INFO;
	}
	
	public ErrorAttribute(String errorInfo, String banInfo) {
		this.errorInfo = errorInfo;
		this.banInfo = banInfo;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public String getBanInfo() {
		return banInfo;
	}

	public void setBanInfo(String banInfo) {
		this.banInfo = banInfo;
	}
	
}
