package io;

import employees.Payslip;

import exceptions.IncorrectTypeException;
import exceptions.InvalidLineException;
import utils.Constants;
import utils.Messages;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Reads persisted payslip records from the payslip CSV file.
 */
public class PayslipFileReader implements CsvFileReader<Payslip> {
    private String header = Constants.PAYSLIPS_HEADER;

    /**
     * Creates a payslip file reader.
     */
    public PayslipFileReader() {
    }

    /**
     * Reads payslip records from a file if it exists.
     * Missing payslip files are non-fatal and return an empty list.
     *
     * @param path payslip file path
     * @return valid payslip records
     */
    @Override
    public ArrayList<Payslip> read(String path) {
        ArrayList<Payslip> payslips = new ArrayList<>();
        File file = new File(path);

        if (!file.exists()) {
            Messages.printPayslipFileDoesNotExist(path);
            return payslips;
        }

        try (Scanner scanner = new Scanner(file)) {
            int lineNumber = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNumber++;

                if (lineNumber == 1) {
                    header = line;
                    continue;
                }

                if (line.trim().isEmpty()) {
                    continue;
                }

                try {
                    Payslip payslip = processLine(line, lineNumber);
                    payslips.add(payslip);

                } catch (IncorrectTypeException | InvalidLineException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            Messages.printFileProcessingError();
        }

        return payslips;
    }

    /**
     * Reads payslip records from a file if it exists.
     * Kept as a descriptive wrapper for engine code readability.
     *
     * @param path payslip file path
     * @return valid payslip records
     */
    public ArrayList<Payslip> readPayslips(String path) {
        return read(path);
    }

    /**
     * Returns the header read from the payslip file, or the default header
     * when the file does not exist.
     *
     * @return payslip CSV header
     */
    public String getHeader() {
        return header;
    }

    /**
     * Converts a CSV data line into a payslip.
     *
     * @param line CSV data line
     * @param lineNumber physical line number in the file
     * @return parsed payslip
     * @throws IncorrectTypeException if employee or salary details are invalid
     * @throws InvalidLineException if the line has an invalid number of fields
     */
    private Payslip processLine(String line, int lineNumber)
            throws IncorrectTypeException, InvalidLineException {

        String[] lineArray = line.split(Constants.CSV_DELIMITER, -1);

        if (lineArray.length != Constants.PAYSLIP_FIELD_COUNT) {
            throw new InvalidLineException(
                    String.format(Messages.INCORRECT_PAYSLIPS_LINE,
                            lineNumber)
            );
        }

        String employeeId = lineArray[0].trim();
        String employeeName = lineArray[1].trim();

        if (employeeId.isEmpty() || employeeName.isEmpty()) {
            throw new IncorrectTypeException(
                    String.format(Messages.INCORRECT_EMPLOYEE_DETAILS_SKIP_THIS_LINE,
                            lineNumber)
            );
        }

        double baseSalary = Double.parseDouble(lineArray[2].trim());
        double deliveredPay = Double.parseDouble(lineArray[3].trim());
        double hitPenalty = Double.parseDouble(lineArray[4].trim());
        double restrictedPenalty = Double.parseDouble(lineArray[5].trim());
        double reporteePay = Double.parseDouble(lineArray[6].trim());
        double netSalary = Double.parseDouble(lineArray[7].trim());

        if (baseSalary <= 0
                || deliveredPay < 0
                || hitPenalty < 0
                || restrictedPenalty < 0
                || reporteePay < 0
                || netSalary <= 0) {
            throw new IncorrectTypeException(
                    String.format(Messages.INCORRECT_EMPLOYEE_SALARY_DETAILS,
                            lineNumber)
            );
        }

        return new Payslip(employeeId, employeeName, baseSalary,
                deliveredPay, hitPenalty, restrictedPenalty, reporteePay, netSalary);
    }
}
