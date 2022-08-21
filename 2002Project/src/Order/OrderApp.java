package Order;

import Menu.*;
import Reservation.ReservationMgr;
import Revenue.*;
import Discount.*;
import Table.*;
import Staff.*;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Application that acts as an interface for the user to interact with the menu manager.
 */
public class OrderApp {
    static Scanner scanner = new Scanner(System.in);

    /**
     * The application provides an interface for the user to input commands to the order manager.
     * The application also prints the output returned from the order manager.
     * Options: Add Order Item, Modify Order, Show Order, Show Bill, Apply Discount, Make Payment.
     * @param orderMgr The order manager that handles requests from the user.
     * @param menuMgr The menu manager that handles requests from the user.
     * @param discountMgr The discount manager that handles requests from the user.
     * @param revenueMgr The revenue manager that handles requests from the user.
     * @param tableMgr The table manager that handles requests from the user.
     * @param reservationMgr The reservation manager that handles requests from the user.
     * @param staffMgr The staff manager that handles requests from the user.
     */
    public static void application(OrderMgr orderMgr, MenuMgr menuMgr, DiscountMgr discountMgr, RevenueMgr revenueMgr, TableMgr tableMgr, ReservationMgr reservationMgr, StaffMgr staffMgr) {
        boolean running = true;
        int staff_ID;
        int table_ID;
        try {
            System.out.print("Enter staff ID: ");
            staff_ID = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            if (!staffMgr.isExist(staff_ID)) {
                System.out.println("Invalid Staff ID");
                System.out.println();
                System.out.print("Enter to continue...");
                scanner.nextLine();
                return;
            }
            System.out.println();
            System.out.print("Enter table ID: ");
            table_ID = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            if (!tableMgr.tableExists(table_ID)) {
                System.out.println("Invalid Table ID");
                System.out.println();
                System.out.print("Enter to continue...");
                scanner.nextLine();
                return;
            }
            while (running) {
                System.out.println("-----Order Section-----");
                System.out.println("(1) Add Order Item");
                System.out.println("(2) Modify Order");
                System.out.println("(3) Show Order Items");
                System.out.println("(4) Show Bill");
                System.out.println("(5) Apply Discount");
                System.out.println("(6) Make Payment");
                System.out.println("(7) Back");
                System.out.print("Choice: \t");
                int choice = scanner.nextInt();
                scanner.nextLine();
                System.out.println();
                switch (choice) {
                    case 1: { // Add Order
                        if (!orderMgr.isExist(table_ID)) {
                            Table table = tableMgr.getTable(table_ID);
                            orderMgr.newOrder(table, discountMgr.getDiscount(0), staff_ID);
                        }
                        while (true) {
                            System.out.println("-----Item Types-----");
                            System.out.println("(1) STARTERS");
                            System.out.println("(2) MAINS ");
                            System.out.println("(3) DRINKS ");
                            System.out.println("(4) DESERTS");
                            System.out.println("(5) ITEM SETS");
                            System.out.println("(6) Back");
                            System.out.print("Choice: \t");
                            int choose = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println();
                            if (choose == 6) {
                                break;
                            } else if (choose >= 1 && choose <= 5) {
                                menuMgr.printSubMenu(ItemType.values()[choose - 1]);
                                System.out.print("Enter item index: ");
                                int input = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println();
                                if (input > 0 && input <= menuMgr.getMenuSize()) {
                                    MenuItem menuItem = menuMgr.getMenuItem(input - 1);
                                    orderMgr.addOrderItem(menuItem, table_ID);
                                    System.out.println("Item added to order.");
                                } else {
                                    System.out.println("Invalid Choice!");
                                }
                            } else {
                                System.out.println("Invalid Input.");
                            }
                            System.out.print("Add another item? (Y/N): ");
                            char option = scanner.next().charAt(0);
                            scanner.nextLine();
                            System.out.println();
                            if (option != 'Y' && option != 'y') {
                                break;
                            }
                        }
                        System.out.println();
                        System.out.print("Enter to continue...");
                        scanner.nextLine();
                        break;
                    }
                    case 2: { // Modify Order
                        int index = orderMgr.getIndex(table_ID);
                        if (index == -1) {
                            System.out.println("There is no existing order for this table.");
                            System.out.println("Please start an order.");
                            System.out.println();
                            System.out.print("Enter to continue...");
                            scanner.nextLine();
                            break;
                        } else if (orderMgr.getOrder(index).getNumOfItems() == 0) {
                            System.out.println("There are no items in order.");
                            System.out.println();
                            System.out.print("Enter to continue...");
                            scanner.nextLine();
                            break;
                        }
                        System.out.println("How would you like to modify the order: ");
                        System.out.println("(1) Cancel Order Item");
                        System.out.println("(2) Comp Order Item");
                        System.out.println("(3) Back");
                        int choose = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println();
                        if (choose == 3) {
                            System.out.println();
                            System.out.print("Enter to continue...");
                            scanner.nextLine();
                            break;
                        }
                        if (choose != 1 && choose != 2) {
                            System.out.println("Invalid input");
                            System.out.println();
                            System.out.print("Enter to continue...");
                            scanner.nextLine();
                            break;
                        }
                        orderMgr.printOrders(table_ID);
                        System.out.println();
                        System.out.println("Enter ID of order to change ");
                        int orderID = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println();
                        if (orderID < 0 || orderID >= orderMgr.getOrder(index).getNumOfItems()) {
                            System.out.println("Invalid Order ID!");
                            System.out.println();
                            System.out.print("Enter to continue...");
                            scanner.nextLine();
                            break;
                        }
                        switch (choose) {
                            case 1: { // cancel
                                orderMgr.cancelOrder(orderID, table_ID);
                                System.out.println("Order item has been canceled.");
                                break;
                            }
                            case 2: { // comp
                                orderMgr.compOrderItem(orderID, table_ID);
                                System.out.println("Order item has been made complimentary.");
                                break;
                            }
                            case 3: { // back
                                break;
                            }
                            default: {
                                System.out.println("Invalid input");
                            }
                        }
                        System.out.println();
                        System.out.print("Enter to continue...");
                        scanner.nextLine();
                        break;
                    }
                    case 3: { // Show Order Items
                        int index = orderMgr.getIndex(table_ID);
                        if (index == -1) {
                            System.out.println("There is no existing order for this table.");
                            System.out.println("Please start an order.");
                        } else if (orderMgr.getOrder(index).getNumOfItems() == 0) {
                            System.out.println("There are no items in order.");
                        } else {
                            System.out.println("Current Orders Items:");
                            orderMgr.printOrders(table_ID);
                        }
                        System.out.println();
                        System.out.print("Enter to continue...");
                        scanner.nextLine();
                        break;
                    }
                    case 4: { // Show Bill
                        int index = orderMgr.getIndex(table_ID);
                        if (index == -1) {
                            System.out.println("There is no existing order for this table.");
                            System.out.println("Please start an order.");
                            System.out.println();
                            System.out.print("Enter to continue...");
                            scanner.nextLine();
                            break;
                        }
                        System.out.println("--------- Nuits Blanches ----------");
                        System.out.println("       11 Schloss Schlaflos        ");
                        System.out.println("       Schulter, Schmerzland       ");
                        System.out.println();
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                        LocalDateTime now = LocalDateTime.now();
                        System.out.println(dtf.format(now));
                        System.out.println();
                        System.out.println("--------------------------------");
                        orderMgr.printOrders(table_ID);
                        System.out.println("-------------------------------- \n");
                        System.out.printf("Subtotal: $%.2f\n", orderMgr.getSubtotal(table_ID));
                        if (orderMgr.isDiscount(table_ID)) {
                            System.out.printf("After discount: $%.2f\n", orderMgr.getDiscounted(table_ID));
                        }
                        System.out.printf("GST: $%.2f\n", orderMgr.getGstCharge(table_ID));
                        System.out.printf("Service Charge: $%.2f\n", orderMgr.getServiceCharge(table_ID));
                        System.out.printf("Total: $%.2f\n", orderMgr.getTotalBill(table_ID));
                        System.out.println();
                        System.out.print("Enter to continue...");
                        scanner.nextLine();
                        break;
                    }
                    case 5: { // Apply Discount
                        int index = orderMgr.getIndex(table_ID);
                        if (index == -1) {
                            System.out.println("There is no existing order for this table.");
                            System.out.println("Please start an order.");
                            System.out.println();
                            System.out.print("Enter to continue...");
                            scanner.nextLine();
                            break;
                        }
                        discountMgr.printListOfDiscounts();
                        System.out.println("Select Discount Index");
                        int selection = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println();
                        if (selection < 0 || selection >= discountMgr.getNumOfDiscount()) {
                            System.out.println("Invalid Input!");
                            System.out.println();
                            System.out.print("Enter to continue...");
                            scanner.nextLine();
                            break;
                        }
                        orderMgr.setDiscount(table_ID, discountMgr.getDiscount(selection));
                        System.out.println("Discount has been added successfully.");
                        System.out.println();
                        System.out.print("Enter to continue...");
                        scanner.nextLine();
                        break;
                    }
                    case 6: { // Make Payment
                        int index = orderMgr.getIndex(table_ID);
                        if (index == -1) {
                            System.out.println("There is no existing order for this table.");
                            System.out.println("Please start an order.");
                            System.out.println();
                            System.out.print("Enter to continue...");
                            scanner.nextLine();
                            break;
                        }
                        revenueMgr.archive(orderMgr.paid(table_ID));
                        reservationMgr.vacated(table_ID, tableMgr);
                        System.out.println("Payment Made.");
                        System.out.println("This order has been archived.");
                        System.out.println();
                        System.out.print("Enter to continue...");
                        scanner.nextLine();
                        break;
                    }
                    case 7: { // back
                        running = false;
                        break;
                    }
                    default:
                        System.out.println("Invalid Choice!");
                        System.out.println();
                        System.out.print("Enter to continue...");
                        scanner.nextLine();
                        break;
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
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

