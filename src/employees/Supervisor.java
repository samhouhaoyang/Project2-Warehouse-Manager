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

import java.util.ArrayList;

/**
 * Represents a supervisor who can operate the warehouse and manage reportees.
 */
public class Supervisor extends Employee implements WarehouseWorker {
    private final ArrayList<Employee> reportees;

    /**
     * Creates a supervisor.
     *
     * @param employeeId unique employee ID
     * @param employeeName employee's display name
     * @param designation supervisor designation
     * @param baseSalary base salary
     */
    public Supervisor(String employeeId, String employeeName,
                      DesignationType designation, double baseSalary) {
        super(employeeId, employeeName, designation, baseSalary);
        this.reportees = new ArrayList<>();
    }

    /**
     * Adds an employee as a direct reportee.
     *
     * @param employee reportee to add
     */
    public void addReportee(Employee employee) {
        reportees.add(employee);
    }

    /**
     * Returns a copy of this supervisor's reportee list.
     *
     * @return copied reportee list
     */
    public ArrayList<Employee> getReportees() {
        return new ArrayList<>(reportees);
    }

    /**
     * Returns the number of direct reportees.
     *
     * @return reportee count
     */
    public int getReporteeCount() {
        return reportees.size();
    }

    /**
     * Calculates management pay from the number of reportees.
     *
     * @return reportee management pay
    */
    @Override
    public double getReporteeManagementPay() {
        return getReporteeCount() * Constants.REPORTEE_MANAGEMENT_PAY;
    }
}
