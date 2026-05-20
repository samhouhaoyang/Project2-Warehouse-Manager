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

public class PayslipFileReader {

    public ArrayList<Payslip> readPayslips(String path) {
        ArrayList<Payslip> payslips = new ArrayList<>();
        File file = new File(path);

        if (!file.exists()) {
            Messages.printPayslipFileDoesNotExist(path);
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
            Messages.printFileProcessingError();
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

        double baseSalary = parsePayslipAmount(lineArray[2], lineNumber);
        double deliveredPay = parsePayslipAmount(lineArray[3], lineNumber);
        double hitPenalty = parsePayslipAmount(lineArray[4], lineNumber);
        double restrictedPenalty = parsePayslipAmount(lineArray[5], lineNumber);
        double reporteePay = parsePayslipAmount(lineArray[6], lineNumber);
        double netSalary = parsePayslipAmount(lineArray[7], lineNumber);

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

    private double parsePayslipAmount(String amountText, int lineNumber)
            throws IncorrectTypeException {

        try {
            double amount = Double.parseDouble(amountText.trim());

            if (Double.isNaN(amount) || Double.isInfinite(amount)) {
                throw new NumberFormatException();
            }

            return amount;
        } catch (NumberFormatException e) {
            throw new IncorrectTypeException(
                    String.format(Messages.INCORRECT_EMPLOYEE_SALARY_DETAILS,
                            lineNumber)
            );
        }
    }





}
