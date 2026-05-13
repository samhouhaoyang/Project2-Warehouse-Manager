package employees;

import enums.DesignationType;
import java.util.ArrayList;

public class Supervisor extends Employee implements WarehouseWorker{
    private final ArrayList<Employee> reportees;

    public Supervisor(String employeeId, String employeeName, DesignationType designation, double baseSalary) {
        super(employeeId, employeeName, designation, baseSalary);
        this.reportees = new ArrayList<>();
    }

    public void addReportee(Employee employee){
        reportees.add(employee);
    }

    public ArrayList<Employee> getReportees() {
        return new ArrayList<>(reportees);
    }

    public int getReporteeCount(){
        return reportees.size();
    }


    @Override
    public double getReporteeManagementPay(){
        return getReporteeCount() * PayslipConstants.REPORTEE_MANAGEMENT_PAY;
    }


}
