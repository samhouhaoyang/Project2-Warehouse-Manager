package employees;

import enums.DesignationType;
import utils.Constants;
import java.util.ArrayList;

/**
 * Represents a supervisor who can operate the warehouse and manage reportees.
 */
public class Supervisor extends Employee implements WarehouseWorker{
    private final ArrayList<Employee> reportees;

    /**
     * Creates a supervisor.
     *
     * @param employeeId unique employee ID
     * @param employeeName employee's display name
     * @param designation supervisor designation
     * @param baseSalary base salary
     */
    public Supervisor(String employeeId, String employeeName, DesignationType designation, double baseSalary) {
        super(employeeId, employeeName, designation, baseSalary);
        this.reportees = new ArrayList<>();
    }

    /**
     * Adds an employee as a direct reportee.
     *
     * @param employee reportee to add
     */
    public void addReportee(Employee employee){
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
    public int getReporteeCount(){
        return reportees.size();
    }


    /**
     * Calculates management pay from the number of reportees.
     *
     * @return reportee management pay
     */
    @Override
    public double getReporteeManagementPay(){
        return getReporteeCount() * Constants.REPORTEE_MANAGEMENT_PAY;
    }


}
