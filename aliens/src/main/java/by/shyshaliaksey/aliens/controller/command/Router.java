package by.shyshaliaksey.aliens.controller.command;


/**
 *	Suitable class for storing information about `page to go`, response parameter and type of response
 */
public class Router {

	/**
	 *	Enum defining types of response answer
	 */
	public enum Type {
		AJAX_RESPONSE, FORWARD, REDIRECT;
	}

	private final String pageToGo;
	private final String jsonResponse;
	private final Type type;

	/**
	 * 
	 * @param pageToGo contains information about url address to send response (may be null if {@link Type#AJAX_RESPONSE})
	 * @param jsonResponse contains response to ajax request (may be null if server error occurred) 
	 * @param type contains information about current response type 
	 * @see Controller
	 */
	public Router(String pageToGo, String jsonResponse, Type type) {
		this.pageToGo = pageToGo;
		this.jsonResponse = jsonResponse;
		this.type = type;
	}

	public String getPageToGo() {
		return pageToGo;
	}

	public String getJsonResponse() {
		return jsonResponse;
	}

	public Type getRouterType() {
		return type;
	}

}
