package Table;

import java.util.Scanner;
import Reservation.ReservationMgr;

public class TableApp {
    static Scanner scanner = new Scanner(System.in);
    /**
     *Application that displays the Table Manager.
     *Options: Viewing Tables, Adding Tables, Removing Tables, Modifying Table Capacity.
     */
    public static void application(TableMgr tableMgr, ReservationMgr reservationMgr) {
        boolean running = true;
        while (running) {
            try {
                System.out.println(" -----Table Manager----- ");
                System.out.println(" (1) View Tables ");
                System.out.println(" (2) Add Tables ");
                System.out.println(" (3) Remove Table ");
                System.out.println(" (4) Modify Table capacity");
                System.out.println(" (5) Back ");
                System.out.print(" Choice: \t");
                int choice = scanner.nextInt();
                scanner.nextLine();
                System.out.println();
                switch (choice) {
                    case 1: { // View Tables
                        if (tableMgr.getNumOfTables() == 0) {
                            System.out.println("There are currently no tables.");
                        } else {
                            tableMgr.printListOfTables();
                            System.out.println("Total seating capacity: " + tableMgr.getSeatingCapacity());
                        }
                        System.out.println();
                        System.out.print("Enter to continue...");
                        scanner.nextLine();
                        break;
                    }
                    case 2: { // Add Tables
                        System.out.println("Number of tables to be added: ");
                        int numOfTables = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println();
                        if (numOfTables < 1) {
                            System.out.println("Invalid Input!");
                            System.out.println();
                            System.out.print("Enter to continue...");
                            scanner.nextLine();
                            break;
                        }
                        System.out.println("Do the tables have the same capacity? (Y/N)");
                        char option = scanner.next().charAt(0);
                        scanner.nextLine();
                        System.out.println();
                        boolean isSameCapacity;
                        if (option == 'Y' || option == 'y') {
                            isSameCapacity = true;
                        } else {
                            isSameCapacity = false;
                        }
                        if (isSameCapacity) {
                            System.out.println("Please enter the capacity of the tables to be added: ");
                            int capacity = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println();
                            if (!checkValid(capacity)) {
                                System.out.println("Please enter a valid capacity.");
                                System.out.println();
                                System.out.print("Enter to continue...");
                                scanner.nextLine();
                                break;
                            }
                            for (int i = 0; i < numOfTables; i++) {
                                tableMgr.addTable(capacity);
                            }
                            System.out.printf("%d tables have been added.\n", numOfTables);
                            System.out.println();
                            System.out.print("Enter to continue...");
                            scanner.nextLine();
                            break;
                        } else {
                            for (int i = 0; i < numOfTables; i++) {
                                System.out.println("Please enter the capacity of the next table to be added (enter -1 to stop early): ");
                                int capacity = scanner.nextInt();
                                scanner.nextLine();
                                if (capacity < 0) {
                                    break;
                                } else {
                                    if (!checkValid(capacity)) {
                                        System.out.println("Please enter a valid capacity.");
                                        System.out.println();
                                        i--;
                                    } else {
                                        tableMgr.addTable(capacity);
                                        System.out.println("Table has been added.");
                                        System.out.println();
                                    }
                                }
                            }
                        }
                        System.out.println();
                        System.out.print("Enter to continue...");
                        scanner.nextLine();
                        break;
                    }
                    case 3: { // Remove Table
                        if (tableMgr.getNumOfTables() == 0) {
                            System.out.println("There are currently no tables.");
                            System.out.println();
                            System.out.print("Enter to continue...");
                            scanner.nextLine();
                            break;
                        }
                        System.out.println("Enter table id to be removed: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println();
                        if (!tableMgr.tableExists(id)) {
                            System.out.println("Table ID does not exist.");
                            System.out.println();
                            System.out.print("Enter to continue...");
                            scanner.nextLine();
                            break;
                        }
                        if (tableMgr.getTableStatus(id) == TableStatus.EMPTY) {
                            tableMgr.removeTable(id);
                            System.out.println("Table has been removed.");
                        } else if (tableMgr.getTableStatus(id) == TableStatus.OCCUPIED) {
                            System.out.println("Table can't be removed, table is currently occupied.");
                        } else {
                            if (reservationMgr.checkReallocation(id)) {
                                reservationMgr.reallocateReservation(id);
                                tableMgr.removeTable(id);
                                System.out.println("Table has been removed.");
                            } else {
                                System.out.println("Table can't be removed, there are reservations on the table that cannot be reallocated.");
                                reservationMgr.printTableReservations(id);
                            }
                        }
                        System.out.println();
                        System.out.print("Enter to continue...");
                        scanner.nextLine();
                        break;
                    }
                    case 4: { // Modify table capacity
                        if (tableMgr.getNumOfTables() == 0) {
                            System.out.println("There are currently no tables.");
                            System.out.println();
                            System.out.print("Enter to continue...");
                            scanner.nextLine();
                            break;
                        }
                        System.out.println("Enter table id to be modified: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println();
                        if (!tableMgr.tableExists(id)) {
                            System.out.println("Table ID does not exist.");
                            System.out.println();
                            System.out.print("Enter to continue...");
                            scanner.nextLine();
                            break;
                        }
                        System.out.println("Enter new capacity: ");
                        int capacity = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println();
                        if (checkValid(capacity)) {
                            tableMgr.setTableCapacity(id, capacity);
                            System.out.println("Table " + id + " has been modified");
                        } else {
                            System.out.println("Invalid capacity.");
                        }
                        System.out.println();
                        System.out.print("Enter to continue...");
                        scanner.nextLine();
                        break;
                    }
                    case 5: { // back
                        running = false;
                        break;
                    }
                    default: {
                        System.out.println("Invalid Choice!");
                        System.out.println();
                        System.out.print("Enter to continue...");
                        scanner.nextLine();
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

    public static boolean checkValid(int capacity) {
        if (capacity <= 10 && capacity >= 2 && capacity % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }
}

