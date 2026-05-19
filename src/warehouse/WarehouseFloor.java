package warehouse;

import enums.CellType;
import enums.Direction;
import enums.MovementResult;
import enums.ShelfType;
import utils.Constants;
import utils.Messages;

/**
 * Represents one floor of the warehouse.
 * Each floor has its own 2D grid and its own forklift.
 */
public class WarehouseFloor {


    private final int floorNumber;
    private final int rows;
    private final int cols;
    private final WarehouseCell[][] grid;
    private final Forklift forklift;
    private final boolean[][] visitedShelves;

    /**
     * Creates a warehouse floor with boundary walls, aisles, a start cell,
     * and a forklift at the start position.
     *
     * @param floorNumber the floor number, starting from 1
     * @param rows        the number of rows
     * @param cols        the number of columns
     */
    public WarehouseFloor(int floorNumber, int rows, int cols) {
        this.floorNumber = floorNumber;
        this.rows = rows;
        this.cols = cols;
        this.grid = new WarehouseCell[rows][cols];
        this.forklift = new Forklift();
        this.visitedShelves = new boolean[rows][cols];

        initialiseCells();
    }

    /**
     * Initialises the floor grid.
     * Boundary cells are walls. Inner cells are aisles.
     * The start cell is placed at (1,1).
     */
    private void initialiseCells() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (isBoundary(row, col)) {
                    grid[row][col] = new WarehouseCell(row, col, CellType.WALL);
                } else {
                    grid[row][col] = new WarehouseCell(row, col, CellType.AISLE);
                }
            }
        }

        grid[Constants.START_ROW][Constants.START_COL].setType(CellType.START);
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    /**
     * Returns this floor's forklift.
     * Later, if we want stronger encapsulation, we can replace this with
     * controlled forklift methods.
     *
     * @return the forklift on this floor
     */
    public Forklift getForklift() {
        return forklift;
    }

    public boolean isInBounds(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public boolean isBoundary(int row, int col) {
        return row == 0 || row == rows - 1 || col == 0 || col == cols - 1;
    }

    public CellType getCellTypeAt(int row, int col) {
        return grid[row][col].getType();
    }

    public boolean isShelfAt(int row, int col) {
        return isInBounds(row, col) && grid[row][col].isShelfCell();
    }

    public boolean isWallAt(int row, int col) {
        return isInBounds(row, col) && grid[row][col].isWall();
    }

    public boolean isRestrictedAt(int row, int col) {
        return isInBounds(row, col) && grid[row][col].isRestricted();
    }


    public boolean hasShelfAt(int row, int col) {
        return grid[row][col].hasShelf();
    }

    public ShelfType getShelfTypeAt(int row, int col) {
        return grid[row][col].getShelfType();
    }

    /**
     * Marks the cell at the given location as restricted.
     *
     * @param row the row index
     * @param col the column index
     */
    public void markRestrictedAt(int row, int col) {
        grid[row][col].markRestricted();
    }

    /**
     * Attaches a shelf at the given location.
     *
     * @param row       the row index
     * @param col       the column index
     * @param shelfType the type of shelf to attach
     */
    public void attachShelfAt(int row, int col, ShelfType shelfType) {
        grid[row][col].attachShelf(shelfType);
    }

    /**
     * Adds an item to the shelf at the given location.
     * Blank item names and "-" are ignored.
     *
     * @param row      the row index
     * @param col      the column index
     * @param itemName the item name
     */
    public void addItemToShelfAt(int row, int col, String itemName) {
        if (itemName == null || itemName.trim().isEmpty() || itemName.trim().equals("-")) {
            return;
        }

        grid[row][col].addItemToShelf(new Item(itemName.trim()));
    }

    /**
     * Removes an item from the shelf at the given location.
     *
     * @param row       the row index
     * @param col       the column index
     * @param userIndex the 1-based index entered by the user
     * @return the removed item, or null if no item is removed
     */
    public Item removeItemFromShelfAt(int row, int col, int userIndex) {
        return grid[row][col].removeItemFromShelf(userIndex);
    }

    public void markShelfVisitedAt(int row, int col) {
        if (isInBounds(row, col)) {
            visitedShelves[row][col] = true;
        }
    }

    public boolean isShelfVisitedAt(int row, int col) {
        if (!isInBounds(row, col)) {
            return false;
        }

        return visitedShelves[row][col];
    }

    /**
     * Returns a copy of the cell at the given location.
     * This avoids exposing the real internal grid cell.
     *
     * @param row the row index
     * @param col the column index
     * @return a copy of the cell
     */
    public WarehouseCell getCellSnapshotAt(int row, int col) {
        if (!isInBounds(row, col)) {
            return null;
        }

        return new WarehouseCell(grid[row][col]);
    }
    /**
     * Prints this warehouse floor.
     * The forklift position is displayed as F.
     */
    public void printFloor() {
        System.out.printf("==========Floor: %d==========%n", floorNumber);
        System.out.printf("Forklift at: (%d,%d)%n", forklift.getRow(), forklift.getCol());

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (forklift.getRow() == row && forklift.getCol() == col) {
                    System.out.print("F ");
                } else {
                    System.out.print(grid[row][col].getSymbol() + " ");
                }
            }

            System.out.println();
        }
    }

    public MovementResult moveForklift(Direction direction) {
        int nextRow = forklift.getRow();
        int nextCol = forklift.getCol();

        switch (direction) {
            case UP -> nextRow--;
            case DOWN -> nextRow++;
            case LEFT -> nextCol--;
            case RIGHT -> nextCol++;
            default -> {
                return MovementResult.INVALID_INPUT;
            }
        }

        if (!isInBounds(nextRow, nextCol) || isWallAt(nextRow, nextCol)) {
            return MovementResult.WALL_HIT;
        }

        if (isRestrictedAt(nextRow, nextCol)) {
            return MovementResult.RESTRICTED_HIT;
        }

        forklift.moveTo(nextRow, nextCol);
        return MovementResult.MOVED;
    }

    public void resetForklift() {
        forklift.resetSessionState();
    }
    public void printShelfItemsAt(int row, int col) {
        if (!isInBounds(row, col) || !isShelfAt(row, col)) {
            System.out.println(Messages.NO_ITEMS_ON_SHELF);
            return;
        }
        WarehouseCell cellSnapshot = getCellSnapshotAt(row, col);

        if (cellSnapshot == null || cellSnapshot.getShelf() == null) {
            System.out.println(Messages.NO_ITEMS_ON_SHELF);
            return;
        }
        Shelf shelf = cellSnapshot.getShelf();

        if (shelf == null || shelf.isEmpty()) {
            System.out.println(Messages.NO_ITEMS_ON_SHELF);
            return;
        }

        shelf.printItems();
    }
}


