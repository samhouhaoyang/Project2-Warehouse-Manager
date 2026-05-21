package exceptions;

/**
 * Thrown when a data file line has an invalid number of fields.
 */
public class InvalidLineException extends Exception {
    /**
     * Creates an exception with the message required for the current input line.
     *
     * @param message error message to display
     */
    public InvalidLineException(String message) {
        super(message);
    }
}
