package employees;

import enums.DesignationType;

public abstract class Employee implements PayslipViewable {
    private final String employeeId;
    private final String employeeName;
    private final DesignationType designation;
    private final double baseSalary;
    private final ShiftSummary shiftSummary;
    private Supervisor supervisor;


    public Employee(String employeeId, String employeeName, DesignationType designation, double baseSalary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.designation = designation;
        this.baseSalary = baseSalary;
        this.shiftSummary = new ShiftSummary();
    }

    public String getEmployeeId() {
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

    public double getReporteeManagementPay() {
        return 0.0;
    }

    public Payslip generatePayslip() {
        double deliveredPay = shiftSummary.getItemsDelivered()
                * PayslipConstants.DELIVERY_PAY;

        double wallPenalty = shiftSummary.getWallHits()
                * PayslipConstants.WALL_HIT_PENALTY;

        double restrictedPenalty = shiftSummary.getRestrictedAreaHits()
                * PayslipConstants.RESTRICTED_AREA_PENALTY;

        double reporteePay = getReporteeManagementPay();

        double netSalary = baseSalary
                + deliveredPay
                - wallPenalty
                - restrictedPenalty
                + reporteePay;

        return new Payslip(employeeId, employeeName, baseSalary,
                deliveredPay, wallPenalty, restrictedPenalty,
                reporteePay, netSalary);
    }

    @Override
    public void viewPayslip(Payslip payslip) {
        if (payslip != null) {
            payslip.printPayslip();
        }
    }

}
