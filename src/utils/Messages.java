package utils;

import employees.Employee;

/**
 * Class for user-facing messages.
 */
public final class Messages {

    private Messages() {
    }

    /** Hyphen text used in exact output and CSV validation. */
    public static final String HYPHEN = "-";

    /** Dash used in role menu headings. */
    public static final String MENU_DASH = "\u2014";

    /* ===== Command-line / file messages ===== */

    /** Error printed when the command-line argument count is wrong. */
    public static final String INVALID_ARGS_USAGE =
            "Invalid number of Command Line Arguments. Usage: "
                    + "java WarehouseManagerEngine <floors> <rows> <cols> "
                    + "<master file> <employees file>.";

    /** Error printed when numeric command-line arguments are not integers. */
    public static final String INVALID_ARGS_INTEGERS =
            "Error: Floors, rows and columns must be integers.";

    /** Error printed when the requested floor count is out of bounds. */
    public static final String INVALID_FLOORS =
            "Error: Number of floors has to be between 1 and 3.";

    /** Error printed when rows or columns are too small. */
    public static final String INVALID_ROWS_COLS =
            "Error: Rows and columns must be at least 4 to allow proper map layout.";

    /** Backwards-compatible alias for the row and column validation message. */
    public static final String INVALID_ROWs_COLS = INVALID_ROWS_COLS;

    /** Fatal file processing error message. */
    public static final String FILE_PROCESSING_ERROR =
            "Unable to process file. Exiting program.";

    /** Processing message for the warehouse file. */
    public static final String PROCESSING_WAREHOUSE_FILE =
            "Processing Warehouse file: %s%n";

    /** Processing message for the employees file. */
    public static final String PROCESSING_EMPLOYEES_FILE =
            "Processing Employees file: %s%n";

    /** Processing message for the payslips file. */
    public static final String PROCESSING_PAYSLIPS_FILE =
            "Processing Payslips file: %s%n";

    /** Message printed when the payslip file is absent. */
    public static final String PAYSLIP_FILE_DOES_NOT_EXIST =
            "Payslip file doesnt exist: %s%n";

    /** Invalid employee CSV line message. */
    public static final String INCORRECT_EMPLOYEES_LINE =
            "Incorrect Employees line at line %d. Skipping this line.%n";

    /** Invalid employee details message with trailing newline. */
    public static final String INCORRECT_EMPLOYEE_DETAILS_SKIP_THE_LINE =
            "Incorrect Employee Details at line %d. Skipping this line.%n";

    /** Invalid employee details message without embedded newline. */
    public static final String INCORRECT_EMPLOYEE_DETAILS_SKIP_THIS_LINE =
            "Incorrect Employee Details at line %d. Skipping this line.";

    /** Invalid employee designation message. */
    public static final String INCORRECT_EMPLOYEE_DESIGNATION =
            "Incorrect Employee Designation at line %d. Skipping this line.%n";

    /** Invalid payslip CSV line message. */
    public static final String INCORRECT_PAYSLIPS_LINE =
            "Incorrect Payslips line at line %d. Skipping this line.";

    /** Invalid payslip salary details message. */
    public static final String INCORRECT_EMPLOYEE_SALARY_DETAILS =
            "Incorrect Employee Salary details at line %d. Skipping this line.";

    /** Invalid warehouse CSV line message. */
    public static final String INVALID_WAREHOUSE_LINE =
            "Invalid Warehouse line at line %d. Skipping this line.";

    /** Invalid warehouse floor number message. */
    public static final String INVALID_FLOOR_NUMBER_IN_WAREHOUSE_FILE =
            "Invalid floor number in warehouse file: %d. Skipping this line.";

    /** Invalid warehouse row or column location message. */
    public static final String INVALID_LOCATION_IN_WAREHOUSE_FILE =
            "Invalid location in warehouse file at line %d. Skipping this line.";

    /** Invalid warehouse cell type message. */
    public static final String INVALID_CELL_TYPE =
            "Invalid cell type at line %d. Skipping this line.";

    /** Invalid warehouse shelf type message. */
    public static final String INVALID_SHELF_TYPE =
            "Invalid shelf type at line %d. Skipping this line.";

    /** Message for shelf and restricted cell overlap. */
    public static final String RESTRICTED_LOCATION_OVERLAPS_SHELF =
            "Restricted location overlaps shelf at line %d. Skipping this line.";

