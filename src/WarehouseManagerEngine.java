
import java.util.Scanner;
import employees.Employee;
import employees.Payslip;
import io.EmployeeFileReader;
import io.PayslipFileReader;
import io.PayslipFileWriter;
import io.FileConstants;

import java.io.FileNotFoundException;
import java.util.ArrayList;
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

        //Step 2: File Reading, fir file not found exception print message and terminate. for all other kind skip lines
        engine.testIoPackage(args);
        //Step 3: Main Menu Loop
        //Step 4: Write the payslips data before terminating the program.
        //engine.exitProgram();
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

        generateCurrentPayslips();

        System.out.println();
        System.out.println("Generated payslips:");
        printPayslips(currentPayslips);

        // Only uncomment this when you intentionally want to overwrite data/payslips.csv
        //savePayslipsIfNeeded();
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

    private void generateCurrentPayslips() {
        currentPayslips.clear();

        for (Employee employee : employees) {
            currentPayslips.add(employee.generatePayslip());
        }

        hasGeneratedCurrentPayslips = true;
        payslipsModified = true;
    }

    private void printPayslips(ArrayList<Payslip> payslips) {
        if (payslips.isEmpty()) {
            System.out.println("Payslip not generated yet.");
            return;
        }

        for (int i = 0; i < payslips.size(); i++) {
            payslips.get(i).printPayslip();

            if (i < payslips.size() - 1) {
                System.out.println("=======================");
                System.out.println();
            }
        }
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
}

