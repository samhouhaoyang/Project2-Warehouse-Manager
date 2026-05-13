package io;

import employees.Payslip;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class PayslipFileWriter {

    public void writePayslips(ArrayList<Payslip> payslips) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(FileConstants.PAYSLIPS_FILE_PATH);

        writer.println(FileConstants.PAYSLIPS_HEADER);

        for (Payslip payslip : payslips) {
            writer.println(payslip.toFileLine());
        }

        writer.flush();
        writer.close();
    }
}