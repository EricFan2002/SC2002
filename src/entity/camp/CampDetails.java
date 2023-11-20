package entity.camp;

import java.util.Date;

import entity.interfaces.ITaggedItem;

/**
 * Represents the details of a camp, including its ID, name, description, visibility,
 * start and end dates, registration closure date, school, location, and the total number of slots.
 * This class implements the ITaggedItem interface.
 */
public class CampDetails implements ITaggedItem {
    protected String ID;
    protected String name;
    protected String description;
    protected Boolean visibility;
    protected Date startDate;
    protected Date endDate;
    protected Date closeRegistrationDate;
    protected String school;
    protected String location;
    protected Integer totalSlots;

    /**
     * Constructs a new CampDetails object with specified details.
     *
     * @param ID                   The unique identifier of the camp.
     * @param name                 The name of the camp.
     * @param description          A description of the camp.
     * @param visibility           The visibility status of the camp.
     * @param startDate            The start date of the camp.
     * @param endDate              The end date of the camp.
     * @param closeRegistrationDate The last date to register for the camp.
     * @param school               The school associated with the camp.
     * @param location             The location of the camp.
     * @param totalSlots           The total number of slots available in the camp.
     */
    public CampDetails(String ID, String name, String description, boolean visibility, Date startDate, Date endDate,
            Date closeRegistrationDate,
            String school, String location, Integer totalSlots) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.visibility = visibility;
        this.startDate = startDate;
        this.endDate = endDate;
        this.closeRegistrationDate = closeRegistrationDate;
        this.school = school;
        this.location = location;
        this.totalSlots = totalSlots;
    }

    /**
     * Constructs a new CampDetails object with default values.
     */
    public CampDetails() {
        this.ID = null;
        this.name = null;
        this.description = null;
        this.visibility = false;
        this.startDate = null;
        this.endDate = null;
        this.closeRegistrationDate = null;
        this.school = null;
        this.location = null;
    }

    // Getters and setters for each property with appropriate Javadoc comments.
    // For example:

    /**
     * Returns the ID of the camp.
     *
     * @return The camp's unique identifier.
     */
    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Boolean isVisible() {
        return visibility;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getCloseRegistrationDate() {
        return closeRegistrationDate;
    }

    public String getSchool() {
        return school;
    }

    public String getLocation() {
        return location;
    }

    public Integer getTotalSlots() {
        return totalSlots;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setCloseRegistrationDate(Date closeRegistrationDate) {
        this.closeRegistrationDate = closeRegistrationDate;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }

}
