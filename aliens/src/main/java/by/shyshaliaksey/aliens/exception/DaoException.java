package by.shyshaliaksey.aliens.exception;

import java.io.Serial;

/**
 * Exception for methods in database layer
 * 
 * @author Aliaksey Shysh
 *
 */
public class DaoException extends Exception {

	@Serial
	private static final long serialVersionUID = -3303410395502061392L;

	@SuppressWarnings("unused")
	public DaoException() {
		super();
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	@SuppressWarnings("unused")
	public DaoException(Throwable cause) {
		super(cause);
	}
}
