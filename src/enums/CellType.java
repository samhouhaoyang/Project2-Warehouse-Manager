package enums;

/**
 * Represents the type of a warehouse cell.
 */
public enum CellType {
    /** Boundary wall cell. */
    WALL,

    /** Walkable warehouse aisle. */
    AISLE,

    /** Restricted warehouse cell. */
    RESTRICTED,

    /** Shelf cell that can store items. */
    SHELF,

    /** Start cell where items are delivered. */
    START
}
