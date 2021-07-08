package by.shyshaliaksey.webproject.model.entity.feedback;

public class AddNewUpdateAlienResultInfo {

	private int responseStatus;
	private boolean alienNameCorrect;
	private boolean alienSmallDescriptionCorrect;
	private boolean alienFullDescriptionCorrect;
	private boolean alienImageCorrect;
	private String alienNameErrorInfo;
	private String alienSmallDescriptionErrorInfo;
	private String alienFullDescriptionErrorInfo;
	private String alienImageErrorInfo;
	
	public int getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(int responseStatus) {
		this.responseStatus = responseStatus;
	}
	public boolean isAlienNameCorrect() {
		return alienNameCorrect;
	}
	public void setAlienNameCorrect(boolean alienNameCorrect) {
		this.alienNameCorrect = alienNameCorrect;
	}
	public boolean isAlienSmallDescriptionCorrect() {
		return alienSmallDescriptionCorrect;
	}
	public void setAlienSmallDescriptionCorrect(boolean alienSmallDescriptionCorrect) {
		this.alienSmallDescriptionCorrect = alienSmallDescriptionCorrect;
	}
	public boolean isAlienFullDescriptionCorrect() {
		return alienFullDescriptionCorrect;
	}
	public void setAlienFullDescriptionCorrect(boolean alienFullDescriptionCorrect) {
		this.alienFullDescriptionCorrect = alienFullDescriptionCorrect;
	}
	public boolean isAlienImageCorrect() {
		return alienImageCorrect;
	}
	public void setAlienImageCorrect(boolean alienImageCorrect) {
		this.alienImageCorrect = alienImageCorrect;
	}
	public String getAlienNameErrorInfo() {
		return alienNameErrorInfo;
	}
	public void setAlienNameErrorInfo(String alienNameErrorInfo) {
		this.alienNameErrorInfo = alienNameErrorInfo;
	}
	public String getAlienSmallDescriptionErrorInfo() {
		return alienSmallDescriptionErrorInfo;
	}
	public void setAlienSmallDescriptionErrorInfo(String alienSmallDescriptionErrorInfo) {
		this.alienSmallDescriptionErrorInfo = alienSmallDescriptionErrorInfo;
	}
	public String getAlienFullDescriptionErrorInfo() {
		return alienFullDescriptionErrorInfo;
	}
	public void setAlienFullDescriptionErrorInfo(String alienFullDescriptionErrorInfo) {
		this.alienFullDescriptionErrorInfo = alienFullDescriptionErrorInfo;
	}
	public String getAlienImageErrorInfo() {
		return alienImageErrorInfo;
	}
	public void setAlienImageErrorInfo(String alienImageErrorInfo) {
		this.alienImageErrorInfo = alienImageErrorInfo;
	}
	
}
