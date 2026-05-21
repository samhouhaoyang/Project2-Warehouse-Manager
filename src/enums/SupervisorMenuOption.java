package enums;

import utils.Constants;

/**
 * Represents menu options available to supervisors.
 */
public enum SupervisorMenuOption {
    /** Start or resume warehouse navigation from the supervisor menu. */
    START_SHIFT,

    /** Resume a previously started warehouse shift. */
    RESUME_SHIFT,

    /** Show the supervisor's shift summary. */
    VIEW_SHIFT_SUMMARY,

    /** Show the supervisor's payslip. */
    VIEW_PAYSLIP,

    /** Show direct reportees' shift summaries. */
    VIEW_REPORTEE_SHIFT_SUMMARY,

    /** Log out from the supervisor menu. */
    LOGOUT,

    /** Represents an unrecognised supervisor menu option. */
    INVALID;


    /**
     * Converts user input into a supervisor menu option.
     *
     * @param input raw user input
     * @return the matching menu option, or INVALID if the input is not recognised
     */
    public static SupervisorMenuOption fromInput(String input) {
        if (input == null) {
            return INVALID;
        }

        return switch (input.trim()) {
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
