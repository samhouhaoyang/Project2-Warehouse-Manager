package employees;

import enums.DesignationType;
import utils.Constants;

/**
 * Represents a payroll manager.
 * Payroll managers generate and view payslips, and their own payslip uses
 * base salary only.
 */
public class PayrollManager extends Employee{
    /**
     * Creates a payroll manager.
     *
     * @param employeeId unique employee ID
     * @param employeeName employee's display name
     * @param designation payroll manager designation
     * @param baseSalary base salary
     */
    public PayrollManager(String employeeId, String employeeName, DesignationType designation, double baseSalary) {
        super(employeeId, employeeName, designation, baseSalary);
    }



    /**
     * Generates a payroll manager payslip using base salary only.
     *
     * @return generated payslip
     */
    @Override
    public Payslip generatePayslip(){
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
