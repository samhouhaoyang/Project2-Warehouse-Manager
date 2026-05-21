package employees;

import enums.DesignationType;

/**
 * Represents a senior operator with the same permissions as an operator
 * but a different base salary.
 */
public class SeniorOperator extends Employee implements WarehouseWorker{

    /**
     * Creates a senior operator.
     *
     * @param employeeId unique employee ID
     * @param employeeName employee's display name
     * @param designation senior operator designation
     * @param baseSalary base salary
     */
    public SeniorOperator(String employeeId, String employeeName, DesignationType designation, double baseSalary) {
        super(employeeId, employeeName, designation, baseSalary);
    }


}
