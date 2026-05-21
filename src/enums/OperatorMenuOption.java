package enums;

import utils.Constants;

/**
 * Represents menu options available to operators and senior operators.
 */
public enum OperatorMenuOption {
    START_SHIFT,
    RESUME_SHIFT,
    VIEW_SHIFT_SUMMARY,
    VIEW_PAYSLIP,
    LOGOUT,
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
