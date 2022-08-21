package Menu;

import java.util.Scanner;

/**
 * Application that acts as an interface for the user to interact with the menu manager.
 */
public class MenuApp {
    static Scanner scanner = new Scanner(System.in);

    /**
     * The application provides an interface for the user to input commands to the menu manager.
     * The application also prints the output returned from the menu manager.
     * Options: View Menu, View Sub-Menu, Add Item, Remove Item, Modify Item, Toggle Description.
     * @param menuMgr The menu manager that handles requests from the user.
     */
    public static void application(MenuMgr menuMgr) {
        boolean running = true;
        while (running) {
            try {
                System.out.print("""
                        -----Menu Manager-----
                        (1) View Menu
                        (2) View Sub-Menu
                        (3) Add Item
                        (4) Remove Item
                        (5) Modify Item
                        (6) Toggle Description
                        (7) Back
                        Choice:\s""");
                int choice = scanner.nextInt();
                scanner.nextLine();
                System.out.println();
                switch (choice) {
                    case 1 -> { // View Menu
                        if (menuMgr.hasNoItems()) {
                            System.out.println("There are no items on MENU.");
                        } else {
                            menuMgr.printMenu();
                        }
                        System.out.println();
                        System.out.print("Enter to continue...");
                        scanner.nextLine();
                    }
                    case 2 -> { // View Sub-Menu
                        ItemType type = chooseItemType();
                        if (type == null) {
                            System.out.println("Invalid Choice!");
                        } else if (menuMgr.hasNoItemType(type)) {
                            System.out.printf("There are no items on %s MENU.\n", type.toString());
                        } else {
                            menuMgr.printSubMenu(type);
                        }
                        System.out.println();
                        System.out.print("Enter to continue...");
                        scanner.nextLine();
                    }
                    case 3 -> { // Add Item
                        ItemType type = chooseItemType();
                        if (type == null) {
                            System.out.println("Invalid Choice!");
                            System.out.println();
                            System.out.print("Enter to continue...");
                            scanner.nextLine();
                            break;
                        }
                        System.out.print("Enter Item Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Item Price: ");
                        double price = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.println();
                        if (price >= 0.00) {
                            menuMgr.addItem(name, price, type);
                            System.out.println("Item Added.");
                            System.out.println();
                            System.out.print("Add Item Description? (Y/N): ");
                            char input = scanner.next().charAt(0);
                            scanner.nextLine();
                            int index = menuMgr.getMenuSize();
                            if (input == 'Y' || input == 'y') {
                                System.out.println();
                                System.out.print("Enter Description: ");
                                String description = scanner.nextLine();
                                menuMgr.setItemDescription(index - 1, description);
                                System.out.println("Description added.");
                            }
                            if (type == ItemType.ITEM_SETS) {
                                System.out.println();
                                MenuItemSet itemSet = (MenuItemSet)menuMgr.getMenuItem(index - 1);
                                ItemSetApp.application(menuMgr, itemSet);
                            }
                            menuMgr.sortMenu();
                        } else {
                            System.out.println("Invalid Price!");
                        }
                        System.out.println();
                        System.out.print("Enter to continue...");
                        scanner.nextLine();
                    }
                    case 4 -> { // Remove Item
                        ItemType type = chooseItemType();
                        if (type == null) {
                            System.out.println("Invalid Choice!");
                            System.out.println();
                            System.out.print("Enter to continue...");
                            scanner.nextLine();
                            break;
                        } else if (menuMgr.hasNoItemType(type)) {
                            System.out.printf("There are no items on %s MENU.\n", type.toString());
                            System.out.println();
                            System.out.print("Enter to continue...");
                            scanner.nextLine();
                            break;
                        } else {
                            menuMgr.printSubMenu(type);
                        }
                        System.out.println();
                        System.out.println("Enter index of item to remove: ");
                        int input = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println();
                        if (input > 0 && input <= menuMgr.getMenuSize()) {
                            String name = menuMgr.getItemName(input - 1);
                            menuMgr.removeItem(input - 1);
                            System.out.printf("%s removed.\n", name);
                        } else {
                            System.out.println("Invalid Choice!");
                        }
                        System.out.println();
                        System.out.print("Enter to continue...");
                        scanner.nextLine();
                    }
                    case 5 -> { // Modify Item
                        ItemType type = chooseItemType();
                        if (type == null) {
                            System.out.println("Invalid Choice!");
                            System.out.println();
                            System.out.print("Enter to continue...");
                            scanner.nextLine();
                            break;
                        } else if (menuMgr.hasNoItemType(type)) {
                            System.out.printf("There are no items on %s MENU.\n", type.toString());
                            System.out.println();
                            System.out.print("Enter to continue...");
                            scanner.nextLine();
                            break;
                        } else {
                            menuMgr.printSubMenu(type);
                        }
                        System.out.println();
                        System.out.println("Enter index of item to modify: ");
                        int input = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println();
                        if (input <= 0 || input > menuMgr.getMenuSize()) {
                            System.out.println("Invalid Choice!");
                            break;
                        }
                        String name = menuMgr.getItemName(input - 1);
                        System.out.printf("The current item name is %s\n", name);
                        System.out.print("Edit item name? (Y/N): ");
                        char option = scanner.next().charAt(0);
                        scanner.nextLine();
                        if (option == 'Y' || option == 'y') {
                            System.out.println();
                            System.out.print("Enter new item name: ");
                            name = scanner.nextLine();
                            menuMgr.setItemName(input - 1, name);
                            System.out.println();
                            System.out.println("Item name modified.");
                        }
                        System.out.println();
                        double price = menuMgr.getItemPrice(input - 1);
                        System.out.printf("The current price is %.2f\n", price);
                        System.out.print("Edit item price? (Y/N): ");
                        option = scanner.next().charAt(0);
                        scanner.nextLine();
                        if (option == 'Y' || option == 'y') {
                            System.out.println();
                            System.out.print("Enter new value: ");
                            price = scanner.nextDouble();
                            scanner.nextLine();
                            if (price < 0.00) {
                                System.out.println();
                                System.out.println("Invalid Input!");
                                System.out.println("Negative value is not accepted.");
                                System.out.println();
                                System.out.print("Enter to continue...");
                                scanner.nextLine();
                                break;
                            }
                            System.out.println();
                            menuMgr.setItemPrice(input - 1, price);
                            System.out.println();
                            System.out.println("Item price modified.");
                        }
                        System.out.println();
                        String description = menuMgr.getItemDescription(input - 1);
                        System.out.println("The current description is:");
                        if (description.equals("")) {
                            System.out.println("No Description.");
                        } else {
                            System.out.println(description);
                        }
                        System.out.print("Edit item description? (Y/N): ");
                        option = scanner.next().charAt(0);
                        scanner.nextLine();
                        if (option == 'Y' || option == 'y') {
                            System.out.println();
                            System.out.print("Enter new description: ");
                            description = scanner.nextLine();
                            menuMgr.setItemDescription(input - 1, description);
                            System.out.println();
                            System.out.println("Description modified.");
                        }
                        if (type == ItemType.ITEM_SETS) {
                            System.out.println();
                            System.out.print("Edit items in set? (Y/N): ");
                            option = scanner.next().charAt(0);
                            scanner.nextLine();
                            if (option == 'Y' || option == 'y') {
                                MenuItemSet itemSet = (MenuItemSet) menuMgr.getMenuItem(input - 1);
                                ItemSetApp.application(menuMgr, itemSet);
                                System.out.println();
                                System.out.println("Item set modified.");
                            }
                        }
                        System.out.println();
                        System.out.print("Enter to continue...");
                        scanner.nextLine();
                    }
                    case 6 -> { // Toggle Description
                        menuMgr.toggleDescription();
                        boolean shown = menuMgr.isShowDescription();
                        if (shown) {
                            System.out.println("Item Descriptions are now shown.");
                        } else {
                            System.out.println("Item Descriptions are now hidden.");
                        }
                        System.out.println();
                        System.out.print("Enter to continue...");
                        scanner.nextLine();
                    }
                    case 7 -> running = false; // Back
                    default -> {
                        System.out.println("Invalid Choice!");
                        System.out.println();
                        System.out.print("Enter to continue...");
                        scanner.nextLine();
                    }
                }
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println();
                System.out.println("Invalid Input!");
                System.out.println();
                System.out.print("Enter to continue...");
                scanner.nextLine();
            }
            System.out.println();
        }
    }

    /**
     * An interface to allow the user to choose from the available item types.
     * @return the item type chosen by the user.
     */
    public static ItemType chooseItemType() {
        try {
            System.out.print("""
                    -----Item Types-----
                    (1) Starters
                    (2) Mains
                    (3) Drinks
                    (4) Deserts
                    (5) Sets
                    
                    Enter index of item type:\s""");
            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            if (choice > 0 && choice <= ItemType.values().length) {
                return ItemType.values()[choice - 1];
            } else {
                return null;
            }
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println();
            return null;
        }
    }

    /**
     * An interface to allow the user to choose from the available item types except the set.
     * @return the item type chosen by the user.
     */
    public static ItemType chooseItemTypeNoSets() { // No Set Option
        try {
            System.out.print("""
                    -----Item Types-----
                    (1) Starters
                    (2) Mains
                    (3) Drinks
                    (4) Deserts
                    
                    Enter index of item type:\s""");
            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            if (choice > 0 && choice <= (ItemType.values().length - 1)) {
                return ItemType.values()[choice - 1];
            } else {
                return null;
            }
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println();
            return null;
        }
    }
}
