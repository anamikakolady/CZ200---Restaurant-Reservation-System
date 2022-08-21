package Discount;

import java.util.ArrayList;

/**
 * Represents a list of discounts available.
 */
public class DiscountMgr {

    /**
     * The list of discounts.
     */
    private ArrayList<Discount> listOfDiscounts;

    /**
     * Creates a new DiscountMgr containing a list of discounts.
     * A default discount with value 0.00 is added first to the list.
     */
    public DiscountMgr() {
        this.listOfDiscounts = new ArrayList<Discount>();
        Discount defaultDiscount = new Discount("Default", 0.00);
        listOfDiscounts.add(defaultDiscount);
    }

    /**
     * Gets the list of discounts.
     * @return the list of discounts.
     */
    public ArrayList<Discount> getListOfDiscounts() {
        return listOfDiscounts;
    }

    /**
     * Replaces the existing list of discounts with the given list.
     * @param listOfDiscounts The new list of discounts.
     */
    public void setListOfDiscounts(ArrayList<Discount> listOfDiscounts) {
        this.listOfDiscounts = listOfDiscounts;
    }

    /**
     * Gets the name of a discount with the given index in the list.
     * @param index The index of the discount.
     * @return the name of the discount.
     */
    public String getDiscountName(int index) {
        return listOfDiscounts.get(index).getName();
    }

    /**
     * Changes the name of a discount with the given index to the given name.
     * @param index The index of the discounts.
     * @param name The new name of the discount.
     */
    public void setDiscountName(int index, String name) {
        listOfDiscounts.get(index).setName(name);
    }


    /**
     * Gets the value of a discount with the given index in the list.
     * @param index The index of the discount.
     * @return the value of the discount.
     */
    public double getDiscountValue(int index) {
        return listOfDiscounts.get(index).getValue();
    }

    /**
     * Changes the value of a discount with the given index to the given value.
     * @param index The index of the discount.
     * @param value The new value of the discount.
     */
    public void setDiscountValue(int index, double value) {
        listOfDiscounts.get(index).setValue(value);
    }

    /**
     * Gets the discount object containing its name and value.
     * @param index The index of the discount.
     * @return the discount object.
     */
    public Discount getDiscount(int index) {
        return listOfDiscounts.get(index);
    }

    /**
     * Creates and adds a new discount to the list with the given name and value.
     * @param name The discount name.
     * @param value The discount value.
     */
    public void addDiscount(String name, double value) {
        listOfDiscounts.add(new Discount(name, value));
    }

    /**
     * Removes a discount with the given index from the list.
     * @param index The index of the discount.
     */
    public void removeDiscount(int index) {
        listOfDiscounts.remove(index);
    }

    /**
     * Checks if the index of a discount can be found in the list.
     * @param index The index of the discount.
     * @return true if index is found, otherwise false.
     */
    public boolean findDiscount(int index) {
        return (index >= 0 && index < listOfDiscounts.size());
    }

    /**
     * Prints the details of a discount with the given index.
     * @param index The index of the discount.
     */
    public void printDiscount(int index) {
        listOfDiscounts.get(index).printDiscount();
    }

    /**
     * Prints all discounts in the list along with its index.
     */
    public void printListOfDiscounts() {
        System.out.println("-----List of Discounts-----");
        for (int i = 0; i < listOfDiscounts.size(); i++) {
            System.out.printf("(%d) ", i);
            listOfDiscounts.get(i).printDiscount();
        }
    }

    /**
     * Checks if there are no discounts in the list.
     * @return true if there is no discount in the list, false otherwise.
     */
    public boolean hasNoDiscount() {
        return (listOfDiscounts.size() == 0);
    }

    /**
     * Gets the total number of discounts in the list.
     * This number is equal to the size of the list.
     * @return total number of discounts.
     */
    public int getNumOfDiscount() {
        return listOfDiscounts.size();
    }
}