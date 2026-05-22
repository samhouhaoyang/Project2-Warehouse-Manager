/**
 * Student Name - Haoyang Hou
 * Student Id - 1462169
 * Student email - houhh@student.unimelb.edu.au
 * AI Usage Declaration - I used AI assistance to understand and improve encapsulation and interface design, to debug test/output mismatches, and to improve code comments and Javadoc. All final design decisions, code changes, and submission responsibility are mine.
 * Assignment 1 solution reference - Parts of the warehouse movement, shelf interaction, and map-display structure were informed by the Assignment 1 specification/scaffold concepts, then adapted for the Assignment 2 multi-floor, employee, and payroll requirements.
 */

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
