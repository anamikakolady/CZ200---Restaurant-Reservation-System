package Order;

import Menu.MenuItem;

/**
 * Represents an item on an order.
 * An order item contains its price at the time of entry.
 */
public class OrderItem {

    /**
     * The price of this OrderItem at the time of entry.
     */
    private double price;

    /**
     * The MenuItem this OrderItem refers to.
     */
    private MenuItem item;

    /**
     * Default constructor to create an OrderItem.
     */
    public OrderItem() {}

    /**
     * Creates an OrderItem associated with the given MenuItem.
     * The price of the OrderItem is set at the time of creation as the price of the MenuItem.
     * @param item The MenuItem this OrderItem refers to.
     */
    public OrderItem(MenuItem item) {
        this.price = item.getPrice();
        this.item = item;
    }

    /**
     * Gets the price of the OrderItem.
     * @return the price of the OrderItem.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Changes the price of the OrderItem to the given price.
     * @param price The new price of the OrderItem.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the MenuItem this OrderItem is associated with.
     * @return the MenuItem associated with this OrderItem.
     */
    public MenuItem getItem() {
        return item;
    }

    /**
     * Changes the associated MenuItem with the given item.
     * @param item The new MenuItem.
     */
    public void setItem(MenuItem item) {
        this.item = item;
    }

    /**
     * Gets the name of the MenuItem associated with the OrderItem.
     * @return the name of the item associated with the OrderItem.
     */
    public String getName() {
        return getItem().getName();
    }

    /**
     * Prints the name and price of this OrderItem.
     */
    public void printOrderItem() {
        System.out.printf("$%.2f %s\n", getPrice(), getName());
    }
}