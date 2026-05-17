/**
 * Forklift represents the player-controlled vehicle in the warehouse.
 * It manages position, movement, collision statistics, carried items,
 * session state, and operation history.
 */
package warehouse;

import utils.*;



public class Forklift {

    // Current row position of the forklift on the grid
    private int row;

    // Current column position of the forklift on the grid
    private int col;

    // Indicates whether the current warehouse shift is paused
    private boolean sessionPaused;

    // Item currently carried by the forklift (null if empty)
    private Item carriedItem;



    /**
     * Constructs a Forklift object.
     * Initialises operation history and resets the forklift
     * to its starting state.
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

    /* ===== Simple getters / setters  */

    public int getRow() { return this.row; }
    public int getCol() { return this.col; }

    public boolean isCarrying() { return this.carriedItem != null; }

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
        Item item = new Item(this.carriedItem); //IMP_NOTE: copy constructor to avoid privacy leak
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
}
