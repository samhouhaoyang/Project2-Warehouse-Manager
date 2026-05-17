package io;

import employees.Payslip;
import utils.Constants;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
public class PayslipFileWriter {

    public void writePayslips(ArrayList<Payslip> payslips) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(Constants.PAYSLIPS_FILE_PATH);

        writer.println(Constants.PAYSLIPS_HEADER);

        for (Payslip payslip : payslips) {
            writer.println(payslip.toFileLine());
        }

        writer.flush();
        writer.close();
    }
}