package employees;

import utils.Messages;

public class ShiftSummary {
    private int itemsDelivered;
    private int wallHits;
    private int restrictedAreaHits;

    public ShiftSummary() {
        this.itemsDelivered = 0;
        this.wallHits = 0;
        this.restrictedAreaHits = 0;

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
        this.itemsDelivered += 1;
    }

    public void updateWallHits(){
        this.wallHits += 1;
    }

    public void updateRestrictedAreaHits(){
        this.restrictedAreaHits += 1;
    }

    public void printSummary(){
        Messages.printShiftSummary(itemsDelivered, wallHits, restrictedAreaHits);
    }


}
