package by.shyshaliaksey.webproject.exception;

/**
 * Exception for methods in service layer
 * 
 * @author Aliaksey Shysh
 *
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = -6066363961665416027L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}
}
