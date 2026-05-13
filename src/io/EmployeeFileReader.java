package io;

import employees.*;
import enums.DesignationType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeFileReader {
    private final ArrayList<String> managerIds;

    public EmployeeFileReader(){
        this.managerIds = new ArrayList<>();
    }
    public ArrayList<Employee> readEmployees(String path) throws FileNotFoundException {
        managerIds.clear();

        ArrayList<Employee> employees = new ArrayList<>();

        Scanner scanner = new Scanner(new File(path));
        int lineNumber = 0;
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            lineNumber++;

            // skip the heading
            if (lineNumber == 1 || line.trim().isEmpty()) {
                continue;
            }

            Employee employee = processLine(line,lineNumber);

            if(employee != null){
                employees.add(employee);
            }


        }

        scanner.close();
        linkSupervisor(employees);

        return employees;
    }
    private Employee findEmployeeById(ArrayList<Employee> employees, String id){
        for (Employee employee : employees) {
            if(employee.getEmployeeId().equals(id)){
                return employee;
            }
        }
        return null;
    }

    public void linkSupervisor(ArrayList<Employee> employees){
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
    public Employee processLine(String line, int lineNumber){
        String[] lineArray = line.split(FileConstants.CSV_DELIMITER, -1);
        // incorrect number of columns
        if (lineArray.length < FileConstants.EMPLOYEE_MIN_FIELD_COUNT) {
            System.out.printf(
                    "Incorrect Employees line at line %d. Skipping this line.%n",
                    lineNumber
            );
            return null;
        }

        String employeeId = lineArray[0].trim();
        String employeeName = lineArray[1].trim();
        String designationText = lineArray[2].trim();
        String baseSalaryText = lineArray[3].trim();
        String managerId = "";

        // handles manager if linked to one
        if(lineArray.length >= FileConstants.EMPLOYEE_MAX_FIELD_COUNT){
            managerId = lineArray[4].trim();
        }

        if (employeeId.isEmpty() || employeeName.isEmpty()) {
            System.out.printf(
                    "Incorrect Employee Details at line %d. Skipping the line.%n",
                    lineNumber
            );
            return null;
        }

        double baseSalary;

        try {
            baseSalary = Double.parseDouble(baseSalaryText);
        } catch (NumberFormatException e) {
            System.out.printf(
                    "Incorrect Employee Details at line %d. Skipping the line.%n",
                    lineNumber
            );
            return null;
        }

        if (baseSalary <= 0) {
            System.out.printf(
                    "Incorrect Employee Details at line %d. Skipping the line.%n",
                    lineNumber
            );
            return null;
        }

        DesignationType designation;

        try {
            designation = DesignationType.valueOf(designationText);
        } catch (IllegalArgumentException e) {
            System.out.printf(
                    "Incorrect Employee Designation at line %d. Skipping this line.%n",
                    lineNumber
            );
            return null;
        }

        Employee employee = createEmployee(employeeId, employeeName, designation, baseSalary);

        if (employee != null) {
            // note here default is a blank string
            managerIds.add(managerId);
        }

        return employee;
    }

    private Employee createEmployee(String employeeId, String employeeName,
                                    DesignationType designation, double baseSalary){
        switch (designation){
            case OPERATOR -> {
                return new Operator(employeeId,employeeName, designation, baseSalary);
            }
            case SENIOR_OPERATOR -> {
                return new SeniorOperator(employeeId,employeeName, designation, baseSalary);
            }
            case SUPERVISOR -> {
                return new Supervisor(employeeId, employeeName, designation, baseSalary);
            }
            case PAYROLL_MANAGER -> {
                return new PayrollManager(employeeId, employeeName, designation, baseSalary);
            }
            default -> {
                return  null;
            }
        }
    }
}