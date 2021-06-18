package by.shyshaliaksey.webproject.controller.command;


public class Router {

	public enum RouterType {
		FORWARD, REDIRECT, AJAX_RESPONSE
	}
	
	private final String pagePath;
	private final String responseParameter;
	private final RouterType routerType;
	
	public Router(String pagePath, String responseParameter, RouterType routerType) {
		this.pagePath = pagePath;
		this.responseParameter = responseParameter;
		this.routerType = routerType;
	}
	
	public String getPagePath() {
		return pagePath;
	}

	public String getResponseParameter() {
		return responseParameter;
	}
	
	public RouterType getRouterType() {
		return routerType;
	}
	
}
