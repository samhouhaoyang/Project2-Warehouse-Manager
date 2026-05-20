package utils;

import employees.Employee;

/**
 * Class for user-facing messages.
 */
public final class Messages {

    private Messages() {
    }

    public static final String HYPHEN = "-";
    public static final String MENU_DASH = "—";

    /* ===== Command-line / file messages ===== */

    public static final String INVALID_ARGS_USAGE =
            "Invalid number of Command Line Arguments. Usage: java WarehouseManagerEngine <floors> <rows> <cols> <master file> <employees file>";

    public static final String INVALID_ARGS_INTEGERS =
            "Error: Floors, rows and columns must be integers.";

    public static final String INVALID_FLOORS =
            "Error: Number of floors has to be between 1 and 3.";

    public static final String INVALID_ROWS_COLS =
            "Error: Rows and columns must be at least 4 to allow proper map layout.";

    // Keep this alias only if your current WarehouseManagerEngine still uses the old constant name.
    public static final String INVALID_ROWs_COLS = INVALID_ROWS_COLS;

    public static final String FILE_PROCESSING_ERROR =
            "Unable to process file. Exiting program.";

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

    // Keep this alias only if older code still calls ITEM_PLACED.
    public static final String ITEM_PLACED = ITEM_DELIVERED;

    public static final String MUST_STAND_ON_START =
            "You must stand on the START cell (O) to deliver.";

    public static final String RESET_DONE =
            "Shift and warehouse reset.";

    /* ===== Welcome / login ===== */

    public static void printWelcome() {
        System.out.println("Welcome to Warehouse Manager Console.");
    }

    public static void printWelcomeA2() {
        System.out.println("Welcome to Warehouse Manager Assignment 2.");
        System.out.println();
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
        System.out.println();
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
        System.out.println("5. View reportees' shift summary");
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

    /* ===== Payslips ===== */

    public static void printBreakLine() {
        System.out.println("=======================");
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

}