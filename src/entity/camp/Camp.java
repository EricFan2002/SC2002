package entity.camp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import entity.user.Staff;
import entity.user.Student;

public class Camp extends CampDetails {

    protected Staff staffInCharge;
    protected Set<Student> attendees;
    protected Set<Student> committees;

    public static final int MAX_COMMITTEE = 10;

    public Camp(String ID, String name, String description, boolean visibility, Date startDate, Date endDate,
            Date closeRegistrationDate,
            String school, String location, Staff staffInCharge, int totalSlots) {
        super(ID, name, description, visibility, startDate, endDate, closeRegistrationDate, school, location,
                totalSlots);
        this.staffInCharge = staffInCharge;
        this.attendees = new HashSet<Student>();
        this.committees = new HashSet<Student>();
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
        if (this.committees.size() >= MAX_COMMITTEE) {
            return false;
        }
        committees.add(committee);
        return true;
    }

    public boolean removeAttendee(Student attendee) {
        return attendees.remove(attendee);
    }

    public boolean removeCommittee(Student committee) {
        return committees.remove(committee);
    }

    public CampDetails createSuggestionPlan() {
        CampDetails suggestionPlan = new CampDetails();
        suggestionPlan.setID(this.ID);
        return suggestionPlan;
    }
}
