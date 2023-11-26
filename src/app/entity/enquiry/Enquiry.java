package app.entity.enquiry;

import app.entity.camp.Camp;
import app.entity.interfaces.ITaggedItem;
import app.entity.user.Student;
import app.entity.user.User;

/**
 * Represents an enquiry made by a student regarding a specific camp.
 * This class includes details like the ID of the enquiry, the student who made the enquiry, the question, and the answer.
 */
public class Enquiry implements ITaggedItem {
    protected String ID;
    protected Student sender;
    protected String question;
    protected String answer;
    protected Camp camp;
    protected User answeredBy;

    /**
     * Constructs a new Enquiry with the provided ID, sender, question, and associated camp.
     * Initially, the enquiry is not answered.
     *
     * @param ID       Unique identifier for the enquiry.
     * @param sender   The student who sent the enquiry.
     * @param question The question asked in the enquiry.
     * @param camp     The camp related to the enquiry.
     */
    public Enquiry(String ID, Student sender, String question, Camp camp) {
        this.ID = ID;
        this.sender = sender;
        this.question = question;
        this.camp = camp;
        this.answeredBy = null; // assume not yet answered if it is null
        this.answer = null;
    }

    /**
     * Constructs a new Enquiry with the provided details including an answer and the responder.
     *
     * @param ID        Unique identifier for the enquiry.
     * @param sender    The student who sent the enquiry.
     * @param question  The question asked in the enquiry.
     * @param answer    The answer to the enquiry.
     * @param camp      The camp related to the enquiry.
     * @param answeredBy The user who answered the enquiry.
     */
    public Enquiry(String ID, Student sender, String question, String answer, Camp camp, User answeredBy) {
        this.ID = ID;
        this.sender = sender;
        this.question = question;
        this.answer = answer;
        this.camp = camp;
        this.answeredBy = answeredBy; // assume not yet answered if it is null
    }

    /**
     * Returns the ID of the enquiry.
     *
     * @return The unique identifier of the enquiry.
     */
    public String getID() {
        return ID;
    }

    /**
     * Returns the sender of the enquiry.
     *
     * @return The student who sent the enquiry.
     */
    public Student getSender() {
        return sender;
    }

    /**
     * Returns the question of the enquiry.
     *
     * @return The question asked in the enquiry.
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Returns the answer to the enquiry.
     *
     * @return The answer to the enquiry.
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Returns the camp related to the enquiry.
     *
     * @return The camp related to the enquiry.
     */
    public Camp getCamp() {
        return camp;
    }

    /**
     * Returns the user who answered the enquiry.
     *
     * @return The user who answered the enquiry.
     */
    public User getAnsweredBy() {
        return answeredBy;
    }

    /**
     * Returns whether the enquiry has been answered.
     *
     * @param answer The answer to the enquiry.
     * @param answeredBy The user who answered the enquiry.
     */
    public void setAnswer(String answer, User answeredBy) {
        this.answer = answer;
        this.answeredBy = answeredBy;
    }

    /**
     * Returns whether the enquiry has been answered.
     *
     * @param ID The unique identifier of the enquiry.
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Returns whether the enquiry has been answered.
     *
     * @param sender The student who sent the enquiry.
     */
    public void setSender(Student sender) {
        this.sender = sender;
    }

    /**
     * Returns whether the enquiry has been answered.
     *
     * @param question The question asked in the enquiry.
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Returns whether the enquiry has been answered.
     *
     * @param camp The camp related to the enquiry.
     */
    public void setCamp(Camp camp) {
        this.camp = camp;
    }

    /**
     * Returns whether the enquiry has been answered.
     *
     * @param answeredBy The user who answered the enquiry.
     */
    public void setAnsweredBy(Student answeredBy) {
        this.answeredBy = answeredBy;
    }
}
