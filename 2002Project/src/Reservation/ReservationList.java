package Reservation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.Duration;
import java.util.Comparator;
import java.util.Objects;

import Table.*;

/**
 * Represents a list of reservations for a table.
 */
public class ReservationList {
    /**
     * List of reservations for 1 specific table.
     */
    private ArrayList<Reservation> reservationList;

    /**
     * The table for which the list of reservations is for.
     */
    private Table table;

    /**
     * Default constructor to create a ReservationList.
     */
    public ReservationList() {}

    /**
     * Creates a new ReservationList with the given table.
     * @param table The table at which reservations are made.
     */
    public ReservationList(Table table) {
        reservationList = new ArrayList<Reservation>();
        this.table = table;
    }

    /**
     * Gets the reservation list.
     * @return the reservation list.
     */
    public ArrayList<Reservation> getReservationList() {
        return reservationList;
    }

    /**
     * Replaces the existing reservation list with the given list.
     * @param reservationList The new reservation list.
     */
    public void setReservationList(ArrayList<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    /**
     * Gets the number of reservations.
     * @return the number of reservation.
     */
    public int getNumberOfReservations() {
        return reservationList.size();
    }

    /**
     * Gets the reservation at the given index in the list.
     * @param index The index of the reservation.
     * @return the reservation at the index.
     */
    public Reservation getReservation(int index) {
        return reservationList.get(index);
    }

    /**
     * Gets the table associated with this reservation list.
     * @return the table object.
     */
    public Table getTable() {
        return table;
    }

    /**
     * Changes the table associated with this reservation list with the given table.
     * @param table The new table object.
     */
    public void setTable(Table table) {
        this.table = table;
    }

    /**
     * Gets the table number.
     * @return the table number.
     */
    public int getTableNumber() {
        return table.getTableNumber();
    }

    /**
     * Gets the table capacity.
     * @return the table capacity.
     */
    public int getTableCapacity() {
        return table.getCapacity();
    }

    /**
     * Check if a particular timeslot is available for reservation.
     * @return true if a slot is available, false otherwise.
     */
    public boolean isAvailable(LocalDateTime reservationTime) {
        for (int i = 0; i < reservationList.size(); i++) {
            Duration duration = Duration.between(reservationList.get(i).convertDateTime(), reservationTime);
            if (duration.abs().toMinutes() < 120) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds a reservation to the list.
     * @param reservation The reservation to be added.
     */
    public void addReservation(Reservation reservation) {
        reservationList.add(reservation);
        if (reservationList.size() > 1) {
            sort();
        }
        table.setStatus(TableStatus.RESERVED);
    }

    /**
     * Removes a reservation with the given contact and given date and time of reservation.
     * @param contact The contact associated with the reservation.
     * @param dateTimeObject The date and time of the reservation.
     */
    public void removeReservation(String contact, LocalDateTime dateTimeObject) {
        for (int i = 0; i < reservationList.size(); i++) {
            if ((Objects.equals(contact, reservationList.get(i).getContact())) && (dateTimeObject.compareTo(reservationList.get(i).convertDateTime())==0)) {
                reservationList.get(i).removeTimer();
                reservationList.remove(i);
                if (reservationList.size() == 0) {
                    table.setStatus(TableStatus.EMPTY);
                }
                break;
            }
        }
    }

    /**
     * Removes a reservation at the given index from the list.
     * @param index The index of the reservation.
     */
    public void removeReservation(int index) {
        reservationList.remove(index);
    }

    /**
     * Gets the reservation with the given contact and given date and time of the reservation.
     * @param contact The contact associated with the reservation.
     * @param dateTimeObject The date and time of the reservation.
     * @return the reservation.
     */
    public Reservation findReservation(String contact, LocalDateTime dateTimeObject) {
        for (int j = 0; j < reservationList.size(); j++) {
            if ((Objects.equals(contact, reservationList.get(j).getContact())) && (dateTimeObject.compareTo(reservationList.get(j).convertDateTime())==0)) {
                return reservationList.get(j);
            }
        }
        return null;
    }


    /**
     * Finds the index of a reservation with the given contact and given date and time of the reservation.
     * @param contact The contact associated with the reservation.
     * @param dateTimeObject The date and time of the reservation.
     * @return the index of this reservation.
     */
    public int findIndex(String contact, LocalDateTime dateTimeObject) {
        for (int j = 0; j < reservationList.size(); j++) {
            if ((Objects.equals(contact, reservationList.get(j).getContact())) && (dateTimeObject.compareTo(reservationList.get(j).convertDateTime())==0)) {
                return j;
            }
        }
        return -1;
    }

    /**
     * Change the name associated with a reservation with the given contact and given date and time of the reservation
     * to the given name.
     * @param contact The contact associated with the reservation.
     * @param dateTimeObject The date and time of the reservation.
     * @param name The new name of the reservation holder.
     */
    public void modifyReservation(String contact, LocalDateTime dateTimeObject, String name) {
        int i = findIndex(contact, dateTimeObject);
        reservationList.get(i).setName(name);
    }

    /**
     * Change the contact associated with a reservation with the given contact and given date and time of the reservation
     * to the given contact.
     * @param contact The contact associated with the reservation.
     * @param new_contact The new contact of the reservation holder.
     * @param dateTimeObject The date and time of the reservation.
     */
    public void modifyReservation(String contact, String new_contact, LocalDateTime dateTimeObject) {
        int i = findIndex(contact, dateTimeObject);
        reservationList.get(i).setContact(new_contact);
    }

    /**
     * Removed expired reservation.
     */
    public void removeExpired() {
        Duration duration = Duration.between(LocalDateTime.now(), reservationList.get(0).convertDateTime());
        System.out.println(duration.toSeconds());
        if (duration.toSeconds() < 1) {
            reservationList.get(0).removeTimer();
            reservationList.remove(0);
        }
        if (reservationList.size() == 0){
            table.setStatus(TableStatus.EMPTY);
        }
    }

    /**
     * Sort the reservations based on the date and time of reservation.
     */
    public void sort() {
        reservationList.sort(Comparator.comparing(Reservation::convertDateTime));
    }

    /**
     * Check if a reservation with the given contact and given date and time of the reservation exists.
     * @param contact The contact associated with the reservation.
     * @param dateTimeObject The date and time of the reservation.
     */
    public boolean isExist(String contact, LocalDateTime dateTimeObject) {


        for (int i = 0; i < reservationList.size(); i++) {
            if ((Objects.equals(contact, reservationList.get(i).getContact())) && (dateTimeObject.compareTo(reservationList.get(i).convertDateTime())==0)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Update the status of the table to either reserved or empty.
     */
    public void vacated() {
        if (reservationList.size() > 0) {
            table.setStatus(TableStatus.RESERVED);
        } else {
            table.setStatus(TableStatus.EMPTY);
        }
    }

    /**
     * Print the reservation details.
     */
    public void printReservations() {
        System.out.println("Table: " + getTableNumber());
        for (int i = 0; i < reservationList.size(); i++) {
            System.out.println("(" + i + ")");
            reservationList.get(i).printDetails();
        }
    }
}