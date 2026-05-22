/**
 * Student Name - Haoyang Hou
 * Student Id - 1462169
 * Student email - houhh@student.unimelb.edu.au
 * AI Usage Declaration - I used AI assistance to understand and improve encapsulation and interface design, to debug test/output mismatches, and to improve code comments and Javadoc. All final design decisions, code changes, and submission responsibility are mine.
 * Assignment 1 solution reference - Parts of the warehouse movement, shelf interaction, and map-display structure were informed by the Assignment 1 specification/scaffold concepts, then adapted for the Assignment 2 multi-floor, employee, and payroll requirements.
 */

package employees;

import enums.DesignationType;
import utils.Constants;

/**
 * Represents a payroll manager.
 * Payroll managers generate and view payslips, and their own payslip uses
 * base salary only.
 */
public class PayrollManager extends Employee {
    /**
     * Creates a payroll manager.
     *
     * @param employeeId unique employee ID
     * @param employeeName employee's display name
     * @param designation payroll manager designation
     * @param baseSalary base salary
     */
    public PayrollManager(String employeeId, String employeeName,
                          DesignationType designation, double baseSalary) {
        super(employeeId, employeeName, designation, baseSalary);
    }

    /**
     * Generates a payroll manager payslip using base salary only.
     *
     * @return generated payslip
     */
    @Override
    public Payslip generatePayslip() {
        return new Payslip(
                this,
                Constants.ZERO_MONEY_AMOUNT,
                Constants.ZERO_MONEY_AMOUNT,
                Constants.ZERO_MONEY_AMOUNT,
                Constants.ZERO_MONEY_AMOUNT,
                getBaseSalary()
        );
    }
}
