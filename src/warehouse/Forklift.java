package warehouse;

import utils.Constants;

/**
 * Represents the forklift used on a warehouse floor.
 * It tracks its position, carried item, and pause state.
 */
public class Forklift {
    private int row;
    private int col;
    private boolean sessionPaused;
    private Item carriedItem;

    /**
     * Constructs a Forklift object.
     * Resets the forklift to its starting state.
     */
    public Forklift() {
        resetSessionState();
    }

    /**
     * Resets the forklift to its initial state.
     * This includes position, statistics, carried item,
     * and session pause status.
     */
    public void resetSessionState() {
        this.row = Constants.START_ROW;
        this.col = Constants.START_COL;
        this.sessionPaused = false;
        this.carriedItem = null;
    }

    /**
     * Returns the current row position.
     *
     * @return row index
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Returns the current column position.
     *
     * @return column index
     */
    public int getCol() {
        return this.col;
    }

    /**
     * Checks whether the forklift is carrying an item.
     *
     * @return true if an item is being carried
     */
    public boolean isCarrying() {
        return this.carriedItem != null;
    }

    /**
     * Assigns an item to be carried by the forklift.
     *
     * @param item the item to pick up
     */
    public void pickUp(Item item) {
        if (item != null) {
            this.carriedItem = item;
        }
    }

    /**
     * Drops the currently carried item and returns it.
     *
     * @return the dropped item, or null if none was carried
     */
    public Item drop() {
        if (this.carriedItem == null) {
            return null;
        }

        Item item = new Item(this.carriedItem);
        this.carriedItem = null;
        return item;
    }

    /**
     * Checks whether the forklift is positioned at the start cell.
     *
     * @return true if the forklift is at START
     */
    public boolean isAtStart() {
        return row == Constants.START_ROW && col == Constants.START_COL;
    }

    /**
     * Checks whether the forklift session is paused.
     *
     * @return true if paused
     */
    public boolean isSessionPaused() {
        return sessionPaused;
    }

    /**
     * Updates the paused state for this forklift session.
     *
     * @param sessionPaused pause state to store
     */
    public void setSessionPaused(boolean sessionPaused) {
        this.sessionPaused = sessionPaused;
    }

    /**
     * Moves the forklift to an already validated cell.
     *
     * @param row new row index
     * @param col new column index
     */
    public void moveTo(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
