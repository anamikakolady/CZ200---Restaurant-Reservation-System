package Staff;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Manages all the staff
 */
public class StaffMgr {
    /**
     * This is the list of all the staff.
     */
    private ArrayList<Staff> staffList;

    /**
    * Creates a staff manager.
    */
    public StaffMgr() {
        this.staffList = new ArrayList<>();
    }

    /**
    * Gets the list of Staffs . 
    * @return this is the list of staffs.
    */
    public ArrayList<Staff> getStaffList() {
        return staffList;
    }

    /**
    * Sets the list of the Staffs. 
    * @param staffList This is the list of staffs.
    */
    public void setStaffList(ArrayList<Staff> staffList) {
        this.staffList = staffList;
    }


    /**
     * Add new staff.
     * @param name This is the staff's name.
     * @param gender This is the staff's gender.
     * @param id This is the staff's id.
     * @param jobTitle This is the staff's job title.
     */
    public void addStaff(String name, Gender gender, int id, String jobTitle) {
        Staff newStaff = new Staff(name, gender, id, jobTitle);
        staffList.add(newStaff);
        sort();
    }

    /**
     * Check if a staff exists on the basis of ID number.
     * @param id This is the staff's id.
     * @return true - if staff exists, false - if staff does not exist.
     */
    public boolean isExist(int id) {
        sort();
        if (id < staffList.get(0).getId() || id > staffList.get(staffList.size() - 1).getId()) {
            return false;
        } else {
            int index = findIndex(staffList, 0, staffList.size() - 1, id);
            if (index == -1) {
                return false;
            } else {
                return true;
            }
        }
    }

    /**
     * Modifying staff ID.
     * @param id This is the staff's current id.
     * @param new_id This is the staff's new id.
     */
    public void modifyStaff(int id, int new_id) {
        int index = findIndex(staffList, 0, staffList.size() - 1, id);
        staffList.get(index).setId(new_id);
    }

    /**
     * Modifying staff gender.
     * @param id This is the staff's id.
     * @param gender This is the staff's new gender.
     */
    public void modifyStaff(int id, Gender gender) {
        int index = findIndex(staffList, 0, staffList.size() - 1, id);
        staffList.get(index).setGender(gender);
    }

    /**
     * Modifying staff name.
     * @param id This is the staff's id.
     * @param name This is the staff's new name.
     */
    public void modifyStaff(int id, String name) {
        int index = findIndex(staffList, 0, staffList.size() - 1, id);
        staffList.get(index).setName(name);
    }

    /**
     * Modifying staff jobTitle.
     * @param jobTitle This is the new job title.
     * @param id This is the staff's id.
     */
    public void modifyStaff(String jobTitle, int id) {
        int index = findIndex(id);
        staffList.get(index).setJobTitle(jobTitle);
    }

    /**
     * Binary search of staff id, since list is sorted or almost sorted.
     * @param l This is the pointer to the start.
     * @param r This is the pointer to the end of the segment.
     * @param id This is the staff's ID.
     * @return index of staff.
     */
    public int findIndex(ArrayList<Staff> staffList, int l, int r, int id) {
        if (r >= l) {
            int mid = l + (r - l) / 2;
            if (staffList.get(mid).getId() == id) {
                return mid;
            }
            if (staffList.get(mid).getId() > id) {
                return findIndex(staffList, l, mid - 1, id);
            }
            return findIndex(staffList, mid + 1, r, id);
        }
        return -1;
    }

    /**
     * Find's the index of staff based on their id.
     * @param  id This is the staff's ID.
     * @return this staff's index.
     */

    public int findIndex(int id) {
        int l = 0;
        int r = staffList.size() - 1;
        if (r >= l) {
            int mid = l + (r - l) / 2;
            if (staffList.get(mid).getId() == id) {
                return mid;
            }
            if (staffList.get(mid).getId() > id) {
                return findIndex(staffList, l, mid - 1, id);
            }
            return findIndex(staffList, mid + 1, r, id);
        }
        return -1;
    }

    /**
     * Removing a staff based on their id.
     * @param id This is the staff's ID.
     */
    public void removeStaff(int id) {
        int index = findIndex(id);
        staffList.remove(index);
    }

    /**
     * Sorting the staffs on the basis of staff ID.
     */
    public void sort() {
        staffList.sort(Comparator.comparing(Staff::getId));
    }


    /**
     * Get the total number of staffs.
     * @return this is the number of staffs.
     */
    public int amountStaff() {
        return staffList.size();
    }

    /**
     * Print the details of all staff.
     */
    public void printStaffList() {
        sort();
        for (int i = 0; i < staffList.size(); i++) {
            staffList.get(i).printStaff();
            System.out.println("");
        }
    }

    /**
     * Checks if there are no staff.
     * @return true if staff list is empty and false if not empty.
     */
    public boolean isEmpty() {
        return staffList.isEmpty();
    }
}


