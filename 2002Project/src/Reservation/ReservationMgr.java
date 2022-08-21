package Reservation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import Table.*;

/**
 * Represents a list of reservations for each table.
 */
public class ReservationMgr {
    /**
     * stores all the reservations.
     */
    private ArrayList<ReservationList> reservations;

    /**
     * creates a new reservation manager.
     */
    public ReservationMgr() {
        this.reservations = new ArrayList<ReservationList>();
    }

    /**
     * Get all the reservations.
     * @return the ArrayList of ReservationList objects.
     */
    public ArrayList<ReservationList> getReservations() {
        return reservations;
    }

    /**
     * Loads in the reservations.
     * @param reservations This is the Arraylist of ReservationList objects.
     */
    public void setReservations(ArrayList<ReservationList> reservations) {
        this.reservations = reservations;
    }
    /**
     * Creates new reservation and allocates a suitable table.
     * @param tableMgr This is the table manager.
     * @param dateTime This is the time of the reservation.
     * @param pax This is the number of people coming.
     * @param name This is the name of the reservation holder.
     * @param contact This is the contact details of the reservation holder.
     * @return void.
     */
    public void newReservation(TableMgr tableMgr, String dateTime, int pax, String name, String contact) {
        ArrayList<Table> candidates = tableMgr.possibleTables(pax);
        if (candidates.size() > 0) {
            int index;
            for (int i = 0; i < candidates.size(); i++) {
                index = getTableIndex(candidates.get(i).getTableNumber());
                if (index == -1) {
                    ReservationList newList = new ReservationList(candidates.get(i));
                    Reservation newRes = new Reservation(dateTime, pax, name, contact);
                    newRes.setTimer(this);
                    newList.addReservation(newRes);
                    reservations.add(newList);
                    return;
                } else {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                    LocalDateTime dateTimeObject = LocalDateTime.parse(dateTime, formatter);
                    if (reservations.get(index).isAvailable(dateTimeObject)) {
                        Reservation newRes = new Reservation(dateTime, pax, name, contact);
                        newRes.setTimer(this);
                        reservations.get(index).addReservation(newRes);
                        return;
                    }
                }
            }
        }

    }
    /**
     * Removes reservation.
     * @param contact This is the contact details of the customer.
     * @param dateTimeObject This is the time of reservation.
     */
    public void removeReservation(String contact, LocalDateTime dateTimeObject) {
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).isExist(contact, dateTimeObject)) {
                reservations.get(i).removeReservation(contact, dateTimeObject);
            }
        }
    }
    /**
     * Check if the restaurant can accommodate the reservation.
     * @param pax This is the number of people.
     * @param dateTimeObject This is the time of reservation.
     * @return true if the reservation can be accommodated else false.
     */
    public boolean checkAvailability(int pax, LocalDateTime dateTimeObject, TableMgr tableMgr) {
        if(reservations.size()==0 && tableMgr.getNumOfTables()>0){
            return true;
        }
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getTable().getCapacity() >= pax) {
                if (reservations.get(i).isAvailable(dateTimeObject)) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Check if reallocation is possible.
     * @param tableNumber This is the table number.
     * @return true if reservations can be reallocated else false.
     */
    public boolean checkReallocation(int tableNumber) {
        int flag = reservations.get(tableNumber).getNumberOfReservations();
        for (int j = 0; j < reservations.get(tableNumber).getNumberOfReservations(); j++) {
            for (int i = 0; i < reservations.size(); i++) {
                if (i != tableNumber) {
                    if (reservations.get(i).getTable().getCapacity() == reservations.get(tableNumber).getTable().getCapacity()) {
                        if (reservations.get(i).isAvailable(reservations.get(tableNumber).getReservation(j).convertDateTime()))
                            flag--;
                        break;
                    }
                }
            }
        }
        if (flag == 0) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Reallocate all reservations from a table.
     * @param tableNumber This is the table number.
     */
    public void reallocateReservation(int tableNumber) {
        for (int j = 0; j < reservations.get(tableNumber).getNumberOfReservations(); j++) {
            for (int i = 0; i < reservations.size(); i++) {
                if (i != tableNumber) {
                    if (reservations.get(i).getTable().getCapacity() == reservations.get(tableNumber).getTable().getCapacity()) {
                        if (reservations.get(i).isAvailable(reservations.get(tableNumber).getReservation(j).convertDateTime()))
                            reservations.get(i).addReservation(reservations.get(tableNumber).getReservation(j));
                        reservations.get(tableNumber).removeReservation(j);
                        break;
                    }
                }
            }
        }
        reservations.get(tableNumber).getTable().setStatus(TableStatus.EMPTY);
    }
    /**
     * Check if the reservation exists.
     * @param contact This is the contact details of the customer.
     * @param dateTimeObject This is the time of the reservations.
     * @return returns true if the reservation exists else return false.
     */
    public boolean isExist(String contact, LocalDateTime dateTimeObject) {
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).isExist(contact, dateTimeObject)) {
                return true;
            }
        }
        return false;
    }

    /**
     * find the index for the ReservationList class, which contains the table.
     * @param tableID table number.
     * @return index, if the table cannot be found return -1.
     */

    public int getTableIndex(int tableID) {
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getTableNumber() == tableID) {
                return i;
            }
        }
        return -1;
    }
    /**
     * When tables are vacated, set appropriate status.
     */
    public void vacated(int tableID,TableMgr tableMgr) {
        int index = getTableIndex(tableID);
        if(index == -1){
            tableMgr.vacated(tableID);
            return;
        }
        reservations.get(index).vacated();
    }
    /**
     * Print all the reservations for all tables.
     */
    public void printAllReservations() {
        if (reservations.size() == 0) {
            System.out.println("No reservations currently.");
        } else {
            reservations.sort(Comparator.comparing(ReservationList::getTableNumber));
            for (int i = 0; i < reservations.size(); i++) {
                reservations.get(i).printReservations();
            }

        }
    }
    /**
     * Print reservations for all tables.
     * @Param id tableID.
     */
    public void printTableReservations(int id) {
        reservations.get(id).printReservations();
    }
    /**
     * Removes expired reservations.
     */
    public void expired() {
        for (int i = 0; i < reservations.size(); i++) {
            reservations.get(i).removeExpired();
        }
    }
    /**
     * Check if there's any reservations.
     * @returns true if there's reservations, else false if there's none.
     */
    public int amountReservations() {
        return reservations.size();
    }
    /**
     * Check if there's any reservations.
     * @returns true if there's reservations, else false if there's none.
     */
    public boolean hasReservations() {
        return (reservations.size() > 0);
    }
    /**
     * Get the capacity of the table that the reservation is linked to.
     * @param contact This is the contact of the person which the reservation is for.
     * @param dateTimeObject This is the time of reservation.
     * @return table's capacity.
     */
    public int findTableCapacity(String contact, LocalDateTime dateTimeObject) {
        for (int i = 0; i < reservations.size(); i++) {
            for (int j = 0; j < reservations.get(i).getNumberOfReservations(); j++) {
                if ((Objects.equals(contact, reservations.get(i).getReservation(j).getContact() )) && (dateTimeObject.compareTo(reservations.get(i).getReservation(j).convertDateTime())==0)) {
                    return reservations.get(i).getTableCapacity();
                }
            }
        }
        return -1;
    }
    /**
     * Get the reservation object.
     * @param contact This is the contact of the person which the reservation is for.
     * @param dateTimeObject This is the time of reservation.
     * @return index of the reservationList which contains the specific reservation.
     */
    public int findResList(String contact, LocalDateTime dateTimeObject) {
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).isExist(contact, dateTimeObject)) {
                return i;
            }
        }
        return -1;
    }
    /**
     * Get the reservation object.
     * @param contact This is the contact of the person which the reservation is for.
     * @param dateTimeObject This is the time of reservation.
     * @return returns the reservation object.
     */
    public Reservation findReservation(String contact, LocalDateTime dateTimeObject) {
        int i = findResList(contact, dateTimeObject);
//        reservations.get(i).printReservations();
        return reservations.get(i).findReservation(contact, dateTimeObject);
    }
    /**
     * Method overloading.
     * Change the name of the person who's asking for a reservation.
     * @param contact This is the contact of the person which the reservation is for.
     * @param dateTimeObject This is the time of reservation.
     * @param name This is the person's updated name.
     */
    public void modifyReservation(String contact, LocalDateTime dateTimeObject, String name) {
        // modify name
        int i = findResList(contact, dateTimeObject);
        reservations.get(i).modifyReservation(contact, dateTimeObject, name);
    }
    /**
     * Method overloading.
     * Change the contact details of the person who's asking for a reservation.
     * @param contact This is the old contact of the person which the reservation is for.
     * @param new_contact This is the new contact of the person which the reservation is for.
     * @param dateTimeObject This is the time of reservation.
     */
    public void modifyReservation(String contact, String new_contact, LocalDateTime dateTimeObject) {
        int i = findResList(contact, dateTimeObject);
        reservations.get(i).modifyReservation(contact, new_contact, dateTimeObject);
    }
    /**
     * Method overloading.
     * Change the number of people reserved for, and if need be, changes the table assigned as well.
     * @param tableMgr This is the table manager.
     * @param contact This is the contact of the person who's asking for a reservation.
     * @param dateTimeObject This is the time of reservation.
     * @param pax This is the number of people who are coming.
     */
    public void modifyReservation(TableMgr tableMgr, String contact, LocalDateTime dateTimeObject, int pax) {
        Reservation old = findReservation(contact, dateTimeObject);
        String name = old.getName();
        String dateTime = old.getDateTime();
        removeReservation(contact, dateTimeObject);
        newReservation(tableMgr, dateTime, pax, name, contact);
    }
    /**
     * Method overloading.
     * Change the reservation's time, and if need be, changes the table assigned as well.
     * @param tableMgr This is the table manager.
     * @param contact This is the contact of the person who's asking for a reservation.
     * @param dateTimeObject This is the time of old reservation.
     * @param dateTime This is the time of new reservation.
     */
    public void modifyReservation(TableMgr tableMgr, String contact, LocalDateTime dateTimeObject, String dateTime) {
        // Modify DateTime
        Reservation old = findReservation(contact, dateTimeObject);
        int pax = old.getPax();
        String name = old.getName();
        removeReservation(contact, dateTimeObject);
        newReservation(tableMgr, dateTime, pax, name, contact);
    }

}
