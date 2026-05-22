/**
 * Student Name - Haoyang Hou
 * Student Id - 1462169
 * Student email - houhh@student.unimelb.edu.au
 * AI Usage Declaration - I used AI assistance to understand and improve encapsulation and interface design, to debug test/output mismatches, and to improve code comments and Javadoc. All final design decisions, code changes, and submission responsibility are mine.
 * Assignment 1 solution reference - Parts of the warehouse movement, shelf interaction, and map-display structure were informed by the Assignment 1 specification/scaffold concepts, then adapted for the Assignment 2 multi-floor, employee, and payroll requirements.
 */

package warehouse;

import enums.ShelfType;
import utils.Constants;
import utils.Messages;

/**
 * Represents a shelf that stores items in the warehouse.
 *
 * Internally, the shelf uses a resizable one-dimensional array
 * to manage items, similar to how an ArrayList works.
 */
public class Shelf {
    private final ShelfType shelfType;
    private Item[] items;
    private int size;

    /**
     * Creates an empty shelf with the given shelf type.
     *
     * @param shelfType the type of this shelf
     */
    public Shelf(ShelfType shelfType) {
        this.shelfType = shelfType;
        this.items = new Item[Constants.INITIAL_SHELF_CAPACITY];
        this.size = Constants.COUNTER_INITIAL_VALUE;
    }

    /**
     * Copy constructor.
     * Creates a deep copy of another shelf.
     *
     * @param other the shelf to copy
     */
    public Shelf(Shelf other) {
        this.shelfType = other.shelfType;
        this.items = new Item[other.items.length];
        this.size = other.size;

        for (int i = 0; i < other.size; i++) {
            if (other.items[i] != null) {
                this.items[i] = new Item(other.items[i]);
            }
        }
    }

    /**
     * Returns the type of this shelf.
     *
     * @return shelf type
     */
    public ShelfType getShelfType() {
        return shelfType;
    }

    /**
     * Returns the number of items currently stored on this shelf.
     *
     * @return number of items
     */
    public int getSize() {
        return size;
    }

    /**
     * Checks whether this shelf is empty.
     *
     * @return true if there are no items, false otherwise
     */
    public boolean isEmpty() {
        return size == Constants.COUNTER_INITIAL_VALUE;
    }

    /**
     * Adds an item to the shelf.
     * If the internal array is full, the array is resized first.
     *
     * @param item the item to add
     */
    public void addItem(Item item) {
        if (item == null) {
            return;
        }

        if (size == items.length) {
            items = resizeItemArray(items, items.length * Constants.SIZE_MULTIPLIER);
        }

        items[size] = item;
        size++;
    }

    /**
     * Removes an item using a 1-based user index.
     *
     * For example:
     * userIndex = 1 removes the first item.
     *
     * @param userIndex the 1-based item index entered by the user
     * @return the removed item, or null if the index is invalid
     */
    public Item removeItemByUserIndex(int userIndex) {
        int arrayIndex = userIndex - 1;

        if (arrayIndex < 0 || arrayIndex >= size) {
            return null;
        }

        Item removedItem = items[arrayIndex];
        shiftLeftFrom(arrayIndex);

        size--;
        items[size] = null;

        return removedItem;
    }

    /**
     * Returns a deep copy of the currently stored items.
     * This avoids leaking the internal item array.
     *
     * @return copied item array
     */
    public Item[] getItemsSnapshot() {
        Item[] snapshot = new Item[size];

        for (int i = 0; i < size; i++) {
            if (items[i] != null) {
                snapshot[i] = new Item(items[i]);
            }
        }

        return snapshot;
    }

    /**
     * Prints all items on this shelf using 1-based numbering.
     */
    public void printItems() {
        if (isEmpty()) {
            System.out.println(Messages.NO_ITEMS_ON_SHELF);
            return;
        }

        for (int i = 0; i < size; i++) {
            Messages.printShelfItem(i + Constants.COUNTER_INCREMENT, items[i]);
        }
    }

    /**
     * Resizes the item array.
     *
     * @param original the original item array
     * @param newSize  the new capacity
     * @return resized item array
     */
    private Item[] resizeItemArray(Item[] original, int newSize) {
        Item[] resized = new Item[newSize];

        for (int i = 0; i < original.length; i++) {
            resized[i] = original[i];
        }

        return resized;
    }

    /**
     * Shifts all items after the removed item one position to the left.
     *
     * @param startIndex the index where shifting begins
     */
    private void shiftLeftFrom(int startIndex) {
        for (int i = startIndex; i < size - 1; i++) {
            items[i] = items[i + 1];
        }
    }
}
