package employees;

import enums.DesignationType;

public abstract class Employee implements PayslipViewable {
    private final int employeeId;
    private final String employeeName;
    private final DesignationType designation;
    private final double baseSalary;
    private ShiftSummary shiftSummary;
    private Supervisor supervisor;


    public Employee(int employeeId, String employeeName, DesignationType designation, double baseSalary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.designation = designation;
        this.baseSalary = baseSalary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public DesignationType getDesignation() {
        return designation;
    }

    public double getBaseSalary() {
        return baseSalary;
    }


    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    public ShiftSummary getShiftSummary() {
        return shiftSummary;
    }



}
