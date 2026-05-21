package io;

import enums.CellType;
import enums.ShelfType;
import exceptions.IncorrectLocationException;
import exceptions.IncorrectTypeException;
import exceptions.InvalidLocationException;
import exceptions.InvalidWarehouseException;
import utils.Constants;
import utils.Messages;
import warehouse.WarehouseFloor;
import warehouse.WarehouseMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Reads warehouse map data from a CSV file and loads it into a WarehouseMap.
 */
public class WarehouseFileReader {

    /**
     * Reads the warehouse file and updates the given warehouse map.
     *
     * @param path the warehouse file path
     * @param warehouseMap the warehouse map to update
     * @throws FileNotFoundException if the warehouse file cannot be opened
     */
    public void readWarehouseFile(String path, WarehouseMap warehouseMap)
            throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(path));
        int lineNumber = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lineNumber++;

            if (line.trim().isEmpty()) {
                continue;
            }

            if (lineNumber == 1) {
                continue;
            }

            try {
                processLine(line, lineNumber, warehouseMap);
            } catch (InvalidWarehouseException
                     | InvalidLocationException
                     | IncorrectTypeException
                     | IncorrectLocationException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }

    /**
     * Processes one data line from the warehouse file.
     *
     * @param line the CSV line
     * @param lineNumber the current line number
     * @param warehouseMap the warehouse map to update
     */
    private void processLine(String line, int lineNumber, WarehouseMap warehouseMap)
            throws InvalidWarehouseException,
            InvalidLocationException,
            IncorrectTypeException,
            IncorrectLocationException {

        String[] parts = line.split(Constants.CSV_DELIMITER);

        if (parts.length != Constants.WAREHOUSE_FIELD_COUNT) {
            throw new InvalidWarehouseException(Messages.formatInvalidWarehouseLine(lineNumber));
        }

        int floorNumber = parseWarehouseInteger(parts[0].trim(), lineNumber);

        validateFloorNumber(floorNumber, lineNumber, warehouseMap);

        WarehouseFloor floor = warehouseMap.getFloorByNumber(floorNumber);

        int row = parseWarehouseInteger(parts[1].trim(), lineNumber);
        int col = parseWarehouseInteger(parts[2].trim(), lineNumber);

        validateLocation(row, col, lineNumber, floor);

        String cellTypeText = parts[3].trim();
        String shelfTypeText = parts[4].trim();
        String itemName = parts[5].trim();

        CellType cellType = parseFileCellType(cellTypeText, lineNumber);

        if (cellType == CellType.RESTRICTED) {
            processRestrictedCell(floor, row, col, shelfTypeText, lineNumber);
        } else {
            processShelfCell(floor, row, col, shelfTypeText, itemName, lineNumber);
        }
    }

    private int parseWarehouseInteger(String value, int lineNumber)
            throws InvalidWarehouseException {

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new InvalidWarehouseException(Messages.formatInvalidWarehouseLine(lineNumber));
        }
    }

    /**
     * Validates that the floor number exists in the current warehouse map.
     *
     * @param floorNumber the floor number from the file
     * @param lineNumber the current line number
     * @param warehouseMap the current warehouse map
     * @throws InvalidWarehouseException if the floor number is invalid
     */
    private void validateFloorNumber(int floorNumber, int lineNumber, WarehouseMap warehouseMap)
            throws InvalidWarehouseException {

        if (!warehouseMap.isValidFloorNumber(floorNumber)) {
            throw new InvalidWarehouseException(
                    Messages.formatInvalidFloorNumberInWarehouseFile(lineNumber)
            );
        }
    }

    /**
     * Validates that a row and column are inside the map and not on the boundary.
     *
     * @param row the row index
     * @param col the column index
     * @param lineNumber the current line number
     * @param floor the floor being updated
     * @throws InvalidLocationException if the location is invalid
     */
    private void validateLocation(int row, int col, int lineNumber, WarehouseFloor floor)
            throws InvalidLocationException {

        if (!floor.isInBounds(row, col) || floor.isBoundary(row, col)) {
            throw new InvalidLocationException(
                    Messages.formatInvalidLocationInWarehouseFile(lineNumber)
            );
        }
    }

    /**
     * Parses the raw cell type text into a CellType enum.
     *
     * The warehouse file may only define RESTRICTED or SHELF cells.
     * WALL, AISLE, and START are valid internal cell types but invalid in the file.
     *
     * @param cellTypeText raw text from the file
     * @param lineNumber the current line number
     * @return parsed CellType
     * @throws IncorrectTypeException if the cell type is invalid for the file
     */
    private CellType parseFileCellType(String cellTypeText, int lineNumber)
            throws IncorrectTypeException {

        if (Constants.FILE_CELL_TYPE_RESTRICTED.equals(cellTypeText)) {
            return CellType.RESTRICTED;
        }

        if (Constants.FILE_CELL_TYPE_SHELF.equals(cellTypeText)) {
            return CellType.SHELF;
        }

        throw new IncorrectTypeException(Messages.formatInvalidCellType(lineNumber));
    }

    /**
     * Parses the raw shelf type text into a ShelfType enum.
     *
     * @param shelfTypeText raw text from the file
     * @param lineNumber the current line number
     * @return parsed ShelfType
     * @throws IncorrectTypeException if the shelf type is invalid
     */
    private ShelfType parseShelfType(String shelfTypeText, int lineNumber)
            throws IncorrectTypeException {

        try {
            return ShelfType.valueOf(shelfTypeText);
        } catch (IllegalArgumentException e) {
            throw new IncorrectTypeException(Messages.formatInvalidShelfType(lineNumber));
        }
    }

    /**
     * Processes a RESTRICTED cell line.
     *
     * @param floor the warehouse floor
     * @param row the row index
     * @param col the column index
     * @param shelfTypeText raw shelf type text
     * @param lineNumber the current line number
     * @throws IncorrectLocationException if the restricted location conflicts with shelf rules
     */
    private void processRestrictedCell(WarehouseFloor floor, int row, int col,
                                       String shelfTypeText, int lineNumber)
            throws IncorrectLocationException {

        if (floor.isShelfAt(row, col)) {
            throw new IncorrectLocationException(
                    Messages.formatRestrictedLocationOverlapsShelf(lineNumber)
            );
        }

        if (!shelfTypeText.isEmpty()
                && !shelfTypeText.equals(Constants.HYPHEN)) {
            throw new IncorrectLocationException(
                    Messages.formatShelfTypeCannotBeRestrictedLocation(lineNumber)
            );
        }

        floor.markRestrictedAt(row, col);
    }

    /**
     * Processes a SHELF cell line.
     *
     * @param floor the warehouse floor
     * @param row the row index
     * @param col the column index
     * @param shelfType parsed shelf type
     * @param itemName item name text
     * @param lineNumber the current line number
     * @throws IncorrectLocationException if the shelf overlaps a restricted cell
     * @throws IncorrectTypeException if the shelf type conflicts with an existing shelf
     */
    private void processShelfCell(WarehouseFloor floor, int row, int col,
                                  String shelfTypeText, String itemName,
                                  int lineNumber)
            throws IncorrectLocationException, IncorrectTypeException {

        if (floor.isRestrictedAt(row, col)) {
            throw new IncorrectLocationException(
                    Messages.formatRestrictedLocationOverlapsShelf(lineNumber)
            );
        }

        ShelfType shelfType = parseShelfType(shelfTypeText, lineNumber);

        if (floor.hasShelfAt(row, col)
                && floor.getShelfTypeAt(row, col) != shelfType) {
            throw new IncorrectTypeException(
                    Messages.formatShelfTypeMismatched(lineNumber)
            );
        }

        floor.attachShelfAt(row, col, shelfType);

        if (!isEmptyItemName(itemName)) {
            floor.addItemToShelfAt(row, col, itemName);
        }
    }

    private boolean isEmptyItemName(String itemName) {
        return itemName == null
                || itemName.trim().isEmpty()
                || itemName.trim().equals(Constants.HYPHEN);
    }
}
