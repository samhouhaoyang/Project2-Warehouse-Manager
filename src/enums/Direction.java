/**
 * Student Name - Haoyang Hou
 * Student Id - 1462169
 * Student email - houhh@student.unimelb.edu.au
 * AI Usage Declaration - I used AI assistance to understand and improve encapsulation and interface design, to debug test/output mismatches, and to improve code comments and Javadoc. All final design decisions, code changes, and submission responsibility are mine.
 * Assignment 1 solution reference - Parts of the warehouse movement, shelf interaction, and map-display structure were informed by the Assignment 1 specification/scaffold concepts, then adapted for the Assignment 2 multi-floor, employee, and payroll requirements.
 */

package enums;

import utils.Constants;

/**
 * Represents movement-menu commands for forklift navigation.
 */
public enum Direction {
    /** Move the forklift up one row. */
    UP,

    /** Move the forklift down one row. */
    DOWN,

    /** Move the forklift left one column. */
    LEFT,

    /** Move the forklift right one column. */
    RIGHT,

    /** Deliver the carried item at the start cell. */
    DELIVER,

    /** Leave the movement menu. */
    QUIT,

    /** Represents an unrecognised movement command. */
    INVALID;

    /**
     * Converts user input into a movement command.
     *
     * @param input raw user input
     * @return the matching direction, or INVALID if the input is not recognised
     */
    public static Direction fromInput(String input) {
        if (input == null) {
            return INVALID;
        }

        return switch (input.trim().toUpperCase()) {
            case Constants.INPUT_UP -> UP;
            case Constants.INPUT_DOWN -> DOWN;
            case Constants.INPUT_LEFT -> LEFT;
            case Constants.INPUT_RIGHT -> RIGHT;
            case Constants.INPUT_DELIVER -> DELIVER;
            case Constants.QUIT -> QUIT;
            default -> INVALID;
        };
    }
}
