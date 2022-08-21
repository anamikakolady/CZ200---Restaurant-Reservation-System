import java.util.Scanner;
import Discount.*;
import Menu.*;
import Order.*;
import Reservation.*;
import Revenue.*;
import Staff.*;
import Table.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Data data = Data.LoadData();
        System.out.println();
        DiscountMgr discountMgr = data.getDiscountMgr();
        MenuMgr menuMgr = data.getMenuMgr();
        OrderMgr orderMgr = data.getOrderMgr();
        ReservationMgr reservationMgr = data.getReservationMgr();
        RevenueMgr revenueMgr = data.getRevenueMgr();
        StaffMgr staffMgr = data.getStaffMgr();
        TableMgr tableMgr = data.getTableMgr();

        boolean running = true;
        while (running) {
            try {
                System.out.print("""
                        -----Main-----
                        (1) Discount Manager
                        (2) Menu Manager
                        (3) Order Manager
                        (4) Reservation Manager
                        (5) Revenue Manager
                        (6) Staff Manager
                        (7) Table Manager
                        (8) Save
                        (9) Save & Exit
                        Choice:\s""");
                int choice = scanner.nextInt();
                scanner.nextLine();
                System.out.println();
                switch (choice) {
                    case 1 -> DiscountApp.application(discountMgr);
                    case 2 -> MenuApp.application(menuMgr);
                    case 3 -> OrderApp.application(orderMgr, menuMgr, discountMgr, revenueMgr, tableMgr, reservationMgr,staffMgr);
                    case 4 -> ReservationApp.application(reservationMgr, tableMgr);
                    case 5 -> RevenueApp.application(revenueMgr);
                    case 6 -> StaffApp.application(staffMgr);
                    case 7 -> TableApp.application(tableMgr, reservationMgr);
                    case 8 -> { // Save
                        System.out.println();
                        Data.SaveData(data);
                        System.out.println();
                        System.out.print("Enter to continue...");
                        scanner.nextLine();
                    }
                    case 9 -> running = false; // Save & Exit
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
        System.out.println();
        Data.SaveData(data);
        System.out.println("Exiting program...");
        scanner.nextLine();
        System.exit(0);
    }
}

