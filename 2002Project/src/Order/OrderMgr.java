package Order;

import Discount.Discount;
import Menu.MenuItem;
import Table.Table;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.*;

/**
 * Represents the list of orders the restaurant.
 */
public class OrderMgr {

    /**
     * The list of orders.
     */
    private ArrayList<Order> listOfOrders;

    /**
     * Creates a new OrderMgr containing an empty list of orders.
     */
    public OrderMgr() {
        this.listOfOrders = new ArrayList<Order>();
    }

    /**
     * Gets the list of orders.
     * @return the list of orders.
     */
    public ArrayList<Order> getListOfOrders() {
        return listOfOrders;
    }

    /**
     * Replaces the existing list of orders with the given list.
     * @param listOfOrders The new list of orders.
     */
    public void setListOfOrders(ArrayList<Order> listOfOrders) {
        this.listOfOrders = listOfOrders;
    }

    /**
     * Gets an order with the given index from the list.
     * @param index The index of the order.
     * @return the order at the given index.
     */
    public Order getOrder(int index) {
        return listOfOrders.get(index);
    }

    /**
     * Creates and adds a new order with the given table, discount and staff ID.
     * @param table The table that made this order.
     * @param discount The discount applied to this order.
     * @param staffID The ID of the staff who created this order.
     */
    public void newOrder(Table table, Discount discount, int staffID) {
        listOfOrders.add(new Order(table, discount.getValue(), staffID));
    }

    /**
     * Creates and adds a new order item with the given menu item and table ID.
     * @param order The menu item associated with the new order item.
     * @param tableID The ID of the table that made the order.
     */
    public void addOrderItem(MenuItem order, int tableID) {
        listOfOrders.get(getIndex(tableID)).addOrderItem(new OrderItem(order));
    }

    /**
     * Sorts the list of orders according to their table ID.
     */
    public void sort() {
        listOfOrders.sort(Comparator.comparing(Order::getTableID));
    }

    /**
     * Checks if an order has been made by a table with the given ID.
     * @param tableID The ID of the table that made an order.
     * @return true if an order has been made, otherwise false.
     */
    public boolean isExist(int tableID) {
        for (int i = 0; i < listOfOrders.size(); i++) {
            if (listOfOrders.get(i).getTableID() == tableID) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the index of the order made by a table with the given ID.
     * @param tableID the ID of the table that made the order.
     * @return the index of the order, -1 if an order was not found.
     */
    public int getIndex(int tableID) {
        for (int i = 0; i < listOfOrders.size(); i++) {
            if (listOfOrders.get(i).getTableID() == tableID) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Cancels an order item of the given ID on an order made by a table with the given table ID.
     * @param id The ID of the order item.
     * @param tableID The ID of the table that made the order.
     */
    public void cancelOrder(int id, int tableID) {
        listOfOrders.get(getIndex(tableID)).cancelOrderItem(id);
    }

    /**
     * Applies the given discount to the order made by the table with the given ID.
     * @param tableID The ID of the table that made the order.
     * @param discount The discount to be applied.
     */
    public void setDiscount(int tableID, Discount discount) {
        listOfOrders.get(getIndex(tableID)).setDiscount(discount.getValue());
    }

    /**
     * Prints the order items of an order made by a table with the given ID.
     * @param tableID The ID of the table that made the order.
     */
    public void printOrders(int tableID) {
        listOfOrders.get(getIndex(tableID)).printOrder();
    }

    /**
     * Comps an order item of the given ID on an order made by a table with the given table ID.
     * The price of the order item is set to 0.
     * @param id The ID of the order item.
     * @param tableID The ID of the table that made the order.
     */
    public void compOrderItem(int id, int tableID) {
        listOfOrders.get(getIndex(tableID)).compOrderItem(id);
    }

    /**
     * Gets the subtotal of an order made by the table with the given table ID.
     * @param tableID The ID of the table that made the order.
     * @return the subtotal of the order.
     */
    public double getSubtotal(int tableID) {
        return listOfOrders.get(getIndex(tableID)).getSubtotal();
    }

    /**
     * Gets the discounted price of an order made by the table with the given table ID.
     * @param tableID The ID of the table that made the order.
     * @return the discounted price of the order.
     */
    public double getDiscounted(int tableID) {
        return listOfOrders.get(getIndex(tableID)).getDiscounted();
    }

    /**
     * Gets the GST applied to an order made by the table with the given table ID.
     * @param tableID The ID of the table that made the order.
     * @return the GST applied to the order.
     */
    public double getGstCharge(int tableID) {
        return listOfOrders.get(getIndex(tableID)).getGst();
    }

    /**
     * Gets the service charge applied to an order made by the table with the given table ID.
     * @param tableID The ID of the table that made the order.
     * @return the service charge applied to the order.
     */
    public double getServiceCharge(int tableID) {
        return listOfOrders.get(getIndex(tableID)).getSvcCharge();
    }

    /**
     * Gets the total bill of an order made by the table with the given table ID.
     * The total bill is the price after GST and service charge.
     * @param tableID The ID of the table that made the order.
     * @return the total bill of the order.
     */
    public double getTotalBill(int tableID) {
        return listOfOrders.get(getIndex(tableID)).getBill();
    }

    /**
     * Checks if a discount has been applied to an order made by the table with the given table ID.
     * @param tableID The ID of the table that made the order.
     * @return true if a discount has been applied, false otherwise.
     */
    public boolean isDiscount(int tableID) {
        return listOfOrders.get(getIndex(tableID)).isDiscount();
    }

    /**
     * Marks an order made by the table with the given table ID as paid.
     * Sets the date and time of the order as the time of payment.
     * Removes the order from the current list of orders.
     * The table that made this order is set to unoccupied.
     * @param tableID The ID of the table that made the order.
     * @return the order.
     */
    public Order paid(int tableID) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        listOfOrders.get(getIndex(tableID)).setDateTime(currentDateTime.format(formatter));
        Order hold = listOfOrders.get(getIndex(tableID));
        listOfOrders.remove(getIndex(tableID));
        return hold;
    }

    /**
     * Checks if there are any orders in the list.
     * @return true if an order exists, false otherwise.
     */
    public boolean hasOrders() {
        return (!listOfOrders.isEmpty());
    }
}
