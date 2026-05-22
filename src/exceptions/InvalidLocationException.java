/**
 * Student Name - Haoyang Hou
 * Student Id - 1462169
 * Student email - houhh@student.unimelb.edu.au
 * AI Usage Declaration - I used AI assistance to understand and improve encapsulation and interface design, to debug test/output mismatches, and to improve code comments and Javadoc. All final design decisions, code changes, and submission responsibility are mine.
 * Assignment 1 solution reference - Parts of the warehouse movement, shelf interaction, and map-display structure were informed by the Assignment 1 specification/scaffold concepts, then adapted for the Assignment 2 multi-floor, employee, and payroll requirements.
 */

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
