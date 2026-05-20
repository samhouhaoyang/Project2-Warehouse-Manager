package io;

import enums.CellType;
import enums.ShelfType;
import exceptions.IncorrectLocationException;
import exceptions.IncorrectTypeException;
import exceptions.InvalidLocationException;
import exceptions.InvalidWarehouseException;
import warehouse.WarehouseFloor;
import warehouse.WarehouseMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Reads warehouse map data from a CSV file and loads it into a WarehouseMap.
 */
public class WarehouseFileReader {
    private static final int WAREHOUSE_FIELD_COUNT = 6;
    private static final String CSV_DELIMITER = ",";
    private static final String RESTRICTED_EMPTY_SHELF_TYPE = "-";
    private static final String EMPTY_ITEM_NAME = "-";
    private static final String FILE_CELL_TYPE_SHELF = "SHELF";
    private static final String FILE_CELL_TYPE_RESTRICTED = "RESTRICTED";

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

            if (line.trim().isEmpty()) {
                continue;
            }

            lineNumber++;

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

        String[] parts = line.split(CSV_DELIMITER, -1);

        if (parts.length != WAREHOUSE_FIELD_COUNT) {
            throw new InvalidWarehouseException(
                    "Invalid Warehouse line at line " + lineNumber + ". Skipping this line."
            );
        }

        int floorNumber;
        int row;
        int col;

        try {
            floorNumber = Integer.parseInt(parts[0].trim());
            row = Integer.parseInt(parts[1].trim());
            col = Integer.parseInt(parts[2].trim());
        } catch (NumberFormatException e) {
            throw new InvalidWarehouseException(
                    "Invalid Warehouse line at line " + lineNumber + ". Skipping this line."
            );
        }

        validateFloorNumber(floorNumber, lineNumber, warehouseMap);

        WarehouseFloor floor = warehouseMap.getFloorByNumber(floorNumber);

        validateLocation(row, col, lineNumber, floor);

        String cellTypeText = parts[3].trim();
        String shelfTypeText = parts[4].trim();
        String itemName = parts[5].trim();

        if (itemName.isEmpty()) {
            throw new InvalidWarehouseException(
                    "Invalid Warehouse line at line " + lineNumber + ". Skipping this line."
            );
        }

        CellType cellType = parseFileCellType(cellTypeText, lineNumber);

        if (cellType == CellType.RESTRICTED) {
            processRestrictedCell(floor, row, col, shelfTypeText, lineNumber);
        } else {
            ShelfType shelfType = parseShelfType(shelfTypeText, lineNumber);
            processShelfCell(floor, row, col, shelfType, itemName, lineNumber);
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
                    "Invalid floor number in warehouse file: "
                            + lineNumber
                            + ". Skipping this line."
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
                    "Invalid location in warehouse file at line "
                            + lineNumber
                            + ". Skipping this line."
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

        if (FILE_CELL_TYPE_RESTRICTED.equals(cellTypeText)) {
            return CellType.RESTRICTED;
        }

        if (FILE_CELL_TYPE_SHELF.equals(cellTypeText)) {
            return CellType.SHELF;
        }

        throw new IncorrectTypeException(
                "Invalid cell type at line "
                        + lineNumber
                        + ". Skipping this line."
        );
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
            throw new IncorrectTypeException(
                    "Invalid shelf type at line "
                            + lineNumber
                            + ". Skipping this line."
            );
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
                    "Restricted location overlaps shelf at line "
                            + lineNumber
                            + ". Skipping this line."
            );
        }

        if (!shelfTypeText.isEmpty()
                && !shelfTypeText.equals(RESTRICTED_EMPTY_SHELF_TYPE)) {
            throw new IncorrectLocationException(
                    "Shelf Type cannot be defined for Restricted Location at line "
                            + lineNumber
                            + ". Skipping this line."
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
                                  ShelfType shelfType, String itemName,
                                  int lineNumber)
            throws IncorrectLocationException, IncorrectTypeException {

        if (isEmptyItemName(itemName)) {
            return;
        }

        if (floor.isRestrictedAt(row, col)) {
            throw new IncorrectLocationException(
                    "Restricted location overlaps shelf at line "
                            + lineNumber
                            + ". Skipping this line."
            );
        }

        if (floor.hasShelfAt(row, col)
                && floor.getShelfTypeAt(row, col) != shelfType) {
            throw new IncorrectTypeException(
                    "Shelf Type mismatched at line "
                            + lineNumber
                            + ". Skipping this line."
            );
        }

        floor.attachShelfAt(row, col, shelfType);
        floor.addItemToShelfAt(row, col, itemName);
    }

    private boolean isEmptyItemName(String itemName) {
        return itemName == null
                || itemName.trim().isEmpty()
                || itemName.trim().equals(EMPTY_ITEM_NAME);
    }
}
