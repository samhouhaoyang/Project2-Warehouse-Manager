package enums;

import utils.Constants;

public enum PayrollManagerMenuOption {
    VIEW_ALL_EMPLOYEE_SHIFT,
    GENERATE_PAYSLIPS,
    VIEW_ALL_PAYSLIPS,
    LOGOUT,
    INVALID;


    public static PayrollManagerMenuOption fromInput(String input) {
        return switch (input) {
            case Constants.MENU_OPTION_1 -> VIEW_ALL_EMPLOYEE_SHIFT;
            case Constants.MENU_OPTION_2 -> GENERATE_PAYSLIPS;
            case Constants.MENU_OPTION_3 -> VIEW_ALL_PAYSLIPS;
            case Constants.MENU_OPTION_4 -> LOGOUT;
            default -> INVALID;
        };
    }
}
