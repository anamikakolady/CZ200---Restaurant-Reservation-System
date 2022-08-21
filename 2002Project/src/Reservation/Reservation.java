package Reservation;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import java.time.LocalDateTime; 
import java.time.ZoneId;
import java.util.Date;

/**
 * Represents a reservation at the restaurant.
 */
public class Reservation {

    /**
     * The date and time of reservation.
     */
    private String dateTime;

    /**
     * The number of people coming.
     */
    private int pax;

    /**
     * Name of customer.
     */
    private String name;

    /**
     * Contact details of customer.
     */
    private String contact;

    /**
     * Timer to remove reservation when the time is up.
     */
    private Timer timer;

    /**
     * Default constructor to create a new Reservation.
     */
    public Reservation() {
    }

    /**
     * Creates a new reservation with the given dateTime, pax, name and contact.
     * @param dateTime This is the date and time.
     * @param pax      This is the number of people who will attend.
     * @param name     This is the name of the person holding the Reservation.
     * @param contact  This is the contact number of the reservation holder.
     */
    public Reservation(String dateTime, int pax, String name, String contact) {
        this.dateTime = dateTime;
        this.pax = pax;
        this.name = name;
        this.contact = contact;
    }

    /**
     * Formats the date and time in a specific manner.
     *
     * @return This is the dateTime object.
     */
    public LocalDateTime convertDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime dateTimeObject = LocalDateTime.parse(dateTime, formatter);
        return dateTimeObject;
    }

    /**
     * Gets the date and time.
     *
     * @return This is the date and time.
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Sets the date and time.
     *
     * @param dateTime This is the date and time.
     */
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Gets the number of people.
     *
     * @return This is the number of people.
     */
    public int getPax() {
        return pax;
    }

    /**
     * Sets the number of people.
     *
     * @param pax This is the number of people.
     */
    public void setPax(int pax) {
        this.pax = pax;
    }

    /**
     * Gets the name of the reservation holder.
     *
     * @return This is the name of the reservation holder.
     */
    public String getName() {
        return name;
    }

    /**
     * sets the name of the reservation holder.
     *
     * @param name This is the name of the reservation holder.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the contact of the reservation holder.
     *
     * @return This is the contact of the reservation holder.
     */
    public String getContact() {
        return contact;
    }

    /**
     * Sets the contact of the reservation holder.
     *
     * @param contact This is the contact of the reservation holder.
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * Gets the timer object.
     *
     * @return This is the timer object.
     */
    public Timer getTimer() {
        return timer;
    }


    /**
     * Sets the timer with the help of the given reservation manager.
     *
     * @param reservationMgr This is the Reservation manager.
     */
    public void setTimer(ReservationMgr reservationMgr) {
        Duration duration = Duration.between(LocalDateTime.now(), convertDateTime());
        timer = new Timer();
        long delay_15mins = 60;
        TimerTask task = new Helper(reservationMgr);
        timer.schedule(task, (duration.toSeconds() * 1000 + delay_15mins));
        this.timer = timer;
    }

    /**
     * Removes the timer.
     */
    public void removeTimer() {
        timer.cancel();
    }

    /**
     * Converts the dateTime to a date.
     * @param dateTime
     * @return the date.
     */
    public Date toDate(String dateTime) {
        Date in = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        return out;
    }

    /**
     * Prints the details of the reservation.
     */
    public void printDetails() {
        LocalDateTime dateTimeObject = convertDateTime();
        System.out.println("Name: " + name);
        System.out.println("Time: " + dateTimeObject.getHour() + ":" + dateTimeObject.getMinute() + " on " + dateTimeObject.getDayOfWeek() + " the " + dateTimeObject.getDayOfMonth() + " of " + dateTimeObject.getMonth());
        System.out.println("Pax: " + pax);
        System.out.println("Contact number: " + contact);
    }

}
