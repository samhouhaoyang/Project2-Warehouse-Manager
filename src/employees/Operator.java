/**
 * Student Name - Haoyang Hou
 * Student Id - 1462169
 * Student email - houhh@student.unimelb.edu.au
 * AI Usage Declaration - I used AI assistance to understand and improve encapsulation and interface design, to debug test/output mismatches, and to improve code comments and Javadoc. All final design decisions, code changes, and submission responsibility are mine.
 * Assignment 1 solution reference - Parts of the warehouse movement, shelf interaction, and map-display structure were informed by the Assignment 1 specification/scaffold concepts, then adapted for the Assignment 2 multi-floor, employee, and payroll requirements.
 */

package employees;

import enums.DesignationType;

/**
 * Represents a standard warehouse operator.
 */
public class Operator extends Employee implements WarehouseWorker {

    /**
     * Creates an operator.
     *
     * @param employeeId unique employee ID
     * @param employeeName employee's display name
     * @param designation operator designation
     * @param baseSalary base salary
     */
    public Operator(String employeeId, String employeeName,
                    DesignationType designation, double baseSalary) {
        super(employeeId, employeeName, designation, baseSalary);
    }
}
