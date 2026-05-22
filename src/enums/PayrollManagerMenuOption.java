/**
 * Student Name - Haoyang Hou
 * Student Id - 1462169
 * Student email - houhh@student.unimelb.edu.au
 * AI Usage Declaration - I used AI assistance to understand and improve encapsulation and interface design, to debug test/output mismatches, and to improve code comments and Javadoc. All final design decisions, code changes, and submission responsibility are mine.
 * Assignment 1 solution reference - Parts of the warehouse movement, shelf interaction, and map-display structure were informed by the Assignment 1 specification/scaffold concepts, then adapted for the Assignment 2 multi-floor, employee, and payroll requirements.
 */

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
