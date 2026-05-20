package utils;

import employees.Employee;

/**
 * Class for user-facing messages.
 */
public final class Messages {

    private Messages() {
    }

    public static final String HYPHEN = "-";
    public static final String MENU_DASH = "\u2014";

    /* ===== Command-line / file messages ===== */

    public static final String INVALID_ARGS_USAGE =
            "Invalid number of Command Line Arguments. Usage: java WarehouseManagerEngine <floors> <rows> <cols> <master file> <employees file>.";

    public static final String INVALID_ARGS_INTEGERS =
            "Error: Floors, rows and columns must be integers.";

    public static final String INVALID_FLOORS =
            "Error: Number of floors has to be between 1 and 3.";

    public static final String INVALID_ROWS_COLS =
            "Error: Rows and columns must be at least 4 to allow proper map layout.";

    public static final String INVALID_ROWs_COLS = INVALID_ROWS_COLS;

    public static final String FILE_PROCESSING_ERROR =
            "Unable to process file. Exiting program.";

    public static final String PROCESSING_WAREHOUSE_FILE =
            "Processing Warehouse file: %s%n";

    public static final String PROCESSING_EMPLOYEES_FILE =
            "Processing Employees file: %s%n";

    public static final String PROCESSING_PAYSLIPS_FILE =
            "Processing Payslips file: %s%n";

    public static final String PAYSLIP_FILE_DOES_NOT_EXIST =
            "Payslip file doesnt exist: %s%n";

    public static final String INCORRECT_EMPLOYEES_LINE =
            "Incorrect Employees line at line %d. Skipping this line.%n";

    public static final String INCORRECT_EMPLOYEE_DETAILS_SKIP_THE_LINE =
            "Incorrect Employee Details at line %d. Skipping this line.%n";

    public static final String INCORRECT_EMPLOYEE_DETAILS_SKIP_THIS_LINE =
            "Incorrect Employee Details at line %d. Skipping this line.";

    public static final String INCORRECT_EMPLOYEE_DESIGNATION =
            "Incorrect Employee Designation at line %d. Skipping this line.%n";

    public static final String INCORRECT_PAYSLIPS_LINE =
            "Incorrect Payslips line at line %d. Skipping this line.";

    public static final String INCORRECT_EMPLOYEE_SALARY_DETAILS =
            "Incorrect Employee Salary details at line %d. Skipping this line.";

    /* ===== General messages ===== */

    public static final String INVALID_INPUT =
            "Invalid input.";

    public static final String NO_SHIFT_TO_RESUME =
            "No shift to resume.";

    public static final String GOODBYE =
            "Goodbye!";

    public static final String SESSION_ABANDONED_GOODBYE =
            "Session abandoned. Goodbye!";

    /* ===== Warehouse shift messages ===== */

    public static final String SHIFT_COMPLETE =
            "Shift completed: all shelves visited and all items processed.";

    public static final String SESSION_PAUSED =
            "Shift paused.";

    public static final String HIT_WALL =
            "You cannot enter that area.";

    public static final String HIT_RESTRICTED =
            "You cannot enter that area.";

    public static final String NO_ITEMS_ON_SHELF =
            "No items on this shelf.";

    public static final String NOT_CARRYING =
            "You are not carrying any item.";

    public static final String ALREADY_CARRYING =
            "You are already carrying an item. Place it before picking another.";

    public static final String ENTER_ITEM_INDEX =
            "Enter item number to pick (e.g., 1): ";

    public static final String ITEM_PICKED =
            "Item picked successfully.";

    public static final String ITEM_DELIVERED =
            "Item delivered successfully.";

    public static final String ITEM_PLACED = ITEM_DELIVERED;

    public static final String MUST_STAND_ON_START =
            "You must stand on the START cell (O) to deliver.";

    public static final String RESET_DONE =
            "Shift and warehouse reset.";

    public static final String NO_REPORTEES_FOUND =
            "No reportees found.";

    public static final String FLOOR_ALL_SHELVES_EMPTY =
            "This floor has all shelves empty. Return to floor menu.";

    public static final String WAREHOUSE_ALL_SHELVES_EMPTY =
            "All shelves in the warehouse are empty and nothing to deliver. Returning to main menu.";

