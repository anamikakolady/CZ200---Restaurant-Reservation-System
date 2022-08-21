package Menu;

import java.util.ArrayList;

/**
 * Represents an item set on the menu.
 * An item set is also a menu item.
 */
public class MenuItemSet extends MenuItem {

    /**
     * The list of MenuItems in this set.
     */
    private ArrayList<MenuItem> listOfItems;

    /**
     * Default constructor to create a MenuItemSet.
     */
    public MenuItemSet() {}

    /**
     * Creates a MenuItemSet with the given name, price and type.
     * @param name The name of this MenuItemSet.
     * @param price The price of this MenuItemSet.
     * @param type The type of this MenuItemSet.
     */
    public MenuItemSet(String name, double price, ItemType type) {
        super(name, price, type);
        this.listOfItems = new ArrayList<MenuItem>();
    }

    /**
     * Gets the list of items in the set.
     * @return the list of items.
     */
    public ArrayList<MenuItem> getListOfItems() {
        return listOfItems;
    }

    /**
     * Replaces the existing list of items with the given list of items.
     * @param listOfItems The new list of items.
     */
    public void setListOfItems(ArrayList<MenuItem> listOfItems) {
        this.listOfItems = listOfItems;
    }

    /**
     * Gets the number of items in the set.
     * This number is equal to the size of the set.
     * @return the number of items in the set.
     */
    public int getSetSize() {
        return listOfItems.size();
    }

    /**
     * Gets the name of an item with the given index in the set.
     * @param index The index of the item.
     * @return the name of the item.
     */
    public String getItemName(int index) {
        return listOfItems.get(index).getName();
    }

    /**
     * Adds the given item to the set.
     * @param item The item to be added.
     */
    public void addItem(MenuItem item) {
        listOfItems.add(item);
    }

    /**
     * Removes an item with the given index from the set.
     * @param index The index of the item.
     */
    public void removeItem(int index) {
        listOfItems.remove(index);
    }

    /**
     * Prints the items in the set along with their index in the set.
     * Prints each item's name, price and type.
     * Prints each item's description if asked to showDescription.
     * @param showDescription Value to decide whether to print MenuItem description.
     */
    public void printSetItems(boolean showDescription) {
        for (int i = 0; i < listOfItems.size(); i++) {
            System.out.printf("%d ", i + 1);
            listOfItems.get(i).printItem(showDescription);
        }
    }

    /**
     * Prints the items in the set.
     * Prints each item's name, price and type.
     * Item description is not printed.
     */
    public void printSetItems() {
        for (int i = 0; i < listOfItems.size(); i++) {
            System.out.print("  ");
            listOfItems.get(i).printItem();
        }
    }

    /**
     * Checks if there are no items in the set.
     * @return true if the set has no items, false otherwise.
     */
    public boolean hasNoItems() {
        return (listOfItems.size() == 0);
    }
}
