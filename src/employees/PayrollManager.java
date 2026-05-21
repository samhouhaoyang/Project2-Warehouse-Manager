package employees;

import enums.DesignationType;
import utils.Constants;

public class PayrollManager extends Employee{
    public PayrollManager(String employeeId, String employeeName, DesignationType designation, double baseSalary) {
        super(employeeId, employeeName, designation, baseSalary);
    }



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
