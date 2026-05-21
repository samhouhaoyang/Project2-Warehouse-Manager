package warehouse;

import enums.CellType;
import enums.Direction;
import enums.MovementResult;
import enums.ShelfType;
import utils.Constants;
import utils.Messages;

/**
 * Represents one floor in the warehouse.
 * Each floor owns its grid cells, shelf visit state, and forklift.
 */
public class WarehouseFloor {


    private final int floorNumber;
    private final int rows;
    private final int cols;
    private final WarehouseCell[][] grid;
    private final Forklift forklift;
    private final boolean[][] visitedShelves;

    /**
     * Creates a warehouse floor with boundary walls, aisles,
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
    }

    /**
     * Marks the fixed start cell after warehouse file data has been loaded.
     */
    public void markStartCell() {
        grid[Constants.START_ROW][Constants.START_COL].setType(CellType.START);
    }

    /**
     * Returns this floor number.
     *
     * @return floor number
     */
    public int getFloorNumber() {
        return floorNumber;
    }

    /**
     * Returns the number of rows on this floor.
     *
     * @return row count
     */
    public int getRows() {
        return rows;
    }

    /**
     * Returns the number of columns on this floor.
     *
     * @return column count
     */
    public int getCols() {
        return cols;
    }

    /**
     * Returns the row of this floor's forklift without exposing the forklift.
     *
     * @return forklift row
     */
    public int getForkliftRow() {
        return forklift.getRow();
    }

    /**
     * Returns the column of this floor's forklift without exposing the forklift.
     *
     * @return forklift column
     */
    public int getForkliftCol() {
        return forklift.getCol();
    }

    /**
     * Checks whether this floor's forklift is carrying an item.
     *
     * @return true if the forklift is carrying an item
     */
    public boolean isForkliftCarrying() {
        return forklift.isCarrying();
    }

    /**
     * Checks whether this floor's forklift is at the start cell.
     *
     * @return true if the forklift is at START
     */
    public boolean isForkliftAtStart() {
        return forklift.isAtStart();
    }

    /**
     * Marks this floor's forklift session as paused.
     */
    public void pauseForkliftSession() {
        forklift.setSessionPaused(true);
    }

    /**
     * Picks up an item using this floor's forklift.
     *
     * @param item item to pick up
     */
    public void pickUpWithForklift(Item item) {
        forklift.pickUp(item);
    }

    /**
     * Drops and returns the item carried by this floor's forklift.
     *
     * @return dropped item, or null if none is carried
     */
    public Item dropFromForklift() {
        return forklift.drop();
    }

    /**
     * Checks whether a coordinate is inside the floor bounds.
     *
     * @param row row index
     * @param col column index
     * @return true if the coordinate is in bounds
     */
    public boolean isInBounds(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    /**
     * Checks whether a coordinate is on the boundary wall.
     *
     * @param row row index
     * @param col column index
     * @return true if the coordinate is on a boundary
     */
    public boolean isBoundary(int row, int col) {
        return row == 0 || row == rows - 1 || col == 0 || col == cols - 1;
    }

    /**
     * Returns the cell type at a coordinate.
     *
     * @param row row index
     * @param col column index
     * @return cell type
     */
    public CellType getCellTypeAt(int row, int col) {
        return grid[row][col].getType();
    }

    /**
     * Checks whether a coordinate contains a shelf cell.
     *
     * @param row row index
     * @param col column index
     * @return true if the coordinate is a shelf
     */
    public boolean isShelfAt(int row, int col) {
        return isInBounds(row, col) && grid[row][col].isShelfCell();
    }

    /**
     * Checks whether a coordinate contains a wall.
     *
     * @param row row index
     * @param col column index
     * @return true if the coordinate is a wall
     */
    public boolean isWallAt(int row, int col) {
        return isInBounds(row, col) && grid[row][col].isWall();
    }

    /**
     * Checks whether a coordinate contains a restricted cell.
     *
     * @param row row index
     * @param col column index
     * @return true if the coordinate is restricted
     */
    public boolean isRestrictedAt(int row, int col) {
        return isInBounds(row, col) && grid[row][col].isRestricted();
    }


    /**
     * Checks whether a shelf object is attached at a coordinate.
     *
     * @param row row index
     * @param col column index
     * @return true if a shelf exists at the coordinate
     */
    public boolean hasShelfAt(int row, int col) {
        return grid[row][col].hasShelf();
    }

    /**
     * Returns the shelf type at a coordinate.
     *
     * @param row row index
     * @param col column index
     * @return shelf type, or null if no shelf exists
     */
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
        if (itemName == null
                || itemName.trim().isEmpty()
                || itemName.trim().equals(Constants.HYPHEN)) {
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

    /**
     * Marks a shelf as visited by the forklift.
     *
     * @param row row index
     * @param col column index
     */
    public void markShelfVisitedAt(int row, int col) {
        if (isInBounds(row, col) && isShelfAt(row, col)) {
            visitedShelves[row][col] = true;
        }
    }

    /**
     * Checks whether a shelf has been visited.
     *
     * @param row row index
     * @param col column index
     * @return true if the shelf has been visited
     */
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
        Messages.printFloorHeader(floorNumber);
        System.out.println();
        printFloorWithoutHeader();
    }

    /**
     * Prints the forklift position and grid cells without the floor heading.
     */
    public void printFloorWithoutHeader() {
        Messages.printForkliftPosition(forklift.getRow(), forklift.getCol());

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (forklift.getRow() == row && forklift.getCol() == col) {
                    Messages.printForkliftSymbol();
                } else {
                    Messages.printMapCell(grid[row][col].getSymbol());
                }
            }

            System.out.println();
        }
    }

    /**
     * Attempts to move the forklift in the requested direction.
     *
     * @param direction movement direction
     * @return result describing whether movement succeeded or hit an obstacle
     */
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

    /**
     * Resets the forklift position and carried-item state.
     */
    public void resetForklift() {
        forklift.resetSessionState();
    }

    /**
     * Prints items on the shelf at the given coordinate.
     *
     * @param row row index
     * @param col column index
     */
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

    /**
     * Checks whether all shelves on this floor have been visited
     * and all items on those shelves have been removed.
     *
     * @return true if every shelf on this floor is visited and empty
     */
    public boolean areAllShelvesCompleted() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (isShelfAt(row, col)) {
                    if (!isShelfVisitedAt(row, col)) {
                        return false;
                    }

                    WarehouseCell cellSnapshot = getCellSnapshotAt(row, col);

                    if (cellSnapshot == null) {
                        return false;
                    }

                    Shelf shelf = cellSnapshot.getShelf();

                    if (shelf != null && !shelf.isEmpty()) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Checks whether every shelf on this floor currently has no items.
     *
     * @return true if all shelves are empty
     */
    public boolean areAllShelfItemsEmpty() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (isShelfAt(row, col)) {
                    WarehouseCell cellSnapshot = getCellSnapshotAt(row, col);

                    if (cellSnapshot == null) {
                        return false;
                    }

                    Shelf shelf = cellSnapshot.getShelf();

                    if (shelf != null && !shelf.isEmpty()) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}


