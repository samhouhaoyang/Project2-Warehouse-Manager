package employees;

import utils.Messages;
import utils.Constants;

public class Payslip{
    private final String employeeId;
    private final String employeeName;
    private final double baseSalary;
    private final double deliveredPay;
    private final double hitPenalty;
    private final double restrictedPenalty;
    private final double reporteePay;
    private final double netSalary;

    public Payslip(String employeeId, String employeeName, double baseSalary, double deliveredPay, double hitPenalty,
                   double restrictedPenalty, double reporteePay, double netSalary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.baseSalary = baseSalary;
        this.deliveredPay = deliveredPay;
        this.hitPenalty = hitPenalty;
        this.restrictedPenalty = restrictedPenalty;
        this.reporteePay = reporteePay;
        this.netSalary = netSalary;
    }

    public Payslip(Employee employee, double deliveredPay, double hitPenalty,
                   double restrictedPenalty, double reporteePay, double netSalary) {
        this(employee.getEmployeeId(),
                employee.getEmployeeName(),
                employee.getBaseSalary(),
                deliveredPay,
                hitPenalty,
                restrictedPenalty,
                reporteePay,
                netSalary);
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public double getDeliveredPay() {
        return deliveredPay;
    }

    public double getHitPenalty() {
        return hitPenalty;
    }

    public double getRestrictedPenalty() {
        return restrictedPenalty;
    }

    public double getReporteePay() {
        return reporteePay;
    }

    public double getNetSalary() {
        return netSalary;
    }

    public void printPayslip(){
        Messages.printPayslip(
                employeeId,
                employeeName,
                baseSalary,
                deliveredPay,
                hitPenalty,
                restrictedPenalty,
                reporteePay,
                netSalary
        );
    }

    public String toFileLine(){
        return String.format(Constants.PAYSLIP_FILE_LINE_FORMAT,
                employeeId,
                employeeName,
                baseSalary,
                deliveredPay,
                hitPenalty,
                restrictedPenalty,
                reporteePay,
                netSalary);
    }
}
