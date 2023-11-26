package entity.camp;

import java.util.Date;

import entity.interfaces.ITaggedItem;

/**
 * Represents the details of a camp, including its ID, name, description, visibility,
 * start and end dates, registration closure date, school, location, and the total number of slots.
 * This class implements the ITaggedItem interface.
 */
public class CampDetails extends CampList implements ITaggedItem {
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
    protected Integer totalCommitteeSlots;

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
     * @param totalCommitteeSlots  The total number of committee slots available in the camp.
     */
    public CampDetails(String ID, String name, String description, boolean visibility, Date startDate, Date endDate,
            Date closeRegistrationDate,
            String school, String location, Integer totalSlots, Integer totalCommitteeSlots) {
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
        this.totalCommitteeSlots = totalCommitteeSlots;
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
        this.totalCommitteeSlots = null;
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

    /**
     * Returns the name of the camp.
     *
     * @return The camp's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the description of the camp.
     *
     * @return The camp's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the visibility status of the camp.
     *
     * @return The camp's visibility status.
     */
    public Boolean isVisible() {
        return visibility;
    }

    /**
     * Returns the start date of the camp.
     *
     * @return The camp's start date.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Returns the end date of the camp.
     *
     * @return The camp's end date.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Returns the registration closure date of the camp.
     *
     * @return The camp's registration closure date.
     */
    public Date getCloseRegistrationDate() {
        return closeRegistrationDate;
    }

    /**
     * Returns the school associated with the camp.
     *
     * @return The camp's associated school.
     */
    public String getSchool() {
        return school;
    }

    /**
     * Returns the location of the camp.
     *
     * @return The camp's location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Returns the total number of slots available in the camp.
     *
     * @return The camp's total number of slots.
     */
    public Integer getTotalSlots() {
        return totalSlots;
    }

    /**
     * Returns the total number of committee slots available in the camp.
     *
     * @return The camp's total number of committee slots.
     */
    public Integer getTotalCommitteeSlots(){
        return totalCommitteeSlots;
    }

    /**
     * Set the total number of committee slots available in the camp.
     *
     * @param slots The camp's total number of committee slots.
     */
    public void setTotalCommitteeSlots(int slots){
        this.totalCommitteeSlots = slots;
    }


    /**
     * Sets the ID of the camp.
     *
     * @param ID The camp's unique identifier.
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Sets the name of the camp.
     *
     * @param name The camp's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the description of the camp.
     *
     * @param description The camp's description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the visibility status of the camp.
     *
     * @param visibility The camp's visibility status.
     */
    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    /**
     * Sets the start date of the camp.
     *
     * @param startDate The camp's start date.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Sets the end date of the camp.
     *
     * @param endDate The camp's end date.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Sets the registration closure date of the camp.
     *
     * @param closeRegistrationDate The camp's registration closure date.
     */
    public void setCloseRegistrationDate(Date closeRegistrationDate) {
        this.closeRegistrationDate = closeRegistrationDate;
    }

    /**
     * Sets the school associated with the camp.
     *
     * @param school The camp's associated school.
     */
    public void setSchool(String school) {
        this.school = school;
    }

    /**
     * Sets the location of the camp.
     *
     * @param location The camp's location.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Sets the total number of slots available in the camp.
     *
     * @param totalSlots The camp's total number of slots.
     */
    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }

}
