package enums;

import utils.Constants;

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
            case Constants.MENU_OPTION_1 -> START_SHIFT;
            case Constants.MENU_OPTION_2 -> RESUME_SHIFT;
            case Constants.MENU_OPTION_3 -> VIEW_SHIFT_SUMMARY;
            case Constants.MENU_OPTION_4 -> VIEW_PAYSLIP;
            case Constants.MENU_OPTION_5 -> VIEW_REPORTEE_SHIFT_SUMMARY;
            case Constants.MENU_OPTION_6 -> LOGOUT;
            default -> INVALID;
        };
    }
}
