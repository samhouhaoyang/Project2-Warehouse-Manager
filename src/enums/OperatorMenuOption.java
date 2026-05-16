package enums;

public enum OperatorMenuOption {
    START_SHIFT,
    RESUME_SHIFT,
    VIEW_SHIFT_SUMMARY,
    VIEW_PAYSLIP,
    LOGOUT,
    INVALID;


    public static OperatorMenuOption fromInput(String input) {
        return switch (input) {
            case "1" ->START_SHIFT;
            case "2" -> RESUME_SHIFT;
            case "3" -> VIEW_SHIFT_SUMMARY;
            case "4" -> VIEW_PAYSLIP;
            case "5" -> LOGOUT;
            default -> INVALID;
        };
    }
}