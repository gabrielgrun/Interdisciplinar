package exceptions;

/**
 *
 * @author Gabriel
 */
public class AppException extends Exception{
    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Exception exception) {
        super(message, exception);
    }
}