    /** Message for shelf type data on a restricted location. */
    public static final String SHELF_TYPE_CANNOT_BE_RESTRICTED_LOCATION =
            "Shelf Type cannot be defined for Restricted Location at line %d. Skipping this line.";

    /** Message for conflicting shelf types on the same location. */
    public static final String SHELF_TYPE_MISMATCHED =
            "Shelf Type mismatched at line %d. Skipping this line.";

    /* ===== General messages ===== */

    /** Generic invalid input message. */
    public static final String INVALID_INPUT =
            "Invalid input.";

    /** Message printed when resume is requested before any shift starts. */
    public static final String NO_SHIFT_TO_RESUME =
            "Shift not started, cannot resume shift.";

    /** Program termination farewell message. */
    public static final String GOODBYE =
            "Goodbye!";

    /** Message printed when input ends unexpectedly. */
    public static final String SESSION_ABANDONED_GOODBYE =
            "Session abandoned. Goodbye!";

    /* ===== Warehouse shift messages ===== */

    /** Shift completion message retained for internal compatibility. */
    public static final String SHIFT_COMPLETE =
            "Shift completed: all shelves visited and all items processed.";

    /** Message printed when a movement loop is paused. */
    public static final String SESSION_PAUSED =
            "Shift paused.";

    /** Message printed when the forklift hits a wall. */
    public static final String HIT_WALL =
            "You cannot enter that area.";

    /** Message printed when the forklift attempts a restricted cell. */
    public static final String HIT_RESTRICTED =
            "You cannot enter that area.";

    /** Message printed when a shelf has no items to show. */
    public static final String NO_ITEMS_ON_SHELF =
            "No items on this shelf.";

    /** Message printed when delivery is attempted without an item. */
    public static final String NOT_CARRYING =
            "You are not carrying any item.";

    /** Message printed when picking is attempted while carrying an item. */
    public static final String ALREADY_CARRYING =
            "You are already carrying an item. Place it before picking another.";

    /** Prompt printed before reading a shelf item number. */
    public static final String ENTER_ITEM_INDEX =
            "Enter item number to pick (e.g., 1): ";

    /** Message printed after a successful item pick. */
    public static final String ITEM_PICKED =
            "Item picked successfully.";

    /** Message printed after a successful item delivery. */
    public static final String ITEM_DELIVERED =
            "Item delivered successfully.";

    /** Compatibility alias for the item delivery message. */
    public static final String ITEM_PLACED = ITEM_DELIVERED;

    /** Message printed when delivery is attempted away from the start cell. */
    public static final String MUST_STAND_ON_START =
            "You must stand on the START cell (O) to deliver.";

    /** Message retained for reset-related compatibility. */
    public static final String RESET_DONE =
            "Shift and warehouse reset.";

    /** Message printed when a supervisor has no reportees. */
    public static final String NO_REPORTEES_FOUND =
            "No reportees found.";

    /** Message printed when one floor has all shelves empty. */
    public static final String FLOOR_ALL_SHELVES_EMPTY =
            "This floor has all shelves empty. Return to floor menu.";

    /** Message printed when all warehouse shelves are empty. */
    public static final String WAREHOUSE_ALL_SHELVES_EMPTY =
            "All shelves in the warehouse are empty and nothing to deliver. "
                    + "Returning to main menu.";

    /** Message printed when there is no payslip data to save. */
    public static final String NO_PAYSLIPS_TO_SAVE =
            "No payslips to save.";

    /** Message printed before saving payslip data. */
    public static final String SAVING_PAYSLIPS_FILE =
            "Saving Payslips file: %s%n";

    /** Message printed when no payslip has been generated. */
    public static final String PAYSLIP_NOT_GENERATED =
            "Payslip not generated yet.";

    /** Message printed when a specific employee payslip is missing. */
    public static final String PAYSLIP_NOT_FOUND =
            "Employee %s's payslip not found.";

    /* ===== Welcome / login ===== */

    /**
     * Prints the legacy welcome message.
     */
    public static void printWelcome() {
        System.out.println("Welcome to Warehouse Manager Console.");
    }

    /**
     * Prints the Assignment 2 welcome message.
     */
    public static void printWelcomeA2() {
        System.out.println("Welcome to Warehouse Manager Assignment 2.");
        System.out.println();
    }

