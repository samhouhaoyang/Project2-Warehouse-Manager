package io;

import employees.Payslip;
import utils.Constants;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class PayslipFileWriter {

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
