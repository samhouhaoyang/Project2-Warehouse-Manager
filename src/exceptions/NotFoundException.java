package exceptions;

/**
 * Thrown when requested payslip data is not available.
 */
public class NotFoundException extends Exception {
    /**
     * Creates an exception with the message to display to the user.
     *
     * @param message error message to display
     */
    public NotFoundException(String message) {
        super(message);
    }
}
