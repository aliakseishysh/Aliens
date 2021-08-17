package by.shyshaliaksey.aliens.exception;

/**
 * Exception for methods in database layer
 * 
 * @author Aliaksey Shysh
 *
 */
public class DaoException extends Exception {

	private static final long serialVersionUID = -3303410395502061392L;

	public DaoException() {
		super();
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}
}