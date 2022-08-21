package Discount;

import java.util.Scanner;

/**
 * Application that acts as an interface for the user to interact with the discount manager.
 */
public class DiscountApp {
    static Scanner scanner = new Scanner(System.in);

    /**
     * The application provides an interface for the user to input commands to the discount manager.
     * The application also prints the output returned from the discount manager.
     * Options: Viewing Discounts, Adding Discounts, Removing Discounts, Editing Discounts.
     * @param discountMgr The discount manager that handles requests from the user.
     */
    public static void application(DiscountMgr discountMgr) {
        boolean running = true;
        while (running) {
            try {
                System.out.print("""
                        -----Discount Manager-----
                        (1) View Discounts
                        (2) Add Discount
                        (3) Remove Discount
                        (4) Modify Discount
                        (5) Back
                        Choice:\s""");
                int choice = scanner.nextInt();
                scanner.nextLine();
                System.out.println();
                switch (choice) {
                    case 1 -> { // View Discounts
                        if (discountMgr.hasNoDiscount()) {
                            System.out.println("There are no discounts.");
                        } else {
                            discountMgr.printListOfDiscounts();
                        }
                        System.out.println();
                        System.out.print("Enter to continue...");
                        scanner.nextLine();
                    }
                    case 2 -> { // Add Discount
                        System.out.print("Enter Discount Name: ");
                        String name = scanner.nextLine();
                        System.out.println("Range of Discount is (0.00 - 1.00)");
                        System.out.print("Enter Discount Value: ");
                        double value = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.println();
                        if (value >= 0.00 && value <= 1.00) {
                            discountMgr.addDiscount(name, value);
                            System.out.println("Discount added.");
                        } else {
                            System.out.println("Invalid Value!");
                            System.out.println("Range of Discount is (0.00 - 1.00)");
                        }
                        System.out.println();
                        System.out.print("Enter to continue...");
                        scanner.nextLine();
                    }
                    case 3 -> { // Remove Discount
                        if (discountMgr.hasNoDiscount()) {
                            System.out.println("There are no discounts.");
                        } else {
                            discountMgr.printListOfDiscounts();
                            System.out.println();
                            System.out.print("Enter index of discount to remove: ");
                            int input = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println();
                            if (discountMgr.findDiscount(input - 1)) {
                                discountMgr.removeDiscount(input - 1);
                                System.out.println("Discount removed.");
                            } else {
                                System.out.println("Invalid Choice!");
                            }
                        }
                        System.out.println();
                        System.out.print("Enter to continue...");
                        scanner.nextLine();
                    }
                    case 4 -> { // Modify Discount
                        if (discountMgr.hasNoDiscount()) {
                            System.out.println("There are no discounts.");
                        } else {
                            discountMgr.printListOfDiscounts();
                            System.out.println();
                            System.out.print("Enter index of discount to modify: ");
                            int input = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println();
                            if (discountMgr.findDiscount(input - 1)) {
                                System.out.println();
                                String name = discountMgr.getDiscountName(input - 1);
                                System.out.printf("The current discount name is %s\n", name);
                                System.out.print("Edit discount name? (Y/N): ");
                                char option = scanner.next().charAt(0);
                                scanner.nextLine();
                                if (option == 'Y' || option == 'y') {
                                    System.out.println();
                                    System.out.print("Enter new discount name: ");
                                    name = scanner.nextLine();
                                    discountMgr.setDiscountName(input - 1, name);
                                    System.out.println();
                                    System.out.println("Discount name modified.");
                                }
                                System.out.println();
                                double value = discountMgr.getDiscountValue(input - 1);
                                System.out.printf("The current discount value is %.2f\n", value);
                                System.out.print("Edit discount value? (Y/N): ");
                                option = scanner.next().charAt(0);
                                scanner.nextLine();
                                if (option == 'Y' || option == 'y') {
                                    System.out.println();
                                    System.out.println("Range of Discount is (0.00 - 1.00)");
                                    System.out.print("Enter new value: ");
                                    value = scanner.nextDouble();
                                    scanner.nextLine();
                                    if (value >= 0.00 && value <= 1.00) {
                                        discountMgr.setDiscountValue(input - 1, value);
                                        System.out.println();
                                        System.out.println("Discount value modified.");
                                    } else {
                                        System.out.println();
                                        System.out.println("Invalid Value!");
                                        System.out.println("Range of Discount is (0.00 - 1.00)");
                                    }
                                }
                                System.out.println();
                            } else {
                                System.out.println("Invalid Choice!");
                            }
                        }
                        System.out.println();
                        System.out.print("Enter to continue...");
                        scanner.nextLine();
                    }
                    case 5 -> running = false; // Back
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
