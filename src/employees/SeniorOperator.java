package employees;

import enums.DesignationType;

public class SeniorOperator extends Employee implements WarehouseWorker{

    public SeniorOperator(int employeeId, String employeeName, DesignationType designation, double baseSalary) {
        super(employeeId, employeeName, designation, baseSalary);
    }
}
