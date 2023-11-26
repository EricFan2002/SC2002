package app.entity.user;

import app.entity.camp.Camp;
import app.entity.enquiry.Enquiry;
import app.entity.suggestion.Suggestion;

import java.util.ArrayList;

/**
 * The {@code Student} class represents a student user with information about attended camps, committees, points, enquiries, and suggestions.
 * It extends the {@code User} class.
 */
public class Student extends User {

    /**
     * The number of points accumulated by the student.
     */
    protected int points;

    /**
     * The list of camps attended by the student.
     */
    private ArrayList<Camp> attendedCampList;

    /**
     * The list of camps where the student participated as a committee member.
     */
    private ArrayList<Camp> committeeCampList;

    /**
     * The list of enquiries submitted by the student.
     */
    private ArrayList<Enquiry> enquiryList;

    /**
     * The list of suggestions submitted by the student.
     */
    private ArrayList<Suggestion> suggestionList;

    /**
     * Constructs a Student object with the specified ID, name, and faculty.
     *
     * @param ID      The ID of the student.
     * @param name    The name of the student.
     * @param faculty The faculty of the student.
     */
    public Student(String ID, String name, String faculty) {
        super(ID, name, faculty);
        points = 0;
        attendedCampList = new ArrayList<Camp>();
        committeeCampList = new ArrayList<Camp>();
        enquiryList = new ArrayList<Enquiry>();
        suggestionList = new ArrayList<Suggestion>();
    }

    /**
     * Constructs a Student object with default values.
     */
    public Student() {
        super();
        attendedCampList = new ArrayList<Camp>();
        committeeCampList = new ArrayList<Camp>();
        enquiryList = new ArrayList<Enquiry>();
        suggestionList = new ArrayList<Suggestion>();
    }

    /**
     * Gets the number of points accumulated by the student.
     *
     * @return The number of points.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Adds points to the total points accumulated by the student.
     *
     * @param points The points to be added.
     */
    public void addPoints(int points) {
        this.points += points;
    }

    /**
     * Sets the total points accumulated by the student.
     *
     * @param points The total points to be set.
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Adds a camp to the list of camps where the student participated as a committee member.
     *
     * @param camp The camp to be added.
     */
    public void addCommitteeCamp(Camp camp) {
        committeeCampList.add(camp);
    }

    /**
     * Adds a camp to the list of camps attended by the student.
     *
     * @param camp The camp to be added.
     */
    public void addAttendedCamp(Camp camp) {
        attendedCampList.add(camp);
    }

    /**
     * Removes a camp from the list of camps where the student participated as a committee member.
     *
     * @param camp The camp to be removed.
     */
    public void removeCommitteeCamp(Camp camp) {
        committeeCampList.remove(camp);
    }

    /**
     * Removes a camp from the list of camps attended by the student.
     *
     * @param camp The camp to be removed.
     */
    public void removeAttendedCamp(Camp camp) {
        attendedCampList.remove(camp);
    }

    /**
     * Gets the list of camps attended by the student.
     *
     * @return An {@code ArrayList} representing the attended camps.
     */
    public ArrayList<Camp> getAttendedCampList() {
        return attendedCampList;
    }

    /**
     * Gets the list of camps where the student participated as a committee member.
     *
     * @return An {@code ArrayList} representing the committee camps.
     */
    public ArrayList<Camp> getCommitteeCampList() {
        return committeeCampList;
    }

    /**
     * Adds an enquiry to the list of enquiries submitted by the student.
     *
     * @param enquiry The enquiry to be added.
     */
    public void addEnquiry(Enquiry enquiry) {
        enquiryList.add(enquiry);
    }

    /**
     * Removes an enquiry from the list of enquiries submitted by the student.
     *
     * @param enquiry The enquiry to be removed.
     */
    public void removeEnquiry(Enquiry enquiry) {
        enquiryList.remove(enquiry);
    }

    /**
     * Gets the list of enquiries submitted by the student.
     *
     * @return An {@code ArrayList} representing the enquiries.
     */
    public ArrayList<Enquiry> getEnquiryList() {
        return enquiryList;
    }

    /**
     * Adds a suggestion to the list of suggestions submitted by the student.
     *
     * @param suggestion The suggestion to be added.
     */
    public void addSuggestion(Suggestion suggestion) {
        suggestionList.add(suggestion);
    }

    /**
     * Removes a suggestion from the list of suggestions submitted by the student.
     *
     * @param suggestion The suggestion to be removed.
     */
    public void removeSuggestion(Suggestion suggestion) {
        suggestionList.remove(suggestion);
    }

    /**
     * Gets the list of suggestions submitted by the student.
     *
     * @return An {@code ArrayList} representing the suggestions.
     */
    public ArrayList<Suggestion> getSuggestionList() {
        return suggestionList;
    }
}
