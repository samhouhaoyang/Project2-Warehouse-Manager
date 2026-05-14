package enums;

public enum PayrollManagerMenuOption {
    VIEW_ALL_EMPLOYEE_SHIFT,
    GENERATE_PAYSLIPS,
    VIEW_ALL_PAYSLIPS,
    LOGOUT,
    INVALID;


    public static PayrollManagerMenuOption fromInput(String input) {
        return switch (input) {
            case "1" -> VIEW_ALL_EMPLOYEE_SHIFT;
            case "2" -> GENERATE_PAYSLIPS;
            case "3" -> VIEW_ALL_PAYSLIPS;
            case "4" -> LOGOUT;
            default -> INVALID;
        };
    }
}