    /**
     * Prints the fatal file processing error.
     */
    public static void printFileProcessingError() {
        System.out.println(FILE_PROCESSING_ERROR);
    }

    /**
     * Prints the warehouse file processing message.
     *
     * @param path warehouse file path
     */
    public static void printProcessingWarehouseFile(String path) {
        System.out.printf(PROCESSING_WAREHOUSE_FILE, path);
    }

    /**
     * Prints the employees file processing message.
     *
     * @param path employees file path
     */
    public static void printProcessingEmployeesFile(String path) {
        System.out.printf(PROCESSING_EMPLOYEES_FILE, path);
    }

    /**
     * Prints the payslips file processing message.
     *
     * @param path payslips file path
     */
    public static void printProcessingPayslipsFile(String path) {
        System.out.printf(PROCESSING_PAYSLIPS_FILE, path);
    }

    /**
     * Prints the missing payslip file message.
     *
     * @param path payslips file path
     */
    public static void printPayslipFileDoesNotExist(String path) {
        System.out.printf(PAYSLIP_FILE_DOES_NOT_EXIST, path);
    }

    /**
     * Prints the message used when no payslip data is available to save.
     */
    public static void printNoPayslipsToSave() {
        System.out.println(NO_PAYSLIPS_TO_SAVE);
    }

    /**
     * Prints the payslip saving message.
     *
     * @param path payslips file path
     */
    public static void printSavingPayslipsFile(String path) {
        System.out.printf(SAVING_PAYSLIPS_FILE, path);
    }

    /**
     * Formats the message for a missing employee payslip.
     *
     * @param employeeId employee id
     * @return formatted missing payslip message
     */
    public static String formatPayslipNotFound(String employeeId) {
        return String.format(PAYSLIP_NOT_FOUND, employeeId);
    }

    /**
     * Formats an invalid warehouse line message.
     *
     * @param lineNumber file line number
     * @return formatted message
     */
    public static String formatInvalidWarehouseLine(int lineNumber) {
        return String.format(INVALID_WAREHOUSE_LINE, lineNumber);
    }

    /**
     * Formats an invalid warehouse floor number message.
     *
     * @param lineNumber invalid floor number value
     * @return formatted message
     */
    public static String formatInvalidFloorNumberInWarehouseFile(int lineNumber) {
        return String.format(INVALID_FLOOR_NUMBER_IN_WAREHOUSE_FILE, lineNumber);
    }

    /**
     * Formats an invalid warehouse location message.
     *
     * @param lineNumber file line number
     * @return formatted message
     */
    public static String formatInvalidLocationInWarehouseFile(int lineNumber) {
        return String.format(INVALID_LOCATION_IN_WAREHOUSE_FILE, lineNumber);
    }

    /**
     * Formats an invalid warehouse cell type message.
     *
     * @param lineNumber file line number
     * @return formatted message
     */
    public static String formatInvalidCellType(int lineNumber) {
        return String.format(INVALID_CELL_TYPE, lineNumber);
    }

    /**
     * Formats an invalid shelf type message.
     *
     * @param lineNumber file line number
     * @return formatted message
     */
    public static String formatInvalidShelfType(int lineNumber) {
        return String.format(INVALID_SHELF_TYPE, lineNumber);
    }

    /**
     * Formats the shelf and restricted overlap message.
     *
     * @param lineNumber file line number
     * @return formatted message
     */
    public static String formatRestrictedLocationOverlapsShelf(int lineNumber) {
        return String.format(RESTRICTED_LOCATION_OVERLAPS_SHELF, lineNumber);
    }

    /**
     * Formats the restricted location shelf type message.
     *
     * @param lineNumber file line number
     * @return formatted message
     */
    public static String formatShelfTypeCannotBeRestrictedLocation(int lineNumber) {
        return String.format(SHELF_TYPE_CANNOT_BE_RESTRICTED_LOCATION, lineNumber);
    }

    /**
     * Formats the shelf type mismatch message.
     *
     * @param lineNumber file line number
     * @return formatted message
     */
    public static String formatShelfTypeMismatched(int lineNumber) {
        return String.format(SHELF_TYPE_MISMATCHED, lineNumber);
    }

    /**
     * Prints the employee login prompt.
     */
    public static void printEmployeeLogin() {
        System.out.println("=== Employee Login ===");
        System.out.print("Enter your Employee ID or X to terminate: ");
    }

