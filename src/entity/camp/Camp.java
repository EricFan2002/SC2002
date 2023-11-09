package entity.camp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import entity.interfaces.ITaggedItem;
import entity.user.Staff;
import entity.user.Student;

public class Camp implements ITaggedItem {
    protected String ID;
    protected String name;
    protected String description;
    protected boolean visibility;
    protected Date startDate;
    protected Date endDate;
    protected Date closeRegistrationDate;
    protected String school;
    protected String location;
    protected Staff staffInCharge;
    protected Set<Student> attendees;
    protected Set<Student> committees;

    public static final int  MAX_COMMITTEE = 10;

    public Camp(String ID, String name, String description, boolean visibility, Date startDate, Date endDate,
            Date closeRegistrationDate,
            String school, String location, Staff staffInCharge) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.visibility = visibility;
        this.startDate = startDate;
        this.endDate = endDate;
        this.closeRegistrationDate = closeRegistrationDate;
        this.school = school;
        this.location = location;
        this.staffInCharge = staffInCharge;
        this.attendees = new HashSet();
        this.committees = new HashSet();
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

    public Staff getStaffInCharge() {
        return staffInCharge;
    }

    public Set<Student> getAttendees() {
        return attendees;
    }

    public Set<Student> getCommittees() {
        return committees;
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

    public void setStaffInCharge(Staff staffInCharge) {
        this.staffInCharge = staffInCharge;
    }

    public void setAttendees(Set<Student> attendees) {
        this.attendees = attendees;
    }

    public void setCommittees(Set<Student> committees) {
        this.committees = committees;
    }

    public boolean addAttendee(Student attendee) {
        attendees.add(attendee);
        return true;
    }

    public boolean addCommittee(Student committee) {
        committees.add(committee);
        return false;
    }

    public boolean removeAttendee(Student attendee) {
        return attendees.remove(attendee);
    }

    public boolean removeCommittee(Student committee) {
        return committees.remove(committee);
    }
}
