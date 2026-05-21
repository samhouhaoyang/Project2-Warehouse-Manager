package warehouse;

/**
 * Represents an item stored on a shelf or carried by the forklift.
 */
public class Item {

    private final String name;

    /**
     * Creates an item.
     *
     * @param name item name
     */
    public Item(String name) {
        this.name = name;
    }

    /**
     * Copy constructor for Item.
     *
     * @param item Item object to copy from
     */
    public Item(Item item) {
        if (item != null) {
            this.name = item.name;
        } else {
            this.name = null;
        }
    }

    /**
     * Returns the item name.
     *
     * @return item name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the display name for this item.
     *
     * @return item name
     */
    @Override
    public String toString() {
        return this.name;
    }
}
