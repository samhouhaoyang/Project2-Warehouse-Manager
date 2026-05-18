
import java.io.File;
import java.util.Scanner;

import employees.*;
import enums.OperatorMenuOption;
import enums.PayrollManagerMenuOption;

import enums.SupervisorMenuOption;
import io.EmployeeFileReader;
import io.PayslipFileReader;
import io.PayslipFileWriter;

import io.WarehouseFileReader;
import utils.Constants;
import utils.Messages;
import warehouse.WarehouseMap;


import java.io.FileNotFoundException;
import java.util.ArrayList;

import static enums.PayrollManagerMenuOption.fromInput;

/**
You can modify any code in this class including the existing method signatures present.
*/
public class WarehouseManagerEngine {
    private ArrayList<Employee> employees;
    private ArrayList<Payslip> loadedPayslips;
    private final ArrayList<Payslip> currentPayslips;
    private boolean hasGeneratedCurrentPayslips;
    private boolean payslipsModified;

    private int floors;
    private int rows;
    private int columns;
    private String warehouseMapFilePath;
    private String employeesFilePath;

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

        if (!engine.validateArgs(args)) {
            return;
        }

        engine.loadFiles();
        engine.runMainMenuLoop();
        engine.exitProgram();
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
        loadedPayslips = reader.readPayslips(Constants.PAYSLIPS_FILE_PATH);
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
        savePayslipsIfNeeded();
        System.out.println("Goodbye!");

    }

    private boolean validateArgs(String[] args) {
        //TODO: validate the args
        int VALID_ARGS_NUM = 5;
        if (args.length < VALID_ARGS_NUM) {
            System.out.println(Messages.INVALID_ARGS_USAGE);
            return false;
        }

        try {
            floors = Integer.parseInt(args[0]);
            rows = Integer.parseInt(args[1]);
            columns = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println(Messages.INVALID_ARGS_INTEGERS);
            return false;
        }


        if (floors < Constants.MIN_VALID_FLOOR_NUMBER || floors > Constants.MAX_VALID_FLOOR_NUMBER) {
            System.out.println(Messages.INVALID_FLOORS);
            return false;
        }

        int MIN_COLS_OR_ROWS = 4;
        if (rows < MIN_COLS_OR_ROWS || columns < MIN_COLS_OR_ROWS){
            System.out.println(Messages.INVALID_ROWs_COLS);
            return false;
        }

        warehouseMapFilePath = args[3];
        employeesFilePath = args[4];

        if(!new File(warehouseMapFilePath).exists() || !new File(employeesFilePath).exists()) {
            System.out.println(Messages.FILE_PROCESSING_ERROR);
            return false;
        }

        return true;
    }



    private void loadFiles() {
        WarehouseMap warehouseMap = new WarehouseMap(floors, rows, columns);
        readWarehouseMap(warehouseMap);
        // below two line for testing only
        Messages.printLegend();
        warehouseMap.printMap();


        readEmployees(employeesFilePath);
        readPayslips();
    }

    private void readWarehouseMap(WarehouseMap warehouseMap) {
        WarehouseFileReader reader = new WarehouseFileReader();

        try {
            reader.readWarehouseFile(warehouseMapFilePath, warehouseMap);
        } catch (FileNotFoundException e) {
            System.out.println(Messages.FILE_PROCESSING_ERROR);
        }
    }

    private void runMainMenuLoop()  {
        Messages.printWelcomeA2();
       //Run main menu loop based on employee selection

        boolean isRunning = true;

        while (isRunning) {
            Messages.printEmployeeLogin();

            String input = SCANNER.nextLine().trim();


            if(input.equalsIgnoreCase(Constants.TERMINATE)){
                isRunning = false;
            }else{
                Employee currEmployee = findEmployeeById(input);

                if(findEmployeeById(input) == null){
                    Messages.printEmployeeNotFound();

                }else{
                    Messages.printEmployeeWelcome(currEmployee);

                    switch (currEmployee.getDesignation()){
                        case OPERATOR, SENIOR_OPERATOR -> runOperatorMenu(currEmployee);
                        case PAYROLL_MANAGER -> runPayrollManagerMenu((PayrollManager) currEmployee);
                        case SUPERVISOR -> runSupervisorMenu((Supervisor)currEmployee);
                        default -> System.out.println(Messages.INVALID_INPUT);
                    }
                }

            }

            if (isRunning) {
                System.out.println();
            }

        }
    }


    //TODO: Create other methods
    private void runSupervisorMenu(Supervisor supervisor){
        boolean isRunning = true;
        while(isRunning) {
            Messages.printSupervisorMenu(supervisor);

            String input = SCANNER.nextLine().trim();
            SupervisorMenuOption option = SupervisorMenuOption.fromInput(input);

            switch(option) {
                // TODO: connect with warehouseshifts from project 1
                case START_SHIFT -> startWarehouseShift(supervisor); // temporary stub
                case RESUME_SHIFT -> resumeWarehouseShift(supervisor); // temporary stub

                case VIEW_SHIFT_SUMMARY -> supervisor.getShiftSummary().printSummary();
                case VIEW_PAYSLIP -> viewOwnPayslip(supervisor);
                case VIEW_REPORTEE_SHIFT_SUMMARY -> viewReporteesShift(supervisor);
                case LOGOUT -> isRunning = false;
                case INVALID -> System.out.println(Messages.INVALID_INPUT);

            }

            if (isRunning) {
                System.out.println();
            }
        }
    }
    private void runOperatorMenu(Employee employee) {
        boolean isRunning = true;

        while(isRunning) {
            Messages.printOperatorMenu(employee);

            String input = SCANNER.nextLine().trim();
            OperatorMenuOption option = OperatorMenuOption.fromInput(input);

            switch(option) {
                // TODO: connect with warehouseshifts from project 1
                case START_SHIFT -> startWarehouseShift(employee); // temporary stub
                case RESUME_SHIFT -> resumeWarehouseShift(employee); // temporary stub

                case VIEW_SHIFT_SUMMARY -> employee.getShiftSummary().printSummary();
                case VIEW_PAYSLIP -> viewOwnPayslip(employee);
                case LOGOUT -> isRunning = false;
                case INVALID -> System.out.println(Messages.INVALID_INPUT);

            }

            if (isRunning) {
                System.out.println();
            }
        }

    }
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
                case INVALID -> System.out.println(Messages.INVALID_INPUT);
            }

            if (isRunning) {
                System.out.println();
            }
        }
    }

    private void startWarehouseShift(Employee employee) {
        System.out.println("Start warehouse shift not implemented yet.");
    }

    private void resumeWarehouseShift(Employee employee) {
        System.out.println("Resume warehouse shift not implemented yet.");
    }
    private void viewOwnPayslip(Employee employee) {
        ArrayList<Payslip> payslipSource;

        if (hasGeneratedCurrentPayslips) {
            payslipSource = currentPayslips;
        } else {
            payslipSource = loadedPayslips;
        }

        if (payslipSource.isEmpty()) {
            Messages.printPaySlipNotGenerated();
            return;
        }
        Payslip payslip = findPayslipByEmployeeId(payslipSource, employee.getEmployeeId());

        if (payslip == null) {
            Messages.printPaySlipNotGenerated();
            return;
        }

        employee.viewPayslip(payslip);
    }

    private Payslip findPayslipByEmployeeId(ArrayList<Payslip> payslips, String employeeId) {
        for (Payslip payslip : payslips) {
            if (payslip.getEmployeeId().equals(employeeId)) {
                return payslip;
            }
        }

        return null;
    }

    private void viewReporteesShift(Supervisor supervisor) {
        ArrayList<Employee> reportees;
        reportees = supervisor.getReportees();

        if (reportees.isEmpty()) {
            System.out.println("No reportees found.");
            return;
        }

        for (Employee employee : reportees) {
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

    private Employee findEmployeeById(String employeeId) {
        for (Employee employee : employees) {
            if (employee.getEmployeeId().equals(employeeId)) {
                return employee;
            }
        }

        return null;
    }
}

