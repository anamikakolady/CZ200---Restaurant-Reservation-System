package Order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Table.*;

/**
 * Represents an order made by a table.
 * The order contains a list of OrderItems.
 * An order has 1 associated table.
 */
public class Order {

    /**
     * The list of OrderItems in the Order.
     */
    private ArrayList<OrderItem> listOfOrderItems;

    /**
     * The total price of the order.
     * This is the sum of the prices of all OrderItems in the order.
     */
    private double totalPrice;

    /**
     * The table that made this order.
     */
    private Table table;

    /**
     * The date and time when this order was paid.
     */
    private String dateTime;

    /**
     * The discount value applied to this order.
     */
    private double discountValue;

    /**
     * The goods and service tax applied to this order.
     */
    private double gst;

    /**
     * The service charge applied to this order.
     */
    private double svcCharge;

    /**
     * The ID of the staff who created this order.
     */
    private int staffID;

    /**
     * Defualt constructor to create a new order.
     */
    public Order() {
    }

    /**
     * Creates a new order with the given table, discount and staff ID.
     * The table that made this order is set to "occupied".
     * @param table The table that made this order.
     * @param discount The discount applied to this order.
     * @param staffID The ID of the staff who created this order.
     */
    public Order(Table table, double discount, int staffID) {
        this.listOfOrderItems = new ArrayList<OrderItem>();
        this.totalPrice = 0.0;
        this.table = table;
        this.dateTime = "";
        this.discountValue = discount;
        this.gst = 0.07;
        this.svcCharge = 0.10;
        this.staffID = staffID;
        this.table.setStatus(TableStatus.OCCUPIED);
    }

    /**
     * Gets the list of order items in this order.
     * @return the list of items in this order.
     */
    public ArrayList<OrderItem> getListOfOrderItems() {
        return listOfOrderItems;
    }

    /**
     * Replaces the existing list of order items with the given list.
     * @param listOfOrderItems The new list of order items.
     */
    public void setListOfOrderItems(ArrayList<OrderItem> listOfOrderItems) {
        this.listOfOrderItems = listOfOrderItems;
    }

    /**
     * Gets the total price of all order items in this order.
     * Discounts and extra charges are not included.
     * @return the total price.
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Changes the total price of the order with the given price.
     * @param totalPrice The new total price of this order.
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Gets the value of the discount applied to this order.
     * @return the value of the discount applied to this order.
     */
    public double getDiscountValue() {
        return discountValue;
    }

