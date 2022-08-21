package Revenue;

import Order.Order;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * An archive of all historical orders
 */
public class RevenueMgr {
    /**
     * The archive of historical orders.
     */
    private ArrayList<Order> historicalOrders;

    /**
     * Creates the revenue manager.
     */
    public RevenueMgr() {
        this.historicalOrders = new ArrayList<Order>();
    }

    /**
     * Get this historical orders.
     * @return historical orders.
     */
    public ArrayList<Order> getHistoricalOrders() {
        return historicalOrders;
    }

    /**
     * Set the list of orders.
     * @param historicalOrders This is the list of historical orders.
     */
    public void setHistoricalOrders(ArrayList<Order> historicalOrders) {
        this.historicalOrders = historicalOrders;
    }

    /**
     * Adds a new order to the list.
     * @param order This is the paid order.
     */
    public void archive(Order order) {
        historicalOrders.add(order);
    }
    /**
     * Sorts the list of orders chronologically.
     */
    public void sort() {
        historicalOrders.sort(Comparator.comparing(Order::getDateTime));
    }

    /**
     * returns the number of orders.
     * @return number of orders.
     */
    public int numOfOrders() {
        return historicalOrders.size();
    }

    /**
     * returns an ArrayList of PeriodicSum, each PeriodicSum contains the orders for that specific day.
     * @return ArrayList of PeriodicSum.
     */
    public ArrayList<PeriodicSum> getDailyOrders() {
        sort();
        ArrayList<PeriodicSum> daily = new ArrayList<PeriodicSum>();
        PeriodicSum eachDay = new PeriodicSum();
        LocalDateTime date = historicalOrders.get(0).convertDateTime();
        LocalDateTime dateTime;
        boolean saved = true;
        for (int i = 0; i < historicalOrders.size(); i++) {
            dateTime = historicalOrders.get(i).convertDateTime();
            if ((date.getDayOfMonth() == dateTime.getDayOfMonth()) &&
                    (date.getMonthValue() == dateTime.getMonthValue()) &&
                    (date.getYear() == dateTime.getYear())) {
                eachDay.add(historicalOrders.get(i));
                saved = false;
            } else {
                daily.add(eachDay);
                eachDay = new PeriodicSum();
                date = historicalOrders.get(0).convertDateTime();
                eachDay.add(historicalOrders.get(i));
                saved = true;
            }
        }
        if (!saved) {
            daily.add(eachDay);
        }
        return daily;
    }

    /**
     * returns an ArrayList of PeriodicSum, each PeriodicSum contains the orders for that specific month.
     * @return ArrayList of PeriodicSum.
     */
    public ArrayList<PeriodicSum> getMonthlyOrders() {
        sort();
        ArrayList<PeriodicSum> monthly = new ArrayList<PeriodicSum>();
        PeriodicSum eachMonth = new PeriodicSum();
        LocalDateTime date = historicalOrders.get(0).convertDateTime();
        LocalDateTime dateTime;
        boolean saved = true;
        for (int i = 0; i < historicalOrders.size(); i++) {
            dateTime = historicalOrders.get(i).convertDateTime();
            if ((date.getMonthValue() == dateTime.getMonthValue()) &&
                    (date.getYear() == dateTime.getYear())) {
                eachMonth.add(historicalOrders.get(i));
                saved = false;
            } else {
                monthly.add(eachMonth);
                eachMonth = new PeriodicSum();
                date = historicalOrders.get(0).convertDateTime();
                eachMonth.add(historicalOrders.get(i));
                saved = true;
            }
        }
        if (!saved) {
            monthly.add(eachMonth);
        }
        return monthly;
    }
    /**
     * returns an ArrayList of PeriodicSum, each PeriodicSum contains the orders for that specific year.
     * @return ArrayList of PeriodicSum.
     */
    public ArrayList<PeriodicSum> getYearlyOrders() {
        sort();
        ArrayList<PeriodicSum> yearly = new ArrayList<PeriodicSum>();
        PeriodicSum eachYear = new PeriodicSum();
        LocalDateTime date = historicalOrders.get(0).convertDateTime();
        LocalDateTime dateTime;
        boolean saved = true;
        for (int i = 0; i < historicalOrders.size(); i++) {
            dateTime = historicalOrders.get(i).convertDateTime();
            if ((date.getMonthValue() == dateTime.getMonthValue()) &&
                    (date.getYear() == dateTime.getYear())) {
                eachYear.add(historicalOrders.get(i));
                saved = false;
            } else {
                yearly.add(eachYear);
                eachYear = new PeriodicSum();
                date = historicalOrders.get(0).convertDateTime();
                eachYear.add(historicalOrders.get(i));
                saved = true;
            }
        }
        if (!saved) {
            yearly.add(eachYear);
        }
        return yearly;
    }
    /**
     * Print the Revenue report based on the time period mentioned, daily/monthly/yearly.
     * @param period This is the time period.
     */
    public void printRevenuePeriod(Period period) {
        ArrayList<String> date;
        ArrayList<PeriodicSum> periodic;
        if (period == Period.DAILY) {
            periodic = getDailyOrders();
        } else if (period == Period.MONTHLY) {
            periodic = getMonthlyOrders();
        } else {
            periodic = getYearlyOrders();

        }

        date = getPeriod(periodic, period);
        for (int i = 0; i < date.size(); i++) {
            System.out.println("------------------------------");
            System.out.println(date.get(i));
            System.out.println("------------------------------");
            ArrayList<Integer> count = periodic.get(i).getCount();
            ArrayList<String> name = periodic.get(i).getUnique();
            System.out.println("Item name             Number Sold");
            System.out.println("---------             ------------");
            for (int j = 0; j < name.size(); j++) {
                System.out.printf("%-25s %d\n", name.get(j), count.get(j));
            }
            System.out.printf("Total: $%.2f\n", periodic.get(i).totalRevenue());
            System.out.println();
        }
    }

    /**
     * Get an array of dates based the period.
     * @param period This is the time period.
     * @param periodic This is a list of orders arranged based on the time period.
     * @return the date in a formatted order based on the period.
     */
    public ArrayList<String> getPeriod(ArrayList<PeriodicSum> periodic, Period period) {
        ArrayList<String> date = new ArrayList<String>();
        for (int i = 0; i < periodic.size(); i++) {
            date.add(periodic.get(i).getFormated(period));
        }
        return date;
    }

}

    