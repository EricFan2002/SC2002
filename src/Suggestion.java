import java.util.List;

import entity.Repository;
import entity.user.Staff;
import entity.user.Student;

public class Suggestion extends Repository {
    @Override
    public Object getByID(String id) {
        return null;
    }

    @Override
    public void save() {

    }

    @Override
    public void load() {

    }

    @Override
    public void add(Object object) {

    }

    @Override
    public void update(Object object) {

    }

    @Override
    public void remove(Object object) {

    }

    @Override
    public List findByFilter(IFilter filter) {
        return null;
    }

    @Override
    public void clear() {

    }

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