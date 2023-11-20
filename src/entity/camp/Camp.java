package entity.camp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import entity.enquiry.Enquiry;
import entity.enquiry.EnquiryList;
import entity.suggestion.Suggestion;
import entity.suggestion.SuggestionList;
import entity.user.Staff;
import entity.user.Student;

/**
 * The {@code Camp} class represents a camp, extending the {@code CampDetails}
 * class with additional features.
 * It includes details about the staff in charge, attendees, and committees
 * involved in the camp.
 * The class provides methods to manage these aspects and create suggestion
 * plans for camp modifications.
 *
 * <p>
 * Key features:
 * <ul>
 * <li>Management of staff in charge.
 * <li>Handling attendees and committees.
 * <li>Adding and removing students from attendees and committees.
 * <li>Creating suggestion plans for camps.
 * </ul>
 *
 * @see entity.camp.CampDetails
 * @see entity.user.Staff
 * @see entity.user.Student
 */
public class Camp extends CampDetails {

    protected Staff staffInCharge;
    protected Set<Student> attendees;
    protected Set<Student> committees;
    private ArrayList<Suggestion> suggestionList;
    private ArrayList<Enquiry> enquiryList;

    public static final int MAX_COMMITTEE = 10;

    /**
     * Constructs a new Camp instance with specified details.
     * 
     * @param ID                    Unique identifier for the camp.
     * @param name                  Name of the camp.
     * @param description           Description of the camp.
     * @param visibility            Visibility status of the camp.
     * @param startDate             Start date of the camp.
     * @param endDate               End date of the camp.
     * @param closeRegistrationDate Closing date for registration.
     * @param school                School associated with the camp.
     * @param location              Location of the camp.
     * @param staffInCharge         Staff member in charge of the camp.
     * @param totalSlots            Total number of slots available in the camp.
     */
    public Camp(String ID, String name, String description, Boolean visibility, Date startDate, Date endDate,
            Date closeRegistrationDate,
            String school, String location, Staff staffInCharge, int totalSlots) {
        super(ID, name, description, visibility, startDate, endDate, closeRegistrationDate, school, location,
                totalSlots);
        this.staffInCharge = staffInCharge;
        this.attendees = new HashSet<Student>();
        this.committees = new HashSet<Student>();
        this.suggestionList = new ArrayList<Suggestion>();
        this.enquiryList = new ArrayList<Enquiry>();
    }

    // Example for one getter and one setter
    /**
     * Retrieves the staff member in charge of the camp.
     * 
     * @return Staff The staff member in charge.
     */
    public Staff getStaffInCharge() {
        return staffInCharge;
    }

    public Set<Student> getAttendees() {
        return attendees;
    }

    public Set<Student> getCommittees() {
        return committees;
    }

    /**
     * Sets the staff member in charge of the camp.
     * 
     * @param staffInCharge The staff member to be set as in charge.
     */
    public void setStaffInCharge(Staff staffInCharge) {
        this.staffInCharge = staffInCharge;
    }

    public void setAttendees(Set<Student> attendees) {
        this.attendees = attendees;
    }

    public void setCommittees(Set<Student> committees) {
        this.committees = committees;
    }

    /**
     * Adds a student as an attendee of the camp.
     * 
     * @param attendee The student to be added as an attendee.
     * @return boolean True if the student is successfully added.
     */
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

    /**
     * Creates a suggestion plan based on the current state of the camp.
     * This plan can be used to suggest modifications or improvements to the camp.
     * 
     * @return CampDetails A new {@code CampDetails} object representing the
     *         suggestion plan.
     */
    public CampDetails createSuggestionPlan() {
        CampDetails suggestionPlan = new CampDetails();
        suggestionPlan.setID(this.ID);
        return suggestionPlan;
    }

    public ArrayList<Suggestion> getSuggestionList() {
        return suggestionList;
    }

    public ArrayList<Enquiry> getEnquiryList() {
        return enquiryList;
    }

    public void addSuggestion(Suggestion suggestion) {
        suggestionList.add(suggestion);
    }

    public void addEnquiry(Enquiry enquiry) {
        enquiryList.add(enquiry);
    }

    public void removeSuggestion(Suggestion suggestion) {
        suggestionList.remove(suggestion);
    }

    public void removeEnquiry(Enquiry enquiry) {
        enquiryList.remove(enquiry);
    }
}
