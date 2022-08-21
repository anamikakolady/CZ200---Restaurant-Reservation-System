package Staff;

/**
 * Represents an employee of the restaurent.
 */
public class Staff
{
    /**
     * This staff's name.
     */
    private String name;
    /**
     * This staff's gender.
     */
    private Gender gender;
    /**
     * This staff's id.
     */
    private long id;
    /**
     * This staff's job title.
     */
    private String jobTitle;

    public Staff() {}
    
    /**
     * Creates a new Staff with the given name, gender,id and job title.
     * @param name This Staff's name.
     * @param gender This Staff's gender.
     * @param id This Staff's id.
     * @param jobTitle This Staff's job title.
    */
    public Staff(String name, Gender gender, long id, String jobTitle){
        this.name = name;
        this.gender = gender;
        this.id = id;
        this.jobTitle=jobTitle;
    }
    
    /**
     * Gets the Staff's name .
     * @return this staff's name.
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the name of the Staff.
     * @param name This is the Staff's  name.
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * Gets the Staff's Gender .
     * @return this staff's gender.
     */
    public Gender getGender(){
        return gender;
    }

    /**
     * Sets the Staff's gender.
     * @param gender This is the Staff's gender.
     */
    public void setGender(Gender gender){
        this.gender = gender;
    }
    
    /**
     * Gets the Staff ID .
     * @return this staff's ID.
     */
    public long getId(){
        return id;
    }

    /**
     * Sets the Staff ID.
     * @param id This is the Staff ID.
     */
    public void setId(long id){
        this.id = id;
    }

    /**
     * Gets the Job Title of the staff.
     * @return this staff's job title.
     */
    public String getJobTitle(){
        return jobTitle;
    }

    /**
     * Sets the Job Title of the staff.
     * @param jobTitle This is the staff's jobtitle.
     */
    public void setJobTitle(String jobTitle){
        this.jobTitle = jobTitle;
    }

    /**
     * Printing staff details.
     */
    public void printStaff(){
        System.out.println("ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Gender: " + getGender());
        System.out.println("Job title: "+ getJobTitle());
    }

}
