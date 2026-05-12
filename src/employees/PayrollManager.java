package employees;

import enums.DesignationType;

public class PayrollManager extends Employee{
    public PayrollManager(int employeeId, String employeeName, DesignationType designation, double baseSalary) {
        super(employeeId, employeeName, designation, baseSalary);
    }

}
