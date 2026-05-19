package enums;

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
            case "V" -> VIEW_ITEMS;
            case "P" -> PICK_ITEM;
            case "Q" -> QUIT;
            default -> INVALID;
        };
    }
}