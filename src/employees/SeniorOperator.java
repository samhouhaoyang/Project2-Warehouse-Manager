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
 * Represents a senior operator with the same permissions as an operator
 * but a different base salary.
 */
public class SeniorOperator extends Employee implements WarehouseWorker {

    /**
     * Creates a senior operator.
     *
     * @param employeeId unique employee ID
     * @param employeeName employee's display name
     * @param designation senior operator designation
     * @param baseSalary base salary
     */
    public SeniorOperator(String employeeId, String employeeName,
                          DesignationType designation, double baseSalary) {
        super(employeeId, employeeName, designation, baseSalary);
    }
}