    /**
     * Prints the employee welcome message.
     *
     * @param employee logged-in employee
     */
    public static void printEmployeeWelcome(Employee employee) {
        System.out.printf(
                "Welcome, %s [%s]%n",
                employee.getEmployeeName(),
                employee.getDesignation()
        );
        System.out.println();
    }

    /**
     * Prints the invalid employee id message.
     */
    public static void printEmployeeNotFound() {
        System.out.println("Employee ID not found. Please try again.");
    }

    /* ===== Menus ===== */

    /**
     * Prints the operator menu.
     *
     * @param employee current employee
     */
    public static void printOperatorMenu(Employee employee) {
        System.out.printf(
                "=== Operator Menu %s %s [%s] ===%n",
                MENU_DASH,
                employee.getEmployeeName(),
                employee.getDesignation()
        );
        System.out.println("1. Start warehouse shift");
        System.out.println("2. Resume last shift");
        System.out.println("3. View my shift summary");
        System.out.println("4. View my payslip");
        System.out.println("5. Logout");
        System.out.print("> ");
    }

    /**
     * Prints the supervisor menu.
     *
     * @param employee current supervisor
     */
    public static void printSupervisorMenu(Employee employee) {
        System.out.printf(
                "=== Supervisor Menu %s %s [%s] ===%n",
                MENU_DASH,
                employee.getEmployeeName(),
                employee.getDesignation()
        );
        System.out.println("1. Start warehouse shift");
        System.out.println("2. Resume last shift");
        System.out.println("3. View my shift summary");
        System.out.println("4. View my payslip");
        System.out.println("5. View all reportees' shift summary");
        System.out.println("6. Logout");
        System.out.print("> ");
    }

    /**
     * Prints the payroll manager menu.
     *
     * @param manager current payroll manager
     */
    public static void printPayrollManagerMenu(Employee manager) {
        System.out.printf(
                "=== Payroll Manager Menu %s %s [%s] ===%n",
                MENU_DASH,
                manager.getEmployeeName(),
                manager.getDesignation()
        );
        System.out.println("1. View all employees' shift summary");
        System.out.println("2. Generate payslips");
        System.out.println("3. View all generated payslips");
        System.out.println("4. Logout");
        System.out.print("> ");
    }

    /* ===== Warehouse map / movement ===== */

    /**
     * Prints the warehouse map legend.
     */
    public static void printLegend() {
        System.out.println(
                "Legend: # Wall | . Aisle | X Restricted | S Shelf | O Start | F Forklift"
        );
    }

    /**
     * Prints the movement menu options.
     */
    public static void printMovementOptions() {
        System.out.println("Enter direction:");
        System.out.println("U - Up.");
        System.out.println("D - Down.");
        System.out.println("L - Left.");
        System.out.println("R - Right.");
        System.out.println("T - Deliver carried item at START (O).");
        System.out.println("Q - Quit to main menu.");
        System.out.print("> ");
    }

    /**
     * Prints the shelf submenu.
     */
    public static void printShelfMenu() {
        System.out.println("Shelf Menu:");
        System.out.println("Press V to view items.");
        System.out.println("Press P to pick an item.");
        System.out.println("Press Q to exit shelf menu.");
        System.out.print("> ");
    }

    /**
     * Prints a floor heading.
     *
     * @param floorNumber displayed floor number
     */
    public static void printFloorHeader(int floorNumber) {
        System.out.printf("==========Floor: %d==========%n", floorNumber);
    }

    /**
     * Prints the forklift position.
     *
     * @param row forklift row
     * @param col forklift column
     */
    public static void printForkliftPosition(int row, int col) {
        System.out.printf("Forklift at: (%d,%d)%n", row, col);
    }

    /**
     * Prints a numbered shelf item.
     *
     * @param userIndex one-based item number
     * @param item item to display
     */
    public static void printShelfItem(int userIndex, Object item) {
        System.out.printf("%d. %s%n", userIndex, item);
    }

    /**
     * Prints one warehouse map cell.
     *
     * @param symbol cell symbol
     */
    public static void printMapCell(char symbol) {
        System.out.print(symbol + " ");
    }

    /**
     * Prints the forklift map symbol.
     */
    public static void printForkliftSymbol() {
        System.out.print("F ");
    }