    /**
     * Changes the value of the discount to the given value.
     * @param discountValue The new discount value.
     */
    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }

    /**
     * Gets the value of the discount applied to this order.
     * @return the value of the discount applied to this order.
     */
    public double getDiscount() {
        return discountValue;
    }

    /**
     * Changes the value of the discount to the given value.
     * @param discount The new discount value.
     */
    public void setDiscount(double discount) {
        this.discountValue = discount;
    }

    /**
     * Checks if a discount has been applied to this order.
     * @return true if the discount value is not 0.
     */
    public boolean isDiscount() {
        if (discountValue == 0.00) {
            return false;
        }
        return true;
    }

    /**
     * Gets the table that made this order.
     * @return the table that the order is for.
     */
    public Table getTable() {
        return table;
    }
    /**
     * Gets the table id that made this order.
     * @return the table id that the order is for.
     */
    public int getTableID() {
        return table.getTableNumber();
    }
    /**
     * sets the table for this order.
     * @param table This is the table for this order.
     */
    public void setTable(Table table) {
        this.table = table;
    }

    /**
     * Sets the table status for this table that the order is for.
     * @param status This is the table status.
     */
    public void setTableStatus(TableStatus status) {
        table.setStatus(status);
    }

    /**
     * Return a LocalDateTime object form the date time string.
     * @return This date as a datetime object.
     */
    public LocalDateTime convertDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dateTimeObject = LocalDateTime.parse(dateTime, formatter);
        return dateTimeObject;
    }

    /**
     * Return the date that this order was paid.
     * @return this date.
     */
    public String getDateTime() {
        return dateTime;
    }
    /**
     * Set the date that this order was paid.
     * @param dateTime This is the date that the order was paid.
     */
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
    /**
     * Get the amount of the GST multiplier.
     * @return this order's GST multiplier.
     */
    public double getGst() {
        return gst;
    }
    /**
     * set the GST multiplier.
     * @param gst This is the GST multiplier.
     */
    public void setGst(double gst) {
        this.gst = gst;
    }
    /**
     * Get the service charge multiplier.
     * @return this order's service charge multiplier.
     */
    public double getSvcCharge() {
        return svcCharge;
    }
    /**
     * Set the service charge multiplier.
     * @param svcCharge This is the service charge multiplier.
     */
    public void setSvcCharge(double svcCharge) {
        this.svcCharge = svcCharge;
    }

    /**
     * Gets the staff ID.
     * @return the ID of the staff who made this order.
     */
    public int getStaffID() {
        return staffID;
    }
    /**
     * Set the staff ID who made this order.
     * @param staffID This is the staff's ID.
     */
    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }
    /**
     * Add new item to the order.
     * @param item This is the order item.
     */
    public void addOrderItem(OrderItem item) {
        listOfOrderItems.add(item);
        totalPrice += item.getPrice();
    }
    /**
     * Makes the item complimentary.
     * @param id This is the index of the item to be made complimentary.
     */
    public void compOrderItem(int id) {
        totalPrice -= listOfOrderItems.get(id).getPrice();
        listOfOrderItems.get(id).setPrice(0.0);
    }
    /**
     * Cancels item.
     * @param id  This is the index of the item to cancel.
     */
    public void cancelOrderItem(int id) {
        totalPrice -= listOfOrderItems.get(id).getPrice();
        listOfOrderItems.remove(id);
    }

    /**
     * Returns the subtotal of the bill before any multipliers.
     * @return this order's subtotal.
     */
    public double getSubtotal() {
        return totalPrice;
    }
    /**
     * Returns the discounted amount, but before gst and service charge.
     * @return this order's amount that is discounted.
     */
    public double getDiscounted() {
        return totalPrice * (1 - getDiscount());
    }
    /**
     * Returns service charge.
     * @return this order's service charge.
     */
    public double getTotalAfterServiceCharge() {
        return getDiscounted() * getSvcCharge();
    }
    /**
     * Returns the GST charge.
     * @return this order's GST charge.
     */
    public double getTotalAfterGstCharge() {
        return getDiscounted() * getGst();
    }
    /**
     * Returns the amount payable.
     * @return this order's amount payable.
     */
    public double getBill() {
        return getDiscounted() * (1 + getSvcCharge()) * (1 + getGst());
    }
    /**
     * Prints list of items in this order.
     */
    public void printOrder() {
        for (int i = 0; i < listOfOrderItems.size(); i++) {
            System.out.printf("%d ", i);
            listOfOrderItems.get(i).printOrderItem();
        }
    }

    /**
     * Gets the names of the unique items in this order.
     * @return An ArrayList of unique item names.
     */
    public ArrayList<String> getUnique() {
        ArrayList<String> unique = new ArrayList<String>();
        unique.add(listOfOrderItems.get(0).getName());
        for (int i = 1; i < listOfOrderItems.size(); i++) {
            boolean flag = true;
            for (int j = 0; j < unique.size(); j++) {
                if (unique.get(j) == listOfOrderItems.get(i).getName()) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                unique.add(listOfOrderItems.get(i).getName());
            }
        }
        return unique;
    }

    /**
     * Get the number of each item that was ordered.
     * @return number of each item in this order.
     */

    public ArrayList<Integer> getCount() {
        ArrayList<String> unique = getUnique();
        ArrayList<Integer> counter = new ArrayList<>();
        for (int i = 0; i < unique.size(); i++) {
            counter.add(0);
        }
        int x;
        for (int i = 0; i < listOfOrderItems.size(); i++) {
            for (int j = 0; j < unique.size(); j++) {
                if (unique.get(j) == listOfOrderItems.get(i).getName()) {
                    x = counter.get(j) + 1;
                    counter.set(j, x);
                    break;

                }
            }
        }
        return counter;
    }

    /**
     * Gets the number of items ordered.
     * @return number of items ordered in this order.
     */

    public int getNumOfItems() {
        return listOfOrderItems.size();
    }
}