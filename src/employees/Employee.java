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
 * Base class for all employees in the warehouse manager system.
 * It stores shared employee details, shift summary, supervisor relationship,
 * and the standard payslip calculation used by non-payroll-manager roles.
 */
public abstract class Employee {
    private final String employeeId;
    private final String employeeName;
    private final DesignationType designation;
    private final double baseSalary;
    private final ShiftSummary shiftSummary;
    private Supervisor supervisor;


    /**
     * Creates an employee with identity, role, and salary data.
     *
     * @param employeeId unique employee ID
     * @param employeeName employee's display name
     * @param designation employee designation
     * @param baseSalary base salary used for payslip calculation
     */
    public Employee(String employeeId, String employeeName,
                    DesignationType designation, double baseSalary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.designation = designation;
        this.baseSalary = baseSalary;
        this.shiftSummary = new ShiftSummary();
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
     * Returns this employee's designation.
     *
     * @return employee designation
     */
    public DesignationType getDesignation() {
        return designation;
    }

    /**
     * Returns this employee's base salary.
     *
     * @return base salary
     */
    public double getBaseSalary() {
        return baseSalary;
    }


    /**
     * Returns this employee's supervisor, if one has been assigned.
     *
     * @return supervisor, or null if there is no supervisor
     */
    public Supervisor getSupervisor() {
        return supervisor;
    }

    /**
     * Assigns this employee's supervisor.
     *
     * @param supervisor supervisor to assign
     */
    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    /**
     * Returns this employee's shift summary.
     *
     * @return shift summary
     */
    public ShiftSummary getShiftSummary() {
        return shiftSummary;
    }

    /**
     * Returns additional management pay for reportees.
     *
     * @return reportee management pay, zero for non-supervisors
     */
    public double getReporteeManagementPay() {
        return 0.0;
    }

    /**
     * Generates a payslip from this employee's current shift summary.
     *
     * @return generated payslip
     */
    public Payslip generatePayslip() {
        double deliveredPay = shiftSummary.getItemsDelivered()
                * Constants.DELIVERY_PAY;

        double wallPenalty = shiftSummary.getWallHits()
                * Constants.WALL_HIT_PENALTY;

        double restrictedPenalty = shiftSummary.getRestrictedAreaHits()
                * Constants.RESTRICTED_AREA_PENALTY;

        double reporteePay = getReporteeManagementPay();

        double netSalary = baseSalary
                + deliveredPay
                - wallPenalty
                - restrictedPenalty
                + reporteePay;

        return new Payslip(this,
                deliveredPay, wallPenalty, restrictedPenalty,
                reporteePay, netSalary);
    }

    /**
     * Prints the supplied payslip if it exists.
     *
     * @param payslip payslip to display
     */
    public void viewPayslip(Payslip payslip) {
        if (payslip != null) {
            payslip.printPayslip();
        }
    }

}
