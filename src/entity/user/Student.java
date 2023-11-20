package entity.user;

import entity.camp.Camp;
import entity.camp.CampList;
import entity.enquiry.Enquiry;
import entity.suggestion.Suggestion;

import java.util.ArrayList;

public class Student extends User {
    protected int points;
    private ArrayList<Camp> attendedCampList;
    private ArrayList<Camp> committeeCampList;
    private ArrayList<Enquiry> enquiryList;
    private ArrayList<Suggestion> suggestionList;

    public Student(String ID, String name, String faculty) {
        super(ID, name, faculty);
        points = 0;
        attendedCampList = new ArrayList<Camp>();
        committeeCampList = new ArrayList<Camp>();
        enquiryList = new ArrayList<Enquiry>();
        suggestionList = new ArrayList<Suggestion>();
    }

    public Student() {
        super();
        attendedCampList = new ArrayList<Camp>();
        committeeCampList = new ArrayList<Camp>();
        enquiryList = new ArrayList<Enquiry>();
        suggestionList = new ArrayList<Suggestion>();
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addCommitteeCamp(Camp camp) {
        committeeCampList.add(camp);
    }

    public void addAttendedCamp(Camp camp) {
        attendedCampList.add(camp);
    }

    public void removeCommitteeCamp(Camp camp) {
        committeeCampList.remove(camp);
    }

    public void removeAttendedCamp(Camp camp) {
        attendedCampList.remove(camp);
    }

    public CampList getAttendedCampList() {
        return new CampList(attendedCampList);
    }

    public CampList getCommitteeCampList() {
        return new CampList(committeeCampList);
    }

    public void addEnquiry(Enquiry enquiry) {
        enquiryList.add(enquiry);
    }

    public void removeEnquiry(Enquiry enquiry) {
        enquiryList.remove(enquiry);
    }

    public ArrayList<Enquiry> getEnquiryList() {
        return enquiryList;
    }

    public void addSuggestion(Suggestion suggestion) {
        suggestionList.add(suggestion);
    }

    public void removeSuggestion(Suggestion suggestion) {
        suggestionList.remove(suggestion);
    }

    public ArrayList<Suggestion> getSuggestionList() {
        return suggestionList;
    }
}
