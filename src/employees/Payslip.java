package employees;

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
        System.out.printf("EmployeeID: %s%n", employeeId);
        System.out.printf("EmployeeName: %s%n", employeeName);
        System.out.printf("BaseSalary: %.2f%n", baseSalary);
        System.out.printf("DeliveredPay: %.2f%n", deliveredPay);
        System.out.printf("Hit Penalty: %.2f%n", hitPenalty);
        System.out.printf("Restricted Penalty: %.2f%n", restrictedPenalty);
        System.out.printf("Reportee Pay: %.2f%n", reporteePay);
        System.out.printf("Net Salary: %.2f%n", netSalary);
    }

    public String toFileLine(){
        return String.format("%s,%s,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f",
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
