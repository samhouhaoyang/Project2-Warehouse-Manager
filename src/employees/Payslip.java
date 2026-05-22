/**
 * Student Name - Haoyang Hou
 * Student Id - 1462169
 * Student email - houhh@student.unimelb.edu.au
 * AI Usage Declaration - I used AI assistance to understand and improve encapsulation and interface design, to debug test/output mismatches, and to improve code comments and Javadoc. All final design decisions, code changes, and submission responsibility are mine.
 * Assignment 1 solution reference - Parts of the warehouse movement, shelf interaction, and map-display structure were informed by the Assignment 1 specification/scaffold concepts, then adapted for the Assignment 2 multi-floor, employee, and payroll requirements.
 */

package employees;

import utils.Constants;
import utils.Messages;

/**
 * Represents a generated or loaded payslip record.
 */
public class Payslip {
    private final String employeeId;
    private final String employeeName;
    private final double baseSalary;
    private final double deliveredPay;
    private final double hitPenalty;
    private final double restrictedPenalty;
    private final double reporteePay;
    private final double netSalary;

    /**
     * Creates a payslip from explicit payslip fields.
     *
     * @param employeeId employee ID
     * @param employeeName employee name
     * @param baseSalary base salary
     * @param deliveredPay delivered item pay
     * @param hitPenalty wall hit penalty
     * @param restrictedPenalty restricted area penalty
     * @param reporteePay reportee management pay
     * @param netSalary final net salary
     */
    public Payslip(String employeeId, String employeeName,
                   double baseSalary, double deliveredPay, double hitPenalty,
                   double restrictedPenalty, double reporteePay, double netSalary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.baseSalary = baseSalary;
        this.deliveredPay = deliveredPay;
        this.hitPenalty = hitPenalty;
        this.restrictedPenalty = restrictedPenalty;
        this.reporteePay = reporteePay;
        this.netSalary = netSalary;
    }

    /**
     * Creates a payslip using identity and base salary from an employee.
     *
     * @param employee employee to pay
     * @param deliveredPay delivered item pay
     * @param hitPenalty wall hit penalty
     * @param restrictedPenalty restricted area penalty
     * @param reporteePay reportee management pay
     * @param netSalary final net salary
     */
    public Payslip(Employee employee, double deliveredPay, double hitPenalty,
                   double restrictedPenalty, double reporteePay, double netSalary) {
        this(employee.getEmployeeId(),
                employee.getEmployeeName(),
                employee.getBaseSalary(),
                deliveredPay,
                hitPenalty,
                restrictedPenalty,
                reporteePay,
                netSalary);
    }

    /**
     * Returns the employee ID.
     *
     * @return employee ID
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * Returns the employee name.
     *
     * @return employee name
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * Returns the base salary.
     *
     * @return base salary
     */
    public double getBaseSalary() {
        return baseSalary;
    }

    /**
     * Returns the delivered item pay.
     *
     * @return delivered item pay
     */
    public double getDeliveredPay() {
        return deliveredPay;
    }

    /**
     * Returns the wall hit penalty.
     *
     * @return wall hit penalty
     */
    public double getHitPenalty() {
        return hitPenalty;
    }

    /**
     * Returns the restricted area penalty.
     *
     * @return restricted area penalty
     */
    public double getRestrictedPenalty() {
        return restrictedPenalty;
    }

    /**
     * Returns the reportee management pay.
     *
     * @return reportee management pay
     */
    public double getReporteePay() {
        return reporteePay;
    }

    /**
     * Returns the net salary.
     *
     * @return net salary
     */
    public double getNetSalary() {
        return netSalary;
    }

    /**
     * Prints this payslip in the assignment output format.
     */
    public void printPayslip() {
        Messages.printPayslip(
                employeeId,
                employeeName,
                baseSalary,
                deliveredPay,
                hitPenalty,
                restrictedPenalty,
                reporteePay,
                netSalary
        );
    }

    /**
     * Converts this payslip to a CSV data row.
     *
     * @return CSV row for file persistence
     */
    public String toFileLine() {
        return String.format(Constants.PAYSLIP_FILE_LINE_FORMAT,
                employeeId,
                employeeName,
                baseSalary,
                deliveredPay,
                hitPenalty,
                restrictedPenalty,
                reporteePay,
                netSalary);
    }
}
