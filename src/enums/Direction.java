package enums;

public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    DELIVER,
    QUIT,
    INVALID;

    public static Direction fromInput(String input) {
        if (input == null) {
            return INVALID;
        }

        return switch (input.trim().toUpperCase()) {
            case "U" -> UP;
            case "D" -> DOWN;
            case "L" -> LEFT;
            case "R" -> RIGHT;
            case "T" -> DELIVER;
            case "Q" -> QUIT;
            default -> INVALID;
        };
    }
}