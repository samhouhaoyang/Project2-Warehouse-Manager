package enums;

import utils.Constants;

public enum OperatorMenuOption {
    START_SHIFT,
    RESUME_SHIFT,
    VIEW_SHIFT_SUMMARY,
    VIEW_PAYSLIP,
    LOGOUT,
    INVALID;


    public static OperatorMenuOption fromInput(String input) {
        return switch (input) {
            case Constants.MENU_OPTION_1 -> START_SHIFT;
            case Constants.MENU_OPTION_2 -> RESUME_SHIFT;
            case Constants.MENU_OPTION_3 -> VIEW_SHIFT_SUMMARY;
            case Constants.MENU_OPTION_4 -> VIEW_PAYSLIP;
            case Constants.MENU_OPTION_5 -> LOGOUT;
            default -> INVALID;
        };
    }
}
