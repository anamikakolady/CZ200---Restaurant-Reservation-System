package Revenue;

import Order.Order;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A list of all orders that are in the same time period(DAILY/MONTHLY/YEARLY),
 */
public class PeriodicSum {
    /**
     * List of orders in one time period.
     */
    private ArrayList<Order> orders;

    /**
     * Default constructor for PeriodicSum
     */
    public PeriodicSum() {
        this.orders = new ArrayList<Order>();
    }

    /**
     * Adds an order.
     * @param order This is an order
     */
    public void add(Order order) {
        orders.add(order);
    }

    /**
     * Gets the orders
     * @return These are the orders
     */
    public ArrayList<Order> getOrders() {
        return orders;
    }

    /**
     * loads in list of orders.
     * @param orders This is the list of orders.
     */
    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    /**
     * Get the formatted order of the Date.
     * @param period This is the period(DAILY,MONTHLY,YEARLY).
     * @return the formatted order for daily/monthly/yearly.
     */
    public String getFormated(Period period) {
        if (period == Period.DAILY) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return orders.get(0).convertDateTime().format(formatter);
        } else if (period == Period.MONTHLY) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
            return orders.get(0).convertDateTime().format(formatter);
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
            return orders.get(0).convertDateTime().format(formatter);
        }
    }

    /**
     * Gets the total Revenue.
     * @return this is the total revenue.
     */
    public double totalRevenue() {
        double total = 0;
        for (int i = 0; i < orders.size(); i++) {
            total += orders.get(i).getDiscounted();
        }
        return total;
    }

    /**
     * Gets all the unique names of items in the period.
     * @return a list of unique item names.
     */
    public ArrayList<String> getUnique() {
        ArrayList<String> unique = orders.get(0).getUnique();
        for (int i = 1; i < orders.size(); i++) {
            ArrayList<String> next = orders.get(i).getUnique();
            for (int j = 0; j < next.size(); j++) {
                boolean flag = true;
                for (int k = 0; k < unique.size(); k++) {
                    if (Objects.equals(unique.get(k),next.get(j))) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    unique.add(next.get(j));
                }
            }
        }
        return unique;
    }

    /**
     * Gets the count of items in the list.
     * @return the count of items in the list.
     */
    public ArrayList<Integer> getCount() {
        ArrayList<Integer> count = new ArrayList<>();
        ArrayList<String> unique = getUnique();
        for (int i = 0; i < unique.size(); i++) {
            count.add(0);
        }
        int x;
        for (int i = 0; i < orders.size(); i++) {
            ArrayList<String> nextNames = orders.get(i).getUnique();
            ArrayList<Integer> nextCount = orders.get(i).getCount();
            for (int j = 0; j<nextNames.size(); j++){
                for(int k = 0; k<unique.size(); k++){
                    if(Objects.equals(unique.get(k),nextNames.get(j))){
                        x = count.get(k) + nextCount.get(j);
                        count.set(k, x);
                        break;
                    }
                }
            }
        }
        return count;
    }
}

    