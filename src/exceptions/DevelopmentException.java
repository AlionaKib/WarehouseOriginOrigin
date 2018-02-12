package exceptions;

/**
 * @author Gray-Wanderer on 06.01.2018.
 */
public class DevelopmentException extends RuntimeException {

    public DevelopmentException(String message) {
        super(message);
    }

    public DevelopmentException(String message, Throwable cause) {
        super(message, cause);
    }
}
