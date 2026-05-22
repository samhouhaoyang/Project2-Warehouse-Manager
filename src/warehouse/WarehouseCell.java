/**
 * Student Name - Haoyang Hou
 * Student Id - 1462169
 * Student email - houhh@student.unimelb.edu.au
 * AI Usage Declaration - I used AI assistance to understand and improve encapsulation and interface design, to debug test/output mismatches, and to improve code comments and Javadoc. All final design decisions, code changes, and submission responsibility are mine.
 * Assignment 1 solution reference - Parts of the warehouse movement, shelf interaction, and map-display structure were informed by the Assignment 1 specification/scaffold concepts, then adapted for the Assignment 2 multi-floor, employee, and payroll requirements.
 */

package warehouse;

import enums.CellType;
import enums.ShelfType;
import utils.Constants;

/**
 * Represents a single cell on a warehouse floor.
 * A cell stores its row, column, cell type, and optionally a shelf.
 */
public class WarehouseCell {
    private final int row;
    private final int col;
    private CellType type;
    private Shelf shelf;

    /**
     * Creates a warehouse cell at the given row and column.
     *
     * @param row  the row index of the cell
     * @param col  the column index of the cell
     * @param type the type of this cell
     */
    public WarehouseCell(int row, int col, CellType type) {
        this.row = row;
        this.col = col;
        this.type = type;
        this.shelf = null;
    }

    /**
     * Copy constructor.
     *
     * @param other the warehouse cell to copy
     */
    public WarehouseCell(WarehouseCell other) {
        this.row = other.row;
        this.col = other.col;
        this.type = other.type;

        if (other.shelf == null) {
            this.shelf = null;
        } else {
            this.shelf = new Shelf(other.shelf);
        }
    }

    /**
     * Returns the row index of this cell.
     *
     * @return row index
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the column index of this cell.
     *
     * @return column index
     */
    public int getCol() {
        return col;
    }

    /**
     * Returns the cell type.
     *
     * @return cell type
     */
    public CellType getType() {
        return type;
    }

    /**
     * Sets the type of this cell.
     * If the cell is no longer a shelf, the internal shelf is removed.
     *
     * @param type the new cell type
     */
    public void setType(CellType type) {
        this.type = type;

        if (type != CellType.SHELF) {
            this.shelf = null;
        }
    }

    /**
     * Checks whether this cell is a wall.
     *
     * @return true if this cell is a wall
     */
    public boolean isWall() {
        return type == CellType.WALL;
    }

    /**
     * Checks whether this cell is an aisle.
     *
     * @return true if this cell is an aisle
     */
    public boolean isAisle() {
        return type == CellType.AISLE;
    }

    /**
     * Checks whether this cell is restricted.
     *
     * @return true if this cell is restricted
     */
    public boolean isRestricted() {
        return type == CellType.RESTRICTED;
    }

    /**
     * Checks whether this cell is a shelf cell.
     *
     * @return true if this cell is a shelf
     */
    public boolean isShelfCell() {
        return type == CellType.SHELF;
    }

    /**
     * Checks whether this cell is the start cell.
     *
     * @return true if this cell is START
     */
    public boolean isStart() {
        return type == CellType.START;
    }

    /**
     * Checks whether a shelf object is attached to this cell.
     *
     * @return true if a shelf is attached
     */
    public boolean hasShelf() {
        return shelf != null;
    }

    /**
     * Returns a copy of the shelf stored in this cell.
     * This avoids exposing the real internal shelf object.
     *
     * @return a copy of the shelf, or null if this cell has no shelf
     */
    public Shelf getShelf() {
        if (shelf == null) {
            return null;
        }

        return new Shelf(shelf);
    }

    /**
     * Returns the type of the shelf stored in this cell.
     * This avoids exposing the actual shelf object.
     *
     * @return the shelf type, or null if this cell has no shelf
     */
    public ShelfType getShelfType() {
        if (shelf == null) {
            return null;
        }

        return shelf.getShelfType();
    }

    /**
     * Marks this cell as restricted.
     * A restricted cell cannot also contain a shelf.
     */
    public void markRestricted() {
        this.type = CellType.RESTRICTED;
        this.shelf = null;
    }

    /**
     * Attaches a shelf to this cell.
     * If the shelf already exists, it keeps the existing shelf.
     *
     * @param shelfType the type of shelf to attach
     */
    public void attachShelf(ShelfType shelfType) {
        this.type = CellType.SHELF;

        if (this.shelf == null) {
            this.shelf = new Shelf(shelfType);
        }
    }

    /**
     * Adds an item to the shelf stored in this cell.
     * This modifies the real internal shelf without exposing it.
     *
     * @param item the item to add
     */
    public void addItemToShelf(Item item) {
        if (this.shelf != null && item != null) {
            this.shelf.addItem(item);
        }
    }

    /**
     * Removes an item from this cell's shelf using the user's 1-based item index.
     *
     * @param userIndex the 1-based index entered by the user
     * @return the removed item, or null if there is no shelf or invalid index
     */
    public Item removeItemFromShelf(int userIndex) {
        if (this.shelf == null) {
            return null;
        }

        return this.shelf.removeItemByUserIndex(userIndex);
    }

    /**
     * Returns the display symbol for this cell.
     * This does not consider forklift position.
     *
     * @return the display symbol
     */
    public char getSymbol() {
        switch (type) {
            case WALL:
                return Constants.WALL_SYMBOL;
            case AISLE:
                return Constants.AISLE_SYMBOL;
            case RESTRICTED:
                return Constants.RESTRICTED_SYMBOL;
            case SHELF:
                return Constants.SHELF_SYMBOL;
            case START:
                return Constants.START_SYMBOL;
            default:
                return Constants.UNKNOWN_SYMBOL;
        }
    }


}
