package Revenue;

import java.util.Scanner;

public class RevenueApp {
    static Scanner scanner = new Scanner(System.in);

    /**
     * Application for displaying the Revenue Manager.
     * Options: View Report(Daily Report/Monthly Report/Yearly Report)
     */
    public static void application(RevenueMgr revenueMgr) {
        boolean running = true;
        while (running) {
            try {
                System.out.print("""
                        (1) View Report
                        (2) Back
                        Choice:\s""");
                int choice = scanner.nextInt();
                scanner.nextLine();
                System.out.println();
                switch (choice) {
                    case 1: {
                        System.out.print("""
                                Please select frequency:
                                (1) Daily
                                (2) Monthly
                                (3) Yearly
                                Choice:\s""");
                        int freq = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println();
                        if (freq < 1 | freq > 3) {
                            System.out.println("Invalid Choice!");
                            System.out.println();
                            System.out.print("Enter to continue...");
                            scanner.nextLine();
                            break;
                        }
                        revenueMgr.printRevenuePeriod(Period.values()[freq - 1]);
                        System.out.println();
                        System.out.print("Enter to continue...");
                        scanner.nextLine();
                        break;
                    }
                    case 2: {
                        running = false;
                        break;
                    }

                    default:
                        System.out.println("Invalid Choice!");
                        System.out.println();
                        System.out.print("Enter to continue...");
                        scanner.nextLine();
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
                
