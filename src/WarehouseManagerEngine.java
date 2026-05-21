
import java.io.File;
import java.util.Scanner;

import employees.*;
import enums.*;
import exceptions.NotFoundException;

import io.EmployeeFileReader;
import io.PayslipFileReader;
import io.PayslipFileWriter;

import io.WarehouseFileReader;
import utils.Constants;
import utils.Messages;
import warehouse.Item;
import warehouse.WarehouseFloor;
import warehouse.WarehouseMap;


import java.io.FileNotFoundException;
import java.util.ArrayList;

import static enums.PayrollManagerMenuOption.fromInput;

public class WarehouseManagerEngine {
    private ArrayList<Employee> employees;
    private ArrayList<Payslip> loadedPayslips;
    private final ArrayList<Payslip> currentPayslips;
    private boolean hasGeneratedCurrentPayslips;
    private boolean payslipsModified;
    private boolean payslipFileExists;
    private String payslipHeader;

    private WarehouseMap warehouseMap;

    private int floors;
    private int rows;
    private int columns;
    private String warehouseMapFilePath;
    private String employeesFilePath;

    private boolean hasPausedShift;
    private boolean shiftCompleted;

    public WarehouseManagerEngine() {
        employees = new ArrayList<>();
        loadedPayslips = new ArrayList<>();
        currentPayslips = new ArrayList<>();
        hasGeneratedCurrentPayslips = false;
        payslipsModified = false;
        payslipFileExists = false;
        payslipHeader = Constants.PAYSLIPS_HEADER;
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
            Messages.printFileProcessingError();
        }
    }

    private void readPayslips() {
        PayslipFileReader reader = new PayslipFileReader();
        payslipFileExists = new File(Constants.PAYSLIPS_FILE_PATH).exists();
        loadedPayslips = reader.readPayslips(Constants.PAYSLIPS_FILE_PATH);
        payslipHeader = reader.getHeader();
    }





    private void savePayslipsIfNeeded() {
        ArrayList<Payslip> payslipsToSave;

        if (hasGeneratedCurrentPayslips) {
            payslipsToSave = currentPayslips;
        } else {
            payslipsToSave = loadedPayslips;
        }

        payslipsToSave = filterPayslipsForCurrentEmployees(payslipsToSave);

        if (payslipsToSave.isEmpty()) {
            Messages.printNoPayslipsToSave();
            return;
        }

        PayslipFileWriter writer = new PayslipFileWriter();

        try {
            Messages.printSavingPayslipsFile(Constants.PAYSLIPS_FILE_PATH);
            writer.writePayslips(payslipsToSave, payslipHeader);
        } catch (FileNotFoundException e) {
            Messages.printFileProcessingError();
        }
    }



    private void exitProgram(){
        savePayslipsIfNeeded();
        System.out.println(Messages.GOODBYE);

    }

    private boolean validateArgs(String[] args) {
        if (args.length < Constants.VALID_ARGS_NUM) {
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

        if (rows < Constants.MIN_VALID_ROWS_OR_COLS
                || columns < Constants.MIN_VALID_ROWS_OR_COLS){
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
        warehouseMap = new WarehouseMap(floors, rows, columns);
        Messages.printProcessingWarehouseFile(warehouseMapFilePath);
        readWarehouseMap(warehouseMap);
        warehouseMap.markStartCells();

        Messages.printProcessingEmployeesFile(employeesFilePath);
        readEmployees(employeesFilePath);
        Messages.printProcessingPayslipsFile(Constants.PAYSLIPS_FILE_PATH);
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

        boolean isRunning = true;

        while (isRunning) {
            Messages.printEmployeeLogin();

            String input = SCANNER.nextLine().trim();


            if(input.equalsIgnoreCase(Constants.TERMINATE)){
                isRunning = false;
            }else{
                Employee currEmployee = findEmployeeById(input);

                if(currEmployee == null){
                    Messages.printEmployeeNotFound();


                }else{
                    System.out.println();
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


    private void runSupervisorMenu(Supervisor supervisor){
        boolean isRunning = true;
        while(isRunning) {
            Messages.printSupervisorMenu(supervisor);

            String input = SCANNER.nextLine().trim();
            SupervisorMenuOption option = SupervisorMenuOption.fromInput(input);

            switch(option) {
                case START_SHIFT -> startWarehouseShift(supervisor);
                case RESUME_SHIFT -> resumeWarehouseShift(supervisor);

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
                case START_SHIFT -> startWarehouseShift(employee);
                case RESUME_SHIFT -> resumeWarehouseShift(employee);

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
                case VIEW_ALL_EMPLOYEE_SHIFT -> {
                    System.out.println();
                    viewAllEmployeeShiftSummary();
                }
                case GENERATE_PAYSLIPS -> generateCurrentPayslips();
                case VIEW_ALL_PAYSLIPS -> {
                    viewAllPayslips();
                }
                case LOGOUT -> isRunning = false;
                case INVALID -> System.out.println(Messages.INVALID_INPUT);
            }

            if (isRunning) {
                System.out.println();
            }
        }
    }

    private void startWarehouseShift(Employee employee) {
        if (hasPausedShift) {
            shiftCompleted = false;
            runWarehouseShift(employee);
            return;
        }

        resetAllForklifts();

        hasPausedShift = false;
        shiftCompleted = false;

        if (stopIfNoDeliverableWork()) {
            return;
        }

        runWarehouseShift(employee);
    }

    private void resumeWarehouseShift(Employee employee) {
        if (stopIfNoDeliverableWork()) {
            return;
        }

        if (!hasPausedShift) {
            System.out.println(Messages.NO_SHIFT_TO_RESUME);
            return;
        }

        shiftCompleted = false;
        runWarehouseShift(employee);
    }

    private boolean stopIfNoDeliverableWork() {
        if (warehouseMap.areAllShelfItemsEmpty()
                && !warehouseMap.isAnyForkliftCarrying()) {
            System.out.println(Messages.WAREHOUSE_ALL_SHELVES_EMPTY);
            shiftCompleted = true;
            hasPausedShift = false;
            return true;
        }

        return false;
    }

    private void runWarehouseShift(Employee employee) {
        boolean selectingFloor = true;

        while (selectingFloor) {
            Messages.printLegend();
            warehouseMap.printMap();

            Messages.printFloorSelectionPrompt();
            String input = SCANNER.nextLine().trim();

            if (input.equalsIgnoreCase(Constants.TERMINATE)) {
                hasPausedShift = !this.shiftCompleted;
                selectingFloor = false;
            } else {
                try {
                    int selectedFloorNumber = Integer.parseInt(input);

                    if (!warehouseMap.isValidFloorNumber(selectedFloorNumber)) {
                        System.out.println(Messages.INVALID_FLOOR_SELECTION);
                    } else {
                        WarehouseFloor selectedFloor =
                                warehouseMap.getFloorByNumber(selectedFloorNumber);

                        boolean completedNow =
                                runFloorMovementLoop(employee, selectedFloor);

                        if (completedNow) {
                            hasPausedShift = false;
                            selectingFloor = false;
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println(Messages.INVALID_FLOOR_SELECTION);
                }
            }
        }
    }

    private boolean runFloorMovementLoop(Employee employee, WarehouseFloor currentFloor) {
        boolean isInFloor = true;
        boolean completedNow = false;

        while (isInFloor && !completedNow) {
            currentFloor.printFloorWithoutHeader();

            Messages.printMovementOptions();

            String input = SCANNER.nextLine();
            Direction direction = Direction.fromInput(input);

            switch (direction) {
                case DELIVER -> deliverItemAtStart(employee, currentFloor);

                case QUIT -> {
                    System.out.println(Messages.SESSION_PAUSED);
                    currentFloor.pauseForkliftSession();
                    isInFloor = false;
                }

                case UP, DOWN, LEFT, RIGHT -> {
                    MovementResult result = currentFloor.moveForklift(direction);
                    handleMovementResult(employee, currentFloor, result);
                }

                case INVALID -> System.out.println(Messages.INVALID_INPUT);
            }

            if (isFloorComplete(currentFloor)) {
                System.out.println(Messages.FLOOR_ALL_SHELVES_EMPTY);
                isInFloor = false;
            }

            if (isShiftComplete()) {
                System.out.println(Messages.WAREHOUSE_ALL_SHELVES_EMPTY);
                completedNow = true;
                this.shiftCompleted = true;
                hasPausedShift = false;
            }
        }

        return completedNow;
    }


    private void handleMovementResult(Employee employee, WarehouseFloor floor,
                                      MovementResult result) {
        switch (result) {
            case MOVED -> handlePostMoveCell(employee, floor);

            case WALL_HIT -> {
                System.out.println(Messages.HIT_WALL);
                employee.getShiftSummary().updateWallHits();
            }

            case RESTRICTED_HIT -> {
                System.out.println(Messages.HIT_RESTRICTED);
                employee.getShiftSummary().updateRestrictedAreaHits();
            }

            case INVALID_INPUT -> System.out.println(Messages.INVALID_INPUT);
        }
    }
    private void handlePostMoveCell(Employee employee, WarehouseFloor floor) {
        int forkliftRow = floor.getForkliftRow();
        int forkliftCol = floor.getForkliftCol();

        if (floor.isShelfAt(forkliftRow, forkliftCol)) {
            floor.markShelfVisitedAt(forkliftRow, forkliftCol);
            floor.printFloorWithoutHeader();
            runShelfMenu(employee, floor);
        }
    }

    private void resetAllForklifts() {
        for (int floorNumber = 1; floorNumber <= warehouseMap.getFloorCount(); floorNumber++) {
            warehouseMap.getFloorByNumber(floorNumber).resetForklift();
        }
    }
    private void viewOwnPayslip(Employee employee) {
        try {
            Payslip payslip = getPayslipForEmployee(employee);
            System.out.println();
            employee.viewPayslip(payslip);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private Payslip getPayslipForEmployee(Employee employee) throws NotFoundException {
        ArrayList<Payslip> payslipSource;

        if (hasGeneratedCurrentPayslips) {
            payslipSource = currentPayslips;
        } else {
            payslipSource = loadedPayslips;
        }

        if (payslipSource.isEmpty()) {
            if (!hasGeneratedCurrentPayslips && payslipFileExists) {
                throw new NotFoundException(
                        Messages.formatPayslipNotFound(employee.getEmployeeId())
                );
            }

            throw new NotFoundException(Messages.PAYSLIP_NOT_GENERATED);
        }
        Payslip payslip = findPayslipByEmployeeId(payslipSource, employee.getEmployeeId());

        if (payslip == null) {
            throw new NotFoundException(
                    Messages.formatPayslipNotFound(employee.getEmployeeId())
            );
        }

        return payslip;
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
            System.out.println(Messages.NO_REPORTEES_FOUND);
            return;
        }

        System.out.println();

        for (int i = 0; i < reportees.size(); i++) {
            Employee employee = reportees.get(i);
            Messages.printEmployeeSummaryHeader(
                    employee.getEmployeeId(),
                    employee.getEmployeeName(),
                    employee.getDesignation()
            );

            employee.getShiftSummary().printSummary();

            if (i < reportees.size() - 1) {
                System.out.println();
            }
        }

    }
    private void viewAllEmployeeShiftSummary() {
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            Messages.printEmployeeSummaryHeader(
                    employee.getEmployeeId(),
                    employee.getEmployeeName(),
                    employee.getDesignation()
            );

            employee.getShiftSummary().printSummary();

            if (i < employees.size() - 1) {
                System.out.println();
            }
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
        ArrayList<Payslip> payslipsToPrint;

        if (hasGeneratedCurrentPayslips) {
            payslipsToPrint = filterPayslipsForCurrentEmployees(currentPayslips);
        }else if (!loadedPayslips.isEmpty()) {
            payslipsToPrint = filterPayslipsForCurrentEmployees(loadedPayslips);
        }else{
            Messages.printPaySlipNotGenerated();
            return;
        }

        if (payslipsToPrint.isEmpty()) {
            Messages.printPaySlipNotGenerated();
            return;
        }

        System.out.println();
        printPayslips(payslipsToPrint);
    }

    private ArrayList<Payslip> filterPayslipsForCurrentEmployees(ArrayList<Payslip> payslips) {
        ArrayList<Payslip> filteredPayslips = new ArrayList<>();

        for (Employee employee : employees) {
            Payslip payslip = findPayslipByEmployeeId(payslips, employee.getEmployeeId());

            if (payslip != null) {
                filteredPayslips.add(payslip);
            }
        }

        return filteredPayslips;
    }

    private void printPayslips(ArrayList<Payslip> payslips) {
        for (int i = 0; i < payslips.size(); i++) {
            payslips.get(i).printPayslip();
            Messages.printBreakLine();

            if (i < payslips.size() - 1) {
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


    private void runShelfMenu(Employee employee, WarehouseFloor floor) {
        boolean inShelfMenu = true;

        while (inShelfMenu) {
            Messages.printShelfMenu();

            String input = SCANNER.nextLine();
            ShelfMenuOption option = ShelfMenuOption.fromInput(input);

            switch (option) {
                case VIEW_ITEMS -> viewCurrentShelfItems(floor);

                case PICK_ITEM -> pickItemFromCurrentShelf(floor);

                case QUIT -> inShelfMenu = false;

                case INVALID -> System.out.println(Messages.INVALID_INPUT);
            }

            if (inShelfMenu
                    && option != ShelfMenuOption.VIEW_ITEMS
                    && option != ShelfMenuOption.PICK_ITEM
                    && option != ShelfMenuOption.INVALID) {
                System.out.println();
            }
        }
    }

    private void viewCurrentShelfItems(WarehouseFloor floor) {
        floor.printShelfItemsAt(floor.getForkliftRow(), floor.getForkliftCol());
    }

    private void pickItemFromCurrentShelf(WarehouseFloor floor) {
        if (floor.isForkliftCarrying()) {
            System.out.println(Messages.ALREADY_CARRYING);
            return;
        }

        System.out.print(Messages.ENTER_ITEM_INDEX);

        int itemIndex;

        try {
            itemIndex = Integer.parseInt(SCANNER.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(Messages.INVALID_INPUT);
            return;
        }

        if (itemIndex < Constants.MIN_VALID_ITEM_INDEX) {
            System.out.println(Messages.INVALID_INPUT);
            return;
        }

        Item pickedItem = floor.removeItemFromShelfAt(
                floor.getForkliftRow(),
                floor.getForkliftCol(),
                itemIndex
        );

        if (pickedItem == null) {
            System.out.println(Messages.INVALID_INPUT);
            return;
        }

        floor.pickUpWithForklift(pickedItem);
        System.out.println(Messages.ITEM_PICKED);
    }

    /**
     * Delivers the item currently carried by the forklift.
     * The item can only be delivered when the forklift is at the START cell.
     *
     * @param employee the employee currently operating the forklift
     * @param floor the current warehouse floor
     */
    private void deliverItemAtStart(Employee employee, WarehouseFloor floor) {
        if (!floor.isForkliftCarrying()) {
            System.out.println(Messages.NOT_CARRYING);
            return;
        }

        if (!floor.isForkliftAtStart()) {
            System.out.println(Messages.MUST_STAND_ON_START);
            return;
        }

        Item deliveredItem = floor.dropFromForklift();

        if (deliveredItem == null) {
            System.out.println(Messages.NOT_CARRYING);
            return;
        }

        employee.getShiftSummary().updateItemDelivered();
        System.out.println(Messages.ITEM_DELIVERED);
    }

    /**
     * Checks whether the warehouse shift is complete.
     *
     * A shift is complete when all shelves in all floors are visited and empty,
     * and no forklift is carrying any item.
     *
     * @return true if the whole warehouse shift is complete
     */
    private boolean isShiftComplete() {
        return warehouseMap.areAllShelvesCompleted()
                && !warehouseMap.isAnyForkliftCarrying();
    }

    private boolean isFloorComplete(WarehouseFloor floor) {
        return floor.areAllShelvesCompleted()
                && !floor.isForkliftCarrying();
    }
}

