package entity.camp;

import java.util.Date;

import entity.interfaces.ITaggedItem;

public class CampDetails implements ITaggedItem {
    protected String ID;
    protected String name;
    protected String description;
    protected boolean visibility;
    protected Date startDate;
    protected Date endDate;
    protected Date closeRegistrationDate;
    protected String school;
    protected String location;

    public CampDetails(String ID, String name, String description, boolean visibility, Date startDate, Date endDate,
            Date closeRegistrationDate,
            String school, String location) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.visibility = visibility;
        this.startDate = startDate;
        this.endDate = endDate;
        this.closeRegistrationDate = closeRegistrationDate;
        this.school = school;
        this.location = location;
    }

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

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isVisible() {
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

}
