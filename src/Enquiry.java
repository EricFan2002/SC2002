public class Enquiry {
    private Student sender;
    private User answeredBy;  // This will be null if not replied to
    private String question;
    private String answer;

    // Constructor
    public Enquiry(Student sender, String question) {
        this.sender = sender;
        this.question = question;
        this.answeredBy = null;  // Initially, there's no one who answered
        this.answer = null;      // Initially, there's no answer
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
}
