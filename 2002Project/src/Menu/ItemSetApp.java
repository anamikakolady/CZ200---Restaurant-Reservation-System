package Menu;

import java.util.Scanner;

/**
 * Application that specializes in getting user input and returning menu manager output.
 * relating to the items in a MenuItemSet.
 */
public class ItemSetApp {
    static Scanner scanner = new Scanner(System.in);

    /**
     * The application provides an interface for the user to access the items in a MenuItemSet.
     * Options: View Items, Add Item, Remove Item.
     * @param menuMgr The menu manager that handles requests from the user.
     * @param itemSet The item set to be accessed.
     */
    public static void application(MenuMgr menuMgr, MenuItemSet itemSet) {
        boolean running = true;
        while (running) {
            try {
                System.out.print("""
                        -----Item Set Editor-----
                        (1) View Item Set
                        (2) Add Item To Set
                        (3) Remove Item From Set
                        (4) Back
                        Choice:\s""");
                int choice = scanner.nextInt();
                scanner.nextLine();
                System.out.println();
                switch (choice) {
                    case 1 -> { // View ItemSet
                        if (itemSet.hasNoItems()) {
                            System.out.println("There are no items in set");
                        } else {
                            System.out.printf("Showing Items in %s:\n", itemSet.getName());
                            itemSet.printSetItems(menuMgr.isShowDescription());
                        }
                        System.out.println();
                        System.out.print("Enter to continue...");
                        scanner.nextLine();
                    }
                    case 2 -> { // Add Item To Set
                        ItemType type = MenuApp.chooseItemTypeNoSets();
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
                            MenuItem menuItem = menuMgr.getMenuItem(input - 1);
                            itemSet.addItem(menuItem);
                            System.out.printf("%s added.\n", menuItem.getName());
                        } else {
                            System.out.println("Invalid Choice!");
                        }
                        System.out.println();
                        System.out.print("Enter to continue...");
                        scanner.nextLine();
                    }
                    case 3 -> { // Remove Item From Set
                        if (itemSet.hasNoItems()) {
                            System.out.println("There are no items in set");
                            System.out.println();
                            System.out.print("Enter to continue...");
                            scanner.nextLine();
                            break;
                        } else {
                            System.out.printf("Showing Items in %s:\n", itemSet.getName());
                            itemSet.printSetItems(menuMgr.isShowDescription());
                        }
                        System.out.println();
                        System.out.println("Enter index of item to remove: ");
                        int input = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println();
                        if (input > 0 && input <= itemSet.getSetSize()) {
                            String name = itemSet.getItemName(input - 1);
                            itemSet.removeItem(input - 1);
                            System.out.printf("%s removed.\n", name);
                        } else {
                            System.out.println("Invalid Choice!");
                        }
                        System.out.println();
                        System.out.print("Enter to continue...");
                        scanner.nextLine();
                    }
                    case 4 -> running = false; // Back
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
}
