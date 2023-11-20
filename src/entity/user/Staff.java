package entity.user;

import java.util.ArrayList;

import entity.camp.Camp;
import entity.camp.CampList;

public class Staff extends User {
    private ArrayList<Camp> organizedCampList;

    public Staff(String ID, String name, String faculty) {
        super(ID, name, faculty);
        organizedCampList = new ArrayList<Camp>();
    }

    public Staff() {
        super();
        organizedCampList = new ArrayList<Camp>();
    }

    public void addOrganizedCamp(Camp camp) {
        organizedCampList.add(camp);
    }

    public void removeOrganizedCamp(Camp camp) {
        organizedCampList.remove(camp);
    }

    public CampList getOrganizedCampList() {
        return new CampList(organizedCampList);
    }

}
