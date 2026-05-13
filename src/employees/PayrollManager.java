package employees;

import enums.DesignationType;

public class PayrollManager extends Employee{
    public PayrollManager(String employeeId, String employeeName, DesignationType designation, double baseSalary) {
        super(employeeId, employeeName, designation, baseSalary);
    }



    @Override
    public Payslip generatePayslip(){
        // payroll manager only have base salary
        return new Payslip(
                getEmployeeId(),
                getEmployeeName(),
                getBaseSalary(),
                0.0,
                0.0,
                0.0,
                0.0,
                getBaseSalary()
        );
    }
}
