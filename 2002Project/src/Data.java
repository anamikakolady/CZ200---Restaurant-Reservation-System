import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;

import Discount.DiscountMgr;
import Menu.MenuMgr;
import Order.OrderMgr;
import Reservation.ReservationMgr;
import Revenue.RevenueMgr;
import Staff.StaffMgr;
import Table.TableMgr;

@XmlRootElement
public class Data {
    private static final String FILE_NAME = "data.xml";

    private DiscountMgr discountMgr;
    private MenuMgr menuMgr;
    private OrderMgr orderMgr;
    private ReservationMgr reservationMgr;
    private RevenueMgr revenueMgr;
    private StaffMgr staffMgr;
    private TableMgr tableMgr;

    public Data() {
        discountMgr = new DiscountMgr();
        menuMgr = new MenuMgr();
        orderMgr = new OrderMgr();
        reservationMgr = new ReservationMgr();
        revenueMgr = new RevenueMgr();
        staffMgr = new StaffMgr();
        tableMgr = new TableMgr();
    }

    public static String getFileName() {
        return FILE_NAME;
    }

    public DiscountMgr getDiscountMgr() {
        return discountMgr;
    }

    public void setDiscountMgr(DiscountMgr discountMgr) {
        this.discountMgr = discountMgr;
    }

    public MenuMgr getMenuMgr() {
        return menuMgr;
    }

    public void setMenuMgr(MenuMgr menuMgr) {
        this.menuMgr = menuMgr;
    }

    public OrderMgr getOrderMgr() {
        return orderMgr;
    }

    public void setOrderMgr(OrderMgr orderMgr) {
        this.orderMgr = orderMgr;
    }

    public ReservationMgr getReservationMgr() {
        return reservationMgr;
    }

    public void setReservationMgr(ReservationMgr reservationMgr) {
        this.reservationMgr = reservationMgr;
    }

    public RevenueMgr getRevenueMgr() {
        return revenueMgr;
    }

    public void setRevenueMgr(RevenueMgr revenueMgr) {
        this.revenueMgr = revenueMgr;
    }

    public StaffMgr getStaffMgr() {
        return staffMgr;
    }

    public void setStaffMgr(StaffMgr staffMgr) {
        this.staffMgr = staffMgr;
    }

    public TableMgr getTableMgr() {
        return tableMgr;
    }

    public void setTableMgr(TableMgr tableMgr) {
        this.tableMgr = tableMgr;
    }

    public void createDiscountMgr() {
        discountMgr = new DiscountMgr();
    }

    public boolean hasDiscountMgr() {
        return (discountMgr != null);
    }

    public void createMenuMgr() {
        menuMgr = new MenuMgr();
    }

    public boolean hasMenuMgr() {
        return (menuMgr != null);
    }

    public void createOrderMgr() {
        orderMgr = new OrderMgr();
    }

    public boolean hasOrderMgr() {
        return (orderMgr != null);
    }

    public void createReservationMgr() {
        reservationMgr = new ReservationMgr();
    }

    public boolean hasReservationMgr() {
        return (reservationMgr != null);
    }

    public void createRevenueMgr() {
        revenueMgr = new RevenueMgr();
    }

    public boolean hasRevenueMgr() {
        return (revenueMgr != null);
    }

    public void createStaffMgr() {
        staffMgr = new StaffMgr();
    }

    public boolean hasStaffMgr() {
        return (staffMgr != null);
    }

    public void createTableMgr() {
        tableMgr = new TableMgr();
    }

    public boolean hasTableMgr() {
        return (tableMgr != null);
    }

    public static Data LoadData() {
        Data data = null;

        try {
            System.out.println("Loading Data...");
            JAXBContext context = JAXBContext.newInstance(Data.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            File file = new File(FILE_NAME);
            data = (Data) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
                e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("Initializing Data...");
            data = new Data();
        }
        if (!data.hasDiscountMgr()) {
            System.out.println("Initializing DiscountMgr...");
            data.createDiscountMgr();
        }
        if (!data.hasMenuMgr()) {
            System.out.println("Initializing MenuMgr...");
            data.createMenuMgr();
        }
        if (!data.hasOrderMgr()) {
            System.out.println("Initializing OrderMgr...");
            data.createOrderMgr();
        }
        if (!data.hasReservationMgr()) {
            System.out.println("Initializing ReservationMgr...");
            data.createReservationMgr();
        }
        if (!data.hasRevenueMgr()) {
            System.out.println("Initializing StaffMgr...");
            data.createRevenueMgr();
        }
        if (!data.hasStaffMgr()) {
            System.out.println("Initializing StaffMgr...");
            data.createStaffMgr();
        }
        if (!data.hasTableMgr()) {
            System.out.println("Initializing TableMgr...");
            data.createTableMgr();
        }
        return data;
    }

    public static void SaveData(Data data) {
        System.out.println("Saving Data...");

        try {
            JAXBContext context = JAXBContext.newInstance(Data.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            File file = new File(FILE_NAME);
            marshaller.marshal(data, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
