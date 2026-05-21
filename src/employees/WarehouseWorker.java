package employees;

public interface WarehouseWorker {
    ShiftSummary getShiftSummary();

    Payslip generatePayslip();
}
