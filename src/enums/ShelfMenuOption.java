package enums;

import utils.Constants;

public enum ShelfMenuOption {
    VIEW_ITEMS,
    PICK_ITEM,
    QUIT,
    INVALID;

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
