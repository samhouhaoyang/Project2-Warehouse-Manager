/**
 * Student Name - Haoyang Hou
 * Student Id - 1462169
 * Student email - houhh@student.unimelb.edu.au
 * AI Usage Declaration - I used AI assistance to understand and improve encapsulation and interface design, to debug test/output mismatches, and to improve code comments and Javadoc. All final design decisions, code changes, and submission responsibility are mine.
 * Assignment 1 solution reference - Parts of the warehouse movement, shelf interaction, and map-display structure were informed by the Assignment 1 specification/scaffold concepts, then adapted for the Assignment 2 multi-floor, employee, and payroll requirements.
 */

package utils;

/**
 * Stores shared constants used across the warehouse manager application.
 */
public final class Constants {
    /**
     * Prevents creation of utility instances.
     */
    private Constants() {
    }

    /** Empty text value used when parsing optional file fields. */
    public static final String EMPTY_STRING = "";

    /** Login command that terminates the program. */
    public static final String TERMINATE = "X";

    /** Menu command that leaves a submenu. */
    public static final String QUIT = "Q";

    /** Movement command for moving up. */
    public static final String INPUT_UP = "U";

    /** Movement command for moving down. */
    public static final String INPUT_DOWN = "D";

    /** Movement command for moving left. */
    public static final String INPUT_LEFT = "L";

    /** Movement command for moving right. */
    public static final String INPUT_RIGHT = "R";

    /** Movement command for delivering the carried item. */
    public static final String INPUT_DELIVER = "T";

    /** Shelf menu command for viewing items. */
    public static final String INPUT_VIEW = "V";

    /** Shelf menu command for picking an item. */
    public static final String INPUT_PICK = "P";

    /** First numbered menu option. */
    public static final String MENU_OPTION_1 = "1";

    /** Second numbered menu option. */
    public static final String MENU_OPTION_2 = "2";

    /** Third numbered menu option. */
    public static final String MENU_OPTION_3 = "3";

    /** Fourth numbered menu option. */
    public static final String MENU_OPTION_4 = "4";

    /** Fifth numbered menu option. */
    public static final String MENU_OPTION_5 = "5";

    /** Sixth numbered menu option. */
    public static final String MENU_OPTION_6 = "6";

    /** Required number of command-line arguments. */
    public static final int VALID_ARGS_NUM = 5;

    /** Smallest valid row or column count for a warehouse floor. */
    public static final int MIN_VALID_ROWS_OR_COLS = 4;

    /** Default value used for counters. */
    public static final int COUNTER_INITIAL_VALUE = 0;

    /** Amount added when incrementing counters. */
    public static final int COUNTER_INCREMENT = 1;

    /** Money value used when no pay or penalty applies. */
    public static final double ZERO_MONEY_AMOUNT = 0.0;

    /** Column index of the start cell. */
    public static final int START_COL = 1;

    /** Row index of the start cell. */
    public static final int START_ROW = 1;

    /** Initial array capacity used for shelves. */
    public static final int INITIAL_SHELF_CAPACITY = 4;

    /** Multiplier used when expanding shelf storage. */
    public static final int SIZE_MULTIPLIER = 2;

    /** Map symbol for a wall. */
    public static final char WALL_SYMBOL = '#';

    /** Map symbol for an aisle. */
    public static final char AISLE_SYMBOL = '.';

    /** Map symbol for a restricted cell. */
    public static final char RESTRICTED_SYMBOL = 'X';

    /** Map symbol for a shelf. */
    public static final char SHELF_SYMBOL = 'S';

    /** Map symbol for the start cell. */
    public static final char START_SYMBOL = 'O';

    /** Map symbol for a forklift. */
    public static final char FORKLIFT_SYMBOL = 'F';

    /** Fallback map symbol for an unknown cell type. */
    public static final char UNKNOWN_SYMBOL = '?';

    /** Fixed payslip file path required by the assignment. */
    public static final String PAYSLIPS_FILE_PATH = "data/payslips.csv";

    /** CSV delimiter used by all data files. */
    public static final String CSV_DELIMITER = ",";

    /** Hyphen marker used in CSV fields and display placeholders. */
    public static final String HYPHEN = "-";

    /** Warehouse CSV cell type value for shelves. */
    public static final String FILE_CELL_TYPE_SHELF = "SHELF";

    /** Warehouse CSV cell type value for restricted cells. */
    public static final String FILE_CELL_TYPE_RESTRICTED = "RESTRICTED";

    /** Header written to the payslip file. */
    public static final String PAYSLIPS_HEADER =
            "employee_id,employee_name,base_pay,delivered_item_pay,"
                    + "hits_penalty,restricted_area_penalty,"
                    + "reportees_managing_pay,net_salary";

    /** CSV row format used when saving one payslip. */
    public static final String PAYSLIP_FILE_LINE_FORMAT =
            "%s,%s,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f";

    /** Required number of fields in a warehouse CSV row. */
    public static final int WAREHOUSE_FIELD_COUNT = 6;

    /** Minimum valid field count in an employee CSV row. */
    public static final int EMPLOYEE_MIN_FIELD_COUNT = 4;

    /** Maximum field count consumed from an employee CSV row. */
    public static final int EMPLOYEE_MAX_FIELD_COUNT = 5;

    /** Required number of fields in a payslip CSV row. */
    public static final int PAYSLIP_FIELD_COUNT = 8;

    /** Minimum valid warehouse floor number. */
    public static final int MIN_VALID_FLOOR_NUMBER = 1;

    /** Maximum valid warehouse floor number. */
    public static final int MAX_VALID_FLOOR_NUMBER = 3;

    /** Smallest valid user-facing item number. */
    public static final int MIN_VALID_ITEM_INDEX = 1;

    /** Pay awarded for each delivered item. */
    public static final double DELIVERY_PAY = 10.00;

    /** Penalty applied for each wall hit. */
    public static final double WALL_HIT_PENALTY = 0.25;

    /** Penalty applied for each restricted-area hit. */
    public static final double RESTRICTED_AREA_PENALTY = 0.50;

    /** Supervisor pay awarded per reportee. */
    public static final double REPORTEE_MANAGEMENT_PAY = 5.00;

    /** Minimum valid non-base salary component. */
    public static final double MIN_VALID_PAY_OR_PENALTY = 0.00;
}
