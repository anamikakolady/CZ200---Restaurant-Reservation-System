package Discount;

/**
 * Represents a discount to be applied to an order.
 * A discount can have only 1 value.
 */
public class Discount {

    /**
     * The name of this Discount.
     */
    String name;

    /**
     * The value of this Discount.
     */
    double value;

    /**
     * Default constructor to create a Discount.
     */
    public Discount() {}

    /**
     * Creates a new Discount with the given name and value.
     * @param name This Discount's name.
     * @param value This Discount's value.
     */
    public Discount(String name, double value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Gets the discount name.
     * @return this Discount's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Changes the discount name to the given name.
     * @param name This Discount's new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the discount value.
     * @return this Discount's value.
     */
    public double getValue() {
        return value;
    }

    /**
     * Changes the discount value to the given value.
     * @param value This Discount's new value
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Prints the discount name and value as output.
     * The discount value is formatted to 2 decimal places.
     */
    public void printDiscount() {
        System.out.printf("%s - %.2f\n", getName(), getValue());
    }
}