package by.shyshaliaksey.aliens.exception;

import java.io.Serial;

/**
 * Exception for methods in services
 * 
 * @author Aliaksey Shysh
 *
 */
public class ServiceException extends Exception {

	@Serial
    private static final long serialVersionUID = -6066363961665416027L;

	@SuppressWarnings("unused")
	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	@SuppressWarnings("unused")
	public ServiceException(Throwable cause) {
		super(cause);
	}
}
