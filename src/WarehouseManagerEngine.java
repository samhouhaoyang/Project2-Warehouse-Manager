
import java.util.Scanner;
import employees.Employee;
import employees.PayrollManager;
import employees.Payslip;
import employees.ShiftSummary;
import enums.PayrollManagerMenuOption;

import io.EmployeeFileReader;
import io.PayslipFileReader;
import io.PayslipFileWriter;
import io.FileConstants;


import java.io.FileNotFoundException;
import java.util.ArrayList;

import static enums.PayrollManagerMenuOption.fromInput;

/**
You can modify any code in this class including the existing method signatures present.
*/
public class WarehouseManagerEngine {
    private ArrayList<Employee> employees;
    private ArrayList<Payslip> loadedPayslips;
    private ArrayList<Payslip> currentPayslips;

    private boolean hasGeneratedCurrentPayslips;
    private boolean payslipsModified;

    public WarehouseManagerEngine() {
        employees = new ArrayList<>();
        loadedPayslips = new ArrayList<>();
        currentPayslips = new ArrayList<>();
        hasGeneratedCurrentPayslips = false;
        payslipsModified = false;
    }

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        WarehouseManagerEngine engine = new WarehouseManagerEngine();
        //Step 1: Command line args
        //Validate all the cmd args except file names. if success then create the warehouse map using floor, row, cols
        // if validation fails, print message, return false and terminate.
        if(args.length < 5){
            System.out.println(Messages.INVALID_ARGS_USAGE);
            return;
        }
        String floorText = args[0];
        String rowsText = args[1];
        String columnsText = args[2];
        String warehouseMapFilePath = args[3];
        String EmployeesFilePath = args[4];


        //Step 2: File Reading, fir file not found exception print message and terminate. for all other kind skip lines
        engine.testIoPackage(args);
        engine.testManagerMenu();
        //Step 3: Main Menu Loop
        //Step 4: Write the payslips data before terminating the program.
        //engine.exitProgram();
    }

    private void testManagerMenu() {
    }

    private void testIoPackage(String[] args) {
        String employeesFilePath;

        // Temporary testing logic.
        // Later, this must be replaced by strict command-line validation.
        if (args.length >= 5) {
            employeesFilePath = args[4];
        } else {
            employeesFilePath = "data/employees_1.csv";
        }

        readEmployees(employeesFilePath);
        readPayslips();

        System.out.println("Employees loaded: " + employees.size());
        System.out.println("Payslips loaded: " + loadedPayslips.size());

        PayrollManager payrollManager = findFirstPayrollManager();

        if (payrollManager != null) {
            runPayrollManagerMenu(payrollManager);
        }
        // Only uncomment this when you intentionally want to overwrite data/payslips.csv
        //savePayslipsIfNeeded();
    }
    private PayrollManager findFirstPayrollManager() {
        for (Employee employee : employees) {
            if (employee instanceof PayrollManager) {
                return (PayrollManager) employee;
            }
        }

        return null;
    }
    private void readEmployees(String path) {
        EmployeeFileReader reader = new EmployeeFileReader();

        try {
            employees = reader.readEmployees(path);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to process file. Exiting program.");
        }
    }

    private void readPayslips() {
        PayslipFileReader reader = new PayslipFileReader();
        loadedPayslips = reader.readPayslips(FileConstants.PAYSLIPS_FILE_PATH);
    }





    private void savePayslipsIfNeeded() {
        if (!payslipsModified) {
            return;
        }

        PayslipFileWriter writer = new PayslipFileWriter();

        try {
            writer.writePayslips(currentPayslips);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to process file. Exiting program.");
        }
    }



    private void exitProgram(){
        //TODO: Write the payslips data at the end.

        System.out.println("Goodbye!");

    }

    private boolean validateArgs(String[] args) {
        //TODO: validate the args
        return true;
    }

    private void loadFiles(String[] args) {
        //TODO: load files

    }

    private void runMainMenuLoop()  {
        Messages.printWelcomeA2();
       //Run main menu loop based on employee selection
    }

    
    //TODO: Create other methods

    // payroll manager menu
    private void runPayrollManagerMenu(PayrollManager manager)  {
        boolean isRunning= true;

        /*
        menu options
        1. view all employees' shift summaries
        2. generate payslips
        3. view payslips
        4. logout: return to employee login
         */
        while(isRunning) {
            Messages.printPayrollManagerMenu(manager);
            String optionText = SCANNER.nextLine().trim();
            PayrollManagerMenuOption option = fromInput(optionText);

            switch (option) {
                case VIEW_ALL_EMPLOYEE_SHIFT -> viewAllEmployeeShiftSummary();
                case GENERATE_PAYSLIPS -> generateCurrentPayslips();
                case VIEW_ALL_PAYSLIPS ->  viewAllPayslips();
                case LOGOUT -> isRunning = false;
                case INVALID -> System.out.println("Invalid input.");
            }

        }
    }

    private void viewAllEmployeeShiftSummary() {
        for (Employee employee : employees) {
            System.out.printf(
                    "Employee Id: %s, Employee Name: %s, Designation: %s%n",
                    employee.getEmployeeId(),
                    employee.getEmployeeName(),
                    employee.getDesignation()
            );

            employee.getShiftSummary().printSummary();
            System.out.println();
        }

    }

    private void generateCurrentPayslips() {
        currentPayslips.clear();

        for (Employee employee : employees) {
            currentPayslips.add(employee.generatePayslip());
        }

        hasGeneratedCurrentPayslips = true;
        payslipsModified = true;

        Messages.printPaySlipGenerated();
    }

    private void viewAllPayslips(){
        if (hasGeneratedCurrentPayslips) {
            printPayslips(currentPayslips);
        }else if (!loadedPayslips.isEmpty()) {
            printPayslips(loadedPayslips);
        }else{
            Messages.printPaySlipNotGenerated();
        }


    }
    private void printPayslips(ArrayList<Payslip> payslips) {
        for (int i = 0; i < payslips.size(); i++) {
            payslips.get(i).printPayslip();

            if (i < payslips.size() - 1) {
                Messages.printBreakLine();
                System.out.println();
            }
        }
    }

}

