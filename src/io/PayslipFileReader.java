package io;

import employees.Payslip;

import employees.PayslipConstants;
import exceptions.IncorrectTypeException;
import exceptions.InvalidLineException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PayslipFileReader {

    public ArrayList<Payslip> readPayslips(String path) throws FileNotFoundException {
        ArrayList<Payslip> payslips = new ArrayList<>();
        File file = new File(path);

        if (!file.exists()) {
            return payslips;
        }

        try{
            Scanner scanner = new Scanner(file);
            int lineNumber = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNumber++;

                // this handles header line
                if (lineNumber == 1 || line.trim().isEmpty()) {
                    continue;
                }

                try{
                    Payslip payslip = processLine(line,lineNumber);
                    payslips.add(payslip);

                }catch(IncorrectTypeException | InvalidLineException e){
                    System.out.println(e.getMessage());
                }
            }

            scanner.close();

        }catch (FileNotFoundException e){
            System.out.println("Unable to process file. Exiting program.");
        }

        return payslips;
    }

    private Payslip findPayslipByEmployeeId(ArrayList<Payslip> Payslips, String id){
        for (Payslip payslip : Payslips) {
            if(payslip.getEmployeeId().equals(id)){
                return payslip;
            }
        }
        return null;
    }

    public Payslip processLine(String line, int lineNumber)
            throws IncorrectTypeException, InvalidLineException {

        String[] lineArray = line.split(FileConstants.CSV_DELIMITER, -1);

        if (lineArray.length != FileConstants.PAYSLIP_FIELD_COUNT) {
            throw new InvalidLineException(
                    String.format("Incorrect Payslips line at line %d. Skipping this line.",
                            lineNumber)
            );
        }

        String employeeId = lineArray[0].trim();
        String employeeName = lineArray[1].trim();

        if (employeeId.isEmpty() || employeeName.isEmpty()) {
            throw new IncorrectTypeException(
                    String.format("Incorrect Employee Details at line %d. Skipping this line.",
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
                    String.format("Incorrect Employee Salary details at line %d. Skipping this line.",
                            lineNumber)
            );
        }

        return new Payslip(employeeId, employeeName, baseSalary,
                deliveredPay, hitPenalty, restrictedPenalty, reporteePay, netSalary);
    }





}
