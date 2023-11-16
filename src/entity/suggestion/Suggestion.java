package entity.suggestion;

import entity.camp.Camp;
import entity.camp.CampDetails;
import entity.interfaces.ITaggedItem;
import entity.user.Staff;
import entity.user.Student;

public class Suggestion implements ITaggedItem {
    protected String ID;
    protected Student sender;
    protected CampDetails suggestion;
    protected Camp originalCamp;
    protected Staff reviewedBy;
    protected SuggestionStatus status;

    public Suggestion(Student sender, CampDetails suggsetion, Camp originalCamp) {
        // id is current timestamp in string
        this.ID = Long.toString(System.currentTimeMillis());
        this.sender = sender;
        this.suggestion = suggsetion;
        this.originalCamp = originalCamp;
        this.status = SuggestionStatus.PENDING;
    }

    public Suggestion(String ID, Student sender, CampDetails suggsetion, Camp originalCamp) {
        // id is current timestamp in string
        this.ID = ID;
        this.sender = sender;
        this.suggestion = suggsetion;
        this.originalCamp = originalCamp;
        this.status = SuggestionStatus.PENDING;
    }

    public Suggestion(String ID, Student sender, CampDetails suggestion, Camp originalCamp, Staff reviewedBy,
            SuggestionStatus status) {
        this.ID = ID;
        this.sender = sender;
        this.suggestion = suggestion;
        this.originalCamp = originalCamp;
        this.reviewedBy = reviewedBy;
        this.status = status;
    }

    public String getID() {
        return ID;
    }

    public Student getSender() {
        return sender;
    }

    public CampDetails getSuggestion() {
        return suggestion;
    }

    public Camp getOriginalCamp() {
        return originalCamp;
    }

    public Staff getReviewedBy() {
        return reviewedBy;
    }

    public SuggestionStatus getStatus() {
        return status;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setSender(Student sender) {
        this.sender = sender;
    }

    public void setSuggestion(CampDetails suggestion) {
        this.suggestion = suggestion;
    }

    public void setOriginalCamp(Camp originalCamp) {
        this.originalCamp = originalCamp;
    }

    public void setReviewedBy(Staff reviewedBy) {
        this.reviewedBy = reviewedBy;
    }

    public void setStatus(SuggestionStatus status) {
        this.status = status;
    }

    public void setReviewedBy(Staff reviewedBy, SuggestionStatus status) {
        this.reviewedBy = reviewedBy;
        this.status = status;
    }
}
