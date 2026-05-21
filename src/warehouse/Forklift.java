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

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

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

    public boolean isAtStart() {
        return row == Constants.START_ROW && col == Constants.START_COL;
    }

    public boolean isSessionPaused() {
        return sessionPaused;
    }

    public void setSessionPaused(boolean sessionPaused) {
        this.sessionPaused = sessionPaused;
    }

    public void moveTo(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