    public static final String NO_PAYSLIPS_TO_SAVE =
            "No payslips to save.";

    public static final String SAVING_PAYSLIPS_FILE =
            "Saving Payslips file: %s%n";

    /* ===== Welcome / login ===== */

    public static void printWelcome() {
        System.out.println("Welcome to Warehouse Manager Console.");
    }

    public static void printWelcomeA2() {
        System.out.println("Welcome to Warehouse Manager Assignment 2.");
        System.out.println();
    }

    public static void printFileProcessingError() {
        System.out.println(FILE_PROCESSING_ERROR);
    }

    public static void printProcessingWarehouseFile(String path) {
        System.out.printf(PROCESSING_WAREHOUSE_FILE, path);
    }

    public static void printProcessingEmployeesFile(String path) {
        System.out.printf(PROCESSING_EMPLOYEES_FILE, path);
    }

    public static void printProcessingPayslipsFile(String path) {
        System.out.printf(PROCESSING_PAYSLIPS_FILE, path);
    }

    public static void printPayslipFileDoesNotExist(String path) {
        System.out.printf(PAYSLIP_FILE_DOES_NOT_EXIST, path);
    }

    public static void printNoPayslipsToSave() {
        System.out.println(NO_PAYSLIPS_TO_SAVE);
    }

    public static void printSavingPayslipsFile(String path) {
        System.out.printf(SAVING_PAYSLIPS_FILE, path);
    }

    public static void printEmployeeLogin() {
        System.out.println("=== Employee Login ===");
        System.out.print("Enter your Employee ID or X to terminate: ");
    }

    public static void printEmployeeWelcome(Employee employee) {
        System.out.printf(
                "Welcome, %s [%s]%n",
                employee.getEmployeeName(),
                employee.getDesignation()
        );
        System.out.println();
    }

    public static void printEmployeeNotFound() {
        System.out.println("Employee ID not found. Please try again.");
    }

    /* ===== Menus ===== */

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

    public static void printLegend() {
        System.out.println("Legend: # Wall | . Aisle | X Restricted | S Shelf | O Start | F Forklift");
    }

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

    public static void printShelfMenu() {
        System.out.println("Shelf Menu:");
        System.out.println("Press V to view items.");
        System.out.println("Press P to pick an item.");
        System.out.println("Press Q to exit shelf menu.");
        System.out.print("> ");
    }

    public static void printFloorHeader(int floorNumber) {
        System.out.printf("==========Floor: %d==========%n", floorNumber);
    }

    public static void printForkliftPosition(int row, int col) {
        System.out.printf("Forklift at: (%d,%d)%n", row, col);
    }

    public static void printShelfItem(int userIndex, Object item) {
        System.out.printf("%d. %s%n", userIndex, item);
    }

    public static void printMapCell(char symbol) {
        System.out.print(symbol + " ");
    }

    public static void printForkliftSymbol() {
        System.out.print("F ");
    }

    /* ===== Payslips / summaries ===== */

    public static void printBreakLine() {
        System.out.println("=======================");
    }

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

    public static void printPaySlipNotGenerated() {
        System.out.println("Payslip not generated yet.");
    }

    public static void printPayslipNotFound(String employeeId) {
        System.out.println("Employee " + employeeId + "'s payslip not found.");
    }

    public static void printPaySlipGenerated() {
        System.out.println("Payslips generated successfully.");
    }

    public static final String INVALID_FLOOR_SELECTION =
            "Invalid Input";

    public static void printFloorSelectionPrompt() {
        System.out.print("Enter a floor number to navigate the warehouse or X to return to the main menu : ");
    }

    public static void printEmployeeFileInvalidLine(int lineNumber) {
        System.out.printf(INCORRECT_EMPLOYEES_LINE, lineNumber);
    }

    public static void printEmployeeFileInvalidDetails(int lineNumber) {
        System.out.printf(INCORRECT_EMPLOYEE_DETAILS_SKIP_THE_LINE, lineNumber);
    }

    public static void printEmployeeFileInvalidDesignation(int lineNumber) {
        System.out.printf(INCORRECT_EMPLOYEE_DESIGNATION, lineNumber);
    }

    public static final String SHIFT_ALREADY_IN_PROGRESS =
            "A warehouse shift is already in progress. Please resume the last shift.";
}
