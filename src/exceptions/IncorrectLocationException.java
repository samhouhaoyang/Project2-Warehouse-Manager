/**
 * Student Name - Haoyang Hou
 * Student Id - 1462169
 * Student email - houhh@student.unimelb.edu.au
 * AI Usage Declaration - I used AI assistance to understand and improve encapsulation and interface design, to debug test/output mismatches, and to improve code comments and Javadoc. All final design decisions, code changes, and submission responsibility are mine.
 * Assignment 1 solution reference - Parts of the warehouse movement, shelf interaction, and map-display structure were informed by the Assignment 1 specification/scaffold concepts, then adapted for the Assignment 2 multi-floor, employee, and payroll requirements.
 */

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
