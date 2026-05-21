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
