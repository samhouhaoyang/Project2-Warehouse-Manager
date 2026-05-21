package exceptions;

/**
 * Thrown when a file value does not match one of the allowed enum types.
 */
public class IncorrectTypeException extends Exception {
    /**
     * Creates an exception with the message required for the current input line.
     *
     * @param message error message to display
     */
    public IncorrectTypeException(String message) {
        super(message);
    }
}
