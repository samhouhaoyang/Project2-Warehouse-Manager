package warehouse;

import utils.Constants;

/**
 * Represents the complete warehouse map.
 * A warehouse map contains multiple warehouse floors.
 */
public class WarehouseMap {
    private final WarehouseFloor[] warehouseFloors;

    /**
     * Creates a warehouse map with the given number of floors.
     *
     * @param numberOfFloors the number of floors
     * @param rows           the number of rows per floor
     * @param cols           the number of columns per floor
     */
    public WarehouseMap(int numberOfFloors, int rows, int cols) {
        this.warehouseFloors = new WarehouseFloor[numberOfFloors];

        for (int i = 0; i < numberOfFloors; i++) {
            warehouseFloors[i] = new WarehouseFloor(i + 1, rows, cols);
        }
    }

    /**
     * Returns the number of floors in this warehouse map.
     *
     * @return floor count
     */
    public int getFloorCount() {
        return warehouseFloors.length;
    }

    /**
     * Checks whether a floor number is valid.
     * Floor numbers start from 1, not 0.
     *
     * @param floorNumber the floor number to check
     * @return true if the floor number is valid, false otherwise
     */
    public boolean isValidFloorNumber(int floorNumber) {
        return floorNumber >= Constants.MIN_VALID_FLOOR_NUMBER
                && floorNumber <= warehouseFloors.length;
    }

    /**
     * Returns the warehouse floor with the given floor number.
     *
     * Floor number 1 corresponds to array index 0.
     *
     * @param floorNumber the floor number
     * @return the matching WarehouseFloor, or null if invalid
     */
    public WarehouseFloor getFloorByNumber(int floorNumber) {
        if (!isValidFloorNumber(floorNumber)) {
            return null;
        }

        return warehouseFloors[floorNumber - 1];
    }

    /**
     * Marks the start cell on every floor after warehouse file data is loaded.
     */
    public void markStartCells() {
        for (WarehouseFloor floor : warehouseFloors) {
            floor.markStartCell();
        }
    }

    /**
     * Prints all warehouse floors.
     *
     * The legend should be printed outside this method,
     * usually from WarehouseManagerEngine or Messages.
     */
    public void printMap() {
        for (WarehouseFloor floor : warehouseFloors) {
            System.out.println();
            floor.printFloor();
        }
    }


    /**
     * Checks whether any forklift in the warehouse is currently carrying an item.
     *
     * @return true if any forklift is carrying an item
     */
    public boolean isAnyForkliftCarrying() {
        for (int floorNumber = 1; floorNumber <= getFloorCount(); floorNumber++) {
            WarehouseFloor floor = getFloorByNumber(floorNumber);

            if (floor.isForkliftCarrying()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks whether all shelves on all floors have been completed.
     *
     * @return true if every shelf in the warehouse is visited and empty
     */
    public boolean areAllShelvesCompleted() {
        for (int floorNumber = 1; floorNumber <= getFloorCount(); floorNumber++) {
            WarehouseFloor floor = getFloorByNumber(floorNumber);

            if (!floor.areAllShelvesCompleted()) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks whether every shelf on every floor currently has no items.
     *
     * @return true if all shelf items have been removed
     */
    public boolean areAllShelfItemsEmpty() {
        for (int floorNumber = 1; floorNumber <= getFloorCount(); floorNumber++) {
            WarehouseFloor floor = getFloorByNumber(floorNumber);

            if (!floor.areAllShelfItemsEmpty()) {
                return false;
            }
        }

        return true;
    }
}
