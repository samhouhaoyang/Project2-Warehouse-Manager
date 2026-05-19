package enums;

public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    INVALID;

    public static Direction fromInput(String input) {
        return switch (input) {
            case "U" -> UP;
            case "D" -> DOWN;
            case "L" -> LEFT;
            case "R" -> RIGHT;
            default -> INVALID;
        };
    }
}
