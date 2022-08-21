package Reservation;

import Table.TableMgr;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ReservationApp {
    static Scanner scanner = new Scanner(System.in);
    /**
     *Application for displaying the Reservation Manager
     *Options: View, Create, Remove and Modify Reservations
     */
    public static void application(ReservationMgr reservationMgr, TableMgr tableMgr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        boolean running = true;
        while (running) {
            try {
                System.out.println("-----Reservation Manager-----");
                System.out.println("(1) View Reservations");
                System.out.println("(2) Make Reservation");
                System.out.println("(3) Remove Reservation");
                System.out.println("(4) Modify Reservation");
                System.out.println("(5) Back");
                System.out.print("Choice: \t");
                int choice = scanner.nextInt();
                scanner.nextLine();
                System.out.println();
                switch (choice) {
                    case 1: { // View Reservations
                        if (!reservationMgr.hasReservations()) {
                            System.out.println("There are no reservations.");
                        } else {
                            reservationMgr.printAllReservations();
                        }
                        System.out.println();
                        System.out.print("Enter to continue...");
                        scanner.nextLine();
                        break;
                    }
                    case 2:{
                        System.out.println(LocalDateTime.now().format(formatter));
                        System.out.println("Enter the date and time of Reservation: ");
                        System.out.println("Format: dd-MM-yyyy HH:mm");
                        String dateTime = scanner.nextLine();
                        System.out.println();
                        LocalDateTime dateTimeObject = LocalDateTime.parse(dateTime, formatter);
                        System.out.println("Enter the number of people: ");
                        int pax = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println();
                        if (!reservationMgr.checkAvailability(pax, dateTimeObject,tableMgr)) {
                            System.out.println("No Available Time for Reservation!");
                        }
                        else {
                            System.out.println("Enter the name under the Reservation: ");
                            String name = scanner.nextLine();
                            System.out.println();
                            System.out.println("Enter the contact number: ");
                            String contact = scanner.nextLine();
                            System.out.println();
                            reservationMgr.newReservation(tableMgr, dateTime, pax, name, contact);
                            System.out.println("Reservation Successful!");
                        }
                        System.out.println();
                        System.out.print("Enter to continue...");
                        scanner.nextLine();
                        break;
                    }
                    case 3: { // Remove Reservations
                        reservationMgr.hasReservations();
                        if (!reservationMgr.hasReservations()) {
                            System.out.println("There are no reservations.");
                            System.out.println();
                            System.out.print("Enter to continue...");
                            scanner.nextLine();
                            break;
                        }
                        System.out.println("Enter contact of Reservation Holder: ");
                        String contact = scanner.nextLine();
                        System.out.println();
                        System.out.println("Enter the date and time of Reservation: ");
                        System.out.println("Format: dd-MM-yyyy HH:mm");
                        String dateTime = scanner.nextLine();
                        System.out.println();
                        LocalDateTime dateTimeObject = LocalDateTime.parse(dateTime, formatter);
                        if (!reservationMgr.isExist(contact, dateTimeObject))
                            System.out.println("No such reservation exists!");
                        else {
                            reservationMgr.removeReservation(contact, dateTimeObject);
                            System.out.println("Reservation removed!");
                        }
                        System.out.println();
                        System.out.print("Enter to continue...");
                        scanner.nextLine();
                        break;
                    }
                    case 4: { // Modify Reservations
                        if (!reservationMgr.hasReservations()) {
                            System.out.println("There are no reservations.");
                            System.out.println();
                            System.out.print("Enter to continue...");
                            scanner.nextLine();
                            break;
                        }
                        System.out.println("Enter contact of Reservation Holder: ");
                        String contact = scanner.nextLine();
                        System.out.println();
                        System.out.println("Enter the date and time of Reservation: ");
                        System.out.println("Format: dd-MM-yyyy HH:mm");
                        String dateTime = scanner.nextLine();
                        System.out.println();
                        LocalDateTime dateTimeObject = LocalDateTime.parse(dateTime, formatter);
                        if (reservationMgr.isExist(contact, dateTimeObject)) {
                            System.out.println("What would you like to modify?");
                            System.out.println("To change Pax or time, please remove and recreate Reservation");
                            System.out.println("(1) Name");
                            System.out.println("(2) Contact");
                            System.out.println("(3) Back");
                            System.out.print("Choice: \t");
                            int selection = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println();
                            Reservation reservation = reservationMgr.findReservation(contact, dateTimeObject);
                            switch (selection) {
                                case 1: { // modify name
                                    System.out.println("Enter the new name: ");
                                    String new_name = scanner.nextLine();
                                    reservationMgr.modifyReservation(contact, reservation.convertDateTime(), new_name);
                                    System.out.println("Name Updated!");
                                    System.out.println();
                                    System.out.print("Enter to continue...");
                                    scanner.nextLine();
                                    break;
                                }
                                case 2: { // modify contact
                                    System.out.println("Enter new contact number: ");
                                    String new_contact = scanner.nextLine();
                                    reservationMgr.modifyReservation(contact, new_contact, reservation.convertDateTime());
                                    System.out.println("Contact Number Updated!");
                                    System.out.println();
                                    System.out.print("Enter to continue...");
                                    scanner.nextLine();
                                    break;
                                }
                                case 3: // back
                                    break;
                                default:
                                    System.out.println("Invalid choice");
                                    System.out.println();
                                    System.out.print("Enter to continue...");
                                    scanner.nextLine();
                            }
                        } else {
                            System.out.println("No such reservation exists!");
                            System.out.println();
                            System.out.print("Enter to continue...");
                            scanner.nextLine();
                        }
                        break;
                    }
                    case 5: { // Back
                        running = false;
                        break;
                    }
                    default:
                        System.out.println("Invalid choice");
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