    /* ===== Payslips / summaries ===== */

    /**
     * Prints the payslip separator line.
     */
    public static void printBreakLine() {
        System.out.println("=======================");
    }

    /**
     * Prints a shift summary.
     *
     * @param itemsDelivered delivered item count
     * @param wallHits wall hit count
     * @param restrictedAreaHits restricted-area hit count
     */
    public static void printShiftSummary(int itemsDelivered, int wallHits,
                                         int restrictedAreaHits) {
        System.out.printf(
                "No. of Items delivered: %d%n"
                        + "No. of Walls hit: %d%n"
                        + "No. of Restricted Areas hit: %d%n",
                itemsDelivered,
                wallHits,
                restrictedAreaHits
        );
    }

    /**
     * Prints a payslip.
     *
     * @param employeeId employee id
     * @param employeeName employee name
     * @param baseSalary base salary
     * @param deliveredPay delivered item pay
     * @param hitPenalty wall hit penalty
     * @param restrictedPenalty restricted-area penalty
     * @param reporteePay reportee management pay
     * @param netSalary net salary
     */
    public static void printPayslip(String employeeId, String employeeName,
                                    double baseSalary, double deliveredPay,
                                    double hitPenalty, double restrictedPenalty,
                                    double reporteePay, double netSalary) {
        System.out.printf("EmployeeID: %s%n", employeeId);
        System.out.printf("Employee Name: %s%n", employeeName);
        System.out.printf("Base salary: %.2f%n", baseSalary);
        System.out.printf("Delivered Item Pay: %.2f%n", deliveredPay);
        System.out.printf("Hits Penalty: %.2f%n", hitPenalty);
        System.out.printf("Restricted Penalty: %.2f%n", restrictedPenalty);
        System.out.printf("Reportees Management Pay: %.2f%n", reporteePay);
        System.out.printf("Net Salary: %.2f%n", netSalary);
    }

    /**
     * Prints the header shown before an employee shift summary.
     *
     * @param employeeId employee id
     * @param employeeName employee name
     * @param designation employee designation
     */
    public static void printEmployeeSummaryHeader(String employeeId,
                                                  String employeeName,
                                                  Object designation) {
        System.out.printf(
                "Employee Id: %s, Employee Name: %s, Designation: %s%n",
                employeeId,
                employeeName,
                designation
        );
    }

    /**
     * Prints the message for unavailable generated payslips.
     */
    public static void printPaySlipNotGenerated() {
        System.out.println(PAYSLIP_NOT_GENERATED);
    }

    /**
     * Prints the message for a missing employee payslip.
     *
     * @param employeeId employee id
     */
    public static void printPayslipNotFound(String employeeId) {
        System.out.println(formatPayslipNotFound(employeeId));
    }

    /**
     * Prints the successful payslip generation message.
     */
    public static void printPaySlipGenerated() {
        System.out.println("Payslips generated successfully.");
    }

    /** Invalid floor selection message. */
    public static final String INVALID_FLOOR_SELECTION =
            "Invalid Input";

    /**
     * Prints the floor selection prompt.
     */
    public static void printFloorSelectionPrompt() {
        System.out.print(
                "Enter a floor number to navigate the warehouse or X to return to the main menu : "
        );
    }

    /**
     * Prints an invalid employee file line message.
     *
     * @param lineNumber file line number
     */
    public static void printEmployeeFileInvalidLine(int lineNumber) {
        System.out.printf(INCORRECT_EMPLOYEES_LINE, lineNumber);
    }

    /**
     * Prints an invalid employee details message.
     *
     * @param lineNumber file line number
     */
    public static void printEmployeeFileInvalidDetails(int lineNumber) {
        System.out.printf(INCORRECT_EMPLOYEE_DETAILS_SKIP_THE_LINE, lineNumber);
    }

    /**
     * Prints an invalid employee designation message.
     *
     * @param lineNumber file line number
     */
    public static void printEmployeeFileInvalidDesignation(int lineNumber) {
        System.out.printf(INCORRECT_EMPLOYEE_DESIGNATION, lineNumber);
    }

    /** Message printed when start shift is selected during a paused shift. */
    public static final String SHIFT_ALREADY_IN_PROGRESS =
            "A warehouse shift is already in progress. Please resume the last shift.";
}
