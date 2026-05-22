/**
 * Student Name - Haoyang Hou
 * Student Id - 1462169
 * Student email - houhh@student.unimelb.edu.au
 * AI Usage Declaration - I used AI assistance to understand and improve encapsulation and interface design, to debug test/output mismatches, and to improve code comments and Javadoc. All final design decisions, code changes, and submission responsibility are mine.
 * Assignment 1 solution reference - Parts of the warehouse movement, shelf interaction, and map-display structure were informed by the Assignment 1 specification/scaffold concepts, then adapted for the Assignment 2 multi-floor, employee, and payroll requirements.
 */

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
