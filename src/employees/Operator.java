package employees;

import enums.DesignationType;

/**
 * Represents a standard warehouse operator.
 */
public class Operator extends Employee implements WarehouseWorker {

    /**
     * Creates an operator.
     *
     * @param employeeId unique employee ID
     * @param employeeName employee's display name
     * @param designation operator designation
     * @param baseSalary base salary
     */
    public Operator(String employeeId, String employeeName,
                    DesignationType designation, double baseSalary) {
        super(employeeId, employeeName, designation, baseSalary);
    }
}
