package exceptions;

/**
 * Thrown when a warehouse row or column is outside the usable map area.
 */
public class InvalidLocationException extends Exception {
    /**
     * Creates an exception with the message required for the current input line.
     *
     * @param message error message to display
     */
    public InvalidLocationException(String message) {
        super(message);
    }
}
