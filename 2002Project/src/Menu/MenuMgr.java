package Menu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

/**
 * Represents the menu of the restaurant.
 * The menu contains a list of MenuItems.
 */
public class MenuMgr {

    /**
     * The list of MenuItems.
     */
    private ArrayList<MenuItem> listOfMenuItems;

    /**
     * Option to show or hide descriptions when printing item information.
     */
    private boolean showDescription;

    /**
     * Creates a new MenuMgr containing an empty list of MenuItems.
     */
    public MenuMgr() {
        this.listOfMenuItems = new ArrayList<>();
        this.showDescription = true;
    }

    /**
     * Gets the list of MenuItems.
     * @return the list of MenuItems.
     */
    public ArrayList<MenuItem> getListOfMenuItems() {
        return listOfMenuItems;
    }

    /**
     * Replaces the existing list of MenuItems with the given list.
     * @param listOfMenuItems The new list of MenuItems.
     */
    public void setListOfMenuItems(ArrayList<MenuItem> listOfMenuItems) {
        this.listOfMenuItems = listOfMenuItems;
    }

    /**
     * Tells whether to show descriptions when printing items.
     * @return true if showDescription, otherwise false.
     */
    public boolean isShowDescription() {
        return showDescription;
    }

    /**
     * Changes the value of showDescription to the given value.
     * @param showDescription the new value of showDescription.
     */
    public void setShowDescription(boolean showDescription) {
        this.showDescription = showDescription;
    }

    /**
     * Toggles the value of showDescription from show to hide and vice versa.
     */
    public void toggleDescription() {
        showDescription = !showDescription;
    }

    /**
     * Gets the MenuItem at the given index in the menu.
     * @param index The index of the MenuItem.
     * @return the MenuItem.
     */
    public MenuItem getMenuItem(int index) {
        return listOfMenuItems.get(index);
    }

    /**
     * Gets the number of items on the menu.
     * This number is equal to the size of the menu.
     * @return the number of items on the menu.
     */
    public int getMenuSize() {
        return listOfMenuItems.size();
    }

    /**
     * Creates and adds an item to the menu with the given name, price and type.
     * @param name The name of the item.
     * @param price The price of the item.
     * @param type The type of the item.
     */
    public void addItem(String name, double price, ItemType type) {
        if (type == ItemType.ITEM_SETS) listOfMenuItems.add(new MenuItemSet(name, price, type));
        else {
            listOfMenuItems.add(new MenuItem(name, price, type));
        }
    }

    /**
     * Removes an item with the given index from the menu.
     * @param index The index of the item.
     */
    public void removeItem(int index) {
        listOfMenuItems.remove(index);
    }

    /**
     * Gets the name of the item with the given index.
     * @param index The index of the item.
     * @return the item name.
     */
    public String getItemName(int index) {
        return listOfMenuItems.get(index).getName();
    }

     /**
      * Changes the name of the item with the given index to the given name.
      * @param index The index of the item.
      * @param name The new name of the item.
      */
    public void setItemName(int index, String name) {
        listOfMenuItems.get(index).setName(name);
    }

    /**
     * Gets the price of the item with the given index.
     * @param index The index of the item.
     * @return the price of the item.
     */
    public double getItemPrice(int index) {
        return listOfMenuItems.get(index).getPrice();
    }

    /**
     * Changes the price of the item with the given index to the given price.
     * @param index The index of the item.
     * @param price The new price of the item.
     */
    public void setItemPrice(int index, double price) {
        listOfMenuItems.get(index).setPrice(price);
    }

    /**
     * Gets the description of the item with the given index.
     * @param index The index of the item.
     * @return the item description.
     */
    public String getItemDescription(int index) {
        return listOfMenuItems.get(index).getDescription();
    }

    /**
     * Changes the description of the item with the given index to the given description.
     * @param index The index of the item.
     * @param description The new description of the item.
     */
    public void setItemDescription(int index, String description) {
        listOfMenuItems.get(index).setDescription(description);
    }

    /**
     * Sorts the items on the menu according to their type.
     */
    public void sortMenu() {
        Collections.sort(listOfMenuItems, Comparator.comparing(MenuItem::getType));
    }

    /**
     * Prints all items on the menu along with their index.
     * Item description is printed if showDescription is true.
     */
    public void printMenu() {
        System.out.println("-----MENU-----");
        for (int i = 0; i < listOfMenuItems.size(); i++) {
            System.out.printf("%d ", i + 1);
            listOfMenuItems.get(i).printItem(showDescription);
        }
    }

    /**
     * Prints all items of the given type along with their index.
     * Item description is printed if showDescription is true.
     * Also prints all items in a set.
     * @param type The type of the items to print.
     */
    public void printSubMenu(ItemType type) {
        System.out.printf("-----%s MENU-----\n", type.toString());
        for (int i = 0; i < listOfMenuItems.size(); i++) {
            if (listOfMenuItems.get(i).getType() == type) {
                System.out.printf("%d ", i + 1);
                listOfMenuItems.get(i).printItem(showDescription);
                if (type == ItemType.ITEM_SETS) {
                    MenuItemSet itemSet = (MenuItemSet) listOfMenuItems.get(i);
                    itemSet.printSetItems();
                }
            }
        }
    }

    /**
     * Checks if the menu has no items.
     * @return true if the list has no menu items, false otherwise.
     */
    public boolean hasNoItems() {
        return (listOfMenuItems.size() == 0);
    }

    /**
     * Checks if the menu has no items of the given type.
     * @param type The type of item to search for.
     * @return true if the menu has no items of the given type, false otherwise.
     */
    public boolean hasNoItemType(ItemType type) {
        for (MenuItem menuItem : listOfMenuItems) {
            if (menuItem.getType() == type) {
                return false;
            }
        }
        return true;
    }
}
