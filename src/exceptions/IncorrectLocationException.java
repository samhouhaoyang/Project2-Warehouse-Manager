package exceptions;

/**
 * Thrown when a warehouse file line conflicts with an existing cell location.
 */
public class IncorrectLocationException extends Exception {
    /**
     * Creates an exception with the message required for the current input line.
     *
     * @param message error message to display
     */
    public IncorrectLocationException(String message) {
        super(message);
    }
}
