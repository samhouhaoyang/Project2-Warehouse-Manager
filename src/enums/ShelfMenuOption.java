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
 * Represents menu options available while standing on a shelf.
 */
public enum ShelfMenuOption {
    /** Show items on the current shelf. */
    VIEW_ITEMS,

    /** Pick an item from the current shelf. */
    PICK_ITEM,

    /** Leave the shelf menu. */
    QUIT,

    /** Represents an unrecognised shelf menu option. */
    INVALID;

    /**
     * Converts user input into a shelf menu option.
     *
     * @param input raw user input
     * @return the matching menu option, or INVALID if the input is not recognised
     */
    public static ShelfMenuOption fromInput(String input) {
        if (input == null) {
            return INVALID;
        }

        return switch (input.trim().toUpperCase()) {
            case Constants.INPUT_VIEW -> VIEW_ITEMS;
            case Constants.INPUT_PICK -> PICK_ITEM;
            case Constants.QUIT -> QUIT;
            default -> INVALID;
        };
    }
}
