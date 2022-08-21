package Menu;

import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * Represents an item on the menu.
 * An item on the menu can also be an item set.
 */
@XmlSeeAlso({MenuItemSet.class})
public class MenuItem {

    /**
     * The name of this MenuItem.
     */
    private String name;

    /**
     * The price of this MenuItem.
     */
    private double price;

    /**
     * The type of this MenuItem.
     */
    private ItemType type;

    /**
     * The description of this MenuItem
     */
    private String description;

    /**
     * Default constructor to create a MenuItem.
     */
    public MenuItem() {}

    /**
     * Creates a MenuItem with the given name, price and type.
     * @param name The name of this MenuItem.
     * @param price The price of this MenuItem.
     * @param type The type of this MenuItem.
     */
    public MenuItem(String name, double price, ItemType type) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.description = "";
    }

    /**
     * Gets the name of the MenuItem.
     * @return the name of the MenuItem.
     */
    public String getName() {
        return name;
    }

    /**
     * Changes the name of the MenuItem to the given name.
     * @param name The new name of the MenuItem.
     */
    public void setName(String name) {
        this.name = name;
    }

    /** Gets the price of the MenuItem.
     * @return the price of the MenuItem.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Changes the price of the MenuItem to the given price.
     * @param price The new price of the MenuItem.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the type of the MenuItem.
     * @return the type of the MenuItem.
     */
    public ItemType getType() {
        return type;
    }

    /**
     * Changes the type of the MenuItem to the given type.
     * @param type The new type of the MenuItem.
     */
    public void setType(ItemType type) {
        this.type = type;
    }

    /**
     * Gets the description of the MenuItem.
     * @return the description of the MenuItem.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Changes the description of the MenuItem with the given description.
     * @param description The new description of the MenuItem.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Prints the details of the MenuItem including the name, price and type.
     * Also prints the description if asked to showDescription.
     * @param showDescription Value to decide whether to print MenuItem description.
     */
    public void printItem(boolean showDescription) {
        System.out.printf("$%.2f %s - %s\n", getPrice(), getName(), getType().toString());
        if (showDescription) {
            if (getDescription().equals("")) {
                System.out.println("No Description.");
            } else {
                System.out.printf("%s\n", getDescription());
            }
        }
    }

    /**
     * Prints the details of the MenuItem including the name, price and type.
     * The description is not printed.
     */
    public void printItem() {
        System.out.printf("(%.2f)%s - %s\n", getPrice(), getName(), getType().toString());
    }
}