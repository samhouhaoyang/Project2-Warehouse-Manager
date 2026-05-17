package io;

import enums.CellType;
import enums.ShelfType;
import exceptions.*;
import warehouse.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WarehouseFileReader {
    public void readWarehouseFile(String path, WarehouseMap warehouseMap)
            throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(path));
        int lineNumber = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lineNumber++;

            if (lineNumber == 1 || line.trim().isEmpty()) {
                continue;
            }

            try {
                processLine(line, lineNumber, warehouseMap);
            } catch (Exception | InvalidWarehouseException | InvalidLocationException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }

    private void processLine(String line, int lineNumber, WarehouseMap warehouseMap)
            throws InvalidWarehouseException,
            InvalidLocationException,
            IncorrectTypeException {
        // implement step-by-step
    }
}