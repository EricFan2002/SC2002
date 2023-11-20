package entity.user;

import entity.camp.Camp;
import entity.camp.CampList;

import java.util.ArrayList;

public class Student extends User {
    protected int points;
    private ArrayList<Camp> attendedCampList;
    private ArrayList<Camp> committeeCampList;

    public Student(String ID, String name, String faculty) {
        super(ID, name, faculty);
        points = 0;
        attendedCampList = new ArrayList<Camp>();
        committeeCampList = new ArrayList<Camp>();
    }

    public Student() {
        super();
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
}
