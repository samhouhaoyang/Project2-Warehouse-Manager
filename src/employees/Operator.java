package employees;

import enums.DesignationType;

public class Operator extends Employee implements WarehouseWorker{

    public Operator(String employeeId, String employeeName, DesignationType designation, double baseSalary) {
        super(employeeId, employeeName, designation, baseSalary);
    }


}
