import java.util.List;

import entity.Repository;
import entity.user.Student;
import entity.user.User;

public class Enquiry extends Repository {
    private Student sender;
    private User answeredBy; // This will be null if not replied to
    private String question;
    private String answer;

    // Constructor
    public Enquiry(Student sender, String question) {
        this.sender = sender;
        this.question = question;
        this.answeredBy = null; // Initially, there's no one who answered
        this.answer = null; // Initially, there's no answer
    }

    // Check if the enquiry has been answered
    public boolean isAnswered() {
        return answeredBy != null && answer != null && !answer.isEmpty();
    }

    // Set the answer
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    // Set the user who answered the enquiry
    public void setAnsweredBy(User user) {
        this.answeredBy = user;
    }

    // Edit the question
    public void editQuestion(String newQuestion) {
        this.question = newQuestion;
    }

    // Getters (and potentially setters) as needed
    public Student getSender() {
        return sender;
    }

    public User getAnsweredBy() {
        return answeredBy;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

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
        if (!isAnswered()) {
            setAnswer(answer);
            setAnsweredBy(answeredBy);
        }
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
}
