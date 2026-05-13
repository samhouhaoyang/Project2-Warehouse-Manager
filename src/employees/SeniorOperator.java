package employees;

import enums.DesignationType;

public class SeniorOperator extends Employee implements WarehouseWorker{

    public SeniorOperator(String employeeId, String employeeName, DesignationType designation, double baseSalary) {
        super(employeeId, employeeName, designation, baseSalary);
    }


}
