import employees.Employee;

/**
 * Class for user-facing messages.
 */
public final class Messages {

    private Messages() { }

    public static final String HYPHEN = "-";
    public static final String MENU_DASH = "—";

    public static final String INVALID_ARGS_USAGE =
            "Invalid number of Command Line Arguments. Usage: java WarehouseManagerEngine <floors> <rows> <cols> <master file> <employees file>";

    public static final String INVALID_ARGS_DIMENSIONS =
            "Error: Rows and columns must be at least 4 to allow proper map layout.";

    public static final String INVALID_INPUT =
            "Invalid input.";

    public static final String NO_SHIFT_TO_RESUME =
            "No shift to resume.";

    public static final String GOODBYE =
            "Session abandoned. Goodbye!";

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

    public static final String ITEM_PLACED =
            "Item placed successfully.";


    public static final String RESET_DONE =
            "Shift and warehouse reset.";

    public static void printWelcome() {
        System.out.println("Welcome to Warehouse Manager Console.");
    }

    public static void printLegend() {
        System.out.println("Legend: # Wall | . Aisle | X Restricted | S Shelf | O Start | F Forklift");
    }

    public static void printWelcomeA2() {
        System.out.println("Welcome to Warehouse Manager Assignment 2.");
        System.out.println();
    }

    public static void printPayrollManagerMenu(Employee manager) {
        System.out.printf(
                "=== Payroll Manager Menu " + MENU_DASH + " %s [%s] ===%n",
                manager.getEmployeeName(),
                manager.getDesignation()
        );
        System.out.println("1. View all employees' shift summary");
        System.out.println("2. Generate payslips");
        System.out.println("3. View all generated payslips");
        System.out.println("4. Logout");
        System.out.print("> ");
    }
    public static void printBreakLine(){
        System.out.println("=======================");
    }

    public static void printPaySlipNotGenerated(){
        System.out.println("Payslip not generated yet.");

    }

    public static void printPaySlipGenerated(){
        System.out.println("Payslips generated successfully.");
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

    public static void printOperatorMenu(Employee employee) {
        System.out.printf("=== Operator Menu — %s [%s] ===%n", employee.getEmployeeName(), employee.getDesignation());
        System.out.println("1. Start warehouse shift");
        System.out.println("2. Resume last shift.");
        System.out.println("3. View my shift summary");
        System.out.println("4. View my payslip");
        System.out.println("5. Logout");
        System.out.print("> ");
    }

    public static void printSupervisorMenu(Employee employee) {
        System.out.printf("=== Supervisor Menu — %s [%s] ===%n", employee.getEmployeeName(), employee.getDesignation());
        System.out.println("1. Start warehouse shift");
        System.out.println("2. Resume last shift.");
        System.out.println("3. View my shift summary");
        System.out.println("4. View my payslip");
        System.out.println("5. View reportees' shift summary");
        System.out.println("6. Logout");
        System.out.print("> ");
    }

}
