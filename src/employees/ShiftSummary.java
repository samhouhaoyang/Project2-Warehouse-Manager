package employees;

public class ShiftSummary {
    private int itemDelivered;
    private int wallHits;
    private int restrictedAreaHits;

    public ShiftSummary() {
    }

    public int getItemDelivered() {
        return itemDelivered;
    }

    public int getWallHits() {
        return wallHits;
    }

    public int getRestrictedAreaHits() {
        return restrictedAreaHits;
    }

    public void updateItemDelivered(){
        this.itemDelivered += 1;
    }

    public void updateWallHits(){
        this.wallHits += 1;
    }

    public void updateRestrictedAreaHits(){
        this.restrictedAreaHits += 1;
    }

    public void printSummary(){
        System.out.printf("No. of Items delivered: %d\n" +
                "No. of Walls hit: %d\n" +
                "No. of Restricted Areas hit: %d\n", itemDelivered, wallHits, restrictedAreaHits);
    }
}
