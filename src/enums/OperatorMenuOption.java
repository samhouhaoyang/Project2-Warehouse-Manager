package enums;

import utils.Constants;

/**
 * Represents menu options available to operators and senior operators.
 */
public enum OperatorMenuOption {
    /** Start or resume warehouse navigation from the operator menu. */
    START_SHIFT,

    /** Resume a previously started warehouse shift. */
    RESUME_SHIFT,

    /** Show the current employee shift summary. */
    VIEW_SHIFT_SUMMARY,

    /** Show the current employee payslip. */
    VIEW_PAYSLIP,

    /** Log out from the operator menu. */
    LOGOUT,

    /** Represents an unrecognised operator menu option. */
    INVALID;


    /**
     * Converts user input into an operator menu option.
     *
     * @param input raw user input
     * @return the matching menu option, or INVALID if the input is not recognised
     */
    public static OperatorMenuOption fromInput(String input) {
        if (input == null) {
            return INVALID;
        }

        return switch (input.trim()) {
            case Constants.MENU_OPTION_1 -> START_SHIFT;
            case Constants.MENU_OPTION_2 -> RESUME_SHIFT;
            case Constants.MENU_OPTION_3 -> VIEW_SHIFT_SUMMARY;
            case Constants.MENU_OPTION_4 -> VIEW_PAYSLIP;
            case Constants.MENU_OPTION_5 -> LOGOUT;
            default -> INVALID;
        };
    }
}
