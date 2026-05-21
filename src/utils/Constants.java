package utils;

public class Constants {
    public static final String EMPTY_STRING = "";
    public static final String TERMINATE = "X";
    public static final String QUIT = "Q";
    public static final String INPUT_UP = "U";
    public static final String INPUT_DOWN = "D";
    public static final String INPUT_LEFT = "L";
    public static final String INPUT_RIGHT = "R";
    public static final String INPUT_DELIVER = "T";
    public static final String INPUT_VIEW = "V";
    public static final String INPUT_PICK = "P";
    public static final String MENU_OPTION_1 = "1";
    public static final String MENU_OPTION_2 = "2";
    public static final String MENU_OPTION_3 = "3";
    public static final String MENU_OPTION_4 = "4";
    public static final String MENU_OPTION_5 = "5";
    public static final String MENU_OPTION_6 = "6";

    public static final int VALID_ARGS_NUM = 5;
    public static final int MIN_VALID_ROWS_OR_COLS = 4;
    public static final int COUNTER_INITIAL_VALUE = 0;
    public static final int COUNTER_INCREMENT = 1;
    public static final double ZERO_MONEY_AMOUNT = 0.0;

    public static final int START_COL = 1;
    public static final int START_ROW = 1;


    public static final int INITIAL_SHELF_CAPACITY = 4;
    public static final int SIZE_MULTIPLIER = 2;

    public static final char WALL_SYMBOL = '#';
    public static final char AISLE_SYMBOL = '.';
    public static final char RESTRICTED_SYMBOL = 'X';
    public static final char SHELF_SYMBOL = 'S';
    public static final char START_SYMBOL = 'O';
    public static final char FORKLIFT_SYMBOL = 'F';
    public static final char UNKNOWN_SYMBOL = '?';

    public static final String PAYSLIPS_FILE_PATH = "data/payslips.csv";
    public static final String CSV_DELIMITER = ",";
    public static final String HYPHEN = "-";
    public static final String FILE_CELL_TYPE_SHELF = "SHELF";
    public static final String FILE_CELL_TYPE_RESTRICTED = "RESTRICTED";
    public static final String PAYSLIPS_HEADER = "employee_id,employee_name,base_pay,delivered_item_pay,hits_penalty,restricted_area_penalty,reportees_managing_pay,net_salary";
    public static final String PAYSLIP_FILE_LINE_FORMAT =
            "%s,%s,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f";

    public static final int WAREHOUSE_FIELD_COUNT = 6;
    public static final int EMPLOYEE_MIN_FIELD_COUNT = 4;
    public static final int EMPLOYEE_MAX_FIELD_COUNT = 5;
    public static final int PAYSLIP_FIELD_COUNT = 8;


    public static final int MIN_VALID_FLOOR_NUMBER = 1;
    public static final int MAX_VALID_FLOOR_NUMBER = 3;

    public static final int MIN_VALID_ITEM_INDEX = 1;

    public static final double DELIVERY_PAY = 10.00;
    public static final double WALL_HIT_PENALTY = 0.25;
    public static final double RESTRICTED_AREA_PENALTY = 0.50;
    public static final double REPORTEE_MANAGEMENT_PAY = 5.00;
    public static final double MIN_VALID_PAY_OR_PENALTY = 0.00;
}
