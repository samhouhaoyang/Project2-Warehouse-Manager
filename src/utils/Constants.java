package utils;

public class Constants {
    public static final String TERMINATE = "X";
    public static final int VALID_ARGS_NUM = 5;

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

    public static final String PAYSLIPS_FILE_PATH = "data/payslips.csv";
    public static final String CSV_DELIMITER = ",";
    public static final String PAYSLIPS_HEADER = "employee_id,employee_name,base_pay,delivered_item_pay,hits_penalty,restricted_area_penalty,reportees_managing_pay,net_salary";

    public static final int WAREHOUSE_FIELD_COUNT = 6;
    public static final int EMPLOYEE_MIN_FIELD_COUNT = 4;
    public static final int EMPLOYEE_MAX_FIELD_COUNT = 5;
    public static final int PAYSLIP_FIELD_COUNT = 8;


    public static final int MIN_VALID_FLOOR_NUMBER = 1;
    public static final int MAX_VALID_FLOOR_NUMBER = 3;
}
