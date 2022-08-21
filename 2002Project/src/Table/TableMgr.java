package Table;

import java.util.ArrayList;
import static java.util.Comparator.comparing;

/**
 * Stores all the tables in the restaurent and manages them
 */
public class TableMgr {
    /**
     * ArrayList containing all the tables.
     */
    private ArrayList<Table> listOfTables;

    /**
     * Create a new table manager.
     */
    public TableMgr() {
        listOfTables = new ArrayList<>();
    }

    /**
     * Gets the list of tables.
     * @return this list of table.
     */

    public ArrayList<Table> getListOfTables() {
        return listOfTables;
    }
    /**
     * Sets the number of tables.
     * @param listOfTables   This is the number of tables.
     */

    public void setListOfTables(ArrayList<Table> listOfTables) {
        this.listOfTables = listOfTables;
    }

    /**
     * gets the number of tables.
     * @return  this is the number of tables.
     */
    public int getNumOfTables() {
        return listOfTables.size();
    }

    /**
     * Adding a new table of certain capacity.
     * @param capacity This is the capacity of the new table.
     */
    public void addTable(int capacity) {
        Table newTable = new Table(listOfTables.size(), capacity);
        listOfTables.add(newTable);
    }

    /**
     * Removing a table.
     * @param tableNumber This is the table ID.
     */
    public void removeTable(int tableNumber) {
        listOfTables.remove(tableNumber);
        sort();
        reIndex();
    }

    /**
     * Checking if a table can be removed if its status is empty.
     * @param tableNumber This is the table number.
     * @return this true if table can be removed.
     */
    public boolean checkRemovable(int tableNumber) {
        if (listOfTables.get(tableNumber).getStatus() == TableStatus.EMPTY) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets the total seating capacity of the restaurant.
     * @return this is the seating capacity.
     */
    public int getSeatingCapacity() {
        int total = 0;
        for (int i = 0; i < listOfTables.size(); i++) {
            total += listOfTables.get(i).getCapacity();
        }
        return total;
    }

    /**
     * Gets the seating capacity of a particular table.
     * @param tableNumber This is the table number.
     * @return this is the seating capacity of this table.
     */
    public int getTableCapacity(int tableNumber) {
        return listOfTables.get(tableNumber).getCapacity();
    }

    /**
     * sets the seating capacity of a particular table.
     * @param tableNumber This is the table number.
     * @param capacity This is the seating capacity.
     */
    public void setTableCapacity(int tableNumber, int capacity) {
        listOfTables.get(tableNumber).setCapacity(capacity);
    }

    /**
     * Gets the table status.
     * @param tableNumber This is the table number.
     * @return this is the table's status.
     */
    public TableStatus getTableStatus(int tableNumber) {
        return listOfTables.get(tableNumber - 1).getStatus();
    }

    /**
     * Set table status.
     * @param tableNumber This is the table number.
     * @param status This is the table status.
     */
    public void setTableStatus(int tableNumber, TableStatus status) {
        listOfTables.get(tableNumber).setStatus(status);
    }

    /**
     * Sorts the tables.
     */
    private void sort() {
        listOfTables.sort(comparing(Table::getTableNumber));
    }

    /**
     * Re-indexes the tables.
     */
    private void reIndex() {
        for (int i = 0; i < listOfTables.size(); i++) {
            listOfTables.get(i).setTableNumber(i);
        }
    }

    /**
     * Finds tables that can accommodate the number of people.
     * @param pax This is the number of people.
     * @return this ArrayList of tables, sorted in increasing seating capacity.
     */
    public ArrayList<Table> possibleTables(int pax) {
        ArrayList<Table> possible = new ArrayList<>();
        for (int i = 0; i < listOfTables.size(); i++) {
            if (listOfTables.get(i).getCapacity() >= pax) {
                possible.add(listOfTables.get(i));
            }
        }
        if (possible.size() > 1) {
            possible.sort(comparing(Table::getCapacity));
        }
        return possible;
    }

    /**
     * Prints the list of tables.
     */
    public void printListOfTables() {
        sort();
        for (int i = 0; i < listOfTables.size(); i++) {
            Table table = listOfTables.get(i);
            System.out.printf("Table %d - %s - %d pax\n", table.getTableNumber(), table.getStatus().toString(), table.getCapacity());
        }
    }
    /**
     * Check if table exists.
     * @param id This is the table id.
     * @return this true if there is a table with this is else false.
     */
    public boolean tableExists(int id) {
        for (Table t : listOfTables) {
            if (t.getTableNumber() == id) {
                return true;
            }
        }
        return false;
    }
    /**
     * Get specific table based on table number.
     * @param id This is the table number.
     * @return this table object.
     */

    public Table getTable(int id) {
        for (Table t : listOfTables) {
            if (t.getTableNumber() == id) {
                return t;
            }
        }
        return null;
    }

    /**
     * when table is vacated.
     * @param tableID This is the table number.
     */
    public void vacated(int tableID) {
        setTableStatus(tableID, TableStatus.EMPTY);
    }
}