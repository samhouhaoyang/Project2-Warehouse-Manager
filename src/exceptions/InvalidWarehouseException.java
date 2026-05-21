package exceptions;

/**
 * Thrown when a warehouse file line has an invalid format or floor number.
 */
public class InvalidWarehouseException extends Exception {
    /**
     * Creates an exception with the message required for the current input line.
     *
     * @param message error message to display
     */
    public InvalidWarehouseException(String message) {
        super(message);
    }
}
