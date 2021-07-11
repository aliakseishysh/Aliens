package by.shyshaliaksey.webproject.model.entity.feedback;

public class AddNewCommentResultInfo {

	private int statusCode;
	private boolean commentCorrect;
	private String commentErrorInfo;
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public boolean isCommentCorrect() {
		return commentCorrect;
	}
	public void setCommentCorrect(boolean commentCorrect) {
		this.commentCorrect = commentCorrect;
	}
	public String getCommentErrorInfo() {
		return commentErrorInfo;
	}
	public void setCommentErrorInfo(String commentErrorInfo) {
		this.commentErrorInfo = commentErrorInfo;
	}
	
	
	
}
