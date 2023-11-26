package app.entity.suggestion;

import app.entity.camp.Camp;
import app.entity.camp.CampDetails;
import app.entity.interfaces.ITaggedItem;
import app.entity.user.Staff;
import app.entity.user.Student;

/**
 * Represents a suggestion made by a student for a camp. This class includes the suggestion's details,
 * the original camp, and the staff member who reviewed it, along with its status.
 */
public class Suggestion implements ITaggedItem {
    protected String ID;
    protected Student sender;
    protected CampDetails suggestion;
    protected Camp originalCamp;
    protected Staff reviewedBy;
    protected SuggestionStatus status;

    /**
     * Creates a new suggestion with the given details.
     *
     * @param sender        The student who made the suggestion.
     * @param suggsetion    The details of the suggestion.
     * @param originalCamp  The camp that the suggestion is for.
     */
    public Suggestion(Student sender, CampDetails suggsetion, Camp originalCamp) {
        // id is current timestamp in string
        this.ID = Long.toString(System.currentTimeMillis());
        this.sender = sender;
        this.suggestion = suggsetion;
        this.originalCamp = originalCamp;
        this.status = SuggestionStatus.PENDING;
    }

    /**
     * Creates a new suggestion with the given details.
     *
     * @param ID            The unique identifier of the suggestion.
     * @param sender        The student who made the suggestion.
     * @param suggestion    The details of the suggestion.
     * @param originalCamp  The camp that the suggestion is for.
     */
    public Suggestion(String ID, Student sender, CampDetails suggestion, Camp originalCamp) {
        // id is current timestamp in string
        this.ID = ID;
        this.sender = sender;
        this.suggestion = suggestion;
        this.originalCamp = originalCamp;
        this.status = SuggestionStatus.PENDING;
    }

    /**
     * Creates a new suggestion with the given details.
     *
     * @param sender        The student who made the suggestion.
     * @param suggestion    The details of the suggestion.
     * @param originalCamp  The camp that the suggestion is for.
     * @param reviewedBy    The staff member who reviewed the suggestion.
     * @param status        The status of the suggestion.
     */
    public Suggestion(String ID, Student sender, CampDetails suggestion, Camp originalCamp, Staff reviewedBy,
            SuggestionStatus status) {
        this.ID = ID;
        this.sender = sender;
        this.suggestion = suggestion;
        this.originalCamp = originalCamp;
        this.reviewedBy = reviewedBy;
        this.status = status;
    }

    /**
     * Returns the unique identifier of the suggestion.
     *
     * @return A string representing the ID of the suggestion.
     */
    public String getID() {
        return ID;
    }

    /**
     * Returns the student who made the suggestion.
     *
     * @return The student who made the suggestion.
     */
    public Student getSender() {
        return sender;
    }

    /**
     * Returns the details of the suggestion.
     *
     * @return The details of the suggestion.
     */
    public CampDetails getSuggestion() {
        return suggestion;
    }

    /**
     * Returns the camp that the suggestion is for.
     *
     * @return The camp that the suggestion is for.
     */
    public Camp getOriginalCamp() {
        return originalCamp;
    }

    /**
     * Returns the staff member who reviewed the suggestion.
     *
     * @return The staff member who reviewed the suggestion.
     */
    public Staff getReviewedBy() {
        return reviewedBy;
    }

    /**
     * Returns the status of the suggestion.
     *
     * @return The status of the suggestion.
     */
    public SuggestionStatus getStatus() {
        return status;
    }

    /**
     * Returns the tag of the suggestion.
     *
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Returns the tag of the suggestion.
     *
     */
    public void setSender(Student sender) {
        this.sender = sender;
    }

    /**
     * Returns the tag of the suggestion.
     *
     */
    public void setSuggestion(CampDetails suggestion) {
        this.suggestion = suggestion;
    }

    /**
     * Returns the tag of the suggestion.
     *
     */
    public void setOriginalCamp(Camp originalCamp) {
        this.originalCamp = originalCamp;
    }

    /**
     * Returns the tag of the suggestion.
     *
     */
    public void setReviewedBy(Staff reviewedBy) {
        this.reviewedBy = reviewedBy;
    }

    /**
     * Returns the tag of the suggestion.
     *
     */
    public void setStatus(SuggestionStatus status) {
        this.status = status;
    }

    /**
     * Returns the tag of the suggestion.
     *
     */
    public void setReviewedBy(Staff reviewedBy, SuggestionStatus status) {
        this.reviewedBy = reviewedBy;
        this.status = status;
    }
}
