package Reservation;

import java.util.TimerTask;


class Helper extends TimerTask {
    /**
     * reservation manager
     */
    private ReservationMgr reservationMgr;

    public Helper () {}
    /**
     *Create Helper with the given reservationMgr
     *@Param reservationMgr the reservation manager
     */
    public Helper (ReservationMgr reservationMgr) {
        this.reservationMgr = reservationMgr;
    }
    /**
     *Return in reservation manager
     *@returns stored reservation manager
     */
    public ReservationMgr getReservationMgr() {
        return reservationMgr;
    }
    /**
     *Set reservation manager
     *@Param reservationMgr This is the reservation manager
     */
    public void setReservationMgr(ReservationMgr reservationMgr) {
        this.reservationMgr = reservationMgr;
    }
    /**
     *when timer has expired, executes reservationMgr.expired(), which removes the reservation  
     */
    public void run() {
        reservationMgr.expired();
    }
}