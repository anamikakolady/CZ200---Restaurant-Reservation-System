package Staff;

import java.util.Scanner;

public class StaffApp {
    static Scanner scanner = new Scanner(System.in);

    /**
    *Application that displays the staff Menu.
    *Options: Viewing Staff, Adding Staff, Removing Staff, Editing Staff.
    */
    public static void application(StaffMgr staffMgr) {
        boolean running = true;
        while (running) {
            try {
                System.out.println("-----Staff Menu-----");
                System.out.println("(1) View Staff");
                System.out.println("(2) Add Staff");
                System.out.println("(3) Remove Staff");
                System.out.println("(4) Edit Staff");
                System.out.println("(5) Back");
                System.out.print("Choice: \t");
                int choice = scanner.nextInt();
                scanner.nextLine();
                System.out.println();
                switch (choice) {
                    case 1: { // View Staff
                        if (staffMgr.isEmpty()) {
                            System.out.println("There are no registered staff.");
                        } else {
                            staffMgr.printStaffList();
                        }
                        System.out.println();
                        System.out.print("Enter to continue...");
                        scanner.nextLine();
                        break;
                    }
                    case 2: { // Add Staff
                        System.out.println("Enter new staff ID: ");
                        int ID = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println();
                        if (!staffMgr.isEmpty()) {
                            if (staffMgr.isExist(ID)) {
                                System.out.println("ID already exists.");
                                System.out.println();
                                System.out.print("Enter to continue...");
                                scanner.nextLine();
                                break;
                            }
                        }
                        System.out.println("Enter new staff name: ");
                        String name = scanner.nextLine();
                        System.out.println();
                        System.out.println("Select new staff gender: ");
                        int count = 0;
                        for (Gender g : Gender.values()) {
                            System.out.println(count + ": " + g);
                            count++;
                            if (count == 3) {
                                break;
                            }
                        }
                        int gender = scanner.nextInt();
                        scanner.nextLine();
                        if (gender < 0 || gender >= Gender.values().length) {
                            System.out.println("Invalid Input!");
                            System.out.println();
                            System.out.print("Enter to continue...");
                            scanner.nextLine();
                            break;
                        }
                        System.out.println();
                        System.out.println("Enter new staff job title: ");
                        String role = scanner.nextLine();
                        System.out.println();
                        staffMgr.addStaff(name, Gender.values()[gender], ID, role);
                        System.out.println("Registration Successful!");
                        System.out.println();
                        System.out.print("Enter to continue...");
                        scanner.nextLine();
                        break;
                    }
                    case 3: { // Remove Staff
                        if (staffMgr.isEmpty()) {
                            System.out.println("There are no registered staff.");
                            System.out.println();
                            System.out.print("Enter to continue...");
                            scanner.nextLine();
                            break;
                        }
                        System.out.println("Enter staff ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println();
                        if (staffMgr.isExist(id)) {
                            staffMgr.removeStaff(id);
                            System.out.println("Staff removed.");
                        } else {
                            System.out.println("ID does not exist.");
                        }
                        System.out.println();
                        System.out.print("Enter to continue...");
                        scanner.nextLine();
                        break;
                    }
                    case 4: { // Edit Staff
                        if (staffMgr.isEmpty()) {
                            System.out.println("There are no registered staff.");
                            System.out.println();
                            System.out.print("Enter to continue...");
                            scanner.nextLine();
                            break;
                        }
                        System.out.println("Enter staff ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println();
                        if (staffMgr.isExist(id)) {
                            System.out.println("What would you like to modify?");
                            System.out.println("(1) ID");
                            System.out.println("(2) Name");
                            System.out.println("(3) Gender");
                            System.out.println("(4) Job Title");
                            System.out.println("(5) Back");
                            System.out.print("Choice: \t");
                            int selection = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println();
                            switch (selection) {
                                case 1: // modify ID
                                    System.out.println("Enter new ID:");
                                    int new_id = scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.println();
                                    staffMgr.modifyStaff(id, new_id);
                                    System.out.println("Staff ID modified.");
                                    System.out.println();
                                    System.out.print("Enter to continue...");
                                    scanner.nextLine();
                                    break;
                                case 2: // modify name
                                    System.out.println("Enter new Name");
                                    String new_name = scanner.next();
                                    scanner.nextLine();
                                    System.out.println();
                                    staffMgr.modifyStaff(id, new_name);
                                    System.out.println("Staff name modified.");
                                    System.out.println();
                                    System.out.print("Enter to continue...");
                                    scanner.nextLine();
                                    break;
                                case 3: // modify gender
                                    System.out.println("Select staff gender: ");
                                    int counter = 0;
                                    for (Gender g : Gender.values()) {
                                        System.out.println(counter + ": " + g);
                                        counter++;
                                        if (counter == 3) {
                                            break;
                                        }
                                    }
                                    int new_gender = scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.println();
                                    if (new_gender < 0 || new_gender >= Gender.values().length) {
                                        System.out.println("Invalid Input!");
                                        System.out.println();
                                        System.out.print("Enter to continue...");
                                        scanner.nextLine();
                                        break;
                                    }
                                    staffMgr.modifyStaff(id, Gender.values()[new_gender]);
                                    System.out.println("Staff gender modified.");
                                    System.out.println();
                                    System.out.print("Enter to continue...");
                                    scanner.nextLine();
                                    break;
                                case 4: // modify job title
                                    System.out.println("Enter new Job Title");
                                    String new_role = scanner.nextLine();
                                    System.out.println();
                                    staffMgr.modifyStaff(new_role, id);
                                    System.out.println("Staff job title modified.");
                                    System.out.println();
                                    System.out.print("Enter to continue...");
                                    scanner.nextLine();
                                    break;
                                case 5: // back
                                    break;
                                default:
                                    System.out.println("Invalid choice");
                                    System.out.println();
                                    System.out.print("Enter to continue...");
                                    scanner.nextLine();
                            }
                        } else {
                            System.out.println("ID does not exist.");
                            System.out.println();
                            System.out.print("Enter to continue...");
                            scanner.nextLine();
                        }
                        break;
                    }
                    case 5: { // back
                        running = false;
                        break;
                    }
                    default: {
                        System.out.println("Invalid choice");
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
}

