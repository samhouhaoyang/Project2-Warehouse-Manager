package employees;

import utils.Constants;
import utils.Messages;

/**
 * Stores the movement summary used for employee reports and payslips.
 */
public class ShiftSummary {
    private int itemsDelivered;
    private int wallHits;
    private int restrictedAreaHits;

    /**
     * Creates an empty shift summary.
     */
    public ShiftSummary() {
        this.itemsDelivered = Constants.COUNTER_INITIAL_VALUE;
        this.wallHits = Constants.COUNTER_INITIAL_VALUE;
        this.restrictedAreaHits = Constants.COUNTER_INITIAL_VALUE;
    }

    /**
     * Returns the number of delivered items.
     *
     * @return delivered item count
     */
    public int getItemsDelivered() {
        return itemsDelivered;
    }

    /**
     * Returns the number of wall hits.
     *
     * @return wall hit count
     */
    public int getWallHits() {
        return wallHits;
    }

    /**
     * Returns the number of restricted area hits.
     *
     * @return restricted area hit count
     */
    public int getRestrictedAreaHits() {
        return restrictedAreaHits;
    }

    /**
     * Records one delivered item.
     */
    public void updateItemDelivered() {
        this.itemsDelivered += Constants.COUNTER_INCREMENT;
    }

    /**
     * Records one wall hit.
     */
    public void updateWallHits() {
        this.wallHits += Constants.COUNTER_INCREMENT;
    }

    /**
     * Records one restricted area hit.
     */
    public void updateRestrictedAreaHits() {
        this.restrictedAreaHits += Constants.COUNTER_INCREMENT;
    }

    /**
     * Prints this summary in the assignment output format.
     */
    public void printSummary() {
        Messages.printShiftSummary(itemsDelivered, wallHits, restrictedAreaHits);
    }
    }
