/**
 * Student Name - Haoyang Hou
 * Student Id - 1462169
 * Student email - houhh@student.unimelb.edu.au
 * AI Usage Declaration - I used AI assistance to understand and improve encapsulation and interface design, to debug test/output mismatches, and to improve code comments and Javadoc. All final design decisions, code changes, and submission responsibility are mine.
 * Assignment 1 solution reference - Parts of the warehouse movement, shelf interaction, and map-display structure were informed by the Assignment 1 specification/scaffold concepts, then adapted for the Assignment 2 multi-floor, employee, and payroll requirements.
 */

package io;

import employees.Employee;
import employees.Operator;
import employees.PayrollManager;
import employees.SeniorOperator;
import employees.Supervisor;
import enums.DesignationType;
import utils.Constants;
import utils.Messages;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Reads employee data from a CSV file and builds employee objects.
 */
public class EmployeeFileReader implements CsvFileReader<Employee> {
    private final ArrayList<String> managerIds;

    /**
     * Creates an employee file reader.
     */
    public EmployeeFileReader() {
        this.managerIds = new ArrayList<>();
    }

    /**
     * Reads employees from the supplied CSV file path.
     * Invalid data lines are skipped after printing the required message.
     *
     * @param path employee file path
     * @return employees loaded from valid lines
     * @throws FileNotFoundException if the file cannot be opened
     */
    @Override
    public ArrayList<Employee> read(String path) throws FileNotFoundException {
        managerIds.clear();

        ArrayList<Employee> employees = new ArrayList<>();

        int lineNumber = 0;
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNumber++;

                if (lineNumber == 1 || line.trim().isEmpty()) {
                    continue;
                }

                Employee employee = processLine(line, lineNumber);

                if (employee != null) {
                    employees.add(employee);
                }
            }
        }

        linkSupervisor(employees);

        return employees;
    }

    /**
     * Finds an employee in the newly loaded employee list.
     *
     * @param employees loaded employees
     * @param id employee id to match
     * @return matching employee, or null if none exists
     */
    private Employee findEmployeeById(ArrayList<Employee> employees, String id) {
        for (Employee employee : employees) {
            if (employee.getEmployeeId().equals(id)) {
                return employee;
            }
        }

        return null;
    }

    /**
     * Links employees to valid supervisor managers after all employees are loaded.
     *
     * @param employees loaded employees
     */
    private void linkSupervisor(ArrayList<Employee> employees) {
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            String managerId = managerIds.get(i);

            if (!managerId.isEmpty()) {
                Employee manager = findEmployeeById(employees, managerId);

                if (manager instanceof Supervisor) {
                    Supervisor supervisor = (Supervisor) manager;
                    employee.setSupervisor(supervisor);
                    supervisor.addReportee(employee);
                }
            }
        }
    }

    /**
     * Parses one employee CSV line.
     *
     * @param line raw CSV line
     * @param lineNumber physical file line number
     * @return created employee, or null when the line is invalid
     */
    private Employee processLine(String line, int lineNumber) {
        String[] lineArray = line.split(Constants.CSV_DELIMITER, -1);

        if (lineArray.length < Constants.EMPLOYEE_MIN_FIELD_COUNT) {
            Messages.printEmployeeFileInvalidLine(lineNumber);
            return null;
        }

        String employeeId = lineArray[0].trim();
        String employeeName = lineArray[1].trim();
        String designationText = lineArray[2].trim();
        String baseSalaryText = lineArray[3].trim();
        String managerId = Constants.EMPTY_STRING;

        if (lineArray.length >= Constants.EMPLOYEE_MAX_FIELD_COUNT) {
            managerId = lineArray[4].trim();
        }

        if (employeeId.isEmpty() || employeeName.isEmpty()) {
            Messages.printEmployeeFileInvalidDetails(lineNumber);
            return null;
        }

        double baseSalary;

        try {
            baseSalary = Double.parseDouble(baseSalaryText);
        } catch (NumberFormatException e) {
            Messages.printEmployeeFileInvalidDetails(lineNumber);
            return null;
        }

        if (baseSalary <= 0 || Double.isNaN(baseSalary) || Double.isInfinite(baseSalary)) {
            Messages.printEmployeeFileInvalidDetails(lineNumber);
            return null;
        }

        DesignationType designation;

        try {
            designation = DesignationType.valueOf(designationText);
        } catch (IllegalArgumentException e) {
            Messages.printEmployeeFileInvalidDesignation(lineNumber);
            return null;
        }

        Employee employee = createEmployee(employeeId, employeeName, designation, baseSalary);

        if (employee != null) {
            managerIds.add(managerId);
        }

        return employee;
    }

    /**
     * Creates the concrete employee subtype for a designation.
     *
     * @param employeeId employee id
     * @param employeeName employee name
     * @param designation employee designation
     * @param baseSalary base salary
     * @return concrete employee instance
     */
    private Employee createEmployee(String employeeId, String employeeName,
                                    DesignationType designation, double baseSalary) {
        switch (designation) {
            case OPERATOR -> {
                return new Operator(employeeId, employeeName, designation, baseSalary);
            }
            case SENIOR_OPERATOR -> {
                return new SeniorOperator(employeeId, employeeName, designation, baseSalary);
            }
            case SUPERVISOR -> {
                return new Supervisor(employeeId, employeeName, designation, baseSalary);
            }
            case PAYROLL_MANAGER -> {
                return new PayrollManager(employeeId, employeeName, designation, baseSalary);
            }
            default -> {
                return null;
            }
        }
    }
}
