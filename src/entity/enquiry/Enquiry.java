package entity.enquiry;

import entity.camp.Camp;
import entity.interfaces.ITaggedItem;
import entity.user.Student;
import entity.user.User;

public class Enquiry implements ITaggedItem {
    protected String ID;
    protected Student sender;
    protected String question;
    protected String answer;
    protected Camp camp;
    protected User answeredBy;

    public Enquiry(String ID, Student sender, String question, Camp camp) {
        this.ID = ID;
        this.sender = sender;
        this.question = question;
        this.camp = camp;
        this.answeredBy = null; // assume not yet answered if it is null
        this.answer = null;
    }

    public Enquiry(String ID, Student sender, String question, String answer, Camp camp, User answeredBy) {
        this.ID = ID;
        this.sender = sender;
        this.question = question;
        this.answer = answer;
        this.camp = camp;
        this.answeredBy = answeredBy; // assume not yet answered if it is null
    }

    public String getID() {
        return ID;
    }

    public Student getSender() {
        return sender;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public Camp getCamp() {
        return camp;
    }

    public User getAnsweredBy() {
        return answeredBy;
    }

    public void setAnswer(String answer, User answeredBy) {
        this.answer = answer;
        this.answeredBy = answeredBy;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setSender(Student sender) {
        this.sender = sender;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setCamp(Camp camp) {
        this.camp = camp;
    }

    public void setAnsweredBy(Student answeredBy) {
        this.answeredBy = answeredBy;
    }
}
