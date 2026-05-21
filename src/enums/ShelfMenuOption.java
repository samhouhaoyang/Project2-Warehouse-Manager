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
