package entity.user;

import java.util.ArrayList;

import entity.camp.Camp;
import entity.camp.CampList;

/**
 * The {@code Staff} class represents a staff member user who can organize camps.
 * It extends the {@code User} class.
 */
public class Staff extends User {

    /**
     * The list of camps organized by the staff member.
     */
    private ArrayList<Camp> organizedCampList;

    /**
     * Constructs a Staff object with the specified ID, name, and faculty.
     *
     * @param ID      The ID of the staff member.
     * @param name    The name of the staff member.
     * @param faculty The faculty of the staff member.
     */
    public Staff(String ID, String name, String faculty) {
        super(ID, name, faculty);
        organizedCampList = new ArrayList<Camp>();
    }

    /**
     * Constructs a Staff object with default values.
     */
    public Staff() {
        super();
        organizedCampList = new ArrayList<Camp>();
    }

    /**
     * Adds a camp to the list of camps organized by the staff member.
     *
     * @param camp The camp to be added.
     */
    public void addOrganizedCamp(Camp camp) {
        organizedCampList.add(camp);
    }

    /**
     * Removes a camp from the list of camps organized by the staff member.
     *
     * @param camp The camp to be removed.
     */
    public void removeOrganizedCamp(Camp camp) {
        organizedCampList.remove(camp);
    }

    /**
     * Gets the list of camps organized by the staff member.
     *
     * @return A {@code CampList} representing the organized camps.
     */
    public CampList getOrganizedCampList() {
        return new CampList(organizedCampList);
    }
}

