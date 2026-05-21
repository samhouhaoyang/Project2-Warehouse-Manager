package employees;

import utils.Messages;
import utils.Constants;

public class ShiftSummary {
    private int itemsDelivered;
    private int wallHits;
    private int restrictedAreaHits;

    public ShiftSummary() {
        this.itemsDelivered = Constants.COUNTER_INITIAL_VALUE;
        this.wallHits = Constants.COUNTER_INITIAL_VALUE;
        this.restrictedAreaHits = Constants.COUNTER_INITIAL_VALUE;

    }

    public int getItemsDelivered() {
        return itemsDelivered;
    }

    public int getWallHits() {
        return wallHits;
    }

    public int getRestrictedAreaHits() {
        return restrictedAreaHits;
    }

    public void updateItemDelivered(){
        this.itemsDelivered += Constants.COUNTER_INCREMENT;
    }

    public void updateWallHits(){
        this.wallHits += Constants.COUNTER_INCREMENT;
    }

    public void updateRestrictedAreaHits(){
        this.restrictedAreaHits += Constants.COUNTER_INCREMENT;
    }

    public void printSummary(){
        Messages.printShiftSummary(itemsDelivered, wallHits, restrictedAreaHits);
    }


}
