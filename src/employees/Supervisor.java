package employees;

import enums.DesignationType;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Supervisor extends Employee implements WarehouseWorker{
    private ArrayList<Employee> reportees;

    public Supervisor(int employeeId, String employeeName, DesignationType designation, double baseSalary) {
        super(employeeId, employeeName, designation, baseSalary);
    }

    public void addReportees(Employee employee){
        reportees.add(employee);
    }

    // is this data leakage? using ArrayList()?
    public Employee getReportees(){
        return reportees.getFirst();
    }
    
    public int getReporteeCount(){
        return reportees.size();
    }
}
