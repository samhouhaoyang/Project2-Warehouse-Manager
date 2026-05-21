package io;

import employees.Payslip;
import utils.Constants;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Writes payslip records back to the payslip CSV file.
 */
public class PayslipFileWriter {

    /**
     * Creates a payslip file writer.
     */
    public PayslipFileWriter() {
    }

    /**
     * Replaces the payslip file contents with the provided records.
     *
     * @param payslips payslips to save
     * @param header header row to preserve in the file
     * @throws FileNotFoundException if the payslip file cannot be opened for writing
     */
    public void writePayslips(ArrayList<Payslip> payslips, String header)
            throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(Constants.PAYSLIPS_FILE_PATH)) {
            writer.println(header);

            for (Payslip payslip : payslips) {
                writer.println(payslip.toFileLine());
            }

            writer.flush();
        }
    }
}
