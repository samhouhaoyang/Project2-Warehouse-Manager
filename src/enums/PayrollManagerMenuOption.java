package enums;

import utils.Constants;

/**
 * Represents menu options available to payroll managers.
 */
public enum PayrollManagerMenuOption {
    /** Show all employees' shift summaries. */
    VIEW_ALL_EMPLOYEE_SHIFT,

    /** Generate payslips for the current session. */
    GENERATE_PAYSLIPS,

    /** Show generated or loaded payslips. */
    VIEW_ALL_PAYSLIPS,

    /** Log out from the payroll manager menu. */
    LOGOUT,

    /** Represents an unrecognised payroll manager menu option. */
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
