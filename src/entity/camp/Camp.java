package entity.camp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import entity.enquiry.Enquiry;
import entity.enquiry.EnquiryList;
import entity.interfaces.ITaggedItem;
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
public class Camp extends CampDetails implements ITaggedItem {

    protected Staff staffInCharge;
    protected Set<Student> attendees;
    protected Set<Student> committees;
    protected Set<Student> registeredStudents;
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
        this.registeredStudents = new HashSet<Student>();
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
        if (this.attendees.size() >= this.totalSlots - MAX_COMMITTEE) {
            return false;
        }
        attendees.add(attendee);
        registeredStudents.add(attendee);
        return true;
    }

    /**
     * Adds a student as a committee member of the camp.
     *
     * @param committee The student to be added as a committee member.
     * @return boolean True if the student is successfully added.
     */
    public boolean addCommittee(Student committee) {
        if (this.committees.size() >= MAX_COMMITTEE) {
            return false;
        }
        committees.add(committee);
        registeredStudents.add(committee);
        return true;
    }

    /**
     * Removes a student from the list of attendees.
     *
     * @param attendee The student to be removed.
     * @return boolean True if the student is successfully removed.
     */
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

    /**
     * Retrieves the list of suggestions for the camp.
     *
     * @return The list of suggestions.
     */
    public ArrayList<Suggestion> getSuggestionList() {
        return suggestionList;
    }

    /**
     * Retrieves the list of enquiries for the camp.
     *
     * @return The list of enquiries.
     */
    public ArrayList<Enquiry> getEnquiryList() {
        return enquiryList;
    }

    /**
     * Adds a suggestion to the list of suggestions.
     *
     * @param suggestion The suggestion to be added.
     */
    public void addSuggestion(Suggestion suggestion) {
        suggestionList.add(suggestion);
    }

    /**
     * Adds an enquiry to the list of enquiries.
     *
     * @param enquiry The enquiry to be added.
     */
    public void addEnquiry(Enquiry enquiry) {
        enquiryList.add(enquiry);
    }

    /**
     * Removes a suggestion from the list of suggestions.
     *
     * @param suggestion The suggestion to be removed.
     */
    public void removeSuggestion(Suggestion suggestion) {
        suggestionList.remove(suggestion);
    }

    /**
     * Removes an enquiry from the list of enquiries.
     *
     * @param enquiry The enquiry to be removed.
     */
    public void removeEnquiry(Enquiry enquiry) {
        enquiryList.remove(enquiry);
    }

    /**
     * Checks if a student is registered in the camp.
     *
     * @param student The student to check for registration.
     * @return True if the student is registered, false otherwise.
     */
    public boolean isStudentRegistered(Student student) {
        return registeredStudents.contains(student);
    }

    /**
     * Adds a student to the list of registered students (used for deserialize
     * only).
     *
     * @param student The student to be added.
     */
    public void addRegisteredStudent(Student student) {
        registeredStudents.add(student);
    }

    /**
     * Retrieves the list of previously registered students.
     *
     * @param student The student to be removed.
     */
    public Set<Student> getRegisteredStudents() {
        return registeredStudents;
    }
}
