package data;

/**
 * @author Gray-Wanderer on 06.01.2018.
 */
public class DaoException extends RuntimeException {


    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
