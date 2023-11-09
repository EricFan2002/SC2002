package entity.camp;

import java.util.Date;
import java.util.Set;

import entity.user.Staff;
import entity.user.Student;

public class Camp {
    protected String ID;
    protected String name;
    protected String description;
    protected Date startDate;
    protected Date endDate;
    protected Date closeRegistrationDate;
    protected String school;
    protected String location;
    protected Staff staffInCharge;
    protected Set<Student> attendees;
    protected Set<Student> committees;

    public Camp(String ID, String name, String description, Date startDate, Date endDate, Date closeRegistrationDate,
            String school, String location, Staff staffInCharge, Set<Student> attendees, Set<Student> committees) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.closeRegistrationDate = closeRegistrationDate;
        this.school = school;
        this.location = location;
        this.staffInCharge = staffInCharge;
        this.attendees = attendees;
        this.committees = committees;
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

    public void addAttendee(Student attendee) {
        attendees.add(attendee);
    }

    public void addCommittee(Student committee) {
        committees.add(committee);
    }

    public void removeAttendee(Student attendee) {
        attendees.remove(attendee);
    }

    public void removeCommittee(Student committee) {
        committees.remove(committee);
    }
}
