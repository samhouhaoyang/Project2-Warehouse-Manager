package enums;

public enum SupervisorMenuOption {
    START_SHIFT,
    RESUME_SHIFT,
    VIEW_SHIFT_SUMMARY,
    VIEW_PAYSLIP,
    VIEW_REPORTEE_SHIFT_SUMMARY,
    LOGOUT,
    INVALID;


    public static SupervisorMenuOption fromInput(String input) {
        return switch (input) {
            case "1" ->START_SHIFT;
            case "2" -> RESUME_SHIFT;
            case "3" -> VIEW_SHIFT_SUMMARY;
            case "4" -> VIEW_PAYSLIP;
            case "5" -> VIEW_REPORTEE_SHIFT_SUMMARY;
            case "6" -> LOGOUT;
            default -> INVALID;
        };
    }
}
