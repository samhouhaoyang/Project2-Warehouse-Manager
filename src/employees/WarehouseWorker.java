package employees;

/**
 * Represents employee roles that can perform warehouse shift work.
 */
public interface WarehouseWorker {
    /**
     * Returns the worker's shift summary.
     *
     * @return shift summary
     */
    ShiftSummary getShiftSummary();

    /**
     * Generates a payslip from the worker's shift data.
     *
     * @return generated payslip
     */
    Payslip generatePayslip();
}
