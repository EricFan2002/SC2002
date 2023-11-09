public class Suggestion {
    enum SuggestionStatus {
        OPEN, APPROVED, REJECTED
    }

    private Student sender;
    private Staff reviewedBy; // null if not viewed
    private Camp suggestion;
    private SuggestionStatus status;

    public Suggestion(Student sender, Camp camp) {
        this.sender = sender;
        this.suggestion = camp;
        this.status = SuggestionStatus.OPEN;
        this.reviewedBy = null;
    }

    public Student getSender() {
        return sender;
    }

    public Staff getReviewedBy() {
        return reviewedBy;
    }

    public Camp getSuggestion() {
        return suggestion;
    }

    public SuggestionStatus getStatus() {
        return status;
    }

    public void setSuggestion(Camp camp) {
        this.suggestion = camp;
    }

    public void approveSuggestion(Staff staff) {
        this.status = SuggestionStatus.APPROVED;
        this.reviewedBy = staff;
    }

    public void rejectSuggestion(Staff staff) {
        this.status = SuggestionStatus.REJECTED;
        this.reviewedBy = staff;
    }
}