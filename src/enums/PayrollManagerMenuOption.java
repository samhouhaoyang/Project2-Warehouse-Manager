package enums;

import utils.Constants;

/**
 * Represents menu options available to payroll managers.
 */
public enum PayrollManagerMenuOption {
    VIEW_ALL_EMPLOYEE_SHIFT,
    GENERATE_PAYSLIPS,
    VIEW_ALL_PAYSLIPS,
    LOGOUT,
    INVALID;


    /**
     * Converts user input into a payroll manager menu option.
     *
     * @param input raw user input
     * @return the matching menu option, or INVALID if the input is not recognised
     */
    public static PayrollManagerMenuOption fromInput(String input) {
        if (input == null) {
            return INVALID;
        }

        return switch (input.trim()) {
            case Constants.MENU_OPTION_1 -> VIEW_ALL_EMPLOYEE_SHIFT;
            case Constants.MENU_OPTION_2 -> GENERATE_PAYSLIPS;
            case Constants.MENU_OPTION_3 -> VIEW_ALL_PAYSLIPS;
            case Constants.MENU_OPTION_4 -> LOGOUT;
            default -> INVALID;
        };
    }
}
